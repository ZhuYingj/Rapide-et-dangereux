package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;

import interfaces.Dessinable;


/**
 * Class qui permet de creer un objet piste virage haut
 * 
 * @author Ludovic Julien
 *
 */
	

public class PisteVirageHaut implements Dessinable {

	private static final int TAILLE_PISTE = 87;


	private int x;
	private int y;
	private int pente;

	private double pixelsParMetre = 1; //Defaut


	private Path2D triangle;


	private Area aireTriangle;
	
	/**
	 * Methode qui permet de construire la piste virage haut a l'aide de parametres
	 * 
	 * @param x 	position en x de la piste
	 * @param y		position en y de la piste
	 * */



	public PisteVirageHaut(int x, int y) {
		this.x = x;
		this.y = y;




	}

	
	/**
	 * Methode qui permet de dessiner la piste virage haut sur la zone d'animation a l'aide de g2d
	 */
	



	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		g2d.drawLine(x, y, x, y + (TAILLE_PISTE / 3));
		g2d.drawLine(x, y + (TAILLE_PISTE / 3), x + (TAILLE_PISTE / 3), y + ((TAILLE_PISTE / 3) * 2));
		g2d.drawLine(x + (TAILLE_PISTE / 3), y + ((TAILLE_PISTE / 3) * 2), x + ((TAILLE_PISTE / 3) * 2),
				y + TAILLE_PISTE);
		g2d.drawLine(x + ((TAILLE_PISTE / 3) * 2), y + TAILLE_PISTE, x + TAILLE_PISTE, y + TAILLE_PISTE);
		// g2d.fillRect(x+TAILLE_PISTE, y, -3-3);

		triangle = new Path2D.Double();
		triangle.moveTo(x , y+TAILLE_PISTE);
		triangle.lineTo(x , y +(TAILLE_PISTE/3));
		triangle.lineTo(x+ ((TAILLE_PISTE / 3) * 2),  y + TAILLE_PISTE);
		triangle.closePath();
		g2d.fill(triangle);
		
		
		aireTriangle = new Area(triangle);
		
	}
	
	public Area getAireTriangle() {
		return aireTriangle;
	}
	
	/**
	 * Méthode qui retourne le nombre de pixels par metre
	 * 
	 * @return nombre de pixel par metre
	 */
	public double getPixelsParMetre() {
		return pixelsParMetre;
	}

	/**
	 * Méthode qui permet de changer le nombre de pixel par mètre par un nombre
	 * voulu
	 * 
	 * @param pixelsParMetreVoulu
	 */
	public void setPixelsParMetre(double pixelsParMetre) {
		this.pixelsParMetre = pixelsParMetre;
		
	}
	


}
