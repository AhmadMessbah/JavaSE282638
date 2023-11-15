package mft.controller.exception;

public class NotReturnedBookException extends Exception{
    private String message;

    public NotReturnedBookException() {
        super();
        message = "Previous Borrowed Book not returned";
    }

    public NotReturnedBookException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
