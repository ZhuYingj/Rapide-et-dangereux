package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import physique.MoteurPhysique;

/**
 * Class qui permet de creer un objet piste virage droit
 * 
 * @author Ludovic Julien
 *
 */

public class PisteVirageBas implements Dessinable, Selectionnable, MouseListener, MouseMotionListener {

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 80;
	/** la position en x de depart que l'objet piste qui vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste qui vas etre creer **/
	private int y;
	private int murDroite;
	private int murGauche;
	private int murHaut;
	private int murBas;
	/** Normale du mur gauche **/
	private double angleNormaleMurGauche = 0;
	/** Normale du mur haut **/
	private double angleNormaleMurHaut = 90;

	/** Initialise la forme du triangle **/
	private Path2D triangle;
	/** Initialise l'aire du triangle **/
	private Area aireTriangle;

	public PisteVirageBas(int x, int y) {
		this.x = x;
		this.y = y;
		this.murDroite = x + TAILLE_PISTE + 1;
		this.murGauche = x + 1;
		this.murHaut = y + 1;
		this.murBas = y + TAILLE_PISTE + 1;

	}

	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		g2d.drawLine(x + ((TAILLE_PISTE / 3) * 2), y, x + TAILLE_PISTE - 1, y);
		g2d.drawLine(x + ((TAILLE_PISTE / 3)), y + ((TAILLE_PISTE / 3)), x + ((TAILLE_PISTE / 3) * 2), y);
		g2d.drawLine(x, y + ((TAILLE_PISTE / 3) * 2), x + (TAILLE_PISTE / 3), y + (TAILLE_PISTE / 3));
		g2d.drawLine(x, y + TAILLE_PISTE - 1, x, y + ((TAILLE_PISTE / 3) * 2));

		triangle = new Path2D.Double();
		triangle.moveTo(x, y);
		triangle.lineTo(x + ((TAILLE_PISTE / 3) * 2), y);
		triangle.lineTo(x, y + ((TAILLE_PISTE / 3) * 2));
		triangle.closePath();
		g2d.fill(triangle);

		aireTriangle = new Area(triangle);

	}

	/**
	 * Retourne l'aire du triangle
	 * 
	 * @return l'aire du triangle
	 */
	public Area getAireTriangle() {
		return aireTriangle;
	}

	public void enCollisionAvec(Voiture voiture) {

		Area cercle = new Area(voiture.getCercle());
		cercle.intersect(aireTriangle);
		double pos = 3;

		if (voiture.getPosition().getX() > murGauche && voiture.getPosition().getX() < murDroite
				&& voiture.getPosition().getY() > murHaut && voiture.getPosition().getY() < murBas) {
			if (voiture.getPosition().getX() < murGauche + 1) {
				try {
					Vecteur2D vit = MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(),
							angleNormaleMurGauche);
					voiture.setVitesse(vit);
					voiture.getPosition().setX(murGauche + 1);
					if (Math.toDegrees(voiture.getAngle()) < 270 && Math.toDegrees(voiture.getAngle()) > 180) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) + ((270 - Math.toDegrees(voiture.getAngle())) * 2)));
						System.out.println(Math.toDegrees(voiture.getAngle()));
					} else if (Math.toDegrees(voiture.getAngle()) > 90 && Math.toDegrees(voiture.getAngle()) < 180) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) - ((Math.toDegrees(voiture.getAngle()) - 90) * 2)));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (voiture.getPosition().getY() < murHaut + 1) {
				try {
					Vecteur2D vit = MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(),
							angleNormaleMurHaut);
					voiture.setVitesse(vit);
					voiture.getPosition().setY(murHaut + 1);
					if (Math.toDegrees(voiture.getAngle()) < 270 && Math.toDegrees(voiture.getAngle()) > 180) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) - ((Math.toDegrees(voiture.getAngle()) - 180) * 2)));
					} else if (Math.toDegrees(voiture.getAngle()) > 270 && Math.toDegrees(voiture.getAngle()) < 360) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) + ((360 - Math.toDegrees(voiture.getAngle()) * 2))));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (!cercle.isEmpty()) {

				try {

					Vecteur2D vit = MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), 45);

					voiture.setVitesse(vit);
					voiture.setPosition(
							new Vecteur2D(voiture.getPosition().getX() + pos, voiture.getPosition().getY() + pos));
					if (Math.toDegrees(voiture.getAngle()) <= 225 && Math.toDegrees(voiture.getAngle()) > 135) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) - ((Math.toDegrees(voiture.getAngle()) - 135) * 2)));
					} else if (Math.toDegrees(voiture.getAngle()) >= 225 && Math.toDegrees(voiture.getAngle()) < 315) {
						voiture.setAngle(Math.toRadians(Math.toDegrees(voiture.getAngle())
								+ (((315 - Math.toDegrees(voiture.getAngle())) * 2))));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	@Override
	public boolean contient(double xPix, double yPix) {
		// TODO Auto-generated method stub
		return false;
	}

	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();

		System.out.println("tperhesrh");
	}

	public void mouseDragged(MouseEvent e) {
//		e.getComponent().setLocation((e.getX() + e.getComponent().getX()) - x,
//				(e.getY() + e.getComponent().getY()) - y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("tperhesrh");
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}