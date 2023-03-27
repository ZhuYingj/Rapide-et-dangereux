package pisteDeCourse;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

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

public class PisteItalie implements Dessinable {

	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 80;
	/** Position en x de la piste au complet **/
	private int x;
	/** Position en y de la piste au complet **/
	private int y;

	private List<PisteDeDepart> depart = new ArrayList<PisteDeDepart>();

	private List<PisteHorizontale> horizontale = new ArrayList<PisteHorizontale>();
	private List<PisteVerticale> verticale = new ArrayList<PisteVerticale>();
	private List<PisteVirageBas> bas = new ArrayList<PisteVirageBas>();
	private List<PisteVirageGauche> gauche = new ArrayList<PisteVirageGauche>();
	private List<PisteVirageDroit> droit = new ArrayList<PisteVirageDroit>();
	private List<PisteVirageHaut> haut = new ArrayList<PisteVirageHaut>();

	private Accelerateur vitesse = new Accelerateur(0, 0);
	private Rectangle2D rectangle;

	private TypePiste type = TypePiste.ITALIE;

	private Area aireRectangle;
	private double pixelsParMetre;


	public PisteItalie(int x, int y) {
		this.x = x;
		this.y = y;
		creerLaGeometrie();
	}

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

	public List<PisteDeDepart> getDepart() {
		return depart;

	}

	public void enCollisionAvec(Voiture voiture) {

		for (int i = 0; i < horizontale.size(); i++) {
			horizontale.get(i).enCollisionAvec(voiture);
			horizontale.get(i).traverserPiste(voiture);
			if (horizontale.get(i).isCollision() == true) {
				horizontale.get(i).setColor(Color.blue);
				boolean collision = true;

			} else {
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
		tourComplet(voiture);
	}

	public void tourComplet(Voiture voiture) {

		for (int i = 0; i < horizontale.size(); i++) {
			horizontale.get(i).isCollision();
		}
		int count = 0;
		for (int i = 0; i < horizontale.size(); i++) {
			if (horizontale.get(i).isCollision() == true) {
				count++;
			}
		}

		for (int i = 0; i < bas.size(); i++) {
			bas.get(i).isCollision();
		}

		for (int i = 0; i < bas.size(); i++) {
			if (bas.get(i).isCollision() == true) {
				count++;
			}
		}

		for (int i = 0; i < haut.size(); i++) {
			haut.get(i).isCollision();
		}

		for (int i = 0; i < haut.size(); i++) {
			if (haut.get(i).isCollision() == true) {
				count++;
			}
		}
		for (int i = 0; i < gauche.size(); i++) {
			gauche.get(i).isCollision();
		}

		for (int i = 0; i < gauche.size(); i++) {
			if (gauche.get(i).isCollision() == true) {
				count++;
			}
		}
		for (int i = 0; i < droit.size(); i++) {
			droit.get(i).isCollision();
		}

		for (int i = 0; i < droit.size(); i++) {
			if (droit.get(i).isCollision() == true) {
				count++;
			}
		}
		for (int i = 0; i < depart.size(); i++) {
			depart.get(i).isCollision();
		}

		for (int i = 0; i < depart.size(); i++) {
			if (depart.get(i).isCollision() == true) {
				count++;
			}
		}
		for (int i = 0; i < verticale.size(); i++) {
			verticale.get(i).isCollision();
		}

		for (int i = 0; i < verticale.size(); i++) {
			if (verticale.get(i).isCollision() == true) {
				count++;
			}
		}

		if (count == horizontale.size() + verticale.size() + bas.size() + haut.size() + gauche.size() + droit.size()
				+ depart.size()) {

			if (depart.get(0).resetTout(voiture)) {
				resetTour();

			}
		}
	}

	public void resetTour() {

		for (int i = 0; i < droit.size(); i++) {
			droit.get(i).setCollision(false);

		}
		for (int i = 0; i < depart.size(); i++) {
			depart.get(i).setCollision(false);
		}

		for (int i = 0; i < horizontale.size(); i++) {
			horizontale.get(i).setCollision(false);

		}
		for (int i = 0; i < verticale.size(); i++) {
			verticale.get(i).setCollision(false);
		}

		for (int i = 0; i < gauche.size(); i++) {
			gauche.get(i).setCollision(false);
		}

		for (int i = 0; i < haut.size(); i++) {
			haut.get(i).setCollision(false);
		}

		for (int i = 0; i < gauche.size(); i++) {
			bas.get(i).setCollision(false);
		}

	}

}
