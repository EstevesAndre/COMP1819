abstract class STEntry implements Comparable<STEntry>{
    public String type;
    public int line;
    public int column;

    STEntry(String type, int line, int column)
    {
        this.type = type;
        this.line = line;
        this.column = column;
    }

    @Override
    public int compareTo(STEntry o) {
        if(line != o.line)
            return line - o.line;
        
        return column - o.column;
    }
}