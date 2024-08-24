package srbn.Backend.Domain;

import srbn.Backend.Domain.TypeEnums.SymbolType;

import java.nio.ByteBuffer;

public class SymbT extends Type{

    private byte[] value;
    private int category;
    private String scope;
    private boolean isArray = false;
    private int index;
    private int booleanValue; // 1 != false



    public SymbT(String name, int type, int category, String scope) {
        super(name, type);
        this.category = category;
        this.scope = scope;
    }

    public SymbT(String name, int type, int category) {
        super(name, type);
        this.category = category;
        this.scope = "global";
    }

    public SymbT(String name, int type, int initSize, int finalSize, int category) {
        super(name, type, initSize, finalSize);
        this.isArray = true;
        this.category = category;
    }

    public SymbT(String name, int type, Object value) {
        super(name, type);
        this.category = SymbolType.VARIABLE.ordinal();
        this.value = byteConverter(value);
        this.scope = "global";
    }

    public SymbT(String name, int type, boolean value) {
        super(name, type);
        this.category = SymbolType.VARIABLE.ordinal();
        this.scope = "global";
        this.booleanValue = value ? 1 : 0;
    }



    private byte[] byteConverter(Object value) {
        if (value instanceof Integer) {
            return ByteBuffer.allocate(Integer.BYTES).putInt((Integer) value).array();
        } else if (value instanceof Double) {
            return ByteBuffer.allocate(Double.BYTES).putDouble((Double) value).array();
        } else if (value instanceof Character) {
            return ByteBuffer.allocate(Character.BYTES).putChar((Character) value).array();
        } else if (value instanceof Byte) {
            return new byte[]{(Byte) value};
        } else if (value instanceof String) {
            return ((String) value).getBytes();
        } else {
            throw new IllegalArgumentException("Unsupported data type");
        }
    }


    public void setValue(Object value) {
        this.value = byteConverter(value);
    }

    public Object getValue() {
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
                return buffer.getChar();
            case "string":
                return new String(bytes);
            default:
                throw new IllegalArgumentException("Unsupported data type");
        }
    }

}
