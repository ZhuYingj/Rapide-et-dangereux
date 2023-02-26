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
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeEvent;

/**
 * Cree une piste qui contient un/des voiture(s), un/des obstacle(s) et une
 * piste
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
	/** Valeur booléenne pour savoir si c'est la première fois qu'on dessine **/
	private boolean premiereFois = true;
	/** Valeur booléenne pour savoir si ces touches sont appuyés **/
	private boolean droite, gauche, haut, bas;
	/** Position x de la voiture **/
	double x = 0;
	/** Position y de la voiture **/
	double y = 0;
	/** L'angle de la voiture en degré **/
	private int angleVoitureDegre = 0;
	/** L'angle de la voiture en rad **/
	private double angleVoitureRad;
	/** Vecteur de la position initiale de la voiture **/
	private Vecteur2D posInit = new Vecteur2D(0.2, 0.1);

	private Vecteur2D valeurInit = new Vecteur2D(0.0, 0.0);

	/** Temps écoulé depuis le début de l'animation **/
	private double tempsTotalEcoule = 0;

	// support pour lancer des evenements de type PropertyChange
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * methode qui permettra de s'ajouter en tant qu'ecouteur
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public ZoneAnimPhysique() {

		voiture = new Voiture(posInit, Color.yellow, 50, 25, angleVoitureRad);

		// voiture.setSommeDesForces(MoteurPhysique.calculerForceFrottement(0.45,
		// voiture.getMasseEnKg(), angleVoitureRad)); // test
		// voiture.setVitesse(new Vecteur2D(20, 0));

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					droite = true;
					break;
				case KeyEvent.VK_LEFT:
					gauche = true;
					break;
				case KeyEvent.VK_DOWN:
					bas = true;
					break;
				case KeyEvent.VK_UP:
					haut = true;
					break;
				}

				if (droite == true) {
                    angleVoitureDegre = angleVoitureDegre + 10;
                    setAngle(angleVoitureDegre);
                    if (voiture.getAccel().getX() > 0) {
                        voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));
                    } else if (voiture.getAccel().getY() > 0) {
                        voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));

                    }

                }
				if (gauche == true) {

					angleVoitureDegre = angleVoitureDegre - 10;

					setAngle(angleVoitureDegre);
					if (voiture.getAccel().getX() > 0) {
						voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));
					} else if (voiture.getAccel().getY() > 0) {
						voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));

					}

				}
				if (haut == true) {
					voiture.setAccel(new Vecteur2D(10 * Math.cos(angleVoitureRad), 10 * Math.sin(angleVoitureRad)));
				}
				if (bas == true) {

				}

				repaint();

				// faire une methode KeyReleased
			}

			@Override
			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
//					voiture.setAccel(new Vecteur2D(0, 0));
					droite = false;
				case KeyEvent.VK_LEFT:
					gauche = false;
				case KeyEvent.VK_DOWN:
					bas = false;
				case KeyEvent.VK_UP:
					haut = false;
				}

//				if (e.getKeyCode() == KeyEvent.VK_UP) {
//					voiture.setAccel(new Vecteur2D(0, 0));
//				}

				repaint();

			}

		});
		setBackground(Color.gray);

	}

	/**
	 * Permet de dessiner une scene qui inclut ici une simple balle en mouvement
	 * 
	 * @param g Contexte graphique
	 */

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

	/**
	 * Animation de la voiture
	 */

	public void run() {

		while (enCoursDAnimation == true) {

			calculerUneIterationPhysique();
			testerCollisionsEtAjusterVitesses();
			repaint();
			try {
				Thread.sleep(tempsDuSleep);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // fin while
	}

	/**
	 * Demarre le thread s'il n'est pas deja demarre
	 */

	public void demarrer() {

		if (enCoursDAnimation == false) {
			Thread proc = new Thread(this);
			proc.start();
			enCoursDAnimation = true;
			repaint();

		}

	} // fin méthode

	/**
	 * Recommencer l'application avec les valeurs courantes.
	 */
	// Kevin Nguyen
	public void recommencer() {
		arreter();
		tempsTotalEcoule = 0.000;

		repaint();
	}
	public void avancerUnPas() {
        arreter();
        calculerUneIterationPhysique();
        repaint();
    }

	public void restartPos() {
		voiture.setPosition(posInit);
		voiture.setVitesse(valeurInit);
		voiture.setAccel(valeurInit);
		repaint();
	}

	public void arreterAnim() {
		arreter();
		repaint();
	}

	private void testerCollisionsEtAjusterVitesses() {
		voiture.gererCollision(getWidth(), 0, getHeight(), 0);
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

	/**
	 * Demande l'arret du thread (prochain tour de boucle)
	 */

	private void arreter() {
		enCoursDAnimation = false;
		repaint();

	}

	private void calculerUneIterationPhysique() {

		tempsTotalEcoule += deltaT;
		pcs.firePropertyChange("tempsEcoule", 0, tempsTotalEcoule);

		voiture.avancerUnPas(deltaT);

	}

	public void setVoitureMasse(double masseVoulu) {
		this.voiture.setMasseEnKg(masseVoulu);

	}

	public void setAngle(int nouvAngle) {
		angleVoitureRad = Math.toRadians(nouvAngle);
		voiture.setAngle(angleVoitureRad);

		repaint();
	}

	public Vecteur2D getPosInit() {
		return posInit;
	}

	public void setPosInit(Vecteur2D posInit) {
		this.posInit = posInit;
	}

	public double getTempsTotalEcoule() {
		return tempsTotalEcoule;
	}

	public void setTempsTotalEcoule(double tempsTotalEcoule) {
		this.tempsTotalEcoule = tempsTotalEcoule;
	}

}
