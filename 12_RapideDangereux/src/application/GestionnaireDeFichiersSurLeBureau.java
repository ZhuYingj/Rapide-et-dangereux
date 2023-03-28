package application;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import geometrie.Vecteur2D;
import utilitaireObjets.Regroupement;
import utilitaireObjets.Voiture;

/**
 * Classe offrant un ensemble de m�thodes pour illustrer le fonctionnement des
 * fichiers texte et binaires
 * 
 * @author Caroline Houle
 *
 */
public class GestionnaireDeFichiersSurLeBureau {

	String sousDossierSurBureau = "SauvegardePiste";

	private int nombrePiste = 1;

	// fichiers binaires (objets)
	private String nomFichBinRegroupement = "regroupement" + nombrePiste + ".dat";

	/**
	 * crée un fichier binaire et y inscrit un objet regroupement (composé de
	 * d'autres objets) Le place à un endroit spécifique sur le Bureau de
	 * l'utilisateur
	 */
	public void ecrireFichierBinBureau(Regroupement regroupement) {
		nomFichBinRegroupement = "regroupement" + nombrePiste + ".dat";

		// on commence par creer un objet voiture. Ce dernier a une position, une
		// Adresse, et
		// une collection de notes (al�atoires)
		Voiture voiture = new Voiture(new Vecteur2D(0, 0), Color.yellow, 50, 16, 0, 60);

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
			oos.writeObject(voiture);
			System.out.println(
					"\nLes informations sur la voiture et le regroupement sont écrites avec succès. \nLe fichier "
							+ fichierDeTravail.toString() + " est pret pour la lecture!");

			nombrePiste++;

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
	 * objets) Le fichier est a un endroti sp�cifique sur le Bureau de l'utilisateur
	 */
	public Regroupement lireFichierBinBureau(String nomFichierVoulu) {
		nomFichBinRegroupement = nomFichierVoulu;
		Regroupement regroupement = null;
		ObjectInputStream ois = null;

		// chemin d'acces au fichier de travail, qui sera sur le Bureau
		File fichierDeTravail = new File(System.getProperty("user.home"),
				"Desktop" + "\\" + sousDossierSurBureau + "\\" + nomFichBinRegroupement);

		// on teste si le fichier � lire existe
		if (!fichierDeTravail.exists()) {
			JOptionPane.showMessageDialog(null,
					"Probl�me! Le fichier " + fichierDeTravail.toString() + " n'existe pas...");
		}

		try {
			System.out.println("OUI");
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
			// on ex�cutera toujours ceci, erreur ou pas
			try {
				ois.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erreur rencontr�e lors de la fermeture!");
				e.printStackTrace();
			}
		} // fin finally
		return regroupement;

	}

	public String getNomFichBinEtud() {
		return nomFichBinRegroupement;
	}

	public void setNomFichBinEtud(String nomFichBinEtud) {
		this.nomFichBinRegroupement = nomFichBinEtud;
	}

}
