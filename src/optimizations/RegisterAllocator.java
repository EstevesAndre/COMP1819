package optimizations;

import java.util.HashMap;
import java.util.Stack;

public class RegisterAllocator {

    public static HashMap<String, Integer> registerAllocation(Graph<String> interferenceGraph, int k){
        Stack<String> stack = new Stack<String>();
        Graph<String> copy = new Graph<String>(interferenceGraph);
        HashMap<String, Integer> coloring = new HashMap<String, Integer>();

        simplify(copy, k, stack);

        if(copy.getVertexSet().isEmpty()){
            return null;
        }

        select(interferenceGraph, coloring, stack, k);

        return coloring;
    }

    private static void select(Graph<String> original, HashMap<String, Integer> coloring, Stack<String> stack, int k){
        while(!stack.empty()){
            String v = stack.pop();
            Vertex<String> vertex = new Vertex<String>(0, null);

            for(Vertex<String> ver : original.getVertexSet()){
                if(ver.content.equals(v))
                {
                    vertex = ver;
                    break;
                }
            }

            for(int i = 0; i < k; i++)
            {
                boolean found = false;
                for(Edge<String> edge : vertex.getSucc()){
                    if(coloring.get(edge.dest) != null && coloring.get(edge.dest) == k)
                    {
                        found = true;
                        break;
                    }
                }

                if(!found)
                {
                    coloring.put(v, k);
                    break;
                }
            }
        }
    }

    private static void simplify(Graph<String> graph, int k, Stack<String> stack){
        boolean simplified = false;
        while(true)
        {
            for(Vertex<String> vertex : graph.getVertexSet()){
                if(vertex.degree() < k && !vertex.isMoveRelated()){
                    graph.removeVertex(vertex.id);
                    stack.push(vertex.content);
                    simplified = true;
                    break;
                }
            }

            if(!simplified)
                break;
            
            simplified = false;
        }
    }

    // private void freeze(Graph<String> graph){
    //     for(Vertex<String> vertex : graph.getVertexSet()){
    //         if(vertex.isMoveRelated()){
    //             for(Edge<String> e : vertex.succ){
    //                 if(e.type.equals("moveRelated")){
    //                     graph.freeze(vertex.id, e.dest.id);
    //                     return;
    //                 }
    //             }
    //         }
    //     }
    // }

    // private boolean coalesce(Graph<String> graph, int k){

    //     boolean coalesced = false;

    //     for(Vertex<String> vertex : graph.getVertexSet()){
    //         if(vertex.isMoveRelated()){
    //             for(Edge<String> e : vertex.succ){
    //                 if(e.type.equals("moveRelated") && graph.testCoalesce(vertex.id, e.dest.id, k)){
    //                     graph.coalesce(vertex.id, e.dest.id);
    //                     coalesced = true;
    //                 }
    //             }
    //         }
    //     }

    //     return coalesced;
    // }
}