package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;

/**
 * Classe qui permet de créer et gérer un champignon
 * 
 * @author Tan Tommy Rin
 *
 */
public class Champignon extends ObjetSpecial {

	/** Vecteur de la position de la voiture **/
	private Vecteur2D position;
	private TypeObjetSpecial typeObjet = TypeObjetSpecial.CHAMPIGNON;
	private double GRADUATIONPROGESSIVEMASSE = 1.0025;
	private double GRADUATIONPROGRESSIVEDIAMETRE = 1.0015;

	/**
	 * Méthode qui permet de créer un champignon à l'aide de paramètres
	 * 
	 * @param pos       Position du champignon
	 * @param diametre  Diametre du champignon
	 * @param typeObjet Le type d'objetSpecial
	 */
	public Champignon(Vecteur2D pos, double diametre, TypeObjetSpecial typeObjet) {
		super(pos, diametre, typeObjet);
		this.position = super.getPositionObjet();

	}

	/**
	 * Méthode de la fonction du champignon sur la voiture. Elle augmente la masse
	 * et le diametre du champignon de façon progressive.
	 * 
	 * @param voitureAffecte La voiture affectée
	 */

	public void fonctionChampignonActivation(Voiture voitureAffecte) {

		// Masse augmente pendant une durée voulue
		double masseProgressive = voitureAffecte.getMasseEnKg() * GRADUATIONPROGESSIVEMASSE;
		double diametreProgressif = voitureAffecte.getDiametre() * GRADUATIONPROGRESSIVEDIAMETRE;

		voitureAffecte.setMasseEnKg(masseProgressive);
		voitureAffecte.setDiametre(diametreProgressif);

	}

	public TypeObjetSpecial getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjetSpecial typeObjet) {
		this.typeObjet = typeObjet;
	}

	public Vecteur2D getPosition() {
		return position;
	}

	public void setPosition(Vecteur2D position) {
		this.position = position;

	}

}
