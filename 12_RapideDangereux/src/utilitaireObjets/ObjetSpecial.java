package utilitaireObjets;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.util.ArrayList;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;
import physique.MoteurPhysique;

/**
 * Classe permettant de créer et gérer un objet special
 * 
 * @author Tan Tommy Rin
 *
 */
public class ObjetSpecial implements Dessinable {
	
	private Vecteur2D positionObjet;
	private double diametreObjet;
	private TypeObjetSpecial type;
	private double pixelParMetre = 1;
	private double tempsTemporaire;
	private boolean fonctionActive = false;
	private int x;
	private int y;

	/**
	 * Méthode permettant de créer un objet spécial à l'aide de paramètre
	 * 
	 * @param pos       La position de l'objet special
	 * @param diametre  Le diaetre de l'objet special
	 * @param typeObjet Le type de l'objet special
	 */
	public ObjetSpecial(Vecteur2D pos, double diametre, TypeObjetSpecial typeObjet) {
		this.positionObjet = pos;
		this.diametreObjet = diametre;
		this.type = typeObjet;

	}

	/**
	 * Méthode permettant de dessiner sur la zone d'animation à l'aide du g2d
	 */

	@Override
	public void dessiner(Graphics2D g2d) {

		if (type == TypeObjetSpecial.CHAMPIGNON) {
			Champignon champignon = new Champignon(this.positionObjet, this.diametreObjet, type);

		}
		if (type == TypeObjetSpecial.BOULEDENEIGE) {
			BouleDeNeige bouleDeNeige = new BouleDeNeige(this.positionObjet, this.diametreObjet);
			bouleDeNeige.dessiner(g2d);

		}

		

		if(type == TypeObjetSpecial.TROUNOIR) {
			TrouNoir trouNoir = new TrouNoir(this.positionObjet, this.diametreObjet);
			trouNoir.dessiner(g2d);
		}
		if(type == TypeObjetSpecial.COLLE) {
			Colle colle = new Colle(this.positionObjet, this.diametreObjet);
			colle.dessiner(g2d);
		}

	}

	/**
	 * Méthode permettant d'activer la fonction de l'objet special obtenu
	 * 
	 * @param voiture          La voiture affecté
	 * @param tempsTotalEcoule Le temps total écoulé
	 */

	public void fonctionSelonObjet(Voiture voiture, double tempsTotalEcoule) {

		// Condition si fonction active est vrai, le temps temporaire est égale à celui
		// du temps total écoulé et restera fixe tandis que le tempsTotalEcoule changera
		// de valeur
		if (fonctionActive == true) {

			tempsTemporaire = tempsTotalEcoule;
			fonctionActive = false;

		}
		// Fonction du champignon

		if (type == TypeObjetSpecial.CHAMPIGNON) {

			fonctionChampignon(voiture, tempsTotalEcoule);

		} else if (type == TypeObjetSpecial.BOULEDENEIGE) {

			fonctionBouleDeNeige(voiture, tempsTotalEcoule);

		} else if (type == TypeObjetSpecial.COLLE) {

		} else {

		}

	}

	/**
	 * Méhode qui active la fonction du champignon
	 * 
	 * @param voiture          La voiture affecté
	 * @param tempsTotalEcoule Le temps total écoulé
	 */

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

		if ((tempsTemporaire + 3 > tempsFinal)) {
			System.out.println("SLOW DOWN!!!");
			Vecteur2D voitureSlow = new Vecteur2D();
			voitureSlow = MoteurPhysique.calculerForceFrottement(2.50, voiture.getMasseEnKg(), voiture.getAngle());
			voiture.setSommeDesForces(voitureSlow);
			System.out.println(voitureSlow);
			return true;
		} else {
			System.out.println("NORMAL SPEED!!!");
			Vecteur2D voitureNormal = new Vecteur2D();
			voitureNormal = MoteurPhysique.calculerForceFrottement(0.45, voiture.getMasseEnKg(), voiture.getAngle());
			voiture.setSommeDesForces(voitureNormal);
			return false;
		}

	}

	public boolean isFonctionActive() {
		return fonctionActive;
	}

	public void setFonctionActive(boolean fonctionActive) {
		this.fonctionActive = fonctionActive;
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

}
