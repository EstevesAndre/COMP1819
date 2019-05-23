import java.util.ArrayList;
import java.util.List;

public class JasminGenerator implements ASTNodeVisitor {

    @Override
    public String visit(ASTlt node) {
        System.out.println("Displaying ASTlt");

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
        out += "LABEL" + label1 + ": iconst_0\n";

        if (local == null) {
            if (global != null)
                out += "LABEL" + label2 + ": putfield " + assign + "/" + global.order + "\n";
        } else {
            out += "LABEL" + label2 + ": istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTmult node) {
        System.out.println("Displaying ASTmult");

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
                out += "putfield " + assign + "/" + global.order + "\n";
        } else {
            out += "istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTnot node) {
        System.out.println("Displaying ASTnot");

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
        out += "LABEL" + label1 + ": iconst_1\n";

        if (local == null) {
            if (global != null)
                out += "LABEL" + label2 + ": putfield " + assign + "/" + global.order + "\n";
        } else {
            out += "LABEL" + label2 + ": istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTsub node) {
        System.out.println("Displaying ASTsub");
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
                out += "putfield " + assign + "/" + global.order + "\n";
        } else {
            out += "istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTdiv node) {
        System.out.println("Displaying ASTdiv");

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
                out += "putfield " + assign + "/" + global.order + "\n";
        } else {
            out += "istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTVarDeclaration node) {
        System.out.println("Displaying ASTVarDeclaration");

        String out = "";

        if (node.parent instanceof ASTClassDeclaration) {
            out += ".field public " + node.id + " " + SimpleNode.getJasminType(((ASTType) (node.children[0])).getType())
                    + "\n";
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
        System.out.println("Displaying ASTWhile");

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
            out += "LABEL" + label2 + "\n";
        } else if (first_child instanceof ASTand) {

        } else if (first_child instanceof ASTfield || first_child instanceof ASTid || first_child instanceof ASTbool) {
            out += "ifeq LABEL" + label2 + "\n";
            out += visit((ASTStatement) statements);
            out += "goto LABEL" + label1 + "\n";
            out += "LABEL" + label2 + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTIf node) {
        System.out.println("Displaying ASTIf");

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
            out += "ifne LABEL" + label1 + "\n";
            // codigo dos true_statements
            out += ((SimpleNode) true_statements).accept(this);
            out += "goto LABEL" + label2 + "\n";
            out += "LABEL" + label1 + ":\n";
            // codigo dos else statements
            out += ((SimpleNode) else_statements).accept(this);
        } else if (first_child instanceof ASTand) {

        } else if (first_child instanceof ASTfield || first_child instanceof ASTid || first_child instanceof ASTbool) {
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
        System.out.println("Displaying ASTid");
        return "";

    }

    @Override
    public String visit(ASTsum node) {
        System.out.println("Displaying ASTsum");

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
                out += "putfield " + assign + "/" + global.order + "\n";
        } else {
            out += "istore " + local.order + "\n";
        }

        return out;
    }

    @Override
    public String visit(ASTType node) {
        System.out.println("Displaying ASTType");
        return "";

    }

    @Override
    public String visit(ASTStatement node) {
        System.out.println("Displaying ASTStatement");

        String out = "";

        if (node.array && node.assign) {
            System.out.println("CENAS");
            SimpleNode p = (SimpleNode) node.parent;

            String assign = null;
            if (p instanceof ASTStatement)
                assign = ((ASTStatement) p).id;

            STEntry local = node.checkImediateSymbolTable(assign);
            STEntry global = node.checkSymbolTable(assign);

            Node index = node.children[0];
            Node val = node.children[1];

            if (assign != null) {
                if (local == null) {
                    if (global != null) {
                        out += "aload_0\n";

                        if (index instanceof ASTid) {
                            out += "ldc " + ((ASTid) index).info;
                        } else if (index instanceof ASTliteral) {
                            out += "ldc " + ((ASTliteral) index).info;
                        } else {
                            out += ((SimpleNode) index).accept(this);
                        }

                        if (val instanceof ASTid) {
                            out += "ldc " + ((ASTid) val).info;
                        } else if (val instanceof ASTliteral) {
                            out += "ldc " + ((ASTliteral) val).info;
                        } else {
                            out += ((SimpleNode) val).accept(this);
                        }
                        out += "putfield " + assign + "/" + global.order + "\n";
                    }
                } else {
                    out += "aload \n" + local.order + "\n";

                    if (index instanceof ASTid) {
                        out += "ldc " + ((ASTid) index).info;
                    } else if (index instanceof ASTliteral) {
                        out += "ldc " + ((ASTliteral) index).info;
                    } else {
                        out += ((SimpleNode) index).accept(this);
                    }

                    if (val instanceof ASTid) {
                        out += "ldc " + ((ASTid) val).info;
                    } else if (val instanceof ASTliteral) {
                        out += "ldc " + ((ASTliteral) val).info;
                    } else {
                        out += ((SimpleNode) val).accept(this);
                    }

                    out += "iastore\n";
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

        return out;
    }

    @Override
    public String visit(ASTMethodDeclaration node) {
        System.out.println("Displaying ASTMethodDeclaration");

        String out = ".method public " + node.id + "(";

        ASTArgs args = (ASTArgs) node.children[1];

        if (args.children != null)
            for (Node arg : args.children) {
                out += SimpleNode.getJasminType(((ASTType) (((ASTArg) (arg)).children[0])).getType());
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

        for (String arg : argsList) {
            st_id += " " + arg;
        }

        STEntry ret_val = node.checkSymbolTable(st_id);

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
                if (entry.type == "int")
                    out += "iload " + entry.order + "\n";
                else
                    out += "aload " + entry.order + "\n";
            } else {
                entry = node.checkSymbolTable(info);
                if (entry != null) {
                    out += "aload_0\n";
                    out += "getfield " + node.getClassName() + "/" + entry.order + " "
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
        return out + "\n.end method\n";

    }

    @Override
    public String visit(ASTMainDeclaration node) {
        System.out.println("Displaying ASTMainDeclaration");

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

        out += "return\n.end method\n";

        return out;
    }

    @Override
    public String visit(ASTbool node) {
        System.out.println("Displaying ASTbool");

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
                    out += "putfield " + assign + "/" + global.order + "\n";
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
        System.out.println("Displaying ASTArg");
        return "";

    }

    @Override
    public String visit(ASTArgs node) {
        System.out.println("Displaying ASTArgs");
        return "";

    }

    @Override
    public String visit(ASTClassDeclaration node) {
        System.out.println("Displaying ASTClassDeclaration");

        String out = ".class public " + node.id + "\n";

        out += ".super " + (node.ext != null ? node.ext : "java/lang/Object") + "\n";

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
    public String visit(AST_new node) {
        System.out.println("Displaying AST_new");

        String out = "";

        SimpleNode p = (SimpleNode) node.parent;

        String assign = null;
        if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;

        STEntry local = node.checkImediateSymbolTable(assign);
        STEntry global = node.checkSymbolTable(assign);

        if (node.type.equals("array")) {

            int size = Integer.parseInt(((ASTliteral) node.children[0]).info);

            if (assign != null) {
                if (local == null) {
                    if (global != null) {
                        out += "aload_0\n";
                        out += "ldc " + size + "\n";
                        out += "newarray int\n";
                        out += "putfield " + assign + "/" + global.order + "\n";
                    }
                } else {
                    out += "ldc " + size + "\n";
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
                        out += "putfield " + assign + "/" + global.order + "\n";
                    }
                } else {
                    out += "new " + node.info + "\n";
                    out += "dup\n";
                    out += "invokespecial " + node.info + "/<init>()V" + "\n";
                    out += "astore " + local.order + "\n";
                }
            }
        }

        return out;

    }

    @Override
    public String visit(AST_this node) {
        System.out.println("Displaying AST_this");
        return "";

    }

    @Override
    public String visit(ASTand node) {
        System.out.println("Displaying ASTand");
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
        else
            out += "ifeq LABEL" + label1 + "\n";

        out += "iconst_1\n";
        out += "goto LABEL" + label2 + "\n";
        out += "LABEL" + label1 + ": iconst_0\n";

        if (local == null) {
            if (global != null)
                out += "LABEL" + label2 + ": putfield " + assign + "/" + global.order + "\n";
        } else {
            out += "LABEL" + label2 + ": istore " + local.order + "\n";
        }

        return out;

    }

    @Override
    public String visit(ASTfield node) {
        System.out.println("Displaying ASTfield");

        String out = "";
       
        Node n = node.parent;
        String id = null;

        if(n instanceof ASTStatement)
        {
            id = ((ASTStatement) n).id;
        }
        else if (n instanceof ASTid)
        {
            id = ((ASTid) n).info;
        }

        if(id != null)
        {
            STEntry entry = null;
            entry = node.checkImediateSymbolTable(((ASTStatement) n).id);
            if (node.checkSymbolTable(((ASTStatement) node.parent).id) != null) {
                out += "aload " + entry.order + "\n";
                out += "invokevirtual ";
            } else {
                out += "invokestatic ";
            }

            if (entry != null)
                out += entry.type;
            else
                out += ((ASTStatement) node.parent).id;
        }
        else
        {
            while (n != null) {
                if (n instanceof ASTClassDeclaration) {
                    out += "aload_0\n";
                    out += "invokevirtual " + ((ASTClassDeclaration) n).id;
                    break;
                }
                n = ((SimpleNode) n).parent;
            }
        }

        out += "/" + node.info + "(";

        List<String> args = new ArrayList<>();
        String type = null;

        if (node.children != null) {
            for (Node arg : node.children) {
                args.add(((SimpleNode) arg).getType());
            }
        }

        System.out.println(node.info + " - " + type);

        STFunc func = new STFunc(-1, node.info, type, node.line, node.column, args);
        STEntry entry = node.checkSymbolTable(func.getKeyName());

        if (entry != null) {
            if (node.children != null) {
                for (Node arg : node.children) {

                    if (arg instanceof ASTid) {
                        String t = ((SimpleNode) arg).getType();
                        if (t.equals("Error"))
                            out += "L" + ((ASTid) arg).info + ";";
                        else
                            out += SimpleNode.getJasminType(t);
                    }

                    else if (arg instanceof ASTliteral) {
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

            out += ")" + SimpleNode.getJasminType(entry.type);
        }

        if (entry == null)
            out += ")V";

        out += "\n";
        return out;
    }

    @Override
    public String visit(ASTliteral node) {
        System.out.println("Displaying ASTliteral");

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
                    out += "ldc " + node.info + "\n";
                    out += "putfield " + assign + "/" + global.order + "\n";
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
            String arg0 = ((ASTid) first_child).info;
            STEntry local_0 = node.checkImediateSymbolTable(arg0);
            STEntry global_0 = node.checkSymbolTable(arg0);

            if (local_0 == null) {
                if (global_0 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg0 + "/" + global_0.order + "\n";
                }
            } else {
                out += "iload " + local_0.order + "\n";
            }
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
            else
                out += "ifeq LABEL" + label + "\n";
        }

        if (second_child instanceof ASTid) {
            String arg1 = ((ASTid) second_child).info;
            STEntry local_1 = node.checkImediateSymbolTable(arg1);
            STEntry global_1 = node.checkSymbolTable(arg1);

            if (local_1 == null) {
                if (global_1 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg1 + "/" + global_1.order + "\n";
                }
            } else {
                out += "iload " + local_1.order + "\n";
            }
        } else if (second_child instanceof ASTbool) {
            out += "iconst_" + (((ASTbool) second_child).info ? 1 : 0) + "\n";
        } else if (second_child instanceof ASTlt)
            out += getJasminRecursive((ASTlt) second_child);
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
            String arg0 = ((ASTid) first_child).info;
            STEntry local_0 = node.checkImediateSymbolTable(arg0);
            STEntry global_0 = node.checkSymbolTable(arg0);

            if (local_0 == null) {
                if (global_0 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg0 + "/" + global_0.order + "\n";
                }
            } else {
                out += "iload " + local_0.order + "\n";
            }
        } else if (first_child instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) first_child).info + "\n";
        } else if (first_child instanceof ASTsum)
            out += getJasminRecursive((ASTsum) first_child);
        else if (first_child instanceof ASTsub)
            out += getJasminRecursive((ASTsub) first_child);
        else if (first_child instanceof ASTmult)
            out += getJasminRecursive((ASTmult) first_child);
        else if (first_child instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) first_child);
        else if (first_child instanceof AST_this)
            out += visit((ASTfield) ((AST_this) first_child).children[0]);

        if (second_child instanceof ASTid) {
            String arg1 = ((ASTid) second_child).info;
            STEntry local_1 = node.checkImediateSymbolTable(arg1);
            STEntry global_1 = node.checkSymbolTable(arg1);

            if (local_1 == null) {
                if (global_1 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg1 + "/" + global_1.order + "\n";
                }
            } else {
                out += "iload " + local_1.order + "\n";
            }
        } else if (second_child instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) second_child).info + "\n";
        } else if (second_child instanceof ASTsum)
            out += getJasminRecursive((ASTsum) second_child);
        else if (second_child instanceof ASTsub)
            out += getJasminRecursive((ASTsub) second_child);
        else if (second_child instanceof ASTmult)
            out += getJasminRecursive((ASTmult) second_child);
        else if (second_child instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) second_child);
        else if (second_child instanceof AST_this)
            out += visit((ASTfield) ((AST_this) second_child).children[0]);

        out += "idiv\n";

        return out;
    }

    public String getJasminRecursive(ASTIf node) {
        String out = "";
        Node first_child = node.children[0];

        if (first_child instanceof ASTid) {
            String arg0 = ((ASTid) first_child).info;
            STEntry local_0 = node.checkImediateSymbolTable(arg0);
            STEntry global_0 = node.checkSymbolTable(arg0);

            if (local_0 == null) {
                if (global_0 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg0 + "/" + global_0.order + "\n";
                }
            } else {
                out += "iload " + local_0.order + "\n";
            }
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

        if (first_child instanceof ASTid) {
            String arg0 = ((ASTid) first_child).info;
            STEntry local_0 = node.checkImediateSymbolTable(arg0);
            STEntry global_0 = node.checkSymbolTable(arg0);

            if (local_0 == null) {
                if (global_0 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg0 + "/" + global_0.order + "\n";
                }
            } else {
                out += "iload " + local_0.order + "\n";
            }
        } else if (first_child instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) first_child).info + "\n";
        } else if (first_child instanceof AST_this)
            out += visit((ASTfield) ((AST_this) first_child).children[0]);
        else if (first_child instanceof ASTsum)
            out += getJasminRecursive((ASTsum) first_child);
        else if (first_child instanceof ASTsub)
            out += getJasminRecursive((ASTsub) first_child);
        else if (first_child instanceof ASTmult)
            out += getJasminRecursive((ASTmult) first_child);
        else if (first_child instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) first_child);

        if (second_child instanceof ASTid) {
            String arg1 = ((ASTid) second_child).info;
            STEntry local_1 = node.checkImediateSymbolTable(arg1);
            STEntry global_1 = node.checkSymbolTable(arg1);

            if (local_1 == null) {
                if (global_1 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg1 + "/" + global_1.order + "\n";
                }
            } else {
                out += "iload " + local_1.order + "\n";
            }
        } else if (second_child instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) second_child).info + "\n";
        } else if (second_child instanceof AST_this)
            out += visit((ASTfield) ((AST_this) second_child).children[0]);
        else if (second_child instanceof ASTsum)
            out += getJasminRecursive((ASTsum) second_child);
        else if (second_child instanceof ASTsub)
            out += getJasminRecursive((ASTsub) second_child);
        else if (second_child instanceof ASTmult)
            out += getJasminRecursive((ASTmult) second_child);
        else if (second_child instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) second_child);

        return out;
    }

    String getJasminRecursive(ASTmult node) {
        String out = "";

        if (node.children[0] instanceof ASTid) {
            String arg0 = ((ASTid) node.children[0]).info;
            STEntry local_0 = node.checkImediateSymbolTable(arg0);
            STEntry global_0 = node.checkSymbolTable(arg0);

            if (local_0 == null) {
                if (global_0 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg0 + "/" + global_0.order + "\n";
                }
            } else {
                out += "iload " + local_0.order + "\n";
            }
        } else if (node.children[0] instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) node.children[0]).info + "\n";
        } else if (node.children[0] instanceof AST_this)
            out += visit((ASTfield) ((AST_this) node.children[0]).children[0]);
        else if (node.children[0] instanceof ASTsum)
            out += getJasminRecursive((ASTsum) node.children[0]);
        else if (node.children[0] instanceof ASTsub)
            out += getJasminRecursive((ASTsub) node.children[0]);
        else if (node.children[0] instanceof ASTmult)
            out += getJasminRecursive((ASTmult) node.children[0]);
        else if (node.children[0] instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) node.children[0]);

        if (node.children[1] instanceof ASTid) {
            String arg1 = ((ASTid) node.children[1]).info;
            STEntry local_1 = node.checkImediateSymbolTable(arg1);
            STEntry global_1 = node.checkSymbolTable(arg1);

            if (local_1 == null) {
                if (global_1 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg1 + "/" + global_1.order + "\n";
                }
            } else {
                out += "iload " + local_1.order + "\n";
            }
        } else if (node.children[1] instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) node.children[1]).info + "\n";
        } else if (node.children[1] instanceof AST_this)
            out += visit((ASTfield) ((AST_this) node.children[1]).children[0]);
        else if (node.children[1] instanceof ASTsum)
            out += getJasminRecursive((ASTsum) node.children[1]);
        else if (node.children[1] instanceof ASTsub)
            out += getJasminRecursive((ASTsub) node.children[1]);
        else if (node.children[1] instanceof ASTmult)
            out += getJasminRecursive((ASTmult) node.children[1]);
        else if (node.children[1] instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) node.children[1]);

        out += "imul\n";

        return out;
    }

    String getJasminRecursive(ASTsub node) {
        String out = "";

        if (node.children[0] instanceof ASTid) {
            String arg0 = ((ASTid) node.children[0]).info;
            STEntry local_0 = node.checkImediateSymbolTable(arg0);
            STEntry global_0 = node.checkSymbolTable(arg0);

            if (local_0 == null) {
                if (global_0 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg0 + "/" + global_0.order + "\n";
                }
            } else {
                out += "iload " + local_0.order + "\n";
            }
        } else if (node.children[0] instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) node.children[0]).info + "\n";
        } else if (node.children[0] instanceof AST_this)
            out += visit((ASTfield) ((AST_this) node.children[0]).children[0]);
        else if (node.children[0] instanceof ASTsum)
            out += getJasminRecursive((ASTsum) node.children[0]);
        else if (node.children[0] instanceof ASTsub)
            out += getJasminRecursive((ASTsub) node.children[0]);
        else if (node.children[0] instanceof ASTmult)
            out += getJasminRecursive((ASTmult) node.children[0]);
        else if (node.children[0] instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) node.children[0]);

        if (node.children[1] instanceof ASTid) {
            String arg1 = ((ASTid) node.children[1]).info;
            STEntry local_1 = node.checkImediateSymbolTable(arg1);
            STEntry global_1 = node.checkSymbolTable(arg1);

            if (local_1 == null) {
                if (global_1 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg1 + "/" + global_1.order + "\n";
                }
            } else {
                out += "iload " + local_1.order + "\n";
            }
        } else if (node.children[1] instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) node.children[1]).info + "\n";
        } else if (node.children[1] instanceof AST_this)
            out += visit((ASTfield) ((AST_this) node.children[1]).children[0]);
        else if (node.children[1] instanceof ASTsum)
            out += getJasminRecursive((ASTsum) node.children[1]);
        else if (node.children[1] instanceof ASTsub)
            out += getJasminRecursive((ASTsub) node.children[1]);
        else if (node.children[1] instanceof ASTmult)
            out += getJasminRecursive((ASTmult) node.children[1]);
        else if (node.children[1] instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) node.children[1]);

        out += "isub\n";

        return out;
    }

    String getJasminRecursive(ASTsum node) {
        String out = "";

        if (node.children[0] instanceof ASTid) {
            String arg0 = ((ASTid) node.children[0]).info;
            STEntry local_0 = node.checkImediateSymbolTable(arg0);
            STEntry global_0 = node.checkSymbolTable(arg0);

            if (local_0 == null) {
                if (global_0 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg0 + "/" + global_0.order + "\n";
                }
            } else {
                out += "iload " + local_0.order + "\n";
            }
        } else if (node.children[0] instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) node.children[0]).info + "\n";
        } else if (node.children[0] instanceof AST_this)
            out += visit((ASTfield) ((AST_this) node.children[0]).children[0]);
        else if (node.children[0] instanceof ASTsum)
            out += getJasminRecursive((ASTsum) node.children[0]);
        else if (node.children[0] instanceof ASTsub)
            out += getJasminRecursive((ASTsub) node.children[0]);
        else if (node.children[0] instanceof ASTmult)
            out += getJasminRecursive((ASTmult) node.children[0]);
        else if (node.children[0] instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) node.children[0]);

        if (node.children[1] instanceof ASTid) {
            String arg1 = ((ASTid) node.children[1]).info;
            STEntry local_1 = node.checkImediateSymbolTable(arg1);
            STEntry global_1 = node.checkSymbolTable(arg1);

            if (local_1 == null) {
                if (global_1 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg1 + "/" + global_1.order + "\n";
                }
            } else {
                out += "iload " + local_1.order + "\n";
            }
        } else if (node.children[1] instanceof ASTliteral) {
            out += "ldc " + ((ASTliteral) node.children[1]).info + "\n";
        } else if (node.children[1] instanceof AST_this)
            out += visit((ASTfield) ((AST_this) node.children[1]).children[0]);
        else if (node.children[1] instanceof ASTsum)
            out += getJasminRecursive((ASTsum) node.children[1]);
        else if (node.children[1] instanceof ASTsub)
            out += getJasminRecursive((ASTsub) node.children[1]);
        else if (node.children[1] instanceof ASTmult)
            out += getJasminRecursive((ASTmult) node.children[1]);
        else if (node.children[1] instanceof ASTdiv)
            out += getJasminRecursive((ASTdiv) node.children[1]);

        out += "isum\n";

        return out;
    }

    public String getJasminRecursive(ASTWhile node) {
        String out = "";
        Node first_child = node.children[0];

        if (first_child instanceof ASTid) {
            String arg0 = ((ASTid) first_child).info;
            STEntry local_0 = node.checkImediateSymbolTable(arg0);
            STEntry global_0 = node.checkSymbolTable(arg0);

            if (local_0 == null) {
                if (global_0 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg0 + "/" + global_0.order + "\n";
                }
            } else {
                out += "iload " + local_0.order + "\n";
            }
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
            String arg0 = ((ASTid) first_child).info;
            STEntry local_0 = node.checkImediateSymbolTable(arg0);
            STEntry global_0 = node.checkSymbolTable(arg0);

            if (local_0 == null) {
                if (global_0 != null) {
                    out += "aload_0\n";
                    out += "getfield " + arg0 + "/" + global_0.order + "\n";
                }
            } else {
                out += "iload " + local_0.order + "\n";
            }
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

        return out;
    }

}