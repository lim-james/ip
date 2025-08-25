import java.util.Scanner;
import java.util.ArrayList;
import java.util.Optional;

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

    private static void listTasks(ArrayList<Task> list) {
        for (var i = 0; i < list.size(); ++i) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    private static void markTask(ArrayList<Task> list, int index) {
        Task task = list.get(index);
        task.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    private static void unmarkTask(ArrayList<Task> list, int index) {
        Task task = list.get(index);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    private static void deleteTask(ArrayList<Task> list, int index) {
        Task task = list.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
    }

    private static void branch(
        CommandParserResult cmd, 
        ArrayList<Task> list
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
        Scanner sc = new Scanner(System.in);

        System.out.println(divider);
        CommandParserResult cmd = Command.fromString(sc.nextLine());
        System.out.println(divider);

        ArrayList<Task> list = new ArrayList<>();

        while (!cmd.getCommand().equals(Command.BYE)) {
            try {
                branch(cmd, list);
            } catch (Exception e) {
                System.out.println("OH DEAR! " + e.getMessage());
            }
            System.out.println(divider);
            cmd = Command.fromString(sc.nextLine());
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
