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
import interfaces.TypeObjetSpecial;

/**
 * Classe permettant de gérer et créer un accélérateur
 * 
 * @author Ludovic Julien
 *
 */
public class Accelerateur implements Dessinable, Selectionnable, Serializable {

	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 80;

	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;

	/** la position en y de depart que l'objet piste vas etre creer **/
	private int y;

	/** Nombre de pixel par metre **/
	private double pixelParMetre;
	private Rectangle2D.Double formeAire;

	/**
	 * Methode qui permet de construire l'objet Accelerateur a l'aide de parametres
	 * 
	 * @param x position en x de la piste
	 * @param y position en y de la piste
	 */
	// Ludovic Julien
	public Accelerateur(int x, int y) {
		this.x = x;
		this.y = y;
		formeAire = new Rectangle2D.Double(this.x, this.y, taillePiste, taillePiste);
	}

	/**
	 * Methode qui permet de dessiner l'accelerateur sur la zone d'animation a
	 * l'aide de g2d
	 */
	// Ludovic Julien
	public void dessiner(Graphics2D g2d) {

		g2d.setColor(Color.GREEN);

		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		g2d.drawLine(x, y, x + taillePiste, y);

		g2d.drawLine(x, y + taillePiste - 1, x + taillePiste, y + taillePiste - 1);
		g2d.drawLine(x, y, x, y + taillePiste);
		g2d.drawLine(x + taillePiste, y, x + taillePiste, y + taillePiste);
		g2d.drawLine(x, y, x + taillePiste, y + taillePiste);
		g2d.drawLine(x + (taillePiste / 2), y, x + taillePiste, y + (taillePiste / 2));
		g2d.drawLine(x, y + (taillePiste / 2), x + (taillePiste / 2), y + taillePiste);

	}

	public double getPixelsParMetre() {
		return pixelParMetre;
	}

	public void setPixelsParMetre(double pixelParMetre) {
		this.pixelParMetre = pixelParMetre;

	}

	/**
	 * Méthode qui permet de détecter si l'accelerateur est ocntenue au clic de la
	 * souris
	 **/
	// Ludovic Julien

	@Override
	public boolean contient(double xPix, double yPix) {
		if (formeAire.contains(xPix, yPix)) {

			return true;
		} else {
			return false;
		}

	}

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
