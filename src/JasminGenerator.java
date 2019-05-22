public class JasminGenerator implements ASTNodeVisitor {

    @Override
    public String visit(SimpleNode node) {
        System.out.println("Displaying SimpleNode");

        return "ya";
    }

    @Override
    public String visit(ASTlt node) {
        System.out.println("Displaying ASTlt");

        return "ya";
    }

    @Override
    public String visit(ASTmult node) {
        System.out.println("Displaying ASTmult");

        return "ya";

    }

    @Override
    public String visit(ASTnot node) {
        System.out.println("Displaying ASTnot");
        return "ya";

    }

    @Override
    public String visit(ASTsub node) {
        System.out.println("Displaying ASTsub");
        return "ya";

    }

    @Override
    public String visit(ASTdiv node) {
        System.out.println("Displaying ASTdiv");
        return "ya";

    }

    @Override
    public String visit(ASTVarDeclaration node) {
        System.out.println("Displaying ASTVarDeclaration");
        return "ya";

    }

    @Override
    public String visit(ASTWhile node) {
        System.out.println("Displaying ASTWhile");
        return "ya";

    }

    @Override
    public String visit(ASTIf node) {
        System.out.println("Displaying ASTIf");
        return "ya";

    }

    @Override
    public String visit(ASTid node) {
        System.out.println("Displaying ASTid");
        return "ya";

    }

    @Override
    public String visit(ASTsum node) {
        System.out.println("Displaying ASTsum");
        return "ya";

    }

    @Override
    public String visit(ASTType node) {
        System.out.println("Displaying ASTType");
        return "ya";

    }

    @Override
    public String visit(ASTStatement node) {
        System.out.println("Displaying ASTStatement");
        return "ya";

    }

    @Override
    public String visit(ASTMethodDeclaration node) {
        System.out.println("Displaying ASTMethodDeclaration");
        return "ya";

    }

    @Override
    public String visit(ASTMainDeclaration node) {
        System.out.println("Displaying ASTMainDeclaration");
        return "ya";

    }

    @Override
    public String visit(ASTbool node) {
        System.out.println("Displaying ASTbool");
        return "ya";

    }

    @Override
    public String visit(ASTArg node) {
        System.out.println("Displaying ASTArg");
        return "ya";

    }

    @Override
    public String visit(ASTArgs node) {
        System.out.println("Displaying ASTArgs");
        return "ya";

    }

    @Override
    public String visit(ASTClassDeclaration node) {
        System.out.println("Displaying ASTClassDeclaration");
        return "ya";

    }

    @Override
    public String visit(AST_new node) {
        System.out.println("Displaying AST_new");

        String out = "";

        if (node.type.equals("array")) {
          SimpleNode p = (SimpleNode) node.parent;
    
          String assign = null;
          if (p instanceof ASTStatement)
            assign = ((ASTStatement) p).id;
    
          STEntry local = checkImediateSymbolTable(assign);
          STEntry global = checkSymbolTable(assign);
    
          int size = Integer.parseInt(((ASTliteral)node.children[0]).info);
    
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
    
        }
    
        return out;

    }

    @Override
    public String visit(AST_this node) {
        System.out.println("Displaying AST_this");
        return "ya";

    }

    @Override
    public String visit(ASTand node) {
        System.out.println("Displaying ASTand");
        return "ya";

    }

    @Override
    public String visit(ASTfield node) {
        System.out.println("Displaying ASTfield");
        return "ya";

    }

    @Override
    public String visit(ASTliteral node) {
        System.out.println("Displaying ASTliteral");
        return "ya";

    }

}