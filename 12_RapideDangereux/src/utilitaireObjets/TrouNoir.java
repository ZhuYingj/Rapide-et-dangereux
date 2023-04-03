package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.TypeObjetSpecial;

/**
 * Classe qui permet de créer et gérer un trou noir
 * 
 * @author Alexis Pineda-Alvarado
 *
 */
public class TrouNoir {

	private transient Shape shapeTrou;
	private transient Shape shapezone;

	private double masseTrouNoir;

	public double getMasseTrouNoir() {
		return masseTrouNoir;
	}

	public void setMasseTrouNoir(int masseTrouNoir) {
		this.masseTrouNoir = masseTrouNoir;
	}

	private int c = 30;
	private double g = 9.8;

	private transient Area bouleDeNeigeAire;
	private transient Area bouleDeNeigeAireCopie;
	private transient Area aireVoiture;

	private TypeObjetSpecial typeObjet = TypeObjetSpecial.TROUNOIR;

	private double pixelsParMetre;

	private Ellipse2D.Double trou;
	private Ellipse2D.Double zone;

	private double diametre;
	private double rayon;
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
		masseTrouNoir = 1100;
		this.rayon = (2 * g * this.masseTrouNoir) / (c * c);
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

		shapeTrou = mat.createTransformedShape(trou);
		shapezone = mat.createTransformedShape(zone);
		g2dcop.setColor(Color.MAGENTA);
		g2dcop.fill(shapeTrou);
		g2dcop.setStroke(new BasicStroke(1));
		g2dcop.draw(shapezone);

	}

	/**
	 * Méthode qui permet de créer la géométrie du trou noir
	 */

	private void creerLaGeometrie() {

		trou = new Ellipse2D.Double(position.getX(), position.getY(), rayon, rayon);
		zone = new Ellipse2D.Double(position.getX() - rayon * 2, position.getY() - rayon * 2, rayon * 5, rayon * 5);
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

	public Ellipse2D.Double getZone() {
		return zone;
	}

	public void setZone(Ellipse2D.Double zone) {
		this.zone = zone;
	}

	public Vecteur2D getPosition() {
		return position;
	}

	public void setPosition(Vecteur2D position) {
		this.position = position;
	}
}