
package srbn.Backend.Management;

import java.io.StringReader;
import srbn.Lexer.Lexer;
import srbn.Parser.Parser;

/**
 *
 * @author byron
 */
public class SemanticDriver {
    
    public void executeAnalyzer(String s) throws Exception{
        
        StringReader sr = new StringReader(s);
        Lexer lex = new Lexer(sr);
        Parser sintax = new Parser(lex);
        sintax.parse();
        System.out.println("Analisis semantico completado");
    }
    
}
