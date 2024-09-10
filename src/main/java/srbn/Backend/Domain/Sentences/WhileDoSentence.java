package srbn.Backend.Domain.Sentences;

import srbn.Backend.Domain.Conditional;
import srbn.Backend.Domain.SymbT;

import java.util.ArrayList;

public class WhileDoSentence {

    private Conditional cond;
    private ArrayList<SymbT> symbols;


    public WhileDoSentence(Conditional cond, ArrayList<SymbT> symbols) {
        this.cond = cond;
        this.symbols = symbols;
    }

    public void updateCondition(Conditional cond){
        this.cond = cond;
    }

}
