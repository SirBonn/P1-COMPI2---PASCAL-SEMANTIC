package srbn.Frontend;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import srbn.Backend.Management.Files.FilesDriver;

/**
 *
 * @author ADMIN
 */
public class TabDriver {

    FilesDriver fd = new FilesDriver();

    private Component parentComp;

    public TabDriver(Component parentComp) {
        this.parentComp = parentComp;
    }

    public void addTabWithCloseButton(final JTabbedPane tabbedPane, final EditPanel panel, String title) {
        tabbedPane.addTab(title, panel);
        int index = tabbedPane.indexOfComponent(panel);

        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabPanel.setOpaque(false);

        JLabel tabLabel = new JLabel(title);

        JButton closeButton = new JButton("x");
        closeButton.setBorder(null);
        closeButton.setPreferredSize(new Dimension(17, 17));
        closeButton.setFocusable(false);
        closeButton.setMargin(new Insets(0, 0, 0, 0));

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int tabIndex = tabbedPane.indexOfComponent(panel);
                if (tabIndex != -1) {
                    tabbedPane.removeTabAt(tabIndex);
                }

                if (panel.getInputText().toString().isBlank()) {
                    fileSaver(panel);
                }
            }
        });

        tabPanel.add(tabLabel);
        tabPanel.add(closeButton);

        tabbedPane.setTabComponentAt(index, tabPanel);
    }

    public void addTabWithContent(final JTabbedPane tabbedPane, final EditPanel panel, DefaultMutableTreeNode root, DefaultTreeModel treeModel) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                panel.getInputText().setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    panel.getInputText().append(line + "\n");
                }
                panel.setFile(selectedFile);
//                addFileToTree(selectedFile, root, treeModel); TODO fix
                addTabWithCloseButton(tabbedPane, panel, selectedFile.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void fileSaver(EditPanel panel) {

        if (panel.getFile() == null) {
            int option = JOptionPane.showConfirmDialog(this.parentComp, "Desea guardar el nuevo archivo?",
                    "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                fd.saveNewFile(panel.getInputText().getText());
            } else if (option == JOptionPane.NO_OPTION) {

                System.out.println("Acción cancelada.");
            }
        } else {
            fd.saveFile(panel.getFile(), panel.getInputText().getText());
        }
    }

    private void addFileToTree(File file, DefaultMutableTreeNode root, DefaultTreeModel treeModel) {
        DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file.getName());
        root.add(fileNode);
        treeModel.reload();
    }

    public void addFileToTree(EditPanel panel, DefaultMutableTreeNode root, DefaultTreeModel treeModel) {
        DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(panel.getFile().getName());
        root.add(fileNode);
        treeModel.reload();
    }

}
