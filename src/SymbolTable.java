import java.util.HashMap;

class SymbolTable {
    public HashMap<String, STEntry> symtbl = new HashMap<String, STEntry>();
    public SimpleNode node;

    public boolean addToSymbolTable(String id, STEntry entry)
    {
      Node n = node.parent;
      STEntry id_prev;
  
      while(n != null)
      {    
        if(n instanceof ASTClassDeclaration)
        {    
          id_prev = ((ASTClassDeclaration) n).symtbl.get(id);
  
          if(id_prev != null)
            return false;
          
          entry.order = ((ASTClassDeclaration) n).global_order;
          ((ASTClassDeclaration) n).global_order++;
          ((ASTClassDeclaration) n).symtbl.put(id, entry);
          return true;
        }
        
        if(n instanceof ASTMainDeclaration)
        {    
  
          id_prev = ((ASTMainDeclaration) n).symtbl.get(id);
  
          if(id_prev != null)
            return false;
          
          entry.order = ((ASTMainDeclaration) n).global_order;
          ((ASTMainDeclaration) n).global_order++;
          ((ASTMainDeclaration) n).symtbl.put(id, entry);
          return true;
        } 
  
        if(n instanceof ASTMethodDeclaration)
        {    
  
          id_prev = ((ASTMethodDeclaration) n).symtbl.get(id);
  
          if(id_prev != null)
            return false;
  
          entry.order = ((ASTMethodDeclaration) n).global_order;
          ((ASTMethodDeclaration) n).global_order++;
          ((ASTMethodDeclaration) n).symtbl.put(id, entry);
          return true;
        } 
  
        n = ((SimpleNode) n).parent;
      }
  
      return false;
    }
  

    public STEntry get(String id){
       return this.symtbl.get(id);
    }

    public STEntry checkSymbolTable(String info) {
        SimpleNode tempParent = (SimpleNode) node.parent;
        STEntry answer = null;
        while (tempParent != null) {
            if (tempParent instanceof ASTClassDeclaration) {
                answer = ((ASTClassDeclaration) tempParent).symtbl.get(info);
            } else if (tempParent instanceof ASTMainDeclaration) {
                answer = ((ASTMainDeclaration) tempParent).symtbl.get(info);
            } else if (tempParent instanceof ASTMethodDeclaration) {
                answer = ((ASTMethodDeclaration) tempParent).symtbl.get(info);
            }
            if (answer != null)
                break;
            tempParent = (SimpleNode) tempParent.parent;
        }

        return answer;
    }

    public STEntry checkImediateSymbolTable(String info) {
        SimpleNode tempParent = (SimpleNode) node.parent;
        STEntry answer = null;
        while (tempParent != null) {
            if (tempParent instanceof ASTClassDeclaration) {
                answer = ((ASTClassDeclaration) tempParent).symtbl.get(info);
                return answer;
            } else if (tempParent instanceof ASTMainDeclaration) {
                answer = ((ASTMainDeclaration) tempParent).symtbl.get(info);
                return answer;
            } else if (tempParent instanceof ASTMethodDeclaration) {
                answer = ((ASTMethodDeclaration) tempParent).symtbl.get(info);
                return answer;
            }
            if (answer != null)
                break;
            tempParent = (SimpleNode) tempParent.parent;
        }

        return answer;
    }
}