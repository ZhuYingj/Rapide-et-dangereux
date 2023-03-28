package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import physique.MoteurPhysique;

/**
 * Classe qui permet de creer un objet: Piste Horizontale
 * 
 * @author Ludovic Julien
 * @author Kevin Nguyen
 */

public class PisteHorizontale implements Dessinable, Selectionnable, Serializable {

	private static final long serialVersionUID = 1L;

	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 80;

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
	/** la position en y de depart que l'objet piste vas etre creer **/
	private int y;
	/** Normale du mur haut **/
	private double angleNormaleMurHaut = 90;
	/** Normale du mur bas **/
	private double angleNormaleMurBas = 270;
	private Color color = Color.black;
	private boolean collision = false;
	private Rectangle2D.Double formeAire;

	/**
	 * Methode qui permet de construire la piste horizontale a l'aide de parametres
	 * 
	 * @param x             position en x de la piste
	 * @param y             position en y de la piste
	 * @param ligneRougeV1X position en x du premier mure
	 * @param ligneRougeV1Y position en y du premier mure
	 * @param ligneRougeV2X position en x du deuxieme mure
	 * @param ligneRougeV2Y position en y du deuxieme mure
	 */
	public PisteHorizontale(int x, int y) {
		this.x = x;
		this.y = y;
		this.murDroite = x + taillePiste + 1;
		this.murGauche = x;
		this.murHaut = y;
		this.murBas = y + taillePiste;
		formeAire = new Rectangle2D.Double(this.x, this.y, taillePiste, taillePiste);
	}

	/**
	 * Methode qui permet de dessiner la piste horizontale sur la zone d'animation a
	 * l'aide de g2d
	 */

	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dCopie = (Graphics2D) g2d.create();
		g2dCopie.setColor(color);
		g2dCopie.fillRect(x, y, taillePiste, taillePiste);
		g2dCopie.setColor(Color.RED);
		g2dCopie.setColor(Color.RED);
		Stroke stroke = new BasicStroke(0.5f);
		g2dCopie.setStroke(stroke);
		g2dCopie.drawLine(x, y, x + taillePiste, y);
		g2dCopie.drawLine(x, y + taillePiste, x + taillePiste, y + taillePiste);

	}

	/**
	 * Méthode permettant de calculer la collision avec les murs du morceau de piste
	 * ainsi que de calculer l'angle de réflexion
	 * 
	 * @param voiture La voiture controllée
	 */
	// Kevin Nguyen
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

	/**
	 * Méthode permettant de savoir si la voiture est passée sur la piste
	 * 
	 * @param voiture La voiture controllée
	 */
	// Kevin Nguyen
	public void traverserPiste(Voiture voiture) {
		if (voiture.getPosition().getX() > murGauche && voiture.getPosition().getX() < murDroite
				&& voiture.getPosition().getY() > murHaut && voiture.getPosition().getY() < murBas) {
			setCollision(true);

		}

	}

	public int getTaillePiste() {
		return taillePiste;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean contient(double xPix, double yPix) {
		if (formeAire.contains(xPix, yPix)) {
			return true;
		} else {
			return false;
		}

	}

	public Rectangle2D.Double getFormeAire() {
		return formeAire;
	}

	public void setFormeAire(Rectangle2D.Double formeAire) {
		this.formeAire = formeAire;
	}

}
