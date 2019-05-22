public interface ASTNode {
    public String accept(ASTNodeVisitor jasminGenerator);

    public void acceptSemanticAnalysis(SemanticAnalyzer semanticAnalyzer);
}