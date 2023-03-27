package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import dessin.ZoneAnimPhysique;
import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import interfaces.TypeObjetSpecial;
import physique.MoteurPhysique;

/**
 * Classe qui crée et gère tous ce qui de la boule de neige
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class BouleDeNeige extends ObjetSpecial {
	private double diametre;
	private Ellipse2D.Double boule;
	private double pixelsParMetre;
	private Vecteur2D vitesse = new Vecteur2D(100, 0); // par defaut
	private Vecteur2D accel = new Vecteur2D(0, 0); // par defaut
	private Voiture voiture;
	Shape shapeBoule;
	private Area bouleDeNeigeAire;
	private Area bouleDeNeigeAireCopie;
	private Area aireVoiture;
	private Area aireVoiture1;
	private TypeObjetSpecial typeObjet = TypeObjetSpecial.BOULEDENEIGE;
	private boolean contactBouleNeige = true;
	private Vecteur2D position;

	/**
	 * Méthode qui crée la boule de neige
	 * 
	 * @param pos       le positionnement de la boule de neige
	 * @param diametre  le diametre de la boule de neige
	 * @param typeObjet le type d'objet spéciale
	 */
	// Alexis Pineda-Alvarado
	public BouleDeNeige(Vecteur2D pos, double diametre, TypeObjetSpecial typeObjet) {
		super(pos, diametre, typeObjet);
		this.diametre = super.getDiametreObjet();
		this.position = super.getPositionObjet();

		creerLaGeometrie();
	}

	/**
	 * Méthode qui dessine la boule de neige
	 */
	// Alexis Pineda-Alvarado
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dcop = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(pixelsParMetre, pixelsParMetre);
		shapeBoule = mat.createTransformedShape(boule);
		g2dcop.setColor(Color.cyan);
		g2dcop.fill(shapeBoule);


		bouleDeNeigeAire = new Area(shapeBoule);
		bouleDeNeigeAireCopie = new Area(bouleDeNeigeAire);

	}

	private void creerLaGeometrie() {

		boule = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre);
	}

	/**
	 * 
	 * méthode qui détecte la collision de la voiture et la boule de neige
	 * 
	 * @param v ceci est la valeur de la voiture
	 * @return la valeur de la collision en true or false
	 */
	// Alexis Pineda-Alvarado
	public boolean collisionDeLaBalle(Voiture v) {
		this.voiture = v;

		aireVoiture = new Area(voiture.getCercle());
		aireVoiture1 = new Area(aireVoiture);
		aireVoiture1.intersect(bouleDeNeigeAireCopie);

		if (contactBouleNeige)
			if (!aireVoiture1.isEmpty()) {
				contactBouleNeige = false;
			}

		return contactBouleNeige;
	}

	/**
	 * Méthode qui gére le déplacement de la boule de neige
	 * 
	 */
	public void deplacementBoule() {

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

	public double getDiametre() {
		return diametre;
	}

	public void setDiametre(double diametre) {
		this.diametre = diametre;
	}

	public Vecteur2D getVitesse() {
		return vitesse;
	}

	public void setVitesse(Vecteur2D vitesse) {
		this.vitesse = vitesse;
	}

	public Vecteur2D getAccel() {
		return accel;
	}

	public void setAccel(Vecteur2D accel) {
		this.accel = accel;
	}

	public Shape getShapeBoule() {
		return shapeBoule;
	}

	public void setShapeBoule(Shape shapeBoule) {
		this.shapeBoule = shapeBoule;
	}

	public TypeObjetSpecial getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjetSpecial typeObjet) {
		this.typeObjet = typeObjet;
	}

}
