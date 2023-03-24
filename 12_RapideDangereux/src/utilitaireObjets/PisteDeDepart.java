package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import physique.MoteurPhysique;

/**
 * Classe qui permet de creer un objet piste de d�part
 * 
 * @author Ludovic Julien
 *
 */

public class PisteDeDepart implements Dessinable, Selectionnable {

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 80;

	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer **/
	private int y;
	/** Normale du mur haut **/
	private double angleNormaleMurHaut = 90;
	/** Normale du mur bas **/
	private double angleNormaleMurBas = 270;
	private int murDroite;
	private int murGauche;
	private int murHaut;
	private int murBas;
	private boolean collision = false;
	private Color color = Color.black;

	private Voiture voiture;

	/**
	 * Methode qui permet de construire la piste verticale a l'aide de parametre
	 * 
	 * @param x             position en x de la piste
	 * @param y             position en y de la piste
	 * @param ligneRougeV1X position en x du premier mure
	 * @param ligneRougeV1Y position en y du premier mure
	 * @param ligneRougeV2X position en x du deuxieme mure
	 * @param ligneRougeV2Y position en y du deuxieme mure
	 */
	public PisteDeDepart(int x, int y) {
		this.x = x;
		this.y = y;
		this.murDroite = x + TAILLE_PISTE;
		this.murGauche = x;
		this.murHaut = y;
		this.murBas = y + TAILLE_PISTE;
		voiture = new Voiture(new Vecteur2D(x + TAILLE_PISTE/4, y  + TAILLE_PISTE/4), Color.yellow, 50, 16, 0, 50);
	}

	/**
	 * Methode qui permet de dessiner la piste de d�part sur la zone d'animation a
	 * l'aide de g2d
	 */
	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		g2d.setColor(Color.RED);
		Stroke stroke1 = new BasicStroke(0.5f);
		g2d.setStroke(stroke1);
		g2d.drawLine(x + 1, y, x + TAILLE_PISTE - 1, y);
		g2d.drawLine(x + 1, y + TAILLE_PISTE, x + TAILLE_PISTE - 1, y + TAILLE_PISTE);

		g2d.setColor(Color.WHITE);
		Stroke stroke0 = new BasicStroke(5f);
		g2d.setStroke(stroke0);
		g2d.drawLine(x + (TAILLE_PISTE / 2), y + (TAILLE_PISTE / 7), x + (TAILLE_PISTE / 2),
				y + ((TAILLE_PISTE / 7) * 2));
		g2d.drawLine(x + (TAILLE_PISTE / 2), y + ((TAILLE_PISTE / 7) * 3), x + (TAILLE_PISTE / 2),
				y + ((TAILLE_PISTE / 7) * 4));
		g2d.drawLine(x + (TAILLE_PISTE / 2), y + ((TAILLE_PISTE / 7) * 5), x + (TAILLE_PISTE / 2),
				y + ((TAILLE_PISTE / 7) * 6));

//		Ellipse2D a = new Ellipse2D.Double(x, y, 50, 50);
//		g2d.draw(a);

		voiture.dessiner(g2d);

	}

	public void enCollisionAvec(Voiture voiture) {

		if (voiture.getPosition().getX() > murGauche && voiture.getPosition().getX() < murDroite
				&& voiture.getPosition().getY() > murHaut && voiture.getPosition().getY() < murBas) {
			if (voiture.getPosition().getY() < murHaut + 1) {
				try {
					Vecteur2D vit = MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(),
							angleNormaleMurHaut);
					if (voiture.getVitesse().module() < 0.3) {
						voiture.setVitesse(new Vecteur2D(0, 0));
					} else {
						voiture.setVitesse(vit);
					}
					voiture.getPosition().setY(murHaut + 1);
					System.out.println("en collisionD");
					if (Math.toDegrees(voiture.getAngle()) < 270 && Math.toDegrees(voiture.getAngle()) > 180) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) - ((Math.toDegrees(voiture.getAngle()) - 180) * 2)));
					} else if (Math.toDegrees(voiture.getAngle()) > 270 && Math.toDegrees(voiture.getAngle()) < 360) {
						voiture.setAngle(Math.toRadians(Math.toDegrees(voiture.getAngle())
								+ ((360 - (Math.toDegrees(voiture.getAngle())) * 2))));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (voiture.getPosition().getY() > murBas - voiture.getDiametre()) {
				try {
					Vecteur2D vit = MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(),
							angleNormaleMurBas);
					if (voiture.getVitesse().module() < 0.3) {
						voiture.setVitesse(new Vecteur2D(0, 0));
					} else {
						voiture.setVitesse(vit);
					}
					voiture.getPosition().setY(murBas - voiture.getDiametre());
					System.out.println("en collisionD");
					if (Math.toDegrees(voiture.getAngle()) < 90 && Math.toDegrees(voiture.getAngle()) > 0) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) - ((Math.toDegrees(voiture.getAngle()) - 180) * 2)));
					} else if (Math.toDegrees(voiture.getAngle()) > 90 && Math.toDegrees(voiture.getAngle()) < 180) {
						voiture.setAngle(Math.toRadians(Math.toDegrees(voiture.getAngle())
								+ ((360 - (Math.toDegrees(voiture.getAngle())) * 2))));
						System.out.println(
								Math.toDegrees(voiture.getAngle()) + (360 - (Math.toDegrees(voiture.getAngle()) * 2)));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void traverserPiste(Voiture voiture) {
		if (voiture.getPosition().getX() > murGauche && voiture.getPosition().getX() < murDroite
				&& voiture.getPosition().getY() > murHaut && voiture.getPosition().getY() < murBas) {
			setCollision(true);

		}

	}

	public boolean resetTout(Voiture voiture) {
		if (voiture.getPosition().getX()  > murGauche + voiture.getDiametre() && voiture.getPosition().getX() < murDroite
				&& voiture.getPosition().getY() > murHaut && voiture.getPosition().getY() < murBas) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean contient(double xPix, double yPix) {

		return true;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}
