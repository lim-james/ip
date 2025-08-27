import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Task parseFromLine(String line) throws CorruptSaveException, UnknownTaskTypeException {
        String trimmedLine = line.trim();

        String[] parts = trimmedLine.split("\\|", 3);

        if (parts.length < 3) {
            throw new CorruptSaveException(line);
        }

        String type = parts[0].trim();
        TaskParser parser;

        if (type.equals("T")) {
            parser = new ToDoParser();
        } else if (type.equals("D")) {
            parser = new DeadlineParser();
        } else if (type.equals("E")) {
            parser = new EventParser();
        } else {
            throw new UnknownTaskTypeException(type);
        }

        String description = parts[2].trim(); 
        Task ret = parser.parseFromFile(description);

        boolean marked = parts[1].trim().equals("1");  
        if (marked) 
            ret.mark();

        return ret;
    }
}
