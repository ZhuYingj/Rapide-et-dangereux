package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import geometrie.Vecteur2D;
import physique.MoteurPhysique;
import pisteDeCourse.PisteMexique;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.Voiture;

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
	private double deltaT = 0.02;
	/** Booleen de l'animation initialise a false */
	private boolean enCoursDAnimation = false;
	/** Temps du sleep de l'application */
	private int tempsDuSleep = 10;

	/** Notre objet voiture **/
	private Voiture voiture = new Voiture();

	private PisteHorizontale pisteHorizontale;
	private PisteVerticale pisteVerticale;
	private PisteVirageBas pisteVirageBas;
	private boolean premiereFois = true;

	/** Position x de la voiture **/
	double x = 0;
	/** Position y de la voiture **/
	double y = 0;
	private int angleVoitureDegre = 0;
	private double angleVoitureRad;
	private Vecteur2D posInit = new Vecteur2D(0.2, 0.1);
	private double tempsTotalEcoule = 0;

	public ZoneAnimPhysique() {

		voiture = new Voiture(posInit, Color.yellow, 50, 25, angleVoitureRad);

		// voiture.setSommeDesForces(MoteurPhysique.calculerForceFrottement(0.45,
		// voiture.getMasseEnKg(), angleVoitureRad)); // test
		// voiture.setVitesse(new Vecteur2D(20, 0));

		System.out.println(voiture.getVitesse());

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("En cours d'animation");

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

					angleVoitureDegre = angleVoitureDegre + 10;
					setAngle(angleVoitureDegre);
					if (voiture.getAccel().getX() > 0) {
						voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));
					} else if (voiture.getAccel().getY() > 0) {
						voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));

					}

				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {

					angleVoitureDegre = angleVoitureDegre - 10;

					setAngle(angleVoitureDegre);
					if (voiture.getAccel().getX() > 0) {
						voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));
					} else if (voiture.getAccel().getY() > 0) {
						voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));

					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {

				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {

					voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));

					System.out.println(voiture.getAccel());
					System.out.println(voiture.getVitesse());
					System.out.println(voiture.getPosition());

				}

				repaint();

				// faire une methode KeyReleased
			}

			@Override
			public void keyReleased(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					voiture.setAccel(new Vecteur2D(0, 0));
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

		PisteMexique pisteMexique = new PisteMexique(0, 0);
		pisteMexique.dessiner(g2d);

		voiture.setPixelsParMetre(pixelsParMetre);

		voiture.dessiner(g2d);

	}

	public void run() {

		while (enCoursDAnimation == true) {

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

		if (enCoursDAnimation == false) {
			Thread proc = new Thread(this);
			proc.start();
			enCoursDAnimation = true;

		}

	}

	/**
	 * Recommencer l'application avec les valeurs courantes.
	 */
	// Kevin Nguyen
	public void recommencer() {
		arreter();
		tempsTotalEcoule = 0.000;

		repaint();
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
		tempsTotalEcoule += deltaT;

		voiture.avancerUnPas(deltaT);
		System.out.println(voiture.getAccel());

	}

	public void setVoitureMasse(double masseVoulu) {
		this.voiture.setMasseEnKg(masseVoulu);

	}

	public void setAngle(int nouvAngle) {
		angleVoitureRad = Math.toRadians(nouvAngle);
		voiture.setAngle(angleVoitureRad);

		repaint();
	}

}
