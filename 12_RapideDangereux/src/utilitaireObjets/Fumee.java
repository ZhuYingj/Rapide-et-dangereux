package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

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

	private int taillePiste = 80;

	private int x;

	private int y;

	private double pixelParMetre;
	private Rectangle2D.Double formeAire;

	public Fumee(int x, int y) {
		this.x = x;
		this.y = y;
		formeAire = new Rectangle2D.Double(this.x, this.y, taillePiste, taillePiste);
	}

	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.gray);

		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		g2d.drawLine(x, y, x + taillePiste, y);
		
		g2d.drawLine(x, y + taillePiste - 1, x + taillePiste, y + taillePiste - 1);
		g2d.drawLine(x, y, x, y + taillePiste);
		g2d.drawLine(x + taillePiste, y, x + taillePiste, y + taillePiste);

	}

	public boolean contient(double xPix, double yPix) {

		return false;
	}

	public double getPixelsParMetre() {
		return pixelParMetre;
	}

	public void setPixelsParMetre(double pixelParMetre) {
		this.pixelParMetre = pixelParMetre;

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
