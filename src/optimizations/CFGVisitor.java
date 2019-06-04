package optimizations;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import parser.*;

public class CFGVisitor {

    int id = 0;

    ArrayList<int[]> edges = new ArrayList<int[]>();

    public static HashMap<String, String> getMoveRelatedVariables(ASTMethodDeclaration node) {
        HashMap<String, String> map = new HashMap<String, String>();

        if (node.children != null) {
            for (Node child : node.children) {
                if (child instanceof ASTStatement) {
                    ASTStatement c = (ASTStatement) child;

                    if (c.assign && c.children[0] instanceof ASTid) {
                        map.put(c.id, ((ASTid) c.children[0]).info);
                    }
                } else {
                    getMoveRelatedVariablesRecursive((SimpleNode) child, map);
                }
            }
        }

        return map;
    }

    public void visit(SimpleNode node) {
        if (node.children != null) {
            for (Node child : node.children) {
                if (child instanceof ASTMethodDeclaration) {
                    System.out.println(((ASTMethodDeclaration) child).id);
                    Graph<BasicBlock> graph = visit((ASTMethodDeclaration) child);
                    graph.printGraph();
                } else if (child instanceof ASTMainDeclaration) {
                    Graph<BasicBlock> graph = visit((ASTMainDeclaration) child);
                    graph.printGraph();
                } else {
                    visit((SimpleNode) child);
                }
            }
        }
    }

    private static void getMoveRelatedVariablesRecursive(SimpleNode node, HashMap<String, String> map) {
        if (node.children != null) {
            for (Node child : node.children) {
                if (child instanceof ASTStatement) {
                    ASTStatement c = (ASTStatement) child;

                    if (c.assign && c.children[0] instanceof ASTid) {
                        map.put(c.id, ((ASTid) c.children[0]).info);
                    }
                } else {
                    getMoveRelatedVariablesRecursive((SimpleNode) child, map);
                }
            }
        }
    }

    public Graph<BasicBlock> visit(ASTMethodDeclaration node) {

        Graph<BasicBlock> graph = new Graph<BasicBlock>();
        int numLocalVars = (node.symtbl.size() + 1);

        HashMap<String, Integer> vars = new HashMap<String, Integer>();

        int i = 0;
        for (String id : node.symtbl.keySet()) {
            vars.put(id, i++);
        }

        BitSet def = new BitSet(numLocalVars);
        BitSet use = new BitSet(numLocalVars);

        for (i = 2; i < node.children.length; i++) {
            SimpleNode child = (SimpleNode) node.children[i];

            if (child instanceof ASTStatement) {
                if (child.children[0] instanceof ASTIf) {
                    graph.addVertex(new Vertex<BasicBlock>(id++, new BasicBlock(use, def)));
                    visit((ASTIf) child.children[0], graph, vars);

                    def.clear();
                    use.clear();
                } else if (child.children[0] instanceof ASTWhile) {
                    graph.addVertex(new Vertex<BasicBlock>(id++, new BasicBlock(use, def)));
                    System.out.println("CARALHO");
                    visit((ASTWhile) child.children[0], graph, vars);

                    def.clear();
                    use.clear();
                } else {
                    getUseSetRecursive( (ASTStatement) child, use, vars);
                    getDefSetRecursive((ASTStatement) child, def, vars);
                }
            }

            else {
                if (child.children != null) {
                    getUseSetRecursive(child, use, vars);
                    getDefSetRecursive(child, def, vars);
                } else if (child instanceof ASTid) {
                    getUseSetRecursive((ASTid) child, use, vars);
                }

            }
        }

        if (!def.isEmpty() || !use.isEmpty()) {
            graph.addVertex(new Vertex<BasicBlock>(id++, new BasicBlock(use, def)));
        }

        for(int[] entry : edges)
        {
            graph.addEdge(entry[0], entry[1]);
        }

        edges.clear();

        id = 0;
        return graph;
    }

    public Graph<BasicBlock> visit(ASTMainDeclaration node) {

        Graph<BasicBlock> graph = new Graph<BasicBlock>();
        int numLocalVars = (node.symtbl.size() + 1);

        HashMap<String, Integer> vars = new HashMap<String, Integer>();

        int i = 0;
        for (String id : node.symtbl.keySet()) {
            vars.put(id, i++);
        }

        BitSet def = new BitSet(numLocalVars);
        BitSet use = new BitSet(numLocalVars);

        for (i = 2; i < node.children.length; i++) {
            SimpleNode child = (SimpleNode) node.children[i];

            if (child instanceof ASTStatement) {
                if (child.children[0] instanceof ASTIf) {
                    graph.addVertex(new Vertex<BasicBlock>(id++, new BasicBlock(use, def)));
                    visit((ASTIf) child.children[0], graph, vars);

                    def.clear();
                    use.clear();
                } else if (child.children[0] instanceof ASTWhile) {
                    graph.addVertex(new Vertex<BasicBlock>(id++, new BasicBlock(use, def)));
                    System.out.println("CARALHO");
                    visit((ASTWhile) child.children[0], graph, vars);

                    def.clear();
                    use.clear();
                } else {
                    getUseSetRecursive( (ASTStatement) child, use, vars);
                    getDefSetRecursive((ASTStatement) child, def, vars);
                }
            } else {
                getUseSetRecursive(child, use, vars);
                getDefSetRecursive(child, def, vars);
            }
        }

        if (!def.isEmpty() || !use.isEmpty()) {
            graph.addVertex(new Vertex<BasicBlock>(id++, new BasicBlock(use, def)));
        }

        for(int[] entry : edges)
        {
            graph.addEdge(entry[0], entry[1]);
        }

        edges.clear();

        id = 0;
        return graph;
    }

    void getUseSetRecursive(ASTid node, BitSet use, HashMap<String, Integer> vars) {
        if (vars.get(node.info) != null)
            use.set(vars.get(node.info));
    }

    void getUseSetRecursive(SimpleNode node, BitSet use, HashMap<String, Integer> vars) {
        if (node.children != null)
            for (Node child : node.children) {
                if (child instanceof ASTid)
                    getUseSetRecursive((ASTid) child, use, vars);
                else
                    getUseSetRecursive((SimpleNode) child, use, vars);
            }
    }

    void getDefSetRecursive(SimpleNode node, BitSet def, HashMap<String, Integer> vars) {
        if (node.children != null)
            for (Node child : node.children) {
                if (child instanceof ASTStatement)
                    getDefSetRecursive((ASTStatement) child, def, vars);
                else
                    getDefSetRecursive((SimpleNode) child, def, vars);
            }
    }

    void getDefSetRecursive(ASTStatement node, BitSet def, HashMap<String, Integer> vars) {
        if (node.assign && vars.get(node.id) != null) {
            def.set(vars.get(node.id));
        }
    }

    public void visit(ASTIf node, Graph<BasicBlock> graph, HashMap<String, Integer> vars) {

        SimpleNode condition = (SimpleNode) node.children[0];

        BitSet use = new BitSet(vars.size());
        getUseSetRecursive(condition, use, vars);
        BasicBlock conditionBlock = new BasicBlock(use, null);
        graph.addVertex(new Vertex<BasicBlock>(id++, conditionBlock));
        edges.add( new int[] {id - 2, id - 1} );

        int first_true_id = id;

        SimpleNode true_statements = (SimpleNode) node.children[1];
        visit(true_statements, graph, vars);
        edges.add( new int[] {first_true_id - 1, first_true_id} );

        int first_false_id = id;

        SimpleNode false_statements = (SimpleNode) node.children[2];
        visit(false_statements, graph, vars);
        edges.add( new int[] {first_true_id - 1, first_false_id} );

        edges.add( new int[] {first_false_id - 1, id });
        edges.add( new int[] {id - 1, id} );

    }

    public void visit(ASTWhile node, Graph<BasicBlock> graph, HashMap<String, Integer> vars) {
        SimpleNode condition = (SimpleNode) node.children[0];

        BitSet use = new BitSet(vars.size());
        getUseSetRecursive(condition, use, vars);
        BasicBlock conditionBlock = new BasicBlock(use, null);
        graph.addVertex(new Vertex<BasicBlock>(id++, conditionBlock));
        edges.add( new int[] {id - 2, id - 1} );

        int first_while_id = id;

        SimpleNode statements = (SimpleNode) node.children[1];
        visit(statements, graph, vars);

        edges.add( new int[] {first_while_id - 1, first_while_id} );
        edges.add( new int[] {first_while_id - 1, id} );
        edges.add( new int[] {id - 1, first_while_id - 1} );


    }

    public void visit(SimpleNode node, Graph<BasicBlock> graph, HashMap<String, Integer> vars) {
        BitSet def = new BitSet(vars.size());
        BitSet use = new BitSet(vars.size());

        if (node.children != null)
            for (int i = 0; i < node.children.length; i++) {
                SimpleNode child = (SimpleNode) node.children[i];

                if (child instanceof ASTStatement) {
                    if (child.children[0] instanceof ASTIf) {
                        graph.addVertex(new Vertex<BasicBlock>(id++, new BasicBlock(use, def)));
                        visit((ASTIf) child.children[0], graph, vars);

                        def.clear();
                        use.clear();
                    } else if (child.children[0] instanceof ASTWhile) {
                        graph.addVertex(new Vertex<BasicBlock>(id++, new BasicBlock(use, def)));
                        System.out.println("CARALHO");
                        visit((ASTWhile) child.children[0], graph, vars);

                        def.clear();
                        use.clear();
                    } else {
                        getUseSetRecursive( (ASTStatement) child, use, vars);
                        getDefSetRecursive((ASTStatement) child, def, vars);
                    }
                }

                else {
                    getUseSetRecursive(child, use, vars);
                    getDefSetRecursive(child, def, vars);
                }
            }

        if (!def.isEmpty() || !use.isEmpty()) {
            graph.addVertex(new Vertex<BasicBlock>(id++, new BasicBlock(use, def)));
        }
    }
}