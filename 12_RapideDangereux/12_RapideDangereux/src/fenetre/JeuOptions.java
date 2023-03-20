package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import utilitaireObjets.Voiture;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import dessin.OutilsImage;
import dessin.ZoneApercupiste;
import pisteDeCourse.PisteMexique;

/**
 * 
 * @author Alexis Pineda-Alvarado
 * @author Ludovic Julien
 */

public class JeuOptions extends JPanel {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final ButtonGroup buttonGroupDiff = new ButtonGroup();
	private JRadioButton rdbtnFacile;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnDifficile;
	private Voiture voiture;
	private JSlider slider;

	private Image imageActuelle;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */

	public JeuOptions() {
		setLayout(null);

		JPanel PanelApercu = new JPanel();
		PanelApercu.setBackground(Color.WHITE);
		PanelApercu.setBounds(10, 200, 649, 489);
		add(PanelApercu);
		PanelApercu.setLayout(null);

		/**
		 * Creer une zone de dessin "ZoneApercuPiste
		 * 
		 * @author Ludovic Julien
		 */
		ZoneApercupiste zoneApercupiste = new ZoneApercupiste();
		zoneApercupiste.setBounds(0, 0, 650, 405);
		PanelApercu.add(zoneApercupiste);
		/**
		 * Creer le bouton qui permet si appuiyer d'afficher l'apercu de cette piste
		 * 
		 * @author Ludovic Julien
		 */
		Object drapeuxMexique = OutilsImage.lireImage("PisteMexique.png");
		// Icon icone = new ImageIcon(drapeuxMexique);
		JButton btnMexique = new JButton("Mexique");
		// btnMexique.setIcon(icone);
		btnMexique.setBounds(130, 77, 126, 78);
		add(btnMexique);
		btnMexique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageActuelle = OutilsImage.lireImage("PisteMexique.png");
				zoneApercupiste.setImg(imageActuelle);
				zoneApercupiste.repaint();
			}
		});
		/**
		 * Creer le bouton qui permet si appuiyer d'afficher l'apercu de cette piste
		 * 
		 * @author Ludovic Julien
		 */
		JButton btnCanada = new JButton("Canada");
		btnCanada.setBounds(307, 77, 126, 78);
		add(btnCanada);
		btnCanada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageActuelle = OutilsImage.lireImage("Construction.gif");
				zoneApercupiste.setImg(imageActuelle);
				zoneApercupiste.repaint();
			}
		});
		/**
		 * Creer le bouton qui permet si appuiyer d'afficher l'apercu de cette piste
		 * 
		 * @author Ludovic Julien
		 */
		JButton btnItalie = new JButton("Italie");
		btnItalie.setBounds(474, 77, 126, 78);
		add(btnItalie);
		btnItalie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageActuelle = OutilsImage.lireImage("pisteItalie.PNG");
				zoneApercupiste.setImg(imageActuelle);
				zoneApercupiste.repaint();
			}
		});

		Icon feuVert = new ImageIcon("green.jpg");
		JLabel feuGreen = new JLabel();
		feuGreen.setIcon(feuVert);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(760, 345, 549, 297);
		add(panel_1);
		panel_1.setLayout(null);

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				pcs.firePropertyChange("MASSE", null, (double) slider.getValue());
				
			}
		});

		slider.setMajorTickSpacing(10);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(10);
		slider.setMinimum(50);
		slider.setBounds(165, 33, 343, 40);
		panel_1.add(slider);

		JLabel lblMasse = new JLabel("Masse de la voiture en kg : ");
		lblMasse.setBounds(10, 39, 163, 20);
		panel_1.add(lblMasse);

		JLabel lblDifficulte = new JLabel("Difficulté du jeu : ");
		lblDifficulte.setBounds(10, 84, 110, 14);
		panel_1.add(lblDifficulte);

		rdbtnFacile = new JRadioButton("Facile");
		rdbtnFacile.setSelected(true);
		rdbtnFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pcs.firePropertyChange("MASSE", null, 60.0);

			}
		});
		rdbtnFacile.setBounds(165, 80, 109, 23);
		panel_1.add(rdbtnFacile);
		buttonGroupDiff.add(rdbtnFacile);

		rdbtnMedium = new JRadioButton("Intermédiaire");
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pcs.firePropertyChange("VITESSEMAXINTERMEDIAIRE", null, 80.0);

			}
		});
		rdbtnMedium.setBounds(165, 105, 109, 23);
		panel_1.add(rdbtnMedium);
		buttonGroupDiff.add(rdbtnMedium);

		rdbtnDifficile = new JRadioButton("Avancé");
		rdbtnDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pcs.firePropertyChange("VITESSEMAXAVANCE", null, 100.0);

			}
		});
		rdbtnDifficile.setBounds(165, 131, 109, 23);
		panel_1.add(rdbtnDifficile);
		buttonGroupDiff.add(rdbtnDifficile);

		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("COMMENCER!", null, -1);
				pcs.firePropertyChange("MASSE", null, (double) slider.getValue());

			}
		});
		btnCommencer.setBounds(984, 653, 143, 36);
		add(btnCommencer);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(970, 77, 143, 257);
		add(panel_2);

		JButton btnGauche = new JButton("<");

		btnGauche.setBounds(905, 188, 55, 23);
		add(btnGauche);

		JButton btnDroite = new JButton(">");
		btnDroite.setBounds(1123, 188, 55, 23);
		add(btnDroite);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

	}
}
