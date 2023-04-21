package dessin;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import application.GestionnaireDeFichiersSurLeBureau;
import application.InfoLigne;

/**
 * zone qui permet d'afficher le tableau record par piste
 * 
 * @author Ludovic Julien
 *
 */

public class TableauRecord extends JPanel{
	private GestionnaireDeFichiersSurLeBureau fichierRecord;
	private static MyTableModel tableModel;
	
	/**
	 * Constructeur de la classe qui permet de creer le tableau
	 */
	public TableauRecord() {
        setLayout(new BorderLayout());
        fichierRecord = new GestionnaireDeFichiersSurLeBureau();

        tableModel = new MyTableModel();
       JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

	/**
	 * class qui initalise le tableau et permet de la modifier
	 * 
	 * @author Ludovic Julien
	 *
	 */
    public class MyTableModel extends AbstractTableModel {
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
     
        
        /**
         * pour mettre à jour le nombre de fois où chaque piste a été jouée
         * 
         * @param piste
         * @param nombreDeFoisJoue
         */
        public void updateNombreDeFoisJoue(String piste, int nombreDeFoisJoue) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals(piste)) {
                    data[i][3] = nombreDeFoisJoue;
                    fireTableDataChanged();
                    break;
                }
            }
        }
        
        /**
         * mettre à jour la moyenne des temps pour chaque piste
         * 
         * @param piste
         * @param moyenne
         */
        public void updateMoyenne(String piste, String moyenne) {
            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals(piste)) {
                    data[i][4] = moyenne;
                    fireTableDataChanged();
                    break;
                }
            }
        }
        
        
        
        
        public void updateRecord(String piste, Map<String, InfoLigne> meilleursTemps) {
            if (piste.equals("Mexique")) {
            	
            	InfoLigne meilleurTemps = meilleursTemps.get("Mexique");
                
               // setValueAt(meilleurTemps.getTemps(), 0, 1);
              
                //setValueAt(meilleurTemps.getNom(), 0, 2);
            	
            	
//                for (int i = 0; i < data.length; i++) {
//                  if (data[i][0].equals(piste)) {
//                      data[i][1] = meilleurTemps.getTemps();
//                      data[i][2] = meilleurTemps.getNom();
//                      fireTableDataChanged();
//                      break;
//                  }
//              }
            	
            	System.out.println(piste);
            	System.out.println(""+meilleurTemps.getTemps());
            	System.out.println(meilleurTemps.getNom());
            	
            	
               
               setValueAt(""+meilleurTemps.getTemps(), 0, 1);
                
                setValueAt(meilleurTemps.getNom(), 0, 2);
                
               // setValueAt(meilleurTemps.getNbFoisJoue(), 0, 3);
             
              //  setValueAt(meilleurTemps.getMoyenne(), 0, 4);
            }
            
            if (piste.equals("Canada")) {
            	
            	InfoLigne meilleurTemps = meilleursTemps.get("Canada");
            	
            	 setValueAt(""+meilleurTemps.getTemps(), 1, 1);
                 
                 setValueAt(meilleurTemps.getNom(), 1, 2);
            }
            
            if (piste.equals("Italie")) {
            	
            	InfoLigne meilleurTemps = meilleursTemps.get("Italie");
            	
            	 setValueAt(""+meilleurTemps.getTemps(), 2, 1);
                 
                 setValueAt(meilleurTemps.getNom(), 2, 2);
            }
            
            
        }
        
    }
    
    public static MyTableModel getTableau() {
    	return tableModel;
    }
    
   
    
}


