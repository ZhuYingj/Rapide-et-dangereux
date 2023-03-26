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

public class BouleDeNeige extends ObjetSpecial {
	private double diametre;
	private Ellipse2D.Double boule;
	private double pixelsParMetre;
	private Vecteur2D vitesse = new Vecteur2D(100, 0); // par defaut
	private Vecteur2D accel = new Vecteur2D(0, 0); // par defaut
	private boolean good = true;
	private Voiture voiture;
	Shape shapeBoule;
	private Area bouleDeNeigeAire;
	private Area bouleDeNeigeAireCopie;
	private Area aireVoiture;
	private Area aireVoiture1;
	private TypeObjetSpecial typeObjet = TypeObjetSpecial.BOULEDENEIGE;
	private boolean contactBouleNeige = true;
	private Vecteur2D position;

	public BouleDeNeige(Vecteur2D pos, double diametre, TypeObjetSpecial typeObjet) {
		super(pos, diametre, typeObjet);
		this.position = super.getPositionObjet();
	}

	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dcop = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(pixelsParMetre, pixelsParMetre);
		shapeBoule = mat.createTransformedShape(boule);
		g2dcop.setColor(Color.cyan);
		if (contactBouleNeige == true) {
			g2dcop.fill(shapeBoule);
		}

		bouleDeNeigeAire = new Area(shapeBoule);
		bouleDeNeigeAireCopie = new Area(bouleDeNeigeAire);

	}

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

	public void deplacementBoule() {

	}
	

//	public void ralentissementVoiture(Voiture v) {
//		System.out.println("SLOW DOWN!!!");
//		Vecteur2D voitureSlow = new Vecteur2D();
//		voitureSlow = MoteurPhysique.calculerForceFrottement(5.00, voiture.getMasseEnKg(), voiture.getAngle());
//		v.setSommeDesForces(voitureSlow);
//		System.out.println(voitureSlow);
//
//	}

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

	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	public TypeObjetSpecial getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjetSpecial typeObjet) {
		this.typeObjet = typeObjet;
	}

}
