package mft.exception;

public class AccessDenied extends Exception{
    private String message;

    public AccessDenied() {
        super();
        message = "Access Denied !!!";
    }

    public AccessDenied(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
