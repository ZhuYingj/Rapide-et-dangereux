package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Path2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import physique.MoteurPhysique;

public class PisteVirageGauche implements Dessinable, Selectionnable {

	private static final int TAILLE_PISTE = 80;

	private int x;
	private int y;
	private int murDroite;
	private int murGauche;
	private int murHaut;
	private int murBas;
	/** Normale du mur droite **/
	private double angleNormaleMurDroite = 180;
	/** Normale du mur haut **/
	private double angleNormaleMurHaut = 90;
	/** Pixels par metre par defaut **/
	private double pixelsParMetre = 1; // Defaut
	/** Initialise la forme du triangle **/
	private Path2D triangle;
	/** Initialise l'aire du triangle **/
	private Area aireTriangle;

	public PisteVirageGauche(int x, int y) {
		this.x = x;
		this.y = y;
		this.murDroite = x + TAILLE_PISTE + 1;
		this.murGauche = x + 1;
		this.murHaut = y + 1;
		this.murBas = y + TAILLE_PISTE + 1;

	}

	/**
	 * Methode qui permet de construire la piste horizontale a l'aide de parametres
	 * 
	 * @param x position en x de la piste
	 * @param y position en y de la piste
	 */
	@Override
	public void dessiner(Graphics2D g2d) {
//		g2d.scale(pixelsParMetre, pixelsParMetre);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke = new BasicStroke(0.5f);
		g2d.setStroke(stroke);
		g2d.drawLine(x + 1, y, x + (TAILLE_PISTE / 3), y);
		g2d.drawLine(x + (TAILLE_PISTE / 3), y, x + ((TAILLE_PISTE / 3) * 2), y + (TAILLE_PISTE / 3));
		g2d.drawLine(x + ((TAILLE_PISTE / 3) * 2), y + (TAILLE_PISTE / 3), x + TAILLE_PISTE,
				y + ((TAILLE_PISTE / 3) * 2));
		g2d.drawLine(x + TAILLE_PISTE, y + ((TAILLE_PISTE / 3) * 2), x + TAILLE_PISTE, y + TAILLE_PISTE - 1);

		triangle = new Path2D.Double();
		triangle.moveTo(x + (TAILLE_PISTE / 3), y);
		triangle.lineTo(x + TAILLE_PISTE, y + ((TAILLE_PISTE / 3) * 2));
		triangle.lineTo(x + TAILLE_PISTE, y);
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
			if (voiture.getPosition().getX() > murDroite - voiture.getDiametre()) {
				try {
					Vecteur2D vit = MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(),
							angleNormaleMurDroite);
					voiture.setVitesse(vit);
					voiture.getPosition().setX(murDroite - voiture.getDiametre());
					if (Math.toDegrees(voiture.getAngle()) < 90 && Math.toDegrees(voiture.getAngle()) > 0) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) + ((90 - Math.toDegrees(voiture.getAngle())) * 2)));
					} else if (Math.toDegrees(voiture.getAngle()) > 270 && Math.toDegrees(voiture.getAngle()) < 360) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) - ((Math.toDegrees(voiture.getAngle()) - 270) * 2)));

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
						voiture.setAngle(Math.toRadians(Math.toDegrees(voiture.getAngle())
								+ ((360 - (Math.toDegrees(voiture.getAngle())) * 2))));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (!cercle.isEmpty()) {

				try {

					Vecteur2D vit = MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), 135);
					voiture.setVitesse(vit);
					voiture.setPosition(
							new Vecteur2D(voiture.getPosition().getX() - pos, voiture.getPosition().getY() + pos));
					if (Math.toDegrees(voiture.getAngle()) <= 315 && Math.toDegrees(voiture.getAngle()) > 225) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) - ((Math.toDegrees(voiture.getAngle()) - 225) * 2)));
					} else if (Math.toDegrees(voiture.getAngle()) <= 360) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) + (450 - Math.toDegrees(voiture.getAngle()) * 2)));
					} else if (Math.toDegrees(voiture.getAngle()) < 45 && voiture.getAngle() >= 0) {
						voiture.setAngle(Math.toRadians(
								Math.toDegrees(voiture.getAngle()) + (90 - Math.toDegrees(voiture.getAngle()) * 2)));
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
}
