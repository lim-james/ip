import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public TaskList load()  throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(filepath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        TaskParser parser = new TaskParser();

        String line;
        Task task;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                try {
                    task = parser.parseFromLine(line);
                    tasks.add(task);
                } catch (CorruptSaveException e) {
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
