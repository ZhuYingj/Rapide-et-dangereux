package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

/**
 * Classe qui permet de creer un objet Piste Verticale
 * 
 * @author Ludovic Julien
 *
 */

public class PisteVerticale implements Dessinable {

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 80;
	/** La position en x du mure de haut **/
	private int ligneRougeV1X;
	/** La position en y du mure de haut **/
	private int ligneRougeV1Y;
	/** La position en x de mure de bas **/
	private int ligneRougeV2X;
	/** La position en y du mure de bas **/
	private int ligneRougeV2Y;
	/** la position en x de depart que l'objet piste qui vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste qui vas etre creer  **/
	private int y;
	/** Pixels par metre par defaut  **/
	private double pixelsParMetre = 1; //Defaut
	
	/**
	 * Methode qui permet de construire la piste verticale a l'aide de parametre
	 * 
	 * @param x		position en x de la piste
	 * @param y		position en y de la piste
	 * @param ligneRougeV1X		position en x du premier mure		
	 * @param ligneRougeV1Y		position en y du premier mure
	 * @param ligneRougeV2X		position en x du deuxieme mure
	 * @param ligneRougeV2Y		position en y du deuxieme mure
	 */
	public PisteVerticale(int x, int y) {
		this.x = x;
		this.y = y;
		this.ligneRougeV1X = x;
	    this.ligneRougeV1Y = y+1;
	    this.ligneRougeV2X = x + TAILLE_PISTE;
	    this.ligneRougeV2Y = y+1;

	}

	/**
	 * Methode qui permet de dessiner la piste verticale sur la zone d'animation a l'aide de g2d
	 */
	@Override
	public void dessiner(Graphics2D g2d) {
	g2d.setColor(Color.BLACK);
	g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
	g2d.setColor(Color.RED);
	Stroke stroke = new BasicStroke(3f);
	g2d.setStroke(stroke);
	g2d.drawLine(x, y +1, x, y + TAILLE_PISTE -1);
	g2d.drawLine(x + TAILLE_PISTE, y+1, x + TAILLE_PISTE, y + TAILLE_PISTE-1);

	}
	
	/**
	 * Methode qui permet de retouner le postion en y du premier mure
	 * 
	 * @return une position en Y
	 */
	public int getLigneRougeV1X() {
        return ligneRougeV1Y;
    }

	/**
	 * Methode qui permet de retouner le postion en y du deuxieme mure
	 * 
	 * @return une position en Y
	 */
    public int getLigneRougeV2X() {
        return ligneRougeV2Y;
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
	
	/**
	 * Méthode qui retourne le nombre de pixels par metre
	 * 
	 * @return nombre de pixel par metre
	 */
	public double getPixelsParMetre() {
		return pixelsParMetre;
	}
}
