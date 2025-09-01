package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
        String dateStr = this.deadline.format(formatter);
        return "[D]" + super.toString() + " (by: " + dateStr + ")";
    }

    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String dateStr = this.deadline.format(formatter);

        String details = " | " + dateStr;
        return "D | " + super.serialize() + details;
    }
}
