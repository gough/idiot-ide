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

        if (segments.get(0).equals("ADD")) {

        } else if (segments.get(0).equals("ASSIGN")) {
            if (segments.size() == 3) {
                variables.put(segments.get(1), segments.get(2));
                this.printOutput("ASSIGN " + segments.get(2) + " to " + segments.get(1));
            } else {
                this.printError("ASSIGN takes exactly 2 arguments (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("CMT")) {

        } else if (segments.get(0).equals("DIV")) {

        } else if (segments.get(0).equals("END")) {

        } else if (segments.get(0).equals("ENTER")) {

        } else if (segments.get(0).equals("INC")) {

        } else if (segments.get(0).equals("MUL")) {

        } else if (segments.get(0).equals("PRINT")) {
            if (segments.size() == 2) {
                this.printOutput((String) variables.get(segments.get(1)));
            } else {
                this.printError("PRINT takes exactly 1 argument (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("START")) {

        } else if (segments.get(0).equals("SUB")) {

        } else if (segments.get(0).equals("VAR")) {
            if (segments.size() == 2) {
                variables.put(segments.get(1), 0.0);
                this.printOutput("VAR " + segments.get(1) + " defined");
            } else {
                this.printError("VAR takes exactly 1 argument (" + (segments.size() - 1) + " given)");
                this.printError("usage: VAR <name>");
            }
        } else {

        }

        return 0;
    }

    private void printOutput(String output) {
        System.out.println("OUT: " + output);
    }

    private void printError(String error) {
        System.out.println("ERROR: " + error);
    }
}
