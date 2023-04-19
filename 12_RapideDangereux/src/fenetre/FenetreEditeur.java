package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import application.GestionnaireDeFichiersSurLeBureau;
import geometrie.Vecteur2D;
import interfaces.TypePiste;
import utilitaireObjets.Accelerateur;
import utilitaireObjets.BlocMystere;
import utilitaireObjets.Fumee;
import utilitaireObjets.PisteDeDepart;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.PisteVirageDroit;
import utilitaireObjets.PisteVirageGauche;
import utilitaireObjets.PisteVirageHaut;
import utilitaireObjets.Regroupement;
import utilitaireObjets.Voiture;

/**
 * Classe qui permet de créer et gérer la fenetre éditeur.
 * 
 * @author Tan Tommy Rin
 */

public class FenetreEditeur extends JPanel {

	private int nombrePisteFerme = 0;
	private JButton btnAjouterAccelerateur;
	private boolean pisteFerme = false;
	private Regroupement regroupement;
	private PanelRegroupement panelRegroupement;
	private GestionnaireDeFichiersSurLeBureau gestionFich;
	private String pisteCourante = "Piste1.dat";

	private JButton btnSauvegarde;
	private JButton btnJouer;
	private JButton btnRetour;
	private JButton btnAjouterPisteDeDepart;
	private JComboBox<String> comboBoxPiste;

	private Regroupement regroupementSauvegarde;

	private PanelObjet panelObjet;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private JLabel lblImage;

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 */
	// Tan Tommy Rin
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */
	// Tan Tommy Rin
	public FenetreEditeur() {
		setBounds(0, 0, 1600, 800);
		setForeground(new Color(255, 255, 255));
		gestionFich = new GestionnaireDeFichiersSurLeBureau();
		setLayout(null);

		setBackground(Color.LIGHT_GRAY);

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", null, -1);
			}
		});

		panelRegroupement = new PanelRegroupement();
		panelRegroupement.setForeground(Color.WHITE);

		panelRegroupement.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panelRegroupement.setBounds(224, 97, 769, 400);
		add(panelRegroupement);
		panelRegroupement.setLayout(null);

		JLabel lblTextEditeur = new JLabel("MODE ÉDITEUR");
		lblTextEditeur.setForeground(new Color(255, 255, 255));
		lblTextEditeur.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 30));
		lblTextEditeur.setBounds(482, 15, 250, 71);
		add(lblTextEditeur);

		panelObjet = new PanelObjet();
		panelObjet.setForeground(Color.WHITE);
		panelObjet.setBackground(Color.WHITE);
		panelObjet.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelObjet.setBounds(1074, 11, 386, 720);
		add(panelObjet);
		panelObjet.setLayout(null);

		btnAjouterAccelerateur = new JButton("+");
		btnAjouterAccelerateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajoutAccelerateur();
			}
		});
		btnAjouterAccelerateur.setBounds(74, 686, 41, 23);
		panelObjet.add(btnAjouterAccelerateur);

		JButton btnSupprimerAccelerateur = new JButton("-");
		btnSupprimerAccelerateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerAccelerateur();
			}
		});
		btnSupprimerAccelerateur.setBounds(114, 686, 41, 23);
		panelObjet.add(btnSupprimerAccelerateur);

		JButton btnAjouterBlocMystere = new JButton("+");
		btnAjouterBlocMystere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterBlocMystere();
			}
		});
		btnAjouterBlocMystere.setBounds(74, 107, 41, 23);
		panelObjet.add(btnAjouterBlocMystere);

		JButton btnSupprimerBlocMystere = new JButton("-");
		btnSupprimerBlocMystere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerBlocMystere();
			}
		});
		btnSupprimerBlocMystere.setBounds(114, 107, 41, 23);
		panelObjet.add(btnSupprimerBlocMystere);

		JButton btnAjouterPisteHorizontale = new JButton("+");
		btnAjouterPisteHorizontale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterPisteHorizontale();
			}
		});
		btnAjouterPisteHorizontale.setBounds(74, 245, 41, 23);
		panelObjet.add(btnAjouterPisteHorizontale);

		JButton btnAjouterPisteVirageBas = new JButton("+");
		btnAjouterPisteVirageBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterVirageBas();
			}
		});

		btnAjouterPisteVirageBas.setBounds(74, 389, 41, 23);
		panelObjet.add(btnAjouterPisteVirageBas);

		JButton btnAjouterPisteVirageGauche = new JButton("+");
		btnAjouterPisteVirageGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterPisteGauche();
			}
		});
		btnAjouterPisteVirageGauche.setBounds(225, 389, 41, 23);
		panelObjet.add(btnAjouterPisteVirageGauche);

		JButton btnSupprimerPisteHorizontale = new JButton("-");
		btnSupprimerPisteHorizontale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerHorizontale();
			}
		});
		btnSupprimerPisteHorizontale.setBounds(114, 245, 41, 23);
		panelObjet.add(btnSupprimerPisteHorizontale);

		JButton btnSupprimerPisteVirageBas = new JButton("-");
		btnSupprimerPisteVirageBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerVirageBas();
			}
		});
		btnSupprimerPisteVirageBas.setBounds(114, 389, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageBas);

		JButton btnSupprimerPisteVirageGauche = new JButton("-");
		btnSupprimerPisteVirageGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerVirageGauche();
			}
		});
		btnSupprimerPisteVirageGauche.setBounds(265, 389, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageGauche);

		btnAjouterPisteDeDepart = new JButton("+");
		btnAjouterPisteDeDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterPisteDepart();
			}
		});
		btnAjouterPisteDeDepart.setBounds(225, 107, 41, 23);
		panelObjet.add(btnAjouterPisteDeDepart);

		JButton btnAjouterPisteVerticale = new JButton("+");
		btnAjouterPisteVerticale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterPisteVerticale();
			}
		});
		btnAjouterPisteVerticale.setBounds(225, 245, 41, 23);
		panelObjet.add(btnAjouterPisteVerticale);

		JButton btnSupprimerPisteVerticale = new JButton("-");
		btnSupprimerPisteVerticale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerPisteVerticale();
			}
		});
		btnSupprimerPisteVerticale.setBounds(265, 245, 41, 23);
		panelObjet.add(btnSupprimerPisteVerticale);

		JButton btnSupprimerPisteDeDepart = new JButton("-");
		btnSupprimerPisteDeDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerPisteDepart();
			}
		});
		btnSupprimerPisteDeDepart.setBounds(265, 107, 41, 23);
		panelObjet.add(btnSupprimerPisteDeDepart);

		JButton btnAjouterPisteVirageDroite = new JButton("+");
		btnAjouterPisteVirageDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterPisteDroit();
			}
		});
		btnAjouterPisteVirageDroite.setBounds(225, 535, 41, 23);
		panelObjet.add(btnAjouterPisteVirageDroite);

		JButton btnSupprimerPisteVirageDroite = new JButton("-");
		btnSupprimerPisteVirageDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerPisteDroite();
			}
		});
		btnSupprimerPisteVirageDroite.setBounds(265, 535, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageDroite);

		JButton btnAjouterPisteVirageHaut = new JButton("+");
		btnAjouterPisteVirageHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterPisteVirageHaut();
			}
		});
		btnAjouterPisteVirageHaut.setBounds(74, 535, 41, 23);
		panelObjet.add(btnAjouterPisteVirageHaut);

		JButton btnSupprimerPisteVirageHaut = new JButton("-");
		btnSupprimerPisteVirageHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerPisteVirageHaut();
			}
		});
		btnSupprimerPisteVirageHaut.setBounds(114, 535, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageHaut);

		JButton btnAjouterFumee = new JButton("+");
		btnAjouterFumee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterFumee();
			}
		});
		btnAjouterFumee.setBounds(225, 686, 41, 23);
		panelObjet.add(btnAjouterFumee);

		JButton btnSupprimerFumee = new JButton("-");
		btnSupprimerFumee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerFumee();
			}
		});
		btnSupprimerFumee.setBounds(265, 686, 41, 23);
		panelObjet.add(btnSupprimerFumee);

		JLabel lblAcc = new JLabel("Accelerateur");
		lblAcc.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblAcc.setBounds(74, 570, 81, 14);
		panelObjet.add(lblAcc);

		JLabel lblBloc = new JLabel("Bloc mystère");
		lblBloc.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblBloc.setBounds(74, 5, 104, 14);
		panelObjet.add(lblBloc);

		JLabel lblFume = new JLabel("Fumée");
		lblFume.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblFume.setBounds(228, 569, 81, 14);
		panelObjet.add(lblFume);

		JLabel lblVirageHaut = new JLabel("Virage haut");
		lblVirageHaut.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblVirageHaut.setBounds(74, 422, 81, 14);
		panelObjet.add(lblVirageHaut);

		JLabel lblVirageGauche = new JLabel("Virage gauche");
		lblVirageGauche.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblVirageGauche.setBounds(228, 423, 101, 14);
		panelObjet.add(lblVirageGauche);

		JLabel lblVirageDroit = new JLabel("Virage droit");
		lblVirageDroit.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblVirageDroit.setBounds(74, 274, 81, 14);
		panelObjet.add(lblVirageDroit);

		JLabel lblVirageBas = new JLabel("Virage bas");
		lblVirageBas.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblVirageBas.setBounds(228, 275, 81, 14);
		panelObjet.add(lblVirageBas);

		JLabel lblPisteHorizontale = new JLabel("Piste horizontale");
		lblPisteHorizontale.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblPisteHorizontale.setBounds(64, 131, 112, 14);
		panelObjet.add(lblPisteHorizontale);

		JLabel lblPisteVerticale = new JLabel("Piste verticale");
		lblPisteVerticale.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblPisteVerticale.setBounds(217, 132, 112, 14);
		panelObjet.add(lblPisteVerticale);

		JLabel lblPisteDeDpart = new JLabel("Piste de départ");
		lblPisteDeDpart.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblPisteDeDpart.setBounds(217, 5, 112, 14);
		panelObjet.add(lblPisteDeDpart);

		JLabel lblTaille = new JLabel("15 M");
		lblTaille.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblTaille.setBounds(49, 59, 71, 14);
		panelObjet.add(lblTaille);

		JLabel lblM = new JLabel("80 M");
		lblM.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblM.setBounds(315, 59, 71, 14);
		panelObjet.add(lblM);

		JLabel lblM_1 = new JLabel("80 M");
		lblM_1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblM_1.setBounds(315, 183, 71, 14);
		panelObjet.add(lblM_1);

		JLabel lblM_2 = new JLabel("80 M");
		lblM_2.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblM_2.setBounds(39, 183, 71, 14);
		panelObjet.add(lblM_2);

		JLabel lblM_3 = new JLabel("80 M");
		lblM_3.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblM_3.setBounds(39, 334, 71, 14);
		panelObjet.add(lblM_3);

		JLabel lblM_4 = new JLabel("80 M");
		lblM_4.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblM_4.setBounds(315, 334, 71, 14);
		panelObjet.add(lblM_4);

		JLabel lblM_5 = new JLabel("80 M");
		lblM_5.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblM_5.setBounds(34, 486, 71, 14);
		panelObjet.add(lblM_5);

		JLabel lblM_6 = new JLabel("80 M");
		lblM_6.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblM_6.setBounds(39, 621, 71, 14);
		panelObjet.add(lblM_6);

		JLabel lblM_7 = new JLabel("80 M");
		lblM_7.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblM_7.setBounds(315, 621, 71, 14);
		panelObjet.add(lblM_7);

		JLabel lblM_7_1 = new JLabel("80 M");
		lblM_7_1.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblM_7_1.setBounds(315, 486, 71, 14);
		panelObjet.add(lblM_7_1);
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		btnSauvegarde = new JButton("SAUVEGARDER LA PISTE");
		btnSauvegarde.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnSauvegarde.setEnabled(false);
		btnSauvegarde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sauvegarde();
			}
		});

		btnSauvegarde.setBounds(224, 508, 250, 23);

		add(btnSauvegarde);

		comboBoxPiste = new JComboBox<String>();
		comboBoxPiste.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		comboBoxPiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pisteCourante = (String) comboBoxPiste.getSelectedItem();
				chargementUnePiste();

			}
		});

		comboBoxPiste.setBounds(743, 508, 250, 23);

		add(comboBoxPiste);

		btnJouer = new JButton("JOUER");
		btnJouer.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnJouer.setEnabled(false);
		btnJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jouer();
			}
		});

		btnJouer.setBounds(498, 501, 214, 63);
		add(btnJouer);

		JLabel lblNbM = new JLabel("80 m");
		lblNbM.setForeground(Color.WHITE);
		lblNbM.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblNbM.setBounds(245, 68, 46, 14);
		add(lblNbM);

		JLabel lblFleche = new JLabel("<------>");
		lblFleche.setForeground(Color.WHITE);
		lblFleche.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFleche.setBounds(224, 82, 89, 14);
		add(lblFleche);

		JButton btnClear = new JButton("EFFACER");
		btnClear.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				effacer();
			}
		});
		btnClear.setBounds(498, 575, 214, 63);
		add(btnClear);
		lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 1600, 800);
		add(lblImage);


	}

	/**
	 * Méthode qui permet de supprimer tous les morceaux de piste qui sont sur le
	 * quadrillage, sauf la piste de depart qui doit toujours etre presente.
	 */
	// Tan Tommy Rin
	private void effacer() {

		panelRegroupement.getListeAccelerateur().clear();
		panelRegroupement.getListePisteVirageBas().clear();
		panelRegroupement.getListePisteVirageHaut().clear();
		panelRegroupement.getListePisteVirageDroit().clear();
		panelRegroupement.getListePisteVirageGauche().clear();
		panelRegroupement.getListePisteVerticale().clear();
		panelRegroupement.getListePisteHorizontale().clear();
		panelRegroupement.getListeBlocMystere().clear();
		panelRegroupement.getListeFumee().clear();
		repaint();

	}

	/**
	 * Méthode qui permet de supprimer un accelerateur
	 */
	// Tan Tommy Rin
	public void supprimerAccelerateur() {
		if (panelRegroupement.getListeAccelerateur().size() != 0) {
			panelRegroupement.getListeAccelerateur().remove(panelRegroupement.getListeAccelerateur().size() - 1);
			repaint();
			btnAjouterAccelerateur.setEnabled(true);
		}
	}

	/**
	 * Méthode qui permet d'ajouter un bloc mystere
	 */
	// Tan Tommy Rin
	public void ajouterBlocMystere() {
		BlocMystere blocMystere = new BlocMystere(15, new Vecteur2D(650, 170));

		panelRegroupement.getListeBlocMystere().add(blocMystere);
		repaint();
	}

	/**
	 * Méthode qui permet de supprimer un bloc mystere
	 */
	// Tan Tommy Rin
	public void supprimerBlocMystere() {
		if (panelRegroupement.getListeBlocMystere().size() != 0) {
			panelRegroupement.getListeBlocMystere().remove(panelRegroupement.getListeBlocMystere().size() - 1);
			repaint();
		}
	}

	/**
	 * Méthode qui permet d'ajouter une piste de horizontale
	 */
	// Tan Tommy Rin
	public void ajouterPisteHorizontale() {
		PisteHorizontale pisteHorizontale = new PisteHorizontale(650, 190);
		panelRegroupement.getListePisteHorizontale().add(pisteHorizontale);
		repaint();
	}

	/**
	 * Méthode qui permet d'ajouter une piste de virage bas
	 */
	// Tan Tommy Rin
	public void ajouterVirageBas() {
		PisteVirageBas pisteVirageBas = new PisteVirageBas(650, 70);
		panelRegroupement.getListePisteVirageBas().add(pisteVirageBas);
		repaint();
	}

	/**
	 * Méthode qui permet d'ajouter une piste de virage gauche
	 */
	// Tan Tommy Rin
	public void ajouterPisteGauche() {
		PisteVirageGauche pisteVirageGauche = new PisteVirageGauche(650, 90);
		panelRegroupement.getListePisteVirageGauche().add(pisteVirageGauche);
		repaint();
	}

	/**
	 * Méthode qui permet de supprimer une piste horizontale
	 */
	// Tan Tommy Rin
	public void supprimerHorizontale() {
		if (panelRegroupement.getListePisteHorizontale().size() != 0) {
			panelRegroupement.getListePisteHorizontale()
					.remove(panelRegroupement.getListePisteHorizontale().size() - 1);
			repaint();
		}
	}

	/**
	 * Méthode qui permet de supprimer une piste de virage bas
	 */
	// Tan Tommy Rin
	public void supprimerVirageBas() {
		if (panelRegroupement.getListePisteVirageBas().size() != 0) {
			panelRegroupement.getListePisteVirageBas().remove(panelRegroupement.getListePisteVirageBas().size() - 1);
			repaint();
		}
	}

	/**
	 * Méthode qui permet de supprimer une piste de virage gauche
	 */
	// Tan Tommy Rin
	public void supprimerVirageGauche() {
		if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
			panelRegroupement.getListePisteVirageGauche()
					.remove(panelRegroupement.getListePisteVirageGauche().size() - 1);
			repaint();
		}
	}

	/**
	 * Méthode qui permet d'ajouter une piste de depart
	 */
	// Tan Tommy Rin
	public void ajouterPisteDepart() {
		if (panelRegroupement.getListePisteDeDepart().size() == 0) {
			PisteDeDepart pisteDeDepart = new PisteDeDepart(650, 150);
			panelRegroupement.getListePisteDeDepart().add(pisteDeDepart);
			btnAjouterPisteDeDepart.setEnabled(false);
			btnSauvegarde.setEnabled(true);
		} else {
			btnSauvegarde.setEnabled(false);
		}
		repaint();
	}

	/**
	 * Méthode qui permet d'ajouter une piste verticale
	 */
	// Tan Tommy Rin
	public void ajouterPisteVerticale() {
		PisteVerticale pisteVerticale = new PisteVerticale(650, 50);
		panelRegroupement.getListePisteVerticale().add(pisteVerticale);
		repaint();
	}

	/**
	 * Méthode qui permet de supprimer une piste verticale
	 */
	// Tan Tommy Rin
	public void supprimerPisteVerticale() {
		if (panelRegroupement.getListePisteVerticale().size() != 0) {
			panelRegroupement.getListePisteVerticale().remove(panelRegroupement.getListePisteVerticale().size() - 1);
			repaint();
		}
	}

	/**
	 * Méthode qui permet de supprimer une piste de depart
	 */
	// Tan Tommy Rin
	public void supprimerPisteDepart() {
		if (panelRegroupement.getListePisteDeDepart().size() != 0) {
			panelRegroupement.getListePisteDeDepart().remove(panelRegroupement.getListePisteDeDepart().size() - 1);
			btnAjouterPisteDeDepart.setEnabled(true);
			btnSauvegarde.setEnabled(false);
			repaint();
		}
	}

	/**
	 * Méthode qui permet d'ajouter une piste de virage droit
	 */
	// Tan Tommy Rin
	public void ajouterPisteDroit() {
		PisteVirageDroit pisteVirageDroit = new PisteVirageDroit(650, 110);
		panelRegroupement.getListePisteVirageDroit().add(pisteVirageDroit);
		repaint();
	}

	/**
	 * Méthode qui permet de supprimer une piste de virage droit
	 */
	// Tan Tommy Rin
	public void supprimerPisteDroite() {
		if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
			panelRegroupement.getListePisteVirageDroit()
					.remove(panelRegroupement.getListePisteVirageDroit().size() - 1);
			repaint();
		}
	}

	/**
	 * Méthode qui permet d'ajouter une piste de virage haut
	 */
	// Tan Tommy Rin
	public void ajouterPisteVirageHaut() {
		PisteVirageHaut pisteVirageHaut = new PisteVirageHaut(650, 210);
		panelRegroupement.getListePisteVirageHaut().add(pisteVirageHaut);
		repaint();
	}

	/**
	 * Méthode permettant de supprimer une piste de virage haut
	 */
	// Tan Tommy Rin
	public void supprimerPisteVirageHaut() {
		if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
			panelRegroupement.getListePisteVirageHaut().remove(panelRegroupement.getListePisteVirageHaut().size() - 1);
			repaint();
		}
	}

	/**
	 * Méthode qui permet d'ajouter le morceau de fumee
	 */
	// Alexis Pineda-Alvarado
	public void ajouterFumee() {
		Fumee fumee = new Fumee(650, 190);
		panelRegroupement.getListeFumee().add(fumee);
		repaint();
	}

	/**
	 * Méthode qui supprime le morceau de fumee
	 */
	// Alexis Pineda-Alvarado
	public void supprimerFumee() {
		if (panelRegroupement.getListeFumee().size() != 0) {
			panelRegroupement.getListeFumee().remove(panelRegroupement.getListeFumee().size() - 1);
			repaint();
		}
	}

	/**
	 * Méthode qui permet la sauvegarde d'une piste lié au bouton de sauvegarde.
	 */
	// Tan Tommy Rin
	public void sauvegarde() {
		sauvegardeUnePiste();
		gestionFich.setNombrePiste(comboBoxPiste.getItemCount() + 1);

		pisteCourante = (String) comboBoxPiste.getSelectedItem();
		chargementUnePiste();
		JOptionPane.showMessageDialog(null, "PISTE SAUVEGARDER SUR LE BUREAU\nNOM :" + pisteCourante);
	}

	/**
	 * Méthode qui permet de faire fonctionner le bouton jouer. Tout dependra de si
	 * la piste est fermé ou non.
	 */
	// Tan Tommy Rin
	public void jouer() {
		verifierSiPisteFerme();
		if (pisteFerme == true) {
			sauvegardeUnePiste2();

			chargementUnePiste();
			pcs.firePropertyChange("JOUEREDITEUR", null, -1);

			pcs.firePropertyChange("REGROUPEMENT", null, pisteCourante);

		} else {
			JOptionPane.showMessageDialog(null,
					"COMPLETEZ, FORMEZ BIEN LA PISTE OU ENLEVEZ LE MORCEAU VIDE POUR JOUEZ");

		}

		resetValeur();
	}

	/**
	 * Méthode qui permet d'ajouter un accelerateur
	 */
	// Tan Tommy Rin
	public void ajoutAccelerateur() {
		Accelerateur accelerateur = new Accelerateur(650, 50);
		panelRegroupement.getListeAccelerateur().add(accelerateur);
		btnAjouterAccelerateur.setEnabled(false);
		repaint();
	}

	/**
	 * Méthode qui permet de reinitialisé tous les valeurs du nombre de pistes
	 * collés pour chaque morceau de piste.
	 */
	// Tan Tommy Rin
	public void resetValeur() {
		if (panelRegroupement.getListePisteHorizontale().size() != 0) {
			for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
				panelRegroupement.getListePisteHorizontale().get(a).setNombrePisteColle(0);
			}
		}
		if (panelRegroupement.getListePisteVerticale().size() != 0) {
			for (int a = 0; a < panelRegroupement.getListePisteVerticale().size(); a++) {
				panelRegroupement.getListePisteVerticale().get(a).setNombrePisteColle(0);
			}
		}
		if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
			for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
				panelRegroupement.getListePisteVirageGauche().get(a).setNombrePisteColle(0);
			}
		}
		if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
			for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {

				panelRegroupement.getListePisteVirageDroit().get(a).setNombrePisteColle(0);
			}
		}
		if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
			for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {

				panelRegroupement.getListePisteVirageHaut().get(a).setNombrePisteColle(0);
			}
		}
		if (panelRegroupement.getListePisteVirageBas().size() != 0) {
			for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {

				panelRegroupement.getListePisteVirageBas().get(a).setNombrePisteColle(0);
			}
		}
		if (panelRegroupement.getListePisteDeDepart().size() != 0) {
			for (int a = 0; a < panelRegroupement.getListePisteDeDepart().size(); a++) {

				panelRegroupement.getListePisteDeDepart().get(a).setNombrePisteColle(0);
			}
		}

		nombrePisteFerme = 0;
	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste bas a les deux
	 * cotés fermés.
	 */
	// Tan Tommy Rin
	public void verifierNombrePisteAttacheBas() {
		if (panelRegroupement.getListePisteVirageBas().size() != 0) {

			for (int i = 0; i < panelRegroupement.getListePisteVirageBas().size(); i++) {
				PisteVirageBas piste = panelRegroupement.getListePisteVirageBas().get(i);

				Rectangle2D.Double formeAireDroite = new Rectangle2D.Double(piste.getX() + piste.getTaillePiste(),
						piste.getY(), piste.getTaillePiste(), piste.getTaillePiste());
				Rectangle2D.Double formeAireBas = new Rectangle2D.Double(piste.getX(),
						piste.getY() + piste.getTaillePiste(), piste.getTaillePiste(), piste.getTaillePiste());

				if (panelRegroupement.getListePisteDeDepart().size() != 0) {
					PisteDeDepart pisteDepart = panelRegroupement.getListePisteDeDepart().get(0);

					if (formeAireDroite.contains(pisteDepart.getX(), pisteDepart.getY())) {
						panelRegroupement.getListePisteVirageBas().get(i).setNombrePisteColle(
								panelRegroupement.getListePisteVirageBas().get(i).getNombrePisteColle() + 1);
					}

				}

				if (panelRegroupement.getListePisteHorizontale().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
						PisteHorizontale pisteHorizontale = panelRegroupement.getListePisteHorizontale().get(a);

						if (formeAireDroite.contains(pisteHorizontale.getX(), pisteHorizontale.getY())) {
							panelRegroupement.getListePisteVirageBas().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageBas().get(i).getNombrePisteColle() + 1);

						}
					}

				}

				if (panelRegroupement.getListePisteVerticale().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVerticale().size(); a++) {
						PisteVerticale pisteVerticale = panelRegroupement.getListePisteVerticale().get(a);
						if (formeAireBas.contains(pisteVerticale.getX(), pisteVerticale.getY())) {
							panelRegroupement.getListePisteVirageBas().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageBas().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {
						PisteVirageDroit pisteVirageDroit = panelRegroupement.getListePisteVirageDroit().get(a);
						if (formeAireBas.contains(pisteVirageDroit.getX(), pisteVirageDroit.getY())
								|| formeAireDroite.contains(pisteVirageDroit.getX(), pisteVirageDroit.getY())) {
							panelRegroupement.getListePisteVirageBas().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageBas().get(i).getNombrePisteColle() + 1);
						}

					}
				}

				if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
						PisteVirageGauche pisteVirageGauche = panelRegroupement.getListePisteVirageGauche().get(a);
						if (formeAireDroite.contains(pisteVirageGauche.getX(), pisteVirageGauche.getY())) {
							panelRegroupement.getListePisteVirageBas().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageBas().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {
						PisteVirageHaut pisteVirageHaut = panelRegroupement.getListePisteVirageHaut().get(a);
						if (formeAireBas.contains(pisteVirageHaut.getX(), pisteVirageHaut.getY())) {
							panelRegroupement.getListePisteVirageBas().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageBas().get(i).getNombrePisteColle() + 1);
						}

					}
				}

			}

			if (panelRegroupement.getListePisteVirageBas().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {
					if (panelRegroupement.getListePisteVirageBas().get(a).getNombrePisteColle() == 2) {
						nombrePisteFerme++;

					}

				}
			}

		}
	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste haut a les deux
	 * cotés fermés.
	 */
	// Tan Tommy Rin
	public void verifierNombrePisteAttacheHaut() {
		if (panelRegroupement.getListePisteVirageHaut().size() != 0) {

			for (int i = 0; i < panelRegroupement.getListePisteVirageHaut().size(); i++) {
				PisteVirageHaut piste = panelRegroupement.getListePisteVirageHaut().get(i);

				Rectangle2D.Double formeAireDroite = new Rectangle2D.Double(piste.getX() + piste.getTaillePiste(),
						piste.getY(), piste.getTaillePiste(), piste.getTaillePiste());
				Rectangle2D.Double formeAireHaut = new Rectangle2D.Double(piste.getX(),
						piste.getY() - piste.getTaillePiste(), piste.getTaillePiste(), piste.getTaillePiste());

				if (panelRegroupement.getListePisteDeDepart().size() != 0) {
					PisteDeDepart pisteDepart = panelRegroupement.getListePisteDeDepart().get(0);

					if (formeAireDroite.contains(pisteDepart.getX(), pisteDepart.getY())) {
						panelRegroupement.getListePisteVirageHaut().get(i).setNombrePisteColle(
								panelRegroupement.getListePisteVirageHaut().get(i).getNombrePisteColle() + 1);
					}

				}

				if (panelRegroupement.getListePisteHorizontale().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
						PisteHorizontale pisteHorizontale = panelRegroupement.getListePisteHorizontale().get(a);

						if (formeAireDroite.contains(pisteHorizontale.getX(), pisteHorizontale.getY())) {
							panelRegroupement.getListePisteVirageHaut().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageHaut().get(i).getNombrePisteColle() + 1);

						}
					}

				}

				if (panelRegroupement.getListePisteVerticale().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVerticale().size(); a++) {
						PisteVerticale pisteVerticale = panelRegroupement.getListePisteVerticale().get(a);
						if (formeAireHaut.contains(pisteVerticale.getX(), pisteVerticale.getY())) {
							panelRegroupement.getListePisteVirageHaut().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageHaut().get(i).getNombrePisteColle() + 1);

						}

					}
				}

				if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
						PisteVirageGauche pisteVirageGauche = panelRegroupement.getListePisteVirageGauche().get(a);
						if (formeAireHaut.contains(pisteVirageGauche.getX(), pisteVirageGauche.getY())
								|| formeAireDroite.contains(pisteVirageGauche.getX(), pisteVirageGauche.getY())) {
							panelRegroupement.getListePisteVirageHaut().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageHaut().get(i).getNombrePisteColle() + 1);

						}

					}
				}
				if (panelRegroupement.getListePisteVirageBas().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {
						PisteVirageBas pisteVirageBas = panelRegroupement.getListePisteVirageBas().get(a);
						if (formeAireHaut.contains(pisteVirageBas.getX(), pisteVirageBas.getY())) {
							panelRegroupement.getListePisteVirageHaut().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageHaut().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {
						PisteVirageDroit pisteVirageDroit = panelRegroupement.getListePisteVirageDroit().get(a);
						if (formeAireDroite.contains(pisteVirageDroit.getX(), pisteVirageDroit.getY())) {
							panelRegroupement.getListePisteVirageHaut().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageHaut().get(i).getNombrePisteColle() + 1);
						}

					}
				}

			}
			if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {
					if (panelRegroupement.getListePisteVirageHaut().get(a).getNombrePisteColle() == 2) {

						nombrePisteFerme++;
					}

				}
			}

		}
	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste droite a les deux
	 * cotés fermés.
	 */
	// Tan Tommy Rin
	public void verifierNombrePisteAttacheDroite() {
		if (panelRegroupement.getListePisteVirageDroit().size() != 0) {

			for (int i = 0; i < panelRegroupement.getListePisteVirageDroit().size(); i++) {
				PisteVirageDroit piste = panelRegroupement.getListePisteVirageDroit().get(i);
				Rectangle2D.Double formeAireGauche = new Rectangle2D.Double(piste.getX() - piste.getTaillePiste(),
						piste.getY(), piste.getTaillePiste(), piste.getTaillePiste());
				Rectangle2D.Double formeAireHaut = new Rectangle2D.Double(piste.getX(),
						piste.getY() - piste.getTaillePiste(), piste.getTaillePiste(), piste.getTaillePiste());

				if (panelRegroupement.getListePisteDeDepart().size() != 0) {
					PisteDeDepart pisteDepart = panelRegroupement.getListePisteDeDepart().get(0);

					if (formeAireGauche.contains(pisteDepart.getX(), pisteDepart.getY())) {
						panelRegroupement.getListePisteVirageDroit().get(i).setNombrePisteColle(
								panelRegroupement.getListePisteVirageDroit().get(i).getNombrePisteColle() + 1);
					}

				}

				if (panelRegroupement.getListePisteHorizontale().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
						PisteHorizontale pisteHorizontale = panelRegroupement.getListePisteHorizontale().get(a);

						if (formeAireGauche.contains(pisteHorizontale.getX(), pisteHorizontale.getY())) {
							panelRegroupement.getListePisteVirageDroit().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageDroit().get(i).getNombrePisteColle() + 1);

						}
					}

				}
				if (panelRegroupement.getListePisteVerticale().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVerticale().size(); a++) {
						PisteVerticale pisteVerticale = panelRegroupement.getListePisteVerticale().get(a);
						if (formeAireHaut.contains(pisteVerticale.getX(), pisteVerticale.getY())) {
							panelRegroupement.getListePisteVirageDroit().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageDroit().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
						PisteVirageGauche pisteVirageGauche = panelRegroupement.getListePisteVirageGauche().get(a);
						if (formeAireHaut.contains(pisteVirageGauche.getX(), pisteVirageGauche.getY())) {
							panelRegroupement.getListePisteVirageDroit().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageDroit().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageBas().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {
						PisteVirageBas pisteVirageBas = panelRegroupement.getListePisteVirageBas().get(a);
						if (formeAireGauche.contains(pisteVirageBas.getX(), pisteVirageBas.getY())
								|| formeAireHaut.contains(pisteVirageBas.getX(), pisteVirageBas.getY())) {
							panelRegroupement.getListePisteVirageDroit().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageDroit().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {
						PisteVirageHaut pisteVirageHaut = panelRegroupement.getListePisteVirageHaut().get(a);
						if (formeAireGauche.contains(pisteVirageHaut.getX(), pisteVirageHaut.getY())) {
							panelRegroupement.getListePisteVirageDroit().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageDroit().get(i).getNombrePisteColle() + 1);
						}

					}
				}

			}
			if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {
					if (panelRegroupement.getListePisteVirageDroit().get(a).getNombrePisteColle() == 2) {

						nombrePisteFerme++;
					}

				}
			}

		}
	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste verticale a les
	 * deux cotés fermés.
	 */
	// Tan Tommy Rin
	public void verifierNombrePisteAttacheVerticale() {
		if (panelRegroupement.getListePisteVerticale().size() != 0) {

			for (int i = 0; i < panelRegroupement.getListePisteVerticale().size(); i++) {
				PisteVerticale piste = panelRegroupement.getListePisteVerticale().get(i);
				Rectangle2D.Double formeAireBas = new Rectangle2D.Double(piste.getX(),
						piste.getY() + piste.getTaillePiste(), piste.getTaillePiste(), piste.getTaillePiste());
				Rectangle2D.Double formeAireHaut = new Rectangle2D.Double(piste.getX(),
						piste.getY() - piste.getTaillePiste(), piste.getTaillePiste(), piste.getTaillePiste());

				if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
						PisteVirageGauche pisteVirageGauche = panelRegroupement.getListePisteVirageGauche().get(a);
						if (formeAireHaut.contains(pisteVirageGauche.getX(), pisteVirageGauche.getY())) {
							panelRegroupement.getListePisteVerticale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVerticale().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {
						PisteVirageDroit pisteVirageDroit = panelRegroupement.getListePisteVirageDroit().get(a);
						if (formeAireBas.contains(pisteVirageDroit.getX(), pisteVirageDroit.getY())) {
							panelRegroupement.getListePisteVerticale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVerticale().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {
						PisteVirageHaut pisteVirageHaut = panelRegroupement.getListePisteVirageHaut().get(a);
						if (formeAireBas.contains(pisteVirageHaut.getX(), pisteVirageHaut.getY())) {
							panelRegroupement.getListePisteVerticale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVerticale().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageBas().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {
						PisteVirageBas pisteVirageBas = panelRegroupement.getListePisteVirageBas().get(a);
						if (formeAireHaut.contains(pisteVirageBas.getX(), pisteVirageBas.getY())) {
							panelRegroupement.getListePisteVerticale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVerticale().get(i).getNombrePisteColle() + 1);
						}

					}
				}

				if (panelRegroupement.getListePisteVerticale().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVerticale().size(); a++) {
						PisteVerticale pisteVerticale = panelRegroupement.getListePisteVerticale().get(a);

						if (formeAireHaut.contains(pisteVerticale.getX(), pisteVerticale.getY())
								|| formeAireBas.contains(pisteVerticale.getX(), pisteVerticale.getY())) {
							panelRegroupement.getListePisteVerticale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVerticale().get(i).getNombrePisteColle() + 1);
						}

					}

				}

			}

			if (panelRegroupement.getListePisteVerticale().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVerticale().size(); a++) {
					if (panelRegroupement.getListePisteVerticale().get(a).getNombrePisteColle() == 2) {

						nombrePisteFerme++;
					}

				}
			}

		}

	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste virage gauche a les
	 * deux cotés fermés.
	 */
	// Tan Tommy Rin
	public void verifierNombrePisteAttacheGauche() {
		if (panelRegroupement.getListePisteVirageGauche().size() != 0) {

			for (int i = 0; i < panelRegroupement.getListePisteVirageGauche().size(); i++) {
				PisteVirageGauche piste = panelRegroupement.getListePisteVirageGauche().get(i);
				Rectangle2D.Double formeAireGauche = new Rectangle2D.Double(piste.getX() - piste.getTaillePiste(),
						piste.getY(), piste.getTaillePiste(), piste.getTaillePiste());
				Rectangle2D.Double formeAireBas = new Rectangle2D.Double(piste.getX(),
						piste.getY() + piste.getTaillePiste(), piste.getTaillePiste(), piste.getTaillePiste());

				if (panelRegroupement.getListePisteDeDepart().size() != 0) {
					PisteDeDepart pisteDepart = panelRegroupement.getListePisteDeDepart().get(0);

					if (formeAireGauche.contains(pisteDepart.getX(), pisteDepart.getY())) {
						panelRegroupement.getListePisteVirageGauche().get(i).setNombrePisteColle(
								panelRegroupement.getListePisteVirageGauche().get(i).getNombrePisteColle() + 1);
					}

				}
				if (panelRegroupement.getListePisteHorizontale().size() != 0) {

					for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
						PisteHorizontale pisteHorizontale = panelRegroupement.getListePisteHorizontale().get(a);
						if (formeAireGauche.contains(pisteHorizontale.getX(), pisteHorizontale.getY())) {
							panelRegroupement.getListePisteVirageGauche().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageGauche().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVerticale().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVerticale().size(); a++) {
						PisteVerticale pisteVerticale = panelRegroupement.getListePisteVerticale().get(a);

						if (formeAireBas.contains(pisteVerticale.getX(), pisteVerticale.getY())) {
							panelRegroupement.getListePisteVirageGauche().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageGauche().get(i).getNombrePisteColle() + 1);
						}
					}

				}
				if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {
						PisteVirageDroit pisteVirageDroit = panelRegroupement.getListePisteVirageDroit().get(a);
						if (formeAireBas.contains(pisteVirageDroit.getX(), pisteVirageDroit.getY())) {
							panelRegroupement.getListePisteVirageGauche().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageGauche().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {
						PisteVirageHaut pisteVirageHaut = panelRegroupement.getListePisteVirageHaut().get(a);
						if (formeAireBas.contains(pisteVirageHaut.getX(), pisteVirageHaut.getY())
								|| formeAireGauche.contains(pisteVirageHaut.getX(), pisteVirageHaut.getY())) {
							panelRegroupement.getListePisteVirageGauche().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageGauche().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageBas().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {
						PisteVirageBas pisteVirageBas = panelRegroupement.getListePisteVirageBas().get(a);
						if (formeAireGauche.contains(pisteVirageBas.getX(), pisteVirageBas.getY())) {
							panelRegroupement.getListePisteVirageGauche().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteVirageGauche().get(i).getNombrePisteColle() + 1);
						}

					}
				}

			}
			if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
					if (panelRegroupement.getListePisteVirageGauche().get(a).getNombrePisteColle() == 2) {

						nombrePisteFerme++;
					}

				}
			}

		}
	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste horizontale a les
	 * deux cotés fermés.
	 */
	// Tan Tommy Rin
	public void verifierNombrePisteAttacheHorizontale() {
		if (panelRegroupement.getListePisteHorizontale().size() != 0) {

			for (int i = 0; i < panelRegroupement.getListePisteHorizontale().size(); i++) {

				PisteHorizontale piste = panelRegroupement.getListePisteHorizontale().get(i);
				Rectangle2D.Double formeAireGauche = new Rectangle2D.Double(piste.getX() - piste.getTaillePiste(),
						piste.getY(), piste.getTaillePiste(), piste.getTaillePiste());
				Rectangle2D.Double formeAireDroit = new Rectangle2D.Double(piste.getX() + piste.getTaillePiste(),
						piste.getY(), piste.getTaillePiste(), piste.getTaillePiste());
				if (panelRegroupement.getListePisteDeDepart().size() != 0) {
					PisteDeDepart pisteDepart = panelRegroupement.getListePisteDeDepart().get(0);

					if (formeAireGauche.contains(pisteDepart.getX(), pisteDepart.getY())
							|| formeAireDroit.contains(pisteDepart.getX(), pisteDepart.getY())) {
						panelRegroupement.getListePisteHorizontale().get(i).setNombrePisteColle(
								panelRegroupement.getListePisteHorizontale().get(i).getNombrePisteColle() + 1);
					}

				}
				if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
						PisteVirageGauche pisteVirageGauche = panelRegroupement.getListePisteVirageGauche().get(a);
						if (formeAireDroit.contains(pisteVirageGauche.getX(), pisteVirageGauche.getY())) {
							panelRegroupement.getListePisteHorizontale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteHorizontale().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {
						PisteVirageDroit pisteVirageDroit = panelRegroupement.getListePisteVirageDroit().get(a);
						if (formeAireDroit.contains(pisteVirageDroit.getX(), pisteVirageDroit.getY())) {
							panelRegroupement.getListePisteHorizontale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteHorizontale().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {
						PisteVirageHaut pisteVirageHaut = panelRegroupement.getListePisteVirageHaut().get(a);
						if (formeAireGauche.contains(pisteVirageHaut.getX(), pisteVirageHaut.getY())) {
							panelRegroupement.getListePisteHorizontale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteHorizontale().get(i).getNombrePisteColle() + 1);
						}

					}
				}
				if (panelRegroupement.getListePisteVirageBas().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {
						PisteVirageBas pisteVirageBas = panelRegroupement.getListePisteVirageBas().get(a);
						if (formeAireGauche.contains(pisteVirageBas.getX(), pisteVirageBas.getY())) {
							panelRegroupement.getListePisteHorizontale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteHorizontale().get(i).getNombrePisteColle() + 1);
						}

					}
				}

				if (panelRegroupement.getListePisteHorizontale().size() != 0) {
					for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
						PisteHorizontale pisteHorizontale = panelRegroupement.getListePisteHorizontale().get(a);

						if (formeAireGauche.contains(pisteHorizontale.getX(), pisteHorizontale.getY())
								|| formeAireDroit.contains(pisteHorizontale.getX(), pisteHorizontale.getY())) {
							panelRegroupement.getListePisteHorizontale().get(i).setNombrePisteColle(
									panelRegroupement.getListePisteHorizontale().get(i).getNombrePisteColle() + 1);

						}
					}

				}

			}
			if (panelRegroupement.getListePisteHorizontale().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
					if (panelRegroupement.getListePisteHorizontale().get(a).getNombrePisteColle() == 2) {

						nombrePisteFerme++;
					}

				}
			}

		}

	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste de depart a les
	 * deux cotés fermés.
	 */
	// Tan Tommy Rin
	public void verifierNombrePisteAttacheDepart() {
		if (panelRegroupement.getListePisteDeDepart().size() != 0) {

			PisteDeDepart piste = panelRegroupement.getListePisteDeDepart().get(0);
			Rectangle2D.Double formeAireGauche = new Rectangle2D.Double(piste.getX() - piste.getTaillePiste(),
					piste.getY(), piste.getTaillePiste(), piste.getTaillePiste());
			Rectangle2D.Double formeAireDroit = new Rectangle2D.Double(piste.getX() + piste.getTaillePiste(),
					piste.getY(), piste.getTaillePiste(), piste.getTaillePiste());

			if (panelRegroupement.getListePisteHorizontale().size() != 0) {

				for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
					PisteHorizontale pisteHorizontale = panelRegroupement.getListePisteHorizontale().get(a);
					if (formeAireGauche.contains(pisteHorizontale.getX(), pisteHorizontale.getY())
							|| formeAireDroit.contains(pisteHorizontale.getX(), pisteHorizontale.getY())) {
						panelRegroupement.getListePisteDeDepart().get(0).setNombrePisteColle(
								panelRegroupement.getListePisteDeDepart().get(0).getNombrePisteColle() + 1);
					}

				}
			}
			if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
					PisteVirageGauche pisteVirageGauche = panelRegroupement.getListePisteVirageGauche().get(a);
					if (formeAireDroit.contains(pisteVirageGauche.getX(), pisteVirageGauche.getY())) {
						panelRegroupement.getListePisteDeDepart().get(0).setNombrePisteColle(
								panelRegroupement.getListePisteDeDepart().get(0).getNombrePisteColle() + 1);
					}

				}
			}
			if (panelRegroupement.getListePisteVirageDroit().size() != 0) {

				for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {
					PisteVirageDroit pisteVirageDroit = panelRegroupement.getListePisteVirageDroit().get(a);
					if (formeAireDroit.contains(pisteVirageDroit.getX(), pisteVirageDroit.getY())) {
						panelRegroupement.getListePisteDeDepart().get(0).setNombrePisteColle(
								panelRegroupement.getListePisteDeDepart().get(0).getNombrePisteColle() + 1);
					}
				}
			}
			if (panelRegroupement.getListePisteVirageHaut().size() != 0) {

				for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {
					PisteVirageHaut pisteVirageHaut = panelRegroupement.getListePisteVirageHaut().get(a);
					if (formeAireGauche.contains(pisteVirageHaut.getX(), pisteVirageHaut.getY())) {
						panelRegroupement.getListePisteDeDepart().get(0).setNombrePisteColle(
								panelRegroupement.getListePisteDeDepart().get(0).getNombrePisteColle() + 1);
					}
				}
			}
			if (panelRegroupement.getListePisteVirageBas().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {
					PisteVirageBas pisteVirageBas = panelRegroupement.getListePisteVirageBas().get(a);
					if (formeAireGauche.contains(pisteVirageBas.getX(), pisteVirageBas.getY())) {
						panelRegroupement.getListePisteDeDepart().get(0).setNombrePisteColle(
								panelRegroupement.getListePisteDeDepart().get(0).getNombrePisteColle() + 1);
					}

				}
			}
			if (panelRegroupement.getListePisteDeDepart().size() != 0) {

				if (panelRegroupement.getListePisteDeDepart().get(0).getNombrePisteColle() == 2) {

					nombrePisteFerme++;

				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "AJOUTEZ UNE PISTE DE DÉPART");
		}

	}

	/**
	 * Méthode qui permet de vérifier si la piste est fermée.
	 */
	// Tan Tommy Rin
	public void verifierSiPisteFerme() {
		verifierNombrePisteAttacheDepart();
		verifierNombrePisteAttacheHorizontale();
		verifierNombrePisteAttacheVerticale();
		verifierNombrePisteAttacheGauche();
		verifierNombrePisteAttacheDroite();
		verifierNombrePisteAttacheHaut();
		verifierNombrePisteAttacheBas();

		if (nombrePisteFerme == (panelRegroupement.getListePisteDeDepart().size()
				+ panelRegroupement.getListePisteHorizontale().size()
				+ panelRegroupement.getListePisteVerticale().size() + panelRegroupement.getListePisteVirageBas().size()
				+ panelRegroupement.getListePisteVirageDroit().size()
				+ panelRegroupement.getListePisteVirageGauche().size()
				+ panelRegroupement.getListePisteVirageHaut().size())) {

			pisteFerme = true;

		} else {
			panelRegroupement.setJouer(true);
			pisteFerme = false;

		}
		repaint();
	}

	/**
	 * Méhode qui permet de sauvegarder une piste sur le bureau en fichier binaire
	 * mais pour le bouton jouer
	 */
	// Tan Tommy Rin
	private void sauvegardeUnePiste2() {

		Voiture voiture = new Voiture(new Vecteur2D(panelRegroupement.getListePisteDeDepart().get(0).getX(),
				panelRegroupement.getListePisteDeDepart().get(0).getY()), Color.yellow, 50, 16, 0, 60);
		regroupement = new Regroupement(voiture, 3, TypePiste.AUTRE);
		regroupement.setListeAccelerateur(panelRegroupement.getListeAccelerateur());
		regroupement.setListePisteDeDepart(panelRegroupement.getListePisteDeDepart());
		regroupement.setListePisteHorizontale(panelRegroupement.getListePisteHorizontale());
		regroupement.setListePisteVerticale(panelRegroupement.getListePisteVerticale());
		regroupement.setListePisteVirageBas(panelRegroupement.getListePisteVirageBas());
		regroupement.setListePisteVirageDroit(panelRegroupement.getListePisteVirageDroit());
		regroupement.setListePisteVirageGauche(panelRegroupement.getListePisteVirageGauche());
		regroupement.setListePisteVirageHaut(panelRegroupement.getListePisteVirageHaut());
		regroupement.setRegroupementObjet(panelRegroupement.getListeBlocMystere());
		regroupement.setListeFumee(panelRegroupement.getListeFumee());

		gestionFich.ecrireFichierBinBureauRegroupement2(regroupement, pisteCourante);
		boolean dejaDansComboBox = false;
		for (int a = 0; a < comboBoxPiste.getItemCount(); a++) {
			if (gestionFich.getNomFichBinRegroupement().equalsIgnoreCase(comboBoxPiste.getItemAt(a))) {

				dejaDansComboBox = true;
				break;
			} else {
				dejaDansComboBox = false;
			}
		}
		if (dejaDansComboBox == true) {

		} else {
			comboBoxPiste.addItem(gestionFich.getNomFichBinRegroupement());
		}

		comboBoxPiste.setSelectedItem(pisteCourante);

		btnJouer.setEnabled(true);

	}

	/**
	 * Méhode qui permet de sauvegarder une piste sur le bureau en fichier binaire
	 */
	// Tan Tommy Rin
	private void sauvegardeUnePiste() {

		Voiture voiture = new Voiture(new Vecteur2D(panelRegroupement.getListePisteDeDepart().get(0).getX(),
				panelRegroupement.getListePisteDeDepart().get(0).getY()), Color.yellow, 50, 16, 0, 60);
		regroupement = new Regroupement(voiture, 3, TypePiste.AUTRE);
		regroupement.setListeAccelerateur(panelRegroupement.getListeAccelerateur());
		regroupement.setListePisteDeDepart(panelRegroupement.getListePisteDeDepart());
		regroupement.setListePisteHorizontale(panelRegroupement.getListePisteHorizontale());
		regroupement.setListePisteVerticale(panelRegroupement.getListePisteVerticale());
		regroupement.setListePisteVirageBas(panelRegroupement.getListePisteVirageBas());
		regroupement.setListePisteVirageDroit(panelRegroupement.getListePisteVirageDroit());
		regroupement.setListePisteVirageGauche(panelRegroupement.getListePisteVirageGauche());
		regroupement.setListePisteVirageHaut(panelRegroupement.getListePisteVirageHaut());
		regroupement.setRegroupementObjet(panelRegroupement.getListeBlocMystere());
		regroupement.setListeFumee(panelRegroupement.getListeFumee());

		gestionFich.ecrireFichierBinBureauRegroupement(regroupement);
		boolean dejaDansComboBox = false;
		for (int a = 0; a < comboBoxPiste.getItemCount(); a++) {
			if (gestionFich.getNomFichBinRegroupement().equalsIgnoreCase(comboBoxPiste.getItemAt(a))) {

				dejaDansComboBox = true;
				break;
			} else {
				dejaDansComboBox = false;
			}
		}
		if (dejaDansComboBox == true) {

		} else {
			comboBoxPiste.addItem(gestionFich.getNomFichBinRegroupement());
		}

		comboBoxPiste.setSelectedIndex(comboBoxPiste.getItemCount() - 1);

		btnJouer.setEnabled(true);

	}

	/**
	 * Méthode qui permet de charger une piste qui est sur le bureau
	 */
	// Tan Tommy Rin
	private void chargementUnePiste() {

		btnSauvegarde.setEnabled(true);
		btnJouer.setEnabled(true);

		regroupementSauvegarde = gestionFich.lireFichierBinBureauRegroupement(pisteCourante);

		panelRegroupement.getListeAccelerateur().clear();
		panelRegroupement.getListePisteVirageBas().clear();
		panelRegroupement.getListePisteVirageHaut().clear();
		panelRegroupement.getListePisteVirageDroit().clear();
		panelRegroupement.getListePisteVirageGauche().clear();
		panelRegroupement.getListePisteVerticale().clear();
		panelRegroupement.getListePisteHorizontale().clear();
		panelRegroupement.getListePisteDeDepart().clear();
		panelRegroupement.getListeBlocMystere().clear();
		panelRegroupement.getListeFumee().clear();

// Pour les accelerateurs

		for (int a = 0; a < regroupementSauvegarde.getListeAccelerateur().size(); a++) {
			panelRegroupement.getListeAccelerateur().add(regroupementSauvegarde.getListeAccelerateur().get(a));
		}

// Pour piste virage bas

		for (int a = 0; a < regroupementSauvegarde.getListePisteVirageBas().size(); a++) {
			panelRegroupement.getListePisteVirageBas().add(regroupementSauvegarde.getListePisteVirageBas().get(a));
		}

// Pour piste virage haut

		for (int a = 0; a < regroupementSauvegarde.getListePisteVirageHaut().size(); a++) {
			panelRegroupement.getListePisteVirageHaut().add(regroupementSauvegarde.getListePisteVirageHaut().get(a));

		}

// Pour piste virage droite

		for (int a = 0; a < regroupementSauvegarde.getListePisteVirageDroit().size(); a++) {
			panelRegroupement.getListePisteVirageDroit().add(regroupementSauvegarde.getListePisteVirageDroit().get(a));
		}

// Pour piste virage gauche

		for (int a = 0; a < regroupementSauvegarde.getListePisteVirageGauche().size(); a++) {
			panelRegroupement.getListePisteVirageGauche()
					.add(regroupementSauvegarde.getListePisteVirageGauche().get(a));

		}

// Pour piste verticale

		for (int a = 0; a < regroupementSauvegarde.getListePisteVerticale().size(); a++) {
			panelRegroupement.getListePisteVerticale().add(regroupementSauvegarde.getListePisteVerticale().get(a));

		}

// Pour piste horizontale

		for (int a = 0; a < regroupementSauvegarde.getListePisteHorizontale().size(); a++) {
			panelRegroupement.getListePisteHorizontale().add(regroupementSauvegarde.getListePisteHorizontale().get(a));

		}
// Pour piste de depart

		for (int a = 0; a < regroupementSauvegarde.getListePisteDeDepart().size(); a++) {
			panelRegroupement.getListePisteDeDepart().add(regroupementSauvegarde.getListePisteDeDepart().get(a));

		}
// Pour bloc mystere

		for (int a = 0; a < regroupementSauvegarde.getRegroupementBoiteMystere().size(); a++) {
			panelRegroupement.getListeBlocMystere().add(regroupementSauvegarde.getRegroupementBoiteMystere().get(a));
		}
// Pour la fumee		


		for (int a = 0; a < regroupementSauvegarde.getListeFumee().size(); a++) {
			panelRegroupement.getListeFumee().add(regroupementSauvegarde.getListeFumee().get(a));
		}


		resetValeur();
		repaint();

	}

	public JComboBox getComboBoxPiste() {
		return comboBoxPiste;
	}

	public void setComboBoxPiste(JComboBox comboBoxPiste) {
		this.comboBoxPiste = comboBoxPiste;
	}

	public GestionnaireDeFichiersSurLeBureau getGestionFich() {
		return gestionFich;
	}

	public void setGestionFich(GestionnaireDeFichiersSurLeBureau gestionFich) {
		this.gestionFich = gestionFich;
	}

	public PanelRegroupement getPanelRegroupement() {
		return panelRegroupement;
	}

	public void setPanelRegroupement(PanelRegroupement panelRegroupement) {
		this.panelRegroupement = panelRegroupement;
	}
}
