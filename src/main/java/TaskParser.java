public abstract class TaskParser {
    public abstract Task parse(String description) throws IncompleteTaskException;
}
