package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class ZoneAnimPhysique extends JPanel implements Runnable {
	
	/** Largeur du composant en metres. */
	private double largeurDuComposantEnMetres = 230;
	/** Hauteur du composant en metres. */
	private double hauteurDuComposantEnMetres;
	/** Nombre de pixels pas metre. */
	private double pixelsParMetre;
	/** Temps du deltaT par d�faut */
	private double deltaT = 0.01;
	/** Booleen de l'animation initialise a false */
	private boolean enCoursDAnimation = false;
	/**
	 * Boolean qui dewtermine si c'est la premiere fois qu'on parcourt le
	 * paintComponent.
	 */
	private boolean premiereFois = true;
	/** Temps du sleep de l'application */
	private int tempsDuSleep = 10;

	public ZoneAnimPhysique() {
		setBackground(Color.gray);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		
		
	}
	
	public void run() {

		while (enCoursDAnimation) {

			calculerUneIterationPhysique();


			repaint();
			try {
				Thread.sleep(tempsDuSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // fin while
	}
	
	public void demarrer() {

		if (!enCoursDAnimation) {
			Thread proc = new Thread(this);
			proc.start();
			enCoursDAnimation = true;

		}

	}
	
	
	/**
	 * Change le temps pour le sleep du thread.
	 * 
	 * @param tempsDuSleep Nouveua temps a appliquer au sleep.
	 */
	// Kevin Nguyen
	public void setTempsDuSleep(int tempsDuSleep) {
		this.tempsDuSleep = tempsDuSleep;
	}

	/**
	 * Retourne le temps de sleep actuel.
	 * 
	 * @return temps du sleep actuel.
	 */
	// Kevin Nguyen
	public int getTempsDuSleep() {
		return tempsDuSleep;
	}

	/**
	 * Modifie le pas (intervalle) de la simulation.
	 * 
	 * @param deltaT le pas (intervalle) de la simulation, exprime en secondes.
	 */
	// Kevin Nguyen
	public void setDeltaT(double deltaT) {
		this.deltaT = deltaT;
	}

	/**
	 * Retourne le pas intervalle) de la simulation.
	 * 
	 * @return le pas intervalle) de la simulation, exprime en secondes.
	 */
	// Kevin Nguyen
	public double getDeltaT() {
		return deltaT;
	}
	
	public void avancerUnPas() {
		arreter();
		calculerUneIterationPhysique();
		repaint();
	}

	private void arreter() {
		enCoursDAnimation = false;
		repaint();
		
	}

	private void calculerUneIterationPhysique() {
		
	}
	
	
}
