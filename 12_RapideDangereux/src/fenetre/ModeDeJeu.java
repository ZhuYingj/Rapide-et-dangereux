package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import application.OutilsImage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dessin.ZoneAnimPhysique;
import javax.swing.ImageIcon;

/**
 * Classe qui crée la fenêtre pour choisir les modes de jeux
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

	/** ajouter le support pour lancer des evenements de type PropertyChange **/
//	Alexis Pineda-Alvarado
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private JLabel lblNewLabel;

	/**
	 * Création de la fenetre
	 */
//	Alexis Pineda-Alvarado
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Méthode qui permet la création de la fenetre mode de jeu
	 */
//	Alexis Pineda-Alvarado
	public ModeDeJeu() {
		setLayout(null);
		btnMonde = new JButton("MONDE");
		btnMonde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("MONDE", 0, -1);
			}
		});

		btnMonde.setForeground(Color.BLACK);
		btnMonde.setBounds(650 - 100, 245, 100, 49);
		add(btnMonde);

		btnCourseMontre = new JButton("COURSE CONTRE LA MONTRE");
		btnCourseMontre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("COURSE CONTRE LA MONTRE", 0, -1);
			}
		});
		btnCourseMontre.setForeground(Color.BLACK);
		btnCourseMontre.setBounds(489, 324, 227, 49);
		add(btnCourseMontre);

		btnEditeur = new JButton("EDITEUR");
		btnEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("EDITEUR", 0, -1);
			}
		});
		btnEditeur.setForeground(Color.BLACK);
		btnEditeur.setBounds(650 - 100, 402, 100, 49);
		add(btnEditeur);

		lblTitre = new JLabel("Mode de jeu");
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 30));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(418, 144, 349, 75);
		add(lblTitre);

		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", null, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);
		
		lblNewLabel = new JLabel("");
		URL urlBleu = getClass().getClassLoader().getResource("blueBackground.gif");
		ImageIcon bleuFond = new ImageIcon(urlBleu);
		lblNewLabel.setIcon(bleuFond);
	
		lblNewLabel.setBounds(0, 0, 1350, 800);
		add(lblNewLabel);
		
	}
}
