package Commands;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Interpreter{
    private Map<String, String> interpreter;

    public Interpreter(){
        this.interpreter = new HashMap<>();
    }

    public void commandSet(String key, String value){
        if(this.interpreter.containsKey(key)){
            var oldValue = this.interpreter.get(key);
            this.interpreter.replace(key, oldValue, value);
        }
        else{
            this.interpreter.put(key, value);
        }
        System.out.printf("Saved %s = %s%n", key, value);
    }

    public void commandGet(String key){
        try {
            if(!this.interpreter.containsKey(key)) {
                throw new IllegalArgumentException(String.format("Err: no value for %s", key));
            }
            var value = this.interpreter.get(key);
            System.out.printf("%s = %s%n", key, value);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    public void commandLoad(String fileName){
        File file = new File(fileName);
        Map<String, String> keysValues = this.readFromFile(file);
        if(!keysValues.isEmpty()){
            this.interpreter.putAll(keysValues);
            System.out.printf("Data from %s is loaded%n", file.getName());
        }
    }

    public void commandSave(String fileName){
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

    public void commandCountWords(String fileName){
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

    public void commandReverseSequence(String[] arguments){
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
