package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {

    @Test
    public void testToString() {
        LocalDate fromDate = LocalDate.of(2023, 10, 26);
        LocalDate toDate = LocalDate.of(2023, 10, 30);
        Event event = new Event("project meeting", fromDate, toDate);
        assertEquals("[E][ ] project meeting (from: Oct 26 to: Oct 30)", event.toString());
        event.mark();
        assertEquals("[E][X] project meeting (from: Oct 26 to: Oct 30)", event.toString());
    }

    @Test
    public void testSerialize() {
        LocalDate fromDate = LocalDate.of(2023, 10, 26);
        LocalDate toDate = LocalDate.of(2023, 10, 30);
        Event event = new Event("project meeting", fromDate, toDate);
        assertEquals("E | 0 | project meeting | Oct 26 2023 | Oct 30 2023", event.serialize());
        event.mark();
        assertEquals("E | 1 | project meeting | Oct 26 2023 | Oct 30 2023", event.serialize());
    }
}
