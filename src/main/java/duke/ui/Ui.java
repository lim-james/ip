package duke.ui;

/**
 * Represents a simple console-based user interface for the application. Provides methods to display
 * a welcome banner, a goodbye message, and a divider line.
 */
public class Ui {

    /** Assistant name shown in messages. */
    private String assistantName;

    /**
     * Creates a {@code Ui} that greets users using the provided assistant name.
     *
     * @param assistantName Name of the assistant to display in messages.
     */
    public Ui(String assistantName) {
        this.assistantName = assistantName;
    }

    /** Displays the welcome message containing the assistant name and a divider line. */
    public void displayWelcome() {
        String welcomeMsg = String.format(
            """
            Hello! I'm %s
            What can I do for you?""", 
            assistantName
        );
        this.drawLine();
        System.out.println(welcomeMsg);
    }

    /** Displays the exit message followed by a divider line. */
    public void displayExit() {
        String exitMsg = """
        Bye. Hope to see you again soon!""";

        System.out.println(exitMsg);
        this.drawLine();
    }

    /** Prints a horizontal divider line to the console. */
    public void drawLine() {
        System.out.println("____________________________________________________________");
    }
}
