public class SemanticAnalyzer implements ASTNodeVisitor {

    @Override
    public String visit(SimpleNode node) {
        System.out.println("Displaying SimpleNode");
        return null;
    }

    @Override
    public String visit(ASTlt node) {
        System.out.println("Displaying ASTlt");
        return null;

    }

    @Override
    public String visit(ASTmult node) {
        System.out.println("Displaying ASTmult");
        return null;

    }

    @Override
    public String visit(ASTnot node) {
        System.out.println("Displaying ASTnot");
        return null;

    }

    @Override
    public String visit(ASTsub node) {
        System.out.println("Displaying ASTsub");
        return null;

    }

    @Override
    public String visit(ASTdiv node) {
        System.out.println("Displaying ASTdiv");
        return null;

    }

    @Override
    public String visit(ASTVarDeclaration node) {
        System.out.println("Displaying ASTVarDeclaration");
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
        return null;

    }

    @Override
    public String visit(ASTsum node) {
        System.out.println("Displaying ASTsum");
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
        return null;

    }

    @Override
    public String visit(ASTMethodDeclaration node) {
        System.out.println("Displaying ASTMethodDeclaration");
        return null;

    }

    @Override
    public String visit(ASTMainDeclaration node) {
        System.out.println("Displaying ASTMainDeclaration");
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
              System.err.println(
                  "Invalid array initialization: " + node.id + " at line " + node.line + ", column " + node.column + ".");
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
        return null;

    }

    @Override
    public String visit(ASTfield node) {
        System.out.println("Displaying ASTfield");
        return null;

    }

    @Override
    public String visit(ASTliteral node) {
        System.out.println("Displaying ASTliteral");
        return null;

    }

}