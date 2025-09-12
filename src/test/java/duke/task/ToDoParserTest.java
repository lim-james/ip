package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.storage.CorruptSaveException;

import org.junit.jupiter.api.Test;

public class ToDoParserTest {
    @Test
    public void testParseValidDescriptionReturnsToDoTask() throws IncompleteTaskException {
        ToDoParser parser = new ToDoParser();
        Task task = parser.parse("read book");

        assertTrue(task instanceof ToDo);

        assertEquals("[T][ ] read book", task.toString());
    }

    @Test
    public void testParseEmptyDescriptionThrowsIncompleteTaskException() {
        ToDoParser parser = new ToDoParser();
        Exception exception =
                assertThrows(
                        IncompleteTaskException.class,
                        () -> {
                            parser.parse("");
                        });

        assertEquals("The description for a 'todo' task cannot be empty.", exception.getMessage());
    }

    @Test
    public void testParseFromFileValidDescriptionReturnsToDoTask() throws CorruptSaveException {
        ToDoParser parser = new ToDoParser();
        Task task = parser.parseFromFile("attend meeting");

        assertTrue(task instanceof ToDo);

        assertEquals("[T][ ] attend meeting", task.toString());
    }
}
