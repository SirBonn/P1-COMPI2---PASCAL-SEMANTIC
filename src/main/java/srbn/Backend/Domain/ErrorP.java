package srbn.Backend.Domain;

import java.util.ArrayList;

public class ErrorP {


    private int line;
    private int column;
    private String content;
    // 0 Lexer, 1 Sintactic, 2 semantic
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
        switch (typeError) {
            case 0:
                return "Lexer";
            case 1:
                return "Sintactic";
            case 2:
                return "Semantic";
            default:
                return "Unknown";
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
        if(expectedSymbols == null || expectedSymbols.isEmpty()){
            return expected;
        }

        if(expectedSymbols.size() == 1){
            return expectedSymbols.get(0);
        }

        for (String s: expectedSymbols) {
            expected += s + ", ";
        }
        return expected;
    }

    @Override
    public String toString() {
        if (line == 0 && column == 0) {
            return message;
        }
        if (expectedSymbols != null & !expectedSymbols.isEmpty()) {
            return getTypeError() + " -> Linea:" + line + " Col:" + column + "\n       - " + message + " " + content + " \n       -" + " Se esperaba -> " + getExpectedSymbolsString();
        } else {
            return getTypeError() + " -> Linea:" + line + " Col:" + column + "\n       - " + message + " " + content + " ";
        }
    }

}
