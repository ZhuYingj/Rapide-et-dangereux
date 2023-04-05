package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.TypeObjetSpecial;

/**
 * Classe qui crée et gère tout ce qui est de la boule de neige
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class BouleDeNeige {
	private double diametre;
	private Ellipse2D.Double boule;

	private double pixelsParMetre;

	private Voiture voiture;

	private transient Shape shapeBoule;
	private transient Area bouleDeNeigeAire;
	private transient Area bouleDeNeigeAireCopie;
	private transient Area aireVoiture;
	private transient Area aireVoiture1;

	private TypeObjetSpecial typeObjet = TypeObjetSpecial.BOULEDENEIGE;
	private boolean contactBouleNeige = false;
	private Vecteur2D position;

	/**
	 * Méthode qui crée la boule de neige
	 * 
	 * @param pos      le positionnement de la boule de neige
	 * @param diametre le diametre de la boule de neige
	 */
	// Alexis Pineda-Alvarado
	public BouleDeNeige(Vecteur2D pos, double diametre) {

		this.diametre = diametre;
		this.position = pos;

		creerLaGeometrie();

	}

	/**
	 * Méthode qui dessine la boule de neige
	 */
	// Alexis Pineda-Alvarado
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dcop = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();

		shapeBoule = mat.createTransformedShape(boule);
		g2dcop.setColor(Color.cyan);
		g2dcop.fill(shapeBoule);

		bouleDeNeigeAire = new Area(shapeBoule);
		bouleDeNeigeAireCopie = new Area(bouleDeNeigeAire);

	}

	/**
	 * Méthode qui permet de créer la géométrie de la boule de neige
	 */

	private void creerLaGeometrie() {

		boule = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre);
	}

	/**
	 * 
	 * méthode qui détecte la collision de la voiture et la boule de neige
	 * 
	 * @param v ceci est la valeur de la voiture
	 * @return la valeur de la collision en true or false
	 */
	// Alexis Pineda-Alvarado
	public boolean collisionDeLaVoiture(Voiture v) {

		this.voiture = v;
		bouleDeNeigeAireCopie = new Area(boule);
		aireVoiture = new Area(voiture.getCercle());
		aireVoiture1 = new Area(aireVoiture);
		aireVoiture1.intersect(bouleDeNeigeAireCopie);
		if (contactBouleNeige == false) {
			if (!aireVoiture1.isEmpty()) {
				contactBouleNeige = true;

			} else {
				contactBouleNeige = false;
			}
		}

		return contactBouleNeige;
	}

	/**
	 * Méthode qui permet de changer le nombre de pixel par mètre par un nombre
	 * voulu
	 * 
	 * @param pixelsParMetreVoulu
	 */
	// Par Alexis Pineda-Alvarado
	public void setPixelsParMetre(double pixelsParMetreVoulu) {
		this.pixelsParMetre = pixelsParMetreVoulu;

	}

	/**
	 * Méthode qui retourne le nombre de pixels par metre
	 * 
	 * @return nombre de pixel par metre
	 */
	// Par Alexis Pineda-Alvarado
	public double getPixelsParMetre() {
		return this.pixelsParMetre;
	}

	public double getDiametre() {
		return diametre;
	}

	public void setDiametre(double diametre) {
		this.diametre = diametre;
	}

	public Shape getShapeBoule() {
		return shapeBoule;
	}

	public void setShapeBoule(Shape shapeBoule) {
		this.shapeBoule = shapeBoule;
	}

	public TypeObjetSpecial getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjetSpecial typeObjet) {
		this.typeObjet = typeObjet;
	}

	public Ellipse2D.Double getBoule() {
		return boule;
	}

	public void setBoule(Ellipse2D.Double boule) {
		this.boule = boule;
	}

}
