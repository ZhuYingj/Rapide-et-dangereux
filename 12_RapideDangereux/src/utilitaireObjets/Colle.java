package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;

/**
 * class qui permet de creer un objet colle
 * 
 * @author Ludovic Julien
 *
 */
public class Colle implements Dessinable {

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 87;
	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer  **/
	private int y;
	private TypeObjetSpecial typeObjet = TypeObjetSpecial.COLLE;
	private transient Shape shapeColle;
	private transient Area bouleDeNeigeAire;
	private transient Area bouleDeNeigeAireCopie;
	private transient Area aireVoiture;

	private double pixelsParMetre;;

	private Ellipse2D.Double colle;

	private double diametre;
	private Vecteur2D position;

	
	
	/**
	 * Méthode qui permet de créer un objet colle à l'aide de paramètres
	 * 
	 * @param x position en x
	 * @param y position en y
	 
	 */
	public Colle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Colle(Vecteur2D pos, double diametre) {
		this.diametre = diametre;
		this.position = pos;

		creerLaGeometrie();

	}

	
	/**
	 * Methode qui permet de dessiner l'objet colle sur la zone d'animation a
	 * l'aide de g2d
	 */
	@Override


	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dcop = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
//		mat.scale(pixelsParMetre, pixelsParMetre);
		shapeColle = mat.createTransformedShape(colle);
		g2dcop.setColor(Color.green);
		g2dcop.fill(shapeColle);

		// bouleDeNeigeAire = new Area(shapeTrou);
		// bouleDeNeigeAireCopie = new Area(bouleDeNeigeAire);

	}


	public TypeObjetSpecial getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjetSpecial typeObjet) {
		this.typeObjet = typeObjet;
	}


	private void creerLaGeometrie() {

		colle = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre);
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

}

