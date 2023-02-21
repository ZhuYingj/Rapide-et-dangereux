package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dessin.ZoneAnimPhysique;
import fenetre.ModeDeJeu;
import fenetre.fenetreMenu;

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

		fenetreMenu fen1 = new fenetreMenu();
		ModeDeJeu fen2 = new ModeDeJeu();

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
				case "RETOUR":
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
				case "Retour":
					fen2.setVisible(false);
					fen1.setVisible(true);
					setContentPane(fen1);
					break;
				
				}
			}
		});

	}
}