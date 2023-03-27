package utilitaireObjets;

import java.awt.Graphics2D;
import java.util.ArrayList;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;
import interfaces.TypePiste;
import pisteDeCourse.PisteMexique;

/**
 * Méthode qui regroupe tout un circuit/piste. Il y a les objets spéciaux,
 * la/les voiture(s) et les morceaux de piste.
 * 
 * @author Tan Tommy Rin
 *
 */

public class Regroupement implements Dessinable {

	/** Liste de boites mysteres **/
	private ArrayList<BlocMystere> regroupementBoiteMystere;
	/** Piste mexique **/
	private PisteMexique pisteMexique;
	/** Le nombre de pixels par metre **/
	private double pixelsParMetre = 1;
	/** Le nombre de boite mystere **/
	private int nombreBoiteMystere = 1;

	/**
	 * Méthode qui permet de créer un groupe à l'aide de paramètre
	 * 
	 * @param voiture     La voiture
	 * @param nombreBoite Le nombre de boite
	 */
	public Regroupement(Voiture voiture, int nombreBoite, PisteMexique pisteMexique, TypePiste typePiste) {

		this.pisteMexique = pisteMexique;
		pisteMexique.getDepart().get(0).setVoiture(voiture);
		this.nombreBoiteMystere = nombreBoite;

		creeBoiteDansListe();

	}

	/**
	 * Méthode qui permet au regroupement d'avancer d'un pas.
	 * 
	 * @param deltaT           L'intervalle de temps(pas)
	 * @param tempsTotalEcoule Le temps total écoulé
	 */

	public void avancerGroupe(double deltaT, double tempsTotalEcoule) {
		pisteMexique.getDepart().get(0).getVoiture().avancerUnPas(deltaT);
		for (int a = 0; a < regroupementBoiteMystere.size(); a++) {
			regroupementBoiteMystere.get(a).enCollisionAvecVoiture(pisteMexique.getDepart().get(0).getVoiture());
			if (regroupementBoiteMystere.get(a)
					.enCollisionAvecVoiture(pisteMexique.getDepart().get(0).getVoiture()) == true) {
				regroupementBoiteMystere.get(a).getObjetSpecial()
						.fonctionSelonObjet(pisteMexique.getDepart().get(0).getVoiture(), tempsTotalEcoule);

				// Pour le champignon, lorsque la fonctionnalité du champignon est fini, nous
				// retirons la boite mystere contenant ce champignon de la liste.
				if (regroupementBoiteMystere.get(a).getObjetSpecial().getType() == TypeObjetSpecial.CHAMPIGNON) {
					if (regroupementBoiteMystere.get(a).getObjetSpecial().fonctionChampignon(
							pisteMexique.getDepart().get(0).getVoiture(), tempsTotalEcoule) == false) {
						regroupementBoiteMystere.remove(a);
						System.out.println(regroupementBoiteMystere.size());
					}
				} // Fin condition pour le champignon

				// Pour la boule de neige
//				if (regroupementBoiteMystere.get(a).getObjetSpecial().getType() == TypeObjetSpecial.BOULEDENEIGE) {
//					if (regroupementBoiteMystere.get(a).getObjetSpecial().fonctionBouleDeNeige(
//							pisteMexique.getDepart().get(0).getVoiture(), tempsTotalEcoule) == false) {
//						regroupementBoiteMystere.remove(a);
//					}
//				}
			}

		}
	}

	/**
	 * Méthode qui crée les boites mystères et les place dans une liste avec un
	 * diametre fixe
	 */
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

	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dCopie = (Graphics2D) g2d.create();

		pisteMexique.setPixelsParMetre(pixelsParMetre);
		pisteMexique.getDepart().get(0).getVoiture().setPixelsParMetre(pixelsParMetre);
		pisteMexique.dessiner(g2d);
		for (int a = 0; a < regroupementBoiteMystere.size(); a++) {
			regroupementBoiteMystere.get(a).setPixelParMetre(pixelsParMetre);
			regroupementBoiteMystere.get(a).dessiner(g2dCopie);

		}

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

}
