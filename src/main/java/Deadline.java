import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static Task build(String str) throws IncompleteTaskException {
        Pattern pattern = Pattern.compile("^(.+?)\\s*/by\\s+(.+)$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new IncompleteTaskException("The 'deadline' command requires a description and a '/by' date. Format: deadline <description> /by <date>");
        }

        String dateStr = matcher.group(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        return new Deadline(matcher.group(1), date);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
        String dateStr = this.deadline.format(formatter);
        return "[D]" + super.toString() + " (by: " + dateStr + ")";
    }

    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String dateStr = this.deadline.format(formatter);

        String details = " | " + dateStr;
        return "D | " + super.serialize() + details;
    }
}
