package fenetre;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import application.GestionnaireDeFichiersSurLeBureau;
import geometrie.Vecteur2D;
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

	private int nombrePisteFerme = 0;

	private int compteurPisteCoteDePisteDepart = 0;
	private boolean pisteFerme = false;
	private Regroupement regroupement;
	private PanelRegroupement panelRegroupement;
	private GestionnaireDeFichiersSurLeBureau gestionFich;

	private JButton btnSauvegarde;
	private JButton btnJouer;
	private JButton btnRetour;
	private JButton btnAjouterPisteDeDepart;
	private JComboBox<String> comboBoxPiste;

	private Regroupement regroupementSauvegarde;
	private PanelObjet panelObjet;

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

		comboBoxPiste = new JComboBox<String>();

		comboBoxPiste.setBounds(592, 508, 181, 23);

		add(comboBoxPiste);

		btnJouer = new JButton("JOUER");
		btnJouer.setEnabled(false);
		btnJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifierSiPisteFerme();
				if (pisteFerme == true) {
					pcs.firePropertyChange("JOUEREDITEUR", null, -1);
					pcs.firePropertyChange("REGROUPEMENT", null, (String) comboBoxPiste.getSelectedItem());
				} else {
					JOptionPane.showMessageDialog(null,
							"COMPLETEZ, FORMEZ BIEN LA PISTE OU ENLEVEZ LE MORCEAU VIDE POUR JOUEZ");
				}

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
	 * Méthode qui permet de vérifier si chaque morceau de piste bas a les deux
	 * cotés fermés.
	 */
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

			if (panelRegroupement.getListePisteVirageBas().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {

					panelRegroupement.getListePisteVirageBas().get(a).setNombrePisteColle(0);
				}
			}
		}
	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste haut a les deux
	 * cotés fermés.
	 */
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

			if (panelRegroupement.getListePisteVirageHaut().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {

					panelRegroupement.getListePisteVirageHaut().get(a).setNombrePisteColle(0);
				}
			}
		}
	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste droite a les deux
	 * cotés fermés.
	 */
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

			if (panelRegroupement.getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {

					panelRegroupement.getListePisteVirageDroit().get(a).setNombrePisteColle(0);
				}
			}
		}
	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste verticale a les
	 * deux cotés fermés.
	 */
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

			if (panelRegroupement.getListePisteVerticale().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVerticale().size(); a++) {
					panelRegroupement.getListePisteVerticale().get(a).setNombrePisteColle(0);
				}
			}
		}

	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste virage gauche a les
	 * deux cotés fermés.
	 */
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

			if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
					panelRegroupement.getListePisteVirageGauche().get(a).setNombrePisteColle(0);
				}
			}
		}
	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste horizontale a les
	 * deux cotés fermés.
	 */
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

			if (panelRegroupement.getListePisteHorizontale().size() != 0) {
				for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
					panelRegroupement.getListePisteHorizontale().get(a).setNombrePisteColle(0);
				}
			}
		}

	}

	/**
	 * Méthode qui permet de vérifier si chaque morceau de piste de depart a les
	 * deux cotés fermés.
	 */

	public void verifierNombrePisteAttacheDepart() {
		PisteDeDepart piste = panelRegroupement.getListePisteDeDepart().get(0);
		Rectangle2D.Double formeAireGauche = new Rectangle2D.Double(piste.getX() - piste.getTaillePiste(), piste.getY(),
				piste.getTaillePiste(), piste.getTaillePiste());
		Rectangle2D.Double formeAireDroit = new Rectangle2D.Double(piste.getX() + piste.getTaillePiste(), piste.getY(),
				piste.getTaillePiste(), piste.getTaillePiste());

		if (panelRegroupement.getListePisteHorizontale().size() != 0) {

			for (int a = 0; a < panelRegroupement.getListePisteHorizontale().size(); a++) {
				PisteHorizontale pisteHorizontale = panelRegroupement.getListePisteHorizontale().get(a);
				if (formeAireGauche.contains(pisteHorizontale.getX(), pisteHorizontale.getY())
						|| formeAireDroit.contains(pisteHorizontale.getX(), pisteHorizontale.getY())) {
					compteurPisteCoteDePisteDepart++;
				}

			}
		}
		if (panelRegroupement.getListePisteVirageGauche().size() != 0) {
			for (int a = 0; a < panelRegroupement.getListePisteVirageGauche().size(); a++) {
				PisteVirageGauche pisteVirageGauche = panelRegroupement.getListePisteVirageGauche().get(a);
				if (formeAireDroit.contains(pisteVirageGauche.getX(), pisteVirageGauche.getY())) {
					compteurPisteCoteDePisteDepart++;
				}

			}
		}
		if (panelRegroupement.getListePisteVirageDroit().size() != 0) {

			for (int a = 0; a < panelRegroupement.getListePisteVirageDroit().size(); a++) {
				PisteVirageDroit pisteVirageDroit = panelRegroupement.getListePisteVirageDroit().get(a);
				if (formeAireDroit.contains(pisteVirageDroit.getX(), pisteVirageDroit.getY())) {
					compteurPisteCoteDePisteDepart++;
				}
			}
		}
		if (panelRegroupement.getListePisteVirageHaut().size() != 0) {

			for (int a = 0; a < panelRegroupement.getListePisteVirageHaut().size(); a++) {
				PisteVirageHaut pisteVirageHaut = panelRegroupement.getListePisteVirageHaut().get(a);
				if (formeAireGauche.contains(pisteVirageHaut.getX(), pisteVirageHaut.getY())) {
					compteurPisteCoteDePisteDepart++;
				}
			}
		}
		if (panelRegroupement.getListePisteVirageBas().size() != 0) {
			for (int a = 0; a < panelRegroupement.getListePisteVirageBas().size(); a++) {
				PisteVirageBas pisteVirageBas = panelRegroupement.getListePisteVirageBas().get(a);
				if (formeAireGauche.contains(pisteVirageBas.getX(), pisteVirageBas.getY())) {
					compteurPisteCoteDePisteDepart++;
				}

			}
		}
		if (panelRegroupement.getListePisteDeDepart().size() != 0) {

			if (compteurPisteCoteDePisteDepart == 2) {

				nombrePisteFerme++;

			}
		}
		compteurPisteCoteDePisteDepart = 0;
	}

	/**
	 * Méthode qui permet de vérifier si la piste est fermée.
	 */
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

			pisteFerme = false;
		}
//		System.out.println(nombrePisteFerme);
		nombrePisteFerme = 0;
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

		gestionFich.ecrireFichierBinBureauRegroupement(regroupement);

		comboBoxPiste.addItem(gestionFich.getNomFichBinRegroupement());

		btnJouer.setEnabled(true);
		JOptionPane.showMessageDialog(null,
				"PISTE SAUVEGARDER SUR LE BUREAU\nNOM :" + gestionFich.getNomFichBinRegroupement());

	}

	/**
	 * Méthode qui permet de charger une piste qui est sur le bureau
	 */

	private void chargementUnePiste() {

		btnSauvegarde.setEnabled(true);
		btnJouer.setEnabled(true);
		regroupementSauvegarde = gestionFich.lireFichierBinBureauRegroupement((String) comboBoxPiste.getSelectedItem());

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

}