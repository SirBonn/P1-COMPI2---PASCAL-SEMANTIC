package srbn.Backend.Domain.Tables;

import java.util.LinkedHashMap;

public class Table <T>{

    private LinkedHashMap<String, T> table = new java.util.LinkedHashMap<>();

    public void addSymbol(String name, T element) {
        table.put(name, element);
    }

    public T get(String name) {
        return table.get(name);
    }

    public void delete(String name){
        table.remove(name);
    }

    public LinkedHashMap<String, T> getTable() {
        return table;
    }

    

}
