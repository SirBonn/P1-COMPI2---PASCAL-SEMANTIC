package srbn.Backend.Domain;

import srbn.Backend.Domain.TypeEnums.SymbolType;
import srbn.Backend.Domain.TypeEnums.VariableType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbT extends Type {

    private byte[] value;
    private String scope;
    private int dir;
    private int booleanValue; // 1 != false
    private Map<String, SymbT> mutAttribs;

    public SymbT(String name, int type, int category) {
        super(name, type);
        this.setCategory(category);
        this.scope = "global";
    }

    public SymbT(String name, Type type, int category) throws ErrorE {
        super(name, type.getType());
        if(type.isArray()) {
            this.setInitSize(type.getInitSize());
            this.setFinalSize(type.getFinalSize());
            this.setMemorySize(type.getMemorySize());
            this.setArray(true);
        }
        this.setMemorySize(type.getMemorySize());
        this.booleanValue = type.getType() == VariableType.BOOLEAN.ordinal() ? 1 : 0;
        this.setCategory(category);
        this.scope = "global";

    }

    public SymbT(String name, int type, int cat, Object value) throws ErrorE {
        super(name, type);
        this.setCategory(cat);
        this.value = byteConverter(value);
        this.scope = "global";
    }

    public SymbT(String name, int type, Object value) throws ErrorE {
        super(name, type);
        this.setCategory(SymbolType.VARIABLE.ordinal());
        this.value = byteConverter(value);
        this.scope = "global";
    }

    public SymbT(String name, int type, boolean value) {
        super(name, type);
        this.setCategory(SymbolType.VARIABLE.ordinal());
        this.scope = "global";
        this.booleanValue = value ? 1 : 0;
    }

    //symbt constructor for symbt object
    public SymbT(SymbT symbT) {
        super(symbT.getName(), symbT.getType());
        this.setCategory(symbT.getCategory());
        this.setScope(symbT.getScope());
        this.setDir(symbT.getDir());
        this.setBooleanValue(symbT.getBooleanValue());
        this.setMemorySize(symbT.getMemorySize());
        this.setArray(symbT.isArray());
        this.setInitSize(symbT.getInitSize());
        this.setFinalSize(symbT.getFinalSize());
        this.setArray(symbT.getArray());
        this.value = symbT.value;
    }

    public SymbT() {
    }


    private byte[] byteConverter(Object objVal) throws ErrorE {
        Object value = objVal;
        if(objVal instanceof SymbT) {
            value = ((SymbT) objVal).getValue();
        }

        if (value instanceof Integer && this.getType() == VariableType.INTEGER.ordinal()) {
            return ByteBuffer.allocate(Integer.BYTES).putInt((Integer) value).array();
        } else if (value instanceof Double && this.getType() == VariableType.REAL.ordinal()) {
            return ByteBuffer.allocate(Double.BYTES).putDouble((Double) value).array();
        } else if (value instanceof String && this.getType() == VariableType.CHAR.ordinal() && !isArray()) {
            if(((String) value).length() > 1) {
                throw new ErrorE("The value is not a char");
            }
            return ByteBuffer.allocate(Character.BYTES).putChar(((String) value).charAt(0)).array();
        } else if (value instanceof Byte) {
            return new byte[]{(Byte) value};
        } else if ((value instanceof String && this.getType() == VariableType.STRING.ordinal()) || (value instanceof String && isArray() && this.getType() == VariableType.CHAR.ordinal())) {
            if(isArray()) {
                String s = fillArray((String) value);
                return s.getBytes();
            }

            return ((String) value).getBytes();
        } else {
            throw new ErrorE("Unsupported data type");
        }
    }

    public void setValue(Object value) throws ErrorE {

        this.value = byteConverter(value);
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public byte[] getValueBytes() {
        return value;
    }

    private String fillArray(String value) throws ErrorE {

        if(value.length() > this.getMemorySize()) {
            throw new ErrorE("The string is not the same size as the array");
        }
        for (int i = getInitSize(); i <= value.length(); i++) {
            this.setInArray(i, ""+value.charAt(i - getInitSize()));
        }

        return getArrayString();
    }

    public SymbT deepCopy() {
        SymbT copiedSymb = new SymbT(this);

        // Copiar profundamente los atributos mutables
        if (this.mutAttribs != null) {
            for (Map.Entry<String, SymbT> entry : this.mutAttribs.entrySet()) {
                copiedSymb.mutAttribs.put(entry.getKey(), entry.getValue().deepCopy());
            }
        }

        return copiedSymb;
    }



    public Object getValue() {
        if(value == null) {
            return null;
        }

        return convertFromBytes(value, this.getStrType());
    }

    private Object convertFromBytes(byte[] bytes, String type) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        switch (type) {
            case "integer":
                return buffer.getInt();
            case "real":
                return buffer.getDouble();
            case "char":
            case "string":
                return new String(bytes);
            default:
                throw new IllegalArgumentException("Unsupported data type");
        }
    }

    @Override
    public void setInArray(int index, Object value) throws ErrorE {

        if (value instanceof Integer && this.getType() == VariableType.INTEGER.ordinal()) {
            super.setInArray(index, value);
        } else if (value instanceof Double && this.getType() == VariableType.REAL.ordinal()) {
            super.setInArray(index, value);
        } else if (value instanceof Character && this.getType() == VariableType.CHAR.ordinal()) {
            super.setInArray(index, value);
        } else if (value instanceof String && this.getType() == VariableType.STRING.ordinal() || value instanceof String && isArray() && this.getType() == VariableType.CHAR.ordinal()) {
            if(((String) value).length() > 1) {
                throw new ErrorE("The value is not a char");
            }
            super.setInArray(index, value);
        } else {
            throw new ErrorE("Unsupported data type");
        }
    }

    public void setValueOnParent(SymbT value) throws ErrorE {

        if(value.getType() == this.getMutAttribute(value.getName()).getType()) {
            this.getMutAttribute(value.getName()).setValue(value.getValueBytes());
        } else {
            throw new ErrorE("The value is not the same type as the variable");
        }

    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(int booleanValue) {
        this.booleanValue = booleanValue;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setMutAttribs(Type type) {
        this.mutAttribs = type.deepCopyAttributes();
    }

    public Map<String, SymbT> getMutAttribs() {
        return mutAttribs;
    }

    public void setAttributes(Type type) {
        this.mutAttribs = type.deepCopyAttributes();
    }

    public void setAttributes(ArrayList<SymbT> mutAttribs) throws ErrorE {
        if (this.mutAttribs == null) {
            this.mutAttribs = new HashMap<>();
        }

        for (SymbT attribute : mutAttribs) {

            if (this.mutAttribs.containsKey(attribute.getName())) {
                throw new ErrorE("The attributes already exists in the struct " + getName());
            }
            int s = getMemorySize();
            s += attribute.getMemorySize();
            this.setMemorySize(s);

            if(getCategory() == SymbolType.PARAMETER.ordinal()) {
                attribute.setParent(this.getName());
            }
            if(getCategory() == SymbolType.FUNCTION.ordinal() || getCategory() == SymbolType.PROCEDURE.ordinal()) {
                attribute.setScope(this.getName());
            }
            attribute.setScope(this.getName());
            this.mutAttribs.put(attribute.getName(), attribute);
        }
    }

    public SymbT getMutAttribute(String name) {
        return this.mutAttribs.get(name);
    }

    public void setMutValonAttribute(String name, Object value) throws ErrorE {
        Object val = value;
        if(value instanceof SymbT) {
            val = ((SymbT) value).getValue();
        }

        this.mutAttribs.get(name).setValue(val);
    }


    //tostring
    @Override
    public String toString() {
        return this.getName() + "";
    }

}
