public class CommandParserResult {
    private Command cmd;
    private String description;

    public CommandParserResult(Command cmd, String description) {
        this.cmd = cmd;
        this.description = description;
    }

    public Command getCommand() {
        return this.cmd;
    }

    public String getDescription() {
        return this.description;
    }
}
