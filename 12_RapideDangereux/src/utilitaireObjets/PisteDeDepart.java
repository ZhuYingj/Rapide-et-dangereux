package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.ImageIcon;

import application.OutilsImage;
import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import physique.MoteurPhysique;

/**
 * Classe qui permet de creer un objet piste de d�part
 * 
 * @author Ludovic Julien
 * @author Kevin Nguyen
 * @author Tan Tommy Rin
 */

public class PisteDeDepart implements Dessinable, Selectionnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 80;

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
	private Rectangle2D.Double formeAire;
	private Voiture voiture;

	private Voiture voiture2;
	private boolean enContactAvecColle = false;
	private int nombrePisteColle = 0;
	/** Couleur de la bordure **/
	private Color bordure = Color.red;

	/**
	 * Methode qui permet de construire la piste verticale a l'aide de parametre
	 * 
	 * @param x position en x de la piste
	 * @param y position en y de la piste
	 * 
	 */
	// Ludovic Julien
	public PisteDeDepart(int x, int y) {
		this.x = x;
		this.y = y;
		this.murDroite = x + taillePiste;
		this.murGauche = x;
		this.murHaut = y;
		this.murBas = y + taillePiste;
		voiture = new Voiture(new Vecteur2D(x + taillePiste / 4, y + taillePiste / 4), Color.yellow, 50, 16, 0, 50);
		voiture2 = new Voiture(new Vecteur2D(x + taillePiste / 4, y + taillePiste * 3 / 4), Color.cyan, 50, 16, 0, 60);

		formeAire = new Rectangle2D.Double(this.x, this.y, taillePiste, taillePiste);
	}

	/**
	 * Methode qui permet de dessiner la piste de d�part sur la zone d'animation a
	 * l'aide de g2d
	 * 
	 * @param g2d Le composant graphique
	 */
	// Ludovic Julien
	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect(x, y, taillePiste, taillePiste);

		g2d.setColor(bordure);
		Stroke stroke1 = new BasicStroke(2f);
		g2d.setStroke(stroke1);
		g2d.drawLine(x + 1, y, x + taillePiste - 1, y);
		g2d.drawLine(x + 1, y + taillePiste, x + taillePiste - 1, y + taillePiste);

		g2d.setColor(Color.WHITE);
		Stroke stroke0 = new BasicStroke(5f);
		g2d.setStroke(stroke0);
		g2d.drawLine(x + (taillePiste / 2), y + (taillePiste / 7), x + (taillePiste / 2), y + ((taillePiste / 7) * 2));
		g2d.drawLine(x + (taillePiste / 2), y + ((taillePiste / 7) * 3), x + (taillePiste / 2),
				y + ((taillePiste / 7) * 4));
		g2d.drawLine(x + (taillePiste / 2), y + ((taillePiste / 7) * 5), x + (taillePiste / 2),
				y + ((taillePiste / 7) * 6));

		voiture.dessiner(g2d);
		voiture2.dessiner(g2d);

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

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Méthode permettant de calculer la collision avec les murs du morceau de piste
	 * et la boule de neige
	 * 
	 * @param objetSpecial L'objet special de type boule de neige
	 * @return si la piste est en collision avec la boule de neige
	 */
	// Tan Tommy Rin

	public boolean enCollisionAvecBouleDeNeige(ObjetSpecial objetSpecial) {

		// Cette variable est juste pour returner la valeur de vérité si la boule de
		// neige est en collision avec ce morceau de piste
		boolean enCollision = false;

		if (objetSpecial.getBouleDeNeige().getBoule().getX() > murGauche
				&& objetSpecial.getBouleDeNeige().getBoule().getX() < murDroite
				&& objetSpecial.getBouleDeNeige().getBoule().getY() > murHaut
				&& objetSpecial.getBouleDeNeige().getBoule().getY() < murBas) {
			if (objetSpecial.getBouleDeNeige().getBoule().getY() < murHaut + 1) {
				enCollision = true;
			} else if (objetSpecial.getBouleDeNeige().getBoule().getY() > murBas - objetSpecial.getDiametreObjet()) {
				enCollision = true;
			}
		}

		return enCollision;
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

	/**
	 * Méthode qui permet de détecter s'il y a une collision de la voiture avec le
	 * morceau de piste
	 * 
	 * @param voiture La voiture en collision
	 */
	// Tan Tommy Rin
	public void collisionColle(Voiture voiture) {

		if (formeAire.contains(voiture.getPosition().getX(), voiture.getPosition().getY())) {
			enContactAvecColle = true;
		} else {
			enContactAvecColle = false;
		}

	}

	/**
	 * Méthode permettant de savoir si la voiture est repassée par le point de
	 * départ
	 * 
	 * @param voiture La voiture controllée
	 * @return La valeur booléene
	 */
	// Kevin Nguyen
	public boolean resetTout(Voiture voiture) {
		if (voiture.getPosition().getX() > murGauche + voiture.getDiametre() && voiture.getPosition().getX() < murDroite
				&& voiture.getPosition().getY() > murHaut && voiture.getPosition().getY() < murBas) {
			return true;
		} else {
			return false;
		}
	}

	public int getNombrePisteColle() {
		return nombrePisteColle;
	}

	public void setNombrePisteColle(int nombrePisteColle) {
		this.nombrePisteColle = nombrePisteColle;
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

	public boolean isEnContactAvecColle() {
		return enContactAvecColle;
	}

	public void setEnContactAvecColle(boolean enContactAvecColle) {
		this.enContactAvecColle = enContactAvecColle;
	}

	/**
	 * Méthode qui permet de savoir si le clic de la souris contient cet objet
	 * 
	 * @param xPix la coordonnée du clic en x
	 * @param yPix la coordonnée du clic en y
	 * 
	 * @return si le clic contient la piste
	 */
	// Kevin Nguyen

	@Override
	public boolean contient(double xPix, double yPix) {
		if (formeAire.contains(xPix, yPix)) {
			return true;
		} else {
			return false;
		}

	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Voiture getVoiture2() {
		return voiture2;
	}

	public void setVoiture2(Voiture voiture2) {
		this.voiture2 = voiture2;
	}

	public int getTaillePiste() {
		return taillePiste;
	}

	public void setTaillePiste(int taillePiste) {
		this.taillePiste = taillePiste;
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

	public Rectangle2D.Double getFormeAire() {
		return formeAire;
	}

	public void setFormeAire(Rectangle2D.Double formeAire) {
		this.formeAire = formeAire;
	}

	public int getMurDroite() {
		return murDroite;
	}

	public void setMurDroite(int murDroite) {
		this.murDroite = murDroite;
	}

	public int getMurGauche() {
		return murGauche;
	}

	public void setMurGauche(int murGauche) {
		this.murGauche = murGauche;
	}

	public int getMurHaut() {
		return murHaut;
	}

	public void setMurHaut(int murHaut) {
		this.murHaut = murHaut;
	}

	public int getMurBas() {
		return murBas;
	}

	public void setMurBas(int murBas) {
		this.murBas = murBas;
	}

	public Color getBordure() {
		return bordure;
	}

	public void setBordure(Color bordure) {
		this.bordure = bordure;
	}

}
