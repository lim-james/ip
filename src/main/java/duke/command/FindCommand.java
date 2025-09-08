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
     */
    @Override
    public void execute(TaskList list, String description) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(list.filtered(description));
    }
}
