package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import physique.MoteurPhysique;

/**
 * Classe qui permet de creer un objet piste virage gauche
 * 
 * @author Ludovic Julien
 * @author Kevin Nguyen
 * @author Tan Tommy Rin
 */

public class PisteVirageGauche implements Dessinable, Selectionnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 80;

	/** la position en x de depart que l'objet piste qui vas etre creer **/
	private int x;

	/** la position en y de depart que l'objet piste qui vas etre creer **/
	private int y;
	/** Position en x du côté droit du morceau de piste **/
	private int murDroite;

	/** Position en x du côté gauche du morceau de piste **/
	private int murGauche;
	/** Position en y du côté haut du morceau de piste **/
	private int murHaut;
	/** Position en y du côté bas du morceau de piste **/
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
	private transient Area aireTriangle;
	/** Boolean collision initié à faux **/
	private boolean collision = false;
	/** Couleur de la piste initié à noir **/
	private Color color = new Color(128, 126, 120);
	/** Aire du morceau de piste **/
	private Rectangle2D.Double formeAire;
	private boolean enContactAvecColle = false;
	private int nombrePisteColle = 0;
	/** Couleur de la bordure **/
	private Color bordure = Color.red;

	/**
	 * Methode qui permet de construire la piste virage gauche a l'aide de
	 * parametres
	 * 
	 * @param x position en x de la piste
	 * @param y position en y de la piste
	 */
	// Ludovic Julien
	public PisteVirageGauche(int x, int y) {
		this.x = x;
		this.y = y;
		this.murDroite = x + taillePiste + 1;
		this.murGauche = x + 1;
		this.murHaut = y + 1;
		this.murBas = y + taillePiste + 1;
		formeAire = new Rectangle2D.Double(this.x, this.y, taillePiste, taillePiste);
	}

	/**
	 * Methode qui permet de construire la piste horizontale a l'aide de parametres
	 * 
	 * @param g2d Composant graphique
	 */
	// Ludovic Julien
	@Override
	public void dessiner(Graphics2D g2d) {
		// g2d.scale(pixelsParMetre, pixelsParMetre);
		g2d.setColor(color);
		g2d.fillRect(x, y, taillePiste, taillePiste);
		g2d.setColor(bordure);
		Stroke stroke = new BasicStroke(2f);
		g2d.setStroke(stroke);
		g2d.drawLine(x + 1, y, x + (taillePiste / 3), y);
		g2d.drawLine(x + (taillePiste / 3), y, x + ((taillePiste / 3) * 2), y + (taillePiste / 3));
		g2d.drawLine(x + ((taillePiste / 3) * 2), y + (taillePiste / 3), x + taillePiste, y + ((taillePiste / 3) * 2));
		g2d.drawLine(x + taillePiste, y + ((taillePiste / 3) * 2), x + taillePiste, y + taillePiste - 1);

		triangle = new Path2D.Double();
		triangle.moveTo(x + (taillePiste / 3), y);
		triangle.lineTo(x + taillePiste, y + ((taillePiste / 3) * 2));
		triangle.lineTo(x + taillePiste, y);
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

	public int getTaillePiste() {
		return taillePiste;
	}

	public int getNombrePisteColle() {
		return nombrePisteColle;
	}

	public void setNombrePisteColle(int nombrePisteColle) {
		this.nombrePisteColle = nombrePisteColle;
	}

	public void setTaillePiste(int taillePiste) {
		this.taillePiste = taillePiste;
	}

	/**
	 * Méthode permettant de calculer la collision avec les murs du morceau de piste
	 * ainsi que de calculer l'angle de réflexion
	 * 
	 * @param voiture La voiture controllée
	 */
	// Kevin Nguyen
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
	 * Méthode permettant de calculer la collision avec les murs du morceau de piste
	 * et la boule de neige
	 * 
	 * @param objetSpecial L'objet special de type boule de neige
	 * @return si la piste est en collision avec la boule de neige
	 */
	// Tan Tommy Rin
	public boolean enCollisionAvecBouleDeNeige(ObjetSpecial objetSpecial) {

		Area cercle = new Area(objetSpecial.getBouleDeNeige().getBoule());
		cercle.intersect(aireTriangle);
		// Cette variable est juste pour returner la valeur de vérité si la boule de
		// neige est en collision avec ce morceau de piste
		boolean enCollision = false;

		if (objetSpecial.getBouleDeNeige().getBoule().getX() > murGauche
				&& objetSpecial.getBouleDeNeige().getBoule().getX() < murDroite
				&& objetSpecial.getBouleDeNeige().getBoule().getY() > murHaut
				&& objetSpecial.getBouleDeNeige().getBoule().getY() < murBas) {
			if (objetSpecial.getBouleDeNeige().getBoule().getX() > murDroite
					- objetSpecial.getBouleDeNeige().getDiametre()) {

				enCollision = true;
			} else if (objetSpecial.getBouleDeNeige().getBoule().getY() < murHaut + 1) {

				enCollision = true;
			} else if (!cercle.isEmpty()) {

				enCollision = true;
			}
		}
		return enCollision;

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

	public boolean isEnContactAvecColle() {
		return enContactAvecColle;
	}

	public void setEnContactAvecColle(boolean enContactAvecColle) {
		this.enContactAvecColle = enContactAvecColle;
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

	public void setBordure(Color bordure) {
		this.bordure = bordure;
	}

}
