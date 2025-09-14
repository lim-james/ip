package dwight.command;

import dwight.personality.PersonalityResponses;
import dwight.task.Task;
import dwight.task.TaskList;

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

        assert index >= 0 && index < list.size()
                : "Parsed index is out of bounds for the TaskList.";

        Task task = list.unmark(index);

        assert task != null : "TaskList.unmark() returned a null task.";
        assert !task.isMarked() : "Task was not marked as not done after UnmarkCommand execution.";

        String message = PersonalityResponses.UNMARK_SUCCESS.getRandomResponse(task);
        return new CommandResponse(message, ResponseType.SUCCESS);
    }
}
