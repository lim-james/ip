package duke.command;

import duke.task.IncompleteTaskException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskParser;
import duke.task.TaskParserFactory;
import duke.task.UnknownTaskTypeException;

/**
 * Represents a command that creates and adds a new task to the specified task list based on the
 * provided description.
 */
public class NewCommand extends Command {

    /**
     * Executes the command by parsing the task description, creating a corresponding task, and
     * adding it to the given task list.
     *
     * @param list The task list where the new task will be stored.
     * @param description The raw description containing the task type and details.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to NewCommand.execute() cannot be null.";
        assert description != null : "Description provided to NewCommand.execute() cannot be null.";

        String[] parts = description.split(" ", 2);
        String taskType = parts[0].trim();

        try {
            TaskParser parser = TaskParserFactory.createFileParser(taskType);
            Task task = parser.parse(parts[1].trim());
            list.add(task);

            return new CommandResponse(
                    "Got it. I've added this task:\n " + task, ResponseType.SUCCESS);
        } catch (UnknownTaskTypeException | IncompleteTaskException e) {
            return new CommandResponse("OH DEAR! " + e.getMessage(), ResponseType.ERROR);
        }
    }
}
