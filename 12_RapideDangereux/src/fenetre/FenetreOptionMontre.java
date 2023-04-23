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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import application.OutilsImage;
import dessin.ZoneAnimPhysique;
import geometrie.Vecteur2D;
import interfaces.TypePiste;

/**
 * 
 * Classe qui crée la fenetre pour les paramètres a choisir pour le mode de jeu
 * course contre la montre
 * 
 * @author Alexis Pineda-Alvarado
 * @author Ludovic Julien
 * @author Kevin Nguyen
 *
 */

public class FenetreOptionMontre extends JPanel {

	private TypePiste type = TypePiste.MEXIQUE;
	private Image imageActuelle;
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
	private JComboBox cbMatPiste;
	private int indexCouleur = 0;
	private int indexCouleur2 = 0;
	private int couleurPiste = 0;
	private int couleurMatPiste = 0;
	private Color[] couleurs = { Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE };
	private Color[] couleurs2 = { Color.cyan, Color.WHITE, Color.GRAY, Color.magenta, Color.PINK };
	private Color[] couleursPiste = { Color.RED, Color.WHITE, Color.GRAY, Color.magenta, Color.PINK, Color.YELLOW,
			Color.CYAN, Color.GREEN, Color.BLUE, Color.ORANGE };
	private Color[] couleursMaterielPiste = { new Color(194, 178, 128), new Color(128, 126, 120),
			new Color(185, 232, 234) };
	private JTextArea txtArea;
	private boolean gauche = false;
	private boolean droite = false;
	private JPanel panelCouleurPiste;
	private ZoneAnimPhysique zoneAnimPhysique;
	private JLabel lblNombreTours;
	private JLabel lblFlecheBasImage;
	private JLabel lblNombreToursMsg;
	private JLabel lblCouleurBordureDe;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel2;
	private JLabel lblNewLabel3;
	private JLabel lblLongueurPiste;
	private JLabel lblTitre;

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 * 
	 * @param listener L'écouteur
	 */
	// Alexis Pineda-Alvarado
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Create the panel.
	 */
	// Alexis Pineda-Alvarado
	public FenetreOptionMontre() {
		setLayout(null);
		setBounds(0, 0, 1600, 800);
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});

		lblNombreTours = new JLabel("1");
		lblNombreTours.setForeground(Color.BLACK);
		lblNombreTours.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 32));
		lblNombreTours.setBounds(1173, 124, 98, 85);
		add(lblNombreTours);

		lblNombreToursMsg = new JLabel("TOURS À FAIRE");
		lblNombreToursMsg.setForeground(Color.BLACK);
		lblNombreToursMsg.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblNombreToursMsg.setBounds(1203, 136, 194, 71);
		add(lblNombreToursMsg);

		lblCouleurBordureDe = new JLabel("Couleur bordure\r\n piste");
		lblCouleurBordureDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblCouleurBordureDe.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblCouleurBordureDe.setBounds(58, 226, 177, 43);
		add(lblCouleurBordureDe);

		JButton btnGauche3 = new JButton("<");
		btnGauche3.setBounds(10, 290, 55, 23);
		add(btnGauche3);

		btnGauche3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				gauche = true;
				changeCouleurPiste();
				gauche = false;
			}
		});

		JButton btnDroit3 = new JButton(">");
		btnDroit3.setBounds(226, 290, 55, 23);
		add(btnDroit3);

		btnDroit3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				droite = true;
				changeCouleurPiste();
				droite = false;
			}
		});

		panelCouleurPiste = new JPanel();
		panelCouleurPiste.setBackground(Color.RED);
		panelCouleurPiste.setBounds(73, 256, 143, 90);
		add(panelCouleurPiste);

		JLabel lblImage3 = new JLabel("");
		panelCouleurPiste.add(lblImage3);
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		zoneAnimPhysique = new ZoneAnimPhysique();
		zoneAnimPhysique.setBounds(300, 200, 700, 439);
		add(zoneAnimPhysique);

		JPanel panelPourMessage = new JPanel();
		panelPourMessage.setBounds(1020, 200, 549, 134);
		add(panelPourMessage);
		panelPourMessage.setLayout(null);

		JScrollPane spPourMessage = new JScrollPane();
		spPourMessage.setBounds(0, 0, 549, 134);
		panelPourMessage.add(spPourMessage);

		lblTitre = new JLabel("Course Contre La Montre");
		lblTitre.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 44));
		lblTitre.setBounds(537, 0, 540, 70);
		add(lblTitre);

		txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setForeground(Color.RED);
		txtArea.setFont(new Font("Dubai", Font.PLAIN, 18));
		txtArea.setText("Choisisez les paramètres!");
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		spPourMessage.setViewportView(txtArea);

		btnCanada = new JButton("Canada");
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

		btnMexique = new JButton("Mexique");
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

		JPanel panel2 = new JPanel();
		panel2.setBorder(new LineBorder(new Color(255, 0, 0), 2));
		panel2.setBounds(1020, 345, 549, 309);
		add(panel2);
		panel2.setLayout(null);

		rdbtnFacile = new JRadioButton("Facile");
		rdbtnFacile.setSelected(true);
		rdbtnFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRdbtnVitesseFacile();
			}
		});
		rdbtnFacile.setBounds(165, 182, 109, 23);
		panel2.add(rdbtnFacile);
		buttonGroupDiff.add(rdbtnFacile);

		rdbtnMedium = new JRadioButton("Intermédiaire");
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRdbtnVitesseIntermediaire();
			}
		});
		rdbtnMedium.setBounds(165, 207, 109, 23);
		panel2.add(rdbtnMedium);
		buttonGroupDiff.add(rdbtnMedium);

		rdbtnDifficile = new JRadioButton("Avancé");
		rdbtnDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionRdbtnVitesseAvance();
			}
		});
		rdbtnDifficile.setBounds(165, 233, 109, 23);
		panel2.add(rdbtnDifficile);
		buttonGroupDiff.add(rdbtnDifficile);

		JLabel lblDifficulte = new JLabel("Difficulté du jeu : ");
		lblDifficulte.setBounds(10, 182, 110, 14);
		panel2.add(lblDifficulte);

		slider = new JSlider();
		slider.addMouseListener(new MouseAdapter() {
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
		panel2.add(slider);

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
		panel2.add(slider2);

		sliderNbrTour = new JSlider();
		sliderNbrTour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				actionSliderNbrTour();
			}
		});
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
		panel2.add(sliderNbrTour);

		cbMatPiste = new JComboBox();
		cbMatPiste.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		cbMatPiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbMatPiste.getSelectedIndex() == 0) {
					couleurMatPiste = 1;
					couleurPisteAsphalt();
					actionCbAsphalt();
					repaint();
				}
				if (cbMatPiste.getSelectedIndex() == 1) {
					couleurMatPiste = 0;
					couleurPisteSable();
					actionCbSable();
					repaint();
				}
				if (cbMatPiste.getSelectedIndex() == 2) {
					couleurMatPiste = 2;
					couleurPisteGlace();
					actionCbGlace();
					repaint();
				}

			}
		});
		cbMatPiste.setModel(new DefaultComboBoxModel(new String[] { "Asphalt", "Sable", "Glace" }));
		cbMatPiste.setBounds(165, 263, 75, 22);
		cbMatPiste.setSelectedItem("Asphalt");
		panel2.add(cbMatPiste);

		JLabel lblMasse = new JLabel("Masse de la voiture 1 en kg : ");
		lblMasse.setBounds(10, 39, 191, 20);
		panel2.add(lblMasse);

		JLabel lblMasse2 = new JLabel("Masse de la voiture 2 en kg : ");
		lblMasse2.setBounds(10, 86, 177, 20);
		panel2.add(lblMasse2);

		JLabel lblNbrTour = new JLabel("Nombre de tour : \r\n");
		lblNbrTour.setBounds(10, 135, 177, 14);
		panel2.add(lblNbrTour);

		JLabel lblVitesseFacile = new JLabel("60 m/s");
		lblVitesseFacile.setBounds(280, 186, 46, 14);
		panel2.add(lblVitesseFacile);

		JLabel lblVitesseMedium = new JLabel("80 m/s");
		lblVitesseMedium.setBounds(280, 211, 65, 14);
		panel2.add(lblVitesseMedium);

		lblVitesseDifficile = new JLabel("100 m/s");
		lblVitesseDifficile.setBounds(280, 237, 65, 14);
		panel2.add(lblVitesseDifficile);

		lblNewLabel = new JLabel("Couleur voiture #1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel.setBounds(73, 360, 134, 13);
		add(lblNewLabel);

		lblNewLabel2 = new JLabel("Couleur Voiture #2");
		lblNewLabel2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setBounds(73, 479, 143, 13);
		add(lblNewLabel2);

		lblNewLabel3 = new JLabel("Aperçue piste");
		lblNewLabel3.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 34));
		lblNewLabel3.setBounds(540, 136, 246, 70);
		add(lblNewLabel3);

		lblFlecheBasImage = new JLabel(
				"<------------------------------------------------------------------------------------->");
		lblFlecheBasImage.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblFlecheBasImage.setBounds(300, 638, 700, 23);
		add(lblFlecheBasImage);

		JPanel panelV1 = new JPanel();
		panelV1.setBackground(Color.YELLOW);
		panelV1.setBounds(73, 378, 143, 90);
		add(panelV1);

		JPanel panelV2 = new JPanel();
		panelV2.setBackground(Color.CYAN);
		panelV2.setBounds(73, 504, 143, 90);
		add(panelV2);

		JButton btnGauche1 = new JButton("<");
		btnGauche1.setBounds(10, 416, 55, 23);
		btnGauche1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(1, 0);
				setBackgroundV1(panelV1);
				repaint();
			}
		});
		add(btnGauche1);

		JButton btnDroite1 = new JButton(">");
		btnDroite1.setBounds(226, 416, 55, 23);
		btnDroite1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changementImage(1, 1);
				setBackgroundV1(panelV1);
				repaint();
			}
		});
		add(btnDroite1);

		JButton btnGauche2 = new JButton("<");
		btnGauche2.setBounds(10, 532, 55, 23);
		btnGauche2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(0, 0);
				setBackgroundV2(panelV2);
				repaint();
			}
		});
		add(btnGauche2);

		JButton btnDroite2 = new JButton(">");
		btnDroite2.setBounds(226, 532, 55, 23);
		btnDroite2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(0, 1);
				setBackgroundV2(panelV2);
				repaint();
			}
		});
		add(btnDroite2);

		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCommencer();
				changeMaterielPiste();
			}
		});
		btnCommencer.setBounds(549, 691, 237, 29);
		add(btnCommencer);

		lblLongueurPiste = new JLabel("640 m");
		lblLongueurPiste.setForeground(Color.DARK_GRAY);
		lblLongueurPiste.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		lblLongueurPiste.setBounds(606, 650, 89, 29);
		add(lblLongueurPiste);

		lblImage = new JLabel("");
		lblImage.setBounds(10, 0, 1600, 800);
		add(lblImage);

		JLabel lblImage2 = new JLabel("");
		lblImage2.setBounds(19, 81, 1600, 800);
		add(lblImage2);

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
			couleurPisteAsphalt();
			couleurPisteSable();
			couleurPisteGlace();

			Image imgHiver = OutilsImage.lireImageEtRedimensionner("canadaWinter.jpg", 1600, 800);
			if (imgHiver != null) {
				lblImage.setIcon(new ImageIcon(imgHiver));
				imgHiver.flush();
			}
			lblNombreToursMsg.setForeground(Color.white);
			lblCouleurBordureDe.setForeground(Color.white);
			lblNewLabel.setForeground(Color.white);
			lblNewLabel2.setForeground(Color.white);
			lblNewLabel3.setForeground(Color.white);
			lblLongueurPiste.setForeground(Color.white);
			lblNombreTours.setForeground(Color.white);
			lblFlecheBasImage.setForeground(Color.white);
			lblTitre.setForeground(Color.white);
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
			couleurPisteAsphalt();
			couleurPisteSable();
			couleurPisteGlace();

			Image imgDesert = OutilsImage.lireImageEtRedimensionner("mexico-building.jpg", 1600, 800);
			if (imgDesert != null) {
				lblImage.setIcon(new ImageIcon(imgDesert));
				imgDesert.flush();
			}
			lblNombreToursMsg.setForeground(Color.white);
			lblCouleurBordureDe.setForeground(Color.white);
			lblNewLabel.setForeground(Color.white);
			lblNewLabel2.setForeground(Color.white);
			lblNewLabel3.setForeground(Color.white);
			lblLongueurPiste.setForeground(Color.white);
			lblNombreTours.setForeground(Color.white);
			lblFlecheBasImage.setForeground(Color.white);
			lblTitre.setForeground(Color.white);
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
			couleurPisteAsphalt();
			couleurPisteSable();
			couleurPisteGlace();

			Image imgVenice = OutilsImage.lireImageEtRedimensionner("italie-rome.jpg", 1600, 800);
			if (imgVenice != null) {
				lblImage.setIcon(new ImageIcon(imgVenice));
				imgVenice.flush();
			}
			lblNombreToursMsg.setForeground(Color.white);
			lblCouleurBordureDe.setForeground(Color.white);
			lblNewLabel.setForeground(Color.white);
			lblNewLabel2.setForeground(Color.white);
			lblNewLabel3.setForeground(Color.white);
			lblLongueurPiste.setForeground(Color.white);
			lblNombreTours.setForeground(Color.white);
			lblFlecheBasImage.setForeground(Color.white);
			lblTitre.setForeground(Color.white);
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
	 * méthode qui permet le changement de couleur de la liste de couleur
	 * 
	 * @param voiture   voiture 1 ou 2
	 * @param direction changer de couleur vers la droite ou vers la geuche
	 */
	// Ludovic Julien
	public void changementImage(int voiture, int direction) {

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
	 * Méthode qui permet d'envoyer des informations à la zone physique à l'aide de
	 * levée d'évènements.
	 * 
	 */
	// Alexis Pineda-Alvarado
	public void actionCommencer() {
		pcs.firePropertyChange("COMMENCER COURSE MONTRE", null, -1);
		pcs.firePropertyChange("TYPEPISTE", null, type);
		pcs.firePropertyChange("MASSEMONTRE1", null, (double) slider.getValue());
		pcs.firePropertyChange("MASSEMONTRE2", null, (double) slider2.getValue());
		pcs.firePropertyChange("NBRDETOUR", null, (double) sliderNbrTour.getValue());
		pcs.firePropertyChange("COULEURPISTE", null, couleursPiste[couleurPiste]);
		actionSkin();
	}

	/**
	 * Méthode pour changer la couleur des côtés de piste
	 */
	// Kevin Nguyen
	public void changeCouleurPiste() {
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
		pcs.firePropertyChange("COULEURPISTE3", null, couleursPiste[couleurPiste]);
		repaint();
	}

	/**
	 * méthode qui dicte le message et l'événement du radio button de la vitesse
	 * choisi
	 */
	// Alexis Pineda-Alvarado
	private void actionRdbtnVitesseFacile() {
		txtArea.append("\nVous avez selectionné la difficulté <<Facile>> ");
		txtArea.append("\nVous avez 130 seconde pour terminer la course");
		pcs.firePropertyChange("VITESSEMAXFACILE2", null, 60.0);
	}

	/**
	 * méthode qui dicte le message et l'événement du radio button de la vitesse
	 * choisi
	 */
	// Alexis Pineda-Alvarado
	private void actionRdbtnVitesseIntermediaire() {
		txtArea.append("\nVous avez selectionné la difficulté <<Intermédiaire>> ");
		txtArea.append("\nVous avez 110 seconde pour terminer la course");
		pcs.firePropertyChange("VITESSEMAXINTERMEDIAIRE2", null, 80.0);
	}

	/**
	 * méthode qui dicte le message et l'événement du radio button de la vitesse
	 * choisi
	 */
	// Alexis Pineda-Alvarado
	private void actionRdbtnVitesseAvance() {
		txtArea.append("\nVous avez selectionné la difficulté <<Avancé>> ");
		txtArea.append("\nVous avez 90 seconde pour terminer la course");
		pcs.firePropertyChange("VITESSEMAXAVANCE2", null, 100.0);
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
	 * méthode qui dicte le message du slider de la masse de la première voiture
	 */

	// Alexis Pineda-Alvarado
	public void actionSliderNbrTour() {
		txtArea.append("\nVous avez choisi " + sliderNbrTour.getValue() + " tour à faire!");
		lblNombreTours.setText(sliderNbrTour.getValue() + "");

	}

	/**
	 * méthode qui permet de dicter la couleur de la piste a l'aide du changement
	 * d'événement
	 */
	// Alexis Pineda-Alvarado
	private void couleurPisteAsphalt() {
		pcs.firePropertyChange("COULEURPISTEASPHALT2", null, couleursMaterielPiste[couleurMatPiste]);
	}

	/**
	 * méthode qui permet de dicter la couleur de la piste a l'aide du changement
	 * d'événement
	 */
	// Alexis Pineda-Alvarado
	private void couleurPisteSable() {
		pcs.firePropertyChange("COULEURPISTESABLE2", null, couleursMaterielPiste[couleurMatPiste]);
	}

	/**
	 * méthode qui permet de dicter la couleur de la piste a l'aide du changement
	 * d'événement
	 */
	// Alexis Pineda-Alvarado
	private void couleurPisteGlace() {
		pcs.firePropertyChange("COULEURPISTEGLACE2", null, couleursMaterielPiste[couleurMatPiste]);
	}

	/**
	 * méthode qui fait un changement d'événement pour le frottement de la piste de
	 * l'asphalt
	 */
	// Alexis Pineda-Alvarado
	private void actionCbAsphalt() {
		pcs.firePropertyChange("MATPISTEASPHALT", null, cbMatPiste.getSelectedItem());
		txtArea.append("\nVous choisi l'asphalt où le coefficient de frottement est 0.20");
	}

	/**
	 * méthode qui fait un changement d'événement pour le frottement de la piste de
	 * sable
	 */
	// Alexis Pineda-Alvarado
	private void actionCbSable() {
		pcs.firePropertyChange("MATPISTESABLE", null, cbMatPiste.getSelectedItem());
		pcs.firePropertyChange("IMGSABLE", null, cbMatPiste.getSelectedItem());
		txtArea.append("\nVous choisi le sable où le coefficient de frottement est 0.70");
	}

	/**
	 * méthode qui fait un changement d'événement pour le frottement de la piste de
	 * glace
	 */
	// Alexis Pineda-Alvarado
	private void actionCbGlace() {
		pcs.firePropertyChange("MATPISTEGLACE", null, cbMatPiste.getSelectedItem());
		pcs.firePropertyChange("IMGGLACE", null, cbMatPiste.getSelectedItem());
		txtArea.append("\nVous choisi la glace où le coefficient de frottement est 0.02");
	}
	
	/**
	 * Méthode qui change la couleur de la piste pour simuler un changement de
	 * matériel de piste pour la course
	 */
	// Alexis Pineda-Alvarado
	private void changeMaterielPiste() {
		pcs.firePropertyChange("COULEURMATPISTE", null, couleursMaterielPiste[couleurMatPiste]);
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

	public ZoneAnimPhysique getZoneAnimPhysique() {
		return zoneAnimPhysique;
	}

	public void setZoneAnimPhysique(ZoneAnimPhysique zoneAnimPhysique) {
		this.zoneAnimPhysique = zoneAnimPhysique;
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
}
