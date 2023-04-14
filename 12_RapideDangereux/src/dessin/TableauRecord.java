package dessin;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TableauRecord extends JPanel{
	
	public TableauRecord() {
        setLayout(new BorderLayout());

        // Définir le modèle de données pour le tableau
        MyTableModel model = new MyTableModel();
       JTable table = new JTable(model);

        // Ajouter le tableau à notre JPanel
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Définir le modèle de données pour le tableau
    private class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Piste", "Record(temps en secondes)", "Record Par", "Nb fois ou lapiste a été joué", "moyenne(temps en secondes)"};
        private Object[][] data = {
            {"Mexique", "0:00","-", "0","0"},
            {"Canada", "0:00","-","0","0"},
            {"Italie", "0:00","-","0","0"}
        };

        @Override
        public int getRowCount() {
            return data.length;
        }

        
        public int getColumnCount() {
            return columnNames.length;
        }

        
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        
        public String getColumnName(int col) {
            return columnNames[col];
        }

       
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

}

