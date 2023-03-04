package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

public class PisteVirageGauche implements Dessinable {

	private static final int TAILLE_PISTE = 87;
	
	private int x;
	private int y;

	/** Initialise la forme du triangle **/
	private Path2D triangle;
	/** Initialise l'aire du triangle **/
	private Area aireTriangle;
	
	public PisteVirageGauche(int x, int y) {
		this.x = x;
		this.y = y;
	
	}
	
	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		g2d.drawLine(x +1, y, x + (TAILLE_PISTE/3), y );
		g2d.drawLine(x + (TAILLE_PISTE/3), y, x + ((TAILLE_PISTE/3)*2), y + (TAILLE_PISTE/3));
		g2d.drawLine(x + ((TAILLE_PISTE/3)*2), y + (TAILLE_PISTE/3), x + TAILLE_PISTE, y + ((TAILLE_PISTE/3)*2) );
		g2d.drawLine(x + TAILLE_PISTE, y + ((TAILLE_PISTE/3)*2), x + TAILLE_PISTE, y + TAILLE_PISTE -1);
		
		triangle = new Path2D.Double();
		triangle.moveTo(x + (TAILLE_PISTE/3), y);
		triangle.lineTo(x + TAILLE_PISTE, y  + ((TAILLE_PISTE/3)*2));
		triangle.lineTo(x + TAILLE_PISTE,  y);
		triangle.closePath();
		g2d.fill(triangle);
		
		
		aireTriangle = new Area(triangle);
		
	}
	
	/**
	 * Retourne l'aire du triangle
	 * @return l'aire du triangle
	 */
	public Area getAireTriangle() {
		return aireTriangle;
	}

}
