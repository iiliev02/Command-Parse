package Commands;

public class Reverse implements ICommand{
    private Interpreter interpreter;
    private String[] arguments;

    public Reverse (Interpreter interpreter){
        this.interpreter = interpreter;
    }

    @Override
    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        this.interpreter.commandReverseSequence(this.arguments);
    }
}
