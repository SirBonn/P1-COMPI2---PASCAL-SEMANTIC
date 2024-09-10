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
        switch (logicalSym) {
            case "<":
                 if (comparator instanceof Integer && comparable instanceof Integer) {
                    return (int) comparator < (int) comparable;
                } else {
                    throw new ErrorE("incompatible types");
                 }
            case ">":
                if (comparator instanceof Integer && comparable instanceof Integer) {
                    return (int) comparator > (int) comparable;
                } else {
                    throw new ErrorE("incompatible types");
                }
            case "=<":
            case "<=":
                if (comparator instanceof Integer && comparable instanceof Integer) {
                    return (int) comparator <= (int) comparable;
                } else {
                    throw new ErrorE("incompatible types");
                }
            case ">=":
            case "=>":
                if (comparator instanceof Integer && comparable instanceof Integer) {
                    return (int) comparator >= (int)  comparable;
                } else {
                    throw new ErrorE("incompatible types");
                }
            case "=":
                if (comparator instanceof Integer && comparable instanceof Integer) {
                    return (int) comparator == (int)  comparable;
                } else if (comparator instanceof String && comparable instanceof String || comparator instanceof Character && comparable instanceof Character) {
                    return ((String) comparator).equals(comparable);
                } else if (comparator instanceof String && comparable instanceof Character || comparator instanceof Character && comparable instanceof String) {
                    return ((String) comparator).equals(comparable.toString());
                } else {
                    throw new ErrorE("incompatible types");
                }

            case "<>":
                if (comparator instanceof Integer && comparable instanceof Integer) {
                    return (int) comparator != (int)  comparable;
                } else if (comparator instanceof String && comparable instanceof String || comparator instanceof Character && comparable instanceof Character) {
                    return !((String) comparator).equals(comparable);
                } else if (comparator instanceof String && comparable instanceof Character || comparator instanceof Character && comparable instanceof String) {
                    return !((String) comparator).equals(comparable.toString());
                } else {
                    throw new ErrorE("incompatible types");
                }

            default:
                return this.value;
        }
    }

    //tostring
    public String toString(){
        return comparator + " " + logicalSym + " " + comparable;
    }

}
