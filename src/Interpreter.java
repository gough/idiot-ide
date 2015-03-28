import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interpreter {
    private Map<String, Double> variables = new HashMap<String, Double>();

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
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    if (variables.containsKey(segments.get(2))) {
                        if (variables.containsKey(segments.get(3))) {
                            variables.put(segments.get(3), variables.get(segments.get(1)) + variables.get(segments.get(2)));
                            this.printOutput("ADD " + segments.get(1) + " to " + segments.get(2) + " and set to " + segments.get(3));
                        } else {
                            this.printErrorAndExit("name " + segments.get(3) + " is not defined");
                        }
                    } else {
                        this.printErrorAndExit("name " + segments.get(2) + " is not defined");
                    }
                } else {
                    this.printErrorAndExit("name " + segments.get(1) + " is not defined");
                }
            } else {
                this.printErrorAndExit("ADD takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("ASSIGN")) {
            arguments = 2;
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    variables.put(segments.get(1), Double.parseDouble(segments.get(2)));
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
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    if (variables.containsKey(segments.get(2))) {
                        if (variables.containsKey(segments.get(3))) {
                            variables.put(segments.get(3), variables.get(segments.get(1)) / variables.get(segments.get(2)));
                            this.printOutput("DIV " + segments.get(1) + " by " + segments.get(2) + " and set to " + segments.get(3));
                        } else {
                            this.printErrorAndExit("name " + segments.get(3) + " is not defined");
                        }
                    } else {
                        this.printErrorAndExit("name " + segments.get(2) + " is not defined");
                    }
                } else {
                    this.printErrorAndExit("name " + segments.get(1) + " is not defined");
                }
            } else {
                this.printErrorAndExit("DIV takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("END")) {
        	arguments = 0;
        	if(segments.size() == arguments + 1){
        		
        	} else {
    			this.printErrorAndExit("END takes exactly " + arguments + " argument (" + (segments.size() - 1) + " given)");
        	}

        } else if (segments.get(0).equals("ENTER")) {
            arguments = 1;
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    Scanner scanner = new Scanner(System.in);
                    variables.put(segments.get(1), scanner.nextDouble());
                } else {
                    this.printErrorAndExit("name " + segments.get(1) + " is not defined");
                }
            } else {
                this.printErrorAndExit("ENTER takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("INC")) {
            arguments = 1;
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    variables.put(segments.get(1), variables.get(segments.get(1)) + 1);
                    this.printOutput("INC " + segments.get(1) + " by 1");
                } else {
                    this.printErrorAndExit("name " + segments.get(2) + " is not defined");
                }
            } else {
                this.printErrorAndExit("INC takes exactly " + arguments + " argument (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("MUL")) {
            arguments = 3;
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    if (variables.containsKey(segments.get(2))) {
                        if (variables.containsKey(segments.get(3))) {
                            variables.put(segments.get(3), variables.get(segments.get(1)) * variables.get(segments.get(2)));
                            this.printOutput("MUL " + segments.get(1) + " by " + segments.get(2) + " and set to " + segments.get(3));
                        } else {
                            this.printErrorAndExit("name " + segments.get(3) + " is not defined");
                        }
                    } else {
                        this.printErrorAndExit("name " + segments.get(2) + " is not defined");
                    }
                } else {
                    this.printErrorAndExit("name " + segments.get(1) + " is not defined");
                }
            } else {
                this.printErrorAndExit("MUL takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("PRINT")) {
            arguments = 1;
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    this.printOutput(Double.toString(variables.get(segments.get(1))));
                } else {
                    this.printErrorAndExit("name " + segments.get(1) + " is not defined");
                }
            } else {
                this.printErrorAndExit("PRINT takes exactly " + arguments + " argument (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("START")) {
        	arguments = 0;
        	if(segments.size() == arguments + 1){
        		
        	} else {
    			this.printErrorAndExit("START takes exactly " + arguments + " argument (" + (segments.size() - 1) + " given)");
        	}
        } else if (segments.get(0).equals("SUB")) {
            arguments = 3;
            if (segments.size() == arguments + 1) {
                if (variables.containsKey(segments.get(1))) {
                    if (variables.containsKey(segments.get(2))) {
                        if (variables.containsKey(segments.get(3))) {
                            variables.put(segments.get(3), variables.get(segments.get(1)) - variables.get(segments.get(2)));
                            this.printOutput("SUB " + segments.get(1) + " by " + segments.get(2) + " and set to " + segments.get(3));
                        } else {
                            this.printErrorAndExit("name " + segments.get(3) + " is not defined");
                        }
                    } else {
                        this.printErrorAndExit("name " + segments.get(2) + " is not defined");
                    }
                } else {
                    this.printErrorAndExit("name " + segments.get(1) + " is not defined");
                }
            } else {
                this.printErrorAndExit("SUB takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
            }
        } else if (segments.get(0).equals("VAR")) {
            arguments = 1;
            if (segments.size() == arguments + 1) {
                if (!variables.containsKey(segments.get(1))) {
                    if (!Character.isDigit(segments.get(1).charAt(0))) {
                        variables.put(segments.get(1), null);
                        this.printOutput("var " + segments.get(1) + " defined");
                    } else {
                        this.printErrorAndExit("variable name cannot start with a digit");
                    }
                } else {
                    this.printErrorAndExit("name " + segments.get(1) + " is already defined");
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
