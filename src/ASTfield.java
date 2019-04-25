import java.util.ArrayList;
import java.util.List;

import java.lang.Class;

/* Gen /* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTfield extends SimpleNode {
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

  void triggerSemanticAnalysis() throws SemanticException
  { 
    if(type.equals("length"))
    {
      STEntry entry = checkSymbolTable(((ASTStatement) parent).id);
      if (entry != null && !entry.type.equals("int[]"))
      {
        throw new SemanticException("Invalid field access: Expecting int[], found " + entry.type + " at line " + line + ", column " + column + ".");
      }
    }
    else {
      List<String> args = new ArrayList<>();
      String type = null;
      if(children != null)
      {
        for(Node arg : children){
          
          if(arg instanceof ASTid){
            args.add(((ASTid)arg).getType());
          }
          
          if(arg instanceof ASTliteral){
            args.add(((ASTliteral)arg).getType());
          }
        }
      }
      STFunc func = new STFunc(-1, info,type,line,column,args);
      if(checkSymbolTable(func.getKeyName()) == null){
        throw new SemanticException("Function not defined: " + id + "() at line " + line + ", column " + column + ".");
      }
    }
  }

  public String getType() {
    if(type.equals("length"))
      return "int";

    return ((SimpleNode) children[0]).getType();
  }

  public String getJasmin()
  {
    String out = "";
    
    if(children != null)
      for (Node n : children) {
        STEntry entry = checkSymbolTable(((ASTid) n).info);
        if(entry != null)
          out += "iload_" + entry.order + "\n";
      }

    out += "invokevirtual ";

    if(!(parent instanceof ASTStatement))
    {
      Node n = parent;
      while(n != null)
      {
        if(n instanceof ASTClassDeclaration)
        {
          out += ((ASTClassDeclaration) n).id;
          break;
        }
        n = ((SimpleNode) n).parent;
      }
    }
    else {out += ((ASTStatement) parent).id;}

    out += "/" + info + "(";

    List<String> args = new ArrayList<>();
    String type = null;

    if(children != null)
    {
      for(Node arg : children){
        
        if(arg instanceof ASTid){
          args.add(((ASTid)arg).getType());
        }
        
        if(arg instanceof ASTliteral){
          args.add(((ASTliteral)arg).getType());
        }
      }
    }
    
    STFunc func = new STFunc(-1, info,type,line,column,args);
    STEntry entry = checkSymbolTable(func.getKeyName());

    if(entry != null)
    {
      if(children != null)
      {
        for(Node arg : children){
        
          if(arg instanceof ASTid){
            String t = ((SimpleNode) arg).getType();
            if(t.equals("Error"))
              out += "L"+((ASTid) arg).info + ";";
            else
              out += SimpleNode.getJasminType(t);
          }
          
          if(arg instanceof ASTliteral){
            out += SimpleNode.getJasminType(((SimpleNode) arg).getType());
          }
        }
      }

      out += ")" + SimpleNode.getJasminType(entry.type); 
    }

    out += "\n";
    return out;
  }
}
/* JavaCC - OriginalChecksum=b1ed2277fe6b22d7aef0ccc7a29e9dd9 (do not edit this line) */
