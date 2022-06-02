package Commands;

public class Save implements ICommand{
    private Interpreter interpreter;
    private String[] arguments;

    public Save (Interpreter interpreter){
        this.interpreter = interpreter;
    }

    @Override
    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        this.interpreter.commandSave(this.arguments[0]);
    }
}
