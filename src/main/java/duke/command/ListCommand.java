package duke.command;

import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList list, String description) {
        System.out.println(list.toString());
    }
}
