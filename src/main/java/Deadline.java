import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static Task build(String str) throws IncompleteTaskException {
        Pattern pattern = Pattern.compile("^(.+?)\\s*/by\\s+(.+)$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new IncompleteTaskException("The 'deadline' command requires a description and a '/by' date. Format: deadline <description> /by <date>");
        }

        return new Deadline(matcher.group(1), matcher.group(2));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
