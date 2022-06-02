import Commands.ICommand;

public class CommandExecute extends Thread{
    private ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    @Override
    public void run() {
        this.command.execute();
    }
}
