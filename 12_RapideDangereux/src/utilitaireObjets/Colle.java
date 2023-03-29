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

public class Colle {

	private transient Shape shapeColle;
	private transient Area bouleDeNeigeAire;
	private transient Area bouleDeNeigeAireCopie;
	private transient Area aireVoiture;

	private TypeObjetSpecial typeObjet = TypeObjetSpecial.COLLE;

	private double pixelsParMetre;;

	private Ellipse2D.Double colle;

	private double diametre;
	private Vecteur2D position;

	public Colle(Vecteur2D pos, double diametre) {
		this.diametre = diametre;
		this.position = pos;

		creerLaGeometrie();
	}

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