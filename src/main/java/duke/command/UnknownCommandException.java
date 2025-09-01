package duke.command;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(String type) {
        super("Unknown command provided '" + type + "'");
    }
}
