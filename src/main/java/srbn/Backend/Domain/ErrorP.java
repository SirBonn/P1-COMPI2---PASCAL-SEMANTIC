package srbn.Backend.Domain;

import java.util.ArrayList;

public class ErrorP {


    private int line;
    private int column;
    private String content;
    // 0 Lexer, 1 Sintactic
    private int typeError;
    private String message;
    private ArrayList<String> expectedSymbols;

    public ErrorP(int line, int column, String content, int typeError, String message, ArrayList<String> expectedSymbols) {
        this.line = line;
        this.column = column;
        this.content = content;
        this.typeError = typeError;
        this.message = message;
        this.expectedSymbols = expectedSymbols;
    }

    public ErrorP(int line, int column, String content, int typeError, String message) {
        this.line = line;
        this.column = column;
        this.content = content;
        this.typeError = typeError;
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypeError() {
        if (typeError == 0) {
            return "Lexico";
        } else {
            return "Sintactico";
        }
    }

    public void setTypeError(int typeError) {
        this.typeError = typeError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getExpectedSymbols() {
        return expectedSymbols;
    }

    public void setExpectedSymbols(ArrayList<String> expectedSymbols) {
        this.expectedSymbols = expectedSymbols;
    }

    public String getExpectedSymbolsString() {
        String expected = "";
        for (int i = 0; i < expectedSymbols.size(); i++) {
            if (i != expectedSymbols.size() - 1) {
                expected += expectedSymbols.get(i) + ",";
            } else {
                expected += expectedSymbols.get(i);
            }
        }
        return expected;
    }

    @Override
    public String toString() {
        if (line == 0 && column == 0) {
            return message;
        }
        if (expectedSymbols != null & !expectedSymbols.isEmpty()) {
            return getTypeError() + " -> L:" + line + "\tC:" + column + "\t --' " + message + " " + content + " '--" + " Se esperaba -> " + getExpectedSymbolsString();
        } else {
            return getTypeError() + " -> L:" + line + "\tC:" + column + "\t --' " + message + " " + content + " '--";
        }
    }

}
