package symbolTable;

public abstract class STEntry implements Comparable<STEntry>{
    public int order;
    public String id;
    public String type;
    public int line;
    public int column;

    public STEntry(int order, String id, String type, int line, int column)
    {
        this.order = order;
        this.id = id;
        this.type = type;
        this.line = line;
        this.column = column;
    }

    @Override
    public int compareTo(STEntry o) {
        if(line == -1)
            return -1;
        if(line != o.line)
            return o.line - line;
        
        return o.column - column;
    }
}