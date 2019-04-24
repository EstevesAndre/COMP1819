import java.util.HashMap;

/* Gen /* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTMethodDeclaration extends SimpleNode {
  public HashMap<String, STEntry> symtbl = new HashMap<String, STEntry>();

  public String id;

  public ASTMethodDeclaration(int id) {
    super(id);
  }

  public ASTMethodDeclaration(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
    return "method " + id;
  }
}
/* JavaCC - OriginalChecksum=4db4e7d00203d02b3e258a10941c8c0e (do not edit this line) */
