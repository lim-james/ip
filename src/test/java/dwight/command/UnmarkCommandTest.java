package dwight.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    /** Executes an unmark command on an empty list, expecting an error response. */
    @Test
    void executeOnEmptyListReturnsErrorResponse() {
        CommandResponse response = this.unmarkCommand.execute(this.taskList, "1");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
    }

    /** Executes an unmark command with an out-of-bounds index, expecting an error response. */
    @Test
    void executeOutOfBoundsIndexThrowsAssertionError() throws Exception {
        this.taskList.add(new ToDo("Read book"));

        CommandResponse response = this.unmarkCommand.execute(this.taskList, "2");

        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
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
