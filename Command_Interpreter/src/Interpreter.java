import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Interpreter{
    private Map<String, String> interpreter;
    private Scanner input;

    public Interpreter(){
        this.interpreter = new HashMap<>();
        this.input = new Scanner(System.in);
    }

    public void Process(){
        boolean end = false;

        while (!end){
            var line = this.input.nextLine();
            var command = Command.Parse(line);
            end = this.executeCommand(command);
        }
    }

    private boolean executeCommand(Command command){
        var name = command.getName();
        var arguments = command.getArguments();

        switch (name.toLowerCase()){
            case "set":
                this.setCommand(arguments[0], arguments[1]);
                break;
            case "get":
                var value = this.getCommand(arguments[0]);
                if(value != null) System.out.printf("%s = %s%n", arguments[0], value);
                break;
            case "load":
                this.loadCommand(arguments[0]);
                break;
            case "save":
                this.saveCommand(arguments[0]);
                break;
            case "count-words":
                this.countWordsCommand(arguments[0]);
                break;
            case "reverse":
                this.reverseCommand(arguments);
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

    private void setCommand(String key, String value){
        if(this.interpreter.containsKey(key)){
            var oldValue = this.interpreter.get(key);
            this.interpreter.replace(key, oldValue, value);
        }
        else{
            this.interpreter.put(key, value);
        }
        System.out.printf("Saved %s = %s%n", key, value);
    }

    private String getCommand(String key){
        try {
            if(!this.interpreter.containsKey(key)) {
                throw new IllegalArgumentException(String.format("Err: no value for %s", key));
            }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return this.interpreter.get(key);
    }

    private void loadCommand(String fileName){
        File file = new File(fileName);
        Map<String, String> keysValues = this.readFromFile(file);
        if(!keysValues.isEmpty()){
            this.interpreter.putAll(keysValues);
            System.out.printf("Data from %s is loaded%n", file.getName());
        }
    }

    private void saveCommand(String fileName){
        try {
            File file = new File(fileName);
            FileWriter outputFile = new FileWriter(file);
            for(var key : this.interpreter.keySet()){
                outputFile.write(String.format("%s=%s%n", key, this.interpreter.get(key)));
            }
            outputFile.close();
            System.out.printf("Data exported to %s%n", file.getName());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void countWordsCommand(String fileName){
        try{
            int countWords = 0;
            String line;
            String regex = "(\\s+)|([()@.,!?:;'\\\"\\'-])";
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                List<String> words = new ArrayList<>(Arrays.stream(line.split(regex)).toList());
                words.removeIf(word -> word.equals(""));
                countWords += words.size();
            }
            System.out.printf("Words in %s: %d%n", file.getName(), countWords);
        }catch (IOException e){
            System.out.println("Non-existent file: " + e.getMessage());
        }
    }

    private void reverseCommand(String[] arguments){
        StringBuilder sequence = new StringBuilder();
        for(int i = arguments.length - 1; i >= 0; i--){
            sequence.append(this.reverseString(arguments[i], arguments[i].length() - 1));
            sequence.append(" ");
        }

        System.out.println(sequence);
    }

    private Map<String, String> readFromFile(File file){
        Map<String, String> keyValue = new HashMap<>();

        try{
            Files.lines(Paths.get(file.getPath()))
                    .map(line -> line.split("="))
                    .forEach(line -> keyValue.put(line[0],line[1]));
        }catch (IOException e){
            System.out.println("Non-existent file: " + e.getMessage());
        }

        return keyValue;
    }

    private String reverseString(String word, int index){
        if(index == 0){
            return word.charAt(index) + "";
        }
        char letter = word.charAt(index);
        return letter + reverseString(word, index - 1);
    }
}
