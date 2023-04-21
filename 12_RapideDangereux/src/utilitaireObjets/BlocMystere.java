package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import interfaces.TypeObjetSpecial;

/**
 * Classe permettant de créer et de gérer une boite mystere
 * 
 * @author Tan Tommy Rin
 *
 */
public class BlocMystere implements Dessinable, Selectionnable, Serializable {

	private static final long serialVersionUID = 1L;
	/** Le nombre de pixel par metre **/
	private double pixelParMetre = 1;
	/** Le diametre de la boite **/
	private double diametre;
	/** Position de la boite mystere **/
	private Vecteur2D position;
	/** La forme de la boite **/
	private Rectangle2D carre;
	private transient Shape shapeCarre;
	private ObjetSpecial objetSpecial;
	/** Boolean pour savoir si la voiture est en contact avec la boite **/
	private boolean enContact = false;

	private transient Graphics2D gTempo;

	/**
	 * Méthode qui permet de construire un bloc mystere avec des parametres
	 * 
	 * @param diametre diametre de la voiture
	 * @param pos      position de la voiture
	 */
	// Tan Tommy Rin
	public BlocMystere(double diametre, Vecteur2D pos) {
		this.diametre = diametre;
		this.position = pos;
		creerLaGeometrie();
	}

	/**
	 * Création de la forme de la boite mystere à l'aide d'un carré
	 */
	// Tan Tommy Rin
	private void creerLaGeometrie() {
		carre = new Rectangle2D.Double(position.getX(), position.getY(), diametre, diametre);

	}

	/**
	 * 
	 * 
	 * Méthode qui permet de gérer la collision de la boite mystere avec la voiture
	 * et retourne si la voiture est en collision avec la boite mystere
	 * 
	 * @param voiture Voiture en collision
	 * @return true ou false dependant de la collision de la voiture avec la boite
	 *         mystere
	 */
	// Tan Tommy Rin
	public boolean enCollisionAvecVoiture(Voiture voiture) {
		AffineTransform mat = new AffineTransform();
		shapeCarre = mat.createTransformedShape(carre);
		Area aireCopieVoiture = new Area(voiture.getCercle());
		Area shapeCarreCopie = new Area(shapeCarre);

		aireCopieVoiture.intersect(shapeCarreCopie);

		if (!aireCopieVoiture.isEmpty() && enContact == false) {
			objetRandomChoisi(voiture);

			enContact = true;

		}

		if (aireCopieVoiture.isEmpty()) {
			enContact = false;
		}
		if (enContact == true) {
			dessiner(gTempo);
			return true;

		} else {
			return false;
		}
	}

	/**
	 * Méthode qui permet de choisir quel objet sera dans la boite mystere à l'aide
	 * de probabilité
	 * 
	 * @param voiture La voiture qui prend le bloc mystere
	 */
	// Tan Tommy Rin
	public void objetRandomChoisi(Voiture voiture) {

		// Crée nombre au hasard de 0 - 1

		double nombreRandom = Math.random();

		// 20 % de chance que ce soit un champignon
		if (nombreRandom < 0.2) {
			objetSpecial = new ObjetSpecial(this.position, this.diametre, TypeObjetSpecial.CHAMPIGNON);

		}
		// 30 % de chance que ce soit une boule de enige
		else if (nombreRandom < 0.5) {

			objetSpecial = new ObjetSpecial(this.position, this.diametre, TypeObjetSpecial.BOULEDENEIGE);

		}

		// 30 % de chance que ce soit de la colle
		else if (nombreRandom < 0.8) {
			objetSpecial = new ObjetSpecial(this.position, this.diametre, TypeObjetSpecial.COLLE);

		}
		// 20 % de chance que ce soit un trou noir
		else {
			objetSpecial = new ObjetSpecial(this.position, this.diametre, TypeObjetSpecial.TROUNOIR);

		}

	}

	/**
	 * Méthode qui permet de dessiner sur la zone d'animation à l'aide du g2d
	 * 
	 * @param g2d Le composant graphique
	 */
	// Tan Tommy Rin
	@Override
	public void dessiner(Graphics2D g2d) {
		gTempo = (Graphics2D) g2d.create();

		AffineTransform mat = new AffineTransform();
		gTempo.scale(pixelParMetre, pixelParMetre);
		shapeCarre = mat.createTransformedShape(carre);
		gTempo.setColor(Color.orange);
		if (enContact == false) {
			gTempo.fill(shapeCarre);
		}

		if (enContact == true) {
			objetSpecial.dessiner(g2d);
		}

	}

	public ObjetSpecial getObjetSpecial() {
		return objetSpecial;
	}

	public void setObjetSpecial(ObjetSpecial objetSpecial) {
		this.objetSpecial = objetSpecial;
	}

	public double getPixelParMetre() {
		return pixelParMetre;
	}

	public void setPixelParMetre(double pixelParMetre) {
		this.pixelParMetre = pixelParMetre;

	}

	public double getDiametre() {
		return diametre;
	}

	public void setDiametre(double diametre) {
		this.diametre = diametre;
		creerLaGeometrie();
	}

	public Vecteur2D getPosition() {
		return position;
	}

	public void setPosition(Vecteur2D position) {
		this.position = position;
		creerLaGeometrie();
	}

	public Rectangle2D getCarre() {
		return carre;
	}

	public void setCarre(Rectangle2D carre) {
		this.carre = carre;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui permet de savoir si le clic de la souris contient cet objet
	 */
	// Tan Tommy Rin
	@Override
	public boolean contient(double xPix, double yPix) {
		if (carre.contains(xPix, yPix)) {
			return true;
		} else {
			return false;
		}

	}

	public Graphics2D getgTempo() {
		return gTempo;
	}

	public void setgTempo(Graphics2D gTempo) {
		this.gTempo = gTempo;

	}

	public boolean isEnContact() {
		return enContact;
	}

	public void setEnContact(boolean enContact) {
		this.enContact = enContact;
	}

}
