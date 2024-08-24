
package srbn.Backend.Management;

import java.io.StringReader;
import java.util.ArrayList;

import srbn.Backend.Domain.ErrorP;
import srbn.Lexer.Lexer;
import srbn.Parser.Parser;

/**
 *
 * @author byron
 */
public class SemanticDriver {

    ArrayList<ErrorP> errors;

    public void executeAnalyzer(String s) throws Exception{
        
        StringReader sr = new StringReader(s);
        Lexer lex = new Lexer(sr);
        Parser sintax = new Parser(lex);
        sintax.parse();
        errors = sintax.getErrors();
        System.out.println("Analisis semantico completado");
    }

    public String getErrors() {

        if(errors.isEmpty()){
            return "No se han encontrado errores";
        }

        String semanticErrors = "\tErrores semanticos\n";
        String sintacticErrors = "\tErrores sintacticos\n";
        int semCount = 1;
        int sintCount = 1;
        for (ErrorP e : errors) {
            if (e.getTypeError().equals("Sintactic")) {
                sintacticErrors += sintCount+ ". "+ e.toString() + "\n";
                sintCount++;
            } else {
                semanticErrors += semCount+". " + e.getMessage()+ " at " + e.getContent() + "\n";
                semCount++;
            }
        }
        return semanticErrors + sintacticErrors;
    }
    
}
