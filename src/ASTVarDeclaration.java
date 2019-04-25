/* Generated By:JJTree: Do not edit this line. ASTVarDeclaration.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTVarDeclaration extends SimpleNode {
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

  void triggerSemanticAnalysis() throws SemanticException
  {
    /* Symbol Table insertions */
    String type = ((ASTType) children[0]).type + (((ASTType) children[0]).array ? "[]" : "");

    if(!addToSymbolTable(id, new STVar(id, type, -1, -1))){
      throw new SemanticException("Variable already defined: " + id + " at line " + line + ", column " + column + ".");
    }
  }
}
/* JavaCC - OriginalChecksum=4e604d6a77c4c7fb01df67af0b175127 (do not edit this line) */
