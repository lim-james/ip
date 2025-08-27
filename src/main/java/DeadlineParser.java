import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineParser extends TaskParser {
    @Override
    public Task parse(String description) throws IncompleteTaskException {
        Pattern pattern = Pattern.compile("^(.+?)\\s*/by\\s+(.+)$");
        Matcher matcher = pattern.matcher(description);

        if (!matcher.matches()) {
            throw new IncompleteTaskException("The 'deadline' command requires a description and a '/by' date. Format: deadline <description> /by <date>");
        }

        String dateStr = matcher.group(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        return new Deadline(matcher.group(1), date);
    }
}
