package optimizations;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class DataflowAnalyzer {

    ArrayList<BitSet> currIn = new ArrayList<BitSet>();
    ArrayList<BitSet> currOut = new ArrayList<BitSet>();

    public BitSet difference(BitSet lhs, BitSet rhs) {
        BitSet diff = (BitSet) lhs.clone(); 

        for(int i = 0; i < diff.size(); i++)
            if(rhs.get(i) && diff.get(i)){
                diff.set(i, false);
            }

        return diff;
    }

    private boolean containsPair(HashMap<String, String> vars, HashMap<String, Integer> variables, int lhs, int rhs){
        
        String lhsId = "", rhsId = "";

        for(Map.Entry<String, Integer> entry : variables.entrySet()){
            if(entry.getValue() == lhs){
                lhsId = entry.getKey();
            }
            else if(entry.getValue() == rhs){
                rhsId = entry.getKey();
            }
        }


        return vars.get(lhsId).equals(rhsId) || vars.get(rhsId).equals(lhsId);
    }

    public Graph<String> getInterferenceGraph(HashMap<String, String> moveRelatedVars, HashMap<String, Integer> variables) {
        Graph<String> graph = new Graph<String>();
        ArrayList<String> liveRanges = new ArrayList<String>();

        for(Integer i : variables.values()){
            graph.addVertex(new Vertex<String>(i, "Oi"));
            liveRanges.add(getVariableLiveRange(variables.get(i)));
        }

        for(int i = 0; i < variables.size(); i++){
            
            for(int j = i + 1; j < liveRanges.size(); j++){
                if(intersect(liveRanges.get(i), liveRanges.get(j)))
                    graph.addEdge(i, j);
                else if(containsPair(moveRelatedVars, variables, i, j))
                    graph.addEdge(i, j, "moveRelated");
            }
        }

        return graph;
    }

    private boolean intersect(String lhs, String rhs){
        String[] split1 = lhs.split("-");
        String[] split2 = rhs.split("-");

        int leftLimit1 = Integer.parseInt(split1[0]);
        int rightLimit1 = Integer.parseInt(split1[1]);
        int leftLimit2 = Integer.parseInt(split1[0]);
        int rightLimit2 = Integer.parseInt(split2[1]);

        return !(leftLimit2 > rightLimit1 || leftLimit1 > rightLimit2); 
    }

    public String getVariableLiveRange(int var) {
        String range = "";

        for (int i = currOut.size() - 1; i >= 0; i--)
            if (currOut.get(i).get(var)) {
                range += i + "-";
                break;
            }

        for (int i = 0; i < currIn.size(); i++)
            if (currIn.get(i).get(var)) {
                range += i;
                break;
            }

        return range;
    }

    public void livenessAnalysis(Graph<BasicBlock> cfg) {

        ArrayList<BitSet> prevIn = new ArrayList<BitSet>();
        currIn = new ArrayList<BitSet>();
        ArrayList<BitSet> prevOut = new ArrayList<BitSet>();
        currOut = new ArrayList<BitSet>();

        do {
            ArrayList<Vertex<BasicBlock>> set = cfg.getVertexSet();
            prevIn = new ArrayList<BitSet>(currIn);
            prevOut = new ArrayList<BitSet>(currOut);

            currIn = new ArrayList<BitSet>();
            currOut = new ArrayList<BitSet>();

            for (int i = set.size() - 1; i >= 0; i--) {
                Vertex<BasicBlock> v = set.get(i);
                BasicBlock vertex = (BasicBlock) set.get(i).getContent();

                BitSet in = vertex.getIn();
                BitSet out = vertex.getOut();

                for (Edge<BasicBlock> edge : v.getSucc()) {
                    out.or(((BasicBlock) edge.dest.getContent()).in);
                }

                in.or(vertex.getUse());
                in.or(difference(vertex.getOut(), vertex.getDef()));

                vertex.setIn(in);
                vertex.setOut(out);

                currIn.add(in);
                currOut.add(in);
            }
        } while (!prevIn.equals(currIn) && !prevOut.equals(currOut));

    }
}