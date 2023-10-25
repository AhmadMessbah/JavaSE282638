package mft.exception;

public class NoContent extends Exception{
    private String message;

    public NoContent() {
        super();
        message = "No Content";
    }

    public NoContent(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
