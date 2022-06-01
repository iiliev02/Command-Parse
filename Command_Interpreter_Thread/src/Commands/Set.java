package Commands;

public class Set implements ICommand{
    private Interpreter interpreter;
    private String[] arguments;

    public Set (Interpreter interpreter){
        this.interpreter = interpreter;
    }

    @Override
    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        this.interpreter.commandSet(this.arguments[0], this.arguments[1]);
    }

}
