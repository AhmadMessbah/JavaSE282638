package mft.exception;

public class DuplicateBookNameException extends Exception {
    private String message;

    public DuplicateBookNameException() {
        super();
        message="Duplicate Book Name";
    }

    public DuplicateBookNameException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
