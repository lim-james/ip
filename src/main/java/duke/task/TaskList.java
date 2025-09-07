package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a collection of {@link Task} objects. Provides methods to add, remove, update,
 * display, and serialize tasks.
 */
public class TaskList {

    /** The underlying list storing all tasks. */
    private List<Task> list;

    /** Creates an empty {@code TaskList}. */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Creates a new {@code TaskList} initialized with an existing list of tasks. Intended for
     * internal use, such as filtering.
     *
     * @param list The list of tasks to initialize the task list with.
     */
    private TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param at The 0-based index of the task to delete.
     * @return The removed task.
     */
    public Task delete(int at) {
        return list.remove(at);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param at The 0-based index of the task to mark.
     * @return The updated task after being marked.
     */
    public Task mark(int at) {
        Task a = list.get(at);
        a.mark();
        return a;
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param at The 0-based index of the task to unmark.
     * @return The updated task after being unmarked.
     */
    public Task unmark(int at) {
        Task a = list.get(at);
        a.unmark();
        return a;
    }

    /**
     * Returns a new {@code TaskList} containing only the tasks whose descriptions match the given
     * keyword or phrase.
     *
     * @param description The keyword or phrase to filter tasks by.
     * @return A new {@code TaskList} containing the matching tasks.
     */
    public TaskList filtered(String description) {
        return new TaskList(
                list.stream()
                        .filter(task -> task.isMatching(description))
                        .collect(Collectors.toList()));
    }

    /**
     * Returns a string representation of the task list, with each task numbered in order.
     *
     * @return The formatted string of all tasks.
     */
    @Override
    public String toString() {
        String ret = "";
        for (var i = 0; i < list.size(); ++i) {
            ret += (i + 1) + ". " + list.get(i).toString() + "\n";
        }
        return ret.trim();
    }

    /**
     * Returns a serialized representation of the entire task list, suitable for saving to storage.
     * Each task is placed on a new line.
     *
     * @return The serialized string of the task list.
     */
    public String serialize() {
        return list.stream().map(Task::serialize).reduce("", (a, b) -> a + "\n" + b).trim();
    }
}
