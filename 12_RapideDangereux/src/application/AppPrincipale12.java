package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dessin.ZoneAnimPhysique;
import fenetre.FenetreEditeur;
import fenetre.ModeDeJeu;
import fenetre.FenetreMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppPrincipale12 extends JFrame {

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
		setBounds(100, 100, 1000, 700);
		setTitle("Rapide et Dangereux");

		FenetreMenu fen1 = new FenetreMenu();
		ModeDeJeu fen2 = new ModeDeJeu();
		FenetreEditeur fen3 = new FenetreEditeur();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JCheckBoxMenuItem checkBoxModeScientifique = new JCheckBoxMenuItem("Mode Scientifique");
		checkBoxModeScientifique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu.add(checkBoxModeScientifique);

		setContentPane(fen1);

		fen1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("JOUER")) {
					fen1.setVisible(false);
					fen2.setVisible(true);
					setContentPane(fen2);
				}
			}
		});

		fen2.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "Retour":
					fen2.setVisible(false);
					fen1.setVisible(true);
					setContentPane(fen1);
					break;

				}
			}
		});
		fen2.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "EDITEUR":
					fen2.setVisible(false);
					fen3.setVisible(true);
					setContentPane(fen3);
					break;
				
				}
			}
		});

		fen3.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "Retour":
					fen2.setVisible(true);
					fen3.setVisible(false);
					setContentPane(fen2);
					break;
				
				}
			}
		});

	}
}