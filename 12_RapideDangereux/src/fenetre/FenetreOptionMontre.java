package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;

import application.OutilsImage;
import dessin.ZoneApercuPiste;
import interfaces.TypePiste;
import utilitaireObjets.Regroupement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * Classe qui crée la fenetre pour les paramètres a choisir pour le mode de jeu
 * course contre la montre
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class FenetreOptionMontre extends JPanel {

	private TypePiste type = TypePiste.MEXIQUE;
	private Image imageActuelle;
	private ZoneApercuPiste zoneApercuPiste;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private JSlider slider;
	private JSlider slider2;
	private JSlider sliderNbrTour;
	private JRadioButton rdbtnFacile;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnDifficile;
	private final ButtonGroup buttonGroupDiff = new ButtonGroup();
	private JLabel lblVitesseDifficile;
	private JLabel lblImage;
	private JButton btnCanada;
	private JButton btnMexique;
	private JButton btnItalie;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Create the panel.
	 */
	public FenetreOptionMontre() {
		setLayout(null);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		JPanel PanelApercu = new JPanel();
		PanelApercu.setBackground(Color.WHITE);
		PanelApercu.setBounds(10, 203, 700, 439);
		add(PanelApercu);
		PanelApercu.setLayout(null);
		zoneApercuPiste = new ZoneApercuPiste();
		zoneApercuPiste.setBounds(0, 0, 700, 439);
		PanelApercu.add(zoneApercuPiste);

		btnCanada = new JButton("Canada");
		btnCanada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectionImageCanada(e);
			}
		});
		btnCanada.setBounds(307, 77, 126, 78);
		add(btnCanada);

		Image imgCanada = OutilsImage.lireImageEtRedimensionner("canada.png", 140, 77);
		if (imgCanada != null) {
			btnCanada.setIcon(new ImageIcon(imgCanada));
			imgCanada.flush();
		}

		btnItalie = new JButton("Italie");
		btnItalie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectionImageItalie(e);
			}
		});
		btnItalie.setBounds(474, 77, 126, 78);
		add(btnItalie);

		Image imgItalia = OutilsImage.lireImageEtRedimensionner("italie-flag.jpg", 140, 77);
		if (imgItalia != null) {
			btnItalie.setIcon(new ImageIcon(imgItalia));
			imgItalia.flush();
		}

		btnMexique = new JButton("Mexique");
		btnMexique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectionImageMexique(e);
			}
		});
		btnMexique.setBounds(130, 77, 126, 78);
		add(btnMexique);

		Image imgMexique = OutilsImage.lireImageEtRedimensionner("mexicano.png", 140, 77);
		if (imgMexique != null) {
			btnMexique.setIcon(new ImageIcon(imgMexique));
			imgMexique.flush();
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		panel_1.setBounds(760, 345, 549, 297);
		add(panel_1);
		panel_1.setLayout(null);

		rdbtnFacile = new JRadioButton("Facile");
		rdbtnFacile.setSelected(true);
		rdbtnFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pcs.firePropertyChange("VITESSEMAXFACILE2", null, 60.0);

			}
		});
		rdbtnFacile.setBounds(165, 182, 109, 23);
		panel_1.add(rdbtnFacile);
		buttonGroupDiff.add(rdbtnFacile);

		rdbtnMedium = new JRadioButton("Intermédiaire");
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pcs.firePropertyChange("VITESSEMAXINTERMEDIAIRE2", null, 80.0);

			}
		});
		rdbtnMedium.setBounds(165, 207, 109, 23);
		panel_1.add(rdbtnMedium);
		buttonGroupDiff.add(rdbtnMedium);

		rdbtnDifficile = new JRadioButton("Avancé");
		rdbtnDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("VITESSEMAXAVANCE2", null, 100.0);
			}
		});
		rdbtnDifficile.setBounds(165, 233, 109, 23);
		panel_1.add(rdbtnDifficile);
		buttonGroupDiff.add(rdbtnDifficile);

		JLabel lblDifficulte = new JLabel("Difficulté du jeu : ");
		lblDifficulte.setBounds(10, 182, 110, 14);
		panel_1.add(lblDifficulte);

		slider = new JSlider();

		slider.setMajorTickSpacing(10);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(10);
		slider.setMinimum(50);
		slider.setBounds(165, 33, 343, 40);
		panel_1.add(slider);

		slider2 = new JSlider();
		slider2.setSnapToTicks(true);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		slider2.setMinorTickSpacing(10);
		slider2.setMinimum(50);
		slider2.setMajorTickSpacing(10);
		slider2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slider2.setBounds(165, 84, 343, 40);
		panel_1.add(slider2);

		sliderNbrTour = new JSlider();
		sliderNbrTour.setValue(1);
		sliderNbrTour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sliderNbrTour.setMinorTickSpacing(1);
		sliderNbrTour.setMajorTickSpacing(1);
		sliderNbrTour.setSnapToTicks(true);
		sliderNbrTour.setPaintTicks(true);
		sliderNbrTour.setPaintLabels(true);
		sliderNbrTour.setMinimum(1);
		sliderNbrTour.setMaximum(5);
		sliderNbrTour.setBounds(165, 135, 343, 40);
		panel_1.add(sliderNbrTour);

		JLabel lblMasse = new JLabel("Masse de la voiture 1 en kg : ");
		lblMasse.setBounds(10, 39, 191, 20);
		panel_1.add(lblMasse);

		JLabel lblMasse2 = new JLabel("Masse de la voiture 2 en kg : ");
		lblMasse2.setBounds(10, 86, 177, 20);
		panel_1.add(lblMasse2);

		JLabel lblNbrTour = new JLabel("Nombre de tour : \r\n");
		lblNbrTour.setBounds(10, 135, 177, 14);
		panel_1.add(lblNbrTour);

		JLabel lblVitesseFacile = new JLabel("60 m/s");
		lblVitesseFacile.setBounds(280, 186, 46, 14);
		panel_1.add(lblVitesseFacile);

		JLabel lblVitesseMedium = new JLabel("80 m/s");
		lblVitesseMedium.setBounds(280, 211, 65, 14);
		panel_1.add(lblVitesseMedium);

		lblVitesseDifficile = new JLabel("100 m/s");
		lblVitesseDifficile.setBounds(280, 237, 65, 14);
		panel_1.add(lblVitesseDifficile);

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

		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("COMMENCER COURSE MONTRE", null, -1);
				pcs.firePropertyChange("TYPEPISTE", null, type);
				pcs.firePropertyChange("MASSEMONTRE1", null, (double) slider.getValue());
				pcs.firePropertyChange("MASSEMONTRE2", null, (double) slider2.getValue());
				pcs.firePropertyChange("NBRDETOUR", null, (double) sliderNbrTour.getValue());
			}
		});
		btnCommencer.setBounds(984, 653, 143, 36);
		add(btnCommencer);

		lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 1600, 800);
		add(lblImage);
	}

	/**
	 * Méthode qui choisie la piste et une photo de fond lorsque la souris et sur le
	 * bouton
	 * 
	 * @param e evenement de la souris
	 */
	// Alexis Pineda-Alvarado
	public void selectionImageCanada(MouseEvent e) {
		if (btnCanada.contains(e.getX(), e.getY())) {
			type = TypePiste.CANADA;
			imageActuelle = OutilsImage.lireImage("PisteCanada.png");
			zoneApercuPiste.setImg(imageActuelle);
			zoneApercuPiste.repaint();

			Image imgHiver = OutilsImage.lireImageEtRedimensionner("canadaWinter.jpg", 1600, 800);
			if (imgHiver != null) {
				lblImage.setIcon(new ImageIcon(imgHiver));
				imgHiver.flush();
			}
		}
	}

	/**
	 * Méthode qui choisie la piste et une photo de fond lorsque la souris et sur le
	 * bouton
	 * 
	 * @param e evenement de la souris
	 */
	// Alexis Pineda-Alvarado
	public void selectionImageMexique(MouseEvent e) {
		if (btnMexique.contains(e.getX(), e.getY())) {
			type = TypePiste.MEXIQUE;
			imageActuelle = OutilsImage.lireImage("PisteMexique.png");
			zoneApercuPiste.setImg(imageActuelle);
			zoneApercuPiste.repaint();

			Image imgDesert = OutilsImage.lireImageEtRedimensionner("mexico-building.jpg", 1600, 800);
			if (imgDesert != null) {
				lblImage.setIcon(new ImageIcon(imgDesert));
				imgDesert.flush();
			}
		}
	}

	/**
	 * Méthode qui choisie la piste et une photo de fond lorsque la souris et sur le
	 * bouton
	 * 
	 * @param e evenement de la souris
	 */
	// Alexis Pineda-Alvarado
	public void selectionImageItalie(MouseEvent e) {
		if (btnItalie.contains(e.getX(), e.getY())) {
			type = TypePiste.ITALIE;
			imageActuelle = OutilsImage.lireImage("pisteItalie.PNG");
			zoneApercuPiste.setImg(imageActuelle);
			zoneApercuPiste.repaint();

			Image imgVenice = OutilsImage.lireImageEtRedimensionner("italie-rome.jpg", 1600, 800);
			if (imgVenice != null) {
				lblImage.setIcon(new ImageIcon(imgVenice));
				imgVenice.flush();
			}
		}
	}
}
