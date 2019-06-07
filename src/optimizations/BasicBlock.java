package optimizations;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class BasicBlock {
    BitSet out;
    BitSet in;
    BitSet use;
    BitSet def;

    public BasicBlock() {
        out = new BitSet();
        in = new BitSet();
        use = new BitSet();
        def = new BitSet();
    }

    public BasicBlock(BitSet use, BitSet def) {
        out = new BitSet();
        in = new BitSet();
        this.use = use;
        this.def = def;
    }

    public void printLiveSets(HashMap<String, Integer> variables) {
        System.out.println("LIVE-IN");
        printSet(in, variables);
        System.out.println("\nLIVE-OUT");
        printSet(out, variables);
    }


    public void printDefUseSets(HashMap<String, Integer> variables) {
        System.out.println("DEF");
        printSet(def, variables);
        System.out.println("\nUSE");
        printSet(use, variables);
    }

    private void printSet(BitSet set, HashMap<String, Integer> variables) {
        String out = "";

        if(set == null)
            return;

        for (Map.Entry<String, Integer> entry : variables.entrySet()) {
            if (set.get(entry.getValue()))
                out += entry.getKey() + " ";
        }

        System.out.println(out);
    }

    public BitSet getOut() {
        return out;
    }

    public void setOut(BitSet out) {
        this.out = out;
    }

    public BitSet getIn() {
        return in;
    }

    public void setIn(BitSet in) {
        this.in = in;
    }

    public BitSet getUse() {
        return use;
    }

    public void setUse(BitSet use) {
        this.use = use;
    }

    public BitSet getDef() {
        return def;
    }

    public void setDef(BitSet def) {
        this.def = def;
    }
}