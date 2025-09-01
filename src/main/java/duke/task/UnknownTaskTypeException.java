package duke.task;

public class UnknownTaskTypeException extends Exception {
    public UnknownTaskTypeException(String type) {
        super("Unknown task type specified '" + type + "'");
    }
}
