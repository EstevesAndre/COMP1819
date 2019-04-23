/* Generated By:JJTree: Do not edit this line. ASTArg.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTArg extends SimpleNode {
  public String id;
  public int line;
  public int column;

  public ASTArg(int id) {
    super(id);
  }

  public ASTArg(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
    return "arg " + id;
  }
  
  void triggerSemanticAnalysis() throws SemanticException
  {
    /* Symbol Table insertions */
    if(!addToSymbolTable(id, ((ASTType) children[0]).type + (((ASTType) children[0]).array ? "[]" : ""))){
      throw new SemanticException("Variable already defined: " + id + " at line " + line + ", column " + column + ".");
    }
  }

  public String getType() {
    return ((SimpleNode) children[0]).getType();
  }
}
/* JavaCC - OriginalChecksum=9449a47bc112a1214da3d3890b56f464 (do not edit this line) */