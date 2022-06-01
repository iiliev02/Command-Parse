package Commands;

public class CountWords implements ICommand{
    private Interpreter interpreter;
    private String[] arguments;

    public CountWords (Interpreter interpreter){
        this.interpreter = interpreter;
    }

    @Override
    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        this.interpreter.commandCountWords(this.arguments[0]);
    }
}
