import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String chatbotName = "Peter";
    private static final String divider = "____________________________________________________________";
    
    private static void displayWelcome() {
        String welcomeMsg = String.format(
            """
 Hello! I'm %s
 What can I do for you?""", 
            chatbotName
        );
        System.out.println(divider);
        System.out.println(welcomeMsg);
    }

    private static void displayExit() {
        String exitMsg = """
 Bye. Hope to see you again soon!""";

        System.out.println(exitMsg);
        System.out.println(divider);
    }

    private static void listTasks(TaskList list) {
        System.out.println(list.toString());
    }

    private static void markTask(TaskList list, int index) {
        Task task = list.mark(index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    private static void unmarkTask(TaskList list, int index) {
        Task task = list.unmark(index);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    private static void deleteTask(TaskList list, int index) {
        Task task = list.delete(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
    }

    private static void branch(
        CommandParserResult cmd, 
        TaskList list
    ) throws IncompleteTaskException, UnknownCommandException {
        Task task;
        String desc = cmd.getDescription();
        switch (cmd.getCommand()) {
            case Command.LIST:
                listTasks(list);
                break;
            case Command.MARK:
                markTask(list, Integer.parseInt(desc) - 1);
                break;
            case Command.UNMARK:
                unmarkTask(list, Integer.parseInt(desc) - 1);
                break;
            case Command.DELETE:
                deleteTask(list, Integer.parseInt(desc) - 1);
                break;
            case Command.TODO:
                task = ToDo.build(cmd.getDescription());
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.toString());
                list.add(task);
                break;
            case Command.DEADLINE:
                task = Deadline.build(cmd.getDescription());
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.toString());
                list.add(task);
                break;
            case Command.EVENT:
                task = Event.build(cmd.getDescription());
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.toString());
                list.add(task);
                break;
            default:
                throw new UnknownCommandException();
        }
    }

    private static void run() {
        Storage storage = new Storage("./james-2103-ip.txt");
        CommandParser parser = new CommandParser();

        TaskList list;
        try {
            list = storage.load();
        } catch (Exception e) {
            System.out.println("OH DEAR! " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println(divider);
        CommandParserResult cmd = parser.parse(sc.nextLine());
        System.out.println(divider);

        while (!cmd.getCommand().equals(Command.BYE)) {
            try {
                branch(cmd, list);
                storage.save(list);
            } catch (Exception e) {
                System.out.println("OH DEAR! " + e.getMessage());
            }
            System.out.println(divider);
            cmd = parser.parse(sc.nextLine());
            System.out.println(divider);
        }

        sc.close();
    }

    public static void main(String[] args) {
        displayWelcome();
        run();
        displayExit();
        return;
    }
}
