public class Duke {
    public static void main(String[] args) {
        String chatbotName = "Peter";
        String divider = "____________________________________________________________";
        String welcomeMsg = String.format("""
 Hello! I'm %s
 What can I do for you?""", chatbotName);
        String exitMsg = """
 Bye. Hope to see you again soon!""";

        System.out.println(divider);
        System.out.println(welcomeMsg);
        System.out.println(divider);
        System.out.println(exitMsg);
        System.out.println(divider);
        return;
    }
}
