package srbn.Frontend.Models;

import srbn.Backend.Domain.Type;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypesTableModel extends AbstractTableModel {

    private LinkedHashMap<String, Type> map;
    private String[] names = {"nombre",  "p-tipo", "Arreglo", "inicio", "final", "tamaño"};

    public TypesTableModel(LinkedHashMap<String, Type> map) {
        this.map = map;
    }


    @Override
    public int getRowCount() {
        return map.size();
    }

    @Override
    public int getColumnCount() {
        return names.length;
    }

    @Override
    public String getColumnName(int index) {
        return names[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int colIndex) {
        Map.Entry<String, Type> entry = (Map.Entry<String, Type>) map.entrySet().toArray()[rowIndex];
        String key = entry.getKey();
        Type tp = entry.getValue();

        if (tp.isArray()) {
            return setObject(colIndex, key + "[" + tp.getMemorySize() + "]", tp);
        } else {
            return setObject(colIndex, key, tp);
        }

    }

    public Object setObject(int colIndex, String key, Type symb) {
        switch (colIndex) {
            case 0:
                return key;
            case 1:
                return symb.getStrType();
            case 2:
                return symb.isArray();
            case 3:
                return symb.getInitSize();
            case 4:
                return symb.getFinalSize();
            case 5:
                return symb.getMemorySize();
            default:
                return null;

        }
    }

}
