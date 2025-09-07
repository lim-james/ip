package duke.task;

import java.util.HashMap;
import java.util.Map;

public class TaskParserFactory {
    private static final Map<String, TaskParser> PARSERS = new HashMap<>();

    static {
        PARSERS.put("T", new ToDoParser());
        PARSERS.put("todo", new ToDoParser());
        PARSERS.put("D", new DeadlineParser());
        PARSERS.put("deadline", new DeadlineParser());
        PARSERS.put("E", new EventParser());
        PARSERS.put("event", new EventParser());
    }

    public static TaskParser createFileParser(String type) throws UnknownTaskTypeException {
        TaskParser parser = PARSERS.get(type);
        if (parser == null) {
            throw new UnknownTaskTypeException(type);
        }
        return parser;
    }
}
