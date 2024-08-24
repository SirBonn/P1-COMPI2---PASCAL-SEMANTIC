package srbn.Backend.Domain;

import srbn.Backend.Domain.TypeEnums.VariableType;

public class Type {
    private String name = "";
    private String parent = "";
    private int type;
    private boolean isArray;
    private int initSize = 0;
    private int finalSize;
    private int memorySize;

    /*
     * @param type - express if symbol is an integer, float, string, etc.
     **/
    public Type(String name, int type) {
        this.name = name;
        this.type = type;
    }

    /*
     * @param type - express if an array type with a final size
     * */
    public Type(String name, int type, int finalSize) {
        this.name = name;
        this.type = type;
        this.isArray = true;
        this.finalSize = finalSize;
        memorySize = finalSize + 1;//+1 because of the 0 index
    }

    /*
     * @param type - express if a record type with a parent
     * */
    public Type(String name, int type, String parent) {
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    /*
     * @param type - express if an array type with an initial and final size
     * */
    public Type(String name, int type, int initSize, int finalSize) {
        this.name = name;
        this.type = type;
        this.isArray = true;
        this.initSize = initSize;
        this.finalSize = finalSize;
        memorySize = finalSize - initSize + 1; //+1 because of the initSize index
    }

    public String getStrType() {
        if (type == VariableType.INTEGER.ordinal()) {
            return "integer";
        } else if (type == VariableType.REAL.ordinal()) {
            return "real";
        } else if (type == VariableType.CHAR.ordinal()) {
            return "char";
        } else if (type == VariableType.STRING.ordinal()) {
            return "string";
        } else if (type == VariableType.BOOLEAN.ordinal()) {
            return "boolean";
        } else if (type == VariableType.ARRAY.ordinal()) {
            return "array";
        } else if (type == VariableType.VOID.ordinal()) {
            return "void";
        } else if (type == VariableType.RECORD.ordinal()) {
            return "record";
        } else if (type == VariableType.DEFINED_TYPE.ordinal()) {
            return "defined_type";
        } else {
            return "unknown";
        }
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public int getInitSize() {
        return initSize;
    }

    public void setInitSize(int initSize) {
        this.initSize = initSize;
    }

    public int getFinalSize() {
        return finalSize;
    }

    public void setFinalSize(int finalSize) {
        this.finalSize = finalSize;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }
}
