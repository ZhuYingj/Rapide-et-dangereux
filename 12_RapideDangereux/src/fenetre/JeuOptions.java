package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import application.OutilsImage;
import dessin.ZoneApercuPiste;
import interfaces.TypePiste;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe qui crée la fenêtre pour choisir les paramètres pour le mode de jeu
 * Monde
 * 
 * @author Alexis Pineda-Alvarado
 * @author Ludovic Julien
 * @author Tan Tommy Rin
 */

public class JeuOptions extends JPanel {
	private JPanel PanelV2;
	private ZoneApercuPiste zoneApercuPiste;
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
	private JLabel lblImage;
	private JButton btnCanada;
	private JButton btnMexique;
	private JButton btnItalie;
	private JSlider sliderNbBoites;
	private Color[] couleurs = { Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE };
	private Color[] couleurs2 = { Color.cyan,Color.WHITE, Color.GRAY, Color.magenta, Color.PINK, };
	private Color transparent = new Color(255, 255, 255, 0);
	private JButton btnGauche2;
	private JButton btnGauche1;
	private JButton btnDroit1;
	private JButton btnDroit2;

	/**
	 * Méthode qui permet de placer un écouteur
	 */
//Alexis Pineda-Alvarado
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */
//Alexis Pineda-Alvarado
	public JeuOptions() {
		setLayout(null);
		setBounds(0, 0, 1600, 800);
		JPanel PanelApercu = new JPanel();
		PanelApercu.setBackground(Color.WHITE);
		PanelApercu.setBounds(10, 200, 700, 439);
		add(PanelApercu);
		PanelApercu.setLayout(null);

		zoneApercuPiste = new ZoneApercuPiste();
		zoneApercuPiste.setBounds(0, 0, 700, 439);
		PanelApercu.add(zoneApercuPiste);

		Object drapeuxMexique = OutilsImage.lireImage("PisteMexique.png");
		// Icon icone = new ImageIcon(drapeuxMexique);
		btnMexique = new JButton("Mexique");
		btnMexique.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
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

		btnCanada = new JButton("Canada");
		btnCanada.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnCanada.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectionImageCanada(e);
			}
		});
		btnCanada.setBounds(305, 77, 126, 78);
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
		btnItalie.setBounds(486, 77, 126, 78);
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
		panel_1.setBounds(1000, 345, 549, 297);
		add(panel_1);
		panel_1.setLayout(null);

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
				pcs.firePropertyChange("MASSE", null, 60.0);

			}
		});
		rdbtnFacile.setBounds(165, 182, 109, 23);
		panel_1.add(rdbtnFacile);
		buttonGroupDiff.add(rdbtnFacile);

		rdbtnMedium = new JRadioButton("Intermédiaire");
		rdbtnMedium.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pcs.firePropertyChange("VITESSEMAXINTERMEDIAIRE", null, 80.0);

			}
		});
		rdbtnMedium.setBounds(165, 207, 109, 23);
		panel_1.add(rdbtnMedium);
		buttonGroupDiff.add(rdbtnMedium);

		rdbtnDifficile = new JRadioButton("Avancé");
		rdbtnDifficile.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		rdbtnDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pcs.firePropertyChange("VITESSEMAXAVANCE", null, 100.0);

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

		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCommencer();

			}
		});
		btnCommencer.setBounds(1225, 653, 143, 36);
		add(btnCommencer);

		JPanel panel_V1 = new JPanel();
		panel_V1.setBackground(Color.YELLOW);
		panel_V1.setBounds(1200, 77, 143, 90);
		add(panel_V1);


//		JButton btnGauche = new JButton("<");

//		btnGauche = new JButton("");
//		btnGauche.setBounds(1123, 116, 55, 23);
//		btnGauche.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ChangementImage(1,0);
//				panel_V1.setBackground(couleurs[indexCouleur]);
//			}
//		});
//		add(btnGauche);
//
//		
//		JButton btnDroite = new JButton(">");
//		btnDroite.setBounds(1365, 116, 55, 23);
//		btnDroite.addActionListener(new ActionListener() {
//
//		btnGauche.setBorder(null);
//
//		Image arrowGauche = OutilsImage.lireImageEtRedimensionner("FlecheGauche.png", 50, 23);
//		if (arrowGauche != null) {
//			btnGauche.setIcon(new ImageIcon(arrowGauche));
//			arrowGauche.flush();
//		}
//
//		
//		btnDroit = new JButton("");
//		btnDroit.setForeground(new Color(255, 255, 255));
//		btnDroit.setBounds(1365, 116, 55, 23);
//		btnDroit.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ChangementImage(1,1);
//				panel_V1.setBackground(couleurs[indexCouleur]);
//			}
//		});
//		add(btnDroit);
//
//		btnDroit.setBorder(null);

//		Image arrowDroite = OutilsImage.lireImageEtRedimensionner("FlecheDroite.png", 50, 23);
//		if (arrowDroite != null) {
//			btnDroit.setIcon(new ImageIcon(arrowDroite));
//			arrowDroite.flush();
//		}

		JButton btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		PanelV2 = new JPanel();
		PanelV2.setBounds(1200, 229, 143, 90);
		PanelV2.setBackground(Color.CYAN);
		add(PanelV2);

		JLabel lblNewLabel = new JLabel("Couleur voiture #1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel.setBounds(1200, 53, 134, 13);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Couleur Voiture #2");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(1200, 205, 143, 13);
		add(lblNewLabel_1);


		JButton btnGauche1 = new JButton("<");
		btnGauche1.setBounds(1123, 116, 55, 23);
		btnGauche1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(1,0);
				panel_V1.setBackground(couleurs[indexCouleur]);
			}
		});
		add(btnGauche1);

		btnGauche2 = new JButton("<");
		btnGauche2.setBounds(1123, 264, 55, 23);
		btnGauche2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(0,0);
				PanelV2.setBackground(couleurs2[indexCouleur2]);
			}
		});
		add(btnGauche2);
		btnGauche2.setBorder(null);

		btnDroit1 = new JButton(">");
		btnDroit1.setBounds(1365, 116, 55, 23);
		//Image arrowGauche2 = OutilsImage.lireImageEtRedimensionner("FlecheGauche.png", 50, 23);
		btnDroit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//		if (arrowGauche2 != null) {
//			btnGauche2.setIcon(new ImageIcon(arrowGauche2));
//			arrowGauche2.flush();
//		}
				changementImage(1,1);
				panel_V1.setBackground(couleurs[indexCouleur]);
		}
		});
		add(btnDroit1);
		btnDroit1.setBorder(null);
		
		btnDroit2 = new JButton(">");
		btnDroit2.setBounds(1365, 264, 55, 23);
		btnDroit2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(0,1);
				PanelV2.setBackground(couleurs2[indexCouleur2]);
			}
		});
		add(btnDroit2);
		btnDroit2.setBorder(null);

//		Image arrowDroite2 = OutilsImage.lireImageEtRedimensionner("FlecheDroite.png", 50, 23);
//		if (arrowDroite2 != null) {
//			btnDroit2.setIcon(new ImageIcon(arrowDroite2));
//			arrowDroite2.flush();
//		}
		
		JButton btnRecorsPiste = new JButton("Records par piste !");
		btnRecorsPiste.setForeground(new Color(0, 0, 0));
		btnRecorsPiste.setBackground(Color.CYAN);
		btnRecorsPiste.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		btnRecorsPiste.setBounds(769, 440, 159, 78);
		btnRecorsPiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("RECORD", null, -1);
			}
		});
		add(btnRecorsPiste);

		JLabel lblFlecheBasImage = new JLabel(
				"<------------------------------------------------------------------------------------------------>");
		lblFlecheBasImage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFlecheBasImage.setBounds(10, 650, 700, 23);
		add(lblFlecheBasImage);

		JLabel lblLongueurPiste = new JLabel("640 m");
		lblLongueurPiste.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
		lblLongueurPiste.setBounds(325, 665, 89, 36);
		add(lblLongueurPiste);

		JLabel lblNewLabel_2 = new JLabel("MONDE");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 44));
		lblNewLabel_2.setBounds(769, 11, 220, 70);
		add(lblNewLabel_2);

		JLabel lblGif = new JLabel("");
		lblGif.setBounds(0, 0, 1408, 700);
		add(lblGif);

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
		pcs.firePropertyChange("SKIN", null, couleurs[indexCouleur]);
		pcs.firePropertyChange("SKIN2", null, couleurs2[indexCouleur2]);
		pcs.firePropertyChange("SKIN", null, couleurs[indexCouleur]);
		pcs.firePropertyChange("NBBOITE", null, (double) sliderNbBoites.getValue());
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

			//btnDroit.setBackground(transparent);
			btnDroit2.setBackground(transparent);
		//	btnGauche.setBackground(transparent);
			btnGauche2.setBackground(transparent);

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

			//btnDroit.setBackground(transparent);
			btnDroit2.setBackground(transparent);
			//btnGauche.setBackground(transparent);
			btnGauche2.setBackground(transparent);
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

		//	btnDroit.setBackground(transparent);
			btnDroit2.setBackground(transparent);
		//	btnGauche.setBackground(transparent);
			btnGauche2.setBackground(transparent);
		}
	}
	
	/**
	 * méthode qui permet le changement de couleur des voiture
	 * 
	 * @param voiture 	voiture 1 ou 2 
	 * @param direction  changer de couleur vers la droite ou vers la geuche
	 */
	//Ludovic Julien
	public void changementImage(int voiture, int direction) {
		
		if (voiture == 1) {
			if (direction == 1) {
				indexCouleur++;
				if (indexCouleur == couleurs.length) {
					indexCouleur = 0;
				}
			}else {
				indexCouleur--;
				if (indexCouleur < 0) {
					indexCouleur = couleurs.length - 1;
				}	
			}	
		}else {
			if (direction == 1) {
				indexCouleur2++;
				if (indexCouleur2 == couleurs2.length) {
					indexCouleur2 = 0;
				}
			}else {
				indexCouleur2--;
				if (indexCouleur2 < 0) {
					indexCouleur2 = couleurs2.length - 1;
				}
			}

}

		}
	}
