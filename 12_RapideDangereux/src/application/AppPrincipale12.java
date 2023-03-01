package application;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fenetre.FenetreEditeur;
import fenetre.FenetreJeuSansScientifique;
import fenetre.FenetreJeuScientifique;
import fenetre.FenetreMenu;
import fenetre.JeuOptions;
import fenetre.ModeDeJeu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class AppPrincipale12 extends JFrame {

	private JCheckBoxMenuItem checkBoxModeNonScientifique;
	private JPanel contentPane;

	/**
	 * Lancement de l'application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppPrincipale12 frame = new AppPrincipale12();
					frame.setVisible(true);
					frame.requestFocus();
					frame.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if (e.getKeyCode() == KeyEvent.VK_P) {
								JOptionPane.showMessageDialog(null, "sup");
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creation de la fenetre.
	 */
	public AppPrincipale12() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 800);
		setTitle("Rapide et Dangereux");

		FenetreMenu fenMenu = new FenetreMenu();
		ModeDeJeu fenModeJeu = new ModeDeJeu();
		FenetreEditeur fenEditeur = new FenetreEditeur();
		FenetreJeuSansScientifique fenSansScience = new FenetreJeuSansScientifique();
		FenetreJeuScientifique fenJeuScience = new FenetreJeuScientifique();
		JeuOptions fenOptions = new JeuOptions();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);

		setContentPane(fenMenu);

		fenMenu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("JOUER")) {
					fenMenu.setVisible(false);
					fenModeJeu.setVisible(true);
					setContentPane(fenModeJeu);
				}
			}
		});

		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "Retour":
					fenModeJeu.setVisible(false);
					fenMenu.setVisible(true);
					setContentPane(fenMenu);
					break;

				}
			}
		});
		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "EDITEUR":
					fenModeJeu.setVisible(false);
					fenEditeur.setVisible(true);
					setContentPane(fenEditeur);
					break;

				}
			}
		});

		fenEditeur.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "Retour":
					fenModeJeu.setVisible(true);
					fenEditeur.setVisible(false);
					setContentPane(fenModeJeu);
					break;

				}
			}
		});

		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "MONDE":
					fenModeJeu.setVisible(false);
					fenOptions.setVisible(true);
					setContentPane(fenOptions);
					break;
				}
			}
		});

		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "MONDE":
					fenModeJeu.setVisible(false);
					fenOptions.setVisible(true);
					setContentPane(fenOptions);
					break;
				}
			}
		});

		fenOptions.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "Retour":
					fenModeJeu.setVisible(true);
					fenOptions.setVisible(false);
					setContentPane(fenModeJeu);
					break;
				}
			}
		});

		fenOptions.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "COMMENCER!":
					fenJeuScience.setVisible(true);
					fenOptions.setVisible(false);
					setContentPane(fenJeuScience);
					pushingP(fenJeuScience);

					break;
				case "MASSE":
					fenJeuScience.getZoneAnimPhysique().setVoitureMasse((double) evt.getNewValue());
					break;
				case "VITESSEMAXFACILE":
					fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
					break;
				case "VITESSEMAXMOYEN":
					fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
					break;
				case "VITESSEMAXDIFFICILE":
					fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
					break;


				}

			}
		});

		fenMenu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "Test":
					fenJeuScience.setVisible(true);
					fenMenu.setVisible(false);
					setContentPane(fenJeuScience);
					break;

				}
			}
		});

		fenJeuScience.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "Retour":
					fenJeuScience.setVisible(false);
					fenOptions.setVisible(true);
					setContentPane(fenOptions);
					break;

				}
			}
		});

		fenSansScience.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "Retour":
					fenSansScience.setVisible(false);
					fenOptions.setVisible(true);
					setContentPane(fenOptions);
					break;

				}
			}
		});

		checkBoxModeNonScientifique = new JCheckBoxMenuItem("Mode Non-Scientifique");
		checkBoxModeNonScientifique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkBoxModeNonScientifique.isSelected()) {
					fenSansScience.setVisible(true);
					fenJeuScience.setVisible(false);
					setContentPane(fenSansScience);
					fenSansScience.setZoneAnimPhysique(fenJeuScience.getZoneAnimPhysique());
					pushingP(fenSansScience);

				} else {
					fenSansScience.setVisible(false);
					fenJeuScience.setVisible(true);
					setContentPane(fenJeuScience);
					fenJeuScience.setZoneAnimPhysique(fenSansScience.getZoneAnimPhysique());

				}
			}
		});
		mnNewMenu.add(checkBoxModeNonScientifique);
	}

	/**
	 * Fait en sorte que lorsqu'on appui sur la touche P un panel JOptionPane va
	 * apparaitre
	 * 
	 * @param fenetreVoulu permet de prendre le JPanel specifique
	 */
	// Alexis Pineda-Alvarado
	public void pushingP(JPanel fenetreVoulu) {
		fenetreVoulu.requestFocus();
		fenetreVoulu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_P) {
					JOptionPane.showMessageDialog(null, "sup");
				}
			}
		});
	}

}
