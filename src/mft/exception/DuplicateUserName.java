package mft.exception;

public class DuplicateUserName extends Exception{
    private String message;

    public DuplicateUserName() {
        super();
        message = "Duplicate User Name";
    }

    public DuplicateUserName(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
