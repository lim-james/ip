package duke.command;

import duke.task.TaskList;

/** Represents a command that displays all tasks currently stored in the task list. */
public class ListCommand extends Command {

    /**
     * Executes the command by printing the contents of the given task list to the console.
     *
     * @param list The task list whose tasks will be displayed.
     * @param description Not used by this command.
     * @return A string message describing the result of executing the command.
     */
    @Override
    public String execute(TaskList list, String description) {
        return list.toString();
    }
}
