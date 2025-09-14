package dwight.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.task.Task;
import dwight.task.TaskList;
import dwight.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link MarkCommand}. */
class MarkCommandTest {

    private TaskList taskList;
    private MarkCommand markCommand;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
        this.markCommand = new MarkCommand();
    }

    /**
     * Executes a valid mark command, ensuring the task is marked and a SUCCESS response is
     * returned.
     */
    @Test
    void executeValidMarkMarksTaskAndReturnsSuccess() throws Exception {
        this.taskList.add(new ToDo("Buy milk"));
        CommandResponse response = this.markCommand.execute(this.taskList, "1");
        Task task = this.taskList.delete(0);
        assertTrue(task.isMarked());
        assertEquals(ResponseType.SUCCESS, response.getType());
    }

    /**
     * Executes a mark command on an empty list, expecting an assertion error. Requires running
     * tests with JVM assertions enabled (-ea).
     */
    @Test
    void executeOnEmptyListThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> this.markCommand.execute(this.taskList, "1"));
    }

    /** Executes a mark command with an out-of-bounds index, expecting an assertion error. */
    @Test
    void executeOutOfBoundsIndexThrowsAssertionError() throws Exception {
        this.taskList.add(new ToDo("Read book"));
        assertThrows(AssertionError.class, () -> this.markCommand.execute(this.taskList, "2"));
    }

    /**
     * Executes a mark command with a non-numeric description, expecting a NumberFormatException.
     */
    @Test
    void executeNonNumericDescriptionThrowsNumberFormatException() {
        assertThrows(
                NumberFormatException.class, () -> this.markCommand.execute(this.taskList, "abc"));
    }
}
