package srbn.Frontend.Models;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

import srbn.Backend.Domain.SymbT;

public class VarsTableModel extends AbstractTableModel {

    private LinkedHashMap<String, SymbT> map;
    private String[] names = {"nombre", "valor", "record", "tipo", "categoria", "Arreglo", "inicio", "final", "tamaño", "boolean", "M-pos"};

    public VarsTableModel(LinkedHashMap<String, SymbT> map) {
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
        Map.Entry<String, SymbT> entry = (Map.Entry<String, SymbT>) map.entrySet().toArray()[rowIndex];
        String key = entry.getKey();
        SymbT symb = entry.getValue();

        if (symb.isArray()) {
            return setObject(colIndex, key + "[" + symb.getMemorySize() + "]", symb);
        } else {
            return setObject(colIndex, key, symb);
        }

    }

    public Object setObject(int colIndex, String key, SymbT symb) {
        switch (colIndex) {
            case 0:
                return key;
            case 1:
                return symb.getValue();
            case 2:
                return symb.getParent();
            case 3:
                return symb.getStrType();
            case 4:
                return symb.getCategory();
            case 5:
                return symb.isArray();
            case 6:
                return symb.getInitSize();
            case 7:
                return symb.getFinalSize();
            case 8:
                return symb.getMemorySize();
            case 9:
                return symb.getBooleanValue();
            case 10:
                return symb.getDir();
            default:
                return null;

        }
    }

}
