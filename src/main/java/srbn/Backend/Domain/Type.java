package srbn.Backend.Domain;

import srbn.Backend.Domain.TypeEnums.VariableType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Type {
    private String name = "";
    private String parent = "";
    private int type;
    private boolean isArray = false;
    private Object array[] = null;
    private int initSize = 0;
    private int finalSize;
    private int memorySize = 1;
    private int category;
    private Map<String, SymbT> attributes;

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
        this.setArray(true);
        this.finalSize = finalSize;
        memorySize = finalSize + 1;//+1 because of the 0 index
    }

    public Type(String name, Type type, int cat) {
        this.name = name;
        this.initSize = type.initSize;
        this.finalSize = type.finalSize;
        this.memorySize = type.memorySize;
        this.isArray = type.isArray;
        this.type = type.type;
        this.parent = type.parent;
        this.category = cat;

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
        this.setArray(true);
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

    public boolean valueType(int type) {
        return this.type == type;
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

    public void setInArray(int index, Object value) throws ErrorE {
        if(index < initSize || index >= finalSize) {
            throw new ErrorE("Index out of bounds");
        }
        //valuate types if no error then set value
        array[index - initSize] =  value;
    }

    public Object getFromIndex(int index) throws ErrorE {
        if(index < initSize || index >= finalSize) {
            throw new ErrorE("Index out of bounds");
        }
        return array[index-initSize];
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public void setArray(boolean array) {
        isArray = array;
        switch (getStrType()) {
            case "integer":
                this.array = new Integer[memorySize];
                break;
            case "real":
                this.array = new Double[memorySize];
                break;
            case "char":
            case "string":
                this.array = new String[memorySize];
                break;
            default:
                this.array = new Object[memorySize];
        }
    }

    public Object[] getArray() {
        return array;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Map<String, SymbT> getAttributes() {
        return attributes;
    }

    public SymbT getAttribute(String name) {
        return attributes.get(name);
    }

    public void setAttribute(SymbT attribute) {
        this.attributes.put(attribute.getName(), attribute);
    }

    public void setAttributes(Map<String, SymbT> attributes) {
        this.attributes = attributes;
    }

    public void setAttributes(ArrayList<SymbT> attributes) throws ErrorE {
        if (this.attributes == null) {
            this.attributes = new HashMap<>();
        }

        for (SymbT attribute : attributes) {

            if (this.attributes.containsKey(attribute.getName())) {
                throw new ErrorE("The attributes already exists in the struct " + this.name);
            }

            memorySize += attribute.getMemorySize();
            this.attributes.put(attribute.getName(), attribute);
        }
    }
}
