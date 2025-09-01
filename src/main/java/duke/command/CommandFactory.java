package duke.command;

import java.util.Map;
import java.util.HashMap;

public class CommandFactory {
    private static final Map<String, Command> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("list", new ListCommand());
        COMMANDS.put("mark", new MarkCommand());
        COMMANDS.put("unmark", new UnmarkCommand());
        COMMANDS.put("delete", new DeleteCommand());
        COMMANDS.put("new", new NewCommand());
    }

    public static Command getCommand(String type) throws UnknownCommandException {
        Command cmd = COMMANDS.get(type);
        if (cmd == null)
            throw new UnknownCommandException(type);
        return cmd;
    }
}
