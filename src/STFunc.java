import java.util.List;

class STFunc extends STEntry {
    public List<String> argTypes;

    STFunc(String id, String type, int line, int column, List<String> argTypes)
    {
        super(id, type, line, column);

        this.argTypes = argTypes;
    }

    int getArity()
    {
        return argTypes.size();
    }

    String getArgType(int pos)
    {
        if(pos < 0 || pos >= argTypes.size())
            return "Error";

        return argTypes.get(pos);
    }

    String getKeyName() {
        String answer = id;
        for(String arg: argTypes){
            answer += " " + arg;
        }
        return answer;
    }
}