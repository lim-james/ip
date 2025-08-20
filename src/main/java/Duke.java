import java.util.Scanner;

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

        System.out.println(divider);
        System.out.println(exitMsg);
        System.out.println(divider);
    }

    private static void echoLoop() {
        Scanner sc = new Scanner(System.in);
        System.out.println(divider);
        String userInput = sc.nextLine();

        System.out.println(divider);

        while (!userInput.equals("bye")) {
            System.out.println("> " + userInput);
            System.out.println(divider);
            userInput = sc.nextLine();
        }

        sc.close();
    }

    public static void main(String[] args) {
        displayWelcome();
        echoLoop();
        displayExit();
        return;
    }
}
