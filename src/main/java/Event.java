import java.util.regex.Pattern;
import java.util.regex.Matcher;
 
public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Task build(String str) throws IncompleteTaskException {
        Pattern pattern = Pattern.compile("^(.+?)\\s*/from\\s*(.+?)\\s*/to\\s*(.+)$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new IncompleteTaskException("The 'event' command requires a description, a '/from' time, and a '/to' time. Format: event <description> /from <start time> /to <end time>");
        }

        return new Event(matcher.group(1), matcher.group(2), matcher.group(3));
    }

    @Override
    public String toString() {
        String fromStr = "from: " + this.from;
        String toStr = "to: " + this.to;
        return "[E]" + super.toString() + " (" + fromStr + " " + toStr + ")";
    }

    @Override
    public String serialize() {
        String details = " | " + this.from + " | " +  this.to;
        return "E | " + super.serialize() + details;
    }
}
