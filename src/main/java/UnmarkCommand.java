public class UnmarkCommand extends Command {
    @Override
    public void execute(TaskList list, String description) {
        int index = Integer.parseInt(description) - 1;
        Task task = list.unmark(index);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }
}
