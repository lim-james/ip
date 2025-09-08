package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskParser;
import duke.task.TaskParserFactory;

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
    public String execute(TaskList list, String description) {
        String[] parts = description.split(" ", 2);
        String taskType = parts[0].trim();

        try {
            TaskParser parser = TaskParserFactory.createFileParser(taskType);
            Task task = parser.parse(parts[1].trim());
            list.add(task);

            return "Got it. I've added this task:\n " + task;
        } catch (Exception e) {
            return "OH DEAR! " + e.getMessage();
        }
    }
}
