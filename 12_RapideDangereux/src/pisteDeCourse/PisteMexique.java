package pisteDeCourse;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import interfaces.Dessinable;
import utilitaireObjets.PisteDeDepart;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.PisteVirageDroit;
import utilitaireObjets.PisteVirageGauche;
import utilitaireObjets.PisteVirageHaut;

/**
 * Class qui permet de creer une piste deja faite (PisteMexique)
 * 
 * @author Ludovic Julien
 *
 */

public class PisteMexique implements Dessinable {
	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 87;
	/** Position en x de la piste au complet **/
	private int x;
	/** Position en y de la piste au complet  **/
	private int y;
	
	private PisteDeDepart depart = new PisteDeDepart(0,0);
	private PisteHorizontale horizontale = new PisteHorizontale(0,0);
	private PisteVerticale verticale = new PisteVerticale(0,0);;
	private PisteVirageBas bas = new PisteVirageBas(0,0);;
	private PisteVirageGauche gauche = new PisteVirageGauche(0,0);;
	private PisteVirageDroit droit = new PisteVirageDroit(0,0);;
	private PisteVirageHaut haut = new PisteVirageHaut(0,0);;
	
	
	/**
	 * Methode qui permet de construire la piste Mexique a l'aide de parametres
	 * 
	 * @param x		position en x de la piste
	 * @param y		position en y de la piste
	 */
	public PisteMexique(int x, int y) {
		this.x = x;
		this.y = y;
	
	}
	
	
	/**
	 * Methode qui permet de dessiner la piste Mexique sur la zone d'animation a l'aide de g2d
	 */
	@Override
	public void dessiner(Graphics2D g2d) {
		
		//piste virgae bas :
		bas = new PisteVirageBas(x,y);
		bas.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale depart :
		depart = new PisteDeDepart(x,y);
		depart.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//Piste virage vers la gauche:
		gauche = new PisteVirageGauche(x,y);
		gauche.dessiner(g2d);
		this.y = y + TAILLE_PISTE;
		
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);
		this.y = y + TAILLE_PISTE;
	
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);
		this.y = y + TAILLE_PISTE;
	
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);
		this.y = y + TAILLE_PISTE;
		
		//piste vers le droit:
		droit = new PisteVirageDroit(x,y);
		droit.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//Piste Virage Haut;
		haut = new PisteVirageHaut(x,y);
		haut.dessiner(g2d);
		this.y = y - TAILLE_PISTE;
		
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);	
		this.y = y - TAILLE_PISTE;
		
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);	
		this.y = y - TAILLE_PISTE;
		
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);
		
	}

	/**
	 * Retourne le composant virage bas
	 * @return le composant virage bas
	 */
	public PisteVirageBas getBas() {
		return bas;
	}

	/**
	 * Retourne le composant virage gauche
	 * @return le composant virage gauche
	 */
	public PisteVirageGauche getGauche() {
		return gauche;
	}

	/**
	 * Retourne le composant virage droite
	 * @return le composant virage droite
	 */
	public PisteVirageDroit getDroit() {
		return droit;
	}

	/**
	 * Retourne le composant virage haut
	 * @return le composant virage haut
	 */
	public PisteVirageHaut getHaut() {
		return haut;
	}


	

}
