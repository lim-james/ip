package duke.task;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.storage.CorruptSaveException;
/** Represents a parser that creates {@link Event} tasks from user input or saved file data. */
public class EventParser extends TaskParser {

    /**
     * Parses a user-provided description into an {@link Event} task. The input must include a
     * description, a {@code /from} date, and a {@code /to} date.
     *
     * <p>Example format: {@code event project meeting /from Feb 10 2025 /to Feb 12 2025}
     *
     * @param description The description string containing the event details and dates.
     * @return A new {@code Event} task created from the description.
     * @throws IncompleteTaskException If the description does not match the required format.
     */
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

    /**
     * Parses a saved description string from storage into an {@link Event} task. The saved data
     * must include the description, start date, and end date.
     *
     * @param description The serialized task data from storage.
     * @return A new {@code Event} task created from the saved data.
     * @throws CorruptSaveException If the saved data is incomplete or corrupted.
     */
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

