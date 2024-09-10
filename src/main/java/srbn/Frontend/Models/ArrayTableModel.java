package srbn.Frontend.Models;

import javax.swing.table.AbstractTableModel;

public class ArrayTableModel extends AbstractTableModel {

    

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
