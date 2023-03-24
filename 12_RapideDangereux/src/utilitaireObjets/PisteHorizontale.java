package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import physique.MoteurPhysique;

/**
 * Classe qui permet de creer un objet: Piste Horizontale
 * 
 * @author Ludovic Julien
 *
 */


public class PisteHorizontale implements Dessinable, Selectionnable {
	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 80;
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
	/** Normale du mur haut **/
	private double angleNormaleMurHaut = 90;
	/** Normale du mur bas **/
	private double angleNormaleMurBas = 270;
	private Color color  = Color.black;
	private boolean collision= false;


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
		this.murDroite = x +  TAILLE_PISTE + 1;
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
		g2dCopie.setColor(color);
		g2dCopie.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2dCopie.setColor(Color.RED);
		g2dCopie.setColor(Color.RED);
		Stroke stroke = new BasicStroke(0.5f);
		g2dCopie.setStroke(stroke);
		g2dCopie.drawLine(murGauche, murHaut, x + TAILLE_PISTE - 1, y);
		g2dCopie.drawLine(murGauche, murBas, x + TAILLE_PISTE - 1, y + TAILLE_PISTE);

	}


	public void enCollisionAvec(Voiture voiture) {

		if(voiture.getPosition().getX() > murGauche && voiture.getPosition().getX() < murDroite  && voiture.getPosition().getY() > murHaut && voiture.getPosition().getY() < murBas ) {
			if(voiture.getPosition().getY() < murHaut + 1) {
				try {
					Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), angleNormaleMurHaut);
					if(  voiture.getVitesse().module() < 0.3 ) {
						voiture.setVitesse(new Vecteur2D(0,0));
					} else {
						voiture.setVitesse(vit);
					}
					voiture.getPosition().setY(murHaut + 1);
					System.out.println("en collision");
					if(Math.toDegrees(voiture.getAngle()) < 270  && Math.toDegrees(voiture.getAngle()) > 180 ) {
						voiture.setAngle(Math.toRadians(Math.toDegrees(voiture.getAngle()) - ((Math.toDegrees(voiture.getAngle()) - 180) * 2)));
					} else if (Math.toDegrees(voiture.getAngle()) > 270  && Math.toDegrees(voiture.getAngle()) < 360 ) {
						voiture.setAngle(Math.toRadians(Math.toDegrees(voiture.getAngle()) + ((360 -(Math.toDegrees(voiture.getAngle())) * 2))));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}   else if(voiture.getPosition().getY()>  murBas - voiture.getDiametre()) {
				try {
					Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), angleNormaleMurBas);
					if(  voiture.getVitesse().module() < 0.3 ) {
						voiture.setVitesse(new Vecteur2D(0,0));
					} else {
						voiture.setVitesse(vit);
					}
					voiture.getPosition().setY(murBas- voiture.getDiametre());
					System.out.println("en collision");
					if(Math.toDegrees(voiture.getAngle()) < 90  && Math.toDegrees(voiture.getAngle()) > 0 ) {
						voiture.setAngle(Math.toRadians(Math.toDegrees(voiture.getAngle()) - ((Math.toDegrees(voiture.getAngle()) - 180) * 2)));
					} else if (Math.toDegrees(voiture.getAngle()) > 90  && Math.toDegrees(voiture.getAngle()) < 180 ) {
						voiture.setAngle(Math.toRadians(Math.toDegrees(voiture.getAngle()) + ((360 -(Math.toDegrees(voiture.getAngle())) * 2))));
						System.out.println(Math.toDegrees(voiture.getAngle()) + (360 -(Math.toDegrees(voiture.getAngle()) * 2)));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void traverserPiste(Voiture voiture) {
		if(voiture.getPosition().getX() > murGauche && voiture.getPosition().getX() < murDroite  && voiture.getPosition().getY() > murHaut && voiture.getPosition().getY() < murBas )  {
			setCollision(true);

			
		
		} 
		
	}

	public boolean isCollision() {
		return collision;
	}


	public void setCollision(boolean collision) {
		this.collision = collision;
	}


	@Override
	public boolean contient(double xPix, double yPix) {
		// TODO Auto-generated method stub
		return false;
	} 

	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}

}
