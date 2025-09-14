package duke.command;

import duke.personality.PersonalityResponses;
import duke.task.Task;
import duke.task.TaskList;

/** Represents a command that marks a specified task in the task list as completed. */
public class MarkCommand extends Command {

    /**
     * Executes the command by marking the task at the given index in the provided task list as
     * completed.
     *
     * @param list The task list containing the task to be marked.
     * @param description The 1-based index of the task to mark as done.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to MarkCommand.execute() cannot be null.";
        assert description != null
                : "Description provided to MarkCommand.execute() cannot be null.";

        int index = Integer.parseInt(description) - 1;

        assert index >= 0 && index < list.size()
                : "Parsed index is out of bounds for the TaskList.";

        Task task = list.mark(index);

        assert task != null : "TaskList.mark() returned a null task.";
        assert task.isMarked() : "Task was not marked as done after MarkCommand execution.";

        String message = PersonalityResponses.MARK_SUCCESS.getRandomResponse(task);
        return new CommandResponse(message, ResponseType.SUCCESS);
    }
}
