package optimizations;

import java.util.ArrayList;

public class Graph<T> {
    private ArrayList<Vertex<T>> vertexSet;

    public Graph(Graph<T> graph) {
        this.vertexSet = new ArrayList<Vertex<T>>(graph.vertexSet);
    }

    public Graph() {
        this.vertexSet = new ArrayList<Vertex<T>>();
    }

    public Vertex<T> findVertex(int id) {
        for (Vertex<T> vertex : vertexSet) {
            if (vertex.id == id)
                return vertex;
        }

        return null;
    }

    public void addVertex(Vertex<T> vertex) {
        vertexSet.add(vertex);
    }

    public void addEdge(int src, int dest) {
        Vertex<T> source = findVertex(src);
        Vertex<T> destiny = findVertex(dest);

        if (source == null || destiny == null)
            return;

        source.addEdge(destiny);
    }

    public void freeze(int src, int dest) {
        Vertex<T> source = findVertex(src);
        Vertex<T> destiny = findVertex(dest);

        if (source == null || destiny == null)
            return;

        for (Edge<T> e : source.succ) {
            if (e.dest == destiny) {
                e.type = "";
                return;
            }
        }
    }

    public void addEdge(int src, int dest, String type) {
        Vertex<T> source = findVertex(src);
        Vertex<T> destiny = findVertex(dest);

        if (source == null || destiny == null)
            return;

        source.addEdge(destiny, type);
    }

    public void coalesce(int vertex1, int vertex2) {
        Vertex<T> v1 = findVertex(vertex1);
        Vertex<T> v2 = findVertex(vertex2);

        if (v1 == null || v2 == null)
            return;

        for (Edge<T> e : v2.succ) {
            addEdge(vertex1, e.dest.id);
        }

        for (Edge<T> e : v2.pred) {
            for (Vertex<T> v : vertexSet) {
                if (v.succ.contains(e)) {
                    addEdge(vertex1, v.id);
                    break;
                }
            }
        }

        removeVertex(vertex2);
    }

    public boolean testCoalesce(int vertex1, int vertex2, int k) {
        Graph<T> test = new Graph<T>(this);
        Vertex<T> v1 = findVertex(vertex1);
        Vertex<T> v2 = findVertex(vertex2);

        if (v1 == null || v2 == null)
            return false;

        for (Edge<T> e : v2.succ) {
            test.addEdge(vertex1, e.dest.id);
        }

        for (Edge<T> e : v2.pred) {
            for (Vertex<T> v : test.vertexSet) {
                if (v.succ.contains(e)) {
                    test.addEdge(vertex1, v.id);
                    break;
                }
            }
        }

        test.removeVertex(vertex2);

        return v1.degree() < k;
    }

    public void removeVertex(int id) {
        Vertex<T> v = findVertex(id);
        vertexSet.remove(v);

        for (Vertex<T> vertex : vertexSet) {
            ArrayList<Edge<T>> out = vertex.succ;

            for (Edge<T> edge : out)
                if (edge.dest.equals(v))
                    out.remove(edge);
        }
    }

    public ArrayList<Vertex<T>> getVertexSet() {
        return vertexSet;
    }

    public void setVertexSet(ArrayList<Vertex<T>> vertexSet) {
        this.vertexSet = vertexSet;
    }

    public void printGraph() {
        for (Vertex<T> vertex : vertexSet) {
            System.out.println("VERTEX " + vertex.id);
            System.out.println("EDGES");
            for (Edge<T> edge : vertex.succ) {
                System.out.println("  DEST: " + edge.dest.id);
            }
        }

        System.out.println();
    }
}

class Vertex<T> {
    protected ArrayList<Edge<T>> succ;
    protected ArrayList<Edge<T>> pred;
    protected int id;
    protected T content;

    public Vertex(int id, T content) {
        succ = new ArrayList<Edge<T>>();
        pred = new ArrayList<Edge<T>>();
        this.id = id;
        this.content = content;
    }

    public Vertex() {

    }

    public T getContent() {
        return content;
    }

    public void addEdge(Vertex<T> dest) {
        Edge<T> edge = new Edge<T>(dest);

        // Only one edge between vertices
        if (succ.contains(edge)) {
            return;
        }

        succ.add(edge);
        dest.pred.add(edge);
    }

    public void addEdge(Vertex<T> dest, String type) {
        Edge<T> edge = new Edge<T>(dest, type);

        // Only one edge between vertices
        if (succ.contains(edge)) {
            return;
        }

        succ.add(edge);
        dest.pred.add(edge);
    }

    public int degree() {

        int degree = 0;

        for (Edge<T> edge : succ) {
            if (!edge.type.equals("moveRelated"))
                degree++;
        }

        for (Edge<T> edge : pred) {
            if (!edge.type.equals("moveRelated"))
                degree++;
        }

        return degree;
    }

    public ArrayList<Edge<T>> getSucc() {
        return succ;
    }

    public void setSucc(ArrayList<Edge<T>> succ) {
        this.succ = succ;
    }

    public ArrayList<Edge<T>> getPred() {
        return pred;
    }

    public void setPred(ArrayList<Edge<T>> pred) {
        this.pred = pred;
    }

    public boolean isMoveRelated() {
        for (Edge<T> e : succ) {
            if (e.type.equals("moveRelated"))
                return true;
        }

        for (Edge<T> e : pred) {
            if (e.type.equals("moveRelated"))
                return true;
        }

        return false;
    }
}

class Edge<T> {
    Vertex<T> dest;
    String type;

    public Edge(Vertex<T> dest) {
        this.dest = dest;
        this.type = "";
    }

    public Edge(Vertex<T> dest, String type) {
        this.dest = dest;
        this.type = type;
    }
}