package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

/**
 * Classe qui permet de creer un objet piste de départ
 * 
 * @author Ludovic Julien
 *
 */

public class PisteDeDepart implements Dessinable{

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 80;
	/** La position en x du mure de haut **/
	private int ligneRougeD1X;
	/** La position en y du mure de haut **/
	private int ligneRougeD1Y;
	/** La position en x de mure de bas **/
	private int ligneRougeD2X;
	/** La position en y du mure de bas **/
	private int ligneRougeD2Y;
	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer  **/
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
		public PisteDeDepart(int x, int y) {
			this.x = x;
			this.y = y;
			this.ligneRougeD1X = x + 1;
		    this.ligneRougeD1Y = y;
		    this.ligneRougeD2X = x + 1;
		    this.ligneRougeD2Y = y + TAILLE_PISTE;
		
		}
		
		/**
		 * Methode qui permet de dessiner la piste de départ sur la zone d'animation a l'aide de g2d
		 */
		@Override
		public void dessiner(Graphics2D g2d) {
			g2d.setColor(Color.BLACK);
			g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
			g2d.setColor(Color.RED);
			g2d.setColor(Color.RED);
			Stroke stroke1 = new BasicStroke(3f);
			g2d.setStroke(stroke1);
			g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
			g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
			
			g2d.setColor(Color.WHITE);
			Stroke stroke0= new BasicStroke(5f);
			g2d.setStroke(stroke0);
			g2d.drawLine(x+(TAILLE_PISTE/2),  y+(TAILLE_PISTE/7), x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*2));
			g2d.drawLine(x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*3), x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*4));
			g2d.drawLine(x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*5), x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*6) );
	
		}
		
		/**
		 * MÃ©thode qui retourne le nombre de pixels par metre
		 * 
		 * @return nombre de pixel par metre
		 */
		public double getPixelsParMetre() {
			return pixelsParMetre;
		}

		/**
		 * MÃ©thode qui permet de changer le nombre de pixel par mÃ¨tre par un nombre
		 * voulu
		 * 
		 * @param pixelsParMetreVoulu
		 */
		public void setPixelsParMetre(double pixelsParMetre) {
			this.pixelsParMetre = pixelsParMetre;
			
		}
		
		/**
		 * Methode qui permet de retouner le postion en y du mure de haut
		 * 
		 * @return une position en Y
		 */
		public int getLigneRodugeD1Y() {
	        return ligneRougeD1Y;
	    }

		
		/**
		 * Methode qui permet de retourner la position en Y du mure de bas
		 * 
		 * @return une position en Y
		 */
	    public int getLigneRougeD2Y() {
	        return ligneRougeD2Y;
	    }
		
	
}
