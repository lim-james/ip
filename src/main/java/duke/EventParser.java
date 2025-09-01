import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventParser extends TaskParser {
    @Override
    public Task parse(String description) throws IncompleteTaskException {
        Pattern pattern = Pattern.compile("^(.+?)\\s*/from\\s*(.+?)\\s*/to\\s*(.+)$");
        Matcher matcher = pattern.matcher(description);

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
    public Task parseFromFile(String description) throws CorruptSaveException {
        String[] parts = description.split("\\|");
        
        if (parts.length < 3)
            throw new CorruptSaveException("Event description incomplete '" + description + "'");

        description = parts[0].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String fromStr = parts[1].trim();
        LocalDate from = LocalDate.parse(fromStr, formatter);
        String toStr = parts[2].trim();
        LocalDate to = LocalDate.parse(toStr, formatter);

        return new Event(description, from, to);
    }
}

