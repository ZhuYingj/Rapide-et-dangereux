package fenetre;

import java.awt.BasicStroke;
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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import application.GestionnaireDeFichiersSurLeBureau;
import geometrie.Vecteur2D;
import interfaces.TypeObjetDeplacable;
import interfaces.TypePiste;
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
import utilitaireObjets.Voiture;

/**
 * Classe qui permet de créer et gérer la fenetre éditeur. La sauvegarde d'un
 * circuit(Classe regroupement) est possible.
 * 
 * @author Tan Tommy Rin
 *
 */

public class FenetreEditeur extends JPanel {

	private PanelRegroupement panelRegroupement;

	private GestionnaireDeFichiersSurLeBureau gestionFich;
	private JButton btnSauvegarde;
	private JButton btnJouer;
	private JButton btnRetour;
	private JButton btnAjouterPisteDeDepart;
	private JComboBox<String> comboBoxPiste;
	private Regroupement regroupementSauvegarde;
	private PanelObjet panelObjet;

	private Regroupement regroupement;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */

	public FenetreEditeur() {
		gestionFich = new GestionnaireDeFichiersSurLeBureau();
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

		panelObjet = new PanelObjet();
		panelObjet.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelObjet.setBounds(920, 11, 386, 720);
		add(panelObjet);
		panelObjet.setLayout(null);
		panelObjet.setEnabled(false);

		JButton btnAjouterAccelerateur = new JButton("+");
		btnAjouterAccelerateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accelerateur accelerateur = new Accelerateur(650, 50);
				panelRegroupement.getListeAccelerateur().add(accelerateur);

				repaint();
			}
		});
		btnAjouterAccelerateur.setBounds(64, 652, 41, 23);
		panelObjet.add(btnAjouterAccelerateur);

		JButton btnSupprimerAccelerateur = new JButton("-");
		btnSupprimerAccelerateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelRegroupement.getListeAccelerateur().size() != 0) {
					panelRegroupement.getListeAccelerateur()
							.remove(panelRegroupement.getListeAccelerateur().size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerAccelerateur.setBounds(126, 652, 41, 23);
		panelObjet.add(btnSupprimerAccelerateur);

		JButton btnAjouterBlocMystere = new JButton("+");
		btnAjouterBlocMystere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BlocMystere blocMystere = new BlocMystere(15, new Vecteur2D(650, 170));

				panelRegroupement.getListeBlocMystere().add(blocMystere);
				repaint();

			}
		});
		btnAjouterBlocMystere.setBounds(64, 107, 41, 23);
		panelObjet.add(btnAjouterBlocMystere);

		JButton btnSupprimerBlocMystere = new JButton("-");
		btnSupprimerBlocMystere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelRegroupement.getListeBlocMystere().size() != 0) {
					panelRegroupement.getListeBlocMystere().remove(panelRegroupement.getListeBlocMystere().size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerBlocMystere.setBounds(114, 107, 41, 23);
		panelObjet.add(btnSupprimerBlocMystere);

		JButton btnAjouterPisteHorizontale = new JButton("+");
		btnAjouterPisteHorizontale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteHorizontale pisteHorizontale = new PisteHorizontale(650, 190);
				panelRegroupement.getListePisteHorizontale().add(pisteHorizontale);
				repaint();
			}
		});
		btnAjouterPisteHorizontale.setBounds(64, 231, 41, 23);
		panelObjet.add(btnAjouterPisteHorizontale);

		JButton btnAjouterPisteVirageBas = new JButton("+");
		btnAjouterPisteVirageBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVirageBas pisteVirageBas = new PisteVirageBas(650, 70);
				panelRegroupement.getListePisteVirageBas().add(pisteVirageBas);
				repaint();
			}
		});

		btnAjouterPisteVirageBas.setBounds(64, 378, 41, 23);
		panelObjet.add(btnAjouterPisteVirageBas);

		JButton btnAjouterPisteVirageGauche = new JButton("+");
		btnAjouterPisteVirageGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVirageGauche pisteVirageGauche = new PisteVirageGauche(650, 90);
				panelRegroupement.getListePisteVirageGauche().add(pisteVirageGauche);
				repaint();
			}
		});
		btnAjouterPisteVirageGauche.setBounds(217, 378, 41, 23);
		panelObjet.add(btnAjouterPisteVirageGauche);

		JButton btnSupprimerPisteHorizontale = new JButton("-");
		btnSupprimerPisteHorizontale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelRegroupement.getListePisteHorizontale().size() != 0) {
					panelRegroupement.getListePisteHorizontale()
							.remove(panelRegroupement.getListePisteHorizontale().size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteHorizontale.setBounds(114, 231, 41, 23);
		panelObjet.add(btnSupprimerPisteHorizontale);

		JButton btnSupprimerPisteVirageBas = new JButton("-");
		btnSupprimerPisteVirageBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelRegroupement.getListePisteVirageBas().size() != 0) {
					panelRegroupement.getListePisteVirageBas()
							.remove(panelRegroupement.getListePisteVirageBas().size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVirageBas.setBounds(114, 378, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageBas);

		JButton btnSupprimerPisteVirageGauche = new JButton("-");
		btnSupprimerPisteVirageGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
					panelRegroupement.getListePisteVirageGauche()
							.remove(panelRegroupement.getListePisteVirageGauche().size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVirageGauche.setBounds(268, 378, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageGauche);

		btnAjouterPisteDeDepart = new JButton("+");
		btnAjouterPisteDeDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnAjouterPisteDeDepart.setBounds(217, 107, 41, 23);
		panelObjet.add(btnAjouterPisteDeDepart);

		JButton btnAjouterPisteVerticale = new JButton("+");
		btnAjouterPisteVerticale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVerticale pisteVerticale = new PisteVerticale(650, 50);
				panelRegroupement.getListePisteVerticale().add(pisteVerticale);
				repaint();
			}
		});
		btnAjouterPisteVerticale.setBounds(217, 231, 41, 23);
		panelObjet.add(btnAjouterPisteVerticale);

		JButton btnSupprimerPisteVerticale = new JButton("-");
		btnSupprimerPisteVerticale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelRegroupement.getListePisteVerticale().size() != 0) {
					panelRegroupement.getListePisteVerticale()
							.remove(panelRegroupement.getListePisteVerticale().size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVerticale.setBounds(267, 231, 41, 23);
		panelObjet.add(btnSupprimerPisteVerticale);

		JButton btnSupprimerPisteDeDepart = new JButton("-");
		btnSupprimerPisteDeDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelRegroupement.getListePisteDeDepart().size() != 0) {
					panelRegroupement.getListePisteDeDepart()
							.remove(panelRegroupement.getListePisteDeDepart().size() - 1);
					btnAjouterPisteDeDepart.setEnabled(true);
					btnSauvegarde.setEnabled(false);
					repaint();
				}
			}
		});
		btnSupprimerPisteDeDepart.setBounds(267, 107, 41, 23);
		panelObjet.add(btnSupprimerPisteDeDepart);

		JButton btnAjouterPisteVirageDroite = new JButton("+");
		btnAjouterPisteVirageDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVirageDroit pisteVirageDroit = new PisteVirageDroit(650, 110);
				panelRegroupement.getListePisteVirageDroit().add(pisteVirageDroit);
				repaint();
			}
		});
		btnAjouterPisteVirageDroite.setBounds(217, 517, 41, 23);
		panelObjet.add(btnAjouterPisteVirageDroite);

		JButton btnSupprimerPisteVirageDroite = new JButton("-");
		btnSupprimerPisteVirageDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
					panelRegroupement.getListePisteVirageDroit()
							.remove(panelRegroupement.getListePisteVirageDroit().size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVirageDroite.setBounds(268, 517, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageDroite);

		JButton btnAjouterPisteVirageHaut = new JButton("+");
		btnAjouterPisteVirageHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PisteVirageHaut pisteVirageHaut = new PisteVirageHaut(650, 210);
				panelRegroupement.getListePisteVirageHaut().add(pisteVirageHaut);
				repaint();
			}
		});
		btnAjouterPisteVirageHaut.setBounds(64, 517, 41, 23);
		panelObjet.add(btnAjouterPisteVirageHaut);

		JButton btnSupprimerPisteVirageHaut = new JButton("-");
		btnSupprimerPisteVirageHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
					panelRegroupement.getListePisteVirageHaut()
							.remove(panelRegroupement.getListePisteVirageHaut().size() - 1);
					repaint();
				}
			}
		});
		btnSupprimerPisteVirageHaut.setBounds(114, 517, 41, 23);
		panelObjet.add(btnSupprimerPisteVirageHaut);

		btnSauvegarde = new JButton("SAUVEGARDER LA PISTE");
		btnSauvegarde.setEnabled(false);
		btnSauvegarde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sauvegardeUnePiste();
			}
		});

		btnSauvegarde.setBounds(132, 508, 173, 23);

		add(btnSauvegarde);

		JButton btnChargerPisteSauvegarde = new JButton("CHARGER LA PISTE SAUVEGARDÉ");
		btnChargerPisteSauvegarde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chargementUnePiste();

			}
		});

		btnChargerPisteSauvegarde.setBounds(335, 508, 214, 23);

		add(btnChargerPisteSauvegarde);

		comboBoxPiste = new JComboBox();

		comboBoxPiste.setBounds(592, 508, 181, 23);

		add(comboBoxPiste);

		btnJouer = new JButton("JOUER");
		btnJouer.setEnabled(false);
		btnJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("JOUEREDITEUR", null, -1);
				pcs.firePropertyChange("REGROUPEMENT", null, (String) comboBoxPiste.getSelectedItem());
			}
		});

		btnJouer.setBounds(335, 553, 214, 63);
		add(btnJouer);

		panelRegroupement = new PanelRegroupement();
		panelRegroupement.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRegroupement.setBounds(132, 96, 769, 400);
		add(panelRegroupement);

	}

	/**
	 * Méhode qui permet de sauvegarder une piste sur le bureau en fichier binaire
	 */
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

		gestionFich.ecrireFichierBinBureau(regroupement);

		comboBoxPiste.addItem(gestionFich.getNomFichBinEtud());
		btnJouer.setEnabled(true);
		JOptionPane.showMessageDialog(null, "PISTE SAUVEGARDER SUR LE BUREAU");

	}

	/**
	 * Méthode qui permet de charger une piste qui est sur le bureau
	 */

	private void chargementUnePiste() {
		regroupementSauvegarde = gestionFich.lireFichierBinBureau((String) comboBoxPiste.getSelectedItem());

		panelRegroupement.getListeAccelerateur().clear();
		panelRegroupement.getListePisteVirageBas().clear();
		panelRegroupement.getListePisteVirageHaut().clear();
		panelRegroupement.getListePisteVirageDroit().clear();
		panelRegroupement.getListePisteVirageGauche().clear();
		panelRegroupement.getListePisteVerticale().clear();
		panelRegroupement.getListePisteHorizontale().clear();
		panelRegroupement.getListePisteDeDepart().clear();
		panelRegroupement.getListeBlocMystere().clear();

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

		repaint();

		JOptionPane.showMessageDialog(null, "PISTE CHARGÉ AVEC SUCCÈS !");
	}

}
