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

    public Duke() {
        this.storage = new Storage("./james-2103-ip.txt");

        try {
            this.taskList = this.storage.load();
        } catch (IOException e) {
            System.out.println("OH DEAR! " + e.getMessage());
            return;
        }
    }

    /** Generates a response for the user's chat message. */
    public CommandResponse getResponse(String input) {
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

        return response;
    }
}
