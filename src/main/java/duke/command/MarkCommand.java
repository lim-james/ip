package duke.command;

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
     */
    @Override
    public void execute(TaskList list, String description) {
        int index = Integer.parseInt(description) - 1;
        Task task = list.mark(index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }
}
