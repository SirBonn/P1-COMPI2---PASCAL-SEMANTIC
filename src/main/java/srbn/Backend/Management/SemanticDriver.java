package srbn.Backend.Management;

import java.io.StringReader;
import java.util.ArrayList;

import srbn.Backend.Domain.ErrorP;
import srbn.Backend.Domain.SymbT;
import srbn.Backend.Domain.Tables.Table;
import srbn.Backend.Domain.Type;
import srbn.Lexer.Lexer;
import srbn.Parser.Parser;

/**
 * @author byron
 */
public class SemanticDriver {

    private ArrayList<ErrorP> errors;
    private Parser sintax;
    private Lexer lex;

    public void executeAnalyzer(String s) throws Exception {

        StringReader sr = new StringReader(s);
        lex = new Lexer(sr);

        sintax = new Parser(lex);
        sintax.setErrorList(lex.getErrors());
        sintax.parse();
        errors = sintax.getErrors();
        System.out.println("Analisis semantico completado");
    }

    public String getErrors() {

        if (errors.isEmpty()) {
            return "No se han encontrado errores";
        }

        String semanticErrors = "\tErrores semanticos\n";
        String sintacticErrors = "\tErrores sintacticos\n";
        String lexicalErrors = "\tErrores lexicos\n";
        int semCount = 1;
        int sintCount = 1;
        int lexCount = 1;
        for (ErrorP e : errors) {
            if (e.getTypeError().equals("Sintactic")) {
                sintacticErrors += sintCount + ". " + e + "\n";
                sintCount++;
            } else if (e.getTypeError().equals("Semantic")) {
                semanticErrors += semCount + ". " + e.getMessage() + " at " + e.getContent() + "\n";
                semCount++;
            } else {
                lexicalErrors += lexCount + ". " + e + "\n";
                lexCount++;
            }
        }
        return  lexicalErrors + sintacticErrors + semanticErrors;
    }

    public Table<SymbT> getSymbTable() {
        return sintax.getSymbTable();
    }

    public Table<Type> getTypeTable() {
        return sintax.getTypeTable();
    }

}
