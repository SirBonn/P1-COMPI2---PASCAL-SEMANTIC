package srbn.Frontend.Drivers;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TablesDriver {

    private AbstractTableModel model;
    private JFrame frame = new JFrame("Tabla de tipos -Pascal S. A-");

    public TablesDriver(AbstractTableModel model) {
        this.model = model;
    }

    public void showTable() {
        JTable table = new JTable(model);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void dispose() {
        frame.dispose();
    }
    
    

}
