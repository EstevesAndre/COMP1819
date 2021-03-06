package parser;

import utils.ASTNodeVisitor;
import semanticAnalysis.*;
/* Generated By:JJTree: Do not edit this line. ASTStatement.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTStatement extends SimpleNode {
  public String type;
  public String id;
  public boolean array = false;
  public boolean assign = false;

  public ASTStatement(int id) {
    super(id);
  }

  public ASTStatement(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
    return "statement " + type + ((id != null) ? (" " + id) : "") + (array ? "[]" : "") + (assign ? " =" : "");
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
 * JavaCC - OriginalChecksum=d080604f9202b38270c2414fd96b3d8a (do not edit this
 * line)
 */
