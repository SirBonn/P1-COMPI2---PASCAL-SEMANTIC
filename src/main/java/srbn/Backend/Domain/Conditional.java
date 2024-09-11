package srbn.Backend.Domain;

public class Conditional {

    private Object comparator;
    private String logicalSym;
    private Object comparable;
    private boolean value;

    public Conditional(Object comparator, String logicalSym, Object comparable) {
        this.comparator = comparator;
        this.logicalSym = logicalSym;
        this.comparable = comparable;
    }

    public Conditional(int comparator, String logicalSym, int comparable) {
        this.comparator = comparator;
        this.logicalSym = logicalSym;
        this.comparable = comparable;

    }

    public String getLogicalSym() {
        return logicalSym;
    }

    public void negate() {
        this.value = !this.value;
    }

    public int getVal(){
        return value? 1 : 0;
    }

    public SymbT getComparator() {
        if(comparator instanceof SymbT){
            return (SymbT) comparator;
        } else {
            return null;
        }
    }

    public SymbT getComparable() {
        if(comparable instanceof SymbT){
            return (SymbT) comparable;
        } else {
            return null;
        }
    }

    public void setComparator(Object comparator) {
        this.comparator = comparator;
    }

    public void setComparable(Object comparable) {
        this.comparable = comparable;
    }


    public boolean operate(Boolean comparator, Boolean comparable){
        switch (logicalSym.toUpperCase()) {
            case "AND":
                this.value = comparator && comparable;
                break;
            case "AND THEN":
                this.value = comparator & comparable;
                break;
            case "OR":
                this.value = comparator || comparable;
                break;
            case "OR THEN":
                this.value = comparator | comparable;
                break;
            default:
                this.value = false;
        }

        return this.value;
    }

    public boolean valuate() throws ErrorE{

        Object comparatorValue = getValue(comparator);
        Object comparableValue = getValue(comparable);

        switch (logicalSym) {
            case "<":
                 if (comparatorValue instanceof Integer && comparableValue instanceof Integer) {
                    return (int) comparatorValue < (int) comparableValue;
                } else {
                    throw new ErrorE("incompatible types");
                 }
            case ">":
                if (comparatorValue instanceof Integer && comparableValue instanceof Integer) {
                    return (int) comparatorValue > (int) comparableValue;
                } else {
                    throw new ErrorE("incompatible types");
                }
            case "=<":
            case "<=":
                if (comparatorValue instanceof Integer && comparableValue instanceof Integer) {
                    return (int) comparatorValue <= (int) comparableValue;
                } else {
                    throw new ErrorE("incompatible types");
                }
            case ">=":
            case "=>":
                if (comparatorValue instanceof Integer && comparableValue instanceof Integer) {
                    return (int) comparatorValue >= (int)  comparableValue;
                } else {
                    throw new ErrorE("incompatible types");
                }
            case "=":
                if (comparatorValue instanceof Integer && comparableValue instanceof Integer) {
                    return (int) comparatorValue == (int)  comparableValue;
                } else if (comparatorValue instanceof String && comparableValue instanceof String || comparator instanceof Character && comparableValue instanceof Character) {
                    return (comparatorValue).equals(comparableValue);
                } else if (comparatorValue instanceof String && comparableValue instanceof Character || comparator instanceof Character && comparableValue instanceof String) {
                    return (comparatorValue).equals(comparableValue.toString());
                } else {
                    throw new ErrorE("incompatible types");
                }

            case "<>":
                if (comparatorValue instanceof Integer && comparableValue instanceof Integer) {
                    return (int) comparatorValue != (int)  comparableValue;
                } else if (comparatorValue instanceof String && comparableValue instanceof String || comparatorValue instanceof Character && comparableValue instanceof Character) {
                    return !(comparatorValue).equals(comparableValue);
                } else if (comparatorValue instanceof String && comparableValue instanceof Character || comparatorValue instanceof Character && comparableValue instanceof String) {
                    return !(comparatorValue).equals(comparableValue.toString());
                } else {
                    throw new ErrorE("incompatible types");
                }

            default:
                return this.value;
        }
    }

    private Object getValue(Object obj) {
        if(obj instanceof SymbT){
            return ((SymbT) obj).getValue();
        } else {
            return obj;
        }
    }

    //tostring
    public String toString(){
        return comparator + " " + logicalSym + " " + comparable;
    }

}
