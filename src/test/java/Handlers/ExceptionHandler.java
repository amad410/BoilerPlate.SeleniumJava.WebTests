package Handlers;

public class ExceptionHandler extends Exception{

    private String _errorMessage;

    public ExceptionHandler(String errorMessage, Throwable err){
        super(errorMessage, err);

    }
}
