package semanticAnalysis;

public class SemanticException extends Exception {

    private static final long serialVersionUID = 8757286345870893232L;

    public SemanticException()
    {
        super();
    }

    public SemanticException(String message)
    {
        super(message);
    }
}