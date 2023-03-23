package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;
import interfaces.Selectionnable;

public class Accelerateur implements Dessinable, Selectionnable {

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 87;
	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer **/
	private int y;
	private double pixelParMetre = 1;
	private Rectangle2D.Double a;

	public Accelerateur(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void dessiner(Graphics2D g2d) {
		Graphics2D gCopie = (Graphics2D) g2d.create();
		a = new Rectangle2D.Double(this.x, this.y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.GREEN);
		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		g2d.drawLine(x, y, x + TAILLE_PISTE, y);

		g2d.drawLine(x, y + TAILLE_PISTE - 1, x + TAILLE_PISTE, y + TAILLE_PISTE - 1);
		g2d.drawLine(x, y, x, y + TAILLE_PISTE);
		g2d.drawLine(x + TAILLE_PISTE, y, x + TAILLE_PISTE, y + TAILLE_PISTE);
		g2d.drawLine(x, y, x + TAILLE_PISTE, y + TAILLE_PISTE);
		g2d.drawLine(x + (TAILLE_PISTE / 2), y, x + TAILLE_PISTE, y + (TAILLE_PISTE / 2));
		g2d.drawLine(x, y + (TAILLE_PISTE / 2), x + (TAILLE_PISTE / 2), y + TAILLE_PISTE);
	

	}

	public double getPixelsParMetre() {
		return pixelParMetre;
	}

	public void setPixelsParMetre(double pixelParMetre) {
		this.pixelParMetre = pixelParMetre;

	}

	@Override
	public boolean contient(double xPix, double yPix) {
		if (a.contains(xPix, yPix)) {
			return true;
		} else {
			return false;
		}

	}

//	public void fonctionAccelarateur(Voiture voitureAffecte) {
//		// augmente la vitesse et l'acceleration
//		final double vitesseAccelerer = voitureAffecte.getVitesseMaxSelonNiveau() * 2;
//		voitureAffecte.setVitesse(vitesseAccelerer);
//
//	}

}
