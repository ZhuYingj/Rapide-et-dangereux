package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import physique.MoteurPhysique;

/**
 * Classe qui permet de creer un objet piste de d�part
 * 
 * @author Ludovic Julien
 *
 */

public class PisteDeDepart implements Dessinable{

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 87;
	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer  **/
	private int y;
	/** Pixels par metre par defaut  **/
	private double pixelsParMetre = 1; //Defaut
	/** Normale du mur haut **/
	private double angleNormaleMurHaut = 90;
	/** Normale du mur bas **/
	private double angleNormaleMurBas = 270;
	private int murDroite;
	private int murGauche;
	private int murHaut;
	private int murBas;
	
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
			this.murDroite = x +  TAILLE_PISTE;
			this.murGauche  =  x;
			this.murHaut    = y;
			this.murBas   = y + TAILLE_PISTE;
		
		}
		
	


		/**
		 * Methode qui permet de dessiner la piste de d�part sur la zone d'animation a l'aide de g2d
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
		
		/**
		 * Methode qui permet de retouner le postion en y du mure de haut
		 * 
		 * @return une position en Y
		 */
		public int getMurHaut() {
	        return murHaut;
	    }

		
		/**
		 * Methode qui permet de retourner la position en Y du mure de bas
		 * 
		 * @return une position en Y
		 */
	    public int getMurBas() {
	        return murBas;
	    }
		
		/**
		 * Retourne la normale du mur haut
		 * @return la normale du mur haut
		 */
		public double getAngleNormaleMurHaut() {
			return angleNormaleMurHaut;
		}

		/**
		 * Retourne la normale du mur bas
		 * @return la normale du mur bas
		 */
		public double getAngleNormaleMurBas() {
			return angleNormaleMurBas;
		}
		
		public void enCollisionAvec(Voiture voiture) {

			if(voiture.getPosition().getX() > murGauche && voiture.getPosition().getX() < murDroite  && voiture.getPosition().getY() > murHaut && voiture.getPosition().getY() < murBas ) {
				if(voiture.getPosition().getY() < murHaut + 1) {
					try {
						Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), angleNormaleMurHaut);
						voiture.setVitesse(vit);
						voiture.getPosition().setY(murHaut + 1);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}   else if(voiture.getPosition().getY()>  murBas - voiture.getDiametre()) {
					try {
						Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), angleNormaleMurBas);
						voiture.setVitesse(vit);
						voiture.getPosition().setY(murBas- voiture.getDiametre());

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} 
	
}
