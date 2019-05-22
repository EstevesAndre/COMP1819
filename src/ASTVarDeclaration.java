/* Generated By:JJTree: Do not edit this line. ASTVarDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTVarDeclaration extends SimpleNode {
  public String id;

  public ASTVarDeclaration(int id) {
    super(id);
  }

  public ASTVarDeclaration(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
    return "var " + id;
  }

  @Override
  public String accept(ASTNodeVisitor visitor) {
    return visitor.visit(this);
  }

  public void acceptSemanticAnalysis(SemanticAnalyzer semanticAnalyzer) {
    semanticAnalyzer.visit(this);

    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode) children[i];
        if (n != null) {
          n.acceptSemanticAnalysis(semanticAnalyzer);
        }
      }
    }
  }
}
/*
 * JavaCC - OriginalChecksum=4e604d6a77c4c7fb01df67af0b175127 (do not edit this
 * line)
 */
