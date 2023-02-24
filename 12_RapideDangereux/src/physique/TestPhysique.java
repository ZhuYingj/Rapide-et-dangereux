package physique;

import geometrie.Vecteur2D;

public class TestPhysique {

	public static void main(String[] args) {

		double masseA = 3200;
		double masseB = 2500;
		double mu = 0.45;
		double deltaT = 0.01;
		double angle = 20;
		double angleRad = Math.toRadians(angle);
		int temps = 0;
		int size = 0;
		Vecteur2D fTotaleA;
		Vecteur2D fTotaleB;
		//Vecteur de force de la voiture A
		Vecteur2D fFrottementA = new Vecteur2D(MoteurPhysique.calculerForceFrottement(mu, masseA, 0));
		Vecteur2D fGraviteA = new Vecteur2D(MoteurPhysique.calculerForceGrav(masseA, 0));
		fTotaleA = new Vecteur2D(fFrottementA.additionne(fGraviteA));
		//Vecteur de force de la voiture B
		Vecteur2D fFrottementB = new Vecteur2D(MoteurPhysique.calculerForceFrottement(mu, masseB, angleRad));
		Vecteur2D fGraviteB = new Vecteur2D(MoteurPhysique.calculerForceGrav(masseB, angleRad));
		fTotaleB = new Vecteur2D(fFrottementB.additionne(fGraviteB));
		//Vecteur avec valeurs initiales
		Vecteur2D pos = new Vecteur2D(0, 0);
		Vecteur2D vit = new Vecteur2D(40, 0);
		Vecteur2D acc = new Vecteur2D(0, 0);
		Vecteur2D vitCollision;

		try {
			acc = MoteurPhysique.calculAcceleration(fTotaleA, masseA);
		} catch (Exception r) {
			System.out.println("Erreur calcul de l'accélération, masse nulle!");
		}

		for (int i = 1; pos.getX() < 60; i++) {
			vit = MoteurPhysique.calculVitesse(deltaT, vit, acc);
			pos = MoteurPhysique.calculPosition(deltaT, pos, vit);
			size = i;
		}//Fin boucle pour definir la taille des arraylist de la voiture A
		//Reinitialisation des vecteurs position et vitesse
		pos = new Vecteur2D(0, 0);
		vit = new Vecteur2D(40, 0);
		Vecteur2D[] vite = new Vecteur2D[size];
		Vecteur2D[] posi = new Vecteur2D[size];
//		vitCollision = MoteurPhysique.calculerVitesseObjetMur(vit);
//
//		vit = vitCollision;
		for (int i = 0; i < vite.length; i++) {

			vit = MoteurPhysique.calculVitesse(deltaT, vit, acc);
			pos = MoteurPhysique.calculPosition(deltaT, pos, vit);
			vite[i] = vit;
			posi[i] = pos;
			if (i < 4) {
				System.out.println("\nTemps= " + String.format("%." + 2 + "f", (i + 1) * deltaT) + "	acc= "
						+ String.format("%." + 3 + "f", acc.getX()) + "	vit= "
						+ String.format("%." + 3 + "f", vite[i].getX()) + "	pos= "
						+ String.format("%." + 3 + "f", posi[i].getX()));
			} else if (i == 4) {
				System.out.println("\n(...)");
			} else if (i > vite.length - 4) {
				System.out.println("\nTemps= " + String.format("%." + 2 + "f", (i + 1) * deltaT) + "	acc= "
						+ String.format("%." + 3 + "f", acc.getX()) + "	vit= "
						+ String.format("%." + 3 + "f", vite[i].getX()) + "	pos= "
						+ String.format("%." + 3 + "f", posi[i].getX()));
			}//Fin if

		}//Fin boucle for

		temps = size;







	}

}

