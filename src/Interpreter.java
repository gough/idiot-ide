import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    private Map variables = new HashMap();

    public Interpreter() {}

    public int interpret(String string) {
        ArrayList<String> lines = new ArrayList<String>();

        for (String line : string.split("[\\r\\n]+")) {
            // split supplied string on newline characters, grouping newline and empty lines as delimiter
            this.interpretString(line);
        }

        return 0;
    }

    private int interpretString(String string) {
        ArrayList<String> segments = new ArrayList<String>();

        for (String split_string : string.split("\\s+")) {
            // split supplied string on whitespace, grouping whitespace together as delimiter
            segments.add(split_string);
        }

        System.out.println("IN: " + segments);

        int arguments = 0;
        if (segments.get(0).equals("ADD")) {
            arguments = 3;
        } else if (segments.get(0).equals("ASSIGN")) {
            arguments = 2;
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    variables.put(segments.get(1), segments.get(2));
                    this.printOutput("ASSIGN " + segments.get(2) + " to " + segments.get(1));
                } else {
                    this.printErrorAndExit("name " + segments.get(1) + " is not defined");
                }
            } else {
                this.printErrorAndExit("ASSIGN takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("CMT")) {
            // do nothing
        } else if (segments.get(0).equals("DIV")) {
            arguments = 3;
        } else if (segments.get(0).equals("END")) {

        } else if (segments.get(0).equals("ENTER")) {

        } else if (segments.get(0).equals("INC")) {

        } else if (segments.get(0).equals("MUL")) {

        } else if (segments.get(0).equals("PRINT")) {
            arguments = 1;
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    this.printOutput((String) variables.get(segments.get(1)));
                } else {
                    this.printErrorAndExit("name " + segments.get(1) + " is not defined");
                }
            } else {
                this.printErrorAndExit("PRINT takes exactly " + arguments + " argument (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("START")) {

        } else if (segments.get(0).equals("SUB")) {
            
        } else if (segments.get(0).equals("VAR")) {
            arguments = 1;
            if (segments.size() == arguments + 1) {
                if (!Character.isDigit(segments.get(1).charAt(0))) {
                    variables.put(segments.get(1), null);
                    this.printOutput("var " + segments.get(1) + " defined");
                } else {
                    this.printErrorAndExit("variable name cannot start with a digit");
                }
            } else {
                this.printErrorAndExit("VAR takes exactly " + arguments + " argument (" + (segments.size() - 1) + " given)");
            }
        } else {
            this.printErrorAndExit(segments.get(0) + " is not a valid function");
        }

        return 0;
    }

    private void printOutput(String output) {
        System.out.println("OUT: " + output);
    }

    private void printErrorAndExit(String error) {
        System.out.println("ERROR: " + error);
        System.exit(1);
    }
}
