package duke.command;

import duke.personality.PersonalityResponses;
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
    public CommandResponse execute(TaskList list, String description) {
        assert list != null : "TaskList provided to ListCommand.execute() cannot be null.";
        String message = PersonalityResponses.LIST_RESPONSE.getRandomResponse(list.toString());
        return new CommandResponse(message, ResponseType.SUCCESS);
    }
}
