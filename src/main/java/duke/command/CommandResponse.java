package duke.command;

/** Represents the result of executing a command. */
public class CommandResponse {

    private final String message;
    private final ResponseType type;

    public CommandResponse(String message, ResponseType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public ResponseType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + type + "] " + message;
    }
}
