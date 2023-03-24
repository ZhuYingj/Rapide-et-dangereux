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
 * @author Kevin Nguyen
 *
 */

public class PisteMexique implements Dessinable {
	/** Taille de la piste qui est toujours constante **/
	private final int TAILLE_PISTE = 80;
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
		creerLaGeometrie();
	}

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

	/**
	 * Methode qui permet de dessiner la piste Mexique sur la zone d'animation a
	 * l'aide de g2d
	 */
	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D gCopie = (Graphics2D) g2d.create();
		// piste virgae bas :
		gCopie.scale(pixelsParMetre, pixelsParMetre);
		for (int i = 0; i < bas.size(); i++) {
			bas.get(i).dessiner(gCopie);
		}

		for (int i = 0; i < horizontale.size(); i++) {
			horizontale.get(i).dessiner(gCopie);
		}

		for (int i = 0; i < verticale.size(); i++) {
			verticale.get(i).dessiner(gCopie);
		}

		for (int i = 0; i < bas.size(); i++) {
			bas.get(i).dessiner(gCopie);
		}

		for (int i = 0; i < gauche.size(); i++) {
			gauche.get(i).dessiner(gCopie);
		}

		for (int i = 0; i < droit.size(); i++) {
			droit.get(i).dessiner(gCopie);
		}

		for (int i = 0; i < haut.size(); i++) {
			haut.get(i).dessiner(gCopie);
		}

		depart.get(0).dessiner(gCopie);

	}

	public double getPixelsParMetre() {
		return pixelsParMetre;
	}

	public void setPixelsParMetre(double pixelsParMetre) {
		this.pixelsParMetre = pixelsParMetre;
	}

	/**
	 * Gérer les collisions avec chaque morceau de piste
	 * @param voiture La voiture controllée
	 */
	// Kevin Nguyen
	public void enCollisionAvec(Voiture voiture) {

		for (int i = 0; i < horizontale.size(); i++) {
			horizontale.get(i).enCollisionAvec(voiture);
			horizontale.get(i).traverserPiste(voiture);
			if (horizontale.get(i).isCollision() == true) {
				horizontale.get(i).setColor(Color.blue);
				boolean  collision = true;
				
			}else {
				horizontale.get(i).setColor(Color.black);
			}
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
//		traverserPiste(voiture);

	}

	/**
	 * Retourne la piste de départ
	 * @return
	 */
	// Kevin Nguyen
	public List<PisteDeDepart> getDepart() {
		return depart;
	}



//	public void traverserPiste(Voiture voiture) {
//		int taille = horizontale.size();
//		if (horizontale.get(taille-1).traverserPiste(voiture) == true) {
//			boolean  collision = true;
//			System.out.println(collision);
//			
//		} else {
//		
//		}
//	}
}
