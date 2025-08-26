import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class TaskParser {
    public Task parseFromLine(String line) throws CorruptSaveException {
        String trimmedLine = line.trim();

        String[] parts = trimmedLine.split("\\|");

        if (parts.length < 3) {
            throw new CorruptSaveException(line);
        }

        String type = parts[0].trim();
        boolean marked = parts[1].trim().equals("1");  
        String description = parts[2].trim(); 

        Task ret = null;

        if (type.equals("T")) {
            ret = new ToDo(description);
        } else if (type.equals("D")) {
            if (parts.length < 4)
                throw new CorruptSaveException(line);

            String deadlineStr = parts[3].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            LocalDate deadline = LocalDate.parse(deadlineStr, formatter);
            ret = new Deadline(description, deadline);
        } else if (type.equals("E")) {
            if (parts.length < 5)
                throw new CorruptSaveException(line);

            String from = parts[3].trim();
            String to = parts[4].trim();
            ret = new Event(description, from, to);
        }

        if (marked) 
            ret.mark();

        return ret;
    }
}
