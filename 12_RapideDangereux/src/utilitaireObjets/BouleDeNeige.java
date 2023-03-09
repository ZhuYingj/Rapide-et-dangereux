package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import interfaces.Dessinable;
import interfaces.Selectionnable;

public class BouleDeNeige implements Dessinable, Selectionnable {
	private Color col;
	private double x;
	private double y;
	private double hauteur, largeur;
	private Ellipse2D.Double boule;
	private double pixelsParMetre;

	public BouleDeNeige(double x, double y, Color col, double largeur, double hauteur) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.col = col;

		creerLaGeometrie();

	}

	public void creerLaGeometrie() {
		boule = new Ellipse2D.Double(x, y - hauteur / 2, largeur, hauteur);
	}

	@Override
	public boolean contient(double xPix, double yPix) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dcop = (Graphics2D) g2d.create();
		g2dcop.draw(boule);
		g2dcop.setColor(Color.blue);
		g2dcop.fill(boule);

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
