import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    private Map<String, Double> variables = new HashMap<String, Double>();
    private int lineNumber;
    private int totalLines;
    private Boolean debug = false;
    private Boolean lastLineError = false;
    private String output = "";

    public Interpreter() {}

    public String interpret(String string) {
        this.totalLines = 0;
        for (String line : string.split("[\\r\\n]+")) {
            this.totalLines++;
        }

        this.lineNumber = 0;
        for (String line : string.split("[\\r\\n]+")) {
            lineNumber++;

            // split supplied string on newline characters, grouping newline and empty lines as delimiter
            this.interpretString(line);
        }

        return this.output;
    }

    public String interpret(String string, Boolean debug) {
        this.debug = debug;
        return interpret(string);
    }

    private void interpretString(String string) {
        if (lastLineError == false) {
            ArrayList<String> segments = new ArrayList<String>();

            for (String split_string : string.split("\\s+")) {
                // split supplied string on whitespace, grouping whitespace together as delimiter
                segments.add(split_string);
            }

            if (this.debug) {
                this.printInput("" + segments);
            }

            if (this.lineNumber == 1 && !segments.get(0).equals("START")) {
                this.printErrorAndExit("START is not called correctly");
            }

            if (this.lineNumber == totalLines && !segments.get(0).equals("END")) {
                this.printErrorAndExit("END is not called correctly");
            }

            int arguments;
            if (segments.get(0).equals("ADD")) {
                arguments = 3;
                if (segments.size() == arguments + 1) {
                    if (variables.containsKey(segments.get(1)) && (variables.get(segments.get(1))) != null) {
                        if (variables.containsKey(segments.get(2)) && (variables.get(segments.get(2))) != null) {
                            if (variables.containsKey(segments.get(3))) {
                                variables.put(segments.get(3), variables.get(segments.get(1)) + variables.get(segments.get(2)));
                                this.printDebugOutput("ADD " + segments.get(1) + " to " + segments.get(2) + " and set to " + segments.get(3));
                            } else {
                                this.printErrorAndExit("name " + segments.get(3) + " is not defined");
                            }
                        } else {
                            this.printErrorAndExit("name " + segments.get(2) + " is not defined or null");
                        }
                    } else {
                        this.printErrorAndExit("name " + segments.get(1) + " is not defined or null");
                    }
                } else {
                    this.printErrorAndExit("ADD takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
                }
            } else if (segments.get(0).equals("ASSIGN")) {
                arguments = 2;
                if (segments.size() == arguments + 1) {
                    if (variables.containsKey(segments.get(1))) {
                        variables.put(segments.get(1), Double.parseDouble(segments.get(2)));
                        this.printDebugOutput("ASSIGN " + segments.get(2) + " to " + segments.get(1));
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
                    if (variables.containsKey(segments.get(1)) && (variables.get(segments.get(1))) != null) {
                        if (variables.containsKey(segments.get(2)) && (variables.get(segments.get(2))) != null) {
                            if (variables.containsKey(segments.get(3))) {
                                variables.put(segments.get(3), variables.get(segments.get(1)) / variables.get(segments.get(2)));
                                this.printDebugOutput("DIV " + segments.get(1) + " by " + segments.get(2) + " and set to " + segments.get(3));
                            } else {
                                this.printErrorAndExit("name " + segments.get(3) + " is not defined");
                            }
                        } else {
                            this.printErrorAndExit("name " + segments.get(2) + " is not defined or null");
                        }
                    } else {
                        this.printErrorAndExit("name " + segments.get(1) + " is not defined or null");
                    }
                } else {
                    this.printErrorAndExit("DIV takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
                }
            } else if (segments.get(0).equals("END")) {
                arguments = 0;
                if (segments.size() != arguments + 1) {
                    this.printErrorAndExit("END takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
                }
            } else if (segments.get(0).equals("ENTER")) {
                arguments = 1;
                if (segments.size() == arguments + 1) {
                    if (variables.containsKey(segments.get(1))) {
                        try {
                            String input = JOptionPane.showInputDialog("Enter a value for " + segments.get(1));
                            //Scanner scanner = new Scanner(System.in);
                            variables.put(segments.get(1), Double.parseDouble(input));
                        } catch (java.lang.NumberFormatException e) {
                            this.printErrorAndExit("ENTER takes number values only");
                        }
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
                        this.printDebugOutput("INC " + segments.get(1) + " by 1");
                    } else {
                        this.printErrorAndExit("name " + segments.get(2) + " is not defined");
                    }
                } else {
                    this.printErrorAndExit("INC takes exactly " + arguments + " argument (" + (segments.size() - 1) + " given)");
                }
            } else if (segments.get(0).equals("MUL")) {
                arguments = 3;
                if (segments.size() == arguments + 1) {
                    if (variables.containsKey(segments.get(1)) && (variables.get(segments.get(1))) != null) {
                        if (variables.containsKey(segments.get(2)) && (variables.get(segments.get(2))) != null) {
                            if (variables.containsKey(segments.get(3))) {
                                variables.put(segments.get(3), variables.get(segments.get(1)) * variables.get(segments.get(2)));
                                this.printDebugOutput("MUL " + segments.get(1) + " by " + segments.get(2) + " and set to " + segments.get(3));
                            } else {
                                this.printErrorAndExit("name " + segments.get(3) + " is not defined");
                            }
                        } else {
                            this.printErrorAndExit("name " + segments.get(2) + " is not defined or null");
                        }
                    } else {
                        this.printErrorAndExit("name " + segments.get(1) + " is not defined or null");
                    }
                } else {
                    this.printErrorAndExit("MUL takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
                }
            } else if (segments.get(0).equals("PRINT")) {
                arguments = 1;
                if (segments.size() >= arguments + 1 && segments.get(1).charAt(0) == '(') {
                    String functionOutput = "";
                    for (int i = 1; i < segments.size(); i++) {
                        if (i == 1) {
                            String temp = segments.get(1).replaceAll("[()]", "");
                            functionOutput = functionOutput + temp + " ";
                        } else if (i == segments.size() - 1) {
                            String temp = segments.get(segments.size() - 1).replaceAll("[()]", "");
                            functionOutput = functionOutput + temp;
                        } else {
                            functionOutput = functionOutput + segments.get(i) + " ";
                        }
                    }

                    this.printOutput(functionOutput);
                } else if (segments.size() == arguments + 1) {
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
                if (segments.size() != arguments + 1) {
                    this.printErrorAndExit("START takes exactly " + arguments + " arguments (" + (segments.size() - 1) + " given)");
                }
            } else if (segments.get(0).equals("SUB")) {
                arguments = 3;
                if (segments.size() == arguments + 1) {
                    if (variables.containsKey(segments.get(1)) && (variables.get(segments.get(1))) != null) {
                        if (variables.containsKey(segments.get(2)) && (variables.get(segments.get(2))) != null) {
                            if (variables.containsKey(segments.get(3))) {
                                variables.put(segments.get(3), variables.get(segments.get(1)) - variables.get(segments.get(2)));
                                this.printDebugOutput("SUB " + segments.get(1) + " by " + segments.get(2) + " and set to " + segments.get(3));
                            } else {
                                this.printErrorAndExit("name " + segments.get(3) + " is not defined");
                            }
                        } else {
                            this.printErrorAndExit("name " + segments.get(2) + " is not defined or null");
                        }
                    } else {
                        this.printErrorAndExit("name " + segments.get(1) + " is not defined or null");
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
                            this.printDebugOutput("var " + segments.get(1) + " defined");
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
        }
    }

    private void printDebugOutput(String output) {
        if (this.debug) {
            this.output = this.output + "DEBUG: " + output + "\n";
        }
    }

    private void printInput(String input) {
        if (this.debug) {
            this.output = this.output + "IN: " + input + "\n";
        }
    }

    private void printOutput(String output) {
        if (this.debug) {
            this.output = this.output + "OUT: " + output + "\n";
        } else {
            this.output = this.output + output + "\n";
        }
    }

    private void printErrorAndExit(String error) {
        this.lastLineError = true;
        this.output = this.output + "ERROR (line " + this.lineNumber + "): " + error + "\n";
    }
}
