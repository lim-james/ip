package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/** Represents a command that marks a specified task in the task list as not completed. */
public class UnmarkCommand extends Command {

    /**
     * Executes the command by unmarking the task at the given index in the provided task list,
     * marking it as not completed.
     *
     * @param list The task list containing the task to be unmarked.
     * @param description The 1-based index of the task to mark as not done.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to UnmarkCommand.execute() cannot be null.";
        assert description != null
                : "Description provided to UnmarkCommand.execute() cannot be null.";

        int index = Integer.parseInt(description) - 1;
        Task task = list.unmark(index);
        return new CommandResponse(
                "OK, I've marked this task as not done yet:\n " + task, ResponseType.SUCCESS);
    }
}
