import java.util.Arrays;

public class Command {
    private String name;
    private String[] arguments;

    public static Command Parse(String text){
        var parts = text.split(" ");

        if(parts.length < 1){
            return null;
        }

        Command command = new Command();

        command.name = parts[0];
        command.arguments = Arrays.copyOfRange(parts, 1, parts.length);

        return command;
    }

    public String getName() {
        return this.name;
    }

    public String[] getArguments() {
        return this.arguments;
    }
}
