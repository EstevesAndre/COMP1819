import java.util.HashMap;

/* Gen /* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTClassDeclaration extends SimpleNode {
  public HashMap<String, String> symtbl = new HashMap<String, String>();
  
  public String id;
  public String ext;

  public ASTClassDeclaration(int id) {
    super(id);
  }

  public ASTClassDeclaration(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
    return "class " + id + " " + ext != null ? ("extends " + ext) : "";
  }
}
/* JavaCC - OriginalChecksum=18527d10e528b78e818ee6610e15139d (do not edit this line) */