package duke.storage;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskParser;
import duke.task.TaskParserFactory;
import duke.task.UnknownTaskTypeException;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

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

        boolean marked = parts[1].trim().equals("1");  
        if (marked) 
            ret.mark();

        return ret;
    }

    public TaskList load()  throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(filepath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        String line;
        Task task;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                try {
                    task = parseFromLine(line);
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println("Oh no! " + e.getMessage());
                }
            }
        }

        return tasks;
    }

    public void save(TaskList list) throws IOException { 
        File file = new File(filepath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }


        String content = list.serialize();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, false))) { // false for overwrite, true for append
            writer.write(content);
            System.out.println("Autosaved to " + filepath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
