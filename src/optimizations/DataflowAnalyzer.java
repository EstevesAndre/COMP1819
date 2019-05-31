package optimizations;

import java.util.ArrayList;
import java.util.HashSet;

class DataflowAnalyzer {

    public HashSet<String> difference(HashSet<String> lhs, HashSet<String> rhs){
        HashSet<String> diff = lhs;

        for(String str : rhs){
            if(diff.contains(str))
                diff.remove(str);
        }
 
        return diff;
    }

    public void livenessAnalysis(ControlFlowGraph cfg){
        
        ArrayList<HashSet<String>> prevIn = new ArrayList<HashSet<String>>();
        ArrayList<HashSet<String>> currIn = new ArrayList<HashSet<String>>();
        ArrayList<HashSet<String>> prevOut = new ArrayList<HashSet<String>>();
        ArrayList<HashSet<String>> currOut = new ArrayList<HashSet<String>>();

        do{
            ArrayList<Vertex> set = cfg.getVertexSet();
            prevIn = new ArrayList<HashSet<String>>(currIn);
            prevOut = new ArrayList<HashSet<String>>(currOut);

            currIn = new ArrayList<HashSet<String>>();
            currOut = new ArrayList<HashSet<String>>();

            for(int i = set.size() - 1; i >= 0; i--){
                Vertex v = set.get(i);

                HashSet<String> in = v.getIn();
                HashSet<String> out = v.getOut();

                for(Edge edge : v.getSucc()){
                    out.addAll(edge.dest.in);
                }

                in.addAll(v.getUse());
                in.addAll(difference(v.getOut(), v.getDef()));
            
                v.setIn(in);
                v.setOut(out);

                currIn.add(in);
                currOut.add(in);
            }
        }
        while(!prevIn.equals(currIn) && !prevOut.equals(currOut));

        
    }
}