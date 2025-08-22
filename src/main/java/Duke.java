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

        ArrayList<String> list = new ArrayList<>();


        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (var i = 0; i < list.size(); ++i)
                    System.out.println((i + 1) + ": " + list.get(i));
            } else {
                System.out.println("added: " + input);
                list.add(input);
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
