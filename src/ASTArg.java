/* Generated By:JJTree: Do not edit this line. ASTArg.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTArg extends SimpleNode {
  public String id;

  public ASTArg(int id) {
    super(id);
    addToSymbolTable(this.id, ((ASTType) children[0]).type + (((ASTType) children[0]).array ? "[]" : ""));
  }

  public ASTArg(jmm p, int id) {
    super(p, id);
    addToSymbolTable(this.id, ((ASTType) children[0]).type + (((ASTType) children[0]).array ? "[]" : ""));
  }

  public String toString() {
    return "arg " + id;
  }
}
/* JavaCC - OriginalChecksum=9449a47bc112a1214da3d3890b56f464 (do not edit this line) */
