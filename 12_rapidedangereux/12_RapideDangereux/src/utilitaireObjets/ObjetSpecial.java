package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;

public class ObjetSpecial implements Dessinable {

	private Vecteur2D positionObjet;
	private double diametreObjet;
	private TypeObjetSpecial type;
	private double pixelParMetre = 1;
	private double tempsTemporaire;
	private boolean fonctionActive = false;

	public ObjetSpecial(Vecteur2D pos, double diametre, TypeObjetSpecial typeObjet) {
		this.positionObjet = pos;
		this.diametreObjet = diametre;
		this.type = typeObjet;

	}

	@Override
	public void dessiner(Graphics2D g2d) {

		if (type == TypeObjetSpecial.CHAMPIGNON) {
			Champignon champignon = new Champignon(this.positionObjet, this.diametreObjet, type);

		}

	}

	public void fonctionSelonObjet(Voiture voiture, double tempsTotalEcoule) {
		if (fonctionActive == true) {

			tempsTemporaire = tempsTotalEcoule;
			fonctionActive = false;

		}
		// Fonction du champignon

		if (type == TypeObjetSpecial.CHAMPIGNON) {
			Champignon champignon = new Champignon(this.positionObjet, this.diametreObjet, type);
			if ((tempsTemporaire + 5 > tempsTotalEcoule) && fonctionActive == false) {
				champignon.fonctionChampignonActivation(voiture);
			} else {
				voiture.setMasseEnKg(voiture.getMasseEnKgInitial());
				voiture.setDiametre(voiture.getDiametreInitial());
			}

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
