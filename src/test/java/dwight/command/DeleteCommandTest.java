package dwight.command;

import static org.junit.jupiter.api.Assertions.*;

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
     * Executes a delete command on an empty list, expecting an assertion error due to an
     * out-of-bounds index.
     */
    @Test
    void executeOnEmptyListThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> this.deleteCommand.execute(this.taskList, "1"));
    }

    /**
     * Executes a delete command with an index larger than the task list size, expecting an
     * assertion error.
     */
    @Test
    void executeOutOfBoundsIndexThrowsAssertionError() throws Exception {
        this.taskList.add(new ToDo("Read book"));
        assertThrows(AssertionError.class, () -> this.deleteCommand.execute(this.taskList, "2"));
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
