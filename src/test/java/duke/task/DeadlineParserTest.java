package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.storage.CorruptSaveException;

import org.junit.jupiter.api.Test;

public class DeadlineParserTest {

    @Test
    public void testParseValidDescriptionReturnsDeadlineTask() throws IncompleteTaskException {
        DeadlineParser parser = new DeadlineParser();
        String description = "submit project proposal /by Feb 20 2025";
        Task task = parser.parse(description);

        assertTrue(task instanceof Deadline);

        String expectedToString = "[D][ ] submit project proposal (by: Feb 20)";
        assertEquals(expectedToString, task.toString());
    }

    @Test
    public void testParseInvalidFormatThrowsIncompleteTaskException() {
        DeadlineParser parser = new DeadlineParser();
        String invalidDescription = "submit project proposal";

        Exception exception =
                assertThrows(
                        IncompleteTaskException.class,
                        () -> {
                            parser.parse(invalidDescription);
                        });

        assertEquals(
                "The 'deadline' command requires a description and a '/by' date. "
                        + "Format: deadline <description> /by <date>",
                exception.getMessage());
    }

    @Test
    public void testParseFromFileValidDescriptionReturnsDeadlineTask() throws CorruptSaveException {
        DeadlineParser parser = new DeadlineParser();
        String savedDescription = "submit report | Dec 25 2024";
        Task task = parser.parseFromFile(savedDescription);

        assertTrue(task instanceof Deadline);

        String expectedToString = "[D][ ] submit report (by: Dec 25)";
        assertEquals(expectedToString, task.toString());
    }

    @Test
    public void testParseFromFileInvalidFormat_throwsCorruptSaveException() {
        DeadlineParser parser = new DeadlineParser();
        String invalidSavedDescription = "incomplete description";

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
