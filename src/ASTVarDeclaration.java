/* Generated By:JJTree: Do not edit this line. ASTVarDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTVarDeclaration extends SimpleNode {
  public String id;

  public ASTVarDeclaration(int id) {
    super(id);
    addToSymbolTable(this.id, ((ASTType) children[0]).type + (((ASTType) children[0]).array ? "[]" : ""));
  }

  public ASTVarDeclaration(jmm p, int id) {
    super(p, id);
    addToSymbolTable(this.id, ((ASTType) children[0]).type + (((ASTType) children[0]).array ? "[]" : ""));
  }

  public String toString() {
    return "arg " + id;
  }
}
/* JavaCC - OriginalChecksum=4e604d6a77c4c7fb01df67af0b175127 (do not edit this line) */
