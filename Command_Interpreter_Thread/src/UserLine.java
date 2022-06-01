import java.util.Arrays;

public class UserLine {
    private String name;
    private String[] arguments;

    public static UserLine Parse(String text){
        var parts = text.split(" ");

        if(parts.length < 1){
            return null;
        }

        UserLine userLine = new UserLine();

        userLine.name = parts[0];
        userLine.arguments = Arrays.copyOfRange(parts, 1, parts.length);

        return userLine;
    }

    public String getName() {
        return this.name;
    }

    public String[] getArguments() {
        return this.arguments;
    }
}
