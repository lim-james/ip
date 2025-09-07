package duke;

import java.util.Scanner;
import java.util.ArrayList;

import duke.ui.Ui;
import duke.command.Command;
import duke.command.CommandFactory;
import duke.task.TaskList;
import duke.storage.Storage;

/**
 * Entry point class for the Duke application. Initializes the user interface, storage, and task
 * management system, and processes user commands until the application is terminated.
 */
public class Duke {

    /**
     * Runs the Duke application by setting up the user interface, storage, and task list.
     * Continuously reads user input, executes commands, and saves tasks until the user exits.
     */
    private static void run() {
        Ui ui = new Ui("Peter");
        ui.displayWelcome();

        Storage storage = new Storage("./james-2103-ip.txt");

        TaskList list;
        try {
            list = storage.load();
        } catch (Exception e) {
            System.out.println("OH DEAR! " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        ui.drawLine();
        String userInput = sc.nextLine().trim();
        ui.drawLine();

        while (!userInput.equals("bye")) {
            try {
                String[] parts = userInput.split(" ", 2);
                String cmdStr = parts[0];
                String description = parts.length > 1 ? parts[1] : "";
                Command cmd = CommandFactory.getCommand(cmdStr); 
                cmd.execute(list, description);
                
                storage.save(list);
            } catch (Exception e) {
                System.out.println("OH DEAR! " + e.getMessage());
            }
            ui.drawLine();
            userInput = sc.nextLine().trim();
            ui.drawLine();
        }

        sc.close();

        ui.displayExit();
    }

    /**
     * Main entry point for the Duke application.
     *
     * @param args Command-line arguments provided by the user.
     */
    public static void main(String[] args) {
        run();
        return;
    }
}
