package duke.ui;

public class Ui {
    private String assistantName;

    public Ui(String assistantName) {
        this.assistantName = assistantName;
    }

    public void displayWelcome() {
        String welcomeMsg =
                String.format(
                        """
                        Hello! I'm %s
                        What can I do for you?\
                        """,
                        assistantName);
        this.drawLine();
        System.out.println(welcomeMsg);
    }

    public void displayExit() {
        String exitMsg =
                """
                Bye. Hope to see you again soon!\
                """;

        System.out.println(exitMsg);
        this.drawLine();
    }

    public void drawLine() {
        System.out.println("____________________________________________________________");
    }
}
