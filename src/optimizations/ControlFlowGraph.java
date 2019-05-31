package optimizations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class ControlFlowGraph {
    ArrayList<Vertex> vertexSet;

    Vertex findVertex(int id) {
        for (Vertex vertex : vertexSet) {
            if (vertex.id == id)
                return vertex;
        }

        return null;
    }

    void addVertex(int id) {
        vertexSet.add(new Vertex(id));
    }

    void addEdge(int src, int dest) {
        Vertex source = findVertex(src);
        Vertex destiny = findVertex(dest);

        if(source == null || destiny == null)
            return;

        source.addEdge(destiny);
    }

    public ArrayList<Vertex> getVertexSet() {
        return vertexSet;
    }

    public void setVertexSet(ArrayList<Vertex> vertexSet) {
        this.vertexSet = vertexSet;
    }
}

class Vertex {
    ArrayList<Edge> succ;
    ArrayList<Edge> pred;
    HashSet<String> out;
    HashSet<String> in;
    HashSet<String> use;
    HashSet<String> def;
    int id;

    public Vertex(int id) {
        succ = new ArrayList<Edge>();
        pred = new ArrayList<Edge>();
        out = new HashSet<String>();
        in = new HashSet<String>();
        use = new HashSet<String>();
        def = new HashSet<String>();
        this.id = id;
    }

    public void addEdge(Vertex dest) {
        Edge edge = new Edge(dest);
        succ.add(edge);
        dest.pred.add(edge);
    }

    public HashSet<String> getOut() {
        return out;
    }

    public void setOut(HashSet<String> out) {
        this.out = out;
    }

    public HashSet<String> getIn() {
        return in;
    }

    public void setIn(HashSet<String> in) {
        this.in = in;
    }

    public HashSet<String> getUse() {
        return use;
    }

    public void setUse(HashSet<String> use) {
        this.use = use;
    }

    public HashSet<String> getDef() {
        return def;
    }

    public void setDef(HashSet<String> def) {
        this.def = def;
    }

    public ArrayList<Edge> getSucc() {
        return succ;
    }

    public void setSucc(ArrayList<Edge> succ) {
        this.succ = succ;
    }

    public ArrayList<Edge> getPred() {
        return pred;
    }

    public void setPred(ArrayList<Edge> pred) {
        this.pred = pred;
    }
}

class Edge {
    Vertex dest;

    public Edge(Vertex dest) {
        this.dest = dest;
    }
}