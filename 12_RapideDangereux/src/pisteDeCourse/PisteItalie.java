package pisteDeCourse;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

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

public class PisteItalie implements Dessinable {

	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 87;
	/** Position en x de la piste au complet **/
	private int x;
	/** Position en y de la piste au complet **/
	private int y;

	private PisteDeDepart depart = new PisteDeDepart(0, 0);
	private PisteHorizontale horizontale = new PisteHorizontale(0, 0);
	private PisteVerticale verticale = new PisteVerticale(0, 0);;
	private PisteVirageBas bas = new PisteVirageBas(0, 0);;
	private PisteVirageGauche gauche = new PisteVirageGauche(0, 0);;
	private PisteVirageDroit droit = new PisteVirageDroit(0, 0);;
	private PisteVirageHaut haut = new PisteVirageHaut(0, 0);
	private Accelerateur vitesse = new Accelerateur(0, 0);
	private Rectangle2D rectangle;
	private Area aireRectangle;;
	private TypePiste type = TypePiste.ITALIE;

	public PisteItalie(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void dessiner(Graphics2D g2d) {

		// piste virgae bas :
		bas = new PisteVirageBas(x, y);
		bas.dessiner(g2d);
		this.x = x + taillePiste;

		// piste horizontale depart :
		depart = new PisteDeDepart(x, y);
		depart.dessiner(g2d);
		this.x = x + taillePiste;

		// piste horizontale :
		horizontale = new PisteHorizontale(x, y);
		horizontale.dessiner(g2d);

		vitesse = new Accelerateur(x, y);
		vitesse.dessiner(g2d);

		this.x = x + taillePiste;

		// piste horizontale :
		horizontale = new PisteHorizontale(x, y);
		horizontale.dessiner(g2d);
		this.x = x + taillePiste;

		// piste horizontale :
		horizontale = new PisteHorizontale(x, y);
		horizontale.dessiner(g2d);
		this.x = x + taillePiste;

		// piste horizontale :
		horizontale = new PisteHorizontale(x, y);
		horizontale.dessiner(g2d);
		this.x = x + taillePiste;

		// piste horizontale :
		horizontale = new PisteHorizontale(x, y);
		horizontale.dessiner(g2d);
		this.x = x + taillePiste;

		// Piste virage vers la gauche:
		gauche = new PisteVirageGauche(x, y);
		gauche.dessiner(g2d);
		this.y = y + taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y + taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y + taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y + taillePiste;

		// piste vers le droit:
		droit = new PisteVirageDroit(x, y);
		droit.dessiner(g2d);
		this.x = x - taillePiste;

		// Piste Virage Haut;
		haut = new PisteVirageHaut(x, y);
		haut.dessiner(g2d);
		this.y = y - taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y - taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y - taillePiste;

		// Piste virage vers la gauche:
		gauche = new PisteVirageGauche(x, y);
		gauche.dessiner(g2d);
		this.x = x - taillePiste;

		// piste horizontale :
		horizontale = new PisteHorizontale(x, y);
		horizontale.dessiner(g2d);
		this.x = x - taillePiste;

		// piste horizontale :
		horizontale = new PisteHorizontale(x, y);
		horizontale.dessiner(g2d);
		this.x = x - taillePiste;

		// piste virgae bas :
		bas = new PisteVirageBas(x, y);
		bas.dessiner(g2d);
		this.y = y + taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y + taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y + taillePiste;

		// piste vers le droit:
		droit = new PisteVirageDroit(x, y);
		droit.dessiner(g2d);
		this.x = x - taillePiste;

		// piste horizontale :
		horizontale = new PisteHorizontale(x, y);
		horizontale.dessiner(g2d);
		this.x = x - taillePiste;

		// piste horizontale :
		horizontale = new PisteHorizontale(x, y);
		horizontale.dessiner(g2d);
		this.x = x - taillePiste;

		// Piste Virage Haut;
		haut = new PisteVirageHaut(x, y);
		haut.dessiner(g2d);
		this.y = y - taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y - taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y - taillePiste;

		// piste vertical:
		verticale = new PisteVerticale(x, y);
		verticale.dessiner(g2d);
		this.y = y - taillePiste;

	}

	/**
	 * Retourne l'aire le rectangle au centre
	 * 
	 * @return le rectangle au centre
	 */
	public Area getRectangle() {
		return aireRectangle;
	}

	/**
	 * Retourne le composant virage bas
	 * 
	 * @return le composant virage bas
	 */
	public PisteVirageBas getBas() {
		return bas;
	}

	/**
	 * Retourne le composant virage gauche
	 * 
	 * @return le composant virage gauche
	 */
	public PisteVirageGauche getGauche() {
		return gauche;
	}

	/**
	 * Retourne le composant virage droite
	 * 
	 * @return le composant virage droite
	 */
	public PisteVirageDroit getDroit() {
		return droit;
	}

	/**
	 * Retourne le composant virage haut
	 * 
	 * @return le composant virage haut
	 */
	public PisteVirageHaut getHaut() {
		return haut;
	}

	public int getTaillePiste() {
		return taillePiste;
	}

	public TypePiste getType() {
		return type;
	}

	public void setType(TypePiste type) {
		this.type = type;
	}

}
