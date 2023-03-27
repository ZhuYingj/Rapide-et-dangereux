package dessin;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Cette classe contient des utilitaires pour le traitement des images. Elle
 * sera enrichie au fil de la session. Notez les methodes permettant de
 * redimensionner une image.
 * 
 * @author Caroline Houle
 *
 */
public class OutilsImage {
	/**
	 * Lit le fichier d'image donne en parametre et retourne un objet Image
	 * correspondant
	 * 
	 * @param nomFichier Le nom du fichier d'image
	 * @return Un objet Image pour cette image
	 */

	public static Image lireImage(String nomFichier) {
		Image img = null;
		URL urlFichier = OutilsImage.class.getClassLoader().getResource(nomFichier);
		if (urlFichier == null) {
			JOptionPane.showMessageDialog(null, "Fichier d'image introuvable: " + nomFichier);
		} else {
			try {
				img = ImageIO.read(urlFichier);
			} catch (IOException e) {
				System.out.println("Erreur de lecture du fichier d'image: " + nomFichier);
			}
		}
		return (img);
	}// fin methode

}
