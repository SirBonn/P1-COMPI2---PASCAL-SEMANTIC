package srbn.Backend.Domain;

public class Operation {

    private Object operator;
    private String operation;
    private Object operand;

    public Operation(Object operator, String operation, Object operand) {
        this.operator = operator;
        this.operation = operation;
        this.operand = operand;
    }


    public Object operate() throws ErrorE {

        Object operator1 = getValue(operator);
        Object operator2 = getValue(operand);

        try {
            switch (operation) {
                case "+":
                    if (operator1 instanceof Integer && operator2 instanceof Integer) {
                        return (int) operator1 + (int) operator2;
                    } else if (operator1 instanceof Double && operator2 instanceof Double) {
                        return (double) operator1 + (double) operator2;
                    } else {
                        throw new ErrorE("Tipos incompatibles");
                    }
                case "-":
                    if (operator1 instanceof Integer && operator2 instanceof Integer) {
                        return (int) operator1 - (int) operator2;
                    } else if (operator1 instanceof Double && operator2 instanceof Double) {
                        return (double) operator1 - (double) operator2;
                    } else {
                        throw new ErrorE("Tipos incompatibles");
                    }
                case "*":
                    if (operator1 instanceof Integer && operator2 instanceof Integer) {
                        return (int) operator1 * (int) operator2;
                    } else if (operator1 instanceof Double && operator2 instanceof Double) {
                        return (double) operator1 * (double) operator2;
                    } else {
                        throw new ErrorE("Tipos incompatibles");
                    }
                case "div":
                    if (operator1 instanceof Double && operator2 instanceof Double) {
                        return (double) operator1 / (double) operator2;
                    } else {
                        throw new ErrorE("Tipos incompatibles");
                    }
                case "mod":
                    if (operator1 instanceof Double && operator2 instanceof Double) {
                        return (double) operator1 % (double) operator2;
                    } else {
                        throw new ErrorE("Tipos incompatibles");
                    }
                default:
                    return null;
            }
        } catch (ErrorE e) {
            throw new ErrorE(e.getMessage());
        }
    }

    public Object getValue(Object obj) {
        if (obj instanceof SymbT) {
            return ((SymbT) obj).getValue();
        } else {
            return obj;
        }
    }

}
