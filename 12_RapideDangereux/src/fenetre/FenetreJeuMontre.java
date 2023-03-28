package fenetre;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JPanel;

import dessin.ZoneAnimPhysique;
import java.awt.Color;
import javax.swing.JLabel;

/**
 * Classe qui crée la fenêtre pour la zone de jeu pour le mode de jeu
 * course contre la montre
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class FenetreJeuMontre extends JPanel {
	private ZoneAnimPhysique nouvZoneAnimPhysique;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Create the panel.
	 */
	public FenetreJeuMontre() {
		setLayout(null);
		setBounds(100, 100, 1300, 700);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(48, 83, 808, 514);
		add(panel);
		
		JLabel lbltest = new JLabel("New label");
		lbltest.setBounds(54, 34, 46, 14);
		add(lbltest);
		

	}

}