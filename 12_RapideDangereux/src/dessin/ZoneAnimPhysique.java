package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.GestionnaireDeFichiersSurLeBureau;
import application.InfoLigne;
import fenetre.FenetreJeuScientifique;
import geometrie.Vecteur2D;
import interfaces.TypeObjetSpecial;
import interfaces.TypePiste;
import physique.MoteurPhysique;
import pisteDeCourse.PisteCanada;
import pisteDeCourse.PisteItalie;
import pisteDeCourse.PisteMexique;
import utilitaireObjets.Regroupement;
import utilitaireObjets.Voiture;

/**
 * * Composant illustrant la simulation : c'est le regroupement physique ou on
 * dessine et anime les objets. C'est ce composant qui doit connaitre/determiner
 * les parametres physiques : largeur du composant en metres, valeur du pas de
 * simulation deltaT, positions et vitesses initiales des objets etc. etc.
 * L'animation d'un regroupement qui contient deux voitures, des morceaux de
 * pistes et des boites mystères contenant des obstacles.
 * 
 * @author Kevin Nguyen
 * @author Tan Tommy Rin
 * @author Alexis Pineda-Alvarado
 * @author Ludovic Julien
 */

public class ZoneAnimPhysique extends JPanel implements Runnable {
	private GestionnaireDeFichiersSurLeBureau gestionFich;
	/** Le nombre de boite mystere **/
	private int nombreBlocMystere = 3;

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
	/** Notre objet voiture 1 **/
	private Voiture voiture;
	/** Valeur booléenne pour savoir si c'est la première fois qu'on dessine **/
	private boolean premiereFois = true;
	/** Valeur booléenne pour savoir si ces touches sont appuyés **/
	private boolean droite, gauche, haut, bas, space, d, a, s, w, q;
	/** Position x de la voiture **/
	private double x = 0;
	/** Position y de la voiture **/
	private double y = 0;
	/** L'angle de la voiture 1 en degré **/
	private int angleVoitureDegre = 0;
	/** L'angle de la voiture en rad **/
	private double angleVoitureRad, angleVoitureRad2;
	/** angle d'un segment de virage en degré **/
	private int angleCoinDegre = 45;
	/** angle d'un segment de virage en radians **/
	private double angleCoinRad = Math.toRadians(angleCoinDegre);
	/** Vecteur de la position initiale de la voiture **/
	private Vecteur2D posInit = new Vecteur2D(90, 20);
	/** Vecteur de la position initiale de la voiture **/
	private Vecteur2D posInit2 = new Vecteur2D(90, 40);
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
	private Vecteur2D forceFreinage, forceFreinage2;
	/** Valeur pour tester le frottement **/
	private double testFrottement = 0.45;
	/** L'objet regroupement **/
	private Regroupement regroupement;
	/** Le type de piste choisi **/
	private TypePiste typePiste = TypePiste.CANADA;
	/** Piste Canada **/
	private PisteCanada pisteCanada;

	private String nomFichierRegroupement = "regroupement2.dat";
	/** Notre objet voiture 2 **/
	private Voiture voiture2;
	/** L'angle de la voiture 2 en degré **/
	private int angleVoitureDegre2;

	private double forceDeLancement = 50;
	private double forceDeLancement2 = 50;
	private Clip newClip;
	private String piste;

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 * 
	 * @param listener L'écouteur
	 */
	// Tan Tommy Rin
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		regroupement.addPropertyChangeListener(listener);
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

		voiture2 = new Voiture(posInit2, Color.cyan, 50, 16, angleVoitureRad2, 60);

		regroupement = new Regroupement(voiture, nombreBlocMystere, typePiste);

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

	}

	/**
	 * Permet de dessiner un regroupement qui inclut ici deux voitures et des boites
	 * mysteres contenant des obstacles.
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
							regroupement.getListePisteDeDepart().get(0).getY() + 10));

			regroupement.getListePisteDeDepart().get(0).getVoiture2()
					.setPosition(new Vecteur2D(regroupement.getListePisteDeDepart().get(0).getX(),
							regroupement.getListePisteDeDepart().get(0).getY() + 50));

		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		regroupement.setPixelsParMetre(pixelsParMetre);
		regroupement.dessiner(g2d);

	}

	/**
	 * Méthode qui détecte quand plusieurs touches sont appuyés en même temps
	 * 
	 * @param e Évènement de clavier
	 */
	// Tan Tommy Rin
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
		case KeyEvent.VK_SPACE:
			space = true;
			break;
		case KeyEvent.VK_Q:
			q = true;
			break;
		case KeyEvent.VK_D:
			d = true;
			break;
		case KeyEvent.VK_A:
			a = true;
			break;
		case KeyEvent.VK_S:
			s = true;
			break;
		case KeyEvent.VK_W:
			w = true;
			break;
		}
	}

	/**
	 * Méthode qui détecte lorsqu'une touche est relaché et change la valeur associé
	 * à faux
	 * 
	 * @param e Évènement de clavier
	 */
	// Tan Tommy Rin
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
			pcs.firePropertyChange("freinageV1Reset", 0, -1);
			break;
		case KeyEvent.VK_UP:
			haut = false;
			break;
		case KeyEvent.VK_D:
			d = false;
			break;
		case KeyEvent.VK_A:
			a = false;
			break;
		case KeyEvent.VK_S:
			s = false;
			pcs.firePropertyChange("freinageV2Reset", 0, -1);
			break;
		case KeyEvent.VK_W:
			w = false;
			break;
		case KeyEvent.VK_SPACE:
			space = false;
			break;
		case KeyEvent.VK_Q:
			q = false;
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

			if (regroupement.getListePisteDeDepart().get(0).getVoiture().getAccel().module() == 0) {
				regroupement.getListePisteDeDepart().get(0).getVoiture().setVitesse((new Vecteur2D(
						voiture.getVitesse().module()
								* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
						voiture.getVitesse().module()
								* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()))));
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
			if (regroupement.getListePisteDeDepart().get(0).getVoiture().getAccel().module() == 0) {
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

		} else if ((regroupement.getObjSpecial() != null && regroupement.getObjSpecial().getColle()
				.collisionDeLaVoiture(regroupement.getListePisteDeDepart().get(0).getVoiture()) == true)
				|| (regroupement.getObjSpecial2() != null && regroupement.getObjSpecial2().getColle()
						.collisionDeLaVoiture(regroupement.getListePisteDeDepart().get(0).getVoiture()) == true)) {

		} else {

			regroupement.getListePisteDeDepart().get(0).getVoiture()
					.setAccel(new Vecteur2D(
							20 * Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
							20 * Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle())));
		}

		if (d == true) {

			angleVoitureDegre2 = (int) (Math
					.toDegrees(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()) + 10);
			if (angleVoitureDegre2 > 350) {
				angleVoitureDegre2 = 0;
			} else if (angleVoitureDegre2 < 10) {
				angleVoitureDegre2 = 360;
			}
			setAngle2(angleVoitureDegre2);

			if (regroupement.getListePisteDeDepart().get(0).getVoiture2().getAccel().module() == 0) {
				regroupement.getListePisteDeDepart().get(0).getVoiture2().setVitesse((new Vecteur2D(
						voiture2.getVitesse().module()
								* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()),
						voiture2.getVitesse().module()
								* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()))));
			}

		}
		if (a == true) {

			angleVoitureDegre2 = (int) (Math
					.toDegrees(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()) - 10);
			if (angleVoitureDegre2 > 370) {
				angleVoitureDegre2 = 0;
			} else if (angleVoitureDegre2 < 10) {
				angleVoitureDegre2 = 360;
			}
			setAngle2(angleVoitureDegre2);
			if (regroupement.getListePisteDeDepart().get(0).getVoiture2().getAccel().module() == 0) {
				regroupement.getListePisteDeDepart().get(0).getVoiture2().setVitesse(new Vecteur2D(
						voiture2.getVitesse().module()
								* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()),
						voiture2.getVitesse().module()
								* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle())));
			}
		}
		if (w == true) {
			regroupement.getListePisteDeDepart().get(0).getVoiture2()
					.setAccel(new Vecteur2D(
							20 * Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()),
							20 * Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle())));

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

			if (regroupement.getListePisteDeDepart().get(0).getVoiture2().getVitesse()
					.module(voiture2.getVitesse()) > voiture2.getVitesseMaxSelonNiveau()) {
				voiture2.setVitesse(new Vecteur2D(
						voiture2.getVitesseMaxSelonNiveau()
								* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()),
						voiture2.getVitesseMaxSelonNiveau()
								* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle())));

			}

			regroupement.enCollisionAvec(voiture);
			regroupement.enCollisionAvec(voiture2);
			try {
				regroupement.getListePisteDeDepart().get(0).getVoiture()
						.collisionEntreVoiture(regroupement.getListePisteDeDepart().get(0).getVoiture2());
			} catch (Exception e1) {

				e1.printStackTrace();
			}

			if (haut == false) {
				if ((regroupement.getObjSpecial() != null
						&& regroupement.getObjSpecial().getType() == TypeObjetSpecial.TROUNOIR
						&& regroupement.getObjSpecial().getTrouNoir()
								.collisionDeLaVoiture(regroupement.getListePisteDeDepart().get(0).getVoiture()) == true)
						|| (regroupement.getObjSpecial2() != null
								&& regroupement.getObjSpecial2().getType() == TypeObjetSpecial.TROUNOIR
								&& regroupement.getObjSpecial2().getTrouNoir().collisionDeLaVoiture(
										regroupement.getListePisteDeDepart().get(0).getVoiture()) == true)) {

				} else if ((regroupement.getObjSpecial() != null && regroupement.getObjSpecial().getColle()
						.collisionDeLaVoiture(regroupement.getListePisteDeDepart().get(0).getVoiture()) == true)
						|| (regroupement.getObjSpecial2() != null
								&& regroupement.getObjSpecial2().getColle().collisionDeLaVoiture(
										regroupement.getListePisteDeDepart().get(0).getVoiture()) == true)) {

				} else if (regroupement.getListeAccelerateur().size() == 1 && regroupement.getListeAccelerateur().get(0)
						.contient(regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
								regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY())) {

				} else if (regroupement.getListeAccelerateur().size() == 2 && (regroupement.getListeAccelerateur()
						.get(1).contient(regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
								regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY())
						|| regroupement.getListeAccelerateur().get(0).contient(
								regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
								regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY()))) {

				} else if (regroupement.getListeAccelerateur().size() == 3 && (regroupement.getListeAccelerateur()
						.get(2).contient(regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
								regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY())
						|| regroupement.getListeAccelerateur().get(1).contient(
								regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
								regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY())
						|| regroupement.getListeAccelerateur().get(0).contient(
								regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
								regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY()))) {

				}

				else {

					voiture.setAccel(valeurInit);
				}

			}

			if (space == true) {

				if (regroupement.getObjSpecial() != null) {
					regroupement.setBoutonAppuye(true);

					regroupement.getObjSpecial().setVitesse(new Vecteur2D(
							forceDeLancement
									* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()),
							forceDeLancement
									* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle())));

				}

			}
			if (q == true) {

				if (regroupement.getObjSpecial2() != null) {
					regroupement.setBoutonAppuye2(true);
					regroupement.getObjSpecial2().setVitesse(new Vecteur2D(
							forceDeLancement2
									* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()),
							forceDeLancement2
									* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle())));

				}

			}

			if (w == false) {
				if ((regroupement.getObjSpecial() != null
						&& regroupement.getObjSpecial().getType() == TypeObjetSpecial.TROUNOIR
						&& regroupement.getObjSpecial().getTrouNoir().collisionDeLaVoiture(
								regroupement.getListePisteDeDepart().get(0).getVoiture2()) == true)
						|| (regroupement.getObjSpecial2() != null
								&& regroupement.getObjSpecial2().getType() == TypeObjetSpecial.TROUNOIR
								&& regroupement.getObjSpecial2().getTrouNoir().collisionDeLaVoiture(
										regroupement.getListePisteDeDepart().get(0).getVoiture2()) == true)) {

				} else if ((regroupement.getObjSpecial() != null && regroupement.getObjSpecial().getColle()
						.collisionDeLaVoiture(regroupement.getListePisteDeDepart().get(0).getVoiture2()) == true)
						|| (regroupement.getObjSpecial2() != null
								&& regroupement.getObjSpecial2().getColle().collisionDeLaVoiture(
										regroupement.getListePisteDeDepart().get(0).getVoiture2()) == true)) {

				} else if (regroupement.getListeAccelerateur().size() != 0 && regroupement.getListeAccelerateur().get(0)
						.contient(regroupement.getListePisteDeDepart().get(0).getVoiture2().getPosition().getX(),
								regroupement.getListePisteDeDepart().get(0).getVoiture2().getPosition().getY())) {

				}

				else {

					voiture2.setAccel(valeurInit);
				}

			}

			repaint();

			try

			{
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

		if (regroupement.getListePisteDeDepart().get(0).getVoiture2().getVitesse()
				.module(voiture2.getVitesse()) > voiture2.getVitesseMaxSelonNiveau()) {
			voiture2.setVitesse(new Vecteur2D(
					voiture2.getVitesseMaxSelonNiveau()
							* Math.cos(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()),
					voiture2.getVitesseMaxSelonNiveau()
							* Math.sin(regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle())));

		}
		regroupement.enCollisionAvec(voiture);
		regroupement.enCollisionAvec(voiture2);

		try {
			regroupement.getListePisteDeDepart().get(0).getVoiture()
					.collisionEntreVoiture(regroupement.getListePisteDeDepart().get(0).getVoiture2());
		} catch (Exception e) {

			e.printStackTrace();
		}
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

		regroupement.getListePisteDeDepart().get(0).getVoiture2().setPosition(posInit2);
		regroupement.getListePisteDeDepart().get(0).getVoiture2().setVitesse(valeurInit);
		regroupement.getListePisteDeDepart().get(0).getVoiture2().setAccel(valeurInit);
		regroupement.getListePisteDeDepart().get(0).getVoiture2().setAngle(0);
		regroupement.getListePisteDeDepart().get(0).getVoiture().setLongueurTete(2.5);
		regroupement.getListePisteDeDepart().get(0).getVoiture().setStrokeVoulu(0.5);
		regroupement.getListePisteDeDepart().get(0).getVoiture().setDiametreFleche(16);
		regroupement.getListePisteDeDepart().get(0).getVoiture2().setLongueurTete(2.5);
		regroupement.getListePisteDeDepart().get(0).getVoiture2().setStrokeVoulu(0.5);
		regroupement.getListePisteDeDepart().get(0).getVoiture2().setDiametreFleche(16);

		angleVoitureDegre = 0;
		angleVoitureDegre2 = 0;

		regroupement.resetTour();
		pcs.firePropertyChange("tempsEcoule", 0, tempsTotalEcoule);

		repaint();
	}

	/**
	 * Méthode qui permet de réinitialiser la position de la voiture directement
	 * dans la piste de départ
	 */
	// Alexis Pineda-Alvarado
	public void restartPosPisteDepart() {
		arreter();
		tempsTotalEcoule = 0.000;
		if (regroupement.getListePisteDeDepart().size() != 0) {
			regroupement.getListePisteDeDepart().get(0).getVoiture()
					.setPosition(new Vecteur2D(regroupement.getListePisteDeDepart().get(0).getX(),
							regroupement.getListePisteDeDepart().get(0).getY() + 10));
			regroupement.getListePisteDeDepart().get(0).getVoiture().setVitesse(valeurInit);
			regroupement.getListePisteDeDepart().get(0).getVoiture().setAccel(valeurInit);
			regroupement.getListePisteDeDepart().get(0).getVoiture().setAngle(0);
			regroupement.getListePisteDeDepart().get(0).getVoiture().setDiametre(16);
			regroupement.getListePisteDeDepart().get(0).getVoiture().setNombreToursFaits(0);
			regroupement.setObjSpecial(null);

			regroupement.getListePisteDeDepart().get(0).getVoiture2()
					.setPosition(new Vecteur2D(regroupement.getListePisteDeDepart().get(0).getX(),
							regroupement.getListePisteDeDepart().get(0).getY() + 50));
			regroupement.getListePisteDeDepart().get(0).getVoiture2().setVitesse(valeurInit);
			regroupement.getListePisteDeDepart().get(0).getVoiture2().setAccel(valeurInit);
			regroupement.getListePisteDeDepart().get(0).getVoiture2().setAngle(0);
			regroupement.getListePisteDeDepart().get(0).getVoiture2().setDiametre(16);
			regroupement.getListePisteDeDepart().get(0).getVoiture2().setNombreToursFaits(0);
			regroupement.setObjSpecial2(null);

			angleVoitureDegre = 0;
			angleVoitureDegre2 = 0;
			regroupement.resetTour();
			pcs.firePropertyChange("tempsEcoule", 0, tempsTotalEcoule);
			regroupement.creeBoiteDansListe();

			repaint();
		}

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
	 * methode qui set la couleur de la piste dependant le materiel prise
	 * 
	 * @param color parametre qui choisi la couleur
	 */
	// Alexis Pineda-Alvarado
	public void changerTexture(Color color) {
		if (regroupement.getListePisteDeDepart().size() != 0) {
			regroupement.getListePisteDeDepart().get(0).setColor(color);
		}
		if (regroupement.getListePisteHorizontale().size() != 0) {
			for (int a = 0; a < regroupement.getListePisteHorizontale().size(); a++) {
				regroupement.getListePisteHorizontale().get(a).setColor(color);
			}
		}
		if (regroupement.getListePisteVerticale().size() != 0) {
			for (int a = 0; a < regroupement.getListePisteVerticale().size(); a++) {
				regroupement.getListePisteVerticale().get(a).setColor(color);
			}
		}
		if (regroupement.getListePisteVirageBas().size() != 0) {
			for (int a = 0; a < regroupement.getListePisteVirageBas().size(); a++) {
				regroupement.getListePisteVirageBas().get(a).setColor(color);
			}
		}
		if (regroupement.getListePisteVirageDroit().size() != 0) {
			for (int a = 0; a < regroupement.getListePisteVirageBas().size(); a++) {
				regroupement.getListePisteVirageBas().get(a).setColor(color);
			}
		}
		if (regroupement.getListePisteVirageDroit().size() != 0) {
			for (int a = 0; a < regroupement.getListePisteVirageDroit().size(); a++) {
				regroupement.getListePisteVirageDroit().get(a).setColor(color);
			}
		}
		if (regroupement.getListePisteVirageGauche().size() != 0) {
			for (int a = 0; a < regroupement.getListePisteVirageGauche().size(); a++) {
				regroupement.getListePisteVirageGauche().get(a).setColor(color);
			}
		}
		if (regroupement.getListePisteVirageHaut().size() != 0) {
			for (int a = 0; a < regroupement.getListePisteVirageHaut().size(); a++) {
				regroupement.getListePisteVirageHaut().get(a).setColor(color);
			}
		}
	}

	/**
	 * Méthode qui permet de détecter les levés d'évènement et d'envoyer la nouvelle
	 * donnée
	 */

	// Tan Tommy Rin
	public void changementTexteParIteration() {
		pcs.firePropertyChange("tempsEcoule", 0, tempsTotalEcoule);
		if (regroupement.getObjSpecial() != null) {
			if (regroupement.getObjSpecial().getType() == TypeObjetSpecial.CHAMPIGNON) {
				pcs.firePropertyChange("champignon1", 0, -1);
			} else if (regroupement.getObjSpecial().getType() == TypeObjetSpecial.BOULEDENEIGE) {
				pcs.firePropertyChange("bouleNeige1", 0, -1);
			} else if (regroupement.getObjSpecial().getType() == TypeObjetSpecial.TROUNOIR) {
				pcs.firePropertyChange("trouNoir1", 0, -1);
			} else {
				pcs.firePropertyChange("colle1", 0, -1);
			}
		}
		if (regroupement.getObjSpecial2() != null) {
			if (regroupement.getObjSpecial2().getType() == TypeObjetSpecial.CHAMPIGNON) {
				pcs.firePropertyChange("champignon2", 0, -1);
			} else if (regroupement.getObjSpecial2().getType() == TypeObjetSpecial.BOULEDENEIGE) {
				pcs.firePropertyChange("bouleNeige2", 0, -1);
			} else if (regroupement.getObjSpecial2().getType() == TypeObjetSpecial.TROUNOIR) {
				pcs.firePropertyChange("trouNoir2", 0, -1);
			} else {
				pcs.firePropertyChange("colle2", 0, -1);
			}
		}

		pcs.firePropertyChange("accEnXV1", 0, voiture.getAccel().getX());
		pcs.firePropertyChange("accEnYV1", 0, voiture.getAccel().getY());
		pcs.firePropertyChange("vitEnXV1", 0, voiture.getVitesse().getX());
		pcs.firePropertyChange("vitEnYV1", 0, voiture.getVitesse().getY());
		pcs.firePropertyChange("posEnXV1", 0, voiture.getPosition().getX());
		pcs.firePropertyChange("posEnYV1", 0, voiture.getPosition().getY());
		pcs.firePropertyChange("angleV1", 0, voiture.getAngle());
		pcs.firePropertyChange("nombreToursV1", 0,
				regroupement.getListePisteDeDepart().get(0).getVoiture().getNombreToursFaits());
		pcs.firePropertyChange("nombreToursV2", 0,
				regroupement.getListePisteDeDepart().get(0).getVoiture2().getNombreToursFaits());
		pcs.firePropertyChange("accEnXV2", 0, voiture2.getAccel().getX());
		pcs.firePropertyChange("accEnYV2", 0, voiture2.getAccel().getY());
		pcs.firePropertyChange("vitEnXV2", 0, voiture2.getVitesse().getX());
		pcs.firePropertyChange("vitEnYV2", 0, voiture2.getVitesse().getY());
		pcs.firePropertyChange("posEnXV2", 0, voiture2.getPosition().getX());
		pcs.firePropertyChange("posEnYV2", 0, voiture2.getPosition().getY());
		pcs.firePropertyChange("angleV2", 0, voiture2.getAngle());
		pcs.firePropertyChange("masse1", 0, voiture.getMasseEnKg());
		pcs.firePropertyChange("masse2", 0, voiture2.getMasseEnKg());
		pcs.firePropertyChange("diametre1", 0, voiture.getDiametre());
		pcs.firePropertyChange("diametre2", 0, voiture2.getDiametre());

		if (regroupement.getObjSpecial() != null
				&& regroupement.getObjSpecial().getType() == TypeObjetSpecial.BOULEDENEIGE) {
			if (forceDeLancement > 150) {
				forceDeLancement = 50;
				regroupement.getListePisteDeDepart().get(0).getVoiture().setLongueurTete(2.5);
				regroupement.getListePisteDeDepart().get(0).getVoiture().setStrokeVoulu(0.5);
				regroupement.getListePisteDeDepart().get(0).getVoiture().setDiametreFleche(16);
			} else if (forceDeLancement > 49 && forceDeLancement < 150) {
				forceDeLancement = forceDeLancement + 0.3;
				regroupement.getListePisteDeDepart().get(0).getVoiture().setLongueurTete(
						regroupement.getListePisteDeDepart().get(0).getVoiture().getLongueurTete() + 0.03);
				regroupement.getListePisteDeDepart().get(0).getVoiture().setStrokeVoulu(
						regroupement.getListePisteDeDepart().get(0).getVoiture().getStrokeVoulu() + 0.01);
				regroupement.getListePisteDeDepart().get(0).getVoiture().setDiametreFleche(
						regroupement.getListePisteDeDepart().get(0).getVoiture().getDiametreFleche() + 0.03);

			}

			pcs.firePropertyChange("ForceLance", 0, forceDeLancement);
		} else {
			forceDeLancement = 50;
			regroupement.getListePisteDeDepart().get(0).getVoiture().setLongueurTete(2.5);
			regroupement.getListePisteDeDepart().get(0).getVoiture().setStrokeVoulu(0.5);
			regroupement.getListePisteDeDepart().get(0).getVoiture().setDiametreFleche(16);
		}
		if (regroupement.getObjSpecial2() != null
				&& regroupement.getObjSpecial2().getType() == TypeObjetSpecial.BOULEDENEIGE) {
			if (forceDeLancement2 > 150) {
				forceDeLancement2 = 50;
				regroupement.getListePisteDeDepart().get(0).getVoiture2().setLongueurTete(2.5);
				regroupement.getListePisteDeDepart().get(0).getVoiture2().setStrokeVoulu(0.5);
				regroupement.getListePisteDeDepart().get(0).getVoiture2().setDiametreFleche(16);
			} else if (forceDeLancement2 > 49 && forceDeLancement2 < 150) {
				forceDeLancement2 = forceDeLancement2 + 0.3;
				regroupement.getListePisteDeDepart().get(0).getVoiture2().setLongueurTete(
						regroupement.getListePisteDeDepart().get(0).getVoiture2().getLongueurTete() + 0.03);
				regroupement.getListePisteDeDepart().get(0).getVoiture2().setStrokeVoulu(
						regroupement.getListePisteDeDepart().get(0).getVoiture2().getStrokeVoulu() + 0.01);
				regroupement.getListePisteDeDepart().get(0).getVoiture2().setDiametreFleche(
						regroupement.getListePisteDeDepart().get(0).getVoiture2().getDiametreFleche() + 0.03);
			}

			pcs.firePropertyChange("ForceLance2", 0, forceDeLancement2);
		} else {
			forceDeLancement2 = 50;
			regroupement.getListePisteDeDepart().get(0).getVoiture2().setLongueurTete(2.5);
			regroupement.getListePisteDeDepart().get(0).getVoiture2().setStrokeVoulu(0.5);
			regroupement.getListePisteDeDepart().get(0).getVoiture2().setDiametreFleche(16);
		}

	}

	/**
	 * Calcul d'une interation physique
	 */
	// Kevin Nguyen
	private void calculerUneIterationPhysique() {

		tempsTotalEcoule += deltaT;
		changementTexteParIteration();

		Vecteur2D forceTotal = new Vecteur2D(MoteurPhysique.calculerForceFrottement(testFrottement,
				regroupement.getListePisteDeDepart().get(0).getVoiture().getMasseEnKg(),
				regroupement.getListePisteDeDepart().get(0).getVoiture().getAngle()));
		pcs.firePropertyChange("frottementEnXV1", 0, forceTotal.getX());
		pcs.firePropertyChange("frottementEnYV1", 0, forceTotal.getY());
		if (bas == true) {

			forceFreinage = new Vecteur2D(MoteurPhysique
					.calculerForceFrottement(testFrottement,
							regroupement.getListePisteDeDepart().get(0).getVoiture().getMasseEnKg(), angleVoitureRad)

					.multiplie(2));

			forceTotal = forceTotal.additionne(forceFreinage);

			pcs.firePropertyChange("freinageEnXV1", 0, forceFreinage.getX());
			pcs.firePropertyChange("freinageEnYV1", 0, forceFreinage.getY());
		}

		if (haut == false && regroupement.getListePisteDeDepart().get(0).getVoiture().getVitesse().module() != 0) {

			if ((regroupement.getObjSpecial2() != null
					&& regroupement.getObjSpecial2().getType() == TypeObjetSpecial.TROUNOIR
					&& regroupement.getObjSpecial2().getTrouNoir().collisionDeLaVoiture(voiture) == true)
					|| (regroupement.getObjSpecial() != null
							&& regroupement.getObjSpecial().getType() == TypeObjetSpecial.TROUNOIR
							&& regroupement.getObjSpecial().getTrouNoir().collisionDeLaVoiture(voiture) == true)) {

			} else if (regroupement.getListeAccelerateur().size() == 1 && regroupement.getListeAccelerateur().get(0)
					.contient(regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
							regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY())) {

			} else if (regroupement.getListeAccelerateur().size() == 2 && (regroupement.getListeAccelerateur().get(1)
					.contient(regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
							regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY())
					|| regroupement.getListeAccelerateur().get(0).contient(
							regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
							regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY()))) {

			} else if (regroupement.getListeAccelerateur().size() == 3 && (regroupement.getListeAccelerateur().get(2)
					.contient(regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
							regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY())
					|| regroupement.getListeAccelerateur().get(1).contient(
							regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
							regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY())
					|| regroupement.getListeAccelerateur().get(0).contient(
							regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getX(),
							regroupement.getListePisteDeDepart().get(0).getVoiture().getPosition().getY()))) {

			} else if ((regroupement.getObjSpecial() != null
					&& regroupement.getObjSpecial().getColle()
							.collisionDeLaVoiture(regroupement.getListePisteDeDepart().get(0).getVoiture()) == true
					&& regroupement.getListePisteDeDepart().get(0).getVoiture().getVitesse().module() > 8)
					|| (regroupement.getObjSpecial2() != null
							&& regroupement.getObjSpecial2().getColle().collisionDeLaVoiture(
									regroupement.getListePisteDeDepart().get(0).getVoiture()) == true
							&& regroupement.getListePisteDeDepart().get(0).getVoiture().getVitesse().module() > 8)) {

			}

			else {
				regroupement.getListePisteDeDepart().get(0).getVoiture().setSommeDesForces(forceTotal);
				if (regroupement.getListePisteDeDepart().get(0).getVoiture().getVitesse().module() < 0.5) {

					regroupement.getListePisteDeDepart().get(0).getVoiture().setVitesse(new Vecteur2D(0, 0));

				}
			}

		}

		Vecteur2D forceTotal2 = new Vecteur2D(MoteurPhysique.calculerForceFrottement(testFrottement,
				regroupement.getListePisteDeDepart().get(0).getVoiture2().getMasseEnKg(),
				regroupement.getListePisteDeDepart().get(0).getVoiture2().getAngle()));
		pcs.firePropertyChange("frottementEnXV2", 0, forceTotal2.getX());
		pcs.firePropertyChange("frottementEnYV2", 0, forceTotal2.getY());
		if (s == true) {

			forceFreinage2 = new Vecteur2D(MoteurPhysique
					.calculerForceFrottement(testFrottement,
							regroupement.getListePisteDeDepart().get(0).getVoiture2().getMasseEnKg(), angleVoitureRad2)

					.multiplie(2));

			forceTotal2 = forceTotal2.additionne(forceFreinage2);
			pcs.firePropertyChange("freinageEnXV2", 0, forceFreinage2.getX());
			pcs.firePropertyChange("freinageEnYV2", 0, forceFreinage2.getY());

		}
		if (w == false && regroupement.getListePisteDeDepart().get(0).getVoiture2().getVitesse().module() != 0) {

			if ((regroupement.getObjSpecial2() != null
					&& regroupement.getObjSpecial2().getType() == TypeObjetSpecial.TROUNOIR
					&& regroupement.getObjSpecial2().getTrouNoir().collisionDeLaVoiture(voiture2) == true)
					|| (regroupement.getObjSpecial() != null
							&& regroupement.getObjSpecial().getType() == TypeObjetSpecial.TROUNOIR
							&& regroupement.getObjSpecial().getTrouNoir().collisionDeLaVoiture(voiture2) == true)) {

			} else if (regroupement.getListeAccelerateur().size() != 0 && regroupement.getListeAccelerateur().get(0)
					.contient(regroupement.getListePisteDeDepart().get(0).getVoiture2().getPosition().getX(),
							regroupement.getListePisteDeDepart().get(0).getVoiture2().getPosition().getY())) {

			} else if ((regroupement.getObjSpecial() != null
					&& regroupement.getObjSpecial().getColle()
							.collisionDeLaVoiture(regroupement.getListePisteDeDepart().get(0).getVoiture2()) == true
					&& regroupement.getListePisteDeDepart().get(0).getVoiture2().getVitesse().module() > 8)
					|| (regroupement.getObjSpecial2() != null
							&& regroupement.getObjSpecial2().getColle().collisionDeLaVoiture(
									regroupement.getListePisteDeDepart().get(0).getVoiture2()) == true
							&& regroupement.getListePisteDeDepart().get(0).getVoiture2().getVitesse().module() > 8)) {

			}

			else {
				regroupement.getListePisteDeDepart().get(0).getVoiture2().setSommeDesForces(forceTotal2);
				if (regroupement.getListePisteDeDepart().get(0).getVoiture2().getVitesse().module() < 0.5) {
					regroupement.getListePisteDeDepart().get(0).getVoiture2().setVitesse(new Vecteur2D(0, 0));
				}
			}

		}
		pcs.firePropertyChange("frottementEnXV2", 0, forceTotal2.getX());
		pcs.firePropertyChange("frottementEnYV2", 0, forceTotal2.getY());
		regroupement.avancerGroupe(deltaT, tempsTotalEcoule);
		arretQuandFini();
		arretMusic();
		arretGraphique();
		gagnantCourse();
	}

	/**
	 * Méthode qui permet d'arreter l'animation losrque le nombre de tours a faire
	 * est accomplie par une voiture
	 */
	// Tan Tommy Rin
	public void arretQuandFini() {
		if (regroupement.getNombreToursAFaire() == regroupement.getListePisteDeDepart().get(0).getVoiture2()
				.getNombreToursFaits()) {
			System.out.println("LA VOITURE 1 A GAGNÉE!!!");

			arreter();

		} else if (regroupement.getNombreToursAFaire() == regroupement.getListePisteDeDepart().get(0).getVoiture()
				.getNombreToursFaits()) {
			System.out.println("LA VOITURE 2 A GAGNÉE!!!");
			arreter();

		}

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
	 * Attribuer une nouvelle masse a la voiture
	 * 
	 * @param masseVoulu Nouvelle masse
	 */
	// Kevin Nguyen
	public void setVoitureMasse2(double masseVoulu) {
		this.voiture2.setMasseEnKg(masseVoulu);
		this.voiture2.setMasseEnKgInitial(masseVoulu);

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
	 * Attribuer un nouvel angle en rad
	 * 
	 * @param nouvAngle Angle en degre
	 */
	// Kevin Nguyen
	public void setAngle2(int nouvAngle) {
		angleVoitureRad2 = Math.toRadians(nouvAngle);
		voiture2.setAngle(angleVoitureRad2);

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
	// Tan Tommy Rin
	public void setVoitureVitesseMax(double nouvelleVitesseMax) {
		voiture.setVitesseMaxSelonNiveau(nouvelleVitesseMax);
		voiture2.setVitesseMaxSelonNiveau(nouvelleVitesseMax);
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
	// Tan Tommy Rin
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
	// Tan Tommy Rin
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
			regroupement.getListePisteDeDepart().get(0).setVoiture2(voiture2);
			regroupement.creeBoiteDansListe();
			regroupement.getListeAccelerateur().clear();
			regroupement.getListeFumee().clear();
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
			regroupement.getListePisteDeDepart().get(0).setVoiture2(voiture2);
			regroupement.creeBoiteDansListe();
			regroupement.getListeAccelerateur().clear();
			regroupement.getListeFumee().clear();
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
			regroupement.getListePisteDeDepart().get(0).setVoiture2(voiture2);
			regroupement.creeBoiteDansListe();
			regroupement.getListeAccelerateur().clear();
			regroupement.getListeFumee().clear();
		}
		if (typePiste == TypePiste.AUTRE) {

			Regroupement regroupementTempo = (gestionFich.lireFichierBinBureauRegroupement(nomFichierRegroupement));
			regroupement.setListePisteDeDepart(regroupementTempo.getListePisteDeDepart());
			regroupement.setListePisteHorizontale(regroupementTempo.getListePisteHorizontale());
			regroupement.setListePisteVerticale(regroupementTempo.getListePisteVerticale());
			regroupement.setListePisteVirageBas(regroupementTempo.getListePisteVirageBas());
			regroupement.setListePisteVirageDroit(regroupementTempo.getListePisteVirageDroit());
			regroupement.setListePisteVirageGauche(regroupementTempo.getListePisteVirageGauche());
			regroupement.setListePisteVirageHaut(regroupementTempo.getListePisteVirageHaut());
			regroupement.setListeAccelerateur(regroupementTempo.getListeAccelerateur());
			regroupement.setListeFumee(regroupementTempo.getListeFumee());
			voiture.setPosition(new Vecteur2D(regroupement.getListePisteDeDepart().get(0).getX(),
					regroupement.getListePisteDeDepart().get(0).getY()
							+ regroupement.getListePisteDeDepart().get(0).getTaillePiste() / 4));
			voiture2.setPosition(new Vecteur2D(regroupement.getListePisteDeDepart().get(0).getX(),
					regroupement.getListePisteDeDepart().get(0).getY()
							+ regroupement.getListePisteDeDepart().get(0).getTaillePiste() / 2));
			regroupement.getListePisteDeDepart().get(0).setVoiture(voiture);
			regroupement.getListePisteDeDepart().get(0).setVoiture2(voiture2);
			regroupement.setNombreBoiteMystere(regroupementTempo.getRegroupementBoiteMystere().size());
			regroupement.setRegroupementObjet(regroupementTempo.getRegroupementBoiteMystere());

		}
	}

	public String getNomFichierRegroupement() {
		return nomFichierRegroupement;
	}

	public void setNomFichierRegroupement(String nomFichierRegroupement) {
		this.nomFichierRegroupement = nomFichierRegroupement;
	}

	public int getNombreBlocMystere() {
		return nombreBlocMystere;
	}

	/**
	 * Méthode qui permet de set le nombre de bloc mystere
	 * 
	 * @param nombreBlocMystere le nombre voulu
	 */
	// Tan Tommy Rin
	public void setNombreBlocMystere(int nombreBlocMystere) {
		this.nombreBlocMystere = nombreBlocMystere;
		regroupement.setNombreBoiteMystere(nombreBlocMystere);
		regroupement.creeBoiteDansListe();
	}

	/**
	 * méthode qui permet d'arreter la musique quand la partie est terminé
	 * 
	 */
	// Ludovic Julien
	public void arretMusic() {
		if (regroupement.getNombreToursAFaire() == regroupement.getListePisteDeDepart().get(0).getVoiture()
				.getNombreToursFaits()
				|| regroupement.getNombreToursAFaire() == regroupement.getListePisteDeDepart().get(0).getVoiture2()
						.getNombreToursFaits()) {
			try {
				newClip = FenetreJeuScientifique.getClip();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (newClip != null) {
				newClip.stop();
			}
		}
	}

	/**
	 * méthode qui permet d'arreter le timer des grahique losrque la partie est
	 * terminé
	 */
	// Ludovic Julien
	public void arretGraphique() {
		if (regroupement.getNombreToursAFaire() == regroupement.getListePisteDeDepart().get(0).getVoiture()
				.getNombreToursFaits()
				|| regroupement.getNombreToursAFaire() == regroupement.getListePisteDeDepart().get(0).getVoiture2()
						.getNombreToursFaits()) {

			FenetreJeuScientifique.getTimer().stop();

		}
	}

	/**
	 * méthode qui permet de récolter les information du gagnant de la course
	 * (nom,temps,piste jouer)
	 */
	// Ludovic Julien
	public void gagnantCourse() {
		if (regroupement.getNombreToursAFaire() == regroupement.getListePisteDeDepart().get(0).getVoiture()
				.getNombreToursFaits()
				|| regroupement.getNombreToursAFaire() == regroupement.getListePisteDeDepart().get(0).getVoiture2()
						.getNombreToursFaits()) {

			String nomUtilisateur = "Inconnue";
			nomUtilisateur = JOptionPane
					.showInputDialog("félicitation pour cette belle victoire, entrez votre nom pour le classement !");

			if (typePiste == TypePiste.CANADA) {
				piste = "Canada";
			} else {
				if (typePiste == TypePiste.MEXIQUE) {
					piste = "Mexique";
				} else {
					piste = "Italie";
				}
			}

			GestionnaireDeFichiersSurLeBureau.ecrireFichier(nomUtilisateur,
					Math.round(tempsTotalEcoule * 100.0) / 100.0, piste);
		}
	}

}
