package duke.command;

import duke.task.TaskList;

/**
 * Represents a command that searches for tasks in the task list whose descriptions contain a
 * specified keyword or phrase.
 */
public class FindCommand extends Command {

    /**
     * Executes the command by filtering the task list for tasks that match the given description
     * keyword or phrase, and prints the results to the console.
     *
     * @param list The task list to search within.
     * @param description The keyword or phrase to match against task descriptions.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to FindCommand.execute() cannot be null.";
        assert description != null
                : "Description provided to FindCommand.execute() cannot be null.";
        TaskList filtered = list.filtered(description);
        return new CommandResponse(
                "Here are the matching tasks in your list:\n" + filtered, ResponseType.SUCCESS);
    }
}
