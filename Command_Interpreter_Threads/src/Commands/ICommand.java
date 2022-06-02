package Commands;

public interface ICommand {
    void setArguments(String[] arguments);
    void execute();
}
