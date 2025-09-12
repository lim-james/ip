package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testMark() {
        assertEquals(false, new ToDo("").isMarked());
        assertEquals(true, new ToDo("").mark().isMarked());
        assertEquals(false, new ToDo("").mark().unmark().isMarked());
    }

    @Test
    public void testToString() {
        assertEquals("[T][ ] TD", new ToDo("TD").toString());
        assertEquals("[T][X] TD", new ToDo("TD").mark().toString());
        assertEquals("[T][ ] TD", new ToDo("TD").mark().unmark().toString());
    }

    @Test
    public void testSerialize() {
        assertEquals("T | 0 | TD", new ToDo("TD").serialize());
        assertEquals("T | 1 | TD", new ToDo("TD").mark().serialize());
        assertEquals("T | 0 | TD", new ToDo("TD").mark().unmark().serialize());
    }
}
