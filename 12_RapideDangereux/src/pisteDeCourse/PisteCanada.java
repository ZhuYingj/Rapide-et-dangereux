package pisteDeCourse;

import java.util.ArrayList;

import utilitaireObjets.PisteDeDepart;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.PisteVirageDroit;
import utilitaireObjets.PisteVirageGauche;
import utilitaireObjets.PisteVirageHaut;

/**
 * Classe qui crée la piste Canada
 * 
 * @author Kevin Nguyen
 *
 */
public class PisteCanada {
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

	public PisteCanada(int x, int y) {
		this.x = x;
		this.y = y;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui crée le circuit complet
	 */

	private void creerLaGeometrie() {

		bas.add(new PisteVirageBas(x, y));
		this.x = x + taillePiste;

		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + taillePiste;

		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + taillePiste;

		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + taillePiste;

		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + taillePiste;

		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + taillePiste;

		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + taillePiste;

		gauche.add(new PisteVirageGauche(x, y));
		this.y = y + taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y + taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y + taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y + taillePiste;

		droit.add(new PisteVirageDroit(x, y));
		this.x = x - taillePiste;

		depart.add(new PisteDeDepart(x, y));
		this.x = x - taillePiste;

		haut.add(new PisteVirageHaut(x, y));
		this.y = y - taillePiste;

		bas.add(new PisteVirageBas(x, y));
		this.x = x + taillePiste;

		droit.add(new PisteVirageDroit(x, y));
		this.y = y - taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y - taillePiste;

		gauche.add(new PisteVirageGauche(x, y));
		this.x = x - taillePiste;

		bas.add(new PisteVirageBas(x, y));
		this.y = y + taillePiste;

		droit.add(new PisteVirageDroit(x, y));
		this.x = x - taillePiste;

		haut.add(new PisteVirageHaut(x, y));
		this.y = y - taillePiste;

		gauche.add(new PisteVirageGauche(x, y));
		this.x = x - taillePiste;

		bas.add(new PisteVirageBas(x, y));
		this.y = y + taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y + taillePiste;

		haut.add(new PisteVirageHaut(x, y));
		this.x = x + taillePiste;

		gauche.add(new PisteVirageGauche(x, y));
		this.y = y + taillePiste;

		droit.add(new PisteVirageDroit(x, y));
		this.x = x - taillePiste;

		horizontale.add(new PisteHorizontale(x, y));
		this.x = x - taillePiste;

		haut.add(new PisteVirageHaut(x, y));
		this.y = y - taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y - taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y - taillePiste;

		gauche.add(new PisteVirageGauche(x, y));
		this.x = x - taillePiste;

		bas.add(new PisteVirageBas(x, y));
		this.y = y + taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y + taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y + taillePiste;

		droit.add(new PisteVirageDroit(x, y));
		this.x = x - taillePiste;

		haut.add(new PisteVirageHaut(x, y));
		this.y = y - taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y - taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y - taillePiste;

		verticale.add(new PisteVerticale(x, y));
		this.y = y - taillePiste;

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

	public ArrayList<PisteDeDepart> getDepart() {
		return depart;
	}

	public void setDepart(ArrayList<PisteDeDepart> depart) {
		this.depart = depart;
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

	public double getPixelsParMetre() {
		return pixelsParMetre;
	}

	public void setPixelsParMetre(double pixelsParMetre) {
		this.pixelsParMetre = pixelsParMetre;
	}
}
