package Commands;

public class Load implements ICommand{
    private Interpreter interpreter;
    private String[] arguments;

    public Load (Interpreter interpreter){
        this.interpreter = interpreter;
    }

    @Override
    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        this.interpreter.commandLoad(this.arguments[0]);
    }
}
