package pisteDeCourse;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import interfaces.Dessinable;
import interfaces.TypePiste;
import utilitaireObjets.Accelerateur;
import utilitaireObjets.PisteDeDepart;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.PisteVirageDroit;
import utilitaireObjets.PisteVirageGauche;
import utilitaireObjets.PisteVirageHaut;
import utilitaireObjets.Voiture;

/**
 * Classe qui crée la piste Italie
 * 
 * @author Kevin Nguyen
 *
 */
public class PisteItalie {

	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 80;
	/** Position en x de la piste au complet **/
	private int x;
	/** Position en y de la piste au complet **/
	private int y;
	/** Piste de depart **/
	private ArrayList<PisteDeDepart> depart = new ArrayList<PisteDeDepart>();
	/** Piste Horizontale **/
	private ArrayList<PisteHorizontale> horizontale = new ArrayList<PisteHorizontale>();
	/** Piste Verticale **/
	private ArrayList<PisteVerticale> verticale = new ArrayList<PisteVerticale>();
	/** Piste Virage Bas **/
	private ArrayList<PisteVirageBas> bas = new ArrayList<PisteVirageBas>();
	/** Piste Virage Gauche **/
	private ArrayList<PisteVirageGauche> gauche = new ArrayList<PisteVirageGauche>();
	/** Piste Virage Droit **/
	private ArrayList<PisteVirageDroit> droit = new ArrayList<PisteVirageDroit>();
	/** Piste Virage Haut **/
	private ArrayList<PisteVirageHaut> haut = new ArrayList<PisteVirageHaut>();

	/** Nombre de pixels par metre **/
	private double pixelsParMetre;

	/**
	 * Constructeur que l'on spécifie la position du coin supérieur gauche du
	 * circuit
	 * 
	 * @param x position en x de la piste
	 * @param y position en y de la piste
	 */
	public PisteItalie(int x, int y) {
		this.x = x;
		this.y = y;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui crée le circuit complet
	 */
	public void creerLaGeometrie() {
		bas.add(new PisteVirageBas(x, y));
		this.x = x + taillePiste;

		// piste horizontale depart :
		depart.add(new PisteDeDepart(x, y));
		this.x = x + taillePiste;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + taillePiste;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + taillePiste;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));

		this.x = x + taillePiste;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));

		this.x = x + taillePiste;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));

		this.x = x + taillePiste;

		// Piste virage vers la gauche:
		gauche.add(new PisteVirageGauche(x, y));

		this.y = y + taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y + taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y + taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y + taillePiste;

		// piste vers le droit:
		droit.add(new PisteVirageDroit(x, y));
		//
		this.x = x - taillePiste;

		// Piste Virage Haut;
		haut.add(new PisteVirageHaut(x, y));

		this.y = y - taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y - taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y - taillePiste;

		// Piste virage vers la gauche:
		gauche.add(new PisteVirageGauche(x, y));

		this.x = x - taillePiste;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));

		this.x = x - taillePiste;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));

		this.x = x - taillePiste;

		// piste virgae bas :
		bas.add(new PisteVirageBas(x, y));

		this.y = y + taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y + taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y + taillePiste;

		// piste vers le droit:
		droit.add(new PisteVirageDroit(x, y));
		//
		this.x = x - taillePiste;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));

		this.x = x - taillePiste;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));

		this.x = x - taillePiste;

		// Piste Virage Haut;
		haut.add(new PisteVirageHaut(x, y));

		this.y = y - taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y - taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y - taillePiste;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		//
		this.y = y - taillePiste;
	}

	public double getPixelsParMetre() {
		return pixelsParMetre;

	}

	public void setPixelsParMetre(double pixelsParMetre) {
		this.pixelsParMetre = pixelsParMetre;

	}

	public ArrayList<PisteDeDepart> getDepart() {
		return depart;

	}

	public ArrayList<PisteHorizontale> getHorizontale() {
		return horizontale;
	}

	public void setHorizontale(ArrayList<PisteHorizontale> horizontale) {
		this.horizontale = horizontale;
	}

	public ArrayList<PisteVerticale> getVerticale() {
		return verticale;
	}

	public void setVerticale(ArrayList<PisteVerticale> verticale) {
		this.verticale = verticale;
	}

	public ArrayList<PisteVirageBas> getBas() {
		return bas;
	}

	public void setBas(ArrayList<PisteVirageBas> bas) {
		this.bas = bas;
	}

	public ArrayList<PisteVirageGauche> getGauche() {
		return gauche;
	}

	public void setGauche(ArrayList<PisteVirageGauche> gauche) {
		this.gauche = gauche;
	}

	public ArrayList<PisteVirageDroit> getDroit() {
		return droit;
	}

	public void setDroit(ArrayList<PisteVirageDroit> droit) {
		this.droit = droit;
	}

	public ArrayList<PisteVirageHaut> getHaut() {
		return haut;
	}

	public void setHaut(ArrayList<PisteVirageHaut> haut) {
		this.haut = haut;
	}

	public void setDepart(ArrayList<PisteDeDepart> depart) {
		this.depart = depart;
	}

}
