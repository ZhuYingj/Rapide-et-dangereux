package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;

/**
 * class qui permet de creer un objet colle
 * 
 * @author Alexis Pineda-Alvarado
 *
 */
public class Colle implements Dessinable {

	/** Taille du morceau de la piste **/
	private int taillePiste = 80;

	private TypeObjetSpecial typeObjet = TypeObjetSpecial.COLLE;
	private transient Shape shapeColle;
	private transient Area colleAire;
	private transient Area colleAireCopie;

	private transient Area aireVoiture;
	private transient Area aireVoiture1;
	private double pixelsParMetre;;
	private boolean contactColle = false;
	private Ellipse2D.Double colle;

	private Voiture voiture;
	private double diametre;
	private Vecteur2D position;

	/**
	 * Constructeur permettant de créer une colle
	 * 
	 * @param pos      La position en vecteur2D
	 * @param diametre Le diametre de la colle
	 */
	// Par Alexis Pineda-Alvarado
	public Colle(Vecteur2D pos, double diametre) {
		this.diametre = diametre;
		this.position = pos;

		creerLaGeometrie();

	}

	/**
	 * Méthode qui permet de dessiner sur le g2dcop
	 * 
	 * @param g2dcop Le composant graphique
	 */
	// Par Alexis Pineda-Alvarado
	@Override

	public void dessiner(Graphics2D g2dcop) {
		Graphics2D g2dcopcop = (Graphics2D) g2dcop.create();
		AffineTransform mat = new AffineTransform();
//		mat.scale(pixelsParMetre, pixelsParMetre);
		shapeColle = mat.createTransformedShape(colle);
		g2dcopcop.setColor(Color.ORANGE);

		g2dcop.setStroke(new BasicStroke(3f));
		g2dcop.drawLine((int) this.position.getX(), (int) this.position.getY(),
				(int) this.position.getX() + taillePiste, (int) this.position.getY());
		g2dcop.drawLine((int) this.position.getX(), (int) this.position.getY() + taillePiste - 1,
				(int) this.position.getX() + taillePiste, (int) this.position.getY() + taillePiste - 1);
		g2dcop.drawLine((int) this.position.getX(), (int) this.position.getY(), (int) this.position.getX(),
				(int) this.position.getY() + taillePiste);
		g2dcop.drawLine((int) this.position.getX() + taillePiste, (int) this.position.getY(),
				(int) this.position.getX() + taillePiste, (int) this.position.getY() + taillePiste);
		g2dcop.drawLine((int) this.position.getX(), (int) this.position.getY(),
				(int) this.position.getX() + taillePiste, (int) this.position.getY() + taillePiste);
		g2dcop.drawLine((int) this.position.getX() + (taillePiste / 2), (int) this.position.getY(),
				(int) this.position.getX() + taillePiste, (int) this.position.getY() + (taillePiste / 2));
		g2dcop.drawLine((int) this.position.getX(), (int) this.position.getY() + (taillePiste / 2),
				(int) this.position.getX() + (taillePiste / 2), (int) this.position.getY() + taillePiste);
		colleAire = new Area(shapeColle);
		colleAireCopie = new Area(colleAire);
	}

	public TypeObjetSpecial getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjetSpecial typeObjet) {
		this.typeObjet = typeObjet;
	}

	public Area getColleAire() {
		return colleAire;
	}

	public void setColleAire(Area colleAire) {
		this.colleAire = colleAire;
	}

	/**
	 * Méthode permetant de créer la géométrie de cet objet
	 */
	// Par Alexis Pineda-Alvarado
	private void creerLaGeometrie() {

		colle = new Ellipse2D.Double(position.getX(), position.getY(), taillePiste, taillePiste);
	}

	/**
	 * Méthode qui permet de changer le nombre de pixel par mètre par un nombre
	 * voulu
	 * 
	 * @param pixelsParMetreVoulu le nombre de pixel par metre voulu
	 */
	// Par Alexis Pineda-Alvarado
	public void setPixelsParMetre(double pixelsParMetreVoulu) {
		this.pixelsParMetre = pixelsParMetreVoulu;

	}

	/**
	 * 
	 * méthode qui détecte la collision de la voiture et la colle
	 * 
	 * @param v ceci est la valeur de la voiture
	 * @return la valeur de la collision en true or false
	 */
	// Alexis Pineda-Alvarado
	public boolean collisionDeLaVoiture(Voiture v) {
		this.voiture = v;
		colleAireCopie = new Area(colle);
		aireVoiture = new Area(voiture.getCercle());
		aireVoiture1 = new Area(aireVoiture);
		aireVoiture1.intersect(colleAireCopie);

		if (!aireVoiture1.isEmpty()) {
			contactColle = true;

		} else {

			contactColle = false;
		}

		return contactColle;
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

	public Vecteur2D getPosition() {
		return position;
	}

	public void setPosition(Vecteur2D position) {
		this.position = position;
	}

	public Ellipse2D.Double getColle() {
		return colle;
	}

	public void setColle(Ellipse2D.Double colle) {
		this.colle = colle;
	}

}
