package application;

import java.io.BufferedWriter;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

import utilitaireObjets.Regroupement;

/**
 * Classe offrant un ensemble de m�thodes pour illustrer le fonctionnement des
 * fichiers texte et binaires.
 * 
 * // Caroline Houle Inspiration pour cette classe
 * 
 * @author Tan Tommy Rin
 * @author Ludovic Julien
 */
public class GestionnaireDeFichiersSurLeBureau {

	private String sousDossierSurBureau = "SauvegardePiste";

	private int nombrePiste = 1;

	// fichiers binaires (objets)
	private String nomFichBinRegroupement = "Piste" + nombrePiste + ".dat";

	private String nomFichBinComboBox = "comboBox.dat";

	/**
	 * Constructeur par défault
	 */
	// Tan Tommy Rin
	public GestionnaireDeFichiersSurLeBureau() {

	}

	/**
	 * crée un fichier binaire et y inscrit un objet regroupement (composé de
	 * d'autres objets) Le place à un endroit spécifique sur le Bureau de
	 * l'utilisateur
	 * 
	 * @param regroupement Le groupe que l'on sauvegarde
	 */
	// Tan Tommy Rin

	public void ecrireFichierBinBureauRegroupement(Regroupement regroupement) {
		nomFichBinRegroupement = "Piste" + nombrePiste + ".dat";

		// chemin d'acces au dossier
		File dossier = new File(System.getProperty("user.home"), "Desktop" + "\\" + sousDossierSurBureau);

		// on cree le dossier s'il n'existe pas
		if (dossier.mkdir()) {
		
		}

		// chemin d'acces au fichier de travail
		File fichierDeTravail = new File(dossier + "\\" + nomFichBinRegroupement);

		ObjectOutputStream oos = null;

		try {

			oos = new ObjectOutputStream(new FileOutputStream(fichierDeTravail));

			// on écrit chacun des objets
			oos.writeObject(regroupement);
		

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
	// Tan Tommy Rin

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
	// Tan Tommy Rin
	public void ecrireFichierBinBureauRegroupement2(Regroupement regroupement, String nomVoulu) {
		nomFichBinRegroupement = nomVoulu;

		// chemin d'acces au dossier
		File dossier = new File(System.getProperty("user.home"), "Desktop" + "\\" + sousDossierSurBureau);

		// on cree le dossier s'il n'existe pas
		if (dossier.mkdir()) {
	
		}

		// chemin d'acces au fichier de travail
		File fichierDeTravail = new File(dossier + "\\" + nomFichBinRegroupement);

		ObjectOutputStream oos = null;

		try {

			oos = new ObjectOutputStream(new FileOutputStream(fichierDeTravail));

			// on �crit chacun des objets
			oos.writeObject(regroupement);


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

	/**
	 * méthode qui permet de lire le fichier des donnes
	 * 
	 * @param nomFichier nom du fichier en question
	 * @return liste avec les données
	 * @throws FileNotFoundException exeption si fichier introuvable
	 */
	// Ludovic Julien
	public static List<InfoLigne> lireFichier(String nomFichier) throws FileNotFoundException {
		List<InfoLigne> listeLignes = new ArrayList<>();
		File dossier = new File(System.getProperty("user.home") + "/Desktop/" + nomFichier);

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

	/**
	 * méthode qui permet de retourner la moyenne de temps de chaque piste
	 * 
	 * @param listeLignes liste des donner de temps
	 * @param piste       la piste en question (Mexique,Canada,Italie)
	 * @return la moyenne de temps de chaque piste
	 */
	// Ludovic Julien
	public static double calculerMoyenne(List<InfoLigne> listeLignes, String piste) {
		double totalTemps = 0.0;
		int nombreLignes = 0;

		for (InfoLigne ligne : listeLignes) {
			if (ligne.getPiste().equals(piste)) {
				totalTemps += ligne.getTemps();
				nombreLignes++;
			}
		}
		return nombreLignes > 0 ? totalTemps / nombreLignes : 0.0;
	}

	/**
	 * méthode qui permet de retourner le meilleur temps de chaque piste
	 * 
	 * @param listeLignes liste des donner de temps
	 * @return meilleur temps de chaque piste
	 */
	// Ludovic Julien
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

	/**
	 * méthode qui permet de retourner la moyenne de temps de chaque piste
	 * 
	 * @param listeLignes liste des donner de temps
	 * @return la moyenne de temps de chaque piste
	 */
	// Ludovic Julien
	public static Map<String, Double> calculerMoyennes(List<InfoLigne> listeLignes) {
		Map<String, Double> moyennes = new HashMap<>();

		for (InfoLigne ligne : listeLignes) {
			String piste = ligne.getPiste();
			double moyenne = calculerMoyenne(listeLignes, piste);
			moyennes.put(piste, moyenne);
		}
		return moyennes;
	}

	/**
	 * méthode qui permet de retourner le nombre de fois que chaque piste à été joué
	 * 
	 * @param listeLignes liste des donner de temps
	 * @return comptage nombre de fois que la piste a été jouer
	 */
	// Ludovic Julien
	public static Map<String, Integer> compterPistes(List<InfoLigne> listeLignes) {
		Map<String, Integer> comptages = new HashMap<>();

		for (InfoLigne ligne : listeLignes) {
			String piste = ligne.getPiste();
			if (comptages.containsKey(piste)) {
				comptages.put(piste, comptages.get(piste) + 1);
			} else {
				comptages.put(piste, 1);
			}
		}
		return comptages;
	}
}