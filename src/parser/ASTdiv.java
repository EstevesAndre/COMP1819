package parser;

import utils.ASTNodeVisitor;
import semanticAnalysis.*;
/* Generated By:JJTree: Do not edit this line. ASTdiv.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTdiv extends SimpleNode {

  public ASTdiv(int id) {
    super(id);
  }

  public ASTdiv(jmm p, int id) {
    super(p, id);
  }

  public String getType() {
    return "int";
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
 * JavaCC - OriginalChecksum=8e5bf6cb6320f30a44173f2c5c582639 (do not edit this
 * line)
 */
