package srbn.Backend.Domain.Tables;

import java.util.Map;

public class Table <T>{

    private Map<String, T> table = new java.util.HashMap<>();

    public void addSymbol(String name, T element) {
        table.put(name, element);
    }

    public T getSymbol(String name) {
        return table.get(name);
    }

}
