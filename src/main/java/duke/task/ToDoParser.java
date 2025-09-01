package duke.task;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import duke.storage.CorruptSaveException;

public class ToDoParser extends TaskParser {
    @Override
    public Task parse(String description) throws IncompleteTaskException {
        Pattern pattern = Pattern.compile("^(.+)$");
        Matcher matcher = pattern.matcher(description);

        if (!matcher.matches()) {
            throw new IncompleteTaskException("The description for a 'todo' task cannot be empty.");
        }

        return new ToDo(matcher.group(1));
    }

    @Override
    public Task parseFromFile(String description) throws CorruptSaveException {
        return new ToDo(description);
    }
}
