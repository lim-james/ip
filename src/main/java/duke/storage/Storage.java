package duke.storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskParser;
import duke.task.TaskParserFactory;
import duke.task.UnknownTaskTypeException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles reading from and writing to the save file that stores tasks. Provides methods to load
 * tasks into a {@link TaskList} and to save tasks back to storage.
 */
public class Storage {

    /** The path to the save file used for persistence. */
    private final String filepath;

    /**
     * Creates a new {@code Storage} instance with the specified file path.
     *
     * @param filepath The file path where tasks will be saved and loaded from.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Parses a single line of text from the save file into a {@link Task}.
     *
     * @param line The line from the save file.
     * @return A {@code Task} parsed from the line.
     * @throws CorruptSaveException If the line is malformed or incomplete.
     * @throws UnknownTaskTypeException If the task type identifier is not recognized.
     */
    public Task parseFromLine(String line) throws CorruptSaveException, UnknownTaskTypeException {
        String trimmedLine = line.trim();

        String[] parts = trimmedLine.split("\\|", 3);

        if (parts.length < 3) {
            throw new CorruptSaveException(line);
        }

        String type = parts[0].trim();
        TaskParser parser = TaskParserFactory.createFileParser(type);

        String description = parts[2].trim();
        Task ret = parser.parseFromFile(description);

        boolean isMarked = parts[1].trim().equals("1");
        if (isMarked) {
            ret.mark();
        }

        return ret;
    }

    /**
     * Loads tasks from the save file into a new {@link TaskList}. If the file does not exist, it is
     * created and an empty list is returned.
     *
     * @return A {@code TaskList} populated with tasks from the save file.
     * @throws IOException If an I/O error occurs when accessing the file.
     */
    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(filepath);

        if (!file.exists()) {
            return tasks;
        }

        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(parseFromLine(line));
                } catch (CorruptSaveException | UnknownTaskTypeException e) {
                    System.out.println("Oh no! " + e.getMessage());
                }
            }
        }

        return tasks;
    }

    /**
     * Saves the given {@link TaskList} to the save file. Creates the file and directories if they
     * do not already exist.
     *
     * @param list The task list to save.
     * @throws IOException If an I/O error occurs when writing to the file.
     */
    public void save(TaskList list) throws IOException {
        File file = new File(filepath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        String content = list.serialize();

        try (BufferedWriter writer =
                new BufferedWriter(
                        new FileWriter(filepath, false))) { // false for overwrite, true for append
            writer.write(content);
            System.out.println("Autosaved to " + filepath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
