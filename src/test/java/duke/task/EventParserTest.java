package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.storage.CorruptSaveException;

import org.junit.jupiter.api.Test;

public class EventParserTest {

    @Test
    void testParseValidDescriptionReturnsEventTask() throws IncompleteTaskException {
        EventParser parser = new EventParser();
        String description = "project meeting /from Feb 10 2025 /to Feb 12 2025";
        Task task = parser.parse(description);
        assertTrue(task instanceof Event);
        String expectedToString = "[E][ ] project meeting (from: Feb 10 to: Feb 12)";
        assertEquals(expectedToString, task.toString());
    }

    @Test
    void testParseNoToThrowsIncompleteTaskException() {
        EventParser parser = new EventParser();
        String invalidDescription = "project meeting /from Feb 10 2025";
        Exception exception =
                assertThrows(
                        IncompleteTaskException.class,
                        () -> {
                            parser.parse(invalidDescription);
                        });
        assertEquals(
                "The 'event' command requires a description, a '/from' time, and a '/to' time."
                        + " Format: event <description> /from <start time> /to <end time>",
                exception.getMessage());
    }

    @Test
    void testParseNoFromThrowsIncompleteTaskException() {
        EventParser parser = new EventParser();
        String invalidDescription = "project meeting /to Feb 12 2025";
        Exception exception =
                assertThrows(
                        IncompleteTaskException.class,
                        () -> {
                            parser.parse(invalidDescription);
                        });
        assertEquals(
                "The 'event' command requires a description, a '/from' time, and a '/to' time."
                        + " Format: event <description> /from <start time> /to <end time>",
                exception.getMessage());
    }

    @Test
    void testParseFromFileValidDescriptionReturnsEventTask() throws CorruptSaveException {
        EventParser parser = new EventParser();
        String savedDescription = "project meeting | Feb 10 2025 | Feb 12 2025";
        Task task = parser.parseFromFile(savedDescription);
        assertTrue(task instanceof Event);
        String expectedToString = "[E][ ] project meeting (from: Feb 10 to: Feb 12)";
        assertEquals(expectedToString, task.toString());
    }

    @Test
    void testParseFromFileIncompleteDescriptionThrowsCorruptSaveException() {
        EventParser parser = new EventParser();
        String invalidSavedDescription = "project meeting | Feb 10 2025";
        Exception exception =
                assertThrows(
                        CorruptSaveException.class,
                        () -> {
                            parser.parseFromFile(invalidSavedDescription);
                        });
        assertEquals(
                "Save file is corrupted at line '" + invalidSavedDescription + "'",
                exception.getMessage());
    }
}
