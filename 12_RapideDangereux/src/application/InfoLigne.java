package application;

/**
 * Classe qui permet de retourner les information d'une ligne
 * 
 * @author Ludovic Julien
 *
 */
public class InfoLigne {

	private String nom;
	private double temps;
	private String piste;

	/**
	 * constructeur de la classe
	 * 
	 * @param nom   nom du joueur
	 * @param temps temps effectué
	 * @param piste piste jouer
	 */
	// Ludovic Julien
	public InfoLigne(String nom, double temps, String piste) {
		this.nom = nom;
		this.temps = temps;
		this.piste = piste;
	}

	// Ludovic Julien
	public String getNom() {
		return nom;
	}

	// Ludovic Julien
	public double getTemps() {
		return temps;
	}

	// Ludovic Julien
	public String getPiste() {
		return piste;
	}

	/**
	 * Méthode qui permet de l'information de la piste, le temps et le nom
	 * 
	 * @return l'information de la piste, le temps et le nom
	 */
	// Ludovic Julien
	public String toString() {
		return nom + ";" + temps + ";" + piste;
	}

}
