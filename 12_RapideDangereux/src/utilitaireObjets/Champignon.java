package utilitaireObjets;

import geometrie.Vecteur2D;

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
	private final double GRADUATION_PROGESSIVE_MASSE = 1.0025;
	private final double GRADUATION_PROGRESSIVE_DIAMETRE = 1.01;

	/**
	 * Méthode qui permet de créer un champignon à l'aide de paramètres
	 * 
	 * @param pos       Position du champignon
	 * @param diametre  Diametre du champignon
	 * @param typeObjet Le type d'objetSpecial
	 */
	// Tan Tommy Rin
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
	// Tan Tommy Rin
	public void fonctionChampignonActivation(Voiture voitureAffecte) {

		if (voitureAffecte.getDiametre() < 45) {
			// Masse augmente pendant une durée voulue
			double masseProgressive = voitureAffecte.getMasseEnKg() * GRADUATION_PROGESSIVE_MASSE;
			double diametreProgressif = voitureAffecte.getDiametre() * GRADUATION_PROGRESSIVE_DIAMETRE;
			
			voitureAffecte.setMasseEnKg(masseProgressive);
			voitureAffecte.setDiametre(diametreProgressif);
		}

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
