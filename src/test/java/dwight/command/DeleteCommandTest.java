package dwight.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dwight.task.TaskList;
import dwight.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link DeleteCommand}. */
class DeleteCommandTest {

    private TaskList taskList;
    private DeleteCommand deleteCommand;

    @BeforeEach
    void setUp() {
        this.taskList = new TaskList();
        this.deleteCommand = new DeleteCommand();
    }

    /**
     * Executes a valid delete command, ensuring that the task is removed and a SUCCESS response is
     * returned.
     */
    @Test
    void executeValidDeleteRemovesTaskAndReturnsSuccess() throws Exception {
        this.taskList.add(new ToDo("Buy milk"));
        CommandResponse response = this.deleteCommand.execute(this.taskList, "1");

        assertEquals(0, this.taskList.size());
        assertEquals(ResponseType.SUCCESS, response.getType());
        assertTrue(response.getMessage().contains("Buy milk"));
    }

    /**
     * Executes a delete command on an empty list, expecting an error response due to an
     * out-of-bounds index.
     */
    @Test
    void executeOnEmptyListReturnsErrorResponse() {
        CommandResponse response = this.deleteCommand.execute(this.taskList, "1");
        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
    }

    /** Executes a delete command with an out-of-bounds index, expecting an error response. */
    @Test
    void executeOutOfBoundsIndexReturnsErrorResponse() throws Exception {
        this.taskList.add(new ToDo("Read book"));

        CommandResponse response = this.deleteCommand.execute(this.taskList, "2");

        assertEquals(ResponseType.ERROR, response.getType(), "Response should indicate an error.");
        assertTrue(
                response.getMessage().contains("Index out of bounds"),
                "Error message should mention index out of bounds.");
    }

    /**
     * Executes a delete command with a non-numeric description, expecting a NumberFormatException.
     */
    @Test
    void executeNonNumericDescriptionThrowsNumberFormatException() {
        assertThrows(
                NumberFormatException.class,
                () -> this.deleteCommand.execute(this.taskList, "abc"));
    }
}
