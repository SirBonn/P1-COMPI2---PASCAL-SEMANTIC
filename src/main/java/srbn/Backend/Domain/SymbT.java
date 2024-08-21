package srbn.Backend.Domain;

public class SymbT {

    private String name;
    private String value;
    private int category;
    private int scope;

    public SymbT(String name, String value, int type, int category, int scope) {
        this.name = name;
        this.value = value;
        this.category = category;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }
}
