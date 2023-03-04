package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;

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
 * 
 * @author Kevin Nguyen
 * @author Tan Tommy Rin
 * @author Alexis Pineda
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
	private Voiture voiture;
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
	/** angle d'un segment de virage en degré **/
	private int angleCoinDegre = 45;
	/** angle d'un segment de virage en radians **/
	private double angleCoinRad = Math.toRadians(angleCoinDegre);
	/** Vecteur de la position initiale de la voiture **/
	private Vecteur2D posInit = new Vecteur2D(80, 0.1);
	/** Vecteur qui reset les valeurs a 0 **/
	private Vecteur2D valeurInit = new Vecteur2D(0.0, 0.0);
	/** Temps écoulé depuis le début de l'animation **/
	private double tempsTotalEcoule = 0;
	/** support pour lancer des evenements de type PropertyChange **/
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	/** La premiere piste affiché  **/
	private PisteMexique mexique;
	/** Aire du triangle superieur gauche  **/
	private Area aireTriangle1;
	/** Aire du triangle inferieur droit **/
	private Area aireTriangle2;
	/** Aire du triangle superieur droit  **/
	private Area aireTriangle3;
	/** Aire du triangle inferieur gauche  **/
	private Area aireTriangle4;
	/** Aire de la voiture  **/
	private Area aireVoiture1;
	/** Premiere copie de l'aire de la voiture **/
	private Area aireVoiture2;
	/** Deuxieme copie de l'aire de la voiture **/
	private Area aireVoiture3;
	/** Troisieme copie de l'aire de la voiture **/
	private Area aireVoiture4;

	/**
	 * methode qui permettra de s'ajouter en tant qu'ecouteur
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la zone d'animation
	 */
	// Kevin Nguyen
	public ZoneAnimPhysique() {

		voiture = new Voiture(posInit, Color.yellow, 50, 25, angleVoitureRad, 60);

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				appuyerPlusieursToucheEnMemeTemps(e);
				OritentationVoitureSelonTouche(e);

				repaint();

				// faire une methode KeyReleased
			}

			@Override
			public void keyReleased(KeyEvent e) {
				relachementTouches(e);
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
	// Kevin Nguyen
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

		mexique = new PisteMexique(1, 1);
		mexique.dessiner(g2d);
		aireTriangle1 = mexique.getBas().getAireTriangle();
		aireTriangle2 = mexique.getDroit().getAireTriangle();
		aireTriangle3 = mexique.getGauche().getAireTriangle();
		aireTriangle4 = mexique.getHaut().getAireTriangle();

		voiture.setPixelsParMetre(pixelsParMetre);

		voiture.dessiner(g2d);

		aireVoiture1 = new Area(voiture.getCercle());
		aireVoiture2 = new Area(aireVoiture1);
		aireVoiture3 = new Area(aireVoiture1);
		aireVoiture4 = new Area(aireVoiture1);
	}

	/**
	 * Méthode qui détecte quand plusieurs touches sont appuyés en même temps
	 * 
	 * @param e Évènement de clavier
	 */
	// Par Tan Tommy Rin
	public void appuyerPlusieursToucheEnMemeTemps(KeyEvent e) {
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
	}

	/**
	 * Méthode qui détecte lorsqu'une touche est relaché et change la valeur associé
	 * à faux
	 * 
	 * @param e Évènement de clavier
	 */
	// Par Tan Tommy Rin
	public void relachementTouches(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			droite = false;
			break;
		case KeyEvent.VK_LEFT:
			gauche = false;
			break;
		case KeyEvent.VK_DOWN:
			bas = false;
			break;
		case KeyEvent.VK_UP:
			haut = false;
			break;

		}

	}

	/**
	 * Méthode qui change l'orientation de la voiture selon la touche appuyé
	 * 
	 * @param e Évènement du clavier
	 */
	// Kevin Nguyen
	public void OritentationVoitureSelonTouche(KeyEvent e) {
		if (droite == true) {
			angleVoitureDegre = angleVoitureDegre + 10;
			setAngle(angleVoitureDegre);
			if (voiture.getAccel().getX() > 0) {
				voiture.setAccel(new Vecteur2D(20 * Math.cos(angleVoitureRad), 20 * Math.sin(angleVoitureRad)));
			} else if (voiture.getAccel().getY() > 0) {
				voiture.setAccel(new Vecteur2D(20 * Math.cos(angleVoitureRad), 20 * Math.sin(angleVoitureRad)));

			}

		}
		if (gauche == true) {

			angleVoitureDegre = angleVoitureDegre - 10;

			setAngle(angleVoitureDegre);
			if (voiture.getAccel().getX() > 0) {
				voiture.setAccel(new Vecteur2D(20 * Math.cos(angleVoitureRad), 20 * Math.sin(angleVoitureRad)));
			} else if (voiture.getAccel().getY() > 0) {
				voiture.setAccel(new Vecteur2D(20 * Math.cos(angleVoitureRad), 20 * Math.sin(angleVoitureRad)));

			}

		}
		if (haut == true) {
			voiture.setAccel(new Vecteur2D(20 * Math.cos(angleVoitureRad), 20 * Math.sin(angleVoitureRad)));
		}
		if (bas == true) {

		}
	}

	/**
	 * Animation de la voiture
	 */
	// Kevin Nguyen
	public void run() {

		while (enCoursDAnimation == true) {

			calculerUneIterationPhysique();
			if (voiture.getVitesse().module(voiture.getVitesse()) > voiture.getVitesseMaxSelonNiveau()) {
				voiture.setVitesse(new Vecteur2D(voiture.getVitesseMaxSelonNiveau() * Math.cos(angleVoitureRad),
						voiture.getVitesseMaxSelonNiveau() * Math.sin(angleVoitureRad)));

			}

			collisionCote();
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
	// Kevin Nguyen
	public void demarrer() {

		if (enCoursDAnimation == false) {

			Thread proc = new Thread(this);
			proc.start();
			enCoursDAnimation = true;
			repaint();

		}

	} // fin méthode

	/**
	 * Avancer de un pas l'animation
	 */
	// Kevin Nguyen
	public void avancerUnPas() {
		arreter();
		calculerUneIterationPhysique();
		testerCollisionsEtAjusterVitesses();
		collisionCote();
		repaint();
	}

	/**
	 * va reinitier la position de la voiture
	 */
	// Alexis Pineda-Alvarado
	public void restartPos() {
		arreter();
		tempsTotalEcoule = 0.000;
		voiture.setPosition(posInit);
		voiture.setVitesse(valeurInit);
		voiture.setAccel(valeurInit);
		pcs.firePropertyChange("tempsEcoule", 0, tempsTotalEcoule);

		repaint();
	}

	/**
	 * Tester si la voiture entre en collision avec les extremites du composant
	 * dessin. Si oui, ajuste la position et calcule la nouvelle vitesse de la
	 * voiture.
	 */
	// Kevin Nguyen
	private void testerCollisionsEtAjusterVitesses() {
		voiture.gererCollision(getWidth(), 0, getHeight(), 0);
		if (haut == false) {
			voiture.setAccel(valeurInit);
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

	/**
	 * Demande l'arret du thread (prochain tour de boucle)
	 */
	// Kevin Nguyen
	public void arreter() {
		enCoursDAnimation = false;
		repaint();

	}

	// Par Tan Tommy Rin
	public void changementTexteParIteration() {
		pcs.firePropertyChange("tempsEcoule", 0, tempsTotalEcoule);

		pcs.firePropertyChange("accEnXV1", 0, voiture.getAccel().getX());
		pcs.firePropertyChange("accEnYV1", 0, voiture.getAccel().getY());
		pcs.firePropertyChange("vitEnXV1", 0, voiture.getVitesse().getX());
		pcs.firePropertyChange("vitEnYV1", 0, voiture.getVitesse().getY());

		pcs.firePropertyChange("posEnXV1", 0, voiture.getPosition().getX() / pixelsParMetre);
		pcs.firePropertyChange("posEnYV1", 0, voiture.getPosition().getY() / pixelsParMetre);
	}

	/**
	 * Calcul d'une interation physique
	 */
	// Kevin Nguyen
	private void calculerUneIterationPhysique() {

		tempsTotalEcoule += deltaT;
		changementTexteParIteration();

		voiture.avancerUnPas(deltaT);

	}

	/**
	 * Attribuer une nouvelle masse a la voiture
	 * 
	 * @param masseVoulu Nouvelle masse
	 */
	// Kevin Nguyen
	public void setVoitureMasse(double masseVoulu) {
		this.voiture.setMasseEnKg(masseVoulu);

	}

	/**
	 * Attribuer un nouvel angle en rad
	 * 
	 * @param nouvAngle Angle en degre
	 */
	// Kevin Nguyen
	public void setAngle(int nouvAngle) {
		angleVoitureRad = Math.toRadians(nouvAngle);
		voiture.setAngle(angleVoitureRad);



		repaint();
	}

	/**
	 * Retourne la position initiale de la voiture
	 * 
	 * @return Position initiale de la voiture
	 */
	// Kevin Nguyen
	public Vecteur2D getPosInit() {
		return posInit;
	}

	/**
	 * Attribuer une nouvelle position initiale a la voiture
	 * 
	 * @param posInit Nouvelle position initiale
	 */
	// Kevin Nguyen
	public void setPosInit(Vecteur2D posInit) {
		this.posInit = posInit;
	}

	/**
	 * Retourne le temps total ecoule
	 * 
	 * @return Temps total ecoule
	 */
	// Kevin Nguyen
	public double getTempsTotalEcoule() {
		return tempsTotalEcoule;
	}

	/**
	 * Attribuer un nouveau temps total ecoule
	 * 
	 * @param tempsTotalEcoule Nouveau temps total ecoule
	 */
	// Kevin Nguyen
	public void setTempsTotalEcoule(double tempsTotalEcoule) {
		this.tempsTotalEcoule = tempsTotalEcoule;
	}

	/**
	 * Méthode qui permet de changer la vitesse maximale de la voiture dans la zone
	 * d'animation
	 * 
	 * @param nouvelleVitesseMax la nouvelle vitesse maximale
	 */
	// Par Tan Tommy Rin
	public void setVoitureVitesseMax(double nouvelleVitesseMax) {
		voiture.setVitesseMaxSelonNiveau(nouvelleVitesseMax);

	}

	/**
	 * Retourne la valeur boolean de l'animation
	 * @return La valeur boolean de l'animation
	 */
	//Kevin Nguyen
	public boolean isEnCoursDAnimation() {
		return enCoursDAnimation;
	}
	/**
	 * Attribue une nouvelle valeur boolean à la zone d'animation
	 * @param enCoursDAnimation Nouvelle valeur boolean de l'animation
	 */
	//Kevin Nguyen
	public void setEnCoursDAnimation(boolean enCoursDAnimation) {
		this.enCoursDAnimation = enCoursDAnimation;
	}

	/**
	 * Calcul des collisions sur les virages
	 */
	//Kevin Nguyen
	public void collisionCote() {

		double pos = 3;

		aireVoiture1.intersect(aireTriangle1);
		aireVoiture2.intersect(aireTriangle2);
		aireVoiture3.intersect(aireTriangle3);
		aireVoiture4.intersect(aireTriangle4);



		if(!aireVoiture1.isEmpty()) {
			System.out.println("test");
			try {
				Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), 45);
				voiture.setVitesse(vit);
				voiture.setPosition(new Vecteur2D(voiture.getPosition().getX()+pos, voiture.getPosition().getY()+pos));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		} else if (!aireVoiture2.isEmpty()) {
			System.out.println("test");
			try {
				Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), 215);
				voiture.setVitesse(vit);
				voiture.setPosition(new Vecteur2D(voiture.getPosition().getX()-pos, voiture.getPosition().getY()-pos));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} else if (!aireVoiture3.isEmpty()) {
			System.out.println("test");
			try {
				Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), 135);
				voiture.setVitesse(vit);
				voiture.setPosition(new Vecteur2D(voiture.getPosition().getX()-pos, voiture.getPosition().getY()+pos));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} else if (!aireVoiture4.isEmpty()) {
			System.out.println("test");
			try {
				Vecteur2D vit =	MoteurPhysique.calculerVitesseCollisionAngle(voiture.getVitesse(), 315);
				voiture.setVitesse(vit);
				voiture.setPosition(new Vecteur2D(voiture.getPosition().getX()+pos, voiture.getPosition().getY()-pos));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}
}
