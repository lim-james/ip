package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isMatching(String comp) {
        return this.description.contains(comp); 
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }

    public String serialize() {
        String done = isDone ? "1" : "0";
        return done + " | " + this.description;
    }
}
