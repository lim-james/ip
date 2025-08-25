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

    public static Task build(String str) {
        Pattern pattern = Pattern.compile("^event\\s+(.+?)\\s*/from\\s*(.+?)\\s*/to\\s*(.+)$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            return null;
        }

        return new Event(matcher.group(1), matcher.group(2), matcher.group(3));
    }

    @Override
    public String toString() {
        String fromStr = "from: " + this.from;
        String toStr = "to: " + this.to;
        return "[E]" + super.toString() + " (" + fromStr + " " + toStr + ")";
    }
}
