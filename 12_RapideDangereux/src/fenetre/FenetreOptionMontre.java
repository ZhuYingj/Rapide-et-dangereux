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
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
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
	private int indexCouleur = 0;
	private int indexCouleur2 = 0;
	private Color[] couleurs = { Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE };
	private Color[] couleurs2 = { Color.cyan, Color.WHITE, Color.GRAY, Color.magenta, Color.PINK};
	private JTextArea txtArea;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Create the panel.
	 */
	//Alexis Pineda-Alvarado
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

		JPanel panelPourMessage = new JPanel();
		panelPourMessage.setBounds(1000, 200, 549, 134);
		add(panelPourMessage);
		panelPourMessage.setLayout(null);

		JScrollPane spPourMessage = new JScrollPane();
		spPourMessage.setBounds(0, 0, 549, 134);
		panelPourMessage.add(spPourMessage);
		
		JLabel lblTitre = new JLabel("Course Contre La Montre");
		lblTitre.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 44));
		lblTitre.setBounds(503, 11, 540, 70);
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
		panel_1.setBounds(1000, 345, 549, 297);
		add(panel_1);
		panel_1.setLayout(null);

		rdbtnFacile = new JRadioButton("Facile");
		rdbtnFacile.setSelected(true);
		rdbtnFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtArea.append("\nVous avez choisi la difficulter <<Facile>> ");
				pcs.firePropertyChange("VITESSEMAXFACILE2", null, 60.0);

			}
		});
		rdbtnFacile.setBounds(165, 182, 109, 23);
		panel_1.add(rdbtnFacile);
		buttonGroupDiff.add(rdbtnFacile);

		rdbtnMedium = new JRadioButton("Intermédiaire");
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtArea.append("\nVous avez choisi la difficulter <<Intermédiaire>> ");
				pcs.firePropertyChange("VITESSEMAXINTERMEDIAIRE2", null, 80.0);

			}
		});
		rdbtnMedium.setBounds(165, 207, 109, 23);
		panel_1.add(rdbtnMedium);
		buttonGroupDiff.add(rdbtnMedium);

		rdbtnDifficile = new JRadioButton("Avancé");
		rdbtnDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtArea.append("\nVous avez choisi la difficulter <<Avancé>> ");
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

		JPanel panel_V1 = new JPanel();
		panel_V1.setBackground(Color.YELLOW);
		panel_V1.setBounds(970, 77, 143, 84);
		add(panel_V1);
		
		JPanel panel_V2 = new JPanel();
		panel_V2.setBackground(Color.CYAN);
		panel_V2.setBounds(970, 232, 143, 84);
		add(panel_V2);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(917, 77, 143, 78);
		add(panel_2);

		JButton btnGauche1 = new JButton("<");
		btnGauche1.setBounds(905, 105, 55, 23);
		btnGauche1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(1,0);
				panel_V1.setBackground(couleurs[indexCouleur]);
			}
		});
		add(btnGauche1);

		JButton btnGauche = new JButton("<");
		btnGauche.setBounds(852, 105, 55, 23);
		add(btnGauche);


		JButton btnDroite1 = new JButton(">");
		btnDroite1.setBounds(1123, 105, 55, 23);
		btnDroite1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changementImage(1,1);
				panel_V1.setBackground(couleurs[indexCouleur]);
		}
		});
		add(btnDroite1);
		
		JButton btnGauche2 = new JButton("<");
		btnGauche2.setBounds(905, 260, 55, 23);
		btnGauche2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(0,0);
				
				panel_V2.setBackground(couleurs2[indexCouleur2]);
			}
		});
		add(btnGauche2);
		
		JButton btnDroite2 = new JButton(">");
		btnDroite2.setBounds(1123, 260, 55, 23);
		btnDroite2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changementImage(0,1);
				panel_V2.setBackground(couleurs2[indexCouleur2]);
			}
		});
		add(btnDroite2);
		
		JButton btnDroite = new JButton(">");
		btnDroite.setBounds(1070, 105, 55, 23);
		add(btnDroite);

		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("COMMENCER COURSE MONTRE", null, -1);
				pcs.firePropertyChange("TYPEPISTE", null, type);
				pcs.firePropertyChange("MASSEMONTRE1", null, (double) slider.getValue());
				pcs.firePropertyChange("MASSEMONTRE2", null, (double) slider2.getValue());
				pcs.firePropertyChange("NBRDETOUR", null, (double) sliderNbrTour.getValue());
				//actionSkin();
				pcs.firePropertyChange("SKIN", null, couleurs[indexCouleur]);
				pcs.firePropertyChange("SKIN2", null, couleurs2[indexCouleur2]);
			}
		});
		btnCommencer.setBounds(1225, 653, 143, 36);
		add(btnCommencer);

		lblImage = new JLabel("");
		lblImage.setBounds(10, 0, 1600, 800);
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
			txtArea.append("\nVous avez choisi la piste Canada!");
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
			txtArea.append("\nVous avez choisi la piste Mexique!");
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
			txtArea.append("\nVous avez choisi la piste Italie!");
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
	
	/**
	 * Méthode qui permet d'envoyer des informations à la zone physique à l'aide de
	 * levée d'évènements.
	 */
	//Ludovic Julien
	private void actionSkin() {
		pcs.firePropertyChange("SKIN", null, couleurs[indexCouleur]);
		pcs.firePropertyChange("SKIN2", null, couleurs2[indexCouleur2]);
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
