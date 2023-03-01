package physique;

import geometrie.Vecteur2D;


/**
 * Cette classe regroupe les calculs physiques necessaires au mouvement des objets
 * des divers objets dans la scene.
 * Utilise la methode d'integration numerique d'Euler semi-implicite. 
 * @author Kevin Nguyen
 */
public class MoteurPhysique {

	/** Coefficient de restitution ayant une valeur fixe. */
	private static final double COEFF_RESTITUTION_E = 0.8;
	/** Constante gravitationnelle terrestre. */
	private static final double ACCEL_G = 9.80665;
	/** Tolerance utilisee dans les comparaisons reelles avec zero. */
	private static final double EPSILON = 1e-10;

	/**
	 * Calcule et retourne l'acceleration en utilisant F=ma
	 * 
	 * @param sommeDesForces Somme des forces appliquees
	 * @param masse          Masse de l'objet
	 * @return l'acceletation F/m
	 * @throws Exception Erreur si la masse est pratiquement nulle
	 */
	public static Vecteur2D calculAcceleration(Vecteur2D sommeDesForces, double masse) throws Exception {
		if (masse < EPSILON)
			throw new Exception(
					"Erreur MoteurPhysique: La masse étant nulle ou presque l'accéleration ne peut pas etre calculée.");
		else
			return new Vecteur2D(sommeDesForces.getX() / masse, sommeDesForces.getY() / masse);
	}

	/**
	 * Calcule et retourne la nouvelle vitesse, deltaT secondes plus tard, en
	 * utilisant l'algorithme d'Euler semi-implicite.
	 * 
	 * @param deltaT  L'intervalle de temps (petit!) en secondes
	 * @param vitesse La vitesse initiale au debut de l'intervalle de temps, en m/s
	 * @param accel   L'acceleration initiale au debut de l'intervalle de temps, en
	 *                m/s2
	 * @return La nouvelle vitesse (a la fin de l'intervalle)
	 */
	public static Vecteur2D calculVitesse(double deltaT, Vecteur2D vitesse, Vecteur2D accel) {
		Vecteur2D deltaVitesse = Vecteur2D.multiplie(accel, deltaT);
		Vecteur2D resultVit = vitesse.additionne(deltaVitesse);
		return new Vecteur2D(resultVit.getX(), resultVit.getY());

	}

	/**
	 * Calcule et retourne la nouvelle position, deltaT secondes plus tard, en
	 * utilisant l'algorithme d'Euler semi-implicite.
	 * 
	 * @param deltaT   L'intervalle de temps (petit!) en secondes
	 * @param position La position initiale au debut de l'intervalle de temps, en m
	 * @param vitesse  La vitesse initiale au debut de l'intervalle de temps, en m/s
	 * @return La nouvelle position (a la fin de l'intervalle)
	 */
	public static Vecteur2D calculPosition(double deltaT, Vecteur2D position, Vecteur2D vitesse) {

		Vecteur2D deltaPosition = Vecteur2D.multiplie(vitesse, deltaT);

		Vecteur2D resultPos = position.additionne(deltaPosition);

		return new Vecteur2D(resultPos.getX(), resultPos.getY());

	}

	/**
	 * Calcule et retourne la force de frottement existante entre un corps en
	 * mouvement avec un plan.
	 * 
	 * @param mu       Coefficient de frottement cinetique.
	 * @param masse    Masse de l'objet en mouvement.
	 * @param angleRad Angle d'un plan incline avec le sol en radians.
	 * @return La force de frottement.
	 */
	public static Vecteur2D calculerForceFrottement(double mu, double masse, double angleRad) {

		return new Vecteur2D(-mu * masse * ACCEL_G * Math.cos(angleRad), -mu * masse * ACCEL_G * Math.sin(angleRad));

	}

	/**
	 * Calcule et retourne la force gravitationnelle agissant sur un objet.
	 * 
	 * @param masse    Masse de l'objet
	 * @param angleRad Angle d'un plan incline avec le sol en radians.
	 * @return La force gravitationnelle.
	 */
	public static Vecteur2D calculerForceGrav(double masse, double angleRad) {
		return new Vecteur2D(-masse * ACCEL_G * Math.sin(angleRad), 0);
	}

	/**
	 * Calcule et retourne la vitesse de la voiture apres une collision
	 * @param vitesse Vitesse initiale de l'objet
	 * @return La vitesse après collision
	 */
	public static Vecteur2D calculerVitesseObjetMur(Vecteur2D vitesse) {
		Vecteur2D deltaVit = Vecteur2D.multiplie(vitesse, 2);
		Vecteur2D vitFinale = Vecteur2D.soustrait(vitesse, deltaVit);
		return new Vecteur2D(vitFinale);
	}
	
	public static double calculerNormeVitesse(Vecteur2D vitesse) {
		return vitesse.module();
	}
}
