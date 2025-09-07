package duke.command;

import duke.task.TaskList;

public class FindCommand extends Command {
    @Override
    public void execute(TaskList list, String description) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(list.filtered(description));
    }
}
