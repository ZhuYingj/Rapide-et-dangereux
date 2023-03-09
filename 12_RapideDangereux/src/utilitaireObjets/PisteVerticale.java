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
 * Classe qui permet de creer un objet Piste Verticale
 * 
 * @author Ludovic Julien
 *
 */

public class PisteVerticale implements Dessinable {

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 87;
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
	/** Normale du mur droite **/
	private double angleNormaleMurDroite = 180;
	/** Normale du mur gauche **/
	private double angleNormaleMurGauche = 0;
	
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
	    this.ligneRougeV2Y = y + TAILLE_PISTE ;

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
	 /**
     * Retourne la normale du mur droite
     * @return la normale du mur droite
     */
	public double getAngleNormaleMurDroite() {
		return angleNormaleMurDroite;
	}

	/**
     * Retourne la normale du mur gauche
     * @return la normale du mur gauche
     */
	public double getAngleNormaleMurGauche() {
		return angleNormaleMurGauche;
	}
	
	public void enCollisionAvec(Voiture voiture) {

		if(voiture.getPosition().getX() > ligneRougeV1X && voiture.getPosition().getX() < ligneRougeV2X  && voiture.getPosition().getY() > ligneRougeV1Y && voiture.getPosition().getY() < ligneRougeV2Y  ) {
			if(voiture.getPosition().getX() < ligneRougeV1X + 1) {
				try {
					Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), angleNormaleMurDroite);
					voiture.setVitesse(vit);
					voiture.getPosition().setX(ligneRougeV1X + 1);
					System.out.println("wow");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}   else if(voiture.getPosition().getX() > ligneRougeV2X - voiture.getDiametre()) {
				try {
					Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), angleNormaleMurGauche);
					voiture.setVitesse(vit);
					voiture.getPosition().setX(ligneRougeV2X- voiture.getDiametre());
					System.out.println("wow");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
}
