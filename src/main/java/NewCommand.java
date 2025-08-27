public class NewCommand extends Command {
    @Override
    public void execute(TaskList list, String description) {
        String[] parts = description.split(" ", 2);  
        String taskType = parts[0].trim();

        try {
            TaskParser parser = TaskParserFactory.createFileParser(taskType);
            Task task = parser.parse(parts[1].trim());

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task.toString());
            list.add(task);
        } catch (Exception e) {
            System.out.println("OH DEAR! " + e.getMessage());
        }

    }
}
