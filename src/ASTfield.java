import java.util.ArrayList;
import java.util.List;

import java.lang.Class;

/* Gen /* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTfield extends SimpleNode {
  public String type;
  public String info;

  public ASTfield(int id) {
    super(id);
  }

  public ASTfield(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
    return "field " + type + " " + info;
  }

  public String getType() {

    if (type.equals("length"))
      return "int";

    if (children == null) {
      return "";
    }

    return ((SimpleNode) children[0]).getType();
  }

  @Override
  public String accept(ASTNodeVisitor visitor) {
    return visitor.visit(this);
  }
}
/*
 * JavaCC - OriginalChecksum=b1ed2277fe6b22d7aef0ccc7a29e9dd9 (do not edit this
 * line)
 */
