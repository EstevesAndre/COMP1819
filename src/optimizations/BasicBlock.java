package optimizations;

import java.util.BitSet;

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