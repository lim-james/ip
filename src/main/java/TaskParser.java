public class TaskParser {
    public Task parseFromLine(String line) {
        String trimmedLine = line.trim();

        String[] parts = trimmedLine.split("\\|");
        String type = parts[0].trim();
        boolean marked = parts[1].trim().equals("1");  
        String description = parts[2].trim(); 

        Task ret = null;

        if (type.equals("T")) {
            ret = new ToDo(description);
        } else if (type.equals("D")) {
            String deadline = parts[3].trim();
            ret = new Deadline(description, deadline);
        } else if (type.equals("E")) {
            String from = parts[3].trim();
            String to = parts[4].trim();
            ret = new Event(description, from, to);
        }

        if (marked) 
            ret.mark();

        return ret;
    }
}
