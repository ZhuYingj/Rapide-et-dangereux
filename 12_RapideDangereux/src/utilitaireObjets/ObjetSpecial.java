package utilitaireObjets;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;
import physique.MoteurPhysique;

/**
 * Classe permettant de créer et gérer un objet special
 * 
 * @author Tan Tommy Rin
 * @author Alexis Pineda-Alvarado
 *
 */
public class ObjetSpecial implements Dessinable {

	/** Vecteur de la vitesse de la voiture **/
	private Vecteur2D vitesse = new Vecteur2D(0, 0); // par defaut

	/** Vecteur de l'acceleration de la voiture **/
	private Vecteur2D accel = new Vecteur2D(0, 0); // par defaut

	private Vecteur2D positionObjet;
	private double diametreObjet;
	private TypeObjetSpecial type;
	private double pixelParMetre = 1;
	private double tempsTemporaire;
	private BouleDeNeige bouleDeNeige;
	private Colle colle;

	/**
	 * Constructeur permettant de créer un objet spécial
	 * 
	 * @param pos       La position de l'objet special
	 * @param diametre  Le diaetre de l'objet special
	 * @param typeObjet Le type de l'objet special
	 */
	// Par Tan Tommy Rin
	public ObjetSpecial(Vecteur2D pos, double diametre, TypeObjetSpecial typeObjet) {
		this.positionObjet = pos;
		this.diametreObjet = diametre;
		this.type = typeObjet;
		bouleDeNeige = new BouleDeNeige(positionObjet, diametre);
		colle = new Colle(positionObjet, 80);
		;
	}

	/**
	 * Méthode permettant de dessiner sur la zone d'animation à l'aide du g2d
	 */
	// Par Tan Tommy Rin
	@Override
	public void dessiner(Graphics2D g2d) {

		if (type == TypeObjetSpecial.CHAMPIGNON) {
			Champignon champignon = new Champignon(this.positionObjet, this.diametreObjet, type);

		}
		if (type == TypeObjetSpecial.BOULEDENEIGE) {
			bouleDeNeige = new BouleDeNeige(this.positionObjet, this.diametreObjet);
			bouleDeNeige.dessiner(g2d);

		}

		if (type == TypeObjetSpecial.TROUNOIR) {
			TrouNoir trouNoir = new TrouNoir(this.positionObjet, this.diametreObjet);
			trouNoir.dessiner(g2d);
		}
		if (type == TypeObjetSpecial.COLLE) {
			colle = new Colle(this.positionObjet, 80);
			colle.dessiner(g2d);

		}

	}

	/**
	 * Méthode décrivant la fonction de la colle. Lorsque la voiture est en contact
	 * avec la colle, une acceleration du sens inverse de la voiture est produite,
	 * ce qui l'amene a ralentir. Selon si la touche d'accleration est activé ou
	 * non, la valeur de cette accelération implanté change.
	 * 
	 * @param voiture      La voiture affectée
	 * @param toucheActive Si la touche d'acceleration est activée
	 */
	// Par Tan Tommy Rin
	public void fonctionColle(Voiture voiture, boolean toucheActive) {

		if (toucheActive == true) {
			if (voiture.getVitesse().module() < 5) {

			} else if (voiture.getVitesse().module() < 15) {
				voiture.setAccel(new Vecteur2D(-4 * Math.cos(voiture.getAngle()), -4 * Math.sin(voiture.getAngle())));
			} else {
				voiture.setAccel(new Vecteur2D(-12 * Math.cos(voiture.getAngle()), -12 * Math.sin(voiture.getAngle())));
			}

		} else {
			if (voiture.getVitesse().module() < 5) {

			} else if (voiture.getVitesse().module() < 15) {
				voiture.setAccel(new Vecteur2D(-5 * Math.cos(voiture.getAngle()), -5 * Math.sin(voiture.getAngle())));
			} else {
				voiture.setAccel(new Vecteur2D(-15 * Math.cos(voiture.getAngle()), -15 * Math.sin(voiture.getAngle())));
			}
		}

	}

	/**
	 * Méhode qui active la fonction du champignon
	 * 
	 * @param voiture          La voiture affecté
	 * @param tempsTotalEcoule Le temps total écoulé
	 * @return si la fonction est en cours
	 */
	// Par Tan Tommy Rin

	public boolean fonctionChampignon(Voiture voiture, double tempsTotalEcoule) {
		Champignon champignon = new Champignon(this.positionObjet, this.diametreObjet, type);
		if ((tempsTemporaire + 5 > tempsTotalEcoule)) {
			champignon.fonctionChampignonActivation(voiture);

			return true;
		} else {
			voiture.setMasseEnKg(voiture.getMasseEnKgInitial());
			voiture.setDiametre(voiture.getDiametreInitial());

			return false;
		}

	}

	/**
	 * 
	 * @param voiture    la valeur de la voiture qui va être affecté
	 * @param tempsFinal le temps total finaux qui va être écoulé
	 * @return la valeur du fonctionnement de la boule de neige causer par le temps
	 */
	// Alexis Pineda-Alvarado
	public boolean fonctionBouleDeNeige(Voiture voiture, double tempsFinal) {

		if (tempsTemporaire + 3 > tempsFinal) {

			Vecteur2D voitureSlow = new Vecteur2D();
			voitureSlow = MoteurPhysique.calculerForceFrottement(2.50, voiture.getMasseEnKg(), voiture.getAngle());
			voiture.setSommeDesForces(voitureSlow);

			return true;
		} else {

			Vecteur2D voitureNormal = new Vecteur2D();
			voitureNormal = MoteurPhysique.calculerForceFrottement(0.45, voiture.getMasseEnKg(), voiture.getAngle());
			voiture.setSommeDesForces(voitureNormal);

			return false;
		}

	}

	/**
	 * Calcule la nouvelle vitesse et la nouvelle position de la boule de neige ou
	 * trou noir apres cet nouvel intervalle de temps.
	 * 
	 * @param deltaT intervalle de temps (pas)
	 */
	// Par Tan Tommy Rin
	public void avancerUnPas(double deltaT) {
		this.vitesse = MoteurPhysique.calculVitesse(deltaT, vitesse, accel);
		this.positionObjet = MoteurPhysique.calculPosition(deltaT, positionObjet, vitesse);

	}

	/**
	 * Recalcule l'acceleration de la boule de neige ou le trou noir a l'aide la
	 * nouvelle somme des forces passee en parametre Ceci aura pour consequence de
	 * modifier l'acceleration
	 * 
	 * @param sommeForcesSurLaBouleDeNeigeOuTrouNoir La somme des forces exercees
	 *                                               sur la boule de neige ou le
	 *                                               trou noir
	 */
	// Par Tan Tommy Rin
	public void setSommeDesForces(Vecteur2D sommeForcesSurLaBouleDeNeigeOuTrouNoir) {
		// ici changer les forces signifie recalculer l'acceleration
		// on relegue cette tache au moteur physique.
		try {
			accel = MoteurPhysique.calculAcceleration(sommeForcesSurLaBouleDeNeigeOuTrouNoir, 50);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Vecteur2D getAccel() {
		return accel;
	}

	public void setAccel(Vecteur2D accel) {
		this.accel = accel;
	}

	public double getTempsTemporaire() {
		return tempsTemporaire;
	}

	public void setTempsTemporaire(double tempsTemporaire) {
		this.tempsTemporaire = tempsTemporaire;
	}

	public TypeObjetSpecial getType() {
		return type;
	}

	public void setType(TypeObjetSpecial type) {
		this.type = type;
	}

	public Vecteur2D getPositionObjet() {
		return positionObjet;
	}

	public void setPositionObjet(Vecteur2D positionObjet) {
		this.positionObjet = positionObjet;
	}

	public double getDiametreObjet() {
		return diametreObjet;
	}

	public void setDiametreObjet(double diametreObjet) {
		this.diametreObjet = diametreObjet;

	}

	public double getPixelParMetre() {
		return pixelParMetre;
	}

	public void setPixelParMetre(double pixelParMetre) {
		this.pixelParMetre = pixelParMetre;
	}

	public Vecteur2D getVitesse() {
		return vitesse;
	}

	public void setVitesse(Vecteur2D vitesse) {
		this.vitesse = vitesse;
	}

	public BouleDeNeige getBouleDeNeige() {
		return bouleDeNeige;
	}

	public void setBouleDeNeige(BouleDeNeige bouleDeNeige) {
		this.bouleDeNeige = bouleDeNeige;
	}

	public Colle getColle() {
		return colle;
	}

	public void setColle(Colle colle) {
		this.colle = colle;
	}

}
