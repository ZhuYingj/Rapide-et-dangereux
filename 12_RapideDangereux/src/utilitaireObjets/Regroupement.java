package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;
import interfaces.TypePiste;

/**
 * Méthode qui regroupe tout un circuit/piste. Il y a les objets spéciaux,
 * la/les voiture(s) et les morceaux de piste.
 * 
 * @author Tan Tommy Rin
 * @author Kevin Nguyen
 */

public class Regroupement implements Dessinable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -787642010013354365L;

	/** Liste de boites mysteres **/
	private ArrayList<BlocMystere> regroupementBoiteMystere;
	/** Liste d'accelerateur **/
	private ArrayList<Accelerateur> listeAccelerateur = new ArrayList<Accelerateur>();
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

	/** Le nombre de pixels par metre **/
	private double pixelsParMetre = 1;
	/** Le nombre de boite mystere **/
	private int nombreBoiteMystere = 1;
	/** Type de piste **/
	private TypePiste type;
	/** Nombre de tour initial **/

	private int tour = 0;

	private ObjetSpecial objSpecial;
	private ObjetSpecial objSpecialTemporaire;

	private ObjetSpecial objSpecial2;

	private double tours = 0;

	private double tempsTemp;

	private boolean boutonAppuye = false;
	private boolean firstTime = false;
	private boolean objetEnCours;

	/**
	 * Méthode qui permet de créer un groupe à l'aide de paramètre
	 * 
	 * @param voiture     La voiture
	 * @param nombreBoite Le nombre de boite
	 * @param typePiste   Le type de piste
	 */
	// Tan Tommy Rin
	public Regroupement(Voiture voiture, int nombreBoite, TypePiste typePiste) {
		type = typePiste;

		this.nombreBoiteMystere = nombreBoite;

	}

	/**
	 * Méthode qui permet au regroupement d'avancer d'un pas.
	 * 
	 * @param deltaT           L'intervalle de temps(pas)
	 * @param tempsTotalEcoule Le temps total écoulé
	 */
	// Tan Tommy Rin
	public void avancerGroupe(double deltaT, double tempsTotalEcoule) {

		listePisteDeDepart.get(0).getVoiture().avancerUnPas(deltaT);
		listePisteDeDepart.get(0).getVoiture2().avancerUnPas(deltaT);

		for (int a = 0; a < regroupementBoiteMystere.size(); a++) {

			if (regroupementBoiteMystere.get(a)
					.enCollisionAvecVoiture(listePisteDeDepart.get(0).getVoiture()) == true) {

				objSpecial = regroupementBoiteMystere.get(a).getObjetSpecial();
				boutonAppuye = false;

				tempsTemp = tempsTotalEcoule;
				regroupementBoiteMystere.remove(a).getObjetSpecial();
				break;
			}
			if (regroupementBoiteMystere.get(a)
					.enCollisionAvecVoiture(listePisteDeDepart.get(0).getVoiture2()) == true) {
				boutonAppuye = false;
				objSpecial2 = regroupementBoiteMystere.get(a).getObjetSpecial();
//Peut etre faire tempsTemp une autre fois (2ieme variable pour voiture 2)
//				tempsTemp = tempsTotalEcoule;
				regroupementBoiteMystere.remove(a).getObjetSpecial();
				break;
			}

		} // fin v1 for

		// Pour v1
		if (objSpecial != null) {
			if (objSpecial.getType() == TypeObjetSpecial.CHAMPIGNON) {
				objSpecial.setTempsTemporaire(tempsTemp);

				objSpecial.fonctionChampignon(listePisteDeDepart.get(0).getVoiture(), tempsTotalEcoule);

			}
			if (objSpecial.getType() == TypeObjetSpecial.BOULEDENEIGE) {
				// Si le champignon etait en fonction et un autre objet a été pris, on remet le
				// diametre et masse aux valeurs initiales.
				listePisteDeDepart.get(0).getVoiture()
						.setMasseEnKg(listePisteDeDepart.get(0).getVoiture().getMasseEnKgInitial());
				listePisteDeDepart.get(0).getVoiture()
						.setDiametre(listePisteDeDepart.get(0).getVoiture().getDiametreInitial());

				// Si la boule de neige est lancé on avance d'un pas.

				if (boutonAppuye == true) {

					objSpecial.avancerUnPas(deltaT);
					// Si la boule de neige rentre en collision avec la voiture 2
					if (objSpecial.getBouleDeNeige()
							.collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture2()) == true) {

						tempsTemp = tempsTotalEcoule;
						objSpecial.setTempsTemporaire(tempsTemp);
						firstTime = true;
					}
					if (firstTime == true) {
						// Ici la fonction est activé si la voiture2 est en collision et on set le
						// diametre à 0 (Pour pas voir l'objet) et on set sa vitesse à 0, car sinon elle
						// peut faire des collisions avec les murs et l'objet sera mis à null
						objSpecial.setDiametreObjet(0);
						objSpecial.setVitesse(new Vecteur2D(0, 0));
						objSpecial.fonctionBouleDeNeige(listePisteDeDepart.get(0).getVoiture2(), tempsTotalEcoule);
						if (objSpecial.fonctionBouleDeNeige(listePisteDeDepart.get(0).getVoiture2(),
								tempsTotalEcoule) == false) {
							boutonAppuye = false;
							firstTime = false;
							objSpecial = null;

						}
					}

				}

			} // Fin condition pour le type "Boule de neige"
			if (objSpecial.getType() == TypeObjetSpecial.TROUNOIR) {

			}
			if (objSpecial.getType() == TypeObjetSpecial.COLLE) {

			}
		}

		// Pour v2
//		if (objSpecial2 != null)
//
//		{
//			if (objSpecial2.getType() == TypeObjetSpecial.CHAMPIGNON) {
//				objSpecial2.setTempsTemporaire(tempsTemp);
//
//				if (objSpecial2.isFonctionActive() == true) {
//
//					objSpecial2.fonctionChampignon(listePisteDeDepart.get(0).getVoiture2(), tempsTotalEcoule);
//				}
//
//			}
//			if (objSpecial2.getType() == TypeObjetSpecial.BOULEDENEIGE) {
//				objSpecial2.setTempsTemporaire(tempsTemp);
//				if (objSpecial2.isFonctionActive() == true) {
//					objSpecial2.fonctionBouleDeNeige(listePisteDeDepart.get(0).getVoiture2(), tempsTotalEcoule);
//				}
//
//			}
//			if (objSpecial.getType() == TypeObjetSpecial.TROUNOIR) {
////						objSpecial.dessiner(g2d)
//
//			}

	}

	/**
	 * Méthode qui crée les boites mystères et les place dans une liste avec un
	 * diametre fixe
	 */
	// Tan Tommy Rin
	public void creeBoiteDansListe() {
		regroupementBoiteMystere = new ArrayList<BlocMystere>();
		for (int a = 0; a < nombreBoiteMystere; a++) {
			double diametreBoite = 15;
			regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
					new Vecteur2D(listePisteHorizontale.get(a).getX(), listePisteHorizontale.get(a).getY())));

		}

	}

	/**
	 * Dessine les différents objets. (Voiture, morceaux de piste, boites mysteres,
	 * etc.) On crée des copies pour ne pas affecter l'original
	 * 
	 * @param g2d Le contexte graphique du composant sur lequel on dessine
	 */
	// Tan Tommy Rin

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

		for (int i = 0; i < listeAccelerateur.size(); i++) {
			listeAccelerateur.get(i).dessiner(g2dCopie);
		}

		for (int a = 0; a < regroupementBoiteMystere.size(); a++) {

			regroupementBoiteMystere.get(a).dessiner(g2dCopie);

		}
		if (objSpecial != null) {
			if (objSpecial.getType() == TypeObjetSpecial.COLLE) {

				objSpecial.dessiner(g2dCopie);
			}
			if (objSpecial.getType() == TypeObjetSpecial.BOULEDENEIGE) {

				if (boutonAppuye == false) {
					objSpecial.setPositionObjet(listePisteDeDepart.get(0).getVoiture().getPosition());

				}

				objSpecial.dessiner(g2dCopie);
			}

		}
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
			collisionBouleDeNeigeAvecPisteHorizontale(i);
			if (listePisteHorizontale.get(i).isCollision() == true) {
				listePisteHorizontale.get(i).setColor(Color.blue);
			} else {
				listePisteHorizontale.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVerticale.size(); i++) {
			listePisteVerticale.get(i).enCollisionAvec(voiture);
			listePisteVerticale.get(i).traverserPiste(voiture);
			collisionBouleDeNeigeAvecPisteVerticale(i);
			if (listePisteVerticale.get(i).isCollision() == true) {
				listePisteVerticale.get(i).setColor(Color.blue);
			} else {
				listePisteVerticale.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVirageBas.size(); i++) {
			listePisteVirageBas.get(i).enCollisionAvec(voiture);
			listePisteVirageBas.get(i).traverserPiste(voiture);
			collisionBouleDeNeigeAvecPisteVirageBas(i);
			if (listePisteVirageBas.get(i).isCollision() == true) {
				listePisteVirageBas.get(i).setColor(Color.blue);
			} else {
				listePisteVirageBas.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVirageGauche.size(); i++) {
			listePisteVirageGauche.get(i).enCollisionAvec(voiture);
			listePisteVirageGauche.get(i).traverserPiste(voiture);
			collisionBouleDeNeigeAvecPisteVirageGauche(i);
			if (listePisteVirageGauche.get(i).isCollision() == true) {
				listePisteVirageGauche.get(i).setColor(Color.blue);
			} else {
				listePisteVirageGauche.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVirageDroit.size(); i++) {
			listePisteVirageDroit.get(i).enCollisionAvec(voiture);
			listePisteVirageDroit.get(i).traverserPiste(voiture);
			collisionBouleDeNeigeAvecPisteVirageDroit(i);
			if (listePisteVirageDroit.get(i).isCollision() == true) {
				listePisteVirageDroit.get(i).setColor(Color.blue);
			} else {
				listePisteVirageDroit.get(i).setColor(Color.black);
			}
		}

		for (int i = 0; i < listePisteVirageHaut.size(); i++) {
			listePisteVirageHaut.get(i).enCollisionAvec(voiture);
			listePisteVirageHaut.get(i).traverserPiste(voiture);
			collisionBouleDeNeigeAvecPisteVirageHaut(i);
			if (listePisteVirageHaut.get(i).isCollision() == true) {
				listePisteVirageHaut.get(i).setColor(Color.blue);
			} else {
				listePisteVirageHaut.get(i).setColor(Color.black);
			}
		}

		listePisteDeDepart.get(0).enCollisionAvec(voiture);
		listePisteDeDepart.get(0).traverserPiste(voiture);
		collisionBouleDeNeigeAvecPisteDeDepart(0);
		if (listePisteDeDepart.get(0).isCollision() == true) {
			listePisteDeDepart.get(0).setColor(Color.blue);
		} else {
			listePisteDeDepart.get(0).setColor(Color.black);
		}

		tourComplet(voiture);
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Par Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteDeDepart(int i) {
		if (objSpecial != null) {
			if (listePisteDeDepart.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}
		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Par Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteHorizontale(int i) {
		if (objSpecial != null) {
			if (listePisteHorizontale.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}
		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Par Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVerticale(int i) {
		if (objSpecial != null) {
			if (listePisteVerticale.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}
		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Par Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVirageBas(int i) {

		if (objSpecial != null) {

			if (listePisteVirageBas.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}

		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Par Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVirageDroit(int i) {

		if (objSpecial != null) {

			if (listePisteVirageDroit.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}

		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Par Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVirageGauche(int i) {

		if (objSpecial != null) {

			if (listePisteVirageGauche.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}

		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Par Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVirageHaut(int i) {
		if (objSpecial != null) {
			if (listePisteVirageHaut.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}
		}
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

	public double getTour() {
		return tour;
	}

	public void setTour(double tour) {
		this.tours = tour;
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

	public ArrayList<Accelerateur> getListeAccelerateur() {
		return listeAccelerateur;
	}

	public void setListeAccelerateur(ArrayList<Accelerateur> listeAccelerateur) {
		this.listeAccelerateur = listeAccelerateur;
	}

	public ObjetSpecial getObjSpecial() {
		return objSpecial;
	}

	public void setObjSpecial(ObjetSpecial objSpecial) {
		this.objSpecial = objSpecial;
	}

	public ObjetSpecial getObjSpecial2() {
		return objSpecial2;
	}

	public void setObjSpecial2(ObjetSpecial objSpecial2) {
		this.objSpecial2 = objSpecial2;
	}

	public boolean isBoutonAppuye() {
		return boutonAppuye;
	}

	public void setBoutonAppuye(boolean boutonAppuye) {
		this.boutonAppuye = boutonAppuye;
	}

}
