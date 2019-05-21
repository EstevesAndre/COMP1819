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

  private List<String> getArgs() {
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

  String getJasmin() {
    String out = ".method public " + id + "(";

    ASTArgs args = (ASTArgs) children[1];

    if (args.children != null)
      for (Node arg : args.children) {
        out += getJasminType(((ASTType) (((ASTArg) (arg)).children[0])).getType());
      }

    out += ")" + getJasminType(((ASTType) (children[0])).getType()) + "\n";

    out += ".limit locals " + (symtbl.size() + 1) + "\n";

    String st_id = new String(id);

    for (String arg : this.args) {
      st_id += " " + arg;
    }

    STEntry ret_val = checkSymbolTable(st_id);

    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode) children[i];

        if(i == children.length -1 && !ret_val.type.equals("void"))
          break;

        if (n != null) {
          out += n.getJasmin();
        }
      }
    }



    Node return_value = children[children.length - 1];

    if (return_value instanceof ASTliteral) {
      out += "ldc " + ((ASTliteral) return_value).info + "\n";
    } else if (return_value instanceof ASTbool) {
      out += "iconst_ " + (((ASTbool) return_value).info ? 1 : 0) + "\n";
    } else if (return_value instanceof ASTid) {
      String info = ((ASTid) return_value).info;
      STEntry entry = checkImediateSymbolTable(info);

      if (entry != null) {
        if (entry.type == "int")
          out += "iload " + entry.order + "\n";
        else
          out += "aload " + entry.order + "\n";
      } else {
        entry = checkSymbolTable(info);
        if (entry != null) {
          out += "aload_0\n";
          out += "getfield " + getClassName() + "/" + entry.order + " " + getJasminType(entry.type) + "\n";
        }
      }
    } else {
      out += ((SimpleNode) return_value).getJasmin();
    }

    if (ret_val != null) {

      switch (ret_val.type) {
      case "bool":
      case "int":
        out += "ireturn";
        break;
      case "void":
        out += "return";
        break;
      default:
        out += "areturn";
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
