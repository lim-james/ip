import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
 
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
        String fromStr = "from: " + this.from.format(formatter);
        String toStr = "to: " + this.to.format(formatter);
        return "[E]" + super.toString() + " (" + fromStr + " " + toStr + ")";
    }

    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String fromStr = " | " + this.from.format(formatter);
        String toStr = " | " + this.to.format(formatter);
        return "E | " + super.serialize() + fromStr + toStr;
    }
}
