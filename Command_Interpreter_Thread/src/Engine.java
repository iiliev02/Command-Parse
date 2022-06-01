import Commands.*;

import java.util.Scanner;

public class Engine {
    private Scanner input;
    private Interpreter interpreter;
    private ICommand set;
    private ICommand get;
    private ICommand load;
    private ICommand save;
    private ICommand reverse;
    private ICommand countWords;


    public Engine(Interpreter interpreter){
        this.input = new Scanner(System.in);
        this.interpreter = interpreter;
    }

    public void Process(){
        this.set = new Set(this.interpreter);
        this.get = new Get(this.interpreter);
        this.load = new Load(this.interpreter);
        this.save = new Save(this.interpreter);
        this.reverse = new Reverse(this.interpreter);
        this.countWords = new CountWords(this.interpreter);
        boolean end = false;

        while (!end){
            var line = this.input.nextLine();
            var command = UserLine.Parse(line);
            end = this.runCommand(command);
        }
    }

    private boolean runCommand(UserLine command){
        var name = command.getName();
        var arguments = command.getArguments();
        CommandExecute commandThread = new CommandExecute();

        switch (name.toLowerCase()){
            case "set":
                this.set.setArguments(arguments);
                commandThread.setCommand(set);
                commandThread.start();
                break;
            case "get":
                this.get.setArguments(arguments);
                commandThread.setCommand(get);
                commandThread.start();
                break;
            case "load":
                this.load.setArguments(arguments);
                commandThread.setCommand(load);
                commandThread.start();
                break;
            case "save":
                this.save.setArguments(arguments);
                commandThread.setCommand(save);
                commandThread.start();
                break;
            case "count-words":
                this.countWords.setArguments(arguments);
                commandThread.setCommand(countWords);
                commandThread.start();
                break;
            case "reverse":
                this.reverse.setArguments(arguments);
                commandThread.setCommand(reverse);
                commandThread.start();
                break;
            case "exit":
                System.out.println("Exiting the program...");
                return true;
            default:
                System.out.println("Invalid command!");
                break;
        }
        return false;
    }
}
