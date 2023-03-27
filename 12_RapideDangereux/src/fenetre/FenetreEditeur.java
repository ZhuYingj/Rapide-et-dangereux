package fenetre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
import utilitaireObjets.Regroupement;

/**
 * Classe qui permet de créer et gérer la fenetre éditeur. La sauvegarde d'un
 * circuit(Classe regroupement) est possible.
 * 
 * @author Tan Tommy Rin
 *
 */

public class FenetreEditeur extends JPanel {
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

	private JButton btnRetour;
	private JButton btnAjouterPisteDeDepart;

	private Regroupement regroupement;
	private BlocMystere blocMystere;
	private Accelerateur acc;
	private PisteDeDepart pisteDeDepart;
	private PisteHorizontale pisteHorizontale;
	private PisteVerticale pisteVerticale;
	private PisteVirageBas pisteVirageBas;
	private PisteVirageDroit pisteVirageDroit;
	private PisteVirageGauche pisteVirageGauche;
	private PisteVirageHaut pisteVirageHaut;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

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

	private TypeObjetDeplacable type = TypeObjetDeplacable.ACCELERATEUR;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */

	public FenetreEditeur() {

		setLayout(null);

		setBackground(Color.LIGHT_GRAY);

		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", null, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		PanelObjet panelObjet = new PanelObjet();
		panelObjet.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelObjet.setBounds(920, 11, 386, 720);
		add(panelObjet);
		panelObjet.setLayout(null);
		panelObjet.setEnabled(false);

		JButton btnAjouterAccelerateur = new JButton("+");
		btnAjouterAccelerateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accelerateur accelerateur = new Accelerateur(100, 50);
				listeAccelerateur.add(accelerateur);
				repaint();
			}
		});
		btnAjouterAccelerateur.setBounds(64, 652, 41, 23);
		panelObjet.add(btnAjouterAccelerateur);

		JButton btnSupprimerAccelerateur = new JButton("-");
		btnSupprimerAccelerateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listeAccelerateur.size() != 0) {
					listeAccelerateur.remove(listeAccelerateur.size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerAccelerateur.setBounds(126, 652, 41, 23);
		panelObjet.add(btnSupprimerAccelerateur);

		JButton btnAjouterBlocMystere = new JButton("+");
		btnAjouterBlocMystere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BlocMystere blocMystere = new BlocMystere(15, new Vecteur2D(100, 150));
				listeBlocMystere.add(blocMystere);
				repaint();

			}
		});
		btnAjouterBlocMystere.setBounds(64, 107, 41, 23);
		panelObjet.add(btnAjouterBlocMystere);

		JButton btnSupprimerBlocMystere = new JButton("-");
		btnSupprimerBlocMystere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listeBlocMystere.size() != 0) {
					listeBlocMystere.remove(listeBlocMystere.size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerBlocMystere.setBounds(114, 107, 41, 23);
		panelObjet.add(btnSupprimerBlocMystere);

		JButton btnAjouterPisteHorizontale = new JButton("+");
		btnAjouterPisteHorizontale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteHorizontale pisteHorizontale = new PisteHorizontale(100, 250);
				listePisteHorizontale.add(pisteHorizontale);
				repaint();
			}
		});
		btnAjouterPisteHorizontale.setBounds(64, 231, 41, 23);
		panelObjet.add(btnAjouterPisteHorizontale);

		JButton btnAjouterPisteVirageBas = new JButton("+");
		btnAjouterPisteVirageBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVirageBas pisteVirageBas = new PisteVirageBas(100, 350);
				listePisteVirageBas.add(pisteVirageBas);
				repaint();
			}
		});
		btnAjouterPisteVirageBas.setBounds(64, 378, 41, 23);
		panelObjet.add(btnAjouterPisteVirageBas);

		JButton btnAjouterPisteVirageGauche = new JButton("+");
		btnAjouterPisteVirageGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVirageGauche pisteVirageGauche = new PisteVirageGauche(100, 450);
				listePisteVirageGauche.add(pisteVirageGauche);
				repaint();
			}
		});
		btnAjouterPisteVirageGauche.setBounds(64, 517, 41, 23);
		panelObjet.add(btnAjouterPisteVirageGauche);

		JButton btnSupprimerPisteHorizontale = new JButton("-");
		btnSupprimerPisteHorizontale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listePisteHorizontale.size() != 0) {
					listePisteHorizontale.remove(listePisteHorizontale.size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteHorizontale.setBounds(114, 231, 41, 23);
		panelObjet.add(btnSupprimerPisteHorizontale);

		JButton btnSupprimerPisteVirageBas = new JButton("-");
		btnSupprimerPisteVirageBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listePisteVirageBas.size() != 0) {
					listePisteVirageBas.remove(listePisteVirageBas.size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVirageBas.setBounds(114, 378, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageBas);

		JButton btnSupprimerPisteVirageGauche = new JButton("-");
		btnSupprimerPisteVirageGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listePisteVirageGauche.size() != 0) {
					listePisteVirageGauche.remove(listePisteVirageGauche.size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVirageGauche.setBounds(114, 517, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageGauche);

		btnAjouterPisteDeDepart = new JButton("+");
		btnAjouterPisteDeDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listePisteDeDepart.size() == 0) {
					PisteDeDepart pisteDeDepart = new PisteDeDepart(100, 550);
					listePisteDeDepart.add(pisteDeDepart);
					btnAjouterPisteDeDepart.setEnabled(false);
				}
				repaint();
			}
		});
		btnAjouterPisteDeDepart.setBounds(217, 107, 41, 23);
		panelObjet.add(btnAjouterPisteDeDepart);

		JButton btnAjouterPisteVerticale = new JButton("+");
		btnAjouterPisteVerticale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVerticale pisteVerticale = new PisteVerticale(100, 450);
				listePisteVerticale.add(pisteVerticale);
				repaint();
			}
		});
		btnAjouterPisteVerticale.setBounds(217, 231, 41, 23);
		panelObjet.add(btnAjouterPisteVerticale);

		JButton btnSupprimerPisteVerticale = new JButton("-");
		btnSupprimerPisteVerticale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listePisteVerticale.size() != 0) {
					listePisteVerticale.remove(listePisteVerticale.size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVerticale.setBounds(267, 231, 41, 23);
		panelObjet.add(btnSupprimerPisteVerticale);

		JButton btnSupprimerPisteDeDepart = new JButton("-");
		btnSupprimerPisteDeDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listePisteDeDepart.size() != 0) {
					listePisteDeDepart.remove(listePisteDeDepart.size() - 1);
					btnAjouterPisteDeDepart.setEnabled(true);
					repaint();
				}
			}
		});
		btnSupprimerPisteDeDepart.setBounds(267, 107, 41, 23);
		panelObjet.add(btnSupprimerPisteDeDepart);

		JButton btnAjouterPisteVirageDroite = new JButton("+");
		btnAjouterPisteVirageDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVirageDroit psiteVirageDroit = new PisteVirageDroit(100, 650);
				listePisteVirageDroit.add(psiteVirageDroit);
				repaint();
			}
		});
		btnAjouterPisteVirageDroite.setBounds(217, 378, 41, 23);
		panelObjet.add(btnAjouterPisteVirageDroite);

		JButton btnSupprimerPisteVirageDroite = new JButton("-");
		btnSupprimerPisteVirageDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listePisteVirageDroit.size() != 0) {
					listePisteVirageDroit.remove(listePisteVirageDroit.size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVirageDroite.setBounds(267, 378, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageDroite);

		JButton btnAjouterPisteVirageHaut = new JButton("+");
		btnAjouterPisteVirageHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVirageHaut pisteVirageHaut = new PisteVirageHaut(250, 150);
				listePisteVirageHaut.add(pisteVirageHaut);
				repaint();
			}
		});
		btnAjouterPisteVirageHaut.setBounds(217, 517, 41, 23);
		panelObjet.add(btnAjouterPisteVirageHaut);

		JButton btnSupprimerPisteVirageHaut = new JButton("-");
		btnSupprimerPisteVirageHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listePisteVirageHaut.size() != 0) {
					listePisteVirageHaut.remove(listePisteVirageHaut.size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVirageHaut.setBounds(267, 517, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageHaut);

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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (listePisteVirageDroit.size() != 0) {

			for (int a = 0; a < listePisteVirageDroit.size(); a++) {

				listePisteVirageDroit.get(a).dessiner(g2d);

			}

		}

		if (listePisteDeDepart.size() != 0) {

			for (int a = 0; a < listePisteDeDepart.size(); a++) {

				listePisteDeDepart.get(a).dessiner(g2d);

			}

		}

		if (listePisteHorizontale.size() != 0) {

			for (int a = 0; a < listePisteHorizontale.size(); a++) {

				listePisteHorizontale.get(a).dessiner(g2d);

			}

		}

		if (listePisteVirageBas.size() != 0) {

			for (int a = 0; a < listePisteVirageBas.size(); a++) {

				listePisteVirageBas.get(a).dessiner(g2d);

			}
		}

		if (listePisteVirageGauche.size() != 0) {

			for (int a = 0; a < listePisteVirageGauche.size(); a++) {

				listePisteVirageGauche.get(a).dessiner(g2d);

			}
		}
		if (listePisteVerticale.size() != 0) {

			for (int a = 0; a < listePisteVerticale.size(); a++) {

				listePisteVerticale.get(a).dessiner(g2d);

			}

		}
		if (listePisteVirageHaut.size() != 0) {

			for (int a = 0; a < listePisteVirageHaut.size(); a++) {

				listePisteVirageHaut.get(a).dessiner(g2d);

			}

		}
		if (listeAccelerateur.size() != 0) {

			for (int a = 0; a < listeAccelerateur.size(); a++) {

				listeAccelerateur.get(a).dessiner(g2d);

			}

		}
		if (listeBlocMystere.size() != 0) {

			for (int a = 0; a < listeBlocMystere.size(); a++) {

				listeBlocMystere.get(a).dessiner(g2d);

			}

		}

	}

	/**
	 * Méthode qui permet de détecter lorsque la souris n'est plus enfoncé et
	 * redessine
	 * 
	 * @param e Évenement de souris
	 */
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

		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de depart en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
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

		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste verticale en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
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

		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage haut en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
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

		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage gauche en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */
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

		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste de virage bas en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */

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

		}
	}

	/**
	 * Méthode qui permet de modifier la postion de la piste horizontale en la
	 * bougeant avec la souris
	 * 
	 * @param e Évenement de souris
	 */

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

		}
	}

	/**
	 * Méthode qui permet de determiner si une piste de virage haut est contenue
	 * dans le clic de la souris
	 * 
	 * @param e Évenement de souris
	 */

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
}
