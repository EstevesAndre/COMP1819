/* Generated By:JJTree: Do not edit this line. ASTArgs.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTArgs extends SimpleNode {
  public ASTArgs(int id) {
    super(id);
  }

  public ASTArgs(jmm p, int id) {
    super(p, id);
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
 * JavaCC - OriginalChecksum=3b31490a561081bd69d997efacc92b9d (do not edit this
 * line)
 */
