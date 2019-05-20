import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/* Gen /* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTMethodDeclaration extends SimpleNode {
  public int global_order = 1;
  public HashMap<String, STEntry> symtbl = new HashMap<String, STEntry>();

  public String id;

  private List<String> args;

  public ASTMethodDeclaration(int id) {
    super(id);
  }

  public ASTMethodDeclaration(jmm p, int id) {
    super(p, id);
  }

  public String toString() {
    return "method " + id;
  }

  private List<String> getArgs(){
    List<String> args = new ArrayList<>();
    SimpleNode argsNode = (SimpleNode) children[1];

    if (argsNode.children != null)
      for (Node arg : argsNode.children) {
        if (arg instanceof ASTArg) {
          args.add(((ASTArg) arg).getType());
        }
      }

      return args;
  }

  void triggerSemanticAnalysis() throws SemanticException {
    args = this.getArgs();
    String type = null;
    if (children[0] instanceof ASTType) {
      type = ((ASTType) (children[0])).getType();
    }

    STFunc func = new STFunc(-1, id, type, line, column, args);
    if (!addToSymbolTable(func.getKeyName(), func)) {
      throw new SemanticException("Function already defined: " + id + " at line " + line + ", column " + column + ".");
    }
  }

  String getPreJasmin() {
    String out = ".method public " + id + "(";

    ASTArgs args = (ASTArgs) children[1];

    if(args.children != null)
      for (Node arg : args.children) {
        out += getJasminType(((ASTType) (((ASTArg) (arg)).children[0])).getType());
      }

    out += ")" + getJasminType(((ASTType) (children[0])).getType()) + "\n";

    out += ".limit locals " + (symtbl.size() + 1) + "\n";

    return out;
  }

  String getPostJasmin() {

    // TODO: verificar return

    String out = "";
    System.out.println("CARALHO: " + id);
    String st_id = new String(id);


    for(String arg : args){
      st_id += " " + arg;
    }

    STEntry naosei = checkSymbolTable(st_id);

    if(naosei != null)
    {

      System.out.println("ID: " + naosei.id);
      if(naosei.type != null)
      System.out.println("TYPE: " + naosei.type);

      switch(naosei.type){
        case "bool":
        case "int":
        out+="ireturn";
        break;
        case "void":
        out+="return";
        break;
        default:
        out+="areturn";
        break;
      }
     
    }
    return out + "\n.end method\n";
  }
}
/*
 * JavaCC - OriginalChecksum=4db4e7d00203d02b3e258a10941c8c0e (do not edit this
 * line)
 */
