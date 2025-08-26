import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
 
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        String fromStr = matcher.group(2);
        LocalDate from = LocalDate.parse(fromStr, formatter);
        String toStr = matcher.group(3);
        LocalDate to = LocalDate.parse(toStr, formatter);

        return new Event(matcher.group(1), from, to);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
        String fromStr = "from: " + this.from.format(formatter);
        String toStr = "to: " + this.to.format(formatter);
        return "[E]" + super.toString() + " (" + fromStr + " " + toStr + ")";
    }

    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String fromStr = " | " + this.from.format(formatter);
        String toStr = " | " + this.to.format(formatter);
        return "E | " + super.serialize() + fromStr + toStr;
    }
}
