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
import interfaces.TypePiste;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private JLabel lblNewLabel;

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 * 
	 * @param listener L'écouteur
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
		setBackground(Color.BLACK);

		setLayout(null);
		btnMonde = new JButton("MONDE");
		btnMonde.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnMonde.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pcs.firePropertyChange("PISTECREATION", 0, TypePiste.MEXIQUE);
				if (btnMonde.contains(e.getX(), e.getY())) {
					URL urlPlanete = getClass().getClassLoader().getResource("earth.gif");
					ImageIcon planete = new ImageIcon(urlPlanete);
					lblNewLabel.setIcon(planete);
				}
				repaint();
			}

		});
		btnMonde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("MONDE", 0, -1);
			}
		});

		btnMonde.setForeground(Color.BLACK);
		btnMonde.setBounds(700, 245, 100, 49);
		add(btnMonde);

		btnCourseMontre = new JButton("COURSE CONTRE LA MONTRE");
		btnCourseMontre.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnCourseMontre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnCourseMontre.contains(e.getX(), e.getY())) {
					URL urlPlanete = getClass().getClassLoader().getResource("clock.gif");
					ImageIcon planete = new ImageIcon(urlPlanete);
					lblNewLabel.setIcon(planete);
				}
			}
		});
		btnCourseMontre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("COURSE CONTRE LA MONTRE", 0, -1);
			}
		});
		btnCourseMontre.setForeground(Color.BLACK);
		btnCourseMontre.setBounds(643, 323, 227, 49);
		add(btnCourseMontre);

		btnEditeur = new JButton("EDITEUR");
		btnEditeur.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnEditeur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnEditeur.contains(e.getX(), e.getY())) {
					URL urlPlanete = getClass().getClassLoader().getResource("editeur.gif");
					ImageIcon planete = new ImageIcon(urlPlanete);
					lblNewLabel.setIcon(planete);
				}
			}
		});
		btnEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("EDITEUR", 0, -1);
			}
		});
		btnEditeur.setForeground(Color.BLACK);
		btnEditeur.setBounds(700, 402, 100, 49);
		add(btnEditeur);

		lblTitre = new JLabel("Mode de jeu");
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(568, 144, 349, 75);
		add(lblTitre);

		btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", null, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		lblNewLabel = new JLabel("");

		lblNewLabel.setBounds(0, 0, 1600, 800);
		add(lblNewLabel);

	}
}
