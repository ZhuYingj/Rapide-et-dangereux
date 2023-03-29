package fenetre;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import java.util.ArrayList;

import javax.swing.JPanel;

import geometrie.Vecteur2D;
import interfaces.TypeObjetDeplacable;
import utilitaireObjets.Accelerateur;
import utilitaireObjets.BlocMystere;
import utilitaireObjets.PisteDeDepart;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.PisteVirageDroit;
import utilitaireObjets.PisteVirageGauche;
import utilitaireObjets.PisteVirageHaut;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Classe permettant de créer et gérer une fenetre de regroupement. Le
 * regroupement contient des morceaux de pistes, des blocs mysteres et une
 * voiture.
 * 
 * @author Tan Tommy Rin
 * @author Alexis Pineda-Alvarado
 *
 */

public class PanelRegroupement extends JPanel {

	private static final long serialVersionUID = -3942442779188948737L;
	private BlocMystere blocMystere;
	private Accelerateur acc;
	private PisteDeDepart pisteDeDepart;
	private PisteHorizontale pisteHorizontale;
	private PisteVerticale pisteVerticale;
	private PisteVirageBas pisteVirageBas;
	private PisteVirageDroit pisteVirageDroit;
	private PisteVirageGauche pisteVirageGauche;
	private PisteVirageHaut pisteVirageHaut;

	private int xPrecedent, yPrecedent;
	private double xAccelerateur;
	private double yAccelerateur;
	private double xBlocMystere;
	private double yBlocMystere;
	private double xPisteHorizontale;
	private double yPisteHorizontale;
	private double xPisteVirageBas;
	private double yPisteVirageBas;
	private double xPisteVirageGauche;
	private double yPisteVirageGauche;
	private double xPisteVerticale;
	private double yPisteVerticale;
	private double xPisteDeDepart;
	private double yPisteDeDepart;
	private double xPisteVirageDroit;
	private double yPisteVirageDroit;
	private double xPisteVirageHaut;
	private double yPisteVirageHaut;

	private ArrayList<Accelerateur> listeAccelerateur = new ArrayList<Accelerateur>();

	private ArrayList<BlocMystere> listeBlocMystere = new ArrayList<BlocMystere>();
	private ArrayList<PisteHorizontale> listePisteHorizontale = new ArrayList<PisteHorizontale>();
	private ArrayList<PisteVirageBas> listePisteVirageBas = new ArrayList<PisteVirageBas>();
	private ArrayList<PisteVirageGauche> listePisteVirageGauche = new ArrayList<PisteVirageGauche>();
	private ArrayList<PisteVerticale> listePisteVerticale = new ArrayList<PisteVerticale>();
	private ArrayList<PisteDeDepart> listePisteDeDepart = new ArrayList<PisteDeDepart>();
	private ArrayList<PisteVirageDroit> listePisteVirageDroit = new ArrayList<PisteVirageDroit>();
	private ArrayList<PisteVirageHaut> listePisteVirageHaut = new ArrayList<PisteVirageHaut>();

	private boolean objetSelectionne = false;

	private TypeObjetDeplacable type;

	/**
	 * Constructeur du panel de regroupement
	 */
	// Par Tan Tommy Rin
	public PanelRegroupement() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// Pour les accélérateurs
				accelerateurDrag(e);

				// Pour les blocs mysteres
				blocMystereDrag(e);

				// Pour les pistes horizontales
				pisteHorizontaleDrag(e);

				// Pour les pistes de virage bas
				pisteVirageBasDrag(e);

				// Pour les pistes de virage gauche
				pisteVirageGaucheDrag(e);

				// Pour les pistes verticales
				pisteVerticaleDrag(e);

				// Pour la piste de depart
				pisteDeDepartDrag(e);

				// Pour la piste de virage droite
				pisteVirageDroitDrag(e);

				// Pour la piste de virage haut
				pisteVirageHautDrag(e);

				repaint();
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Condition pour les accélérateurs
				accelerateurPressed(e);
				// Condition pour les blocs mysteres
				blocMysterePressed(e);
				// Condition pour pisteHorizontale
				pisteHorizontalePressed(e);
				// Condition pour piste virage bas
				pisteVirageBasPressed(e);
				// Condition pour piste virage gauche
				pisteVirageGauchePressed(e);
				// Condition pour piste verticale
				pisteVerticalePressed(e);
				// Condition pour de depart
				pisteDeDepartPressed(e);
				// Condition pour piste virage droite
				pisteVirageDroitPressed(e);
				// Condition pour piste virage haut
				pisteVirageHautPressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				relachementSouris(e);
			}
		});

	}

	/**
	 * On redefinit la methode de dessin
	 * 
	 * @param g Le contexte graphique de la zone de dessin
	 */
	// Par Tan Tommy Rin
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Graphics2D g2dCopie = (Graphics2D) g2d.create();
		creationQuadrillage(g2dCopie);

		for (int a = 0; a < listePisteVirageDroit.size(); a++) {

			listePisteVirageDroit.get(a).dessiner(g2d);

		}

		for (int a = 0; a < listePisteDeDepart.size(); a++) {

			listePisteDeDepart.get(a).dessiner(g2d);

		}

		for (int a = 0; a < listePisteHorizontale.size(); a++) {

			listePisteHorizontale.get(a).dessiner(g2d);

		}

		for (int a = 0; a < listePisteVirageBas.size(); a++) {

			listePisteVirageBas.get(a).dessiner(g2d);

		}

		for (int a = 0; a < listePisteVirageGauche.size(); a++) {

			listePisteVirageGauche.get(a).dessiner(g2d);

		}

		for (int a = 0; a < listePisteVerticale.size(); a++) {

			listePisteVerticale.get(a).dessiner(g2d);

		}

		for (int a = 0; a < listePisteVirageHaut.size(); a++) {

			listePisteVirageHaut.get(a).dessiner(g2d);

		}

		for (int a = 0; a < listeAccelerateur.size(); a++) {

			listeAccelerateur.get(a).dessiner(g2d);

		}

		for (int a = 0; a < listeBlocMystere.size(); a++) {

			listeBlocMystere.get(a).dessiner(g2d);

		}

	}

	/**
	 * Méthode qui permet de détecter lorsque la souris n'est plus enfoncé et
	 * redessine
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void relachementSouris(MouseEvent e) {
		objetSelectionne = false;
		repaint();
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage droite en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVirageDroitDrag(MouseEvent e) {
		if (listePisteVirageDroit.size() != 0 && objetSelectionne == true
				&& type == TypeObjetDeplacable.PISTEVIRAGEDROIT) {
			xPisteVirageDroit += e.getX() - xPrecedent;
			yPisteVirageDroit += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVirageDroit.setX((int) xPisteVirageDroit);
			pisteVirageDroit.setY((int) yPisteVirageDroit);
			pisteVirageDroit.getFormeAire().setRect(xPisteVirageDroit, yPisteVirageDroit,
					pisteVirageDroit.getTaillePiste(), pisteVirageDroit.getTaillePiste());
			pisteVirageDroit.setMurHaut((int) yPisteVirageDroit);
			pisteVirageDroit.setMurBas((int) yPisteVirageDroit + pisteDeDepart.getTaillePiste());
			pisteVirageDroit.setMurDroite((int) xPisteVirageDroit + pisteDeDepart.getTaillePiste());
			pisteVirageDroit.setMurGauche((int) xPisteVirageDroit);
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de depart en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteDeDepartDrag(MouseEvent e) {
		if (listePisteDeDepart.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.PISTEDEDEPART) {
			xPisteDeDepart += e.getX() - xPrecedent;
			yPisteDeDepart += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteDeDepart.setX((int) xPisteDeDepart);
			pisteDeDepart.setY((int) yPisteDeDepart);
			pisteDeDepart.getVoiture().setPosition(new Vecteur2D(xPisteDeDepart + pisteDeDepart.getTaillePiste() / 4,
					yPisteDeDepart + pisteDeDepart.getTaillePiste() / 4));
			pisteDeDepart.getFormeAire().setRect(xPisteDeDepart, yPisteDeDepart, pisteDeDepart.getTaillePiste(),
					pisteDeDepart.getTaillePiste());
			pisteDeDepart.setMurHaut((int) yPisteDeDepart);
			pisteDeDepart.setMurBas((int) yPisteDeDepart + pisteDeDepart.getTaillePiste());
			pisteDeDepart.setMurDroite((int) xPisteDeDepart + pisteDeDepart.getTaillePiste());
			pisteDeDepart.setMurGauche((int) xPisteDeDepart);

		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste verticale en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVerticaleDrag(MouseEvent e) {
		if (listePisteVerticale.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.PISTEVERTICALE) {
			xPisteVerticale += e.getX() - xPrecedent;
			yPisteVerticale += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVerticale.setX((int) xPisteVerticale);
			pisteVerticale.setY((int) yPisteVerticale);
			pisteVerticale.getFormeAire().setRect(xPisteVerticale, yPisteVerticale, pisteVerticale.getTaillePiste(),
					pisteVerticale.getTaillePiste());
			pisteVerticale.setMurHaut((int) yPisteVerticale);
			pisteVerticale.setMurBas((int) yPisteVerticale + pisteDeDepart.getTaillePiste());
			pisteVerticale.setMurDroite((int) xPisteVerticale + pisteDeDepart.getTaillePiste());
			pisteVerticale.setMurGauche((int) xPisteVerticale);
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage haut en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVirageHautDrag(MouseEvent e) {
		if (listePisteVirageHaut.size() != 0 && objetSelectionne == true
				&& type == TypeObjetDeplacable.PISTEVIRAGEHAUT) {
			xPisteVirageHaut += e.getX() - xPrecedent;
			yPisteVirageHaut += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVirageHaut.setX((int) xPisteVirageHaut);
			pisteVirageHaut.setY((int) yPisteVirageHaut);
			pisteVirageHaut.getFormeAire().setRect(xPisteVirageHaut, yPisteVirageHaut, pisteVirageHaut.getTaillePiste(),
					pisteVirageHaut.getTaillePiste());
			pisteVirageHaut.setMurBas((int) yPisteVirageHaut + pisteVirageHaut.getTaillePiste());
			pisteVirageHaut.setMurHaut((int) yPisteVirageHaut);
			pisteVirageHaut.setMurDroite((int) xPisteVirageHaut + pisteVirageHaut.getTaillePiste() + 1);
			pisteVirageHaut.setMurGauche((int) xPisteVirageHaut);
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage gauche en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVirageGaucheDrag(MouseEvent e) {
		if (listePisteVirageGauche.size() != 0 && objetSelectionne == true
				&& type == TypeObjetDeplacable.PISTEVIRAGEGAUCHE) {
			xPisteVirageGauche += e.getX() - xPrecedent;
			yPisteVirageGauche += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVirageGauche.setX((int) xPisteVirageGauche);
			pisteVirageGauche.setY((int) yPisteVirageGauche);
			pisteVirageGauche.getFormeAire().setRect(xPisteVirageGauche, yPisteVirageGauche,
					pisteVirageGauche.getTaillePiste(), pisteVirageGauche.getTaillePiste());
			pisteVirageGauche.setMurBas((int) yPisteVirageGauche + pisteVirageGauche.getTaillePiste() + 1);
			pisteVirageGauche.setMurGauche((int) xPisteVirageGauche + 1);
			pisteVirageGauche.setMurHaut((int) yPisteVirageGauche + 1);
			pisteVirageGauche.setMurDroite((int) xPisteVirageGauche + pisteVirageGauche.getTaillePiste() + 1);
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage bas en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVirageBasDrag(MouseEvent e) {
		if (listePisteVirageBas.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.PISTEVIRAGEBAS) {
			xPisteVirageBas += e.getX() - xPrecedent;
			yPisteVirageBas += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVirageBas.setX((int) xPisteVirageBas);
			pisteVirageBas.setY((int) yPisteVirageBas);
			pisteVirageBas.getFormeAire().setRect(xPisteVirageBas, yPisteVirageBas, pisteVirageBas.getTaillePiste(),
					pisteVirageBas.getTaillePiste());
			pisteVirageBas.setMurBas((int) yPisteVirageBas + pisteVirageBas.getTaillePiste() + 1);
			pisteVirageBas.setMurGauche((int) xPisteVirageBas + 1);
			pisteVirageBas.setMurHaut((int) yPisteVirageBas + 1);
			pisteVirageBas.setMurDroite((int) xPisteVirageBas + pisteVirageBas.getTaillePiste() + 1);
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste horizontale en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteHorizontaleDrag(MouseEvent e) {
		if (listePisteHorizontale.size() != 0 && objetSelectionne == true
				&& type == TypeObjetDeplacable.PISTEHORIZONTALE) {
			xPisteHorizontale += e.getX() - xPrecedent;
			yPisteHorizontale += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteHorizontale.setX((int) xPisteHorizontale);
			pisteHorizontale.setY((int) yPisteHorizontale);
			pisteHorizontale.getFormeAire().setRect(xPisteHorizontale, yPisteHorizontale,
					pisteHorizontale.getTaillePiste(), pisteHorizontale.getTaillePiste());
			pisteHorizontale.setMurBas((int) yPisteHorizontale + pisteHorizontale.getTaillePiste());
			pisteHorizontale.setMurHaut((int) yPisteHorizontale);
			pisteHorizontale.setMurDroite((int) xPisteHorizontale + pisteHorizontale.getTaillePiste() + 1);
			pisteHorizontale.setMurGauche((int) xPisteHorizontale);
		}
	}

	/**
	 * Méthode qui permet de determiner si une piste de virage haut est contenue
	 * dans le clic de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVirageHautPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVirageHaut.size(); a++) {
				if (listePisteVirageHaut.get(a).contient(e.getX(), e.getY())) {

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					xPisteVirageHaut = listePisteVirageHaut.get(a).getFormeAire().getX();
					yPisteVirageHaut = listePisteVirageHaut.get(a).getFormeAire().getY();
					pisteVirageHaut = listePisteVirageHaut.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.PISTEVIRAGEHAUT;

					break;

				}

			} // Fin loop

		}
	}

	/**
	 * Méthode qui permet de determiner si une piste de virage droite est contenue
	 * dans le clic de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVirageDroitPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVirageDroit.size(); a++) {
				if (listePisteVirageDroit.get(a).contient(e.getX(), e.getY())) {

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					xPisteVirageDroit = listePisteVirageDroit.get(a).getFormeAire().getX();
					yPisteVirageDroit = listePisteVirageDroit.get(a).getFormeAire().getY();
					pisteVirageDroit = listePisteVirageDroit.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.PISTEVIRAGEDROIT;

					break;

				}

			} // Fin loop

		}
	}

	/**
	 * Méthode qui permet de determiner si une piste de depart est contenue dans le
	 * clic de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteDeDepartPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteDeDepart.size(); a++) {
				if (listePisteDeDepart.get(a).contient(e.getX(), e.getY())) {

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					xPisteDeDepart = listePisteDeDepart.get(a).getFormeAire().getX();
					yPisteDeDepart = listePisteDeDepart.get(a).getFormeAire().getY();
					pisteDeDepart = listePisteDeDepart.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.PISTEDEDEPART;

					break;

				}

			} // Fin loop

		}
	}

	/**
	 * Méthode qui permet de determiner si une piste verticale est contenue dans le
	 * clic de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVerticalePressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVerticale.size(); a++) {
				if (listePisteVerticale.get(a).contient(e.getX(), e.getY())) {

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					xPisteVerticale = listePisteVerticale.get(a).getFormeAire().getX();
					yPisteVerticale = listePisteVerticale.get(a).getFormeAire().getY();
					pisteVerticale = listePisteVerticale.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.PISTEVERTICALE;

					break;

				}

			} // Fin loop

		}
	}

	/**
	 * Méthode qui permet de determiner si une piste de virage gauche est contenue
	 * dans le clic de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVirageGauchePressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVirageGauche.size(); a++) {
				if (listePisteVirageGauche.get(a).contient(e.getX(), e.getY())) {

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					xPisteVirageGauche = listePisteVirageGauche.get(a).getFormeAire().getX();
					yPisteVirageGauche = listePisteVirageGauche.get(a).getFormeAire().getY();
					pisteVirageGauche = listePisteVirageGauche.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.PISTEVIRAGEGAUCHE;

					break;

				}

			} // Fin loop

		}
	}

	/**
	 * Méthode qui permet de determiner si une piste de virage bas est contenue dans
	 * le clic de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteVirageBasPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVirageBas.size(); a++) {
				if (listePisteVirageBas.get(a).contient(e.getX(), e.getY())) {

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					xPisteVirageBas = listePisteVirageBas.get(a).getFormeAire().getX();
					yPisteVirageBas = listePisteVirageBas.get(a).getFormeAire().getY();
					pisteVirageBas = listePisteVirageBas.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.PISTEVIRAGEBAS;

					break;

				}

			} // Fin loop

		}
	}

	/**
	 * Méthode qui permet de determiner si une piste horizontale est contenue dans
	 * le clic de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void pisteHorizontalePressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteHorizontale.size(); a++) {
				if (listePisteHorizontale.get(a).contient(e.getX(), e.getY())) {

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					xPisteHorizontale = listePisteHorizontale.get(a).getFormeAire().getX();
					yPisteHorizontale = listePisteHorizontale.get(a).getFormeAire().getY();
					pisteHorizontale = listePisteHorizontale.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.PISTEHORIZONTALE;

					break;

				}

			} // Fin loop

		}
	}

	/**
	 * Méthode qui permet de determiner si un accelerateur est contenue dans le clic
	 * de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void accelerateurPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listeAccelerateur.size(); a++) {
				if (listeAccelerateur.get(a).contient(e.getX(), e.getY())) {

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					xAccelerateur = listeAccelerateur.get(a).getFormeAire().getX();
					yAccelerateur = listeAccelerateur.get(a).getFormeAire().getY();
					acc = listeAccelerateur.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.ACCELERATEUR;
					break;

				}

			} // Fin loop
		}
	}

	/**
	 * Méthode qui permet de determiner si un bloc mystere est contenue dans le clic
	 * de la souris
	 * 
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void blocMysterePressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listeBlocMystere.size(); a++) {
				if (listeBlocMystere.get(a).contient(e.getX(), e.getY())) {

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					xBlocMystere = listeBlocMystere.get(a).getCarre().getX();
					yBlocMystere = listeBlocMystere.get(a).getCarre().getY();
					blocMystere = listeBlocMystere.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.BLOCMYSTERE;

					break;

				}

			} // Fin loop
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de l'accelerateur en la bougeant
	 * avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void accelerateurDrag(MouseEvent e) {
		if (listeAccelerateur.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.ACCELERATEUR) {

			xAccelerateur += e.getX() - xPrecedent;
			yAccelerateur += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();

			acc.setX((int) xAccelerateur);
			acc.setY((int) yAccelerateur);
			acc.getFormeAire().setRect(xAccelerateur, yAccelerateur, acc.getTaillePiste(), acc.getTaillePiste());

		}
	}

	/**
	 * Méthode qui permet de modifier la postion du bloc mystere en la bougeant avec
	 * la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Par Tan Tommy Rin
	private void blocMystereDrag(MouseEvent e) {
		if (listeBlocMystere.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.BLOCMYSTERE) {

			xBlocMystere += e.getX() - xPrecedent;
			yBlocMystere += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			blocMystere.setPosition(new Vecteur2D(xBlocMystere, yBlocMystere));
			blocMystere.setCarre(new Rectangle2D.Double(xBlocMystere, yBlocMystere, 87, 87));

		}
	}

	/**
	 * Méthode qui dessine le quadrillage
	 * 
	 * @param g2d paramètre qui permet a dessiner
	 */
	// Alexis Pineda-Alvarado
	private void creationQuadrillage(Graphics2D g2d) {
		Graphics2D g2dCopie = (Graphics2D) g2d.create();
		int x = 80;
		int y = 80;
		int tailleDuCarre = 80;

		for (int i = 0; i < 8; i++) {
			for (int b = 0; b < 5; b++) {
				g2dCopie.setStroke(new BasicStroke(1));
				g2dCopie.setColor(Color.black);
				g2dCopie.drawRect(x * i, y * b, tailleDuCarre, tailleDuCarre);

			}
		}
	}

	public ArrayList<BlocMystere> getListeBlocMystere() {
		return listeBlocMystere;
	}

	public void setListeBlocMystere(ArrayList<BlocMystere> listeBlocMystere) {
		this.listeBlocMystere = listeBlocMystere;
	}

	public int getxPrecedent() {
		return xPrecedent;
	}

	public void setxPrecedent(int xPrecedent) {
		this.xPrecedent = xPrecedent;
	}

	public int getyPrecedent() {
		return yPrecedent;
	}

	public void setyPrecedent(int yPrecedent) {
		this.yPrecedent = yPrecedent;
	}

	public double getxAccelerateur() {
		return xAccelerateur;
	}

	public void setxAccelerateur(double xAccelerateur) {
		this.xAccelerateur = xAccelerateur;
	}

	public double getyAccelerateur() {
		return yAccelerateur;
	}

	public void setyAccelerateur(double yAccelerateur) {
		this.yAccelerateur = yAccelerateur;
	}

	public double getxBlocMystere() {
		return xBlocMystere;
	}

	public void setxBlocMystere(double xBlocMystere) {
		this.xBlocMystere = xBlocMystere;
	}

	public double getyBlocMystere() {
		return yBlocMystere;
	}

	public void setyBlocMystere(double yBlocMystere) {
		this.yBlocMystere = yBlocMystere;
	}

	public double getxPisteHorizontale() {
		return xPisteHorizontale;
	}

	public void setxPisteHorizontale(double xPisteHorizontale) {
		this.xPisteHorizontale = xPisteHorizontale;
	}

	public double getyPisteHorizontale() {
		return yPisteHorizontale;
	}

	public void setyPisteHorizontale(double yPisteHorizontale) {
		this.yPisteHorizontale = yPisteHorizontale;
	}

	public double getxPisteVirageBas() {
		return xPisteVirageBas;
	}

	public void setxPisteVirageBas(double xPisteVirageBas) {
		this.xPisteVirageBas = xPisteVirageBas;
	}

	public double getyPisteVirageBas() {
		return yPisteVirageBas;
	}

	public void setyPisteVirageBas(double yPisteVirageBas) {
		this.yPisteVirageBas = yPisteVirageBas;
	}

	public double getxPisteVirageGauche() {
		return xPisteVirageGauche;
	}

	public void setxPisteVirageGauche(double xPisteVirageGauche) {
		this.xPisteVirageGauche = xPisteVirageGauche;
	}

	public double getyPisteVirageGauche() {
		return yPisteVirageGauche;
	}

	public void setyPisteVirageGauche(double yPisteVirageGauche) {
		this.yPisteVirageGauche = yPisteVirageGauche;
	}

	public double getxPisteVerticale() {
		return xPisteVerticale;
	}

	public void setxPisteVerticale(double xPisteVerticale) {
		this.xPisteVerticale = xPisteVerticale;
	}

	public double getyPisteVerticale() {
		return yPisteVerticale;
	}

	public void setyPisteVerticale(double yPisteVerticale) {
		this.yPisteVerticale = yPisteVerticale;
	}

	public double getxPisteDeDepart() {
		return xPisteDeDepart;
	}

	public void setxPisteDeDepart(double xPisteDeDepart) {
		this.xPisteDeDepart = xPisteDeDepart;
	}

	public double getyPisteDeDepart() {
		return yPisteDeDepart;
	}

	public void setyPisteDeDepart(double yPisteDeDepart) {
		this.yPisteDeDepart = yPisteDeDepart;
	}

	public double getxPisteVirageDroit() {
		return xPisteVirageDroit;
	}

	public void setxPisteVirageDroit(double xPisteVirageDroit) {
		this.xPisteVirageDroit = xPisteVirageDroit;
	}

	public double getyPisteVirageDroit() {
		return yPisteVirageDroit;
	}

	public void setyPisteVirageDroit(double yPisteVirageDroit) {
		this.yPisteVirageDroit = yPisteVirageDroit;
	}

	public double getxPisteVirageHaut() {
		return xPisteVirageHaut;
	}

	public void setxPisteVirageHaut(double xPisteVirageHaut) {
		this.xPisteVirageHaut = xPisteVirageHaut;
	}

	public double getyPisteVirageHaut() {
		return yPisteVirageHaut;
	}

	public void setyPisteVirageHaut(double yPisteVirageHaut) {
		this.yPisteVirageHaut = yPisteVirageHaut;
	}

	public ArrayList<Accelerateur> getListeAccelerateur() {
		return listeAccelerateur;
	}

	public void setListeAccelerateur(ArrayList<Accelerateur> listeAccelerateur) {
		this.listeAccelerateur = listeAccelerateur;
	}

	public ArrayList<PisteHorizontale> getListePisteHorizontale() {
		return listePisteHorizontale;
	}

	public void setListePisteHorizontale(ArrayList<PisteHorizontale> listePisteHorizontale) {
		this.listePisteHorizontale = listePisteHorizontale;
	}

	public ArrayList<PisteVirageBas> getListePisteVirageBas() {
		return listePisteVirageBas;
	}

	public void setListePisteVirageBas(ArrayList<PisteVirageBas> listePisteVirageBas) {
		this.listePisteVirageBas = listePisteVirageBas;
	}

	public ArrayList<PisteVirageGauche> getListePisteVirageGauche() {
		return listePisteVirageGauche;
	}

	public void setListePisteVirageGauche(ArrayList<PisteVirageGauche> listePisteVirageGauche) {
		this.listePisteVirageGauche = listePisteVirageGauche;
	}

	public ArrayList<PisteVerticale> getListePisteVerticale() {
		return listePisteVerticale;
	}

	public void setListePisteVerticale(ArrayList<PisteVerticale> listePisteVerticale) {
		this.listePisteVerticale = listePisteVerticale;
	}

	public ArrayList<PisteDeDepart> getListePisteDeDepart() {
		return listePisteDeDepart;
	}

	public void setListePisteDeDepart(ArrayList<PisteDeDepart> listePisteDeDepart) {
		this.listePisteDeDepart = listePisteDeDepart;
	}

	public ArrayList<PisteVirageDroit> getListePisteVirageDroit() {
		return listePisteVirageDroit;
	}

	public void setListePisteVirageDroit(ArrayList<PisteVirageDroit> listePisteVirageDroit) {
		this.listePisteVirageDroit = listePisteVirageDroit;
	}

	public ArrayList<PisteVirageHaut> getListePisteVirageHaut() {
		return listePisteVirageHaut;
	}

	public void setListePisteVirageHaut(ArrayList<PisteVirageHaut> listePisteVirageHaut) {
		this.listePisteVirageHaut = listePisteVirageHaut;
	}

	public TypeObjetDeplacable getType() {
		return type;
	}

	public void setType(TypeObjetDeplacable type) {
		this.type = type;
	}

}
