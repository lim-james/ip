package duke;

import duke.command.Command;
import duke.command.CommandFactory;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {
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

    public static void main(String[] args) {
        run();
        return;
    }
}
