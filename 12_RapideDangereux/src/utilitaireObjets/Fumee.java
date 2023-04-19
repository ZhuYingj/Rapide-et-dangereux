package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;

/**
 * 
 * classe permettant de creer une vue voilée
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class Fumee implements Dessinable, Selectionnable, Serializable {

	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 80;
	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer **/
	private int y;
	/** Nombre de pixel par metre **/
	private double pixelParMetre;
	/** Creer la forme du carre **/
	private Rectangle2D.Double formeAire;
	/** Prend la boule creer de la classe BouleFumee **/
	private transient BouleFumee bouleFumee;

	/**
	 * Methode qui permet de construire l'objet Fumee a l'aide de parametres
	 * 
	 * @param x position en x de la piste
	 * @param y position en y de la piste
	 */
	// Alexis Pineda-Alvarado
	public Fumee(int x, int y) {
		this.x = x;
		this.y = y;

		bouleFumee = new BouleFumee(new Vecteur2D(x + taillePiste / 4, y + taillePiste / 4), 16, 1);
		formeAire = new Rectangle2D.Double(this.x, this.y, taillePiste, taillePiste);

	}

	/**
	 * Methode qui permet de dessiner la fumee sur la zone d'animation a l'aide de
	 * g2d
	 */
	// Alexis Pineda-Alvarado
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.gray);

		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		g2d.drawLine(x, y, x + taillePiste, y);

		g2d.drawLine(x, y + taillePiste - 1, x + taillePiste, y + taillePiste - 1);
		g2d.drawLine(x, y, x, y + taillePiste);
		g2d.drawLine(x + taillePiste, y, x + taillePiste, y + taillePiste);

		bouleFumee.dessiner(g2d);

	}

	/**
	 * Méthode qui permet de détecter si la fumee est contenue au clic de la souris
	 **/
	// Alexis Pineda-Alvarado
	public boolean contient(double xPix, double yPix) {
		if (formeAire.contains(xPix, yPix)) {

			return true;
		} else {
			return false;
		}
	}

	public double getPixelsParMetre() {
		return pixelParMetre;
	}

	public void setPixelsParMetre(double pixelParMetre) {
		this.pixelParMetre = pixelParMetre;

	}

//	public BouleFumee getBouleFumee() {
//		return bouleFumee;
//	}
//
//	public void setBouleFumee(BouleFumee bouleFumee) {
//		this.bouleFumee = bouleFumee;
//	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;

	}

	public int getY() {
		return y;
	}

	public int getTaillePiste() {
		return taillePiste;
	}

	public void setTaillePiste(int taillePiste) {
		this.taillePiste = taillePiste;
	}

	public Rectangle2D.Double getFormeAire() {
		return formeAire;
	}

	public void setFormeAire(Rectangle2D.Double formeAire) {
		this.formeAire = formeAire;
	}
}
