package pisteDeCourse;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import interfaces.Dessinable;
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

public class PisteMexique implements Dessinable {
	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 87;
	/** Position en x de la piste au complet **/
	private int x;
	/** Position en y de la piste au complet  **/
	private int y;

	private PisteDeDepart depart = new PisteDeDepart(0,0);

	private List<PisteHorizontale>  horizontale  = new ArrayList<PisteHorizontale>();
	private List<PisteVerticale>  verticale  = new ArrayList<PisteVerticale>();
	private List<PisteVirageBas>  bas  = new ArrayList<PisteVirageBas>();
	private List<PisteVirageGauche>  gauche  = new ArrayList<PisteVirageGauche>();
	private List<PisteVirageDroit>  droit  = new ArrayList<PisteVirageDroit>();
	private List<PisteVirageHaut>  haut  = new ArrayList<PisteVirageHaut>();


	private Accelerateur vitesse = new Accelerateur(0,0);
	private Colle colle = new Colle(0,0);
	private Rectangle2D rectangle;
	private Area aireRectangle;;


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

		bas.add(new PisteVirageBas(x,y));
		bas.get(0).dessiner(g2d);
		this.x = x + taillePiste;

		//piste horizontale depart :
		depart = new PisteDeDepart(x,y);
		depart.dessiner(g2d);
		this.x = x + taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(0).dessiner(g2d);


		//		colle = new Colle(x,y);
		//		colle.dessiner(g2d);

		//		vitesse = new Accelerateur(x,y);
		//		vitesse.dessiner(g2d);

		this.x = x + taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(1).dessiner(g2d);
		this.x = x + taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(2).dessiner(g2d);
		this.x = x + taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(3).dessiner(g2d);
		this.x = x + taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(4).dessiner(g2d);
		this.x = x + taillePiste;

		//Piste virage vers la gauche:
		gauche.add(new PisteVirageGauche(x,y));
		gauche.get(0).dessiner(g2d);
		this.y = y + taillePiste;

		//piste vertical:
		verticale.add(new PisteVerticale(x,y)); 
		verticale.get(0).dessiner(g2d);
		this.y = y + taillePiste;

		//piste vertical:
		verticale.add(new PisteVerticale(x,y)); 
		verticale.get(1).dessiner(g2d);
		this.y = y + taillePiste;

		//piste vertical:
		verticale.add(new PisteVerticale(x,y)); 
		verticale.get(2).dessiner(g2d);
		this.y = y + taillePiste;

		//piste vers le droit:
		droit.add(new PisteVirageDroit(x,y));
		droit.get(0).dessiner(g2d);
		this.x = x - taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(5).dessiner(g2d);
		this.x = x - taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(6).dessiner(g2d);
		this.x = x - taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(7).dessiner(g2d);
		this.x = x - taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(8).dessiner(g2d);
		this.x = x - taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(9).dessiner(g2d);
		this.x = x - taillePiste;

		//piste horizontale :
		horizontale.add(new PisteHorizontale(x,y));
		horizontale.get(10).dessiner(g2d);
		this.x = x - taillePiste;

		//Piste Virage Haut;
		haut.add(new PisteVirageHaut(x,y));
		haut.get(0).dessiner(g2d);
		this.y = y - taillePiste;

		//piste vertical:
		verticale.add(new PisteVerticale(x,y)); 
		verticale.get(3).dessiner(g2d);
		this.y = y - taillePiste;

		//piste vertical:
		verticale.add(new PisteVerticale(x,y)); 
		verticale.get(4).dessiner(g2d);	
		this.y = y - taillePiste;

		//piste vertical:
		verticale.add(new PisteVerticale(x,y)); 
		verticale.get(5).dessiner(g2d);


		rectangle = new Rectangle2D.Double(taillePiste+1, taillePiste+1 ,taillePiste*6-1 ,taillePiste*3-1);
		aireRectangle = new Area(rectangle);
		g2d.setColor(Color.gray);
		g2d.fill(aireRectangle);
		g2d.setColor(Color.red);
		g2d.draw(aireRectangle);

	}
	/**
	 * Retourne l'aire le rectangle au centre
	 * @return le rectangle au centre
	 */
	public Area getRectangle() {
		return aireRectangle;
	}

	/**
	 * Retourne le composant virage bas
	 * @return le composant virage bas
	 */
	public PisteVirageBas getBas() {
		return bas.get(0);
	}

	/**
	 * Retourne le composant virage gauche
	 * @return le composant virage gauche
	 */
	public PisteVirageGauche getGauche() {
		return gauche.get(0);
	}

	/**
	 * Retourne le composant virage droite
	 * @return le composant virage droite
	 */
	public PisteVirageDroit getDroit() {
		return droit.get(0);
	}

	/**
	 * Retourne le composant virage haut
	 * @return le composant virage haut
	 */
	public PisteVirageHaut getHaut() {
		return haut.get(0);
	}

	public int getTaillePiste() {
		return taillePiste;
	}

	public void enCollisionAvec(Voiture voiture) {
		
		for(int i =0;i<horizontale.size();  i++) {
			horizontale.get(i).enCollisionAvec(voiture);
		}

		for(int i =0;i<verticale.size();  i++) {
			verticale.get(i).enCollisionAvec(voiture);
		}
		
		for(int i =0;i<bas.size();  i++) {
			bas.get(i).enCollisionAvec(voiture);
		}
		
		for(int i=0; i<gauche.size();  i++) {
			gauche.get(i).enCollisionAvec(voiture);
		}
		
		for(int i=0; i<droit.size();  i++) {
			droit.get(i).enCollisionAvec(voiture);
		}
		
		for(int i=0; i<haut.size();  i++) {
			haut.get(i).enCollisionAvec(voiture);
		}
		
		
		depart.enCollisionAvec(voiture);
		
		


	}



}
