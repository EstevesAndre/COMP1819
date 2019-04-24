   import java.util.List;

class STFunc extends STEntry {
    public List<String> argTypes;

    STFunc(String type, int line, int column, List<String> argTypes)
    {
        super(type, line, column);

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
}