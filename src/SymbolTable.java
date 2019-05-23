import java.util.HashMap;

class SymbolTable {
  public HashMap<String, STEntry> symtbl = new HashMap<String, STEntry>();
  public SimpleNode node;

  public SymbolTable(SimpleNode node) {
    this.node = node;
  }

  public STEntry get(String id) {
    return this.symtbl.get(id);
  }

  public void put(String id, STEntry entry){
    this.symtbl.put(id, entry);
  }

  public int size() {
    return this.symtbl.size();
  }
}