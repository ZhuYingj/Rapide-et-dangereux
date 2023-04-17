package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;

import application.OutilsImage;
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
	/** Liste Smoke **/
	private ArrayList<Fumee> listeFumee = new ArrayList<Fumee>();

	/** Le nombre de pixels par metre **/
	private double pixelsParMetre = 1;
	/** Le nombre de boite mystere **/
	private int nombreBoiteMystere = 1;
	/** Type de piste **/
	private TypePiste type;
	/** Nombre de tour initial **/
	private int tour = 0;
	/** Notre premier objet special **/
	private ObjetSpecial objSpecial;
	/** Notre deuxieme objet special **/
	private ObjetSpecial objSpecial2;
	private double nombreToursAFaire = 1; ////////////////////////////////////

	private double tours = 0;

	/** Le temps temporaire pour l'effet de l'objet **/
	private double tempsTemp;
	/** Le temps temporaire pour l'effet de l'objet **/
	private double tempsTemp2;

	private boolean boutonAppuye = false;
	private boolean boutonAppuye2 = false;

	private boolean collePisteHorizontale = false;
	private boolean collePisteVerticale = false;
	private boolean collePisteVirageBas = false;
	private boolean collePisteVirageDroit = false;
	private boolean collePisteVirageHaut = false;
	private boolean collePisteVirageGauche = false;
	private boolean collePisteDepart = false;

	private boolean collePisteHorizontale2 = false;
	private boolean collePisteVerticale2 = false;
	private boolean collePisteVirageBas2 = false;
	private boolean collePisteVirageDroit2 = false;
	private boolean collePisteVirageHaut2 = false;
	private boolean collePisteVirageGauche2 = false;
	private boolean collePisteDepart2 = false;

	private boolean firstTime = false;
	private boolean firstTime2 = false;

	private boolean enContactAvecColle = false;
	private boolean enContactAvecColle2 = false;
	/** Pour savoir si la voiture 1 accélère **/
	private boolean toucheHautActive = false;
	/** Pour savoir si la voiture 2 accélère **/
	private boolean toucheWActive = false;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteHorizontale = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVerticale = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVirageBas = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVirageGauche = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVirageHaut = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVirageDroit = 0;

	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteHorizontale2 = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVerticale2 = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVirageBas2 = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVirageGauche2 = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVirageHaut2 = 0;
	/** Le morceau de piste courant de la liste **/
	private int pisteCouranteVirageDroit2 = 0;

	private DessinCollisionBouleDeNeige snowball;
	/** support pour lancer des evenements de type PropertyChange **/
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private transient Graphics2D g2d;

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 */
	// Tan Tommy Rin
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

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
		if (regroupementBoiteMystere.size() != 0) {
			for (int a = 0; a < regroupementBoiteMystere.size(); a++) {

				if (regroupementBoiteMystere.get(a)
						.enCollisionAvecVoiture(listePisteDeDepart.get(0).getVoiture()) == true) {
					// Si le champignon etait en fonction et un autre objet a été pris, on remet le
					// diametre et masse aux valeurs initiales.
					listePisteDeDepart.get(0).getVoiture()
							.setMasseEnKg(listePisteDeDepart.get(0).getVoiture().getMasseEnKgInitial());
					listePisteDeDepart.get(0).getVoiture()
							.setDiametre(listePisteDeDepart.get(0).getVoiture().getDiametreInitial());
					objSpecial = regroupementBoiteMystere.get(a).getObjetSpecial();
					boutonAppuye = false;
					objSpecial.setTempsTemporaire(tempsTemp);
					tempsTemp = tempsTotalEcoule;
					regroupementBoiteMystere.remove(a).getObjetSpecial();

					enContactAvecColle = false;
					collePisteHorizontale = false;
					collePisteVerticale = false;
					collePisteVirageBas = false;
					collePisteVirageDroit = false;
					collePisteVirageHaut = false;
					collePisteVirageGauche = false;
					collePisteDepart = false;

					break;
				}

				if (regroupementBoiteMystere.get(a)
						.enCollisionAvecVoiture(listePisteDeDepart.get(0).getVoiture2()) == true) {
					// Si le champignon etait en fonction et un autre objet a été pris, on remet le
					// diametre et masse aux valeurs initiales.
					listePisteDeDepart.get(0).getVoiture()
							.setMasseEnKg(listePisteDeDepart.get(0).getVoiture2().getMasseEnKgInitial());
					listePisteDeDepart.get(0).getVoiture()
							.setDiametre(listePisteDeDepart.get(0).getVoiture2().getDiametreInitial());
					boutonAppuye2 = false;
					objSpecial2 = regroupementBoiteMystere.get(a).getObjetSpecial();
					objSpecial2.setTempsTemporaire(tempsTemp2);
					tempsTemp2 = tempsTotalEcoule;
					regroupementBoiteMystere.remove(a).getObjetSpecial();

					enContactAvecColle2 = false;
					collePisteHorizontale2 = false;
					collePisteVerticale2 = false;
					collePisteVirageBas2 = false;
					collePisteVirageDroit2 = false;
					collePisteVirageHaut2 = false;
					collePisteVirageGauche2 = false;
					collePisteDepart2 = false;
					break;
				}

			} // Fin for loop
		}

		if (regroupementBoiteMystere.size() == 0) {
			regroupementBoiteMystere = null;

			creeBoiteDansListe();
		}
		fumeeFonction();
		accelerateurFonction();
		fonctionDesObjetsPossibles(tempsTotalEcoule, deltaT);
		placerColleBonMorceauPisteVoiture1(listePisteDeDepart.get(0).getVoiture());
		placerColleBonMorceauPisteVoiture2(listePisteDeDepart.get(0).getVoiture2());
	}

	/**
	 * Méthode qui permet de gérer la fonction des objets possibles dans le bloc
	 * mystère. Cette méthode est activé à chaque avancement du groupe.
	 * 
	 * @param tempsTotalEcoule Le temps total écoulé depuis la simulation
	 * @param deltaT           Le pas
	 */
	// Tan Tommy Rin
	public void fonctionDesObjetsPossibles(double tempsTotalEcoule, double deltaT) {

		// Pour v1
		if (objSpecial != null) {

			if (objSpecial.getType() == TypeObjetSpecial.CHAMPIGNON) {

				objSpecial.setTempsTemporaire(tempsTemp);

				objSpecial.fonctionChampignon(listePisteDeDepart.get(0).getVoiture(), tempsTotalEcoule);

			} else if (objSpecial.getType() == TypeObjetSpecial.BOULEDENEIGE) {

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

			else if (objSpecial.getType() == TypeObjetSpecial.TROUNOIR) {

				// VOITURE 1 ET 2 POUR OBJET1
				if (tempsTemp + 8 > tempsTotalEcoule) {
					if (objSpecial.getTrouNoir().collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture()) == true) {

						Vecteur2D forceAttraction = objSpecial.fonctionTrouNoir(listePisteDeDepart.get(0).getVoiture());
						pcs.firePropertyChange("attractionV1X", 0, forceAttraction.getX());
						pcs.firePropertyChange("attractionV1Y", 0, forceAttraction.getY());
					} else {
						pcs.firePropertyChange("attractionV1Reset", 0, -1);

					}
					if (objSpecial.getTrouNoir()
							.collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture2()) == true) {
						Vecteur2D forceAttraction = objSpecial
								.fonctionTrouNoir(listePisteDeDepart.get(0).getVoiture2());
						objSpecial.fonctionTrouNoir(listePisteDeDepart.get(0).getVoiture2());
						pcs.firePropertyChange("attractionV2X", 0, forceAttraction.getX());
						pcs.firePropertyChange("attractionV2Y", 0, forceAttraction.getY());
					} else {
						pcs.firePropertyChange("attractionV2Reset", 0, -1);

					}

				} else {
					objSpecial = null;
					pcs.firePropertyChange("attractionV1Reset", 0, -1);
					pcs.firePropertyChange("attractionV2Reset", 0, -1);
				}

			} else if (objSpecial.getType() == TypeObjetSpecial.COLLE) {

				// Affecte les 2 voitures
				if (objSpecial.getColle().collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture2()) == true) {

					objSpecial.fonctionColle(listePisteDeDepart.get(0).getVoiture2());

				}

				if (objSpecial.getColle().collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture()) == true) {

					objSpecial.fonctionColle(listePisteDeDepart.get(0).getVoiture());

				}
			}
		} // Fin pour v1
			// Pour v2
		if (objSpecial2 != null) {

			if (objSpecial2.getType() == TypeObjetSpecial.CHAMPIGNON) {
				objSpecial2.setTempsTemporaire(tempsTemp2);

				objSpecial2.fonctionChampignon(listePisteDeDepart.get(0).getVoiture2(), tempsTotalEcoule);

			} else if (objSpecial2.getType() == TypeObjetSpecial.BOULEDENEIGE) {

				// Si la boule de neige est lancé on avance d'un pas.

				if (boutonAppuye2 == true) {

					objSpecial2.avancerUnPas(deltaT);
					// Si la boule de neige rentre en collision avec la voiture1
					if (objSpecial2.getBouleDeNeige()
							.collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture()) == true) {

						tempsTemp2 = tempsTotalEcoule;
						objSpecial2.setTempsTemporaire(tempsTemp2);
						firstTime2 = true;
					}
					if (firstTime2 == true) {
						// Ici la fonction est activé si la voiture1 est en collision et on set le
						// diametre à 0 (Pour pas voir l'objet) et on set sa vitesse à 0, car sinon elle
						// peut faire des collisions avec les murs et l'objet sera mis à null
						objSpecial2.setDiametreObjet(0);
						objSpecial2.setVitesse(new Vecteur2D(0, 0));
						objSpecial2.fonctionBouleDeNeige(listePisteDeDepart.get(0).getVoiture(), tempsTotalEcoule);
						if (objSpecial2.fonctionBouleDeNeige(listePisteDeDepart.get(0).getVoiture(),
								tempsTotalEcoule) == false) {
							boutonAppuye2 = false;
							firstTime2 = false;
							objSpecial2 = null;

						}
					}

				}

			} // Fin condition pour le type "Boule de neige"
			else if (objSpecial2.getType() == TypeObjetSpecial.TROUNOIR) {

				// VOITURE 1 ET 2 POUR OBJET1
				if (tempsTemp2 + 8 > tempsTotalEcoule) {

					if (objSpecial2.getTrouNoir()
							.collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture()) == true) {
						objSpecial2.fonctionTrouNoir(listePisteDeDepart.get(0).getVoiture());
						Vecteur2D forceAttraction = objSpecial2
								.fonctionTrouNoir(listePisteDeDepart.get(0).getVoiture());
						pcs.firePropertyChange("attractionV1X", 0, forceAttraction.getX());
						pcs.firePropertyChange("attractionV1Y", 0, forceAttraction.getY());
					} else {
						pcs.firePropertyChange("attractionV1Reset", 0, -1);

					}
					if (objSpecial2.getTrouNoir()
							.collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture2()) == true) {
						objSpecial2.fonctionTrouNoir(listePisteDeDepart.get(0).getVoiture2());
						Vecteur2D forceAttraction = objSpecial2
								.fonctionTrouNoir(listePisteDeDepart.get(0).getVoiture2());
						pcs.firePropertyChange("attractionV2X", 0, forceAttraction.getX());
						pcs.firePropertyChange("attractionV2Y", 0, forceAttraction.getY());
					} else {
						pcs.firePropertyChange("attractionV2Reset", 0, -1);

					}

				} else {
					objSpecial2 = null;
					pcs.firePropertyChange("attractionV1Reset", 0, -1);
					pcs.firePropertyChange("attractionV2Reset", 0, -1);
				}

			} else if (objSpecial2.getType() == TypeObjetSpecial.COLLE) {

				// Affecte les 2 voitures
				if (objSpecial2.getColle().collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture2()) == true) {

//					objSpecial2.fonctionColle(listePisteDeDepart.get(0).getVoiture2());

				}

				if (objSpecial2.getColle().collisionDeLaVoiture(listePisteDeDepart.get(0).getVoiture()) == true) {

//					objSpecial2.fonctionColle(listePisteDeDepart.get(0).getVoiture());

				}
			}
		} // Fin pour v2
	}

	/**
	 * Méthode qui crée les boites mystères à des places au hasard et les place dans
	 * une liste avec un diametre fixe
	 */
	// Tan Tommy Rin
	public void creeBoiteDansListe() {
		regroupementBoiteMystere = new ArrayList<BlocMystere>();

		// Variable pour ne pas placer une boite mystere au meme exact endroit
		int petiteDeviation = 0;
		for (int a = 0; a < nombreBoiteMystere; a++) {
			if (petiteDeviation > 30) {
				petiteDeviation = 0;
			}
			double nombreRandom = Math.random();
			double diametreBoite = 15;
			// 10 % que ce soit dans la piste de départ
			if (nombreRandom < 0.1) {

				regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
						new Vecteur2D(
								listePisteDeDepart.get(0).getX() + petiteDeviation
										+ listePisteDeDepart.get(0).getTaillePiste(),
								listePisteDeDepart.get(0).getY() + petiteDeviation
										+ listePisteDeDepart.get(0).getTaillePiste() / 2)));

			}
			// 20 % que ce soit dans une des pistes horizontales
			else if (nombreRandom < 0.3) {

				if (listePisteHorizontale.size() != 0) {
					int nombreRandomPiste = ((int) (Math.random() * (listePisteHorizontale.size())));
					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(
									listePisteHorizontale.get(nombreRandomPiste).getX() + petiteDeviation
											+ listePisteHorizontale.get(nombreRandomPiste).getTaillePiste() / 4,
									listePisteHorizontale.get(nombreRandomPiste).getY() + petiteDeviation
											+ listePisteHorizontale.get(nombreRandomPiste).getTaillePiste() / 2)));

				} else {

					regroupementBoiteMystere
							.add(new BlocMystere(diametreBoite, new Vecteur2D(listePisteDeDepart.get(0).getX() / 2 + 40,
									listePisteDeDepart.get(0).getY() / 2 + 40)));
				}

			}
			// 15 % que ce soit dans une des pistes verticales
			else if (nombreRandom < 0.45) {

				if (listePisteVerticale.size() != 0) {
					int nombreRandomPiste = ((int) (Math.random() * (listePisteVerticale.size())));

					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(
									listePisteVerticale.get(nombreRandomPiste).getX() + petiteDeviation
											+ listePisteVerticale.get(nombreRandomPiste).getTaillePiste() / 4,
									listePisteVerticale.get(nombreRandomPiste).getY() + petiteDeviation
											+ listePisteVerticale.get(nombreRandomPiste).getTaillePiste() / 2)));
				} else {

					regroupementBoiteMystere
							.add(new BlocMystere(diametreBoite, new Vecteur2D(listePisteDeDepart.get(0).getX() / 2 - 30,
									listePisteDeDepart.get(0).getY() / 2 - 30)));
				}

			}
			// 15 % que ce soit dans une des pistes de virage droit
			else if (nombreRandom < 0.60) {

				if (listePisteVirageDroit.size() != 0) {
					int nombreRandomPiste = ((int) (Math.random() * (listePisteVirageDroit.size())));

					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(
									listePisteVirageDroit.get(nombreRandomPiste).getX() + petiteDeviation
											+ listePisteVirageDroit.get(nombreRandomPiste).getTaillePiste() / 4,
									listePisteVirageDroit.get(nombreRandomPiste).getY() + petiteDeviation
											- listePisteVirageDroit.get(nombreRandomPiste).getTaillePiste() / 2)));
				} else {

					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(listePisteDeDepart.get(0).getX() / 2, listePisteDeDepart.get(0).getY() / 2)));
				}

			}
			// 15 % que ce soit dans une piste de virage gauche
			else if (nombreRandom < 0.75) {

				if (listePisteVirageGauche.size() != 0) {
					int nombreRandomPiste = ((int) (Math.random() * (listePisteVirageGauche.size())));

					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(
									listePisteVirageGauche.get(nombreRandomPiste).getX() + petiteDeviation
											+ listePisteVirageGauche.get(nombreRandomPiste).getTaillePiste() / 4,
									listePisteVirageGauche.get(nombreRandomPiste).getY() + petiteDeviation
											+ listePisteVirageGauche.get(nombreRandomPiste).getTaillePiste() / 2)));
				} else {

					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(listePisteDeDepart.get(0).getX() / 2, listePisteDeDepart.get(0).getY() / 2)));
				}
			}
			// 15 % que ce soit dans une piste de virage bas
			else if (nombreRandom < 0.9) {

				if (listePisteVirageBas.size() != 0) {
					int nombreRandomPiste = ((int) (Math.random() * (listePisteVirageBas.size())));

					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(
									listePisteVirageBas.get(nombreRandomPiste).getX() + petiteDeviation
											+ listePisteVirageBas.get(nombreRandomPiste).getTaillePiste() / 4,
									listePisteVirageBas.get(nombreRandomPiste).getY() + petiteDeviation
											+ listePisteVirageBas.get(nombreRandomPiste).getTaillePiste() / 2)));
				} else {

					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(listePisteDeDepart.get(0).getX() / 2, listePisteDeDepart.get(0).getY() / 2)));
				}
			}
			// 10 % que ce soit dans une piste de virage haut
			else {

				if (listePisteVirageHaut.size() != 0) {
					int nombreRandomPiste = ((int) (Math.random() * (listePisteVirageHaut.size())));

					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(
									listePisteVirageHaut.get(nombreRandomPiste).getX() + petiteDeviation
											+ listePisteVirageHaut.get(nombreRandomPiste).getTaillePiste() / 4,
									listePisteVirageHaut.get(nombreRandomPiste).getY() + petiteDeviation
											- listePisteVirageHaut.get(nombreRandomPiste).getTaillePiste() / 2)));
				} else {

					regroupementBoiteMystere.add(new BlocMystere(diametreBoite,
							new Vecteur2D(listePisteDeDepart.get(0).getX() / 2, listePisteDeDepart.get(0).getY() / 2)));
				}
			}
			petiteDeviation = petiteDeviation + 15;

		}

	}

	/**
	 * Méthode qui permet de placer la colle au bon morceau de piste.
	 * 
	 * @param voiture Voiture qui prend l'objet de type colle.
	 */
//Tan Tommy Rin
	public void placerColleBonMorceauPisteVoiture2(Voiture voiture) {
		for (int i = 0; i < listePisteHorizontale.size(); i++) {
			listePisteHorizontale.get(i).collisionColle(voiture);
			if (objSpecial2 != null && objSpecial2.getType() == TypeObjetSpecial.COLLE
					&& listePisteHorizontale.get(i).isEnContactAvecColle() && enContactAvecColle2 == false) {
				pisteCouranteHorizontale2 = i;
				collePisteHorizontale2 = true;
				enContactAvecColle2 = true;
			}

		}
		if (collePisteHorizontale2 == true) {

			objSpecial2.setPositionObjet(
					new Vecteur2D(listePisteHorizontale.get(pisteCouranteHorizontale2).getFormeAire().getX(),
							listePisteHorizontale.get(pisteCouranteHorizontale2).getFormeAire().getY()));

		}

		for (int i = 0; i < listePisteVerticale.size(); i++) {
			listePisteVerticale.get(i).collisionColle(voiture);
			if (objSpecial2 != null && objSpecial2.getType() == TypeObjetSpecial.COLLE
					&& listePisteVerticale.get(i).isEnContactAvecColle() == true && enContactAvecColle2 == false) {
				pisteCouranteVerticale2 = i;
				collePisteVerticale2 = true;
				enContactAvecColle2 = true;
			}
		}
		if (collePisteVerticale2 == true) {

			objSpecial2.setPositionObjet(
					new Vecteur2D(listePisteVerticale.get(pisteCouranteVerticale2).getFormeAire().getX(),
							listePisteVerticale.get(pisteCouranteVerticale2).getFormeAire().getY()));

		}
		for (int i = 0; i < listePisteVirageBas.size(); i++) {
			listePisteVirageBas.get(i).collisionColle(voiture);
			if (objSpecial2 != null && objSpecial2.getType() == TypeObjetSpecial.COLLE && enContactAvecColle2 == false
					&& listePisteVirageBas.get(i).isEnContactAvecColle() == true) {
				pisteCouranteVirageBas2 = i;
				collePisteVirageBas2 = true;
				enContactAvecColle2 = true;
			}
		}

		if (collePisteVirageBas2 == true) {

			objSpecial2.setPositionObjet(
					new Vecteur2D(listePisteVirageBas.get(pisteCouranteVirageBas2).getFormeAire().getX(),
							listePisteVirageBas.get(pisteCouranteVirageBas2).getFormeAire().getY()));

		}
		for (int i = 0; i < listePisteVirageGauche.size(); i++) {
			listePisteVirageGauche.get(i).collisionColle(voiture);
			if (objSpecial2 != null && objSpecial2.getType() == TypeObjetSpecial.COLLE && enContactAvecColle2 == false
					&& listePisteVirageGauche.get(i).isEnContactAvecColle() == true) {
				pisteCouranteVirageGauche2 = i;
				collePisteVirageGauche2 = true;
				enContactAvecColle2 = true;
			}
		}
		if (collePisteVirageGauche2 == true) {

			objSpecial2.setPositionObjet(
					new Vecteur2D(listePisteVirageGauche.get(pisteCouranteVirageGauche2).getFormeAire().getX(),
							listePisteVirageGauche.get(pisteCouranteVirageGauche2).getFormeAire().getY()));

		}

		for (int i = 0; i < listePisteVirageDroit.size(); i++) {
			listePisteVirageDroit.get(i).collisionColle(voiture);
			if (objSpecial2 != null && objSpecial2.getType() == TypeObjetSpecial.COLLE && enContactAvecColle2 == false
					&& listePisteVirageDroit.get(i).isEnContactAvecColle() == true) {
				pisteCouranteVirageDroit2 = i;
				collePisteVirageDroit2 = true;
				enContactAvecColle2 = true;
			}
		}
		if (collePisteVirageDroit2 == true) {

			objSpecial2.setPositionObjet(
					new Vecteur2D(listePisteVirageDroit.get(pisteCouranteVirageDroit2).getFormeAire().getX(),
							listePisteVirageDroit.get(pisteCouranteVirageDroit2).getFormeAire().getY()));

		}
		for (int i = 0; i < listePisteVirageHaut.size(); i++) {
			listePisteVirageHaut.get(i).collisionColle(voiture);
			if (objSpecial2 != null && objSpecial2.getType() == TypeObjetSpecial.COLLE && enContactAvecColle2 == false
					&& listePisteVirageHaut.get(i).isEnContactAvecColle() == true) {
				pisteCouranteVirageHaut2 = i;
				collePisteVirageHaut2 = true;
				enContactAvecColle2 = true;
			}
		}
		if (collePisteVirageHaut2 == true) {

			objSpecial2.setPositionObjet(
					new Vecteur2D(listePisteVirageHaut.get(pisteCouranteVirageHaut2).getFormeAire().getX(),
							listePisteVirageHaut.get(pisteCouranteVirageHaut2).getFormeAire().getY()));

		}
		listePisteDeDepart.get(0).collisionColle(voiture);
		if (objSpecial2 != null && objSpecial2.getType() == TypeObjetSpecial.COLLE && enContactAvecColle2 == false
				&& listePisteDeDepart.get(0).isEnContactAvecColle() == true) {

			enContactAvecColle2 = true;
			collePisteDepart2 = true;
		}
		if (collePisteDepart2 == true) {
			if (objSpecial2 != null) {
				objSpecial2.setPositionObjet(new Vecteur2D(listePisteDeDepart.get(0).getFormeAire().getX(),
						listePisteDeDepart.get(0).getFormeAire().getY()));
			}

		}
	}

	/**
	 * Méthode qui permet de placer la colle au bon morceau de piste.
	 * 
	 * @param voiture Voiture qui prend l'objet de type colle.
	 */
//Tan Tommy Rin
	public void placerColleBonMorceauPisteVoiture1(Voiture voiture) {

		for (int i = 0; i < listePisteHorizontale.size(); i++) {
			listePisteHorizontale.get(i).collisionColle(voiture);
			if (objSpecial != null && objSpecial.getType() == TypeObjetSpecial.COLLE
					&& listePisteHorizontale.get(i).isEnContactAvecColle() && enContactAvecColle == false) {
				pisteCouranteHorizontale = i;
				collePisteHorizontale = true;
				enContactAvecColle = true;
			}

		}
		if (collePisteHorizontale == true) {

			objSpecial.setPositionObjet(
					new Vecteur2D(listePisteHorizontale.get(pisteCouranteHorizontale).getFormeAire().getX(),
							listePisteHorizontale.get(pisteCouranteHorizontale).getFormeAire().getY()));

		}

		for (int i = 0; i < listePisteVerticale.size(); i++) {
			listePisteVerticale.get(i).collisionColle(voiture);
			if (objSpecial != null && objSpecial.getType() == TypeObjetSpecial.COLLE
					&& listePisteVerticale.get(i).isEnContactAvecColle() == true && enContactAvecColle == false) {
				pisteCouranteVerticale = i;
				collePisteVerticale = true;
				enContactAvecColle = true;
			}
		}
		if (collePisteVerticale == true) {

			objSpecial.setPositionObjet(
					new Vecteur2D(listePisteVerticale.get(pisteCouranteVerticale).getFormeAire().getX(),
							listePisteVerticale.get(pisteCouranteVerticale).getFormeAire().getY()));

		}
		for (int i = 0; i < listePisteVirageBas.size(); i++) {
			listePisteVirageBas.get(i).collisionColle(voiture);
			if (objSpecial != null && objSpecial.getType() == TypeObjetSpecial.COLLE && enContactAvecColle == false
					&& listePisteVirageBas.get(i).isEnContactAvecColle() == true) {
				pisteCouranteVirageBas = i;
				collePisteVirageBas = true;
				enContactAvecColle = true;
			}
		}

		if (collePisteVirageBas == true) {

			objSpecial.setPositionObjet(
					new Vecteur2D(listePisteVirageBas.get(pisteCouranteVirageBas).getFormeAire().getX(),
							listePisteVirageBas.get(pisteCouranteVirageBas).getFormeAire().getY()));

		}
		for (int i = 0; i < listePisteVirageGauche.size(); i++) {
			listePisteVirageGauche.get(i).collisionColle(voiture);
			if (objSpecial != null && objSpecial.getType() == TypeObjetSpecial.COLLE && enContactAvecColle == false
					&& listePisteVirageGauche.get(i).isEnContactAvecColle() == true) {
				pisteCouranteVirageGauche = i;
				collePisteVirageGauche = true;
				enContactAvecColle = true;
			}
		}
		if (collePisteVirageGauche == true) {

			objSpecial.setPositionObjet(
					new Vecteur2D(listePisteVirageGauche.get(pisteCouranteVirageGauche).getFormeAire().getX(),
							listePisteVirageGauche.get(pisteCouranteVirageGauche).getFormeAire().getY()));

		}

		for (int i = 0; i < listePisteVirageDroit.size(); i++) {
			listePisteVirageDroit.get(i).collisionColle(voiture);
			if (objSpecial != null && objSpecial.getType() == TypeObjetSpecial.COLLE && enContactAvecColle == false
					&& listePisteVirageDroit.get(i).isEnContactAvecColle() == true) {
				pisteCouranteVirageDroit = i;
				collePisteVirageDroit = true;
				enContactAvecColle = true;
			}
		}
		if (collePisteVirageDroit == true) {

			objSpecial.setPositionObjet(
					new Vecteur2D(listePisteVirageDroit.get(pisteCouranteVirageDroit).getFormeAire().getX(),
							listePisteVirageDroit.get(pisteCouranteVirageDroit).getFormeAire().getY()));

		}
		for (int i = 0; i < listePisteVirageHaut.size(); i++) {
			listePisteVirageHaut.get(i).collisionColle(voiture);
			if (objSpecial != null && objSpecial.getType() == TypeObjetSpecial.COLLE && enContactAvecColle == false
					&& listePisteVirageHaut.get(i).isEnContactAvecColle() == true) {
				pisteCouranteVirageHaut = i;
				collePisteVirageHaut = true;
				enContactAvecColle = true;
			}
		}
		if (collePisteVirageHaut == true) {

			objSpecial.setPositionObjet(
					new Vecteur2D(listePisteVirageHaut.get(pisteCouranteVirageHaut).getFormeAire().getX(),
							listePisteVirageHaut.get(pisteCouranteVirageHaut).getFormeAire().getY()));

		}
		listePisteDeDepart.get(0).collisionColle(voiture);
		if (objSpecial != null && objSpecial.getType() == TypeObjetSpecial.COLLE && enContactAvecColle == false
				&& listePisteDeDepart.get(0).isEnContactAvecColle() == true) {

			enContactAvecColle = true;
			collePisteDepart = true;
		}
		if (collePisteDepart == true) {

			objSpecial.setPositionObjet(new Vecteur2D(listePisteDeDepart.get(0).getFormeAire().getX(),
					listePisteDeDepart.get(0).getFormeAire().getY()));
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

		for (int i = 0; i < listeFumee.size(); i++) {
			listeFumee.get(i).dessiner(g2dCopie);
		}

		if (objSpecial != null) {
			if (objSpecial.getType() == TypeObjetSpecial.COLLE) {

				objSpecial.dessiner(g2dCopie);
			}
			if (objSpecial.getType() == TypeObjetSpecial.BOULEDENEIGE) {

				if (boutonAppuye == false) {
					objSpecial.setPositionObjet(listePisteDeDepart.get(0).getVoiture().getPosition());

				}
				effetNeige(g2dCopie);
				objSpecial.dessiner(g2dCopie);
			}
			if (objSpecial.getType() == TypeObjetSpecial.COLLE) {

			}
			if (objSpecial.getType() == TypeObjetSpecial.TROUNOIR) {
				objSpecial.dessiner(g2dCopie);
			}

		}
		// Pour la voiture 2
		if (objSpecial2 != null) {
			if (objSpecial2.getType() == TypeObjetSpecial.COLLE) {

				objSpecial2.dessiner(g2dCopie);
			}
			if (objSpecial2.getType() == TypeObjetSpecial.BOULEDENEIGE) {

				if (boutonAppuye2 == false) {
					objSpecial2.setPositionObjet(listePisteDeDepart.get(0).getVoiture2().getPosition());

				}
				effetNeige(g2dCopie);
				objSpecial2.dessiner(g2dCopie);
			}
			if (objSpecial2.getType() == TypeObjetSpecial.COLLE) {

			}
			if (objSpecial2.getType() == TypeObjetSpecial.TROUNOIR) {
				objSpecial2.dessiner(g2dCopie);
			}

		}
		if (regroupementBoiteMystere.size() != 0) {
			Image boiteMystere = OutilsImage.lireImageEtRedimensionner("LuckyBox.png", 15, 15);
			for (int a = 0; a < regroupementBoiteMystere.size(); a++) {

				regroupementBoiteMystere.get(a).dessiner(g2dCopie);

				if (boiteMystere != null) {
					g2dCopie.drawImage(boiteMystere, (int) this.regroupementBoiteMystere.get(a).getPosition().getX(),
							(int) this.regroupementBoiteMystere.get(a).getPosition().getY(), null);

					boiteMystere.flush();
				}
			}
		}

		g2dCopie.setStroke(new BasicStroke(5));

		g2dCopie.setColor(Color.WHITE);

		if (!listeFumee.get(0).contient(listePisteDeDepart.get(0).getVoiture().getPosition().getX(),
				listePisteDeDepart.get(0).getVoiture().getPosition().getY())) {
			g2dCopie.drawString("Voiture1",
					(int) (listePisteDeDepart.get(0).getVoiture().getPosition().getX()
							- listePisteDeDepart.get(0).getVoiture().getDiametre() / 2),
					(int) (listePisteDeDepart.get(0).getVoiture().getPosition().getY()));

		}
		if (!listeFumee.get(0).contient(listePisteDeDepart.get(0).getVoiture2().getPosition().getX(),
				listePisteDeDepart.get(0).getVoiture2().getPosition().getY())) {
			g2dCopie.drawString("Voiture2",
					(int) (listePisteDeDepart.get(0).getVoiture2().getPosition().getX()
							- listePisteDeDepart.get(0).getVoiture2().getDiametre() / 2),
					(int) (listePisteDeDepart.get(0).getVoiture2().getPosition().getY()));
		}

	}

	/**
	 * Méthode permettant de voir visuellement la durée de l'effet de la boule de
	 * neige
	 * 
	 * @param g2d Composant graphique
	 */
	// Kevin Nguyen
	private void effetNeige(Graphics2D g2d) {
		if (firstTime) {
			snowball = new DessinCollisionBouleDeNeige(listePisteDeDepart.get(0).getVoiture2().getPosition(),
					listePisteDeDepart.get(0).getVoiture2().getDiametre(),
					listePisteDeDepart.get(0).getVoiture2().getSkin(),
					listePisteDeDepart.get(0).getVoiture2().getCercle());
			snowball.dessiner(g2d);
		} else if (firstTime2) {
			snowball = new DessinCollisionBouleDeNeige(listePisteDeDepart.get(0).getVoiture().getPosition(),
					listePisteDeDepart.get(0).getVoiture().getDiametre(),
					listePisteDeDepart.get(0).getVoiture2().getSkin(),
					listePisteDeDepart.get(0).getVoiture2().getCercle());
			snowball.dessiner(g2d);
		}
	}

	/**
	 * Méthode qui permet d'appliquer la fonction de l'accelerateur sur les 2
	 * voitures lorsqu'ils sont en contact avec celui-ci.
	 */
	// Tan Tommy Rin
	private void accelerateurFonction() {

		if (listeAccelerateur.size() != 0) {
			// Voiture 1
			if (listeAccelerateur.get(0).contient(listePisteDeDepart.get(0).getVoiture().getPosition().getX(),
					listePisteDeDepart.get(0).getVoiture().getPosition().getY())) {

				listePisteDeDepart.get(0).getVoiture().setVitesseMaxSelonNiveau(
						listePisteDeDepart.get(0).getVoiture().getVitesseMaxSelonNiveau() + 20);
				listePisteDeDepart.get(0).getVoiture()
						.setAccel(new Vecteur2D(200 * Math.cos(listePisteDeDepart.get(0).getVoiture().getAngle()),
								200 * Math.sin(listePisteDeDepart.get(0).getVoiture().getAngle())));
			} else {
				listePisteDeDepart.get(0).getVoiture().setVitesseMaxSelonNiveau(
						listePisteDeDepart.get(0).getVoiture().getVitesseMaxSelonNiveauInitiale());
			}
			// Voiture 2
			if (listeAccelerateur.get(0).contient(listePisteDeDepart.get(0).getVoiture2().getPosition().getX(),
					listePisteDeDepart.get(0).getVoiture2().getPosition().getY())) {
				listePisteDeDepart.get(0).getVoiture2().setVitesseMaxSelonNiveau(
						listePisteDeDepart.get(0).getVoiture2().getVitesseMaxSelonNiveau() + 20);
				listePisteDeDepart.get(0).getVoiture2()
						.setAccel(new Vecteur2D(200 * Math.cos(listePisteDeDepart.get(0).getVoiture2().getAngle()),
								200 * Math.sin(listePisteDeDepart.get(0).getVoiture2().getAngle())));
			} else {
				listePisteDeDepart.get(0).getVoiture2().setVitesseMaxSelonNiveau(
						listePisteDeDepart.get(0).getVoiture2().getVitesseMaxSelonNiveauInitiale());
			}
		}
	}

	/**
	 * Methode qui permet de set le diametre des deux voitures à 0 lorsqu'ils sont
	 * dans la boite de fumee et le remmet au diametre diametre initial lorsqu'ils
	 * ne sont plus dans la boite a fumee
	 */
	// Alexis Pineda-Alvarado
	private void fumeeFonction() {
		if (listeFumee.size() != 0) {
			// Voiture 1
			if (listeFumee.get(0).contient(listePisteDeDepart.get(0).getVoiture().getPosition().getX(),
					listePisteDeDepart.get(0).getVoiture().getPosition().getY())) {

				listePisteDeDepart.get(0).getVoiture().setDiametre(0);
			} else {
				listePisteDeDepart.get(0).getVoiture()
						.setDiametre(listePisteDeDepart.get(0).getVoiture().getDiametreInitial());
			}
			// Voiture 2
			if (listeFumee.get(0).contient(listePisteDeDepart.get(0).getVoiture2().getPosition().getX(),
					listePisteDeDepart.get(0).getVoiture2().getPosition().getY())) {

				listePisteDeDepart.get(0).getVoiture2().setDiametre(0);
			} else {
				listePisteDeDepart.get(0).getVoiture2()
						.setDiametre(listePisteDeDepart.get(0).getVoiture2().getDiametreInitial());
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
//Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteDeDepart(int i) {
		if (objSpecial != null) {
			if (listePisteDeDepart.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}
		}
		if (objSpecial2 != null) {
			if (listePisteDeDepart.get(i).enCollisionAvecBouleDeNeige(objSpecial2) == true && boutonAppuye2 == true) {

				objSpecial2 = null;
				boutonAppuye2 = false;
			}
		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteHorizontale(int i) {
		if (objSpecial != null) {
			if (listePisteHorizontale.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}
		}
		if (objSpecial2 != null) {
			if (listePisteHorizontale.get(i).enCollisionAvecBouleDeNeige(objSpecial2) == true
					&& boutonAppuye2 == true) {

				objSpecial2 = null;
				boutonAppuye2 = false;
			}
		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVerticale(int i) {
		if (objSpecial != null) {
			if (listePisteVerticale.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}
		}
		if (objSpecial2 != null) {
			if (listePisteVerticale.get(i).enCollisionAvecBouleDeNeige(objSpecial2) == true && boutonAppuye2 == true) {

				objSpecial2 = null;
				boutonAppuye2 = false;
			}
		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVirageBas(int i) {

		if (objSpecial != null) {

			if (listePisteVirageBas.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}

		}
		if (objSpecial2 != null) {

			if (listePisteVirageBas.get(i).enCollisionAvecBouleDeNeige(objSpecial2) == true && boutonAppuye2 == true) {

				objSpecial2 = null;
				boutonAppuye2 = false;
			}

		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVirageDroit(int i) {

		if (objSpecial != null) {

			if (listePisteVirageDroit.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}

		}
		if (objSpecial2 != null) {

			if (listePisteVirageDroit.get(i).enCollisionAvecBouleDeNeige(objSpecial2) == true
					&& boutonAppuye2 == true) {

				objSpecial2 = null;
				boutonAppuye2 = false;
			}

		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVirageGauche(int i) {

		if (objSpecial != null) {

			if (listePisteVirageGauche.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}

		}
		if (objSpecial2 != null) {

			if (listePisteVirageGauche.get(i).enCollisionAvecBouleDeNeige(objSpecial2) == true
					&& boutonAppuye2 == true) {

				objSpecial2 = null;
				boutonAppuye2 = false;
			}

		}
	}

	/**
	 * Méthode qui permet de savoir si le morceau de piste est en collision avec la
	 * boule de neige
	 * 
	 * @param i La piste courante de la loop
	 */
//Tan Tommy Rin
	public void collisionBouleDeNeigeAvecPisteVirageHaut(int i) {
		if (objSpecial != null) {
			if (listePisteVirageHaut.get(i).enCollisionAvecBouleDeNeige(objSpecial) == true && boutonAppuye == true) {

				objSpecial = null;
				boutonAppuye = false;
			}
		}
		if (objSpecial2 != null) {
			if (listePisteVirageHaut.get(i).enCollisionAvecBouleDeNeige(objSpecial2) == true && boutonAppuye2 == true) {

				objSpecial2 = null;
				boutonAppuye2 = false;
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
				voiture.setNombreToursFaits(voiture.getNombreToursFaits() + 1);
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

	public boolean isToucheHautActive() {
		return toucheHautActive;
	}

	public void setToucheHautActive(boolean toucheHautActive) {
		this.toucheHautActive = toucheHautActive;
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

	public ArrayList<Fumee> getListeFumee() {
		return listeFumee;
	}

	public void setListeFumee(ArrayList<Fumee> listeFumee) {
		this.listeFumee = listeFumee;
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

	public double getNombreToursAFaire() {
		return nombreToursAFaire;
	}

	public void setNombreToursAFaire(double nombreToursAFaire) {
		this.nombreToursAFaire = nombreToursAFaire;
	}

	public boolean isBoutonAppuye2() {
		return boutonAppuye2;
	}

	public void setBoutonAppuye2(boolean boutonAppuye2) {
		this.boutonAppuye2 = boutonAppuye2;
	}

	public boolean isToucheWActive() {
		return toucheWActive;
	}

	public void setToucheWActive(boolean toucheWActive) {
		this.toucheWActive = toucheWActive;
	}

	public int getNombreBoiteMystere() {
		return nombreBoiteMystere;
	}

	public void setNombreBoiteMystere(int nombreBoiteMystere) {
		this.nombreBoiteMystere = nombreBoiteMystere;
	}
}
