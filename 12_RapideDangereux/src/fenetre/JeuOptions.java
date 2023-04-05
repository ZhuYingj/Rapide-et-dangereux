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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dessin.OutilsImage;
import dessin.ZoneAnimPhysique;
import dessin.ZoneApercupiste;
import interfaces.TypePiste;
import utilitaireObjets.Regroupement;
import utilitaireObjets.Voiture;
import javax.swing.SwingConstants;

/**
 * Classe qui crée la fenêtre pour choisir les paramètres pour le mode de jeu
 * Monde
 * 
 * @author Alexis Pineda-Alvarado
 * 
 */

public class JeuOptions extends JPanel {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final ButtonGroup buttonGroupDiff = new ButtonGroup();
	private JRadioButton rdbtnFacile;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnDifficile;
	private Voiture voiture;
	private JSlider slider;
	private Regroupement regroupement;
	private TypePiste type = TypePiste.MEXIQUE;
	private Image imageActuelle;
	private int indexCouleur = 0;
	private int indexCouleur2 = 0;
    private Color[] couleurs = {Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE};
    private Color[] couleurs2 = {Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE};

	
	
	/**
	 * Méthode qui permet de placer un écouteur
	 */

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
		PanelApercu.setBounds(10, 200, 700, 439);
		add(PanelApercu);
		PanelApercu.setLayout(null);

		ZoneApercupiste zoneApercupiste = new ZoneApercupiste();
		zoneApercupiste.setBounds(0, 0, 700, 439);
		PanelApercu.add(zoneApercupiste);

		Object drapeuxMexique = OutilsImage.lireImage("PisteMexique.png");
		// Icon icone = new ImageIcon(drapeuxMexique);
		JButton btnMexique = new JButton("Mexique");
		// btnMexique.setIcon(icone);
		btnMexique.setBounds(130, 77, 126, 78);
		add(btnMexique);
		btnMexique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = TypePiste.MEXIQUE;
				imageActuelle = OutilsImage.lireImage("PisteMexique.png");
				zoneApercupiste.setImg(imageActuelle);
				zoneApercupiste.repaint();
			}
		});

		JButton btnCanada = new JButton("Canada");
		btnCanada.setBounds(307, 77, 126, 78);
		add(btnCanada);
		btnCanada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = TypePiste.CANADA;
				imageActuelle = OutilsImage.lireImage("PisteCanada.png");
				zoneApercupiste.setImg(imageActuelle);
				zoneApercupiste.repaint();
			}
		});

		JButton btnItalie = new JButton("Italie");
		btnItalie.setBounds(474, 77, 126, 78);
		add(btnItalie);
		btnItalie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = TypePiste.ITALIE;
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
		
		JLabel lblVitesseFacile = new JLabel("60 m/s");
		lblVitesseFacile.setBounds(280, 84, 46, 14);
		panel_1.add(lblVitesseFacile);
		
		JLabel lblVitesseIntermediaire = new JLabel("80 m/s");
		lblVitesseIntermediaire.setBounds(280, 109, 46, 14);
		panel_1.add(lblVitesseIntermediaire);
		
		JLabel lblVitesseAvance = new JLabel("100 m/s");
		lblVitesseAvance.setBounds(280, 135, 46, 14);
		panel_1.add(lblVitesseAvance);

		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("COMMENCER!", null, -1);
				pcs.firePropertyChange("MASSE", null, (double) slider.getValue());
				pcs.firePropertyChange("TYPEPISTE", null, type);
				pcs.firePropertyChange("SKIN", null, couleurs[indexCouleur]);
				pcs.firePropertyChange("SKIN2", null, couleurs2[indexCouleur2]);
			}
		});
		btnCommencer.setBounds(984, 653, 143, 36);
		add(btnCommencer);

		JPanel panel_V1 = new JPanel();
		panel_V1.setBackground(Color.YELLOW);
		panel_V1.setBounds(967, 77, 143, 90);
		add(panel_V1);

		JButton btnGauche = new JButton("<");
		btnGauche.setBounds(891, 116, 55, 23);
		btnGauche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexCouleur--;
                if (indexCouleur < 0) {
                    indexCouleur = couleurs.length - 1;
                }
                panel_V1.setBackground(couleurs[indexCouleur]);
            }
        });
		add(btnGauche);

		JButton btnDroite = new JButton(">");
		btnDroite.setBounds(1123, 116, 55, 23);
		  btnDroite.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                indexCouleur++;
	                if (indexCouleur == couleurs.length) {
	                    indexCouleur = 0;
	                }
	                panel_V1.setBackground(couleurs[indexCouleur]);
	            }
	        });
		add(btnDroite);
		

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);
		
		JPanel PanelV2 = new JPanel();
		PanelV2.setBounds(967, 229, 143, 90);
		PanelV2.setBackground(Color.WHITE);
		add(PanelV2);
		
		JLabel lblNewLabel = new JLabel("Couleur voiture #1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(967, 42, 134, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Couleur Voiture #2");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(967, 200, 143, 13);
		add(lblNewLabel_1);
		
		JButton btnGauche1 = new JButton("<");
		btnGauche1.setBounds(902, 264, 55, 23);
		btnGauche1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexCouleur2--;
                if (indexCouleur2 < 0) {
                    indexCouleur2 = couleurs2.length - 1;
                }
                PanelV2.setBackground(couleurs2[indexCouleur2]);
            }
        });
		add(btnGauche1);
		
		JButton btnDroite1 = new JButton(">");
		btnDroite1.setBounds(1123, 264, 55, 23);
		btnDroite1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexCouleur2++;
                if (indexCouleur2 == couleurs2.length) {
                    indexCouleur2 = 0;
                }
                PanelV2.setBackground(couleurs2[indexCouleur2]);
            }
        });
		add(btnDroite1);

	}
}
