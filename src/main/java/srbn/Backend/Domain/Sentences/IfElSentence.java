package srbn.Backend.Domain.Sentences;

import srbn.Backend.Domain.Conditional;
import srbn.Backend.Domain.ErrorE;
import srbn.Backend.Domain.SymbT;

import java.util.ArrayList;

public class IfElSentence {

    private Conditional conditional;
    private ArrayList<SymbT> symbolsIsTrue;
    private ArrayList<SymbT> symbolsIsFalse;
    private IfElSentence elseSentence;

    public IfElSentence(Conditional conditional, ArrayList<SymbT> symbolsIsTrue, ArrayList<SymbT> symbolsIsFalse) {
        this.conditional = conditional;
        this.symbolsIsTrue = symbolsIsTrue;
        this.symbolsIsFalse = symbolsIsFalse;
    }

    public IfElSentence(Conditional conditional, ArrayList<SymbT> symbolsIsTrue, IfElSentence elseSentence) {
        this.conditional = conditional;
        this.symbolsIsTrue = symbolsIsTrue;
        this.elseSentence = elseSentence;
    }

    public IfElSentence(Conditional conditional, ArrayList<SymbT> symbolsIsTrue) {
        this.conditional = conditional;
        this.symbolsIsTrue = symbolsIsTrue;
    }


    public Boolean getConditionalVal() throws ErrorE {
        return conditional.valuate();
    }

    public ArrayList<SymbT> getSymbolsIsTrue() {
        return symbolsIsTrue;
    }

    public ArrayList<SymbT> getSymbolsIsFalse() {
        return symbolsIsFalse;
    }

    public IfElSentence getElseSentence() {
        return elseSentence;
    }

    public void setElseSentence(IfElSentence elseSentence) {
        this.elseSentence = elseSentence;
    }
}
