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
 * @author Tan Tommy Rin
 *
 */
public class TrouNoir {

	private transient Shape shapeTrou;
	/** La masse du trou noir **/
	private double masseTrouNoir;
	/** "Vitesse de la lumiere" **/
	private int c = 30;
	/** Constante gravitationnelle terrestre. */
	private static final double G = 9.80665;

	private transient Shape shapeZone;
	private transient Area zoneAire;
	private transient Area zoneAireCopie;
	private transient Area aireVoiture;
	private transient Area aireVoiture1;

	private double pixelsParMetre;

	private Ellipse2D.Double trou;
	private Ellipse2D.Double zone;
	private Voiture voiture;
	private double diametre;
	/** Le rayon du trou noir **/
	private double rayon;
	private Vecteur2D position;
	/** Valeur true ou false de si le trou noir est en contact avec la voiture **/
	private boolean enContactAvecTrouNoir = false;

	/**
	 * Constructeur permettant de créer un trou noir
	 * 
	 * @param pos      Vecteur2D de la position du trou noir
	 * @param diametre Le diametre du trou noir
	 */
	// Tan Tommy Rin

	public TrouNoir(Vecteur2D pos, double diametre) {
		this.diametre = diametre;
		this.position = pos;
		masseTrouNoir = 1100;
		this.rayon = (2 * G * this.masseTrouNoir) / (c * c);
		creerLaGeometrie();
	}

	/**
	 * Méthode qui permet de dessiner sur le g2d
	 * 
	 * @param g2d Le composant graphique
	 */
	// Alexis Pineda-Alvarado
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dcop = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();

		shapeTrou = mat.createTransformedShape(trou);
		shapeZone = mat.createTransformedShape(zone);
		g2dcop.setColor(Color.MAGENTA);
		g2dcop.fill(shapeTrou);
		g2dcop.setStroke(new BasicStroke(1));
		g2dcop.draw(shapeZone);
		zoneAire = new Area(shapeZone);
		zoneAireCopie = new Area(shapeZone);
	}

	/**
	 * Méthode qui permet de créer la géométrie du trou noir
	 */
//Tan Tommy Rin
	private void creerLaGeometrie() {

		trou = new Ellipse2D.Double(position.getX(), position.getY(), rayon, rayon);
		zone = new Ellipse2D.Double(position.getX() - rayon * 2, position.getY() - rayon * 2, rayon * 5, rayon * 5);
	}

	/**
	 * 
	 * méthode qui détecte la collision de la voiture et le trou noir
	 * 
	 * @param v La voiture affectée
	 * @return La valeur de la collision entre la voiture et le trou noir
	 */
	// Tan Tommy Rin
	public boolean collisionDeLaVoiture(Voiture v) {
		this.voiture = v;
		zoneAireCopie = new Area(zone);
		aireVoiture = new Area(voiture.getCercle());
		aireVoiture1 = new Area(aireVoiture);
		aireVoiture1.intersect(zoneAireCopie);

		if (!aireVoiture1.isEmpty()) {
			enContactAvecTrouNoir = true;

		} else {

			enContactAvecTrouNoir = false;
		}

		return enContactAvecTrouNoir;
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

	public double getMasseTrouNoir() {
		return masseTrouNoir;
	}

	public void setMasseTrouNoir(int masseTrouNoir) {
		this.masseTrouNoir = masseTrouNoir;
	}

	public boolean isEnContactAvecTrouNoir() {
		return enContactAvecTrouNoir;
	}

	public void setEnContactAvecTrouNoir(boolean enContactAvecTrouNoir) {
		this.enContactAvecTrouNoir = enContactAvecTrouNoir;
	}
}