package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.PisteVirageDroit;
import utilitaireObjets.PisteVirageGauche;
import utilitaireObjets.PisteVirageHaut;
import utilitaireObjets.Voiture;

import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;

import utilitaireObjets.PisteVirageBas;

import utilitaireObjets.Voiture;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Cree une piste qui contient un/des voiture(s) et un/des obstacle(s)
 */

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

	/** Notre objet voiture **/
	private Voiture voiture;

	private PisteHorizontale pisteHorizontale;
	private PisteVerticale pisteVerticale;
	private PisteVirageBas pisteVirageBas;

	public ZoneAnimPhysique() {

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
					double x = voiture.getPosition().getX();
					x = x + 150;
					voiture.setPosition(new Vecteur2D(x, voiture.getPosition().getY()));
					System.out.println("ss");
				}
				repaint();
			}

		});
		setBackground(Color.gray);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (premiereFois) {
			pixelsParMetre = getWidth() / largeurDuComposantEnMetres;
			hauteurDuComposantEnMetres = getHeight() / pixelsParMetre;
			enCoursDAnimation = true;
			premiereFois = false;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//		g2d.scale(pixelsParMetre, pixelsParMetre);
		voiture = new Voiture(new Vecteur2D(0, 0), Color.yellow, 50, 25);
		voiture.setPixelsParMetre(pixelsParMetre);
		voiture.dessiner(g2d);

//		pisteHorizontale = new PisteHorizontale(30, 5);

		// pisteVerticale = new PisteVerticale(30,40);

//		pisteHorizontale.dessiner(g2d);

		// pisteVerticale.dessiner(g2d);

		pisteVerticale = new PisteVerticale(5, 100);
		pisteVerticale.dessiner(g2d);

		pisteVirageBas = new PisteVirageBas(200, 5);
		pisteVirageBas.dessiner(g2d);

		// pisteVerticale.dessiner(g2d);

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
