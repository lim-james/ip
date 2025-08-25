public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    UNKNOWN("");

    private final String prefix;

    Command(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public static CommandParserResult fromString(String input) {
        String trimmedInput = input.trim();

        String[] parts = trimmedInput.split(" ", 2);
        String prefix = parts[0];
        String description = parts.length > 1 ? parts[1] : "";

        for (Command command : Command.values()) {
            if (command.getPrefix().equals(prefix)) {
                return new CommandParserResult(command, description);
            }
        }
        return new CommandParserResult(UNKNOWN, trimmedInput);
    }
}
