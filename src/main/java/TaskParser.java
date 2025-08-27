public abstract class TaskParser {
    public abstract Task parse(String description) throws IncompleteTaskException;
    public abstract Task parseFromFile(String description) throws CorruptSaveException;
}
