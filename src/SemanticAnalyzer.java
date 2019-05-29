import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer implements ASTNodeVisitor {

    @Override
    public String visit(ASTlt node) {
        System.out.println("Displaying ASTlt");

        SimpleNode left = (SimpleNode) node.children[0];
        SimpleNode right = (SimpleNode) node.children[1];

        if (!left.getType().equals("int") || !right.getType().equals("int")) {
            System.err.println("At least one of operands of < is not an int at line " + node.line + ", column "
                    + node.column + ".");
        }
        return null;
    }

    @Override
    public String visit(ASTmult node) {
        System.out.println("Displaying ASTmult");

        SimpleNode left = (SimpleNode) node.children[0];
        SimpleNode right = (SimpleNode) node.children[1];

        if (!left.getType().equals("int") || !right.getType().equals("int")) {
            System.err.println("At least one of operands of * is not an int at line " + node.line + ", column "
                    + node.column + ".");
        }

        return null;
    }

    @Override
    public String visit(ASTnot node) {
        System.out.println("Displaying ASTnot");

        if (node.children[0] != null) {
            if (!((SimpleNode) node.children[0]).getType().equals("bool")) {
                System.err.println("Operand of ! is not a bool at line " + node.line + ", column " + node.column + ".");
            }
        }

        return null;
    }

    @Override
    public String visit(ASTsub node) {
        System.out.println("Displaying ASTsub");

        SimpleNode left = (SimpleNode) node.children[0];
        SimpleNode right = (SimpleNode) node.children[1];

        if (!left.getType().equals("int") || !right.getType().equals("int")) {
            System.err.println("At least one of operands of - is not an int at line " + node.line + ", column "
                    + node.column + ".");
        }
        return null;
    }

    @Override
    public String visit(ASTdiv node) {
        System.out.println("Displaying ASTdiv");

        SimpleNode left = (SimpleNode) node.children[0];
        SimpleNode right = (SimpleNode) node.children[1];

        if (!left.getType().equals("int") || !right.getType().equals("int")) {
            System.err.println("At least one of operands of / is not an int at line " + node.line + ", column "
                    + node.column + ".");
        }

        return null;

    }

    @Override
    public String visit(ASTVarDeclaration node) {
        System.out.println("Displaying ASTVarDeclaration");

        /* Symbol Table insertions */
        String type = ((ASTType) node.children[0]).getType();

        if (!node.addToSymbolTable(node.id, new STVar(-1, node.id, type, -1, -1))) {
            System.err.println(
                    "Variable already defined: " + node.id + " at line " + node.line + ", column " + node.column + ".");
        }
        return null;
    }

    @Override
    public String visit(ASTWhile node) {

        System.out.println("Displaying ASTWhile");
        return null;

    }

    @Override
    public String visit(ASTIf node) {
        System.out.println("Displaying ASTIf");
        return null;

    }

    @Override
    public String visit(ASTid node) {
        System.out.println("Displaying ASTid");

        STEntry entry = node.checkSymbolTable(node.info);

        if (entry == null || (entry.compareTo(new STVar(-1, node.info, "", node.line, node.column)) < 0))
            System.err.println("Variable may not have been initialized: " + node.info + " at line " + node.line
                    + ", column " + node.column + ".");

        return null;
    }

    @Override
    public String visit(ASTsum node) {
        System.out.println("Displaying ASTsum");

        SimpleNode left = (SimpleNode) node.children[0];
        SimpleNode right = (SimpleNode) node.children[1];

        if (!left.getType().equals("int") || !right.getType().equals("int")) {
            System.err.println("At least one of operands of + is not an int at line " + node.line + ", column "
                    + node.column + ".");
        }
        return null;
    }

    @Override
    public String visit(ASTType node) {
        System.out.println("Displaying ASTType");
        return null;

    }

    @Override
    public String visit(ASTStatement node) {
        System.out.println("Displaying ASTStatement");

        if (node.array) {
            if (!((SimpleNode) node.children[0]).getType().equals("int")) {
                System.err.println(
                        "Invalid array access: " + node.id + " at line " + node.line + ", column " + node.column + ".");
            }

            STEntry entry = node.checkSymbolTable(node.id);
            System.out.println("CRL: " + entry.type);
            if (!entry.type.equals("int[]")) {
                System.err.println(node.id + " is not an array at line " + node.line + ", column " + node.column + ".");
            }

        }

        if (node.assign) {
            // entry is the entry of the variable for the value to the left of assign
            STEntry entry = node.checkSymbolTable(node.id);

            Node p = node.parent;
            while (p != null) {
                if ((p instanceof ASTIf) || (p instanceof ASTWhile))
                    break;

                p = ((SimpleNode) p).parent;
            }

            if (p == null && entry.line == -1) {
                entry.line = node.line;
                entry.column = node.column;
            }

            if (entry.type != null) {
                if (entry.type.equals("int[]")) {
                    if (node.children[0] instanceof AST_new) {
                        AST_new new_node = ((AST_new) (node.children[0]));
                        if (new_node.info != "new") {
                            System.err.println("Invalid assignment: expected int array, found " + new_node.info
                                    + " at line " + new_node.line + ", column " + new_node.column + ".");
                        }
                    }

                    if (node.children.length > 1 && !((SimpleNode) node.children[1]).getType().equals("int")) {
                        System.err.println(
                                "Invalid assignment: expected int, found " + ((SimpleNode) node.children[1]).getType()
                                        + " at line " + node.line + ", column " + node.column + ".");
                    }
                } else if (!((SimpleNode) node.children[0]).getType().equals(entry.type)) {
                    System.err.println("Invalid assignment: expected " + entry.type + ", found "
                            + ((SimpleNode) node.children[0]).getType() + " at line " + node.line + ", column "
                            + node.column + ".");
                }
            } else {
                System.err.println(
                        "Undeclared variable: " + node.id + " at line " + node.line + ", column " + node.column + ".");
            }
        }

        if (node.type != null && !node.assign && node.type.equals("id")) {
            STEntry entry = node.checkSymbolTable(node.id);

            if (entry != null && entry.compareTo(new STVar(-1, node.id, node.type, node.line, node.column)) < 0)
                System.err.println("Variable may not have been initialized: " + node.id + " at line " + node.line
                        + ", column " + node.column + ".");
        }

        return null;
    }

    @Override
    public String visit(ASTMethodDeclaration node) {
        System.out.println("Displaying ASTMethodDeclaration");

        List<String> args = new ArrayList<>();
        SimpleNode argsNode = (SimpleNode) node.children[1];

        if (argsNode.children != null)
            for (Node arg : argsNode.children) {
                if (arg instanceof ASTArg) {
                    args.add(((ASTArg) arg).getType());
                }
            }

        String type = null;
        if (node.children[0] instanceof ASTType) {
            type = ((ASTType) (node.children[0])).getType();
        }

        STFunc func = new STFunc(-1, node.id, type, node.line, node.column, args);
        if (!node.addToSymbolTable(node.id, func)) {
            System.err.println(
                    "Function already defined: " + node.id + " at line " + node.line + ", column " + node.column + ".");
        }
        return null;
    }

    @Override
    public String visit(ASTMainDeclaration node) {
        System.out.println("Displaying ASTMainDeclaration");

        /* Symbol Table insertions */

        if (!node.addToSymbolTable(node.id, new STVar(-1, node.id, "String[]", node.line, node.column))) {
            System.out.println(
                    "Variable already defined: " + node.id + " at line " + node.line + ", column " + node.column + ".");
        }
        return null;
    }

    @Override
    public String visit(ASTbool node) {
        System.out.println("Displaying ASTbool");
        return null;

    }

    @Override
    public String visit(ASTArg node) {
        System.out.println("Displaying ASTArg");

        /* Symbol Table insertions */
        String type = ((ASTType) node.children[0]).getType();

        if (!node.addToSymbolTable(node.id, new STVar(-1, node.id, type, node.line, node.column))) {
            System.err.println(
                    "Variable already defined: " + node.id + " at line " + node.line + ", column " + node.column + ".");
        }

        return null;

    }

    @Override
    public String visit(ASTArgs node) {
        System.out.println("Displaying ASTArgs");
        return null;

    }

    @Override
    public String visit(ASTClassDeclaration node) {
        System.out.println("Displaying ASTClassDeclaration");
        return null;

    }

    @Override
    public String visit(AST_new node) {
        System.out.println("Displaying AST_new");
        if (node.type.equals("array")) {
            if (!((SimpleNode) node.children[0]).getType().equals("int")) {
                System.err.println("Invalid array initialization: " + node.id + " at line " + node.line + ", column "
                        + node.column + ".");
            }
        }
        return null;

    }

    @Override
    public String visit(AST_this node) {
        System.out.println("Displaying AST_this");
        return null;

    }

    @Override
    public String visit(ASTand node) {
        System.out.println("Displaying ASTand");

        SimpleNode left = (SimpleNode) node.children[0];
        SimpleNode right = (SimpleNode) node.children[1];

        if (!left.getType().equals("bool") || !right.getType().equals("bool")) {
            System.err.println("At least one of operands of && is not a boolean at line " + node.line + ", column "
                    + node.column + ".");
        }

        return null;

    }

    @Override
    public String visit(ASTfield node) {
        System.out.println("Displaying ASTfield");

        if (node.type.equals("length")) {
            STEntry entry = null;
            if (node.parent instanceof ASTid)
                entry = node.checkSymbolTable(((ASTid) node.parent).info);
            if (entry != null && !entry.type.equals("int[]")) {
                System.err.println("Invalid field access: Expecting int[], found " + entry.type + " at line "
                        + node.line + ", column " + node.column + ".");
            }
        } else {
            List<String> args = new ArrayList<>();
            String type = null;
            if (node.children != null) {
                for (Node arg : node.children) {

                    if (arg instanceof ASTid) {
                        args.add(((ASTid) arg).getType());
                    }

                    if (arg instanceof ASTliteral) {
                        args.add(((ASTliteral) arg).getType());
                    }

                    if (arg instanceof ASTsum) {
                        args.add(((ASTsum) arg).getType());
                    }

                    if (arg instanceof ASTsub) {
                        args.add(((ASTsub) arg).getType());
                    }

                    if (arg instanceof ASTmult) {
                        args.add(((ASTmult) arg).getType());
                    }

                    if (arg instanceof ASTdiv) {
                        args.add(((ASTmult) arg).getType());
                    }
                }
            }
            if (node.checkSymbolTable(node.info) == null) {
                System.err.println("Function not defined: " + node.info + "() at line " + node.line + ", column "
                        + node.column + ".");
            }
        }
        return null;

    }

    @Override
    public String visit(ASTliteral node) {
        System.out.println("Displaying ASTliteral");
        return null;

    }

}