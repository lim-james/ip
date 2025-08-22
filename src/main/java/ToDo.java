import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static Task build(String str) {
        Pattern pattern = Pattern.compile("^todo\\s+(.+)$");
        Matcher matcher = pattern.matcher(str);

        if (!matcher.matches()) {
            return null;
        }

        return new ToDo(matcher.group(1));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
