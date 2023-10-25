package mft.exception;

public class AccessDeniedException extends Exception{
    private String message;

    public AccessDeniedException() {
        super();
        message = "Access Denied !!!";
    }

    public AccessDeniedException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
