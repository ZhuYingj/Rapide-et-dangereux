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
	private TrouNoir trouNoir;
	private boolean enContactTrouNoir = false;
	private boolean enContactTrouNoir2 = false;

	/**
	 * Constructeur permettant de créer un objet spécial
	 * 
	 * @param pos       La position de l'objet special
	 * @param diametre  Le diaetre de l'objet special
	 * @param typeObjet Le type de l'objet special
	 */
	// Tan Tommy Rin
	public ObjetSpecial(Vecteur2D pos, double diametre, TypeObjetSpecial typeObjet) {
		this.positionObjet = pos;
		this.diametreObjet = diametre;
		this.type = typeObjet;
		bouleDeNeige = new BouleDeNeige(positionObjet, diametre);
		colle = new Colle(positionObjet, 80);
		trouNoir = new TrouNoir(positionObjet, this.diametreObjet);
		;
	}

	/**
	 * Méthode permettant de dessiner sur la zone d'animation à l'aide du g2d
	 */
	// Tan Tommy Rin
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
			trouNoir = new TrouNoir(this.positionObjet, this.diametreObjet);
			trouNoir.dessiner(g2d);
		}
		if (type == TypeObjetSpecial.COLLE) {
			colle = new Colle(this.positionObjet, 80);
			colle.dessiner(g2d);

		}

	}

	/**
	 * Méthode qui permet le fonctionnement du trou noir sur la voiture affecté
	 * 
	 * @param voiture Voiture affectée
	 * @return Le vecteur de la force appliquée
	 */
//Tan Tommy Rin

	public Vecteur2D fonctionTrouNoir(Voiture voiture) {

		Vecteur2D forceApplied = new Vecteur2D(trouNoir.getPosition());
		forceApplied = forceApplied.soustrait(voiture.getPosition());
		// Distance entre la voiture et le trou noir
		double r = forceApplied.module();

		double fg = (trouNoir.getMasseTrouNoir() * 35) / (r * r);
		forceApplied = forceApplied.multiplie(fg);
		voiture.setSommeDesForces(forceApplied);

		return forceApplied;
	}

	/**
	 * Méthode décrivant la fonction de la colle. Lorsque la voiture est en contact
	 * avec la colle, une acceleration du sens inverse de la voiture est produite,
	 * ce qui l'amene a ralentir. Selon si la touche d'accleration est activé ou
	 * non, la valeur de cette accelération implanté change.
	 * 
	 * @param voiture La voiture affectée
	 */
	// Tan Tommy Rin
	public void fonctionColle(Voiture voiture) {

		Vecteur2D forceFrottement = new Vecteur2D();
		// Coefficient de 2.75, colle.(Inventé)
		forceFrottement = MoteurPhysique.calculerForceFrottement(2.75, voiture.getMasseEnKg(), voiture.getAngle());
		voiture.setAccel(new Vecteur2D(20 * Math.cos(voiture.getAngle()), 20 * Math.sin(voiture.getAngle())));
		if (voiture.getVitesse().module() < 5) {
			voiture.setSommeDesForces(new Vecteur2D(0, 0));
		} else {
			voiture.setSommeDesForces(forceFrottement);
		}

	}

	/**
	 * Méhode qui active la fonction du champignon
	 * 
	 * @param voiture          La voiture affecté
	 * @param tempsTotalEcoule Le temps total écoulé
	 * @return si la fonction est en cours
	 */
	// Tan Tommy Rin

	public boolean fonctionChampignon(Voiture voiture, double tempsTotalEcoule) {
		Champignon champignon = new Champignon(this.positionObjet, this.diametreObjet, type);
		boolean fonctionActive = false;

		if ((tempsTemporaire + 8 > tempsTotalEcoule)) {
			champignon.fonctionChampignonActivation(voiture);

			fonctionActive = true;
		} else {
			voiture.setMasseEnKg(voiture.getMasseEnKgInitial());
			voiture.setDiametre(voiture.getDiametreInitial());

			fonctionActive = false;
		}

		return fonctionActive;
	}

	/**
	 * Méthode qui permet d'activer la fonction de la boule de neige sur la voiture
	 * affecté pendant un certain temps.
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
	// Tan Tommy Rin
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
	// Tan Tommy Rin
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

	public boolean isEnContactTrouNoir2() {
		return enContactTrouNoir2;
	}

	public void setEnContactTrouNoir2(boolean enContactTrouNoir2) {
		this.enContactTrouNoir2 = enContactTrouNoir2;
	}

	public boolean isEnContactTrouNoir() {
		return enContactTrouNoir;
	}

	public TrouNoir getTrouNoir() {
		return trouNoir;
	}

	public void setTrouNoir(TrouNoir trouNoir) {
		this.trouNoir = trouNoir;
	}

	public void setEnContactTrouNoir(boolean enContactTrouNoir) {
		this.enContactTrouNoir = enContactTrouNoir;
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
