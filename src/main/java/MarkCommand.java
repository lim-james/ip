public class MarkCommand extends Command {
    @Override
    public void execute(TaskList list, String description) {
        int index = Integer.parseInt(description) - 1;
        Task task = list.mark(index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }
}
