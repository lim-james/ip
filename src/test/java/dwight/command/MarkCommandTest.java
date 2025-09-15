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

    /** Executes a mark command on an empty list, expecting an error response. */
    @Test
    void executeOnEmptyListReturnsErrorResponse() {
        CommandResponse response = this.markCommand.execute(this.taskList, "1");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
    }

    /** Executes a mark command with an out-of-bounds index, expecting an error response. */
    @Test
    void executeOutOfBoundsIndexReturnsErrorResponse() throws Exception {
        this.taskList.add(new ToDo("Read book"));

        CommandResponse response = this.markCommand.execute(this.taskList, "2");

        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
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
