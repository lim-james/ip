public class CommandParser {
    public CommandParserResult parse(String input) {
        String trimmedInput = input.trim();

        String[] parts = trimmedInput.split(" ", 2);
        String prefix = parts[0];
        String description = parts.length > 1 ? parts[1] : "";

        for (Command command : Command.values()) {
            if (command.getPrefix().equals(prefix)) {
                return new CommandParserResult(command, description);
            }
        }
        return new CommandParserResult(Command.UNKNOWN, trimmedInput);
    }
}
