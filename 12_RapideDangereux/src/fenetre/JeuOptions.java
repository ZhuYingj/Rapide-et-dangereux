package fenetre;

import java.awt.Color;
import java.awt.Font;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import application.OutilsImage;
import interfaces.TypePiste;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import dessin.ZoneAnimPhysique;
import geometrie.Vecteur2D;

/**
 * Classe qui crée la fenêtre pour choisir les paramètres pour le mode de jeu
 * Monde
 * 
 * @author Alexis Pineda-Alvarado
 * @author Ludovic Julien
 * @author Tan Tommy Rin
 * @author Kevin Nguyen
 */

public class JeuOptions extends JPanel {
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final ButtonGroup buttonGroupDiff = new ButtonGroup();
	private JRadioButton rdbtnFacile;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnDifficile;
	private JSlider slider2;
	private JSlider slider;
	private TypePiste type = TypePiste.MEXIQUE;
	private Image imageActuelle;
	private int indexCouleur = 0;
	private int indexCouleur2 = 0;
	private int indexMateriel = 0;
	private int couleurPiste = 0;
	private JLabel lblImage;
	private JButton btnCanada;
	private JButton btnMexique;
	private JButton btnItalie;
	private JSlider sliderNbBoites;
	private Color[] couleurs = { Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE };
	private Color[] couleurs2 = { Color.cyan, Color.WHITE, Color.GRAY, Color.magenta, Color.PINK, };

	private Color[] couleursPiste = { Color.RED, Color.WHITE, Color.GRAY, Color.magenta, Color.PINK, Color.YELLOW,
			Color.CYAN, Color.GREEN, Color.BLUE, Color.ORANGE };
	private JLabel lblLongueurPiste;
	private JLabel lblNewLabel_2;
	private JButton btnGauche2;
	private JButton btnGauche1;
	private JButton btnDroit1;
	private JButton btnDroit2;
	private JTextArea txtArea;
	private JComboBox cbMatPiste;
	private JLabel lblFlecheBasImage;
	private JLabel lblNewLabel_2_1;
	private ZoneAnimPhysique zoneAnimPhysique;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblCouleurBordureDe;
	private JButton btnGauche3;
	private JButton btnDroit3;
	private JPanel panelCouleurPiste;
	private boolean gauche = false;
	private boolean droite = false;

	/**
	 * Méthode qui permet de placer un écouteur
	 */
	// Alexis Pineda-Alvarado
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */
	// Alexis Pineda-Alvarado
	public JeuOptions() {

		setLayout(null);
		setBounds(0, 0, 1600, 800);

		lblCouleurBordureDe = new JLabel("Couleur bordure\r\n piste");
		lblCouleurBordureDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblCouleurBordureDe.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCouleurBordureDe.setBounds(58, 226, 177, 43);
		add(lblCouleurBordureDe);

		panelCouleurPiste = new JPanel();
		panelCouleurPiste.setBackground(Color.RED);
		panelCouleurPiste.setBounds(73, 256, 143, 90);
		add(panelCouleurPiste);

		lblImage = new JLabel("");
		panelCouleurPiste.add(lblImage);

		btnGauche3 = new JButton("<");
		btnGauche3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gauche = true;
				changeCouleurPiste();
				gauche = false;
			}
		});

		btnGauche3.setBounds(10, 290, 55, 23);
		add(btnGauche3);

		btnDroit3 = new JButton(">");
		btnDroit3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				droite = true;
				changeCouleurPiste();
				droite = false;
			}
		});

		btnDroit3.setBounds(226, 290, 55, 23);
		add(btnDroit3);

		JPanel panelPourMessage = new JPanel();
		panelPourMessage.setBounds(1020, 200, 549, 134);
		add(panelPourMessage);
		panelPourMessage.setLayout(null);

		JScrollPane spPourMessage = new JScrollPane();
		spPourMessage.setBounds(0, 0, 549, 134);
		panelPourMessage.add(spPourMessage);

		txtArea = new JTextArea();
		spPourMessage.setViewportView(txtArea);
		txtArea.setEditable(false);
		txtArea.setForeground(Color.RED);
		txtArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtArea.setText("Choisisez les paramètres!");
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);


		btnMexique = new JButton("Mexique");
		btnMexique.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnMexique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectionImageMexique(e);

			}
		});

		btnMexique.setBounds(424, 74, 126, 78);
		add(btnMexique);

		Image imgMexique = OutilsImage.lireImageEtRedimensionner("mexicano.png", 140, 77);
		if (imgMexique != null) {
			btnMexique.setIcon(new ImageIcon(imgMexique));
			imgMexique.flush();
		}

		btnCanada = new JButton("Canada");
		btnCanada.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnCanada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectionImageCanada(e);
			}
		});
		btnCanada.setBounds(578, 74, 126, 78);
		add(btnCanada);

		Image imgCanada = OutilsImage.lireImageEtRedimensionner("canada.png", 140, 77);
		if (imgCanada != null) {
			btnCanada.setIcon(new ImageIcon(imgCanada));
			imgCanada.flush();
		}

		btnItalie = new JButton("Italie");
		btnItalie.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnItalie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectionImageItalie(e);
			}
		});
		btnItalie.setBounds(735, 74, 126, 78);
		add(btnItalie);

		Image imgItalia = OutilsImage.lireImageEtRedimensionner("italie-flag.jpg", 140, 77);
		if (imgItalia != null) {
			btnItalie.setIcon(new ImageIcon(imgItalia));
			imgItalia.flush();
		}

		Icon feuVert = new ImageIcon("green.jpg");
		JLabel feuGreen = new JLabel();
		feuGreen.setIcon(feuVert);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 0, 0), 2, true));
		Color a = new Color(240, 240, 240);
		panel_1.setBackground(a);
		panel_1.setBounds(1020, 345, 549, 309);
		add(panel_1);
		panel_1.setLayout(null);

		slider2 = new JSlider();
		slider2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				actionSliderMasse2();
			}
		});
		slider2.setSnapToTicks(true);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		slider2.setMinorTickSpacing(10);
		slider2.setMinimum(50);
		slider2.setMajorTickSpacing(10);
		slider2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slider2.setBounds(165, 84, 343, 40);
		panel_1.add(slider2);

		slider = new JSlider();

		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				actionSliderMasse1();
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

		JLabel lblMasse = new JLabel("Masse de la voiture 1 en kg : ");
		lblMasse.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblMasse.setBounds(10, 39, 191, 20);
		panel_1.add(lblMasse);

		JLabel lblDifficulte = new JLabel("Difficulté du jeu : ");
		lblDifficulte.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblDifficulte.setBounds(10, 182, 110, 14);
		panel_1.add(lblDifficulte);

		rdbtnFacile = new JRadioButton("Facile");
		rdbtnFacile.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		rdbtnFacile.setSelected(true);
		rdbtnFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRdbtnVitesseFacile();

			}
		});
		rdbtnFacile.setBounds(165, 182, 109, 23);
		panel_1.add(rdbtnFacile);
		buttonGroupDiff.add(rdbtnFacile);

		rdbtnMedium = new JRadioButton("Intermédiaire");
		rdbtnMedium.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRdbtnVitesseIntermediaire();

			}
		});
		rdbtnMedium.setBounds(165, 207, 109, 23);
		panel_1.add(rdbtnMedium);
		buttonGroupDiff.add(rdbtnMedium);

		rdbtnDifficile = new JRadioButton("Avancé");
		rdbtnDifficile.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		rdbtnDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRdbtnVitesseAvance();

			}
		});
		rdbtnDifficile.setBounds(165, 233, 109, 23);
		panel_1.add(rdbtnDifficile);
		buttonGroupDiff.add(rdbtnDifficile);

		JLabel lblVitesseFacile = new JLabel("60 m/s");
		lblVitesseFacile.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblVitesseFacile.setBounds(280, 186, 46, 14);
		panel_1.add(lblVitesseFacile);

		JLabel lblVitesseIntermediaire = new JLabel("80 m/s");
		lblVitesseIntermediaire.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblVitesseIntermediaire.setBounds(280, 211, 46, 14);
		panel_1.add(lblVitesseIntermediaire);

		JLabel lblVitesseAvance = new JLabel("100 m/s");
		lblVitesseAvance.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblVitesseAvance.setBounds(280, 237, 46, 14);
		panel_1.add(lblVitesseAvance);

		JLabel lblMasse2 = new JLabel("Masse de la voiture 2 en kg : ");
		lblMasse2.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblMasse2.setBounds(10, 86, 177, 20);
		panel_1.add(lblMasse2);

		JLabel lblNombreBoiteMystere = new JLabel("Nombre de boite mystere : ");
		lblNombreBoiteMystere.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblNombreBoiteMystere.setBounds(10, 135, 159, 14);
		panel_1.add(lblNombreBoiteMystere);

		sliderNbBoites = new JSlider();
		sliderNbBoites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				actionSliderBloc();
			}
		});

		sliderNbBoites.setSnapToTicks(true);
		sliderNbBoites.setPaintTicks(true);
		sliderNbBoites.setPaintLabels(true);
		sliderNbBoites.setValue(3);
		sliderNbBoites.setMinorTickSpacing(1);
		sliderNbBoites.setMaximum(12);
		sliderNbBoites.setMinimum(3);
		sliderNbBoites.setMajorTickSpacing(1);
		sliderNbBoites.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sliderNbBoites.setBounds(165, 135, 343, 40);
		panel_1.add(sliderNbBoites);

		JLabel lblVitesseMaximale = new JLabel("Vitesse maximale");
		lblVitesseMaximale.setFont(new Font("Comic Sans MS", Font.PLAIN, 9));
		lblVitesseMaximale.setBounds(20, 191, 80, 14);
		panel_1.add(lblVitesseMaximale);

		cbMatPiste = new JComboBox();

		cbMatPiste.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));

		cbMatPiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cbMatPiste.setModel(new DefaultComboBoxModel(new String[] { "Asphalt", "Sable", "Glace" }));
		cbMatPiste.setBounds(165, 263, 75, 22);
		cbMatPiste.setSelectedItem("Asphalt");
		panel_1.add(cbMatPiste);

		JLabel lblMatPiste = new JLabel("Matériel de la piste :");
		lblMatPiste.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblMatPiste.setBounds(10, 261, 110, 14);
		panel_1.add(lblMatPiste);

		lblImage = new JLabel("");
		lblImage.setBounds(-815, -417, 1600, 800);
		panel_1.add(lblImage);

		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCommencer();
				actionSkin();

			}
		});
		btnCommencer.setBounds(549, 691, 237, 29);
		add(btnCommencer);

		JPanel panel_V1 = new JPanel();
		panel_V1.setBackground(Color.YELLOW);
		panel_V1.setBounds(73, 378, 143, 90);
		add(panel_V1);

		JButton btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		JPanel panel_V2 = new JPanel();

		panel_V2.setBounds(73, 504, 143, 90);
		panel_V2.setBackground(Color.CYAN);
		add(panel_V2);

		lblNewLabel = new JLabel("Couleur voiture #1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel.setBounds(73, 360, 134, 13);
		add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Couleur Voiture #2");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(73, 479, 143, 13);
		add(lblNewLabel_1);

		JButton btnGauche1 = new JButton("<");
		btnGauche1.setBounds(10, 416, 55, 23);
		btnGauche1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(1, 0);

				panel_V1.setBackground(couleurs[indexCouleur]);

				repaint();

				setBackgroundV1(panel_V1);

			}
		});
		add(btnGauche1);

		btnGauche2 = new JButton("<");
		btnGauche2.setBounds(10, 532, 55, 23);
		btnGauche2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(0, 0);

				panel_V2.setBackground(couleurs2[indexCouleur2]);

				repaint();

				setBackgroundV2(panel_V2);

			}
		});
		add(btnGauche2);

		btnDroit1 = new JButton(">");
		btnDroit1.setBounds(226, 416, 55, 23);

		btnDroit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				changementImage(1, 1);

				panel_V1.setBackground(couleurs[indexCouleur]);

				repaint();

			}
		});
		add(btnDroit1);

		btnDroit2 = new JButton(">");
		btnDroit2.setBounds(226, 532, 55, 23);
		btnDroit2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(0, 1);

				panel_V2.setBackground(couleurs2[indexCouleur2]);
				repaint();

				setBackgroundV2(panel_V2);

			}
		});
		add(btnDroit2);

		JButton btnRecorsPiste = new JButton("Records par piste !");
		btnRecorsPiste.setForeground(new Color(0, 0, 0));
		btnRecorsPiste.setBackground(Color.CYAN);
		btnRecorsPiste.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnRecorsPiste.setBounds(58, 73, 158, 78);
		btnRecorsPiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("RECORD", null, -1);
			}
		});
		add(btnRecorsPiste);

		lblFlecheBasImage = new JLabel(
				"<------------------------------------------------------------------------------------->");
		lblFlecheBasImage.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblFlecheBasImage.setBounds(300, 638, 700, 23);
		add(lblFlecheBasImage);

		lblLongueurPiste = new JLabel("640 m");
		lblLongueurPiste.setForeground(Color.DARK_GRAY);
		lblLongueurPiste.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		lblLongueurPiste.setBounds(615, 651, 89, 29);
		add(lblLongueurPiste);

		lblNewLabel_2 = new JLabel("MONDE");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 44));
		lblNewLabel_2.setBounds(709, 0, 220, 43);
		add(lblNewLabel_2);

		zoneAnimPhysique = new ZoneAnimPhysique();
		zoneAnimPhysique.setBounds(300, 200, 700, 439);
		add(zoneAnimPhysique);

		lblNewLabel_2_1 = new JLabel("Aperçue piste");
		lblNewLabel_2_1.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 34));
		lblNewLabel_2_1.setBounds(540, 136, 246, 70);
		add(lblNewLabel_2_1);

		lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 1600, 800);
		add(lblImage);

	}

	/**
	 * Méthode qui permet d'envoyer des informations à la zone physique à l'aide de
	 * levée d'évènements.
	 * 
	 */
	// Tan Tommy Rin
	private void actionCommencer() {
		pcs.firePropertyChange("COMMENCER!", null, -1);
		pcs.firePropertyChange("MASSE1", null, (double) slider.getValue());
		pcs.firePropertyChange("MASSE2", null, (double) slider2.getValue());
		pcs.firePropertyChange("TYPEPISTE", null, type);
		pcs.firePropertyChange("NBBOITE", null, (double) sliderNbBoites.getValue());
		pcs.firePropertyChange("COULEURPISTE", null, couleursPiste[couleurPiste]);

	}

	/**
	 * Méthode qui choisie la piste et une photo de fond lorsque la souris et sur le
	 * bouton
	 * 
	 * @param e evenement de la souris
	 */
	// Alexis Pineda-Alvarado
	private void selectionImageCanada(MouseEvent e) {
		if (btnCanada.contains(e.getX(), e.getY())) {
			txtArea.append("\nVous avez choisi la piste Canada!");
			type = TypePiste.CANADA;
			imageActuelle = OutilsImage.lireImage("PisteCanada.png");

			zoneAnimPhysique.setTypePiste(type);
			zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getVoiture().setPosition(
					(new Vecteur2D(zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getX(),
							zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getY() + 10)));
			zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getVoiture2().setPosition(
					(new Vecteur2D(zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getX(),
							zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getY() + 50)));
			changeCouleurPiste();

			Image imgHiver = OutilsImage.lireImageEtRedimensionner("canadaWinter.jpg", 1600, 800);
			if (imgHiver != null) {
				lblImage.setIcon(new ImageIcon(imgHiver));
				imgHiver.flush();
			}
			lblLongueurPiste.setForeground(Color.white);
			lblFlecheBasImage.setForeground(Color.white);
			lblNewLabel_2_1.setForeground(Color.white);
			lblNewLabel_2.setForeground(Color.white);
			lblNewLabel.setForeground(Color.white);
			lblNewLabel_1.setForeground(Color.white);
			lblCouleurBordureDe.setForeground(Color.white);
		}
	}

	/**
	 * Méthode qui choisie la piste et une photo de fond lorsque la souris et sur le
	 * bouton
	 * 
	 * @param e evenement de la souris
	 */
	// Alexis Pineda-Alvarado
	private void selectionImageMexique(MouseEvent e) {
		if (btnMexique.contains(e.getX(), e.getY())) {
			type = TypePiste.MEXIQUE;
			txtArea.append("\nVous avez choisi la piste Mexique!");
			imageActuelle = OutilsImage.lireImage("PisteMexique.png");
			zoneAnimPhysique.setTypePiste(type);
			zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getVoiture().setPosition(
					(new Vecteur2D(zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getX(),
							zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getY() + 10)));
			zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getVoiture2().setPosition(
					(new Vecteur2D(zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getX(),
							zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getY() + 50)));
			changeCouleurPiste();

			Image imgDesert = OutilsImage.lireImageEtRedimensionner("mexico-building.jpg", 1600, 800);
			if (imgDesert != null) {
				lblImage.setIcon(new ImageIcon(imgDesert));
				imgDesert.flush();
			}
			lblLongueurPiste.setForeground(Color.white);
			lblFlecheBasImage.setForeground(Color.white);
			lblNewLabel_2_1.setForeground(Color.white);
			lblNewLabel_2.setForeground(Color.white);
			lblNewLabel.setForeground(Color.white);
			lblNewLabel_1.setForeground(Color.white);
			lblCouleurBordureDe.setForeground(Color.white);

			;
		}
	}

	/**
	 * Méthode qui choisie la piste et une photo de fond lorsque la souris et sur le
	 * bouton
	 * 
	 * @param e evenement de la souris
	 */
	// Alexis Pineda-Alvarado
	private void selectionImageItalie(MouseEvent e) {
		if (btnItalie.contains(e.getX(), e.getY())) {
			type = TypePiste.ITALIE;
			txtArea.append("\nVous avez choisi la piste Italie!");
			imageActuelle = OutilsImage.lireImage("pisteItalie.PNG");

			zoneAnimPhysique.setTypePiste(type);
			zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getVoiture().setPosition(
					(new Vecteur2D(zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getX(),
							zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getY() + 10)));
			zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getVoiture2().setPosition(
					(new Vecteur2D(zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getX(),
							zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getY() + 50)));
			changeCouleurPiste();

			Image imgVenice = OutilsImage.lireImageEtRedimensionner("italie-rome.jpg", 1600, 800);
			if (imgVenice != null) {
				lblImage.setIcon(new ImageIcon(imgVenice));
				imgVenice.flush();
			}
			lblLongueurPiste.setForeground(Color.white);
			lblFlecheBasImage.setForeground(Color.white);
			lblNewLabel_2_1.setForeground(Color.white);
			lblNewLabel_2.setForeground(Color.white);
			lblNewLabel.setForeground(Color.white);
			lblNewLabel_1.setForeground(Color.white);
			lblCouleurBordureDe.setForeground(Color.white);

		}
	}

	/**
	 * Méthode qui permet d'envoyer des informations à la zone physique à l'aide de
	 * levée d'évènements. pour faire le changement de couleur des voiture
	 */
	// Ludovic Julien
	private void actionSkin() {
		pcs.firePropertyChange("SKIN", null, couleurs[indexCouleur]);
		pcs.firePropertyChange("SKIN2", null, couleurs2[indexCouleur2]);

	}

	/**
	 * méthode qui permet le changement de couleur des voiture
	 * 
	 * @param voiture   voiture 1 ou 2
	 * @param direction changer de couleur vers la droite ou vers la geuche
	 */
	// Ludovic Julien
	private void changementImage(int voiture, int direction) {

		if (voiture == 1) {
			if (direction == 1) {
				indexCouleur++;
				if (indexCouleur == couleurs.length) {
					indexCouleur = 0;
				}
			} else {
				indexCouleur--;
				if (indexCouleur < 0) {
					indexCouleur = couleurs.length - 1;
				}
			}
		} else {
			if (direction == 1) {
				indexCouleur2++;
				if (indexCouleur2 == couleurs2.length) {
					indexCouleur2 = 0;
				}
			} else {
				indexCouleur2--;
				if (indexCouleur2 < 0) {
					indexCouleur2 = couleurs2.length - 1;
				}
			}

		}
		pcs.firePropertyChange("SKINOPTIONS1", null, couleurs[indexCouleur]);
		pcs.firePropertyChange("SKINOPTIONS2", null, couleurs2[indexCouleur2]);
	}

	/**
	 * Méthode pour changer la couleur des côtés de piste
	 */
	// Kevin Nguyen
	private void changeCouleurPiste() {
		if (gauche) {
			couleurPiste--;
			if (couleurPiste < 0) {
				couleurPiste = couleursPiste.length - 1;
			}
		}
		if (droite) {
			couleurPiste++;
			if (couleurPiste == couleursPiste.length) {
				couleurPiste = 0;
			}
		}
		panelCouleurPiste.setBackground(couleursPiste[couleurPiste]);
		pcs.firePropertyChange("COULEURPISTE2", null, couleursPiste[couleurPiste]);
		repaint();
	}

	/**
	 * méthode qui dicte le message du slider de la masse de la première voiture
	 */

	// Alexis Pineda-Alvarado
	public void actionSliderMasse1() {
		txtArea.append("\nVous avez choisi " + slider.getValue() + "kg pour la masse de la première voiture!");

	}

	/**
	 * méthode qui dicte le message du slider de la masse de la deuxième voiture
	 */
	// Alexis Pineda-Alvarado

	public void actionSliderMasse2() {
		txtArea.append("\nVous avez choisi " + slider2.getValue() + "kg pour la masse de la deuxième voiture!");
	}

	/**
	 * méthode qui dicte le message du slider du nombre de boîte mystère choisi
	 */
	// Alexis Pineda-Alvarado
	private void actionSliderBloc() {
		txtArea.append("\nVous avez changé le nombre de boîte à " + sliderNbBoites.getValue() + " !");
		changementPisteSelonBoite();

	}

	/**
	 * Méthode qui actualise l'apercu de la piste lorsque l'on change le nombre de
	 * boite
	 */
	// Tan Tommy Rin
	private void changementPisteSelonBoite() {
		zoneAnimPhysique.getRegroupement().getRegroupementBoiteMystere().clear();
		zoneAnimPhysique.getRegroupement().setNombreBoiteMystere(sliderNbBoites.getValue());
		zoneAnimPhysique.getRegroupement().creeBoiteDansListe();
		repaint();
	}

	/**
	 * méthode qui dicte le message et l'événement du radio button de la vitesse
	 * choisi
	 */
	// Alexis Pineda-Alvarado
	private void actionRdbtnVitesseFacile() {
		txtArea.append("\nVous avez selectionné la difficulté <<Facile>> ");
		pcs.firePropertyChange("VITESSEMAXFACILE", null, 60.0);
	}

	/**
	 * méthode qui dicte le message et l'événement du radio button de la vitesse
	 * choisi
	 */
	// Alexis Pineda-Alvarado
	private void actionRdbtnVitesseIntermediaire() {
		txtArea.append("\nVous avez selectionné la difficulté <<Intermédiaire>> ");
		pcs.firePropertyChange("VITESSEMAXFACILE", null, 80.0);
	}

	/**
	 * méthode qui dicte le message et l'événement du radio button de la vitesse
	 * choisi
	 */
	// Alexis Pineda-Alvarado
	private void actionRdbtnVitesseAvance() {
		txtArea.append("\nVous avez selectionné la difficulté <<Avancé>> ");
		pcs.firePropertyChange("VITESSEMAXFACILE", null, 100.0);
	}

	/**
	 * méthode qui change la couleur du panel pour permettre de visualiser la
	 * couleur choisit par l'utilisateur
	 * 
	 * @param panel panel a changer la couleur pour la voiture 1
	 */

	// Ludovic Julien
	public void setBackgroundV1(JPanel panel) {
		panel.setBackground(couleurs[indexCouleur]);
	}

	/**
	 * méthode qui change la couleur du panel pour permettre de visualiser la
	 * couleur choisit par l'utilisateur
	 * 
	 * @param panel panel a changer la couleur pour la voiture 2
	 */
	// Ludovic Julien
	public void setBackgroundV2(JPanel panel) {
		panel.setBackground(couleurs2[indexCouleur2]);

	}

	public ZoneAnimPhysique getZoneAnimPhysique() {
		return zoneAnimPhysique;
	}

	public void setZoneAnimPhysique(ZoneAnimPhysique zoneAnimPhysique) {
		this.zoneAnimPhysique = zoneAnimPhysique;
	}
}
