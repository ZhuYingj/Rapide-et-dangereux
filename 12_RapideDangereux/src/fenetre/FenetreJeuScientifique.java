package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import application.AppPrincipale12;
import dessin.ZoneAnimPhysique;
import dessin.ZoneVitesse;
import geometrie.Vecteur2D;

/**
 * Classe qui permet de créer et gérer la fenetre du jeu avec le mode
 * scientifique activé
 * 
 * @author Tan Tommy Rin
 * @author Ludovic Julien
 *
 */
public class FenetreJeuScientifique extends JPanel {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private ZoneAnimPhysique zoneAnimPhysique;

	private JProgressBar progressBarFroce;
	private JProgressBar progressBarFroce2;

	private JLabel lblAccEnXV1;
	private JLabel lblTempsEcouleValeur;
	private JLabel lblAccEnYV1;
	private JLabel lblVitesseEnXV1;
	private JLabel lblVitesseEnYV1;
	private JLabel lblPositionEnXV1;
	private JLabel lblPositionEnYV1;
	private JLabel lblAngleVoiture1Rad;
	private JLabel lblNombreToursVoiture1;
	private JButton btnStart;
	private JButton btnNextImg;
	private JButton btnReset;
	private JButton btnStop;
	private JLabel lblAngleVoiture2Rad;
	private JLabel lblAccEnXV2;
	private JLabel lblAccEnYV2;
	private JLabel lblVitesseEnXV2;
	private JLabel lblVitesseEnYV2;
	private JLabel lblPositionEnXV2;
	private JLabel lblPositionEnYV2;
	private JLabel lblNombreToursVoiture2;

	private static Clip clip;
	private ZoneVitesse zoneVitesse2;
	private ZoneVitesse zoneVitesse;
	private static Timer timerVitesse;

	private JLabel lblAttractionYV2;
	private JLabel lblDiametreV1;
	private JLabel lblDiametreV2;
	private JLabel lblMasseV1;
	private JLabel lblMasseV2;
	private JLabel lblFrottementEnXV1;
	private JLabel lblFrottementEnYV1;
	private JLabel lblFrottementEnXV2;
	private JLabel lblFrottementEnYV2;
	private JLabel lblFreinageEnXV1;
	private JLabel lblFreinageEnYV1;
	private JLabel lblFreinageEnXV2;
	private JLabel lblFreinageEnYV2;
	private JLabel lblAttractionEnXV1;
	private JLabel lblAttractionYV1;
	private JLabel lblAttractionEnXV2;
	private JLabel lblNewLabel_9;
	private JPanel panelObjetEtGraphique;

	/**
	 * Méthode qui permet de placer un écouteur
	 */
	// Tan Tommy Rin
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */
	// Tan Tommy Rin
	public FenetreJeuScientifique() {

		lireMusic();
		panelObjetEtGraphique = new JPanel();
		panelObjetEtGraphique.setBounds(975, 510, 571, 228);
		add(panelObjetEtGraphique);
		panelObjetEtGraphique.setLayout(null);

		zoneVitesse2 = new ZoneVitesse();
		zoneVitesse2.setBounds(260, 0, 311, 228);
		panelObjetEtGraphique.add(zoneVitesse2);
		zoneVitesse2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Graphique de vitesse de la voiture2");
		lblNewLabel_1.setBounds(53, 0, 227, 32);
		zoneVitesse2.add(lblNewLabel_1);

		JLabel lblNewLabel_6 = new JLabel("T(s)");
		lblNewLabel_6.setBounds(221, 211, 46, 14);
		zoneVitesse2.add(lblNewLabel_6);

		JLabel lblNewLabel_4_1 = new JLabel("(m/s)");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1.setBounds(23, 25, 46, 14);
		zoneVitesse2.add(lblNewLabel_4_1);

		JLabel lblNewLabel_2_1 = new JLabel("V");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setBounds(38, 11, 56, 14);
		zoneVitesse2.add(lblNewLabel_2_1);

		zoneVitesse = new ZoneVitesse();
		zoneVitesse.setBounds(-41, 0, 329, 228);
		panelObjetEtGraphique.add(zoneVitesse);
		zoneVitesse.setLayout(null);

		JLabel lblNewLabel = new JLabel("Graphique de vitesse de la voiture1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(40, 0, 227, 32);
		zoneVitesse.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("T(s)");
		lblNewLabel_3.setBounds(221, 211, 46, 14);
		zoneVitesse.add(lblNewLabel_3);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("RetourDuJeuScience", null, -1);
				pcs.firePropertyChange("Test", null, -1);

				resetGraphique();
				resetMusic();
			}
		});

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.setEnCoursDAnimation(false);
				zoneAnimPhysique.demarrer();
				btnNextImg.setEnabled(false);
				btnStart.setEnabled(false);
				pcs.firePropertyChange("STARTBUTTONACTIVE", null, -1);
				actionStart();
				musicStart();

			}
		});
		btnStart.setBounds(10, 650, 89, 76);
		add(btnStart);
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.arreter();
				btnNextImg.setEnabled(true);
				btnStart.setEnabled(true);
				stopGraphique();
				arretMusic();
			}
		});
		btnStop.setBounds(621, 650, 89, 76);
		add(btnStop);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.restartPosPisteDepart();
				btnNextImg.setEnabled(true);
				btnStart.setEnabled(true);
				pcs.firePropertyChange("CHECKBOXACTIVE", null, -1);

				resetGraphique();
				resetMusic();

			}
		});
		btnReset.setBounds(197, 650, 89, 76);
		add(btnReset);

		btnNextImg = new JButton("Next Img");
		btnNextImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.avancerUnPas();
			}
		});
		btnNextImg.setBounds(404, 650, 89, 76);
		add(btnNextImg);

		setLayout(null);
		setBounds(100, 100, 1556, 836);

		JLabel lblTitreModeScientifique = new JLabel("Mode scientifique activé");
		lblTitreModeScientifique.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitreModeScientifique.setBounds(this.getWidth() / 2 - lblTitreModeScientifique.getWidth(), 0, 223, 22);
		add(lblTitreModeScientifique);

		zoneAnimPhysique = new ZoneAnimPhysique();

		zoneAnimPhysique.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				changementDeTextePendantLAnimation(evt);
			}
		});

		zoneAnimPhysique.setBounds(10, 47, 937, 597);
		add(zoneAnimPhysique);

		JPanel panelDonneScientifique = new JPanel();
		panelDonneScientifique.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"DONN\u00C9ES SCIENTIFIQUES", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDonneScientifique.setBackground(Color.WHITE);
		panelDonneScientifique.setBounds(978, 47, 570, 377);
		add(panelDonneScientifique);
		panelDonneScientifique.setLayout(null);

		JLabel lblVoiture1 = new JLabel("Voiture 1");
		lblVoiture1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVoiture1.setBounds(119, 12, 83, 29);
		panelDonneScientifique.add(lblVoiture1);

		JLabel lblVoiture2 = new JLabel("Voiture 2");
		lblVoiture2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVoiture2.setBounds(378, 12, 83, 29);
		panelDonneScientifique.add(lblVoiture2);

		JLabel lblPosition = new JLabel("Position :");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPosition.setBounds(10, 30, 109, 29);
		panelDonneScientifique.add(lblPosition);

		JLabel lblVitesse = new JLabel("Vitesse :");
		lblVitesse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesse.setBounds(10, 60, 77, 29);
		panelDonneScientifique.add(lblVitesse);

		JLabel lblAcceleration = new JLabel("Accélération :");
		lblAcceleration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAcceleration.setBounds(10, 90, 109, 29);
		panelDonneScientifique.add(lblAcceleration);

		JLabel lblFrottement = new JLabel("Frottement :");
		lblFrottement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFrottement.setBounds(10, 120, 109, 29);
		panelDonneScientifique.add(lblFrottement);

		JLabel lblMasse1 = new JLabel("Masse  :");
		lblMasse1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMasse1.setBounds(10, 210, 109, 29);
		panelDonneScientifique.add(lblMasse1);

		JLabel lblFreinage = new JLabel("Freinage :");
		lblFreinage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFreinage.setBounds(10, 150, 109, 29);
		panelDonneScientifique.add(lblFreinage);

		JLabel lblAttraction = new JLabel("Attraction :");
		lblAttraction.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttraction.setBounds(10, 180, 109, 29);
		panelDonneScientifique.add(lblAttraction);

		JLabel lblMetreV1 = new JLabel("m");
		lblMetreV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMetreV1.setBounds(258, 30, 24, 29);
		panelDonneScientifique.add(lblMetreV1);

		JLabel lblMetreV2 = new JLabel("m");
		lblMetreV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMetreV2.setBounds(523, 30, 24, 29);
		panelDonneScientifique.add(lblMetreV2);

		JLabel lblKmParHeureV1 = new JLabel("m/s");
		lblKmParHeureV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKmParHeureV1.setBounds(258, 60, 38, 29);
		panelDonneScientifique.add(lblKmParHeureV1);

		JLabel lblKmParHeureV2 = new JLabel("m/s");
		lblKmParHeureV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKmParHeureV2.setBounds(523, 60, 38, 29);
		panelDonneScientifique.add(lblKmParHeureV2);

		JLabel lblMCarreV1 = new JLabel("m/s");
		lblMCarreV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMCarreV1.setBounds(258, 90, 34, 29);
		panelDonneScientifique.add(lblMCarreV1);

		JLabel lblNV1 = new JLabel("N");
		lblNV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNV1.setBounds(258, 120, 34, 29);
		panelDonneScientifique.add(lblNV1);

		JLabel lblNV1_1 = new JLabel("N");
		lblNV1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNV1_1.setBounds(523, 180, 38, 29);
		panelDonneScientifique.add(lblNV1_1);

		JLabel lblKgMasse1 = new JLabel("Kg");
		lblKgMasse1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKgMasse1.setBounds(258, 210, 34, 29);
		panelDonneScientifique.add(lblKgMasse1);

		JLabel lblKgMasse2 = new JLabel("Kg");
		lblKgMasse2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKgMasse2.setBounds(522, 210, 38, 29);
		panelDonneScientifique.add(lblKgMasse2);

		JLabel lblNFreinageV1 = new JLabel("N");
		lblNFreinageV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNFreinageV1.setBounds(258, 150, 34, 29);
		panelDonneScientifique.add(lblNFreinageV1);

		JLabel lblM = new JLabel("m");
		lblM.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblM.setBounds(258, 240, 34, 29);
		panelDonneScientifique.add(lblM);

		JLabel lblNFreinageV2 = new JLabel("N");
		lblNFreinageV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNFreinageV2.setBounds(523, 120, 38, 29);
		panelDonneScientifique.add(lblNFreinageV2);

		JLabel lblNAttractionV2 = new JLabel("N");
		lblNAttractionV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNAttractionV2.setBounds(523, 150, 38, 29);
		panelDonneScientifique.add(lblNAttractionV2);

		JLabel lblNum2 = new JLabel("2");
		lblNum2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum2.setBounds(287, 90, 30, 14);
		panelDonneScientifique.add(lblNum2);

		JLabel lblMCarreV1_1 = new JLabel("m/s");
		lblMCarreV1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMCarreV1_1.setBounds(523, 90, 56, 29);
		panelDonneScientifique.add(lblMCarreV1_1);

		JLabel lblNum2_1 = new JLabel("2");
		lblNum2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum2_1.setBounds(549, 90, 30, 14);
		panelDonneScientifique.add(lblNum2_1);

		JLabel lblTempsEcoule = new JLabel("Temps écoulé :");
		lblTempsEcoule.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTempsEcoule.setBounds(10, 330, 109, 44);
		panelDonneScientifique.add(lblTempsEcoule);

		JLabel lblSeconde = new JLabel("S");
		lblSeconde.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeconde.setBounds(258, 330, 34, 44);
		panelDonneScientifique.add(lblSeconde);

		lblTempsEcouleValeur = new JLabel("0.00");
		lblTempsEcouleValeur.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTempsEcouleValeur.setBounds(139, 330, 109, 44);
		panelDonneScientifique.add(lblTempsEcouleValeur);

		lblAccEnXV1 = new JLabel("0.00");
		lblAccEnXV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccEnXV1.setBounds(119, 90, 65, 29);
		panelDonneScientifique.add(lblAccEnXV1);

		lblAccEnYV1 = new JLabel("0.00");
		lblAccEnYV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccEnYV1.setBounds(194, 90, 65, 29);
		panelDonneScientifique.add(lblAccEnYV1);

		lblAccEnXV2 = new JLabel("0.00");
		lblAccEnXV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccEnXV2.setBounds(361, 90, 65, 29);
		panelDonneScientifique.add(lblAccEnXV2);

		lblAccEnYV2 = new JLabel("0.00");
		lblAccEnYV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccEnYV2.setBounds(433, 90, 65, 29);
		panelDonneScientifique.add(lblAccEnYV2);

		JLabel lblAccV1Separator = new JLabel("[               ,               ]");
		lblAccV1Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccV1Separator.setBounds(108, 90, 162, 29);
		panelDonneScientifique.add(lblAccV1Separator);

		JLabel lblAccV2Separator = new JLabel("[               ,               ]");
		lblAccV2Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccV2Separator.setBounds(350, 90, 162, 29);
		panelDonneScientifique.add(lblAccV2Separator);

		lblVitesseEnXV1 = new JLabel("0.00");
		lblVitesseEnXV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseEnXV1.setBounds(119, 60, 65, 29);
		panelDonneScientifique.add(lblVitesseEnXV1);

		lblVitesseEnYV1 = new JLabel("0.00");
		lblVitesseEnYV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseEnYV1.setBounds(194, 60, 65, 29);
		panelDonneScientifique.add(lblVitesseEnYV1);

		lblVitesseEnXV2 = new JLabel("0.00");
		lblVitesseEnXV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseEnXV2.setBounds(361, 60, 65, 29);
		panelDonneScientifique.add(lblVitesseEnXV2);

		lblVitesseEnYV2 = new JLabel("0.00");
		lblVitesseEnYV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseEnYV2.setBounds(433, 60, 65, 29);
		panelDonneScientifique.add(lblVitesseEnYV2);

		JLabel lblVitesseV1Separator = new JLabel("[               ,               ]");
		lblVitesseV1Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV1Separator.setBounds(108, 60, 162, 29);
		panelDonneScientifique.add(lblVitesseV1Separator);

		lblPositionEnYV1 = new JLabel("0.00");
		lblPositionEnYV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionEnYV1.setBounds(196, 30, 65, 29);
		panelDonneScientifique.add(lblPositionEnYV1);

		lblPositionEnXV1 = new JLabel("0.00");
		lblPositionEnXV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionEnXV1.setBounds(121, 30, 65, 29);
		panelDonneScientifique.add(lblPositionEnXV1);

		JLabel lblVitesseV2Separator = new JLabel("[               ,               ]");
		lblVitesseV2Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV2Separator.setBounds(350, 60, 162, 29);
		panelDonneScientifique.add(lblVitesseV2Separator);

		lblPositionEnYV2 = new JLabel("0.00");
		lblPositionEnYV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionEnYV2.setBounds(433, 30, 65, 29);
		panelDonneScientifique.add(lblPositionEnYV2);

		lblPositionEnXV2 = new JLabel("0.00");
		lblPositionEnXV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionEnXV2.setBounds(361, 30, 65, 29);
		panelDonneScientifique.add(lblPositionEnXV2);

		JLabel lblPositionV1Separator = new JLabel("[               ,               ]");
		lblPositionV1Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionV1Separator.setBounds(108, 30, 162, 29);
		panelDonneScientifique.add(lblPositionV1Separator);

		JLabel lblPositionV2Separator = new JLabel("[               ,               ]");
		lblPositionV2Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionV2Separator.setBounds(350, 30, 162, 29);
		panelDonneScientifique.add(lblPositionV2Separator);

		JLabel lblAngleVoiture1 = new JLabel("Angle :");
		lblAngleVoiture1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAngleVoiture1.setBounds(10, 270, 109, 29);
		panelDonneScientifique.add(lblAngleVoiture1);

		lblAngleVoiture1Rad = new JLabel("0.00");
		lblAngleVoiture1Rad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAngleVoiture1Rad.setBounds(119, 270, 65, 29);
		panelDonneScientifique.add(lblAngleVoiture1Rad);

		JLabel lblRadVoiture1 = new JLabel("Rad");
		lblRadVoiture1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRadVoiture1.setBounds(258, 270, 34, 29);
		panelDonneScientifique.add(lblRadVoiture1);

		lblAngleVoiture2Rad = new JLabel("0.00");
		lblAngleVoiture2Rad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAngleVoiture2Rad.setBounds(396, 270, 65, 29);
		panelDonneScientifique.add(lblAngleVoiture2Rad);

		JLabel lblRadVoiture2 = new JLabel("Rad");
		lblRadVoiture2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRadVoiture2.setBounds(523, 270, 38, 29);
		panelDonneScientifique.add(lblRadVoiture2);

		JLabel lblNombreToursV1 = new JLabel("Nombre de tours :");
		lblNombreToursV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursV1.setBounds(10, 300, 132, 29);
		panelDonneScientifique.add(lblNombreToursV1);

		lblNombreToursVoiture1 = new JLabel("0");
		lblNombreToursVoiture1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursVoiture1.setBounds(154, 300, 65, 29);
		panelDonneScientifique.add(lblNombreToursVoiture1);

		JLabel lblNombreTourV1 = new JLabel("Tours");
		lblNombreTourV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreTourV1.setBounds(258, 300, 59, 29);
		panelDonneScientifique.add(lblNombreTourV1);

		lblNombreToursVoiture2 = new JLabel("0");
		lblNombreToursVoiture2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursVoiture2.setBounds(396, 300, 65, 29);
		panelDonneScientifique.add(lblNombreToursVoiture2);

		JLabel lblNombreTourV2 = new JLabel("Tours");
		lblNombreTourV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreTourV2.setBounds(523, 300, 56, 29);
		panelDonneScientifique.add(lblNombreTourV2);

		JLabel lblDiametre = new JLabel("Diametre :");
		lblDiametre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiametre.setBounds(10, 240, 109, 29);
		panelDonneScientifique.add(lblDiametre);

		JLabel lblNAttractionV1_1 = new JLabel("N");
		lblNAttractionV1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNAttractionV1_1.setBounds(258, 180, 34, 29);
		panelDonneScientifique.add(lblNAttractionV1_1);

		JLabel lblM_1 = new JLabel("m");
		lblM_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblM_1.setBounds(523, 240, 34, 29);
		panelDonneScientifique.add(lblM_1);

		lblDiametreV1 = new JLabel("0.00");
		lblDiametreV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiametreV1.setBounds(119, 240, 65, 29);
		panelDonneScientifique.add(lblDiametreV1);

		lblDiametreV2 = new JLabel("0.00");
		lblDiametreV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiametreV2.setBounds(396, 240, 65, 29);
		panelDonneScientifique.add(lblDiametreV2);

		JLabel lblVitesseV1Separator_1 = new JLabel("[               ,               ]");
		lblVitesseV1Separator_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV1Separator_1.setBounds(108, 120, 162, 29);
		panelDonneScientifique.add(lblVitesseV1Separator_1);

		JLabel lblVitesseV1Separator_1_1 = new JLabel("[               ,               ]");
		lblVitesseV1Separator_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV1Separator_1_1.setBounds(108, 150, 162, 29);
		panelDonneScientifique.add(lblVitesseV1Separator_1_1);

		JLabel lblVitesseV1Separator_1_1_1 = new JLabel("[               ,               ]");
		lblVitesseV1Separator_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV1Separator_1_1_1.setBounds(108, 180, 162, 29);
		panelDonneScientifique.add(lblVitesseV1Separator_1_1_1);

		JLabel lblVitesseV1Separator_1_1_2 = new JLabel("[               ,               ]");
		lblVitesseV1Separator_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV1Separator_1_1_2.setBounds(350, 150, 162, 29);
		panelDonneScientifique.add(lblVitesseV1Separator_1_1_2);

		JLabel lblVitesseV1Separator_1_1_3 = new JLabel("[               ,               ]");
		lblVitesseV1Separator_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV1Separator_1_1_3.setBounds(351, 120, 162, 29);
		panelDonneScientifique.add(lblVitesseV1Separator_1_1_3);

		JLabel lblVitesseV1Separator_1_1_4 = new JLabel("[               ,               ]");
		lblVitesseV1Separator_1_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV1Separator_1_1_4.setBounds(351, 180, 162, 29);
		panelDonneScientifique.add(lblVitesseV1Separator_1_1_4);

		lblMasseV1 = new JLabel("0.00");
		lblMasseV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMasseV1.setBounds(119, 210, 65, 29);
		panelDonneScientifique.add(lblMasseV1);

		lblMasseV2 = new JLabel("0.00");
		lblMasseV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMasseV2.setBounds(396, 210, 65, 29);
		panelDonneScientifique.add(lblMasseV2);

		lblFrottementEnXV1 = new JLabel("0.00");
		lblFrottementEnXV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFrottementEnXV1.setBounds(119, 120, 65, 29);
		panelDonneScientifique.add(lblFrottementEnXV1);

		lblFrottementEnYV1 = new JLabel("0.00");
		lblFrottementEnYV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFrottementEnYV1.setBounds(194, 120, 65, 29);
		panelDonneScientifique.add(lblFrottementEnYV1);

		lblFrottementEnXV2 = new JLabel("0.00");
		lblFrottementEnXV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFrottementEnXV2.setBounds(361, 120, 65, 29);
		panelDonneScientifique.add(lblFrottementEnXV2);

		lblFrottementEnYV2 = new JLabel("0.00");
		lblFrottementEnYV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFrottementEnYV2.setBounds(433, 120, 65, 29);
		panelDonneScientifique.add(lblFrottementEnYV2);

		lblFreinageEnXV1 = new JLabel("0.00");
		lblFreinageEnXV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFreinageEnXV1.setBounds(119, 150, 65, 29);
		panelDonneScientifique.add(lblFreinageEnXV1);

		lblFreinageEnYV1 = new JLabel("0.00");
		lblFreinageEnYV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFreinageEnYV1.setBounds(194, 150, 65, 29);
		panelDonneScientifique.add(lblFreinageEnYV1);

		lblFreinageEnXV2 = new JLabel("0.00");
		lblFreinageEnXV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFreinageEnXV2.setBounds(361, 150, 65, 29);
		panelDonneScientifique.add(lblFreinageEnXV2);

		lblFreinageEnYV2 = new JLabel("0.00");
		lblFreinageEnYV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFreinageEnYV2.setBounds(433, 150, 65, 29);
		panelDonneScientifique.add(lblFreinageEnYV2);

		lblAttractionEnXV1 = new JLabel("0.00");
		lblAttractionEnXV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttractionEnXV1.setBounds(119, 180, 65, 29);
		panelDonneScientifique.add(lblAttractionEnXV1);

		lblAttractionYV1 = new JLabel("0.00");
		lblAttractionYV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttractionYV1.setBounds(194, 180, 65, 29);
		panelDonneScientifique.add(lblAttractionYV1);

		lblAttractionEnXV2 = new JLabel("0.00");
		lblAttractionEnXV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttractionEnXV2.setBounds(358, 180, 65, 29);
		panelDonneScientifique.add(lblAttractionEnXV2);

		lblAttractionYV2 = new JLabel("0.00");
		lblAttractionYV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttractionYV2.setBounds(433, 180, 65, 29);
		panelDonneScientifique.add(lblAttractionYV2);

		JLabel lblNewLabel_2 = new JLabel("V");
		lblNewLabel_2.setBounds(963, 524, 56, 14);
		add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblNewLabel_4 = new JLabel("(m/s)");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(948, 538, 46, 14);
		add(lblNewLabel_4);

		JLabel lblNewLabel_7 = new JLabel("<---------------->");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(361, 29, 120, 14);
		add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("80 M");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_8.setBounds(406, 13, 46, 14);
		add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("");

		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));

		lblNewLabel_9.setBounds(500, 13, 46, 14);
		add(lblNewLabel_9);

		progressBarFroce = new JProgressBar();
		progressBarFroce.setBounds(985, 470, 211, 29);
		add(progressBarFroce);
		progressBarFroce.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBarFroce.setStringPainted(true);

		progressBarFroce2 = new JProgressBar();
		progressBarFroce2.setBounds(1288, 470, 211, 29);
		add(progressBarFroce2);
		progressBarFroce2.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBarFroce2.setStringPainted(true);

		JLabel lblNewLabel_10 = new JLabel("Force de lancement voiture 1");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10.setBounds(988, 445, 211, 14);
		add(lblNewLabel_10);

		JLabel lblNewLabel_10_1 = new JLabel("Force de lancement voiture 2");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10_1.setBounds(1288, 445, 211, 14);
		add(lblNewLabel_10_1);

		graphiqueVitesse();
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(JButton btnStart) {
		this.btnStart = btnStart;
	}

	/**
	 * Méthode qui retourne la zone d'animation physique
	 * 
	 * @return la zone d'animation physique
	 */
	// Tan Tommy Rin

	public ZoneAnimPhysique getZoneAnimPhysique() {
		return zoneAnimPhysique;
	}

	/**
	 * Méthode qui permet de changer la zone d'animation par une nouvelle
	 * 
	 * @param zoneAnimPhysique La nouvelle zone d'animation
	 */
	// Tan Tommy Rin

	public void setZoneAnimPhysique(ZoneAnimPhysique zoneAnimPhysique) {
		this.zoneAnimPhysique = zoneAnimPhysique;
	}

	/**
	 * Méthode qui change le texte/l'information durant l'animation
	 * 
	 * @param evt Évènement lorsque l'information change
	 */
	// Tan Tommy Rin
	public void changementDeTextePendantLAnimation(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {

		case "tempsEcoule":
			lblTempsEcouleValeur.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "accEnXV1":
			lblAccEnXV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "accEnYV1":
			lblAccEnYV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "vitEnXV1":
			lblVitesseEnXV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "vitEnYV1":
			lblVitesseEnYV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "posEnXV1":
			lblPositionEnXV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "posEnYV1":
			lblPositionEnYV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "angleV1":
			lblAngleVoiture1Rad.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "nombreToursV1":
			lblNombreToursVoiture1.setText(String.format("%.0f", evt.getNewValue()));
			break;
		case "accEnXV2":
			lblAccEnXV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "accEnYV2":
			lblAccEnYV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "vitEnXV2":
			lblVitesseEnXV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "vitEnYV2":
			lblVitesseEnYV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "posEnXV2":
			lblPositionEnXV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "posEnYV2":
			lblPositionEnYV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "angleV2":
			lblAngleVoiture2Rad.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "nombreToursV2":
			lblNombreToursVoiture2.setText(String.format("%.0f", evt.getNewValue()));
			break;
		case "masse1":
			lblMasseV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "masse2":
			lblMasseV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "diametre1":
			lblDiametreV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "diametre2":
			lblDiametreV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "frottementEnXV1":
			lblFrottementEnXV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "frottementEnYV1":
			lblFrottementEnYV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "frottementEnXV2":
			lblFrottementEnXV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "frottementEnYV2":
			lblFrottementEnYV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "freinageV1Reset":
			lblFreinageEnXV1.setText(String.format("%.2f", 0.00));
			lblFreinageEnYV1.setText(String.format("%.2f", 0.00));
			break;
		case "freinageEnXV1":
			lblFreinageEnXV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "freinageEnYV1":
			lblFreinageEnYV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "freinageV2Reset":
			lblFreinageEnXV2.setText(String.format("%.2f", 0.00));
			lblFreinageEnYV2.setText(String.format("%.2f", 0.00));
			break;
		case "freinageEnXV2":
			lblFreinageEnXV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "freinageEnYV2":
			lblFreinageEnYV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "attractionV1X":
			lblAttractionEnXV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "attractionV1Y":
			lblAttractionYV1.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "attractionV2X":
			lblAttractionEnXV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "attractionV2Y":
			lblAttractionYV2.setText(String.format("%.2f", evt.getNewValue()));
			break;
		case "attractionV1Reset":
			lblAttractionEnXV1.setText(String.format("%.2f", 0.00));
			lblAttractionYV1.setText(String.format("%.2f", 0.00));
			break;
		case "attractionV2Reset":
			lblAttractionEnXV2.setText(String.format("%.2f", 0.00));
			lblAttractionYV2.setText(String.format("%.2f", 0.00));
			break;
		case "ForceLance":

			Double newData = new Double((double) evt.getNewValue());
			int valeur = newData.intValue();
			progressBarFroce.setMinimum(0);
			progressBarFroce.setMaximum(100);
			progressBarFroce.setValue(valeur - 50);
			break;
		case "ForceLance2":

			Double newData2 = new Double((double) evt.getNewValue());
			int valeur2 = newData2.intValue();
			progressBarFroce2.setMinimum(0);
			progressBarFroce2.setMaximum(100);
			progressBarFroce2.setValue(valeur2 - 50);
			break;
		case "reset1":
			progressBarFroce.setValue(0);
			break;
		case "reset2":
			progressBarFroce2.setValue(0);
			break;
		}

	}

	/**
	 * Medote Timer qui permet de prendre les donner de la vitesse des deux voiture
	 */
	// Ludovic Julien
	private void graphiqueVitesse() {
		timerVitesse = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double vitesseActuelle1 = zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getVoiture()
						.getVitesse().module();
				double vitesseActuelle2 = zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0)
						.getVoiture2().getVitesse().module();
				if (vitesseActuelle1 < 0) {
					vitesseActuelle1 = (vitesseActuelle1 * -1);
				}
				if (vitesseActuelle2 < 0) {
					vitesseActuelle2 = (vitesseActuelle2 * -1);
				}
				zoneVitesse2.ajouterVitesse(vitesseActuelle2);
				zoneVitesse.ajouterVitesse(vitesseActuelle1);
				zoneVitesse2.ajouterTemps();
				zoneVitesse.ajouterTemps();

			}
		});
	}

	/**
	 * méthode qui retourn le timer des graphique
	 * 
	 * @return timerVitesse
	 */
	// Ludovic Julien
	public static Timer getTimer() {
		return timerVitesse;
	}

	/**
	 * méthode qui permet de reset les graphhique de vitesse en fonction du temps
	 */
	// Ludovic Julien
	public void resetGraphique() {
		zoneVitesse.renouvlerTemps();
		zoneVitesse.renouvlerVitesse();
		zoneVitesse2.renouvlerTemps();
		zoneVitesse2.renouvlerVitesse();
		timerVitesse.stop();
	}

	/**
	 * méthode qui permet de mettre en pause les graphique de vitesse en fonction du
	 * temps
	 */
	// Ludovic Julien
	public void stopGraphique() {
		timerVitesse.stop();
	}

	/**
	 * méthode qui permet d'activer le timer pour les graphique
	 */
	// Ludovic Julien
	public void actionStart() {
		timerVitesse.start();
	}

	/**
	 * méthode qui permet de lancer la piste audio
	 */
	// Ludovic Julien
	public void musicStart() {
		if (AppPrincipale12.getCheckAudio() == false) {
			clip.start();
		}
	}

	/**
	 * méthode qui retourne la valeur de la variable audio
	 * 
	 * @return clip
	 */
	// Ludovic Julien
	public static Clip getClip() {
		return clip;
	}

	/**
	 * méthode qui permet d'arreter et de recommencer la music au debut
	 * 
	 */
	// Ludovic Julien
	public void resetMusic() {
		if (clip != null) {
			clip.stop();
			clip.setMicrosecondPosition(0);
		}
	}

	/**
	 * méthode qui permet de lire un fichier audio
	 */
	// Ludovic Julien
	public void lireMusic() {
		try {
			clip = AudioSystem.getClip();
			URL resource = getClass().getClassLoader().getResource("Kosmorider-Night.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(resource);
			clip.open(inputStream);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * méthode qui permet d'arreter une music
	 */
	// Ludovic Julien
	public void arretMusic() {
		if (clip != null) {
			clip.stop();
		}
	}
}
