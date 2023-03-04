package utilitaireObjets;

import java.awt.Graphics2D;

import interfaces.Dessinable;
import interfaces.Selectionnable;

/**
 * Classe qui permet de gérér et de créer la voiture
 * 
 * @author TanTommyRin
 *
 */

public class Champignon implements Dessinable, Selectionnable {

	@Override
	public boolean contient(double xPix, double yPix) {

		return false;
	}

	@Override
	public void dessiner(Graphics2D g2d) {

	}

}
