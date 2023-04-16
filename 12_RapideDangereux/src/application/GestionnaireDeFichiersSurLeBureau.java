package application;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import geometrie.Vecteur2D;
import utilitaireObjets.Regroupement;
import utilitaireObjets.Voiture;

/**
 * Classe offrant un ensemble de m�thodes pour illustrer le fonctionnement des
 * fichiers texte et binaires.
 * 
 * @author Caroline Houle
 * @author Tan Tommy Rin
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
	 * lit un fichier binaire et y lit un objet regroupement (compos� de d'autres
	 * objets) Le fichier est a un endroti sp�cifique sur le Bureau de l'utilisateur
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

// on teste si le fichier � lire existe
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

}