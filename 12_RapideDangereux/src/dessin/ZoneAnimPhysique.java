package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JPanel;

import application.GestionnaireDeFichiersSurLeBureau;
import geometrie.Vecteur2D;
import interfaces.TypeObjetSpecial;
import interfaces.TypePiste;
import physique.MoteurPhysique;
import pisteDeCourse.PisteCanada;
import pisteDeCourse.PisteItalie;
import pisteDeCourse.PisteMexique;
import utilitaireObjets.Accelerateur;
import utilitaireObjets.BouleDeNeige;
import utilitaireObjets.Champignon;
import utilitaireObjets.ObjetSpecial;
import utilitaireObjets.Regroupement;
import utilitaireObjets.Voiture;

/**
 * Cree une piste qui contient un/des voiture(s), un/des obstacle(s) et une
 * piste
 * 
 * @author Kevin Nguyen
 * @author Tan Tommy Rin
 * @author Alexis Pineda
 */

public class ZoneAnimPhysique extends JPanel implements Runnable {
	private GestionnaireDeFichiersSurLeBureau gestionFich;

	/** Largeur du composant en metres. */
	private double largeurDuComposantEnMetres = 640;
	/** Hauteur du composant en metres. */
	private double hauteurDuComposantEnMetres;
	/** Nombre de pixels pas metre. */
	private double pixelsParMetre;
	/** Temps du deltaT par d�faut */
	private double deltaT = 0.01;
	/** Booleen de l'animation initialise a false */
	private boolean enCoursDAnimation = false;
	/** Temps du sleep de l'application */
	private int tempsDuSleep = 5;
	/** Notre objet voiture **/
	private Voiture voiture;
	/** Valeur booléenne pour savoir si c'est la première fois qu'on dessine **/
	private boolean premiereFois = true;
	/** Valeur booléenne pour savoir si ces touches sont appuyés **/
	private boolean droite, gauche, haut, bas, space;
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
	private Vecteur2D posInit = new Vecteur2D(90, 20);
	/** Vecteur qui reset les valeurs a 0 **/
	private Vecteur2D valeurInit = new Vecteur2D(0.0, 0.0);
	/** Temps écoulé depuis le début de l'animation **/
	private double tempsTotalEcoule = 0;
	/** support pour lancer des evenements de type PropertyChange **/
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	/** La premiere piste affiché **/
	private PisteMexique pisteMexique;
	/** Piste Italie **/
	private PisteItalie pisteItalie;
	/** Vecteur de la force de freinage **/
	private Vecteur2D forceFreinage;
	/** Valeur pour tester le frottement **/
	private double testFrottement = 0.45;
	/** L'objet regroupement **/
	private Regroupement regroupement;
	/** Le type de piste choisi **/
	private TypePiste typePiste = TypePiste.CANADA;
	/** L'objet special **/
	private ObjetSpecial objSpecial;
	/** Piste Canada **/
	private PisteCanada pisteCanada;

	private String nomFichierRegroupement = "regroupement2.dat";

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la zone d'animation
	 */
	// Kevin Nguyen
	public ZoneAnimPhysique() {
		gestionFich = new GestionnaireDeFichiersSurLeBureau();
		pisteMexique = new PisteMexique(0, 0);
		pisteItalie = new PisteItalie(0, 0);
		pisteCanada = new PisteCanada(0, 0);
		voiture = new Voiture(posInit, Color.yellow, 50, 16, angleVoitureRad, 60);

		regroupement = new Regroupement(voiture, 3, typePiste);

//		objSpecial = new ObjetSpecial(new Vecteur2D(getWidth() / 2.0, getHeight() / 2.0), 20,
//				TypeObjetSpecial.BOULEDENEIGE);

//		objSpecial = new ObjetSpecial(new Vecteur2D(90, 40), 20, TypeObjetSpecial.BOULEDENEIGE);
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				appuyerPlusieursToucheEnMemeTemps(e);
				orientationVoitureSelonTouche(e);

				repaint();

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
			regroupement.getListePisteDeDepart().get(0).getVoiture()
					.setPosition(new Vecteur2D(regroupement.getListePisteDeDepart().get(0).getX(),
							regroupement.getListePisteDeDepart().get(0).getY()));
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		regroupement.setPixelsParMetre(pixelsParMetre);
		regroupement.dessiner(g2d);
//		objSpecial.setPixelParMetre(pixelsParMetre);
//		objSpecial.dessiner(g2d);
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
		case KeyEvent.VK_Q:
			space = true;
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
	public void orientationVoitureSelonTouche(KeyEvent e) {

		if (droite == true) {

			angleVoitureDegre = (int) (Math
					.toDegrees(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()) + 10);
			if (angleVoitureDegre > 350) {
				angleVoitureDegre = 0;
			} else if (angleVoitureDegre < 10) {
				angleVoitureDegre = 360;
			}
			setAngle(angleVoitureDegre);

			if (regroupement.getListePisteDeDepart().get(0).getVoiture().getAccel().getX() == 0) {
				regroupement.getListePisteDeDepart().get(0).getVoiture().setVitesse((new Vecteur2D(
						voiture.getVitesse().module()
								* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
						voiture.getVitesse().module()
								* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()))));
			} else if (regroupement.getListePisteDeDepart().get(0).getVoiture().getAccel().getY() == 0) {
				regroupement.getListePisteDeDepart().get(0).getVoiture().setVitesse(new Vecteur2D(
						voiture.getVitesse().module()
								* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
						voiture.getVitesse().module()
								* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle())));

			}

		}
		if (gauche == true) {

			angleVoitureDegre = (int) (Math
					.toDegrees(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()) - 10);
			if (angleVoitureDegre > 370) {
				angleVoitureDegre = 0;
			} else if (angleVoitureDegre < 10) {
				angleVoitureDegre = 360;
			}
			setAngle(angleVoitureDegre);
			if (regroupement.getListePisteDeDepart().get(0).getVoiture().getAccel().getX() == 0) {
				regroupement.getListePisteDeDepart().get(0).getVoiture().setVitesse(new Vecteur2D(
						voiture.getVitesse().module()
								* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
						voiture.getVitesse().module()
								* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle())));
			} else if (regroupement.getListePisteDeDepart().get(0).getVoiture().getAccel().getY() == 0) {
				regroupement.getListePisteDeDepart().get(0).getVoiture().setVitesse(new Vecteur2D(
						voiture.getVitesse().module()
								* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
						voiture.getVitesse().module()
								* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle())));

			}

		}
		if (haut == true) {
			regroupement.getListePisteDeDepart().get(0).getVoiture()
					.setAccel(new Vecteur2D(
							20 * Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
							20 * Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle())));
		}

		if (space == true) {
			System.out.println("yo");
		}
	}

	/**
	 * Animation de la voiture
	 */
	// Kevin Nguyen
	public void run() {

		while (enCoursDAnimation == true) {

			calculerUneIterationPhysique();

			if (regroupement.getListePisteDeDepart().get(0).getVoiture().getVitesse()
					.module(voiture.getVitesse()) > voiture.getVitesseMaxSelonNiveau()) {
				voiture.setVitesse(new Vecteur2D(
						voiture.getVitesseMaxSelonNiveau()
								* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
						voiture.getVitesseMaxSelonNiveau()
								* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle())));

			}

			regroupement.enCollisionAvec(voiture);

			if (haut == false) {
				voiture.setAccel(valeurInit);
			}

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
		if (regroupement.getListePisteDeDepart().get(0).getVoiture().getVitesse().module(voiture.getVitesse()) > voiture
				.getVitesseMaxSelonNiveau()) {
			voiture.setVitesse(new Vecteur2D(
					voiture.getVitesseMaxSelonNiveau()
							* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
					voiture.getVitesseMaxSelonNiveau()
							* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle())));

		}
		regroupement.enCollisionAvec(voiture);
		repaint();
	}

	/**
	 * Méthode qui permet de réinitialiser la position de la voiture
	 */
	// Alexis Pineda-Alvarado
	public void restartPos() {
		arreter();
		tempsTotalEcoule = 0.000;

		regroupement.getListePisteDeDepart().get(0).getVoiture().setPosition(posInit);
		regroupement.getListePisteDeDepart().get(0).getVoiture().setVitesse(valeurInit);
		regroupement.getListePisteDeDepart().get(0).getVoiture().setAccel(valeurInit);
		regroupement.getListePisteDeDepart().get(0).getVoiture().setAngle(0);

		angleVoitureDegre = 0;
		regroupement.resetTour();
		pcs.firePropertyChange("tempsEcoule", 0, tempsTotalEcoule);

		repaint();
	}

	/*
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

	/**
	 * Méthode qui permet de détecter les levés d'évènement et changer le texte
	 */

	// Par Tan Tommy Rin
	public void changementTexteParIteration() {
		pcs.firePropertyChange("tempsEcoule", 0, tempsTotalEcoule);
		pcs.firePropertyChange("accEnXV1", 0, voiture.getAccel().getX());
		pcs.firePropertyChange("accEnYV1", 0, voiture.getAccel().getY());
		pcs.firePropertyChange("vitEnXV1", 0, voiture.getVitesse().getX());
		pcs.firePropertyChange("vitEnYV1", 0, voiture.getVitesse().getY());
		pcs.firePropertyChange("posEnXV1", 0, voiture.getPosition().getX() / pixelsParMetre);
		pcs.firePropertyChange("posEnYV1", 0, voiture.getPosition().getY() / pixelsParMetre);
		pcs.firePropertyChange("angleV1", 0, voiture.getAngle());
		pcs.firePropertyChange("nombreToursV1", 0, regroupement.getTour());

	}

	/**
	 * Calcul d'une interation physique
	 */
	// Kevin Nguyen
	private void calculerUneIterationPhysique() {

		tempsTotalEcoule += deltaT;
		changementTexteParIteration();

		Vecteur2D forceTotal = new Vecteur2D(MoteurPhysique.calculerForceFrottement(0.45,
				regroupement.getListePisteDeDepart().get(0).getVoiture().getMasseEnKg(),
				regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()));

		if (bas == true) {

			forceFreinage = new Vecteur2D(MoteurPhysique
					.calculerForceFrottement(testFrottement,
							regroupement.getListePisteDeDepart().get(0).getVoiture().getMasseEnKg(), angleVoitureRad)

					.multiplie(2));

			forceTotal = forceTotal.additionne(forceFreinage);

		}
		if (haut == false && regroupement.getListePisteDeDepart().get(0).getVoiture().getVitesse().module() != 0) {

			regroupement.getListePisteDeDepart().get(0).getVoiture().setSommeDesForces(forceTotal);
			if (regroupement.getListePisteDeDepart().get(0).getVoiture().getVitesse().module() < 0.3) {
				regroupement.getListePisteDeDepart().get(0).getVoiture().setVitesse(new Vecteur2D(0, 0));
			}

		}

		regroupement.avancerGroupe(deltaT, tempsTotalEcoule);

	}

	/**
	 * Attribuer une nouvelle masse a la voiture
	 * 
	 * @param masseVoulu Nouvelle masse
	 */
	// Kevin Nguyen
	public void setVoitureMasse(double masseVoulu) {
		this.voiture.setMasseEnKg(masseVoulu);
		this.voiture.setMasseEnKgInitial(masseVoulu);

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
	 * 
	 * @return La valeur boolean de l'animation
	 */
	// Kevin Nguyen
	public boolean isEnCoursDAnimation() {
		return enCoursDAnimation;
	}

	/**
	 * Attribue une nouvelle valeur boolean à la zone d'animation
	 * 
	 * @param enCoursDAnimation Nouvelle valeur boolean de l'animation
	 */
	// Kevin Nguyen
	public void setEnCoursDAnimation(boolean enCoursDAnimation) {
		this.enCoursDAnimation = enCoursDAnimation;
	}

	public double getTestFrottement() {
		return testFrottement;
	}

	public void setTestFrottement(double testFrottement) {
		this.testFrottement = testFrottement;
	}

	/**
	 * Méthode qui permet de retourner le type de piste
	 * 
	 * @return le type de piste
	 */
	// Par Tan Tommy Rin
	public TypePiste getTypePiste() {
		return typePiste;
	}

	public Regroupement getRegroupement() {
		return regroupement;
	}

	public void setRegroupement(Regroupement regroupement) {
		this.regroupement = regroupement;
	}

	/**
	 * Méthode qui change le type de piste et change tous les listes des morceaux
	 * par ceux de la piste choisi
	 * 
	 * @param typePiste le type de piste voulu
	 */
	// Par Tan Tommy Rin
	public void setTypePiste(TypePiste typePiste) {

		this.typePiste = typePiste;
		regroupement.setType(typePiste);
		if (typePiste == TypePiste.MEXIQUE) {
			regroupement.setListePisteDeDepart(pisteMexique.getDepart());
			regroupement.setListePisteHorizontale(pisteMexique.getHorizontale());
			regroupement.setListePisteVerticale(pisteMexique.getVerticale());
			regroupement.setListePisteVirageBas(pisteMexique.getBas());
			regroupement.setListePisteVirageDroit(pisteMexique.getDroit());
			regroupement.setListePisteVirageGauche(pisteMexique.getGauche());
			regroupement.setListePisteVirageHaut(pisteMexique.getHaut());
			regroupement.getListePisteDeDepart().get(0).setVoiture(voiture);

		}
		if (typePiste == TypePiste.ITALIE) {
			regroupement.setListePisteDeDepart(pisteItalie.getDepart());
			regroupement.setListePisteHorizontale(pisteItalie.getHorizontale());
			regroupement.setListePisteVerticale(pisteItalie.getVerticale());
			regroupement.setListePisteVirageBas(pisteItalie.getBas());
			regroupement.setListePisteVirageDroit(pisteItalie.getDroit());
			regroupement.setListePisteVirageGauche(pisteItalie.getGauche());
			regroupement.setListePisteVirageHaut(pisteItalie.getHaut());
			regroupement.getListePisteDeDepart().get(0).setVoiture(voiture);
		}
		if (typePiste == TypePiste.CANADA) {
			regroupement.setListePisteDeDepart(pisteCanada.getDepart());
			regroupement.setListePisteHorizontale(pisteCanada.getHorizontale());
			regroupement.setListePisteVerticale(pisteCanada.getVerticale());
			regroupement.setListePisteVirageBas(pisteCanada.getBas());
			regroupement.setListePisteVirageDroit(pisteCanada.getDroit());
			regroupement.setListePisteVirageGauche(pisteCanada.getGauche());
			regroupement.setListePisteVirageHaut(pisteCanada.getHaut());
			regroupement.getListePisteDeDepart().get(0).setVoiture(voiture);

		}
		if (typePiste == TypePiste.AUTRE) {

			Regroupement regroupementTempo = (gestionFich.lireFichierBinBureau(nomFichierRegroupement));
			regroupement.setListePisteDeDepart(regroupementTempo.getListePisteDeDepart());
			regroupement.setListePisteHorizontale(regroupementTempo.getListePisteHorizontale());
			regroupement.setListePisteVerticale(regroupementTempo.getListePisteVerticale());
			regroupement.setListePisteVirageBas(regroupementTempo.getListePisteVirageBas());
			regroupement.setListePisteVirageDroit(regroupementTempo.getListePisteVirageDroit());
			regroupement.setListePisteVirageGauche(regroupementTempo.getListePisteVirageGauche());
			regroupement.setListePisteVirageHaut(regroupementTempo.getListePisteVirageHaut());
			voiture.setPosition(new Vecteur2D(regroupement.getListePisteDeDepart().get(0).getX(),
					regroupement.getListePisteDeDepart().get(0).getY()));

			regroupement.getListePisteDeDepart().get(0).setVoiture(voiture);

		}

	}

	public String getNomFichierRegroupement() {
		return nomFichierRegroupement;
	}

	public void setNomFichierRegroupement(String nomFichierRegroupement) {
		this.nomFichierRegroupement = nomFichierRegroupement;
	}

}
