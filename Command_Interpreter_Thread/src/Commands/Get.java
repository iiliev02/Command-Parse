package Commands;

public class Get implements ICommand{
    private Interpreter interpreter;
    private String[] arguments;

    public Get (Interpreter interpreter){
        this.interpreter = interpreter;
    }

    @Override
    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        this.interpreter.commandGet(this.arguments[0]);
    }
}
