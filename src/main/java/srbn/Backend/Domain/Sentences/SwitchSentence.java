package srbn.Backend.Domain.Sentences;

import srbn.Backend.Domain.ErrorE;
import srbn.Backend.Domain.SwitchCase;
import srbn.Backend.Domain.SymbT;

import java.util.ArrayList;

public class SwitchSentence {
    private SymbT comparator;
    private ArrayList<SwitchCase> cases;
    private ArrayList<SymbT> defaultCase;

    public SwitchSentence(SymbT comparator, ArrayList<SwitchCase> cases, ArrayList<SymbT> defaultCase) {
        this.comparator = comparator;
        this.cases = cases;
        this.defaultCase = defaultCase;
    }


    public ArrayList<SymbT> execValuation() throws ErrorE {
        for (SwitchCase switchCase : cases) {
            if (comparator.getType() != switchCase.getType()) {
                throw new ErrorE("Error: incompatible types");
            } else {
                if (comparator.getValue().equals(switchCase.getComparer())) {
                    return switchCase.getSymbols();
                }
            }
        }
        return defaultCase;
    }

}
