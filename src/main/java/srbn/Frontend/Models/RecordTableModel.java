package srbn.Frontend.Models;

import srbn.Backend.Domain.SymbT;

import javax.swing.table.AbstractTableModel;
import java.util.Map;

public class RecordTableModel extends AbstractTableModel {

    private Map<String, SymbT> map;
    private String[] names = {"nombre", "valor", "tipo", "categoria", "Arreglo", "tamaño"};

    public RecordTableModel(Map<String, SymbT> map) {
        this.map = map;
    }


    @Override
    public String getColumnName(int index) {
        return names[index];
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
    public Object getValueAt(int rowIndex, int columnIndex) {

        Map.Entry<String, SymbT> entry = (Map.Entry<String, SymbT>) map.entrySet().toArray()[rowIndex];
        String key = entry.getKey();
        SymbT symb = entry.getValue();

        switch (columnIndex) {
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
                return symb.getMemorySize();
        }

        return null;
    }
}
