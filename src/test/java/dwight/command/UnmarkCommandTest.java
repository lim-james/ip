package dwight.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dwight.task.Task;
import dwight.task.TaskList;
import dwight.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link UnmarkCommand}. */
class UnmarkCommandTest {

    private TaskList taskList;
    private UnmarkCommand unmarkCommand;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
        this.unmarkCommand = new UnmarkCommand();
    }

    /**
     * Executes a valid unmark command, ensuring the task is unmarked and a SUCCESS response is
     * returned.
     */
    @Test
    void executeValidUnmarkUnmarksTaskAndReturnsSuccess() throws Exception {
        ToDo todo = new ToDo("Buy milk").mark();
        this.taskList.add(todo);
        CommandResponse response = this.unmarkCommand.execute(this.taskList, "1");
        Task task = this.taskList.delete(0);
        assertFalse(task.isMarked());
        assertEquals(ResponseType.SUCCESS, response.getType());
    }

    /**
     * Executes an unmark command on an empty list, expecting an assertion error. Requires running
     * tests with JVM assertions enabled (-ea).
     */
    @Test
    void executeOnEmptyListThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> this.unmarkCommand.execute(this.taskList, "1"));
    }

    /** Executes an unmark command with an out-of-bounds index, expecting an assertion error. */
    @Test
    void executeOutOfBoundsIndexThrowsAssertionError() throws Exception {
        this.taskList.add(new ToDo("Read book"));
        assertThrows(AssertionError.class, () -> this.unmarkCommand.execute(this.taskList, "2"));
    }

    /**
     * Executes an unmark command with a non-numeric description, expecting a NumberFormatException.
     */
    @Test
    void executeNonNumericDescriptionThrowsNumberFormatException() {
        assertThrows(
                NumberFormatException.class,
                () -> this.unmarkCommand.execute(this.taskList, "abc"));
    }
}
