package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dessin.ZoneAnimPhysique;

/**
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class ModeDeJeu extends JPanel {
	private JButton btnMonde;
	private JButton btnCourseMontre;
	private JButton btnEditeur;
	private JLabel lblTitre;
	private JButton btnRetour;

	// ajouter le support pour lancer des evenements de type PropertyChange
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Create the panel.
	 */

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public ModeDeJeu() {
		setLayout(null);
		btnMonde = new JButton("MONDE");
		btnMonde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("MONDE", 0, -1);
			}
		});
		
		btnMonde.setForeground(Color.BLACK);
		btnMonde.setBounds(411, 245, 100, 49);
		add(btnMonde);

		btnCourseMontre = new JButton("COURSE CONTRE LA MONTRE");
		btnCourseMontre.setForeground(Color.BLACK);
		btnCourseMontre.setBounds(346, 319, 227, 49);
		add(btnCourseMontre);

		btnEditeur = new JButton("EDITEUR");
		btnEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("EDITEUR", 0, -1);
			}
		});
		btnEditeur.setForeground(Color.BLACK);
		btnEditeur.setBounds(411, 402, 100, 49);
		add(btnEditeur);

		lblTitre = new JLabel("Mode de jeu");
		lblTitre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 30));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(293, 146, 349, 75);
		add(lblTitre);

		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", null, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);
	}
}
