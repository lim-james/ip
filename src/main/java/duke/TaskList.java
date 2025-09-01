import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int at) {
        return list.remove(at); 
    }

    public Task mark(int at) {
        Task a = list.get(at);
        a.mark();
        return a;
    }

    public Task unmark(int at) {
        Task a = list.get(at);
        a.unmark();
        return a;
    }

    @Override
    public String toString() {
        String ret = "";
        for (var i = 0; i < list.size(); ++i) {
            ret += (i + 1) + ". " + list.get(i).toString() + "\n";
        }
        return ret.trim();
    }

    public String serialize() {
        return list.stream()
                   .map(a -> a.serialize())
                   .reduce("", (a,b) -> a + "\n" + b)
                   .trim();
    }


}
