package dwight.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDate date = LocalDate.of(2023, 11, 15);
        Deadline deadline = new Deadline("submit report", date);
        assertEquals("[D][ ] submit report (by: Nov 15)", deadline.toString());
        deadline.mark();
        assertEquals("[D][X] submit report (by: Nov 15)", deadline.toString());
    }

    @Test
    public void testSerialize() {
        LocalDate date = LocalDate.of(2023, 11, 15);
        Deadline deadline = new Deadline("submit report", date);
        assertEquals("D | 0 | submit report | Nov 15 2023", deadline.serialize());
        deadline.mark();
        assertEquals("D | 1 | submit report | Nov 15 2023", deadline.serialize());
    }
}
