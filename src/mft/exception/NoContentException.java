package mft.exception;

public class NoContentException extends Exception{
    private String message;

    public NoContentException() {
        super();
        message = "No Content";
    }

    public NoContentException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
