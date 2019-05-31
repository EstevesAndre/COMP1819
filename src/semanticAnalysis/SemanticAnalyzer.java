package semanticAnalysis;


import utils.ASTNodeVisitor;
import parser.*;
import symbolTable.*; 
import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer implements ASTNodeVisitor {

    @Override
    public String visit(ASTlt node) {
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
        if (node.children[0] != null) {
            if (!((SimpleNode) node.children[0]).getType().equals("bool")) {
                System.err.println("Operand of ! is not a bool at line " + node.line + ", column " + node.column + ".");
            }
        }

        return null;
    }

    @Override
    public String visit(ASTsub node) {
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

        return null;

    }

    @Override
    public String visit(ASTIf node) {
        return null;

    }

    @Override
    public String visit(ASTid node) {
        STEntry entry = node.checkSymbolTable(node.info);

        if (entry == null || (entry.compareTo(new STVar(-1, node.info, "", node.line, node.column)) < 0))
            System.err.println("Variable may not have been initialized: " + node.info + " at line " + node.line
                    + ", column " + node.column + ".");

        return null;
    }

    @Override
    public String visit(ASTsum node) {
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
        return null;

    }

    @Override
    public String visit(ASTStatement node) {
        if (node.array) {
            if (!((SimpleNode) node.children[0]).getType().equals("int")) {
                System.err.println(
                        "Invalid array access: " + node.id + " at line " + node.line + ", column " + node.column + ".");
            }

            STEntry entry = node.checkSymbolTable(node.id);
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
        if (!node.addToSymbolTable(node.id + args.size(), func)) {
            System.err.println(
                    "Function already defined: " + node.id + " at line " + node.line + ", column " + node.column + ".");
        }
        return null;
    }

    @Override
    public String visit(ASTMainDeclaration node) {
        /* Symbol Table insertions */

        if (!node.addToSymbolTable(node.id, new STVar(-1, node.id, "String[]", node.line, node.column))) {
            System.out.println(
                    "Variable already defined: " + node.id + " at line " + node.line + ", column " + node.column + ".");
        }
        return null;
    }

    @Override
    public String visit(ASTbool node) {
        return null;

    }

    @Override
    public String visit(ASTArg node) {
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
        return null;

    }

    @Override
    public String visit(ASTClassDeclaration node) {
        return null;

    }

    @Override
    public String visit(AST_new node) { 
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
        return null;

    }

    @Override
    public String visit(ASTand node) {
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
        return null;

    }

}