package duke.command;

import duke.task.TaskList;

/** Represents a command that displays all tasks currently stored in the task list. */
public class ListCommand extends Command {

    /**
     * Executes the command by printing the contents of the given task list to the console.
     *
     * @param list The task list whose tasks will be displayed.
     * @param description Not used by this command.
     */
    @Override
    public void execute(TaskList list, String description) {
        System.out.println(list.toString());
    }
}
