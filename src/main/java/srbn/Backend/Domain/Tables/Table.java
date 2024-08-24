package srbn.Backend.Domain.Tables;

import java.util.Map;

public class Table <T>{

    private Map<String, T> table = new java.util.HashMap<>();

    public void addSymbol(String name, T element) {
        table.put(name, element);
    }

    public T get(String name) {
        return table.get(name);
    }

    public void delete(String name){
        table.remove(name);
    }

}
