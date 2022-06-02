import Commands.Interpreter;

public class Main {
    public static void main(String[] args) {
        var interpreter = new Interpreter();
        var engine = new Engine(interpreter);

        engine.Process();
    }
}
