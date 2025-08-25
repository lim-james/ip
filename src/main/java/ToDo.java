import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static boolean isMatch(String str) {
        Pattern pattern = Pattern.compile("^todo(\\s.*)?$");
        return pattern.matcher(str).matches();
    }

    public static Task build(String str) throws IncompleteTaskException {
        Pattern pattern = Pattern.compile("^todo\\s+(.+)$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            throw new IncompleteTaskException("The description for a 'todo' task cannot be empty.");
        }

        return new ToDo(matcher.group(1));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
