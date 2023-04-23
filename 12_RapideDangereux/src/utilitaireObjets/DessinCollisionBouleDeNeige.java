package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

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
	private Vecteur2D position;
	/** Diametre de la voiture pour un bloc de glace symmétrique **/
	private double diametre;
	/** La forme du dessin **/
	private Rectangle2D.Double rectangle;
	/** Couleur de la voiture **/
	private Color couleur;
	/** Forme de la voiture **/
	private Ellipse2D circle;

	/**
	 * Constructeur qui créé la forme du bloc de glace
	 * 
	 * @param pos    Position de la voiture
	 * @param dia    Diametre de la voiture
	 * @param col    Couleur de la voiture
	 * @param cercle Forme cercle de la voiture
	 */
	// Kevin Nguyen
	public DessinCollisionBouleDeNeige(Vecteur2D pos, double dia, Color col, Ellipse2D cercle) {
		this.position = pos;
		this.diametre = dia;
		this.couleur = col;
		this.circle = cercle;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui dessine le bloc de glace
	 * 
	 * @param g2d Composant graphique
	 */
	// Kevin Nguyen
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dCop = (Graphics2D) g2d.create();

		g2dCop.setColor(Color.white);
		g2dCop.fill(rectangle);
		g2dCop.setColor(couleur);
		g2dCop.fill(circle);
	}

	/**
	 * Méthode qui permet de créer le carré selon la position de la voiture
	 */
	// Kevin Nguyen
	private void creerLaGeometrie() {

		rectangle = new Rectangle2D.Double(position.getX(), position.getY(), diametre, diametre);
	}

}
