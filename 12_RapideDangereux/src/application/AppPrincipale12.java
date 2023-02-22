package application;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import fenetre.FenetreEditeur;
import fenetre.FenetreJeuSansScientifique;
import fenetre.FenetreJeuScientifique;
import fenetre.FenetreMenu;
import fenetre.JeuOptions;
import fenetre.ModeDeJeu;

public class AppPrincipale12 extends JFrame {

	private JCheckBoxMenuItem checkBoxModeNonScientifique;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppPrincipale12 frame = new AppPrincipale12();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppPrincipale12() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 700);
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


		checkBoxModeNonScientifique = new JCheckBoxMenuItem("Mode Non-Scientifique");
		checkBoxModeNonScientifique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBoxModeNonScientifique.isSelected()) {
					fenSansScience.setVisible(false);
				} else {
					fenJeuScience.setVisible(true);
				}
			}
		});
		mnNewMenu.add(checkBoxModeNonScientifique);
	}
}