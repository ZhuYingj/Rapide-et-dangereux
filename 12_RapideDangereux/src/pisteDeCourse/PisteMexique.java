package pisteDeCourse;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;

import interfaces.Dessinable;
import interfaces.TypePiste;
import utilitaireObjets.Accelerateur;
import utilitaireObjets.Colle;
import utilitaireObjets.PisteDeDepart;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.PisteVirageDroit;
import utilitaireObjets.PisteVirageGauche;
import utilitaireObjets.PisteVirageHaut;
import utilitaireObjets.Voiture;

/**
 * Class qui permet de creer une piste deja faite (PisteMexique)
 * 
 * @author Ludovic Julien
 *
 */

public class PisteMexique {
	/** Taille de la piste qui est toujours constante **/
	private final int TAILLE_PISTE = 80;
	/** Position en x de la piste au complet **/
	private int x;
	/** Position en y de la piste au complet **/
	private int y;
	/** Nombre de pixel par metre **/
	private double pixelsParMetre;

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

	private ArrayList<Accelerateur> acc = new ArrayList<Accelerateur>();

	private ArrayList<Colle> colle = new ArrayList<Colle>();

	private TypePiste type = TypePiste.MEXIQUE;

	/**
	 * Methode qui permet de construire la piste Mexique a l'aide de parametres
	 * 
	 * @param x position en x de la piste
	 * @param y position en y de la piste
	 */
	// Ludovic Julien
	public PisteMexique(int x, int y) {
		this.x = x;
		this.y = y;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui permet de créer la géometrie de la piste mexique
	 */
	//Ludovic Julien
	public void creerLaGeometrie() {
		bas.add(new PisteVirageBas(x, y));
		this.x = x + TAILLE_PISTE;

		// piste horizontale depart :
		depart.add(new PisteDeDepart(x, y));
		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x + TAILLE_PISTE;

		// Piste virage vers la gauche:
		gauche.add(new PisteVirageGauche(x, y));
		this.y = y + TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		this.y = y + TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		this.y = y + TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		this.y = y + TAILLE_PISTE;

		// piste vers le droit:
		droit.add(new PisteVirageDroit(x, y));
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		this.x = x - TAILLE_PISTE;

		// Piste Virage Haut;
		haut.add(new PisteVirageHaut(x, y));
		this.y = y - TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		this.y = y - TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		this.y = y - TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));

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

	public TypePiste getType() {
		return type;
	}

	public void setType(TypePiste type) {
		this.type = type;
	}

	public ArrayList<PisteHorizontale> getHorizontale() {
		return horizontale;
	}

	public void setHorizontale(ArrayList<PisteHorizontale> horizontale) {
		this.horizontale = horizontale;
	}

	public void setGauche(ArrayList<PisteVirageGauche> gauche) {
		this.gauche = gauche;
	}

	public void setDroit(ArrayList<PisteVirageDroit> droit) {
		this.droit = droit;
	}

	public void setHaut(ArrayList<PisteVirageHaut> haut) {
		this.haut = haut;
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

	public ArrayList<PisteVirageDroit> getDroit() {
		return droit;
	}

	public ArrayList<PisteVirageHaut> getHaut() {
		return haut;
	}

	public void setDepart(ArrayList<PisteDeDepart> depart) {
		this.depart = depart;
	}

}
