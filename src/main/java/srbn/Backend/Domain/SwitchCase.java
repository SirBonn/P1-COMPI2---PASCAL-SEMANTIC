package srbn.Backend.Domain;

import srbn.Backend.Domain.TypeEnums.VariableType;

import java.util.ArrayList;

public class SwitchCase {

    private Object comparer;
    private ArrayList<SymbT> symbols;
    private int type;
    public SwitchCase(Object comparator, ArrayList<SymbT> symbols) {
        this.comparer = comparator;
        this.symbols = symbols;
        this.type = setType(comparer);
    }

    public Object getComparer() {
        return comparer;
    }

    public ArrayList<SymbT> getSymbols() {
        return symbols;
    }

    private int setType(Object comparator){
        if(comparator instanceof Integer){
            return VariableType.INTEGER.ordinal();
        } else if(comparator instanceof String){
            return VariableType.STRING.ordinal();
        } else if (comparator instanceof Float || comparator instanceof Double){
            return VariableType.REAL.ordinal();
        } else if (comparator instanceof Boolean){
            return VariableType.BOOLEAN.ordinal();
        } else {
            return -1;
        }
    }

    public int getType() {
        return this.type;
    }
}
