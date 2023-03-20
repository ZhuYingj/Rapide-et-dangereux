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
	private final int TAILLE_PISTE= 80;
	/** Position en x de la piste au complet **/
	private int x;
	/** Position en y de la piste au complet **/
	private int y;
	/** Nombre de pixel par metre **/
	private double pixelsParMetre;



	private List<PisteDeDepart> depart = new ArrayList<PisteDeDepart>();

	private List<PisteHorizontale> horizontale = new ArrayList<PisteHorizontale>();
	private List<PisteVerticale> verticale = new ArrayList<PisteVerticale>();
	private List<PisteVirageBas> bas = new ArrayList<PisteVirageBas>();
	private List<PisteVirageGauche> gauche = new ArrayList<PisteVirageGauche>();
	private List<PisteVirageDroit> droit = new ArrayList<PisteVirageDroit>();
	private List<PisteVirageHaut> haut = new ArrayList<PisteVirageHaut>();

	private Accelerateur vitesse = new Accelerateur(0, 0);
	private Colle colle = new Colle(0, 0);
	private Rectangle2D rectangle;
	private Area aireRectangle;;

	/**
	 * Methode qui permet de construire la piste Mexique a l'aide de parametres
	 * 
	 * @param x position en x de la piste
	 * @param y position en y de la piste
	 */
	public PisteMexique(int x, int y) {
		this.x = x;
		this.y = y;

	}

	/**
	 * Methode qui permet de dessiner la piste Mexique sur la zone d'animation a
	 * l'aide de g2d
	 */
	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D gCopie = (Graphics2D) g2d.create();
		// piste virgae bas :
		gCopie.scale(pixelsParMetre, pixelsParMetre);
		bas.add(new PisteVirageBas(x, y));
		bas.get(0).dessiner(gCopie);
		this.x = x + TAILLE_PISTE;

		// piste horizontale depart :
		depart.add(new PisteDeDepart(x, y));
		depart.get(0).dessiner(gCopie);
		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(0).dessiner(gCopie);

		// colle = new Colle(x,y);
		// colle.dessiner(g2d);

		// vitesse = new Accelerateur(x,y);
		// vitesse.dessiner(g2d);

		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(1).dessiner(gCopie);
		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(2).dessiner(gCopie);
		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(3).dessiner(gCopie);
		this.x = x + TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(4).dessiner(gCopie);
		this.x = x + TAILLE_PISTE;

		// Piste virage vers la gauche:
		gauche.add(new PisteVirageGauche(x, y));
		gauche.get(0).dessiner(gCopie);
		this.y = y + TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		verticale.get(0).dessiner(gCopie);
		this.y = y + TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		verticale.get(1).dessiner(gCopie);
		this.y = y + TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		verticale.get(2).dessiner(gCopie);
		this.y = y + TAILLE_PISTE;

		// piste vers le droit:
		droit.add(new PisteVirageDroit(x, y));
		droit.get(0).dessiner(gCopie);
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(5).dessiner(gCopie);
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(6).dessiner(gCopie);
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(7).dessiner(gCopie);
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(8).dessiner(gCopie);
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(9).dessiner(gCopie);
		this.x = x - TAILLE_PISTE;

		// piste horizontale :
		horizontale.add(new PisteHorizontale(x, y));
		horizontale.get(10).dessiner(gCopie);
		this.x = x - TAILLE_PISTE;

		// Piste Virage Haut;
		haut.add(new PisteVirageHaut(x, y));
		haut.get(0).dessiner(gCopie);
		this.y = y - TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		verticale.get(3).dessiner(gCopie);
		this.y = y - TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		verticale.get(4).dessiner(gCopie);
		this.y = y - TAILLE_PISTE;

		// piste vertical:
		verticale.add(new PisteVerticale(x, y));
		verticale.get(5).dessiner(gCopie);

		rectangle = new Rectangle2D.Double(TAILLE_PISTE + 1, TAILLE_PISTE + 1, TAILLE_PISTE * 6 - 1, TAILLE_PISTE * 3 - 1);
		aireRectangle = new Area(rectangle);
		gCopie.setColor(Color.gray);
		gCopie.fill(aireRectangle);
		gCopie.setColor(Color.red);
		gCopie.draw(aireRectangle);

	}
	public double getPixelsParMetre() {
		return pixelsParMetre;
	}

	public void setPixelsParMetre(double pixelsParMetre) {
		this.pixelsParMetre = pixelsParMetre;
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
		return bas.get(0);
	}

	/**
	 * Retourne le composant virage gauche
	 * 
	 * @return le composant virage gauche
	 */
	public PisteVirageGauche getGauche() {
		return gauche.get(0);
	}

	/**
	 * Retourne le composant virage droite
	 * 
	 * @return le composant virage droite
	 */
	public PisteVirageDroit getDroit() {
		return droit.get(0);
	}

	/**
	 * Retourne le composant virage haut
	 * 
	 * @return le composant virage haut
	 */
	public PisteVirageHaut getHaut() {
		return haut.get(0);
	}

	public int getTAILLE_PISTE() {
		return TAILLE_PISTE;
	}

	public void enCollisionAvec(Voiture voiture) {

		for (int i = 0; i < horizontale.size(); i++) {
			horizontale.get(i).enCollisionAvec(voiture);
		}

		for (int i = 0; i < verticale.size(); i++) {
			verticale.get(i).enCollisionAvec(voiture);
		}

		for (int i = 0; i < bas.size(); i++) {
			bas.get(i).enCollisionAvec(voiture);
		}

		for (int i = 0; i < gauche.size(); i++) {
			gauche.get(i).enCollisionAvec(voiture);
		}

		for (int i = 0; i < droit.size(); i++) {
			droit.get(i).enCollisionAvec(voiture);
		}

		for (int i = 0; i < haut.size(); i++) {
			haut.get(i).enCollisionAvec(voiture);
		}
		
		depart.get(0).enCollisionAvec(voiture);

	}

}
