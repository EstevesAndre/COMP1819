import java.util.HashMap;

/* Gen /* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTClassDeclaration extends SimpleNode {
public  int global_order = 1;
  public HashMap<String, STEntry> symtbl = new HashMap<String, STEntry>();
  
  public String id;
  public String ext;

  public ASTClassDeclaration(int id) {
    super(id);
  }

  public ASTClassDeclaration(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
    String extendsString = (ext != null) ? ("extends " + ext) : "";
    return "class " + id + " " + extendsString;
  }

  public String getPreJasmin() {
    String out = ".class public " + id + "\n";

    if(ext != null)
    {
      out += ".super " + ext + "\n";
    }

    return out;
  }
}
/* JavaCC - OriginalChecksum=18527d10e528b78e818ee6610e15139d (do not edit this line) */
