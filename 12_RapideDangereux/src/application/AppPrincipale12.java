package application;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

/**
 * Application permettant d'illustrer une simulation physique
 * 
 * @author Alexis Pineda-Alvarado
 * @author Tan Tommy Rin
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
					frame.checkBoxModeNonScientifique.setEnabled(false);
					frame.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if (e.getKeyCode() == KeyEvent.VK_P) {

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
				actionChangeModeJeu(evt, fenMenu, fenModeJeu);
			}
		});

		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourMenu(evt, fenMenu, fenModeJeu);
			}
		});
		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionChangeEditeur(evt, fenModeJeu, fenEditeur);
			}
		});

		fenEditeur.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourModeJeu1(evt, fenModeJeu, fenEditeur);

			}
		});

		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionChangeOption(evt, fenModeJeu, fenOptions);
			}
		});

		fenOptions.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourModeJeu2(evt, fenModeJeu, fenOptions);
			}
		});

		fenOptions.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionFenOptions(evt, fenJeuScience, fenOptions);
			}
		});

		fenJeuScience.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourOptions(evt, fenJeuScience, fenOptions);
			}
		});

		fenSansScience.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourOptions2(evt, fenSansScience, fenOptions);
			}
		});

		checkBoxModeNonScientifique = new JCheckBoxMenuItem("Mode Non-Scientifique");
		checkBoxModeNonScientifique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkBoxModeNonScientifique.isSelected()) {
					actionChangeDesTypeJeu(fenSansScience, fenJeuScience);

				} else {
					actionRetourDesTypeJeu(fenSansScience, fenJeuScience);

				}
			}
		});
		mnNewMenu.add(checkBoxModeNonScientifique);
	}

	/**
	 * Méthode permettant d'accomplir des actions selon des levés d'évènements liés
	 * à la fenetre de jeu d'options
	 * 
	 * @param evt           evenement
	 * @param fenJeuScience la fenetre de jeu avec mode science activé
	 * @param fenOptions    la fenetre de jeu d'options
	 */
	// Par Tan Tommy Rin
	public void actionFenOptions(PropertyChangeEvent evt, FenetreJeuScientifique fenJeuScience, JeuOptions fenOptions) {
		switch (evt.getPropertyName()) {

		case "COMMENCER!":
			fenJeuScience.setVisible(true);
			fenOptions.setVisible(false);
			setContentPane(fenJeuScience);
			checkBoxModeNonScientifique.setEnabled(true);
			pushingP(fenJeuScience);
			break;
		case "MASSE":
			fenJeuScience.getZoneAnimPhysique().setVoitureMasse((double) evt.getNewValue());
			break;
		case "VITESSEMAXFACILE":
			fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			break;
		case "VITESSEMAXINTERMEDIAIRE":
			fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			break;
		case "VITESSEMAXAVANCE":
			fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			break;
		}
	}

	/**
	 * Méthode permettant d'accomplir des actions selon des levés d'évènements liés
	 * à la fenetre du menu et la fenetre mde de jeu
	 * 
	 * @param evt
	 * @param fenMenu
	 * @param fenModeJeu
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeModeJeu(PropertyChangeEvent evt, FenetreMenu fenMenu, ModeDeJeu fenModeJeu) {
		if (evt.getPropertyName().equals("JOUER")) {

			fenMenu.setVisible(false);
			fenModeJeu.setVisible(true);
			setContentPane(fenModeJeu);
		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre menu et celui du mode de jeu
	 * 
	 * @param evt
	 * @param fenMenu
	 * @param fenModeJeu
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourMenu(PropertyChangeEvent evt, FenetreMenu fenMenu, ModeDeJeu fenModeJeu) {
		switch (evt.getPropertyName()) {

		case "Retour":
			fenModeJeu.setVisible(false);
			fenMenu.setVisible(true);
			setContentPane(fenMenu);
			checkBoxModeNonScientifique.setEnabled(false);
			break;

		}
	}

	/**
	 * Méthode qui change de la fenetre mode de jeu à la fenetre du mode éditeur
	 * avec les levés d'évenements
	 * 
	 * @param evt
	 * @param fenModeJeu
	 * @param fenEditeur
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeEditeur(PropertyChangeEvent evt, ModeDeJeu fenModeJeu, FenetreEditeur fenEditeur) {
		switch (evt.getPropertyName()) {

		case "EDITEUR":
			fenModeJeu.setVisible(false);
			fenEditeur.setVisible(true);
			setContentPane(fenEditeur);
			break;

		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre du mod de jeu et celui du mode éditeur
	 * 
	 * @param evt
	 * @param fenModeJeu
	 * @param fenEditeur
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourModeJeu1(PropertyChangeEvent evt, ModeDeJeu fenModeJeu, FenetreEditeur fenEditeur) {
		switch (evt.getPropertyName()) {

		case "Retour":
			fenModeJeu.setVisible(true);
			fenEditeur.setVisible(false);
			setContentPane(fenModeJeu);
			break;

		}
	}

	/**
	 * Méthode qui change de la fenetre mode de jeu à la fenetre options avec les
	 * levés d'évenements
	 * 
	 * @param evt
	 * @param fenModeJeu
	 * @param fenOptions
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeOption(PropertyChangeEvent evt, ModeDeJeu fenModeJeu, JeuOptions fenOptions) {
		switch (evt.getPropertyName()) {

		case "MONDE":
			fenModeJeu.setVisible(false);
			fenOptions.setVisible(true);
			setContentPane(fenOptions);
			break;
		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre du mode de jeu et celui de la fenetre des
	 * options
	 * 
	 * @param evt
	 * @param fenModeJeu
	 * @param fenOptions
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourModeJeu2(PropertyChangeEvent evt, ModeDeJeu fenModeJeu, JeuOptions fenOptions) {
		switch (evt.getPropertyName()) {

		case "Retour":
			fenModeJeu.setVisible(true);
			fenOptions.setVisible(false);
			setContentPane(fenModeJeu);
			break;
		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre du jeu et celui de la fenetre des options
	 * 
	 * @param evt
	 * @param fenJeuScience
	 * @param fenOptions
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourOptions(PropertyChangeEvent evt, FenetreJeuScientifique fenJeuScience,
			JeuOptions fenOptions) {

		switch (evt.getPropertyName()) {
		case "Retour":
			fenJeuScience.setVisible(false);
			fenOptions.setVisible(true);
			setContentPane(fenOptions);
			break;

		case "STARTBUTTONACTIVE":
			checkBoxModeNonScientifique.setEnabled(false);
			break;
		case "CHECKBOXACTIVE":
			checkBoxModeNonScientifique.setEnabled(true);

		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre du jeu sans les parametres scientifique et
	 * celui de la fenetre des options
	 * 
	 * @param evt
	 * @param fenSansScience
	 * @param fenOptions
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourOptions2(PropertyChangeEvent evt, FenetreJeuSansScientifique fenSansScience,
			JeuOptions fenOptions) {

		switch (evt.getPropertyName()) {
		case "Retour":
			fenSansScience.setVisible(false);
			fenOptions.setVisible(true);
			setContentPane(fenOptions);
			break;

		}
	}

	/**
	 * Méthode qui permet de changer de la fenetre du jeu avec les paremetres
	 * scientifiques avec la fenetre du jeu sans les paramatres scientifique
	 * 
	 * @param fenSansScience
	 * @param fenJeuScience
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeDesTypeJeu(FenetreJeuSansScientifique fenSansScience,
			FenetreJeuScientifique fenJeuScience) {

		fenSansScience.setVisible(true);
		fenJeuScience.setVisible(false);
		setContentPane(fenSansScience);
		fenJeuScience.getZoneAnimPhysique().requestFocusInWindow();

		checkBoxModeNonScientifique.setEnabled(true);
		fenSansScience.setZoneAnimPhysique(fenJeuScience.getZoneAnimPhysique());
		pushingP(fenSansScience);

	}

	/**
	 * Méthode qui permet de changer de la fenetre du jeu avec sans les paremetres
	 * scientifiques avec la fenetre du jeu avec les paramatres scientifique
	 * 
	 * @param fenSansScience
	 * @param fenJeuScience
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourDesTypeJeu(FenetreJeuSansScientifique fenSansScience,
			FenetreJeuScientifique fenJeuScience) {

		fenSansScience.setVisible(false);
		fenJeuScience.setVisible(true);
		setContentPane(fenJeuScience);
		fenJeuScience.setZoneAnimPhysique(fenSansScience.getZoneAnimPhysique());
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
					JOptionPane.showMessageDialog(null,
							"Bonjour dans RAPIDE ET DANGEREUX! \nle but de ce jeux et de battre votre combattant"
									+ " \nles contrôles du jeu sont :  \n↑ : pour avancer la voiture"
									+ " \n← et → : pour tourner a gauche et a droite \n↓ : pour ralentir la voiture");
				}
			}
		});
	}

}
