package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.util.HashMap;

import javax.swing.JOptionPane;

import utilitaireObjets.Regroupement;

/**
 * Classe offrant un ensemble de m�thodes pour illustrer le fonctionnement des
 * fichiers texte et binaires.
 * 
 * @author Caroline Houle
 * @author Tan Tommy Rin
 * @author Ludovic Julien
 */
public class GestionnaireDeFichiersSurLeBureau {

	String sousDossierSurBureau = "SauvegardePiste";

	private int nombrePiste = 1;

// fichiers binaires (objets)
	private String nomFichBinRegroupement = "Piste" + nombrePiste + ".dat";

	private String nomFichBinComboBox = "comboBox.dat";

	/**
	 * Constructeur par défault
	 */
	public GestionnaireDeFichiersSurLeBureau() {

	}

	/**
	 * crée un fichier binaire et y inscrit un objet regroupement (composé de
	 * d'autres objets) Le place à un endroit spécifique sur le Bureau de
	 * l'utilisateur
	 * 
	 * @param regroupement Le groupe que l'on sauvegarde
	 */
//Tan Tommy Rin

	public void ecrireFichierBinBureauRegroupement(Regroupement regroupement) {
		nomFichBinRegroupement = "Piste" + nombrePiste + ".dat";

// chemin d'acces au dossier
		File dossier = new File(System.getProperty("user.home"), "Desktop" + "\\" + sousDossierSurBureau);

// on cree le dossier s'il n'existe pas
		if (dossier.mkdir()) {
			System.out.println("\nLe dossier " + dossier.toString() + " a été créé car il n'existait pas...");
		}

// chemin d'acces au fichier de travail
		File fichierDeTravail = new File(dossier + "\\" + nomFichBinRegroupement);

		ObjectOutputStream oos = null;

		try {

			oos = new ObjectOutputStream(new FileOutputStream(fichierDeTravail));

// on écrit chacun des objets
			oos.writeObject(regroupement);
			System.out.println(
					"\nLes informations sur la voiture et le regroupement sont écrites avec succès. \nLe fichier "
							+ fichierDeTravail.toString() + " est pret pour la lecture!");

		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur à l'écriture:");
			e.printStackTrace();
		}

		finally {
// on ex�cutera toujours ceci, erreur ou pas
			try {
				oos.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erreur rencontrée lors de la fermeture!");
				e.printStackTrace();
			}
		} // fin finally

	}

	/**
	 * lit un fichier binaire et y lit un objet regroupement (compos� de d'autres
	 * objets) Le fichier est a un endroti spécifique sur le Bureau de l'utilisateur
	 * 
	 * @param nomFichierVoulu le nom du fichier voulu
	 * @return le groupe lue
	 */
//Tan Tommy Rin

	public Regroupement lireFichierBinBureauRegroupement(String nomFichierVoulu) {
		nomFichBinRegroupement = nomFichierVoulu;
		Regroupement regroupement = null;
		ObjectInputStream ois = null;

// chemin d'acces au fichier de travail, qui sera sur le Bureau
		File fichierDeTravail = new File(System.getProperty("user.home"),
				"Desktop" + "\\" + sousDossierSurBureau + "\\" + nomFichBinRegroupement);

// on teste si le fichier à lire existe
		if (!fichierDeTravail.exists()) {
			JOptionPane.showMessageDialog(null,
					"Probl�me! Le fichier " + fichierDeTravail.toString() + " n'existe pas...");
		}

		try {
			ois = new ObjectInputStream(new FileInputStream(fichierDeTravail));
			regroupement = (Regroupement) ois.readObject();

		}

		catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "L'objet lu est d'une classe inconnue");
			e.printStackTrace();
		}

		catch (InvalidClassException e) {
			JOptionPane.showMessageDialog(null, "Les classes utilis�es pour l'�criture et la lecture diff�rent!");
			e.printStackTrace();
		}

		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fichier  " + fichierDeTravail.toString() + "  introuvable!");
			e.printStackTrace();
		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur rencontree lors de la lecture");
			e.printStackTrace();
		}

		finally {

			try {
				ois.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erreur rencontr�e lors de la fermeture!");
				e.printStackTrace();
			}
		} // fin finally
		return regroupement;

	}

	public String getNomFichBinRegroupement() {
		return nomFichBinRegroupement;
	}

	public void setNomFichBinRegroupement(String nomFichBinRegroupement) {
		this.nomFichBinRegroupement = nomFichBinRegroupement;
	}

	public String getNomFichBinComboBox() {
		return nomFichBinComboBox;
	}

	public void setNomFichBinComboBox(String nomFichBinComboBox) {
		this.nomFichBinComboBox = nomFichBinComboBox;
	}

	public int getNombrePiste() {
		return nombrePiste;
	}

	public void setNombrePiste(int nombrePiste) {
		this.nombrePiste = nombrePiste;
	}

	/**
	 * crée un fichier binaire et y inscrit un objet regroupement (composé de
	 * d'autres objets) Le place à un endroit spécifique sur le Bureau de
	 * l'utilisateur. Ceci est pour une piste deja présente.
	 * 
	 * @param nomVoulu     le nom voulu à sauvegarder
	 * @param regroupement Le groupe que l'on sauvegarde
	 */
//Tan Tommy Rin
	public void ecrireFichierBinBureauRegroupement2(Regroupement regroupement, String nomVoulu) {
		nomFichBinRegroupement = nomVoulu;

// chemin d'acces au dossier
		File dossier = new File(System.getProperty("user.home"), "Desktop" + "\\" + sousDossierSurBureau);

// on cree le dossier s'il n'existe pas
		if (dossier.mkdir()) {
			System.out.println("\nLe dossier " + dossier.toString() + " a été créé car il n'existait pas...");
		}

// chemin d'acces au fichier de travail
		File fichierDeTravail = new File(dossier + "\\" + nomFichBinRegroupement);

		ObjectOutputStream oos = null;

		try {

			oos = new ObjectOutputStream(new FileOutputStream(fichierDeTravail));

// on �crit chacun des objets
			oos.writeObject(regroupement);
			System.out.println(
					"\nLes informations sur la voiture et le regroupement sont écrites avec succès. \nLe fichier "
							+ fichierDeTravail.toString() + " est pret pour la lecture!");

		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur à l'écriture:");
			e.printStackTrace();
		}

		finally {
// on ex�cutera toujours ceci, erreur ou pas
			try {
				oos.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erreur rencontrée lors de la fermeture!");
				e.printStackTrace();
			}
		} // fin finally

	}

	/**
	 * méthode qui permet de creer un fichier (S'il existe pas déjà) et d'ecrire a
	 * l'interieur les donner du gagant
	 * 
	 * @param nomUtilisateur nom d'utilisateur du gagant de la course
	 * @param temps          le temps fait pour terminer la course
	 * @param piste          la piste qu'il a jouer
	 */
	// Ludovic Julien
	public static void ecrireFichier(String nomUtilisateur, double temps, String piste) {
		String cheminFichier = System.getProperty("user.home") + "/Desktop/donnees.txt";	

		try {
			// Créer le fichier s'il n'existe pas déjà
			Path fichier = Paths.get(cheminFichier);
			if (!Files.exists(fichier)) {
				Files.createFile(fichier);
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true));

			writer.write(nomUtilisateur + ";" + temps + ";" + piste);
			writer.newLine();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	 public static List<InfoLigne> lireFichier(String nomFichier) throws FileNotFoundException {
	        List<InfoLigne> listeLignes = new ArrayList<>();
	        File dossier = new File(System.getProperty("user.home") + "/Desktop/"+ nomFichier);

	        Scanner scanner = new Scanner(dossier);
	        while (scanner.hasNextLine()) {
	            String ligne = scanner.nextLine();
	            String[] infos = ligne.split(";");
	            String nom = infos[0];
	            double temps = Double.parseDouble(infos[1]);
	            String piste = infos[2];
	            listeLignes.add(new InfoLigne(nom, temps, piste));
	        }

	        scanner.close();
	        return listeLignes;
	    }
	
	
	 public static Map<String, InfoLigne> trouverMeilleursTemps(List<InfoLigne> listeLignes) {
	        Map<String, InfoLigne> meilleursTemps = new HashMap<>();

	        for (InfoLigne ligne : listeLignes) {
	            String piste = ligne.getPiste();
	            if (!meilleursTemps.containsKey(piste) || ligne.getTemps() < meilleursTemps.get(piste).getTemps()) {
	                meilleursTemps.put(piste, ligne);
	            }
	        }

	        return meilleursTemps;
	    }
	

	public static String getBureau() {
		// TODO Auto-generated method stub
		return System.getProperty("user.home");
	}


}