package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

import geometrie.Vecteur2D;
import interfaces.Dessinable;

/**
 * 
 * Classe qui creer les boules qui seront dans le carre de fumee
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class BouleFumee implements Dessinable, Serializable {

	//private static final long serialVersionUID = 1L;
	/** Position de la boule fumee **/
	Vecteur2D position;
	/** Diametre de la boule fumee car cela est va etre un cercle **/
	double diametre;
	/** Forme de la boule de fumee **/
	Ellipse2D circle;

	/**
	 * Méthode qui permet de creer la boule de fumee a l'aide des parametres
	 * 
	 * @param position
	 * @param diametre
	 */
	// Alexis Pineda-Alvarado
	public BouleFumee(Vecteur2D position, double diametre) {
		this.position = position;
		this.diametre = diametre;

		creerLaGeometrie();
	}

	/**
	 * Méthode qui permet de créer la boule selon la position
	 */
	// Alexis Pineda-Alvarado
	private void creerLaGeometrie() {

		circle = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre);
	}

	/**
	 * Méthode qui dessine la boule de fumee
	 * 
	 * @param g2d Composant graphique
	 */
	// Alexis Pineda-Alvarado
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dCop = (Graphics2D) g2d.create();

		g2dCop.setColor(Color.gray);
		g2dCop.fill(circle);
	}

}
