package fenetre;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JPanel;

import application.OutilsImage;
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
import utilitaireObjets.Fumee;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe permettant de créer et gérer une fenetre de regroupement. Le
 * regroupement contient des morceaux de pistes, des blocs mysteres et une
 * voiture.
 * 
 * @author Tan Tommy Rin
 * @author Alexis Pineda-Alvarado
 * @author Kevin Nguyen
 *
 */

public class PanelRegroupement extends JPanel {

	private static final long serialVersionUID = -3942442779188948737L;

	private Rectangle2D.Double poubelle = new Rectangle2D.Double(640, 320, 80, 80);
	private BlocMystere blocMystere;
	private Accelerateur acc;
	private Fumee fumee;
	private PisteDeDepart pisteDeDepart;
	private PisteHorizontale pisteHorizontale;
	private PisteVerticale pisteVerticale;
	private PisteVirageBas pisteVirageBas;
	private PisteVirageDroit pisteVirageDroit;
	private PisteVirageGauche pisteVirageGauche;
	private PisteVirageHaut pisteVirageHaut;

	private int xPrecedent, yPrecedent;

	private double xBlocMystere;
	private double yBlocMystere;
	private boolean poubelleVide = true;

	private ArrayList<Accelerateur> listeAccelerateur = new ArrayList<Accelerateur>();

	private ArrayList<BlocMystere> listeBlocMystere = new ArrayList<BlocMystere>();
	private ArrayList<PisteHorizontale> listePisteHorizontale = new ArrayList<PisteHorizontale>();
	private ArrayList<PisteVirageBas> listePisteVirageBas = new ArrayList<PisteVirageBas>();
	private ArrayList<PisteVirageGauche> listePisteVirageGauche = new ArrayList<PisteVirageGauche>();
	private ArrayList<PisteVerticale> listePisteVerticale = new ArrayList<PisteVerticale>();
	private ArrayList<PisteDeDepart> listePisteDeDepart = new ArrayList<PisteDeDepart>();
	private ArrayList<PisteVirageDroit> listePisteVirageDroit = new ArrayList<PisteVirageDroit>();
	private ArrayList<PisteVirageHaut> listePisteVirageHaut = new ArrayList<PisteVirageHaut>();
	private ArrayList<Fumee> listeFumee = new ArrayList<Fumee>();

	private boolean objetSelectionne = false;
	private boolean jouer = false;

	private TypeObjetDeplacable type;
	private int tailleDuCarre;
	private int longueur;
	private int hauteur;
	private int indexObjetPris;
	/** L'image de la poubelle **/
	private JLabel lblPoubelle;

	/**
	 * Constructeur du panel de regroupement
	 */
	// Tan Tommy Rin
	public PanelRegroupement() {
		setLayout(null);

		lblPoubelle = new JLabel("");
		lblPoubelle.setBounds(640, 320, 80, 80);

		URL urlPoubelle = getClass().getClassLoader().getResource("poubelleVide.png");
		ImageIcon poubelleVide = new ImageIcon(urlPoubelle);
		lblPoubelle.setIcon(poubelleVide);

		add(lblPoubelle);

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
				
				// Pour la morceau de fumee
				fumeeDrag(e);

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
				// Condition pour la fumee
				fumeePressed(e);

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
	// Tan Tommy Rin
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Graphics2D g2dCopie = (Graphics2D) g2d.create();
		creationQuadrillage(g2dCopie);
		g2dCopie.setColor(Color.CYAN);
		g2dCopie.setStroke(new BasicStroke(4));

		for (int a = 0; a < listePisteVirageDroit.size(); a++) {

			listePisteVirageDroit.get(a).dessiner(g2d);
			if (listePisteVirageDroit.get(a).getNombrePisteColle() != 2 && jouer == true) {
				Rectangle2D.Double pisteIncomplete = new Rectangle2D.Double(listePisteVirageDroit.get(a).getX(),
						listePisteVirageDroit.get(a).getY(), listePisteVirageDroit.get(a).getTaillePiste(),
						listePisteVirageDroit.get(a).getTaillePiste());
				g2dCopie.draw(pisteIncomplete);

			}
		}

		for (int a = 0; a < listePisteDeDepart.size(); a++) {

			listePisteDeDepart.get(a).dessiner(g2d);
			if (listePisteDeDepart.get(0).getNombrePisteColle() != 2 && jouer == true) {
				System.out.println("s");
				Rectangle2D.Double pisteIncomplete = new Rectangle2D.Double(listePisteDeDepart.get(a).getX(),
						listePisteDeDepart.get(a).getY(), listePisteDeDepart.get(a).getTaillePiste(),
						listePisteDeDepart.get(a).getTaillePiste());
				g2dCopie.draw(pisteIncomplete);

			}
		}

		for (int a = 0; a < listePisteHorizontale.size(); a++) {

			listePisteHorizontale.get(a).dessiner(g2d);
			if (listePisteHorizontale.get(a).getNombrePisteColle() != 2 && jouer == true) {
				Rectangle2D.Double pisteIncomplete = new Rectangle2D.Double(listePisteHorizontale.get(a).getX(),
						listePisteHorizontale.get(a).getY(), listePisteHorizontale.get(a).getTaillePiste(),
						listePisteHorizontale.get(a).getTaillePiste());
				g2dCopie.draw(pisteIncomplete);

			}
		}

		for (int a = 0; a < listePisteVirageBas.size(); a++) {

			listePisteVirageBas.get(a).dessiner(g2d);
			if (listePisteVirageBas.get(a).getNombrePisteColle() != 2 && jouer == true) {
				Rectangle2D.Double pisteIncomplete = new Rectangle2D.Double(listePisteVirageBas.get(a).getX(),
						listePisteVirageBas.get(a).getY(), listePisteVirageBas.get(a).getTaillePiste(),
						listePisteVirageBas.get(a).getTaillePiste());
				g2dCopie.draw(pisteIncomplete);

			}
		}

		for (int a = 0; a < listePisteVirageGauche.size(); a++) {

			listePisteVirageGauche.get(a).dessiner(g2d);
			if (listePisteVirageGauche.get(a).getNombrePisteColle() != 2 && jouer == true) {
				Rectangle2D.Double pisteIncomplete = new Rectangle2D.Double(listePisteVirageGauche.get(a).getX(),
						listePisteVirageGauche.get(a).getY(), listePisteVirageGauche.get(a).getTaillePiste(),
						listePisteVirageGauche.get(a).getTaillePiste());
				g2dCopie.draw(pisteIncomplete);

			}
		}

		for (int a = 0; a < listePisteVerticale.size(); a++) {

			listePisteVerticale.get(a).dessiner(g2d);
			if (listePisteVerticale.get(a).getNombrePisteColle() != 2 && jouer == true) {
				Rectangle2D.Double pisteIncomplete = new Rectangle2D.Double(listePisteVerticale.get(a).getX(),
						listePisteVerticale.get(a).getY(), listePisteVerticale.get(a).getTaillePiste(),
						listePisteVerticale.get(a).getTaillePiste());
				g2dCopie.draw(pisteIncomplete);

			}
		}

		for (int a = 0; a < listePisteVirageHaut.size(); a++) {

			listePisteVirageHaut.get(a).dessiner(g2d);
			if (listePisteVirageHaut.get(a).getNombrePisteColle() != 2 && jouer == true) {
				Rectangle2D.Double pisteIncomplete = new Rectangle2D.Double(listePisteVirageHaut.get(a).getX(),
						listePisteVirageHaut.get(a).getY(), listePisteVirageHaut.get(a).getTaillePiste(),
						listePisteVirageHaut.get(a).getTaillePiste());
				g2dCopie.draw(pisteIncomplete);

			}
		}

		for (int a = 0; a < listeAccelerateur.size(); a++) {

			listeAccelerateur.get(a).dessiner(g2d);

		}

		for (int a = 0; a < listeFumee.size(); a++) {

			listeFumee.get(a).dessiner(g2d);
		}

		Image boiteMystere = OutilsImage.lireImageEtRedimensionner("LuckyBox.png", 15, 15);
		for (int a = 0; a < listeBlocMystere.size(); a++) {

			g2d.drawImage(boiteMystere, (int) listeBlocMystere.get(a).getPosition().getX(),
					(int) listeBlocMystere.get(a).getPosition().getY(), null);

			boiteMystere.flush();
		}
		jouer = false;
		if (poubelleVide == false) {
			URL urlPoubelle = getClass().getClassLoader().getResource("poubelleRemplie.png");
			ImageIcon poubelleRemplie = new ImageIcon(urlPoubelle);
			lblPoubelle.setIcon(poubelleRemplie);
		}

	}

	/**
	 * Méthode qui permet de détecter lorsque la souris n'est plus enfoncé et
	 * redessine. Si l'objet est relaché sur la poubelle, elle s'efface.
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void relachementSouris(MouseEvent e) {

		objetSelectionne = false;
		if (type == TypeObjetDeplacable.BLOCMYSTERE
				&& poubelle.contains(listeBlocMystere.get(indexObjetPris).getCarre())) {
			listeBlocMystere.remove(indexObjetPris);
			poubelleVide = false;
		} else if (type == TypeObjetDeplacable.PISTEHORIZONTALE
				&& poubelle.contains(listePisteHorizontale.get(indexObjetPris).getFormeAire())) {
			listePisteHorizontale.remove(indexObjetPris);
			poubelleVide = false;
		} else if (type == TypeObjetDeplacable.PISTEVERTICALE
				&& poubelle.contains(listePisteVerticale.get(indexObjetPris).getFormeAire())) {
			listePisteVerticale.remove(indexObjetPris);
			poubelleVide = false;
		} else if (type == TypeObjetDeplacable.ACCELERATEUR
				&& poubelle.contains(listeAccelerateur.get(indexObjetPris).getFormeAire())) {
			listeAccelerateur.remove(indexObjetPris);
			poubelleVide = false;
		} else if (type == TypeObjetDeplacable.PISTEVIRAGEBAS
				&& poubelle.contains(listePisteVirageBas.get(indexObjetPris).getFormeAire())) {
			listePisteVirageBas.remove(indexObjetPris);
			poubelleVide = false;
		} else if (type == TypeObjetDeplacable.PISTEVIRAGEDROIT
				&& poubelle.contains(listePisteVirageDroit.get(indexObjetPris).getFormeAire())) {
			listePisteVirageDroit.remove(indexObjetPris);
			poubelleVide = false;
		} else if (type == TypeObjetDeplacable.PISTEVIRAGEGAUCHE
				&& poubelle.contains(listePisteVirageGauche.get(indexObjetPris).getFormeAire())) {
			listePisteVirageGauche.remove(indexObjetPris);
			poubelleVide = false;
		} else if (type == TypeObjetDeplacable.PISTEVIRAGEHAUT
				&& poubelle.contains(listePisteVirageHaut.get(indexObjetPris).getFormeAire())) {
			listePisteVirageHaut.remove(indexObjetPris);
			poubelleVide = false;
		} else if (type == TypeObjetDeplacable.FUMEE
				&& poubelle.contains(listeFumee.get(indexObjetPris).getFormeAire())) {
			listeFumee.remove(indexObjetPris);
			poubelleVide = false;
		} else {

		}

		repaint();
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage droite en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void pisteVirageDroitDrag(MouseEvent e) {
		if (listePisteVirageDroit.size() != 0 && objetSelectionne == true
				&& type == TypeObjetDeplacable.PISTEVIRAGEDROIT) {
			if (poubelle.contains(pisteVirageDroit.getFormeAire())) {
				pisteVirageDroit.setTaillePiste(30);
			} else {
				pisteVirageDroit.setTaillePiste(80);
			}
			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVirageDroit.setX(collerX(e));
			pisteVirageDroit.setY(collerY(e));
			pisteVirageDroit.getFormeAire().setRect(pisteVirageDroit.getX(), pisteVirageDroit.getY(),
					pisteVirageDroit.getTaillePiste(), pisteVirageDroit.getTaillePiste());
			pisteVirageDroit.setMurHaut((int) collerY(e));
			pisteVirageDroit.setMurBas((int) collerY(e) + tailleDuCarre);
			pisteVirageDroit.setMurDroite((int) collerX(e) + tailleDuCarre);
			pisteVirageDroit.setMurGauche((int) collerX(e));
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de depart en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void pisteDeDepartDrag(MouseEvent e) {
		if (listePisteDeDepart.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.PISTEDEDEPART) {

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteDeDepart.setX(collerX(e));
			pisteDeDepart.setY(collerY(e));
			pisteDeDepart.getVoiture()
					.setPosition(new Vecteur2D(pisteDeDepart.getX() + pisteDeDepart.getTaillePiste() / 4,
							pisteDeDepart.getY() + pisteDeDepart.getTaillePiste() / 4));
			pisteDeDepart.getVoiture2()
					.setPosition(new Vecteur2D(pisteDeDepart.getX() + pisteDeDepart.getTaillePiste() / 4,
							pisteDeDepart.getY() + pisteDeDepart.getTaillePiste() * 3 / 4));
			pisteDeDepart.getFormeAire().setRect(pisteDeDepart.getX(), pisteDeDepart.getY(),
					pisteDeDepart.getTaillePiste(), pisteDeDepart.getTaillePiste());
			pisteDeDepart.setMurHaut((int) collerY(e));
			pisteDeDepart.setMurBas((int) collerY(e) + tailleDuCarre);
			pisteDeDepart.setMurDroite((int) collerX(e) + tailleDuCarre);
			pisteDeDepart.setMurGauche((int) collerX(e));

		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste verticale en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void pisteVerticaleDrag(MouseEvent e) {
		if (listePisteVerticale.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.PISTEVERTICALE) {
			if (poubelle.contains(pisteVerticale.getFormeAire())) {
				pisteVerticale.setTaillePiste(30);
			} else {
				pisteVerticale.setTaillePiste(80);
			}
			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVerticale.setX(collerX(e));
			pisteVerticale.setY(collerY(e));
			pisteVerticale.getFormeAire().setRect(pisteVerticale.getX(), pisteVerticale.getY(),
					pisteVerticale.getTaillePiste(), pisteVerticale.getTaillePiste());
			pisteVerticale.setMurHaut((int) collerY(e));
			pisteVerticale.setMurBas((int) collerY(e) + tailleDuCarre);
			pisteVerticale.setMurDroite((int) collerX(e) + tailleDuCarre);
			pisteVerticale.setMurGauche((int) collerX(e));
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage haut en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void pisteVirageHautDrag(MouseEvent e) {
		if (listePisteVirageHaut.size() != 0 && objetSelectionne == true
				&& type == TypeObjetDeplacable.PISTEVIRAGEHAUT) {
			if (poubelle.contains(pisteVirageHaut.getFormeAire())) {
				pisteVirageHaut.setTaillePiste(30);
			} else {
				pisteVirageHaut.setTaillePiste(80);
			}
			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVirageHaut.setX(collerX(e));
			pisteVirageHaut.setY(collerY(e));
			pisteVirageHaut.getFormeAire().setRect(pisteVirageHaut.getX(), pisteVirageHaut.getY(),
					pisteVirageHaut.getTaillePiste(), pisteVirageHaut.getTaillePiste());
			pisteVirageHaut.setMurHaut((int) collerY(e));
			pisteVirageHaut.setMurBas((int) collerY(e) + tailleDuCarre);
			pisteVirageHaut.setMurDroite((int) collerX(e) + tailleDuCarre);
			pisteVirageHaut.setMurGauche((int) collerX(e));
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage gauche en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void pisteVirageGaucheDrag(MouseEvent e) {
		if (listePisteVirageGauche.size() != 0 && objetSelectionne == true
				&& type == TypeObjetDeplacable.PISTEVIRAGEGAUCHE) {
			if (poubelle.contains(pisteVirageGauche.getFormeAire())) {
				pisteVirageGauche.setTaillePiste(30);
			} else {
				pisteVirageGauche.setTaillePiste(80);
			}
			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVirageGauche.setX(collerX(e));
			pisteVirageGauche.setY(collerY(e));
			pisteVirageGauche.getFormeAire().setRect(pisteVirageGauche.getX(), pisteVirageGauche.getY(),
					pisteVirageGauche.getTaillePiste(), pisteVirageGauche.getTaillePiste());
			pisteVirageGauche.setMurHaut((int) collerY(e));
			pisteVirageGauche.setMurBas((int) collerY(e) + tailleDuCarre);
			pisteVirageGauche.setMurDroite((int) collerX(e) + tailleDuCarre);
			pisteVirageGauche.setMurGauche((int) collerX(e));
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage bas en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void pisteVirageBasDrag(MouseEvent e) {
		if (listePisteVirageBas.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.PISTEVIRAGEBAS) {
			if (poubelle.contains(pisteVirageBas.getFormeAire())) {
				pisteVirageBas.setTaillePiste(30);
			} else {
				pisteVirageBas.setTaillePiste(80);
			}
			xPrecedent = e.getX();
			yPrecedent = e.getY();
			pisteVirageBas.setX(collerX(e));
			pisteVirageBas.setY(collerY(e));
			pisteVirageBas.getFormeAire().setRect(pisteVirageBas.getX(), pisteVirageBas.getY(),
					pisteVirageBas.getTaillePiste(), pisteVirageBas.getTaillePiste());
			pisteVirageBas.setMurHaut((int) collerY(e));
			pisteVirageBas.setMurBas((int) collerY(e) + tailleDuCarre);
			pisteVirageBas.setMurDroite((int) collerX(e) + tailleDuCarre);
			pisteVirageBas.setMurGauche((int) collerX(e));
		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste horizontale en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void pisteHorizontaleDrag(MouseEvent e) {
		if (listePisteHorizontale.size() != 0 && objetSelectionne == true
				&& type == TypeObjetDeplacable.PISTEHORIZONTALE) {
			if (poubelle.contains(pisteHorizontale.getFormeAire())) {
				pisteHorizontale.setTaillePiste(30);
			} else {
				pisteHorizontale.setTaillePiste(80);
			}

			xPrecedent = e.getX();
			yPrecedent = e.getY();

			pisteHorizontale.setX(collerX(e));
			pisteHorizontale.setY(collerY(e));
			pisteHorizontale.getFormeAire().setRect(pisteHorizontale.getX(), pisteHorizontale.getY(),
					pisteHorizontale.getTaillePiste(), pisteHorizontale.getTaillePiste());

			pisteHorizontale.setMurHaut((int) collerY(e));
			pisteHorizontale.setMurBas((int) collerY(e) + tailleDuCarre);
			pisteHorizontale.setMurDroite((int) collerX(e) + tailleDuCarre);
			pisteHorizontale.setMurGauche((int) collerX(e));
		}
	}

	/**
	 * Méthode qui permet de determiner si une piste de virage haut est contenue
	 * dans le clic de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void pisteVirageHautPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVirageHaut.size(); a++) {
				if (listePisteVirageHaut.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
					xPrecedent = e.getX();
					yPrecedent = e.getY();

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
	// Tan Tommy Rin
	private void pisteVirageDroitPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVirageDroit.size(); a++) {
				if (listePisteVirageDroit.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
					xPrecedent = e.getX();
					yPrecedent = e.getY();

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
	// Tan Tommy Rin
	private void pisteDeDepartPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteDeDepart.size(); a++) {
				if (listePisteDeDepart.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
					xPrecedent = e.getX();
					yPrecedent = e.getY();

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
	// Tan Tommy Rin
	private void pisteVerticalePressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVerticale.size(); a++) {
				if (listePisteVerticale.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
					xPrecedent = e.getX();
					yPrecedent = e.getY();

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
	// Tan Tommy Rin
	private void pisteVirageGauchePressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVirageGauche.size(); a++) {
				if (listePisteVirageGauche.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
					xPrecedent = e.getX();
					yPrecedent = e.getY();

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
	// Tan Tommy Rin
	private void pisteVirageBasPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteVirageBas.size(); a++) {
				if (listePisteVirageBas.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
					xPrecedent = e.getX();
					yPrecedent = e.getY();

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
	// Tan Tommy Rin
	private void pisteHorizontalePressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listePisteHorizontale.size(); a++) {
				if (listePisteHorizontale.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
					xPrecedent = e.getX();
					yPrecedent = e.getY();

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
	// Tan Tommy Rin
	private void accelerateurPressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listeAccelerateur.size(); a++) {
				if (listeAccelerateur.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
					xPrecedent = e.getX();
					yPrecedent = e.getY();

					acc = listeAccelerateur.get(a);

					objetSelectionne = true;
					type = TypeObjetDeplacable.ACCELERATEUR;
					break;

				}

			} // Fin loop
		}
	}
	/**
	 * Méthode qui permet de determiner si un fumée est contenue dans le clic
	 * de la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Alexis Pineda-Alvarado
	private void fumeePressed(MouseEvent e) { 
		if(objetSelectionne == false) {
			for(int a = 0; a < listeFumee.size(); a++) {
				if(listeFumee.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
					xPrecedent = e.getX();
					yPrecedent = e.getY();
					
					fumee = listeFumee.get(a);
					
					objetSelectionne = true;
					type = TypeObjetDeplacable.FUMEE;
					break;
				}
			}
		}
	}

	/**
	 * Méthode qui permet de determiner si un bloc mystere est contenue dans le clic
	 * de la souris
	 * 
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void blocMysterePressed(MouseEvent e) {
		if (objetSelectionne == false) {
			for (int a = 0; a < listeBlocMystere.size(); a++) {
				if (listeBlocMystere.get(a).contient(e.getX(), e.getY())) {
					indexObjetPris = a;
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
	// Tan Tommy Rin
	private void accelerateurDrag(MouseEvent e) {
		if (listeAccelerateur.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.ACCELERATEUR) {

			xPrecedent = e.getX();
			yPrecedent = e.getY();

			acc.setX(collerX(e));
			acc.setY(collerY(e));
			acc.getFormeAire().setRect(acc.getX(), acc.getY(), acc.getTaillePiste(), acc.getTaillePiste());

		}
	}
	
	/**
	 * Méthode qui permet de modifier la postion de l'accelerateur en la bougeant
	 * avec la souris
	 * 
	 * @param e Évenement de souris
	 */
	//Alexis Pineda-Alvarado
	private void fumeeDrag(MouseEvent e) {
		if(listeFumee.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.FUMEE) {
			
			xPrecedent = e.getX();
			yPrecedent = e.getY();
			
			fumee.setX(collerX(e));
			fumee.setY(collerY(e));
			fumee.getFormeAire().setRect(fumee.getX(), fumee.getY(), fumee.getTaillePiste(), fumee.getTaillePiste());
		}
	}

	/**
	 * Méthode qui permet de modifier la postion du bloc mystere en la bougeant avec
	 * la souris
	 * 
	 * @param e Évenement de souris
	 */
	// Tan Tommy Rin
	private void blocMystereDrag(MouseEvent e) {
		if (listeBlocMystere.size() != 0 && objetSelectionne == true && type == TypeObjetDeplacable.BLOCMYSTERE) {

			xBlocMystere += e.getX() - xPrecedent;
			yBlocMystere += e.getY() - yPrecedent;

			xPrecedent = e.getX();
			yPrecedent = e.getY();
			blocMystere.setPosition(new Vecteur2D(xBlocMystere, yBlocMystere));
			blocMystere.setCarre(new Rectangle2D.Double(xBlocMystere, yBlocMystere, 80, 80));

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
		tailleDuCarre = 80;

		for (int i = 0; i < 8; i++) {
			for (int b = 0; b < 5; b++) {
				g2dCopie.setStroke(new BasicStroke(1));
				g2dCopie.setColor(Color.black);
				g2dCopie.drawRect(x * i, y * b, tailleDuCarre, tailleDuCarre);

			}
		}
	}

	/**
	 * Effet aimant pour coller un morceau de piste dans un carre en x
	 * 
	 * @param e Évenement de souris
	 * @return Le x du coin supérieur gauche
	 */
	// Kevin Nguyen
	private int collerX(MouseEvent e) {
		xPrecedent = e.getX();
		if (type == TypeObjetDeplacable.PISTEDEDEPART) {
			longueur = (int) (xPrecedent / tailleDuCarre) % 8 * tailleDuCarre;
		} else {
			longueur = (int) (xPrecedent / tailleDuCarre) % 9 * tailleDuCarre;
		}

		return longueur;
	}

	/**
	 * Effet aimant pour coller un morceau de piste dans un carre en y
	 * 
	 * @param e Évenement de souris
	 * @return Le y du coin supérieur gauche
	 */
	// Kevin Nguyen
	private int collerY(MouseEvent e) {
		yPrecedent = e.getY();

		hauteur = (int) (yPrecedent / tailleDuCarre) % 5 * tailleDuCarre;
		return hauteur;
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

	public ArrayList<Fumee> getListeFumee() {
		return listeFumee;
	}

	public void setListeFumee(ArrayList<Fumee> listeFumee) {
		this.listeFumee = listeFumee;
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

	public boolean isJouer() {
		return jouer;
	}

	public void setJouer(boolean jouer) {
		this.jouer = jouer;
	}

	public TypeObjetDeplacable getType() {
		return type;
	}

	public void setType(TypeObjetDeplacable type) {
		this.type = type;
	}

	public boolean isPoubelleVide() {
		return poubelleVide;
	}

	public void setPoubelleVide(boolean poubelleVide) {
		this.poubelleVide = poubelleVide;
	}

}
