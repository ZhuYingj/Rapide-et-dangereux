package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;

/**
 * Classe qui permet de créer et gérer un trou noir
 * 
 * @author Alexis Pineda-Alvarado
 *
 */
public class TrouNoir {

	private transient Shape shapeTrou;
	private transient Area bouleDeNeigeAire;
	private transient Area bouleDeNeigeAireCopie;
	private transient Area aireVoiture;

	private TypeObjetSpecial typeObjet = TypeObjetSpecial.TROUNOIR;

	private double pixelsParMetre;;

	private Ellipse2D.Double trou;

	private double diametre;
	private Vecteur2D position;

	/**
	 * Constructeur permettant de créer un trou noir
	 * 
	 * @param pos      Vecteur2D de la position du trou noir
	 * @param diametre Le diametre du trou noir
	 */

	public TrouNoir(Vecteur2D pos, double diametre) {
		this.diametre = diametre;
		this.position = pos;

		creerLaGeometrie();
	}

	/**
	 * Méthode qui permet de dessiner sur le g2d
	 * 
	 * @param g2d Le composant graphique
	 */

	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dcop = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
//		mat.scale(pixelsParMetre, pixelsParMetre);
		shapeTrou = mat.createTransformedShape(trou);
		g2dcop.setColor(Color.MAGENTA);
		g2dcop.fill(shapeTrou);

		// bouleDeNeigeAire = new Area(shapeTrou);
		// bouleDeNeigeAireCopie = new Area(bouleDeNeigeAire);

	}

	/**
	 * Méthode qui permet de créer la géométrie du trou noir
	 */

	private void creerLaGeometrie() {

		trou = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre);
	}

	/**
	 * Méthode qui permet de changer le nombre de pixel par mètre par un nombre
	 * voulu
	 * 
	 * @param pixelsParMetreVoulu
	 */
	// Par Alexis Pineda-Alvarado
	public void setPixelsParMetre(double pixelsParMetreVoulu) {
		this.pixelsParMetre = pixelsParMetreVoulu;

	}

	/**
	 * Méthode qui retourne le nombre de pixels par metre
	 * 
	 * @return nombre de pixel par metre
	 */
	// Par Alexis Pineda-Alvarado
	public double getPixelsParMetre() {
		return this.pixelsParMetre;
	}

}