package srbn.Frontend.Drivers;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import srbn.Backend.Domain.SymbT;
import srbn.Backend.Domain.Tables.Table;
import srbn.Backend.Domain.TypeEnums.VariableType;
import srbn.Frontend.Models.RecordTableModel;

public class DetailsDriver {

    private Table<SymbT> symbTable;
    private JPanel buttons;
    private JPanel table;
    private AbstractTableModel tableModel;

    public DetailsDriver(Table<SymbT> symbTable, JPanel buttons, JPanel table) {
        this.symbTable = symbTable;
        this.buttons = buttons;
        this.table = table;
    }

    public void fillButtons(JLabel label) {

        for (Map.Entry<String, SymbT> entry : symbTable.getTable().entrySet()) {
            String key = entry.getKey();
            SymbT s = entry.getValue();

            if (s.getType() == VariableType.RECORD.ordinal()) {
                JButton b = new JButton(key);
//                b.setSize(); todo set size of button
                b.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        table.removeAll();
                        table.repaint();
                        table.revalidate();
                        tableModel = new RecordTableModel(s.getAttributes());
                        JTable t = new JTable(tableModel);
                        label.setText(key);
                        label.repaint();
                        table.add(t);
                        table.setVisible(true);
                        table.repaint();
                        table.revalidate();

                    }
                });
                buttons.add(b);
            }

        }

    }

}
