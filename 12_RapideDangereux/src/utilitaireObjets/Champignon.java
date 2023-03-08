package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;

public class Champignon implements Dessinable, Selectionnable {

	/** Vecteur de la position de la voiture **/
	private Vecteur2D position;
	private double pixelParMetre = 1;
	private double diametre = 1;
	private Ellipse2D cercle;
	Shape shapeCercle;

	public Champignon(Vecteur2D pos, double diametre) {
		this.position = pos;
		this.diametre = diametre;
		creerLaGeometrie();
	}

	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D gCopie = (Graphics2D) g2d.create();
		gCopie.setColor(Color.green);
		gCopie.scale(pixelParMetre, pixelParMetre);
		gCopie.fill(cercle);
	}

	public double getPixelsParMetre() {
		return pixelParMetre;
	}

	public void setPixelsParMetre(double pixelParMetre) {
		this.pixelParMetre = pixelParMetre;

	}

	public double getDiametre() {
		return diametre;
	}

	public void setDiametre(double diametre) {
		this.diametre = diametre;
		creerLaGeometrie();
	}

	private void creerLaGeometrie() {
		AffineTransform mat = new AffineTransform();
		cercle = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre);
		shapeCercle = mat.createTransformedShape(cercle);
	}

	public void fonctionChampignon(Voiture voitureAffecte) {
		// Masse augmente selon le temps
		final double masseProgressive = voitureAffecte.getMasseEnKg() * 0.05;
		voitureAffecte.setMasseEnKg(masseProgressive);

	}

	@Override
	public boolean contient(double xPix, double yPix) {

		return false;
	}

	public Vecteur2D getPosition() {
		return position;
	}

	public void setPosition(Vecteur2D position) {
		this.position = position;

	}

	public void gererCollision() {

	}

}
