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
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import application.GestionnaireDeFichiersSurLeBureau;

/**
 * zone qui permet d'afficher le tableau record par piste
 * 
 * @author Ludovic Julien 
 *
 */

public class TableauRecord extends JPanel{
	private GestionnaireDeFichiersSurLeBureau fichierRecord;
	private MyTableModel model;
	
	public TableauRecord() {
        setLayout(new BorderLayout());
        fichierRecord = new GestionnaireDeFichiersSurLeBureau();

        // Définir le modèle de données pour le tableau
       // MyTableModel model = new MyTableModel();
        model = new MyTableModel();
       JTable table = new JTable(model);

       // Mettre à jour le modèle avec les données des fichiers
    //   updateModelWithFileData();
       
        // Ajouter le tableau à notre JPanel
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
	
	
//	  // Mettre à jour le modèle de données avec les données des fichiers
//    private void updateModelWithFileData() {
//        // Définir le chemin d'accès au dossier contenant les fichiers
//        String cheminDossier = fichierRecord.getBureau() + "/mes_fichiers";
//        
//        // Créer un objet File pour représenter le dossier
//        File dossier = new File(cheminDossier);
//
//        // Obtenir un tableau des fichiers dans le dossier
//        File[] fichiers = dossier.listFiles();
//        
//        // Créer une liste pour stocker les données lues à partir des fichiers
//        ArrayList<Object[]> donneesFichiers = new ArrayList<>();
//        
//        // Parcourir le tableau des fichiers et ajouter les données lues à la liste
//        for (File fichier : fichiers) {
//            try {
//                Scanner scanner = new Scanner(fichier);
//                String nomPiste = scanner.nextLine();
//                int recordTemps = Integer.parseInt(scanner.nextLine());
//                String recordPar = scanner.nextLine();
//                int nbFoisJouee = Integer.parseInt(scanner.nextLine());
//                int tempsMoyen = Integer.parseInt(scanner.nextLine());
//                Object[] donneesFichier = {nomPiste, recordTemps, recordPar, nbFoisJouee, tempsMoyen};
//                donneesFichiers.add(donneesFichier);
//                scanner.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        
//        // Ajouter les données lues du fichier dans le modèle de données
//        for (Object[] donneesFichier : donneesFichiers) {
//            model.addRow(donneesFichier);
//        }
//    }
//	

    // Définir le modèle de données pour le tableau
    private class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Piste", "Record(temps en secondes)", "Record Par", "Nb fois ou lapiste a été joué", "moyenne(temps en secondes)"};
        private Object[][] data;
        
        //        private Object[][] data = {
//            {"Mexique", "0:00","-", "0","0"},
//            {"Canada", "0:00","-","0","0"},
//            {"Italie", "0:00","-","0","0"}
//       };
        
        public MyTableModel() {
            // Initialize the table with default data
            data = new Object[][]{
                {"Mexique", "0:00","-", "0","0"},
                {"Canada", "0:00","-","0","0"},
                {"Italie", "0:00","-","0","0"}
            };
            
            // Load data from file and update the table
            chargerDonnees();
        }
        
        private void chargerDonnees() {
            // Get the path to the file
            String cheminFichier = GestionnaireDeFichiersSurLeBureau.getBureau() + "/Desktop/donnees.txt";
            Path fichier = Paths.get(cheminFichier);
            
            try {
                // Read all lines from the file
                List<String> lignes = Files.readAllLines(fichier, StandardCharsets.UTF_8);
                
                // Create a new data array with the appropriate size
                Object[][] newData = new Object[lignes.size()][columnNames.length];
                
                // Parse each line and add the data to the new array
                for (int i = 0; i < lignes.size(); i++) {
                    String[] champs = lignes.get(i).split(";");
                    newData[i][0] = champs[2];
                    newData[i][1] = champs[1];
                    newData[i][2] = champs[0];
                  //  newData[i][3] = 1; // Set the number of times played to 1
                  //  newData[i][4] = champs[1]; // Set the average time to the initial time
                }
                
                // Replace the old data array with the new one
                data = newData;
                
                // Update the table
                fireTableDataChanged();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        private ArrayList<Object[]> donnees = new ArrayList<>();

        public void addRow(Object[] row) {
            donnees.add(row);
            fireTableRowsInserted(donnees.size()-1, donnees.size()-1);
        }

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

