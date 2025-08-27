public class DeleteCommand extends Command {
    @Override
    public void execute(TaskList list, String description) {
        int index = Integer.parseInt(description) - 1;
        Task task = list.delete(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
    }
}
