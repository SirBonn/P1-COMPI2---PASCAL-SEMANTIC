package srbn.Backend.Domain;

public class Symbol {

    private String name;
    private String value;
    private int type;
    private int Category;
    private int scope;

    public Symbol(String name, String value, int type, int category, int scope) {
        this.name = name;
        this.value = value;
        this.type = type;
        Category = category;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }
}
