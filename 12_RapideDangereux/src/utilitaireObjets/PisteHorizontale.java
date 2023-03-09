package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import physique.MoteurPhysique;

/**
 * Classe qui permet de creer un objet: Piste Horizontale
 * 
 * @author Ludovic Julien
 *
 */


public class PisteHorizontale implements Dessinable {
	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 87;
	/** La position en x du mure de haut **/
	private int murGauche;
	/** La position en y du mure de haut **/
	private int murHaut;
	/** La position en x de mure de bas **/
	private int murDroite;
	/** La position en y du mure de bas **/
	private int murBas;
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

	

	/**
	 * Methode qui permet de construire la piste horizontale a l'aide de parametres
	 * 
	 * @param x 	position en x de la piste
	 * @param y		position en y de la piste
	 * @param ligneRougeV1X		position en x du premier mure		
	 * @param ligneRougeV1Y		position en y du premier mure
	 * @param ligneRougeV2X		position en x du deuxieme mure
	 * @param ligneRougeV2Y		position en y du deuxieme mure
	 */
	public PisteHorizontale(int x, int y) {
		this.x = x;
		this.y = y;
		this.murDroite = x +  TAILLE_PISTE;
		this.murGauche  =  x ;
		this.murHaut    = y;
		this.murBas   = y + TAILLE_PISTE;

	}

	/**
	 * Methode qui permet de dessiner la piste horizontale sur la zone d'animation a l'aide de g2d
	 */

	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dCopie = (Graphics2D) g2d.create();
		g2dCopie.scale(pixelsParMetre, pixelsParMetre);
		g2dCopie.setColor(Color.BLACK);
		g2dCopie.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2dCopie.setColor(Color.RED);
		g2dCopie.setColor(Color.RED);
		Stroke stroke = new BasicStroke(3f);
		g2dCopie.setStroke(stroke);
		g2dCopie.drawLine(murGauche, murHaut, x + TAILLE_PISTE - 1, y);
		g2dCopie.drawLine(murGauche, murBas, x + TAILLE_PISTE - 1, y + TAILLE_PISTE);

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
	public int getmurHaut() {
		return murHaut;
	}


	/**
	 * Methode qui permet de retourner la position en Y du mure de bas
	 * 
	 * @return une position en Y
	 */
	public int getmurBas() {
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
