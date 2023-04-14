package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;

import geometrie.Vecteur2D;

/**
 * Classe qui dessine un bloc de glace sur une voiture pour l'effet de la
 * collision d'une boule de neige
 * 
 * @author Kevin Nguyen
 *
 */
public class DessinCollisionBouleDeNeige {
	/** Position du bloc de glace **/
	Vecteur2D position;
	/** Diametre de la voiture pour un bloc de glace symmétrique **/
	double diametre;
	/** La forme du dessin **/
	private Rectangle2D.Double rectangle;

	/**
	 * Constructeur qui créé la forme du bloc de glace
	 * 
	 * @param pos Position de la voiture
	 * @param dia Diametre de la voiture
	 */
	public DessinCollisionBouleDeNeige(Vecteur2D pos, double dia) {
		this.position = pos;
		this.diametre = dia;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui dessine le bloc de glace
	 * 
	 * @param g2d Composant graphique
	 */
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dCop = (Graphics2D) g2d.create();

		g2dCop.setColor(Color.white);
		g2dCop.fill(rectangle);
	}

	/**
	 * Méthode qui permet de créer le carré selon la position de la voiture
	 */
	private void creerLaGeometrie() {

		rectangle = new Rectangle2D.Double(position.getX(), position.getY(), diametre, diametre);
	}

}
