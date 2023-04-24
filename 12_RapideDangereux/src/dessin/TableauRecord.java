package dessin;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.PrintWriter;

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

public class TableauRecord extends JPanel {
	private GestionnaireDeFichiersSurLeBureau fichierRecord;
	private static MyTableModel tableModel;

	/**
	 * Constructeur de la classe qui permet de creer le tableau
	 */
	// Ludovic Julien
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
		private String[] columnNames = { "Piste", "Record(temps en secondes)", "Record Par",
				"Nb fois ou lapiste a été joué", "moyenne(temps en secondes)" };
		private static Object[][] data = { { "Mexique", "0:00", "-", "0", "0" }, { "Canada", "0:00", "-", "0", "0" },
				{ "Italie", "0:00", "-", "0", "0" } };

		@Override
		public int getRowCount() {
			return data.length;
		}

		/**
		 * méthode qui modifie une emplacement de tableaux
		 * 
		 * @param value La valeur voulue
		 * @param row   La ligne du tableau
		 * @param col   La colonne du tableau
		 */
		// Ludovic Julien
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		/**
		 * pour mettre à jour le nombre de fois où chaque piste a été jouée
		 * 
		 * @param piste            piste en question
		 * @param nombreDeFoisJoue nombre de fois jouer de chaque piste
		 */
		// Ludovic Julien
		public void updateNombreDeFoisJoue(String piste, String nombreDeFoisJoue) {
			if (piste.equals("Mexique")) {
				setValueAt(nombreDeFoisJoue, 0, 3);
			}
			if (piste.equals("Canada")) {
				setValueAt(nombreDeFoisJoue, 1, 3);
			}
			if (piste.equals("Italie")) {
				setValueAt(nombreDeFoisJoue, 2, 3);
			}

		}

		/**
		 * mettre à jour la moyenne des temps pour chaque piste
		 * 
		 * @param piste   piste ne question
		 * @param moyenne moyen de tout les piste
		 */
		// Ludovic Julien
		public void updateMoyenne(String piste, String moyenne) {

			if (piste.equals("Mexique")) {
				setValueAt(moyenne, 0, 4);
			}
			if (piste.equals("Canada")) {
				setValueAt(moyenne, 1, 4);
			}
			if (piste.equals("Italie")) {
				setValueAt(moyenne, 2, 4);
			}

		}

		/**
		 * méthode qui permet de modifier le tableau classement
		 * 
		 * @param piste          piste en question
		 * @param meilleursTemps temps meilleur
		 */
		// Ludovic Julien
		public void updateRecord(String piste, Map<String, InfoLigne> meilleursTemps) {
			if (piste.equals("Mexique")) {
				InfoLigne meilleurTemps = meilleursTemps.get("Mexique");
				setValueAt("" + meilleurTemps.getTemps(), 0, 1);
				setValueAt(meilleurTemps.getNom(), 0, 2);
			}

			if (piste.equals("Canada")) {
				InfoLigne meilleurTemps = meilleursTemps.get("Canada");
				setValueAt("" + meilleurTemps.getTemps(), 1, 1);
				setValueAt(meilleurTemps.getNom(), 1, 2);
			}

			if (piste.equals("Italie")) {
				InfoLigne meilleurTemps = meilleursTemps.get("Italie");
				setValueAt("" + meilleurTemps.getTemps(), 2, 1);
				setValueAt(meilleurTemps.getNom(), 2, 2);
			}
		}

		/**
		 * méthode qui permet de set les donner du tableaux, aux données initial
		 * 
		 */
		// Ludovic Julien
		public void reinitialiserTableau() {
			data = new Object[][] { { "Mexique", "0:00", "-", "0", "0" }, { "Canada", "0:00", "-", "0", "0" },
					{ "Italie", "0:00", "-", "0", "0" } };
			for (int row = 0; row < data.length; row++) {
				for (int col = 0; col < data[row].length; col++) {
					setValueAt(data[row][col], row, col);
				}
			}
			fireTableDataChanged();
		}

		/**
		 * méthode qui permet de supprmier tout les données d'un fichier
		 * 
		 * @param nomFichier nom du fichier
		 */
		// Ludovic Julien
		public static void supprimerContenuFichier(String nomFichier) {
			try {
				PrintWriter writer = new PrintWriter(
						new FileWriter(System.getProperty("user.home") + "/Desktop/" + nomFichier));
				writer.print("");
				writer.close();
			} catch (IOException e) {
				System.err.println("Erreur lors de la suppression du contenu du fichier");
			}
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

		/**
		 * méthode qui renvoie 2 méthode pour permettre l'appel de ceux ci dans une
		 * autre classe
		 * 
		 */
		// Ludovic Julien
		public static void actionReinitialiser() {
			tableModel.reinitialiserTableau();
			supprimerContenuFichier("donnees.txt");
		}

		/**
		 * Méthode qui permet de trier le tableau
		 */
		// Ludovic Julien
		public void trierTableau() {
			Arrays.sort(data, new LigneComparator());
			fireTableDataChanged();
		}
	}

	/**
	 * Classe permettant de comparer les lignes du tableau
	 * 
	 * @author Ludovic Julien
	 *
	 */
	public class LigneComparator implements Comparator<Object[]> {

		/**
		 * Méthode qui permet de comparer deux lignes d'un tableau
		 * 
		 * @param ligne1 La premiere ligne
		 * @param ligne2 La deuxième ligne
		 * @return Le temps le plus court
		 */
		// Ludovic Julien
		public int compare(Object[] ligne1, Object[] ligne2) {
			int temps1 = convertirEnSecondes((String) ligne1[1]);
			int temps2 = convertirEnSecondes((String) ligne2[1]);

			if (temps1 == temps2) {
				return ((String) ligne1[0]).compareTo((String) ligne2[0]);
			}

			return Integer.compare(temps1, temps2);
		}

		/**
		 * Convertir un temps au format "mm:ss" en secondes
		 * 
		 * @param temps Le temps en chaine
		 * @return le temps converti
		 */
		// Ludovic Julien
		private int convertirEnSecondes(String temps) {
			String[] tempsArray = temps.split(":");
			int minutes = Integer.parseInt(tempsArray[0]);
			int secondes = Integer.parseInt(tempsArray[1]);
			return minutes * 60 + secondes;
		}
	}

	/**
	 * méthode qui retourne le tableau
	 * 
	 * @return tableaumodel
	 */
	// Ludovic Julien
	public static MyTableModel getTableau() {
		return tableModel;
	}

}
