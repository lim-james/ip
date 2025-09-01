public class IncompleteTaskException extends Exception {
    public IncompleteTaskException(String message) {
        super(message);
    }

    public IncompleteTaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
