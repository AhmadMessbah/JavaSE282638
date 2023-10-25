package mft.exception;

public class DuplicateUserNameException extends Exception{
    private String message;

    public DuplicateUserNameException() {
        super();
        message = "Duplicate User Name";
    }

    public DuplicateUserNameException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
