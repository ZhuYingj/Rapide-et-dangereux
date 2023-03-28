package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;
import interfaces.TypePiste;
import pisteDeCourse.PisteItalie;
import pisteDeCourse.PisteMexique;

/**
 * Méthode qui regroupe tout un circuit/piste. Il y a les objets spéciaux,
 * la/les voiture(s) et les morceaux de piste.
 * 
 * @author Tan Tommy Rin
 * @author Kevin Nguyen
 */

public class Regroupement implements Dessinable {

	/** Liste de boites mysteres **/
	private ArrayList<BlocMystere> regroupementBoiteMystere;

//	private ArrayList<Accelerateur> listeAccelerateur = new ArrayList<Accelerateur>();
	/** Piste Horizontale **/
	private ArrayList<PisteHorizontale> listePisteHorizontale = new ArrayList<PisteHorizontale>();
	/** Piste Virage Bas **/
	private ArrayList<PisteVirageBas> listePisteVirageBas = new ArrayList<PisteVirageBas>();
	/** Piste Virage Gauche **/
	private ArrayList<PisteVirageGauche> listePisteVirageGauche = new ArrayList<PisteVirageGauche>();
	/** Piste Verticale **/
	private ArrayList<PisteVerticale> listePisteVerticale = new ArrayList<PisteVerticale>();
	/** Piste De Depart **/
	private ArrayList<PisteDeDepart> listePisteDeDepart = new ArrayList<PisteDeDepart>();
	/** Piste Virage Droit **/
	private ArrayList<PisteVirageDroit> listePisteVirageDroit = new ArrayList<PisteVirageDroit>();
	/** Piste Virage Haut **/
	private ArrayList<PisteVirageHaut> listePisteVirageHaut = new ArrayList<PisteVirageHaut>();

	/** Piste Mexique **/
	private PisteMexique pisteMexique = new PisteMexique(0, 0);
	/** Piste Italie **/
	private PisteItalie pisteItalie = new PisteItalie(0, 0);
	/** Le nombre de pixels par metre **/
	private double pixelsParMetre = 1;
	/** Le nombre de boite mystere **/
	private int nombreBoiteMystere = 1;
	/** Type de piste **/
	private TypePiste type;
	/** Nombre de tour initial **/
	private int tour = 0;

	/**
	 * Méthode qui permet de créer un groupe à l'aide de paramètre
	 * 
	 * @param voiture     La voiture
	 * @param nombreBoite Le nombre de boite
	 */
	// Par Tan Tommy Rin
	public Regroupement(Voiture voiture, int nombreBoite, TypePiste typePiste) {
		type = typePiste;

		this.nombreBoiteMystere = nombreBoite;

		creeBoiteDansListe();

	}

	/**
	 * Méthode qui permet au regroupement d'avancer d'un pas.
	 * 
	 * @param deltaT           L'intervalle de temps(pas)
	 * @param tempsTotalEcoule Le temps total écoulé
	 */
	// Par Tan Tommy Rin
	public void avancerGroupe(double deltaT, double tempsTotalEcoule) {

		listePisteDeDepart.get(0).getVoiture().avancerUnPas(deltaT);
		for (int a = 0; a < regroupementBoiteMystere.size(); a++) {
			regroupementBoiteMystere.get(a).enCollisionAvecVoiture(listePisteDeDepart.get(0).getVoiture());
			if (regroupementBoiteMystere.get(a)
					.enCollisionAvecVoiture(listePisteDeDepart.get(0).getVoiture()) == true) {
				regroupementBoiteMystere.get(a).getObjetSpecial()
						.fonctionSelonObjet(listePisteDeDepart.get(0).getVoiture(), tempsTotalEcoule);

				// Pour le champignon, lorsque la fonctionnalité du champignon est fini, nous
				// retirons la boite mystere contenant ce champignon de la liste.
				if (regroupementBoiteMystere.get(a).getObjetSpecial().getType() == TypeObjetSpecial.CHAMPIGNON) {
					if (regroupementBoiteMystere.get(a).getObjetSpecial()
							.fonctionChampignon(listePisteDeDepart.get(0).getVoiture(), tempsTotalEcoule) == false) {
						regroupementBoiteMystere.remove(a);

					}
				} // Fin condition pour le champignon

				// Pour la boule de neige
				if (regroupementBoiteMystere.get(a).getObjetSpecial().getType() == TypeObjetSpecial.BOULEDENEIGE) {
					if (regroupementBoiteMystere.get(a).getObjetSpecial().fonctionBouleDeNeige(
							pisteMexique.getDepart().get(0).getVoiture(), tempsTotalEcoule) == false) {
						regroupementBoiteMystere.remove(a);
					}
				}// Fin condition pour la boule de neige
			}
		}

	}

	/**
	 * Méthode qui crée les boites mystères et les place dans une liste avec un
	 * diametre fixe
	 */
	// Par Tan Tommy Rin
	public void creeBoiteDansListe() {
		regroupementBoiteMystere = new ArrayList<BlocMystere>();
		for (int a = 0; a < nombreBoiteMystere; a++) {
			double diametreBoite = 15;
			regroupementBoiteMystere.add(new BlocMystere(diametreBoite, new Vecteur2D(150 * (a + 1), 5 * (a + 1))));
		}
	}

	/**
	 * Dessine les différents objets. (Voiture, morceaux de piste, boites mysteres,
	 * etc.) On crée des copies pour ne pas affecter l'original
	 * 
	 * @param g2d Le contexte graphique du composant sur lequel on dessine
	 */
	// Par Tan Tommy Rin

	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dCopie = (Graphics2D) g2d.create();
		g2dCopie.scale(pixelsParMetre, pixelsParMetre);
		for (int i = 0; i < listePisteVirageBas.size(); i++) {
			listePisteVirageBas.get(i).dessiner(g2dCopie);
		}

		for (int i = 0; i < listePisteHorizontale.size(); i++) {
			listePisteHorizontale.get(i).dessiner(g2dCopie);
		}

		for (int i = 0; i < listePisteVerticale.size(); i++) {
			listePisteVerticale.get(i).dessiner(g2dCopie);
		}

		for (int i = 0; i < listePisteVirageGauche.size(); i++) {
			listePisteVirageGauche.get(i).dessiner(g2dCopie);
		}

		for (int i = 0; i < listePisteVirageDroit.size(); i++) {
			listePisteVirageDroit.get(i).dessiner(g2dCopie);
		}

		for (int i = 0; i < listePisteVirageHaut.size(); i++) {
			listePisteVirageHaut.get(i).dessiner(g2dCopie);
		}

		listePisteDeDepart.get(0).dessiner(g2dCopie);

		for (int a = 0; a < regroupementBoiteMystere.size(); a++) {

			regroupementBoiteMystere.get(a).dessiner(g2dCopie);

		}

	}

	public PisteItalie getPisteItalie() {
		return pisteItalie;
	}

	public void setPisteItalie(PisteItalie pisteItalie) {
		this.pisteItalie = pisteItalie;
	}

	public PisteMexique getPisteMexique() {
		return pisteMexique;
	}

	public void setPisteMexique(PisteMexique pisteMexique) {
		this.pisteMexique = pisteMexique;
	}

	public ArrayList<BlocMystere> getRegroupementBoiteMystere() {
		return regroupementBoiteMystere;
	}

	public void setRegroupementObjet(ArrayList<BlocMystere> regroupementBoiteMystere) {
		this.regroupementBoiteMystere = regroupementBoiteMystere;
	}

	public double getPixelsParMetre() {
		return pixelsParMetre;
	}

	public void setPixelsParMetre(double pixelsParMetre) {
		this.pixelsParMetre = pixelsParMetre;

	}

	public TypePiste getType() {
		return type;
	}

	public void setType(TypePiste type) {
		this.type = type;
	}

	public ArrayList<PisteHorizontale> getListePisteHorizontale() {
		return listePisteHorizontale;
	}

	public void setListePisteHorizontale(ArrayList<PisteHorizontale> listePisteHorizontale) {
		this.listePisteHorizontale = listePisteHorizontale;
	}

	public ArrayList<PisteVirageBas> getListePisteVirageBas() {
		return listePisteVirageBas;
	}

	public void setListePisteVirageBas(ArrayList<PisteVirageBas> listePisteVirageBas) {
		this.listePisteVirageBas = listePisteVirageBas;
	}

	public ArrayList<PisteVirageGauche> getListePisteVirageGauche() {
		return listePisteVirageGauche;
	}

	public void setListePisteVirageGauche(ArrayList<PisteVirageGauche> listePisteVirageGauche) {
		this.listePisteVirageGauche = listePisteVirageGauche;
	}

	public ArrayList<PisteVerticale> getListePisteVerticale() {
		return listePisteVerticale;
	}

	public void setListePisteVerticale(ArrayList<PisteVerticale> listePisteVerticale) {
		this.listePisteVerticale = listePisteVerticale;
	}

	public ArrayList<PisteDeDepart> getListePisteDeDepart() {
		return listePisteDeDepart;
	}

	public void setListePisteDeDepart(ArrayList<PisteDeDepart> listePisteDeDepart) {
		this.listePisteDeDepart = listePisteDeDepart;
	}

	public ArrayList<PisteVirageDroit> getListePisteVirageDroit() {
		return listePisteVirageDroit;
	}

	public void setListePisteVirageDroit(ArrayList<PisteVirageDroit> listePisteVirageDroit) {
		this.listePisteVirageDroit = listePisteVirageDroit;
	}

	public ArrayList<PisteVirageHaut> getListePisteVirageHaut() {
		return listePisteVirageHaut;
	}

	public void setListePisteVirageHaut(ArrayList<PisteVirageHaut> listePisteVirageHaut) {
		this.listePisteVirageHaut = listePisteVirageHaut;
	}

	/**
	 * Gérer les collisions avec chaque morceau de piste
	 * 
	 * @param voiture La voiture controllée
	 */
	// Kevin Nguyen

	public void enCollisionAvec(Voiture voiture) {

		for (int i = 0; i < listePisteHorizontale.size(); i++) {
			listePisteHorizontale.get(i).enCollisionAvec(voiture);
			listePisteHorizontale.get(i).traverserPiste(voiture);
			if (listePisteHorizontale.get(i).isCollision() == true) {
				listePisteHorizontale.get(i).setColor(Color.blue);
			} else {
				listePisteHorizontale.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVerticale.size(); i++) {
			listePisteVerticale.get(i).enCollisionAvec(voiture);
			listePisteVerticale.get(i).traverserPiste(voiture);
			if (listePisteVerticale.get(i).isCollision() == true) {
				listePisteVerticale.get(i).setColor(Color.blue);
			} else {
				listePisteVerticale.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVirageBas.size(); i++) {
			listePisteVirageBas.get(i).enCollisionAvec(voiture);
			listePisteVirageBas.get(i).traverserPiste(voiture);
			if (listePisteVirageBas.get(i).isCollision() == true) {
				listePisteVirageBas.get(i).setColor(Color.blue);
			} else {
				listePisteVirageBas.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVirageGauche.size(); i++) {
			listePisteVirageGauche.get(i).enCollisionAvec(voiture);
			listePisteVirageGauche.get(i).traverserPiste(voiture);
			if (listePisteVirageGauche.get(i).isCollision() == true) {
				listePisteVirageGauche.get(i).setColor(Color.blue);
			} else {
				listePisteVirageGauche.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVirageDroit.size(); i++) {
			listePisteVirageDroit.get(i).enCollisionAvec(voiture);
			listePisteVirageDroit.get(i).traverserPiste(voiture);
			if (listePisteVirageDroit.get(i).isCollision() == true) {
				listePisteVirageDroit.get(i).setColor(Color.blue);
			} else {
				listePisteVirageDroit.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVirageHaut.size(); i++) {
			listePisteVirageHaut.get(i).enCollisionAvec(voiture);
			listePisteVirageHaut.get(i).traverserPiste(voiture);
			if (listePisteVirageHaut.get(i).isCollision() == true) {
				listePisteVirageHaut.get(i).setColor(Color.blue);
			} else {
				listePisteVirageHaut.get(i).setColor(Color.black);
			}
		}

		listePisteDeDepart.get(0).enCollisionAvec(voiture);
		listePisteDeDepart.get(0).traverserPiste(voiture);
		if (listePisteDeDepart.get(0).isCollision() == true) {
			listePisteDeDepart.get(0).setColor(Color.blue);
		} else {
			listePisteDeDepart.get(0).setColor(Color.black);
		}

		tourComplet(voiture);
	}

	/**
	 * Méthode permettant de savoir si la voiture à fait un tour complet en sachant
	 * si elle est passée par chaque morceau de piste
	 * 
	 * @param voiture La voiture controllée
	 */
	// Kevin Nguyen
	public void tourComplet(Voiture voiture) {
		int count = 0;
		for (int i = 0; i < listePisteHorizontale.size(); i++) {
			if (listePisteHorizontale.get(i).isCollision() == true) {
				count++;
			}
		}

		for (int i = 0; i < listePisteVirageBas.size(); i++) {
			listePisteVirageBas.get(i).isCollision();
		}

		for (int i = 0; i < listePisteVirageBas.size(); i++) {
			if (listePisteVirageBas.get(i).isCollision() == true) {
				count++;
			}
		}

		for (int i = 0; i < listePisteVirageHaut.size(); i++) {
			listePisteVirageHaut.get(i).isCollision();
		}

		for (int i = 0; i < listePisteVirageHaut.size(); i++) {
			if (listePisteVirageHaut.get(i).isCollision() == true) {
				count++;
			}
		}
		for (int i = 0; i < listePisteVirageGauche.size(); i++) {
			listePisteVirageGauche.get(i).isCollision();
		}

		for (int i = 0; i < listePisteVirageGauche.size(); i++) {
			if (listePisteVirageGauche.get(i).isCollision() == true) {
				count++;
			}
		}
		for (int i = 0; i < listePisteVirageDroit.size(); i++) {
			listePisteVirageDroit.get(i).isCollision();
		}

		for (int i = 0; i < listePisteVirageDroit.size(); i++) {
			if (listePisteVirageDroit.get(i).isCollision() == true) {
				count++;
			}
		}
		for (int i = 0; i < listePisteDeDepart.size(); i++) {
			listePisteDeDepart.get(i).isCollision();
		}

		for (int i = 0; i < listePisteDeDepart.size(); i++) {
			if (listePisteDeDepart.get(i).isCollision() == true) {
				count++;
			}
		}
		for (int i = 0; i < listePisteVerticale.size(); i++) {
			listePisteVerticale.get(i).isCollision();
		}

		for (int i = 0; i < listePisteVerticale.size(); i++) {
			if (listePisteVerticale.get(i).isCollision() == true) {
				count++;
			}
		}

		if (count == listePisteHorizontale.size() + listePisteVerticale.size() + listePisteVirageBas.size()
				+ listePisteVirageHaut.size() + listePisteVirageGauche.size() + listePisteVirageDroit.size()
				+ listePisteDeDepart.size()) {

			if (listePisteDeDepart.get(0).resetTout(voiture)) {
				resetTour();

			}
		}
	}

	/**
	 * Méthode permettant de compter le nombre de tour fait par une voiture et
	 * réinitialise le boolean de passage de la voiture d'un morceau de piste
	 */
	// Kevin Nguyen
	public void resetTour() {

		for (int i = 0; i < listePisteVirageDroit.size(); i++) {
			listePisteVirageDroit.get(i).setCollision(false);
			listePisteVirageDroit.get(i).setColor(Color.black);

		}
		for (int i = 0; i < listePisteDeDepart.size(); i++) {
			listePisteDeDepart.get(i).setCollision(false);
			listePisteDeDepart.get(i).setColor(Color.black);
		}

		for (int i = 0; i < listePisteHorizontale.size(); i++) {
			listePisteHorizontale.get(i).setCollision(false);
			listePisteHorizontale.get(i).setColor(Color.black);

		}
		for (int i = 0; i < listePisteVerticale.size(); i++) {
			listePisteVerticale.get(i).setCollision(false);
			listePisteVerticale.get(i).setColor(Color.black);
		}

		for (int i = 0; i < listePisteVirageGauche.size(); i++) {
			listePisteVirageGauche.get(i).setCollision(false);
			listePisteVirageGauche.get(i).setColor(Color.black);
		}

		for (int i = 0; i < listePisteVirageHaut.size(); i++) {
			listePisteVirageHaut.get(i).setCollision(false);
			listePisteVirageHaut.get(i).setColor(Color.black);
		}

		for (int i = 0; i < listePisteVirageBas.size(); i++) {
			listePisteVirageBas.get(i).setCollision(false);
			listePisteVirageBas.get(i).setColor(Color.black);
		}
		tour++;
	}

}
