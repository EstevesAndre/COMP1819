package optimizations;

import java.util.Stack;

public class RegisterAllocator {

    public void registerAllocation(Graph<String> interferenceGraph, int k){
        Stack<String> stack = new Stack<String>();
        Graph<String> copy = new Graph<String>(interferenceGraph);
        boolean simplified = false, coalesced = false;

        simplify(copy, k, stack);

        coalesce(copy, k);

        freeze(copy);

        boolean lowDegree = false;

        for(Vertex<String> vertex : copy.getVertexSet()){
            if(vertex.degree() < k)
                lowDegree = true;
        }

        // if(!lowDegree){
        //     spill();
        // }

        // select();
    }

    private void select(Graph<String> original, Graph<String> graph, Stack<String> stack, int k){
        while(!stack.empty()){
            String v = stack.pop();
            Vertex vertex = original.findVertex(Integer.parseInt(v));

            

        }
    }

    private boolean simplify(Graph<String> graph, int k, Stack<String> stack){
        boolean simplified = false;
        for(Vertex<String> vertex : graph.getVertexSet()){
            if(vertex.degree() < k && !vertex.isMoveRelated()){
                simplified = true;
                graph.removeVertex(vertex.id);
                // stack.add(vertex);
            }
        }

        return simplified;
    }

    private void freeze(Graph<String> graph){
        for(Vertex<String> vertex : graph.getVertexSet()){
            if(vertex.isMoveRelated()){
                for(Edge<String> e : vertex.succ){
                    if(e.type.equals("moveRelated")){
                        graph.freeze(vertex.id, e.dest.id);
                        return;
                    }
                }
            }
        }
    }

    private boolean coalesce(Graph<String> graph, int k){

        boolean coalesced = false;

        for(Vertex<String> vertex : graph.getVertexSet()){
            if(vertex.isMoveRelated()){
                for(Edge<String> e : vertex.succ){
                    if(e.type.equals("moveRelated") && graph.testCoalesce(vertex.id, e.dest.id, k)){
                        graph.coalesce(vertex.id, e.dest.id);
                        coalesced = true;
                    }
                }
            }
        }

        return coalesced;
    }
}