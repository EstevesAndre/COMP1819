import java.util.ArrayList;
import java.util.List;

public class JasminGenerator implements ASTNodeVisitor {

    @Override
    public String visit(ASTlt node) {
        String out = "";

        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (assign != null)
            if (local == null) {
                if (global != null)
                    out += "aload_0\n";
            }

        out += getJasminRecursive(node);

        int label1 = SimpleNode.getNextLabel();
        int label2 = SimpleNode.getNextLabel();

        out += "if_icmpge LABEL" + label1 + "\n";
        out += "iconst_1\n";
        out += "goto LABEL" + label2 + "\n";
        out += "LABEL" + label1 + ":\niconst_0\n";

        if (local == null) {
            if (global != null)
                out += "LABEL" + label2 + ": putfield " + node.getClassName() + "/" + global.id
                        + SimpleNode.getJasminType(global.type) + "\n";
            else
                out += "LABEL" + label2 + ":\n";
        } else {
            out += "LABEL" + label2 + ":\nistore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTmult node) {
        String out = "";

        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (assign != null)
            if (local == null) {
                if (global != null)
                    out += "aload_0\n";
            }

        out += getJasminRecursive(node);

        if (local == null) {
            if (global != null)
                out += "putfield " + node.getClassName() + "/" + global.id + SimpleNode.getJasminType(global.type)
                        + "\n";
        } else {
            out += "istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTnot node) {
        String out = "";

        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (assign != null)
            if (local == null) {
                if (global != null)
                    out += "aload_0\n";
            }

        out += getJasminRecursive(node);

        int label1 = SimpleNode.getNextLabel();
        int label2 = SimpleNode.getNextLabel();

        out += "ifeq LABEL" + label1 + "\n";
        out += "iconst_0\n";
        out += "goto LABEL" + label2 + "\n";
        out += "LABEL" + label1 + ":\niconst_1\n";

        if (local == null) {
            if (global != null)
                out += "LABEL" + label2 + ": putfield " + node.getClassName() + "/" + global.id
                        + SimpleNode.getJasminType(global.type) + "\n";
            else
                out += "LABEL" + label2 + ":\n";
        } else {
            out += "LABEL" + label2 + ":\nistore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTsub node) {
        String out = "";

        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (assign != null)
            if (local == null) {
                if (global != null)
                    out += "aload_0\n";
            }

        out += getJasminRecursive(node);

        if (local == null) {
            if (global != null)
                out += "putfield " + node.getClassName() + "/" + global.id + " " + SimpleNode.getJasminType(global.type)
                        + "\n";
        } else {
            out += "istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTdiv node) {
        String out = "";

        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (assign != null)
            if (local == null) {
                if (global != null)
                    out += "aload_0\n";
            }

        out += getJasminRecursive(node);

        if (local == null) {
            if (global != null)
                out += "putfield " + node.getClassName() + "/" + global.id + " " + SimpleNode.getJasminType(global.type)
                        + "\n";
        } else {
            out += "istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTVarDeclaration node) {
        String out = "";

        if (node.parent instanceof ASTClassDeclaration) {
            out += ".field public \'" + node.id + "\' "
                    + SimpleNode.getJasminType(((ASTType) (node.children[0])).getType()) + "\n";
        }

        if (node.children != null) {
            for (int i = 0; i < node.children.length; ++i) {
                SimpleNode n = (SimpleNode) node.children[i];
                if (n != null) {
                    out += n.accept(this);
                }
            }
        }

        return out;
    }

    @Override
    public String visit(ASTWhile node) {
        String out = "";
        Node first_child = node.children[0];
        Node statements = node.children[1];
        int label1 = SimpleNode.getNextLabel();
        int label2 = SimpleNode.getNextLabel();

        out += "LABEL" + label1 + ":\n";
        out += getJasminRecursive(node);

        if (first_child instanceof ASTlt) {
            out += "if_icmpge LABEL" + label2 + "\n";
            out += visit((ASTStatement) statements);
            out += "goto LABEL" + label1 + "\n";
            out += "LABEL" + label2 + ":\n";
        } else if (first_child instanceof ASTand) {

        } else if (first_child instanceof ASTfield || first_child instanceof ASTid) {
            out += "ifeq LABEL" + label2 + "\n";
            out += visit((ASTStatement) statements);
            out += "goto LABEL" + label1 + "\n";
            out += "LABEL" + label2 + ":\n";
        } else if (first_child instanceof ASTbool) {
            if(((ASTbool) first_child).info)
                out += "iconst_1\n";
            else
                out += "iconst_0\n";
            out += "ifeq LABEL" + label2 + "\n";
            out += visit((ASTStatement) statements);
            out += "goto LABEL" + label1 + "\n";
            out += "LABEL" + label2 + ":\n";
        }

        return out;
    }

    @Override
    public String visit(ASTIf node) {
        String out = "";

        Node first_child = node.children[0];
        Node true_statements = node.children[1];
        Node else_statements = node.children[2];

        out += getJasminRecursive(node);
        int label1 = SimpleNode.getNextLabel();
        int label2 = SimpleNode.getNextLabel();

        if (first_child instanceof ASTlt) {
            out += "if_icmpge LABEL" + label1 + "\n";
            // codigo dos true_statements
            out += ((SimpleNode) true_statements).accept(this);
            out += "goto LABEL" + label2 + "\n";
            out += "LABEL" + label1 + ":\n";
            // codigo dos else statements
            out += ((SimpleNode) else_statements).accept(this);
        } else if (first_child instanceof ASTnot) {

            if (((ASTnot) first_child).children[0] instanceof ASTlt) {
                out += "if_icmplt LABEL" + label1 + "\n";
            } else {
                out += "ifne LABEL" + label1 + "\n";
            }

            // codigo dos true_statements
            out += ((SimpleNode) true_statements).accept(this);
            out += "goto LABEL" + label2 + "\n";
            out += "LABEL" + label1 + ":\n";
            // codigo dos else statements
            out += ((SimpleNode) else_statements).accept(this);
        } else if (first_child instanceof ASTand) {

        } else if (first_child instanceof ASTfield || first_child instanceof ASTid || first_child instanceof ASTbool
                || first_child instanceof AST_this) {
            out += "ifeq LABEL" + label1 + "\n";
            out += ((SimpleNode) true_statements).accept(this);
            out += "goto LABEL" + label2 + "\n";
            out += "LABEL" + label1 + ":\n";
            out += ((SimpleNode) else_statements).accept(this);
        }

        out += "LABEL" + label2 + ":\n";

        return out;
    }

    @Override
    public String visit(ASTid node) {
        String out = "";
        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        String outinst = "";

        if (assign != null) {
            if (local == null) {
                if (global != null) {
                    out += "aload_0\n";
                    outinst += "putfield " + node.getClassName() + "/" + global.id + " "
                            + SimpleNode.getJasminType(global.type) + "\n";
                }
            } else {
                outinst += "istore " + local.order + "\n";
            }
        }

        if (node.children != null && node.children[0] != null && node.children[0] instanceof ASTfield) {
            return visit((ASTfield) node.children[0]) + outinst;
        }

        out += getJasminRecursive(node);
        out += outinst;

        return out;
    }

    @Override
    public String visit(ASTsum node) {
        String out = "";

        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (assign != null)
            if (local == null) {
                if (global != null)
                    out += "aload_0\n";
            }

        out += getJasminRecursive(node);

        if (local == null) {
            if (global != null)
                out += "putfield " + node.getClassName() + "/" + global.id + " " + SimpleNode.getJasminType(global.type)
                        + "\n";
        } else {
            out += "istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTType node) {
        return "";

    }

    @Override
    public String visit(ASTStatement node) {
        String out = "";

        if(node.assign){
             SimpleNode p = (SimpleNode) node.parent;

            STEntry local = node.checkImediateSymbolTable(node.id);
            STEntry global = node.checkSymbolTable(node.id);
            if(!node.array){

                if(local == null){
                    if(global != null && node.children[0] instanceof AST_this){
                        out += "aload_0\n";
                    }
                }
                else{
                    
                }

            }else{
        Node index = node.children[0];
            Node val = node.children[1];

            if (local == null) {
                if (global != null) {
                    out += "aload_0\n";
                        out += "getfield " + node.getClassName() + "/" + global.id + " "
                                + SimpleNode.getJasminType(global.type) + "\n";

                    out += getJasminRecursive(index);
                    out += getJasminRecursive(val);

                        out += "iastore\n";
                    return out;
                }
            } else {
                out += "aload " + local.order + "\n";

                out += getJasminRecursive(index);

                out += getJasminRecursive(val);

                out += "iastore\n";

                return out;
            }
            }
        }

        if (node.children != null) {
            for (int i = 0; i < node.children.length; ++i) {
                SimpleNode n = (SimpleNode) node.children[i];
                if (n != null) {
                    out += n.accept(this);
                }
            }
        }

        if (node.assign && node.children != null && node.children[0] instanceof AST_this) {
            STEntry local = node.checkImediateSymbolTable(node.id);
            STEntry global = node.checkSymbolTable(node.id);

            if (local == null) {
                out += "putfield " + node.getClassName() + "/" + global.id + " " + SimpleNode.getJasminType(global.type)
                        + "\n";
            } else if (local.type.equals("int") || local.type.equals("bool")) {
                out += "istore " + local.order + "\n";
            } else {
                out += "astore " + local.order + "\n";
            }

        }
        return out;
    }

    @Override
    public String visit(ASTMethodDeclaration node) {
        String out = ".method public " + node.id + "(";

        ASTArgs args = (ASTArgs) node.children[1];

        int numArgs = 0;

        if (args.children != null)
            for (Node arg : args.children) {
                out += SimpleNode.getJasminType(((ASTType) (((ASTArg) (arg)).children[0])).getType());
                numArgs++;
            }

        out += ")" + SimpleNode.getJasminType(((ASTType) (node.children[0])).getType()) + "\n";

        out += ".limit stack 100\n"; // TODO: melhorar na proxima entrega
        out += ".limit locals " + (node.symtbl.size() + 1) + "\n";

        String st_id = new String(node.id);

        List<String> argsList = new ArrayList<>();
        SimpleNode argsNode = (SimpleNode) node.children[1];

        if (argsNode.children != null)
            for (Node arg : argsNode.children) {
                if (arg instanceof ASTArg) {
                    argsList.add(((ASTArg) arg).getType());
                }
            }

        STEntry ret_val = node.checkSymbolTable(st_id + numArgs);

        if (node.children != null) {
            for (int i = 0; i < node.children.length; ++i) {
                SimpleNode n = (SimpleNode) node.children[i];

                if (i == node.children.length - 1 && !ret_val.type.equals("void"))
                    break;

                if (n != null) {
                    out += n.accept(this);
                }
            }
        }

        Node return_value = node.children[node.children.length - 1];

        if (return_value instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) return_value).info + "\n";
        } else if (return_value instanceof ASTbool) {
            out += "iconst_" + (((ASTbool) return_value).info ? 1 : 0) + "\n";
        } else if (return_value instanceof ASTid) {

            String info = ((ASTid) return_value).info;
            STEntry entry = node.checkImediateSymbolTable(info);
            if (entry != null) {
                if (entry.type.equals("int") || entry.type.equals("bool"))
                    out += "iload " + entry.order + "\n";
                else
                    out += "aload " + entry.order + "\n";
            } else {
                entry = node.checkSymbolTable(info);
                if (entry != null) {
                    out += "aload_0\n";
                    out += "getfield " + node.getClassName() + "/" + entry.id + " "
                            + SimpleNode.getJasminType(entry.type) + "\n";
                }
            }
        } else {
            out += ((SimpleNode) return_value).accept(this);
        }

        if (ret_val != null) {

            switch (ret_val.type) {
            case "bool":
            case "int":
                out += "ireturn";
                break;
            case "void":
                out += "return";
                break;
            default:
                out += "areturn";
                break;
            }

        }
        return out + "\n.end method\n\n";

    }

    private String getJasminRecursive(ASTfield node) {
        if (node.type.equals("length"))
            return "arraylength\n";

        String out = "";

        SimpleNode n = (SimpleNode) node.parent;

        if(n instanceof AST_this)
            out += "aload_0\n";

        if(node.children != null)
            for (Node child : node.children) {
                out += getJasminRecursive(child);
            }

        String id = null;

        boolean classType = false;

        if (n instanceof ASTStatement) {
            id = ((ASTStatement) n).id;
        } else if (n instanceof ASTid) {
            id = ((ASTid) n).info;
        }
        else if(n instanceof AST_this){
            Node p = node.parent;
            while(!(p instanceof ASTClassDeclaration))
                p = ((SimpleNode) p).parent;
            id = ((ASTClassDeclaration) p).id;
            classType = true;
        }

        STEntry entry = null;
        entry = node.checkImediateSymbolTable(id);

        if (node.checkSymbolTable(id) != null || classType) {
            out += "invokevirtual ";

        } else {
            out += "invokestatic ";
        }

        if (entry != null)
            out += entry.type;
        else
            out += id;

        out += "/" + node.info + "(";

        List<String> args = new ArrayList<>();
        String type = null;

        int numArgs = 0;

        if (node.children != null) {
            for (Node arg : node.children) {
                args.add(((SimpleNode) arg).getType());
                numArgs++;
            }
        }

        entry = node.checkSymbolTable(node.info + numArgs);

        if (entry != null) {

            for (String arg : ((STFunc) entry).argTypes) {
                out += SimpleNode.getJasminType(arg);
            }

            out += ")" + SimpleNode.getJasminType(entry.type) + "\n";

            if (((SimpleNode) node.parent).parent instanceof ASTStatement
                    && !((ASTStatement) ((SimpleNode) node.parent).parent).assign)
                out += "pop\n";

        } else {
            if (node.children != null) {
                for (Node arg : node.children) {

                    if (arg instanceof ASTid) {
                        if (((SimpleNode) arg).children != null && ((SimpleNode) arg).children[0] instanceof ASTfield) {
                            STEntry func = node.checkSymbolTable(((ASTfield) ((SimpleNode) arg).children[0]).info
                                    + (((ASTfield) ((SimpleNode) arg).children[0]).children != null
                                            ? ((ASTfield) ((SimpleNode) arg).children[0]).children.length
                                            : 0));
                            if (func != null) {
                                out += SimpleNode.getJasminType(func.type);
                            }
                        } else {
                            String t = ((SimpleNode) arg).getType();
                            if (t.equals("Error"))
                                out += "L" + ((ASTid) arg).info + ";";
                            else
                                out += SimpleNode.getJasminType(t);
                        }
                    } else if (arg instanceof ASTliteral) {
                        out += SimpleNode.getJasminType(((SimpleNode) arg).getType());
                    } else if (arg instanceof ASTsum)
                        out += SimpleNode.getJasminType(((ASTsum) arg).getType());
                    else if (arg instanceof ASTsub)
                        out += SimpleNode.getJasminType(((ASTsub) arg).getType());
                    else if (arg instanceof ASTmult)
                        out += SimpleNode.getJasminType(((ASTmult) arg).getType());
                    else if (arg instanceof ASTdiv)
                        out += SimpleNode.getJasminType(((ASTdiv) arg).getType());
                }
            }

            if (((SimpleNode) node.parent).parent instanceof ASTStatement
                    && ((ASTStatement) ((SimpleNode) node.parent).parent).assign) {
                if (((ASTStatement) ((SimpleNode) node.parent).parent).array) {
                    out += ")I\n";
                } else {
                    out += ")"
                            + SimpleNode.getJasminType(
                                    node.checkSymbolTable(((ASTStatement) ((SimpleNode) node.parent).parent).id).type)
                            + "\n";
                }
            } else if (((SimpleNode) node.parent).parent instanceof ASTsum
                    || ((SimpleNode) node.parent).parent instanceof ASTdiv
                    || ((SimpleNode) node.parent).parent instanceof ASTmult
                    || ((SimpleNode) node.parent).parent instanceof ASTsub
                    || ((SimpleNode) node.parent).parent instanceof ASTlt) {
                out += ")I\n";
            } else
                out += ")V\n";
        }

        return out;
    }

    private String getJasminRecursive(ASTid node) {
        String info = ((ASTid) node).info;
        STEntry entry = node.checkImediateSymbolTable(info);
        String out = "";

        if (entry != null) {
            if (entry.type.equals("int") || entry.type.equals("bool"))
                out += "iload " + entry.order + "\n";
            else
                out += "aload " + entry.order + "\n";
        } else {
            entry = node.checkSymbolTable(info);
            if (entry != null) {
                out += "aload_0\n";
                out += "getfield " + node.getClassName() + "/" + entry.id + " " + SimpleNode.getJasminType(entry.type)
                        + "\n";
            }
        }
        if (node.children != null) {

            out += getJasminRecursive(node.children[0]);

            if (!(node.children[0] instanceof ASTfield)) {
                out += "iaload\n";

            }
        }

        return out;
    }

    public String getJasminRecursive(Node node) {
        String out = "";
        if (node instanceof ASTliteral) {
            out = "ldc " + ((ASTliteral) node).info + "\n";
        } else if (node instanceof ASTsum)
            out = getJasminRecursive((ASTsum) node);
        else if (node instanceof ASTsub)
            out = getJasminRecursive((ASTsub) node);
        else if (node instanceof ASTmult)
            out = getJasminRecursive((ASTmult) node);
        else if (node instanceof ASTdiv)
            out = getJasminRecursive((ASTdiv) node);
        else if (node instanceof ASTid)
            out = getJasminRecursive((ASTid) node);
        else if (node instanceof ASTfield)
            out = getJasminRecursive((ASTfield) node);
        else if (node instanceof AST_this)
            out += getJasminRecursive((ASTfield) ((AST_this) node).children[0]);

        return out;
    }

    @Override
    public String visit(ASTMainDeclaration node) {
        String out = ".method static public main(" + SimpleNode.getJasminType("String[]") + ")"
                + SimpleNode.getJasminType("void") + "\n";

        out += ".limit stack 100\n"; // TODO: rever na proxima entrega
        out += ".limit locals " + (node.symtbl.size() + 1) + "\n";

        if (node.children != null) {
            for (int i = 0; i < node.children.length; ++i) {
                SimpleNode n = (SimpleNode) node.children[i];
                if (n != null) {
                    out += n.accept(this);
                }
            }
        }

        out += "return\n.end method\n\n";

        return out;
    }

    @Override
    public String visit(ASTbool node) {
        String out = "";
        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (assign != null) {
            if (local == null) {
                if (global != null) {
                    out += "aload_0\n";
                    out += "iconst_" + (node.info ? 1 : 0) + "\n";
                    out += "putfield " + node.getClassName() + "/" + global.id + " "
                            + SimpleNode.getJasminType(global.type) + "\n";
                }
            } else {
                out += "iconst_" + (node.info ? 1 : 0) + "\n";
                out += "istore " + local.order + "\n";
            }
        }

        return out;
    }

    @Override
    public String visit(ASTArg node) {
        return "";

    }

    @Override
    public String visit(ASTArgs node) {
        return "";

    }

    @Override
    public String visit(ASTClassDeclaration node) {
        String out = ".class public " + node.id + "\n";
        out += ".super " + (node.ext != null ? node.ext : "java/lang/Object") + "\n";
        String ext = (node.ext != null ? node.ext : "java/lang/Object");
        if (node.children != null) {
            for (int i = 0; i < node.children.length; ++i) {
                SimpleNode n = (SimpleNode) node.children[i];
                if (n != null) {
                    out += n.accept(this);
                }
            }
        }

        if (node.checkSymbolTable(node.id) == null) {
            out += "; standard initializer\n";
            out += ".method public <init>()V\n";
            out += "aload_0\n";

            if (node.ext == null)
                out += "invokenonvirtual java/lang/Object/<init>()V\n";
            else
                out += "invokespecial " + ext + "/<init>()V\n";

            out += "return\n";
            out += ".end method\n\n";
        }

        return out;
    }

    @Override
    public String visit(AST_new node) {
        String out = "";

        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (node.type.equals("array")) {

            SimpleNode first_child = (SimpleNode) node.children[0];

            if (assign != null) {
                if (local == null) {
                    if (global != null) {
                        out += "aload_0\n";

                        if (first_child instanceof ASTliteral) {
                            out += "ldc " + ((ASTliteral) first_child).info + "\n";
                        } else
                            out += getJasminRecursive(first_child);

                        out += "newarray int\n";
                        out += "putfield " + node.getClassName() + "/" + global.id + " "
                                + SimpleNode.getJasminType(global.type) + "\n";
                    }
                } else {

                    if (first_child instanceof ASTliteral) {
                        out += "ldc " + ((ASTliteral) first_child).info + "\n";
                    } else
                        out += getJasminRecursive(first_child);

                    out += "newarray int\n";
                    out += "astore " + local.order + "\n";
                }
            }
        } else {

            if (assign != null) {
                if (local == null) {
                    if (global != null) {
                        out += "aload_0\n";
                        out += "new\ndup\n";
                        out += "invokespecial ";
                        out += "putfield " + node.getClassName() + "/" + global.id + " "
                                + SimpleNode.getJasminType(global.type) + "\n";
                    }
                } else {
                    out += "new " + node.info + "\n";
                    out += "dup\n";
                    out += "invokespecial " + node.info + "/<init>()V" + "\n";
                    if (node.children == null)
                        out += "astore " + local.order + "\n";
                }
            }
        }

        if (node.children != null && !node.type.equals("array"))
            for (int i = 0; i < node.children.length; ++i) {
                SimpleNode n = (SimpleNode) node.children[i];
                if (n != null) {
                    out += n.accept(this);
                }
            }

        if (node.children != null && node.children[0] instanceof ASTfield)
            if (local == null) {
                out += "putfield " + node.getClassName() + "/" + global.id + " " + SimpleNode.getJasminType(global.type)
                        + "\n";
            } else {
                out += "istore " + local.order + "\n";
            }

        return out;

    }

    @Override
    public String visit(AST_this node) {
        return "";

    }

    @Override
    public String visit(ASTand node) {
        String out = "";

        if (node.parent instanceof ASTand)
            return out;

        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (assign != null)
            if (local == null) {
                if (global != null)
                    out += "aload_0\n";
            }

        int label1 = SimpleNode.getNextLabel();
        int label2 = SimpleNode.getNextLabel();

        out += getJasminRecursive(node, true, label1);

        if (node.children[1] instanceof ASTlt)
            out += "if_icmpge LABEL" + label1 + "\n";
        else if (node.children[1] instanceof ASTnot)
            out += "ifne LABEL" + label1 + "\n";
        else
            out += "ifeq LABEL" + label1 + "\n";

        out += "iconst_1\n";
        out += "goto LABEL" + label2 + "\n";
        out += "LABEL" + label1 + ":\niconst_0\n";

        if (local == null) {
            if (global != null)
                out += "LABEL" + label2 + ": putfield " + node.getClassName() + "/" + global.id + " "
                        + SimpleNode.getJasminType(global.type) + "\n";
            else
                out += "LABEL" + label2 + ":\n";
        } else {
            out += "LABEL" + label2 + ":\nistore " + local.order + "\n";
        }

        return out;

    }

    @Override
    public String visit(ASTfield node) {
        String out = "";

        SimpleNode n = (SimpleNode) node.parent;
        String id = null;

        if (n instanceof ASTStatement) {
            id = ((ASTStatement) n).id;
        } else if (n instanceof ASTid) {
            id = ((ASTid) n).info;
        }

        if (node.type.equals("length")) {
            out += getJasminRecursive(n);

            String assign = null;
            if (n.parent instanceof ASTStatement)
                assign = ((ASTStatement) n.parent).id;

            STEntry local = node.checkImediateSymbolTable(assign);
            STEntry global = node.checkSymbolTable(assign);

            if (assign != null) {

                if (local == null) {
                    if (global != null) {
                        out += "aload_0\n";
                        out += "putfield " + node.getClassName() + "/" + global.id + " "
                                + SimpleNode.getJasminType(global.type) + "\n";
                    }
                } else {
                    // out += "istore " + local.order + "\n";
                }

            }
            return out;
        }

        if (id != null) {
            STEntry entry = null;
            entry = node.checkImediateSymbolTable(id);
            if (node.checkSymbolTable(id) != null) {
                out += "aload " + entry.order + "\n";
            }
        } else if (!(node.parent instanceof AST_new)) {
            out += "aload_0\n";
        }

        if (id != null) {
            STEntry entry = null;
            entry = node.checkImediateSymbolTable(id);
            if (node.checkSymbolTable(id) != null) {

                if (node.children != null) {
                    for (Node arg : node.children) {
                        if (arg instanceof ASTid) {
                            String arg0 = ((ASTid) arg).info;
                            STEntry local_0 = node.checkImediateSymbolTable(arg0);
                            STEntry global_0 = node.checkSymbolTable(arg0);

                            if (local_0 == null && global_0 != null) {
                                out += "aload_0\n";
                                out += "getfield " + node.getClassName() + "/" + global_0.id + " "
                                        + SimpleNode.getJasminType(global_0.type) + "\n";
                            } else {
                                out += visit((ASTid) arg);
                            }
                        } else if (arg instanceof ASTbool)
                            out += "iconst_" + (((ASTbool) arg).info ? 1 : 0) + "\n";
                        else if (arg instanceof ASTlt)
                            out += getJasminRecursive((ASTlt) arg);
                        else if (arg instanceof ASTand)
                            out += getJasminRecursive((ASTand) arg, false, 0);
                        else if (arg instanceof ASTnot)
                            out += getJasminRecursive((ASTnot) arg);
                        else if (arg instanceof AST_this)
                            out += visit((ASTfield) ((AST_this) arg).children[0]);
                        else
                            out += getJasminRecursive(arg);

                    }
                }

                out += "invokevirtual ";
            } else {

                if (node.children != null) {
                    for (Node arg : node.children) {
                        if (arg instanceof ASTid) {
                            out += getJasminRecursive((ASTid) arg);
                        } else if (arg instanceof ASTbool) {
                            out += "iconst_" + (((ASTbool) arg).info ? 1 : 0) + "\n";
                        } else if (arg instanceof ASTlt)
                            out += getJasminRecursive((ASTlt) arg);
                        else if (arg instanceof ASTand)
                            out += getJasminRecursive((ASTand) arg, false, 0);
                        else if (arg instanceof ASTnot)
                            out += getJasminRecursive((ASTnot) arg);
                        else if (arg instanceof AST_this)
                            out += visit((ASTfield) ((AST_this) arg).children[0]);
                        else if (arg instanceof ASTliteral)
                            out += "ldc " + ((ASTliteral) arg).info + "\n";
                        else if (arg instanceof ASTsum)
                            out += getJasminRecursive((ASTsum) arg);
                        else if (arg instanceof ASTsub)
                            out += getJasminRecursive((ASTsub) arg);
                        else if (arg instanceof ASTmult)
                            out += getJasminRecursive((ASTmult) arg);
                        else if (arg instanceof ASTdiv)
                            out += getJasminRecursive((ASTdiv) arg);
                    }
                }
                out += "invokestatic ";
            }

            if (entry != null)
                out += entry.type;
            else
                out += id;
        } else {
            while (n != null) {
                if (n instanceof ASTClassDeclaration) {

                    if (node.children != null) {
                        for (Node arg : node.children) {
                            if (arg instanceof ASTid) {
                                String arg0 = ((ASTid) arg).info;
                                STEntry local_0 = node.checkImediateSymbolTable(arg0);
                                STEntry global_0 = node.checkSymbolTable(arg0);

                                    out += getJasminRecursive((ASTid) arg);
                            } else if (arg instanceof ASTbool) {
                                out += "iconst_" + (((ASTbool) arg).info ? 1 : 0) + "\n";
                            } else if (arg instanceof ASTlt)
                                out += getJasminRecursive((ASTlt) arg);
                            else if (arg instanceof ASTand)
                                out += getJasminRecursive((ASTand) arg, false, 0);
                            else if (arg instanceof ASTnot)
                                out += getJasminRecursive((ASTnot) arg);
                            else if (arg instanceof AST_this)
                                out += visit((ASTfield) ((AST_this) arg).children[0]);
                            else if (arg instanceof ASTliteral)
                                out += "ldc " + ((ASTliteral) arg).info + "\n";
                            else if (arg instanceof ASTsum)
                                out += getJasminRecursive((ASTsum) arg);
                            else if (arg instanceof ASTsub)
                                out += getJasminRecursive((ASTsub) arg);
                            else if (arg instanceof ASTmult)
                                out += getJasminRecursive((ASTmult) arg);
                            else if (arg instanceof ASTdiv)
                                out += getJasminRecursive((ASTdiv) arg);
                        }
                    }

                    out += "invokevirtual " + ((ASTClassDeclaration) n).id;
                    break;
                }
                n = (SimpleNode) ((SimpleNode) n).parent;
            }
        }

        out += "/" + node.info + "(";

        List<String> args = new ArrayList<>();
        String type = null;

        int numArgs = 0;

        if (node.children != null) {
            for (Node arg : node.children) {
                args.add(((SimpleNode) arg).getType());
                numArgs++;
            }
        }

        STEntry entry = node.checkSymbolTable(node.info + numArgs);

        if (entry != null) {

            for (String arg : ((STFunc) entry).argTypes) {
                out += SimpleNode.getJasminType(arg);
            }

            out += ")" + SimpleNode.getJasminType(entry.type) + "\n";

            if (((SimpleNode) node.parent).parent instanceof ASTStatement
                    && !((ASTStatement) ((SimpleNode) node.parent).parent).assign)
                out += "pop\n";
            else if (((SimpleNode) node.parent) instanceof ASTStatement
                    && !((ASTStatement) ((SimpleNode) node.parent)).assign)
                out += "pop\n";

        } else {
            if (node.children != null) {
                for (Node arg : node.children) {

                    if (arg instanceof ASTid) {
                        if (((SimpleNode) arg).children != null && ((SimpleNode) arg).children[0] instanceof ASTfield) {
                            STEntry func = node.checkSymbolTable(((ASTfield) ((SimpleNode) arg).children[0]).info
                                    + (((ASTfield) ((SimpleNode) arg).children[0]).children != null
                                            ? ((ASTfield) ((SimpleNode) arg).children[0]).children.length
                                            : 0));
                            if (func != null) {
                                out += SimpleNode.getJasminType(func.type);
                            }
                        } else {
                            String t = ((SimpleNode) arg).getType();
                            if (t.equals("Error"))
                                out += "L" + ((ASTid) arg).info + ";";
                            else
                                out += SimpleNode.getJasminType(t);
                        }
                    } else if (arg instanceof ASTliteral) {
                        out += SimpleNode.getJasminType(((SimpleNode) arg).getType());
                    } else if (arg instanceof ASTsum)
                        out += SimpleNode.getJasminType(((ASTsum) arg).getType());
                    else if (arg instanceof ASTsub)
                        out += SimpleNode.getJasminType(((ASTsub) arg).getType());
                    else if (arg instanceof ASTmult)
                        out += SimpleNode.getJasminType(((ASTmult) arg).getType());
                    else if (arg instanceof ASTdiv)
                        out += SimpleNode.getJasminType(((ASTdiv) arg).getType());
                }
            }

            if (((SimpleNode) node.parent).parent instanceof ASTStatement
                    && ((ASTStatement) ((SimpleNode) node.parent).parent).assign) {
                out += ")"
                        + SimpleNode.getJasminType(
                                node.checkSymbolTable(((ASTStatement) ((SimpleNode) node.parent).parent).id).type)
                        + "\n";
            } else
                out += ")V\n";
        }

        return out;
    }

    @Override
    public String visit(ASTliteral node) {
        String out = "";
        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;

        if (p instanceof ASTStatement) {
            assign = ((ASTStatement) p).id;
        }

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (assign != null) {
            if (local == null) {
                if (global != null) {
                    out += "aload_0\n";
                    out += "ldc " + node.info + "\n";
                    out += "putfield " + node.getClassName() + "/" + global.id + " "
                            + SimpleNode.getJasminType(global.type) + "\n";
                }
            } else {
                out += "ldc " + node.info + "\n";
                out += "istore " + local.order + "\n";
            }
        }

        return out;
    }

    public String getJasminRecursive(ASTand node, boolean assign, int label) {
        String out = "";

        Node first_child = node.children[0];
        Node second_child = node.children[1];

        if (first_child instanceof ASTid) {
            out += visit((ASTid) (node.children[0]));

        } else if (first_child instanceof ASTbool) {
            out += "iconst_" + (((ASTbool) first_child).info ? 1 : 0) + "\n";
        } else if (first_child instanceof ASTlt)
            out += getJasminRecursive((ASTlt) first_child);
        else if (first_child instanceof ASTand)
            out += getJasminRecursive((ASTand) first_child, false, 0);
        else if (first_child instanceof ASTnot)
            out += getJasminRecursive((ASTnot) first_child);
        else if (first_child instanceof AST_this)
            out += visit((ASTfield) ((AST_this) first_child).children[0]);

        if (assign) {
            if (first_child instanceof ASTlt)
                out += "if_icmpge LABEL" + label + "\n";
            else if (first_child instanceof ASTnot)
                out += "ifne LABEL" + label + "\n";
            else
                out += "ifeq LABEL" + label + "\n";
        }

        if (second_child instanceof ASTid) {
            out += visit((ASTid) (node.children[0]));

        } else if (second_child instanceof ASTbool) {
            out += "iconst_" + (((ASTbool) second_child).info ? 1 : 0) + "\n";
        } else if (second_child instanceof ASTlt)
            out += getJasminRecursive((ASTlt) second_child);
        else if (second_child instanceof ASTnot)
            out += getJasminRecursive((ASTnot) second_child);
        else if (second_child instanceof ASTand)
            out += getJasminRecursive((ASTand) second_child, false, 0);
        else if (second_child instanceof AST_this)
            out += visit((ASTfield) ((AST_this) second_child).children[0]);

        return out;
    }

    String getJasminRecursive(ASTdiv node) {
        String out = "";

        Node first_child = node.children[0];
        Node second_child = node.children[1];

        if (first_child instanceof ASTid) {
            out += visit((ASTid) (node.children[0]));

        } else
            out += getJasminRecursive(first_child);

        if (second_child instanceof ASTid) {
            out += visit((ASTid) (node.children[1]));

        } else
            out += getJasminRecursive(second_child);

        out += "idiv\n";

        return out;
    }

    public String getJasminRecursive(ASTIf node) {
        String out = "";
        Node first_child = node.children[0];

        if (first_child instanceof ASTid) {
            out += visit((ASTid) (node.children[0]));

        } else if (first_child instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) first_child).info + "\n";
        } else if (first_child instanceof ASTlt) {

            out += getJasminRecursive((ASTlt) first_child);
        } else if (first_child instanceof ASTand) {
            out += getJasminRecursive((ASTand) first_child, false, 0);
        } else if (first_child instanceof ASTnot) {
            out += getJasminRecursive((ASTnot) first_child);
        } else if (first_child instanceof AST_this)
            out += visit((ASTfield) ((AST_this) first_child).children[0]);

        return out;
    }

    String getJasminRecursive(ASTlt node) {
        String out = "";

        Node first_child = node.children[0];
        Node second_child = node.children[1];

        out += getJasminRecursive(first_child);
        out += getJasminRecursive(second_child);

        return out;
    }

    String getJasminRecursive(ASTmult node) {
        String out = "";

        if (node.children[0] instanceof ASTid) {
            out += visit((ASTid) (node.children[0]));

        } else
            out += getJasminRecursive(node.children[0]);

        if (node.children[1] instanceof ASTid) {
            String arg1 = ((ASTid) node.children[1]).info;
            STEntry local_1 = node.checkImediateSymbolTable(arg1);
            STEntry global_1 = node.checkSymbolTable(arg1);

            if (local_1 == null) {
                if (global_1 != null) {
                    out += "aload_0\n";
                    out += "getfield " + node.getClassName() + "/" + global_1.id + " "
                            + SimpleNode.getJasminType(global_1.type) + "\n";
                }
            } else {
                out += "iload " + local_1.order + "\n";
            }
        } else
            out += getJasminRecursive(node.children[1]);

        out += "imul\n";

        return out;
    }

    String getJasminRecursive(ASTsub node) {
        String out = "";

        if (node.children[0] instanceof ASTid) {
            out += visit((ASTid) (node.children[0]));
        } else
            out += getJasminRecursive(node.children[0]);

        if (node.children[1] instanceof ASTid) {
            String arg1 = ((ASTid) node.children[1]).info;
            STEntry local_1 = node.checkImediateSymbolTable(arg1);
            STEntry global_1 = node.checkSymbolTable(arg1);

            if (local_1 == null) {
                if (global_1 != null) {
                    out += "aload_0\n";
                    out += "getfield " + node.getClassName() + "/" + global_1.id + " "
                            + SimpleNode.getJasminType(global_1.type) + "\n";
                }
            } else {
                out += "iload " + local_1.order + "\n";
            }
        } else
            out += getJasminRecursive(node.children[1]);

        out += "isub\n";

        return out;
    }

    String getJasminRecursive(ASTsum node) {
        String out = "";

        out += getJasminRecursive(node.children[0]);

        out += getJasminRecursive(node.children[1]);

        out += "iadd\n";

        return out;
    }

    public String getJasminRecursive(ASTWhile node) {
        String out = "";
        Node first_child = node.children[0];

        if (first_child instanceof ASTid) {
            out += visit((ASTid) (node.children[0]));

        } else if (first_child instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) first_child).info + "\n";
        } else if (first_child instanceof ASTlt) {
            out += getJasminRecursive((ASTlt) first_child);
        } else if (first_child instanceof ASTand) {
            out += getJasminRecursive((ASTand) first_child, false, 0);
        } else if (first_child instanceof AST_this)
            out += visit((ASTfield) ((AST_this) first_child).children[0]);
        return out;
    }

    public String getJasminRecursive(ASTnot node) {
        String out = "";

        Node first_child = node.children[0];

        if (first_child instanceof ASTid) {
            out += visit((ASTid) (node.children[0]));

        } else if (first_child instanceof ASTbool) {
            out += "iconst_" + (((ASTbool) first_child).info ? 1 : 0) + "\n";
        } else if (first_child instanceof ASTlt)
            out += getJasminRecursive((ASTlt) first_child);
        else if (first_child instanceof ASTand)
            out += visit((ASTand) first_child);
        else if (first_child instanceof ASTnot)
            out += getJasminRecursive((ASTnot) first_child);
        else if (first_child instanceof AST_this)
            out += visit((ASTfield) ((AST_this) first_child).children[0]);

        return out;
    }

}