package duke;

import duke.command.Command;
import duke.command.CommandFactory;
import duke.command.CommandResponse;
import duke.command.ResponseType;
import duke.command.UnknownCommandException;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Entry point class for the Duke application. Initializes the user interface, storage, and task
 * management system, and processes user commands until the application is terminated.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a new {@code Duke} instance and initializes the storage and task list. Loads existing
     * tasks from the storage file if available.
     */
    public Duke() {
        this.storage = new Storage("./james-2103-ip.txt");
        try {
            this.taskList = this.storage.load();
        } catch (IOException e) {
            System.out.println("OH DEAR! " + e.getMessage());
            return;
        }
        assert this.taskList != null
                : "TaskList should not be null after successful initialization.";
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input The user's input command string.
     * @return A {@code CommandResponse} containing the result of executing the command.
     */
    public CommandResponse getResponse(String input) {
        assert this.taskList != null
                : "TaskList must be initialized before commands can be processed.";
        String trimmedInput = input.trim();
        CommandResponse response;
        try {
            String[] parts = trimmedInput.split(" ", 2);
            String cmdStr = parts[0];
            String description = parts.length > 1 ? parts[1] : "";
            Command cmd = CommandFactory.getCommand(cmdStr);
            response = cmd.execute(taskList, description);
            storage.save(taskList);
        } catch (UnknownCommandException | IOException e) {
            response = new CommandResponse("OH DEAR! " + e.getMessage(), ResponseType.ERROR);
        }
        assert response != null : "Command execution resulted in a null response.";
        return response;
    }
}
