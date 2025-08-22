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

    private static void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println(divider);
        String input = sc.nextLine();
        System.out.println(divider);

        ArrayList<Task> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (var i = 0; i < list.size(); ++i) {
                    System.out.println((i + 1) + ". " + list.get(i).toString());
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task task = list.get(index);
                task.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task.toString());
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task task = list.get(index);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + task.toString());
            } else {
                System.out.println("added: " + input);
                list.add(new Task(input));
            }

            System.out.println(divider);
            input = sc.nextLine();
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
