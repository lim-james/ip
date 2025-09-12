package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/** Represents a command that deletes a specified task from the task list. */
public class DeleteCommand extends Command {

    /**
     * Executes the command by removing the task at the given index from the provided task list.
     *
     * @param list The task list containing the task to be deleted.
     * @param description The 1-based index of the task to delete.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to DeleteCommand.execute() cannot be null.";
        assert description != null
                : "Description provided to DeleteCommand.execute() cannot be null.";

        int index = Integer.parseInt(description) - 1;

        assert index >= 0 && index < list.size()
                : "Parsed index is out of bounds for the TaskList.";

        Task task = list.delete(index);

        assert task != null : "TaskList.delete() returned a null task.";

        return new CommandResponse(
                "Noted. I've removed this task:\n " + task, ResponseType.SUCCESS);
    }
}
