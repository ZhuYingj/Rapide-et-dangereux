package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;

public class BouleDeNeige implements Dessinable, Selectionnable {
	private Color col;
	private double x;
	private double y;
	private double diametre;
	private Vecteur2D posInt;
	private Ellipse2D.Double boule;
	private double pixelsParMetre;
	private Vecteur2D vitesse = new Vecteur2D(100, 0); // par defaut
	private Vecteur2D accel = new Vecteur2D(0, 0); // par defaut
	Shape shapeBoule;

	public BouleDeNeige(Vecteur2D vec, double diametre) {

		this.posInt = vec;
		this.diametre = diametre;
		this.pixelsParMetre = 1;

		creerLaGeometrie();

	}

	public void creerLaGeometrie() {
		boule = new Ellipse2D.Double(posInt.getX(), posInt.getY(), this.diametre, this.diametre);
	}

	@Override
	public boolean contient(double xPix, double yPix) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dcop = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(pixelsParMetre, pixelsParMetre);
		shapeBoule = mat.createTransformedShape(boule);
		g2dcop.setColor(Color.cyan);
		g2dcop.fill(shapeBoule);

	}

	public void deplacementBoule() {

	}
	
	public void ralentissementVoiture(Voiture valeurVoiture) {
//		Vecteur2D accelerationDiminue = valeurVoiture.setSommeDesForces(valeurVoiture.getAccel());
//		valeurVoiture.setAccel(accelerationDiminue);
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

}
