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

    public static void main(String[] args) {
        displayWelcome();
        echoLoop();
        displayExit();
        return;
    }
}
