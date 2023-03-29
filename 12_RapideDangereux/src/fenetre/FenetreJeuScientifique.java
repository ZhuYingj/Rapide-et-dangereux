package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import application.AppPrincipale12;
import dessin.ZoneAcceleration;
import dessin.ZoneAnimPhysique;
import dessin.ZoneVitesse;
import physique.MoteurPhysique;
import utilitaireObjets.Voiture;

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
	private AppPrincipale12 application;
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

	/**
	 * Méthode qui permet de placer un écouteur
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */
	public FenetreJeuScientifique() {
		setLayout(null);
		setBounds(100, 100, 1300, 700);

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

		zoneAnimPhysique.setBounds(10, 47, 700, 439);
		add(zoneAnimPhysique);

		JPanel panelDonneScientifique = new JPanel();
		panelDonneScientifique.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"DONN\u00C9ES SCIENTIFIQUES", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDonneScientifique.setBackground(Color.GRAY);
		panelDonneScientifique.setBounds(720, 33, 570, 466);
		add(panelDonneScientifique);
		panelDonneScientifique.setLayout(null);

		JLabel lblVoiture1 = new JLabel("Voiture 1");
		lblVoiture1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVoiture1.setBounds(139, 29, 83, 29);
		panelDonneScientifique.add(lblVoiture1);

		JLabel lblVoiture2 = new JLabel("Voiture 2");
		lblVoiture2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVoiture2.setBounds(378, 29, 83, 29);
		panelDonneScientifique.add(lblVoiture2);

		JLabel lblPosition = new JLabel("Position :");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPosition.setBounds(10, 52, 109, 29);
		panelDonneScientifique.add(lblPosition);

		JLabel lblVitesse = new JLabel("Vitesse :");
		lblVitesse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesse.setBounds(10, 92, 77, 29);
		panelDonneScientifique.add(lblVitesse);

		JLabel lblAcceleration = new JLabel("Accélération :");
		lblAcceleration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAcceleration.setBounds(10, 132, 109, 29);
		panelDonneScientifique.add(lblAcceleration);

		JLabel lblFrottement = new JLabel("Frottement :");
		lblFrottement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFrottement.setBounds(10, 172, 109, 29);
		panelDonneScientifique.add(lblFrottement);

		JLabel lblGravite = new JLabel("Gravité :");
		lblGravite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGravite.setBounds(10, 215, 109, 29);
		panelDonneScientifique.add(lblGravite);

		JLabel lblFreinage = new JLabel("Freinage :");
		lblFreinage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFreinage.setBounds(10, 257, 109, 29);
		panelDonneScientifique.add(lblFreinage);

		JLabel lblAttraction = new JLabel("Attraction :");
		lblAttraction.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttraction.setBounds(10, 297, 109, 29);
		panelDonneScientifique.add(lblAttraction);

		JLabel lblMetreV1 = new JLabel("m");
		lblMetreV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMetreV1.setBounds(244, 52, 24, 29);
		panelDonneScientifique.add(lblMetreV1);

		JLabel lblMetreV2 = new JLabel("m");
		lblMetreV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMetreV2.setBounds(523, 52, 24, 29);
		panelDonneScientifique.add(lblMetreV2);

		JLabel lblKmParHeureV1 = new JLabel("m/s");
		lblKmParHeureV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKmParHeureV1.setBounds(244, 92, 38, 29);
		panelDonneScientifique.add(lblKmParHeureV1);

		JLabel lblKmParHeureV2 = new JLabel("m/s");
		lblKmParHeureV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKmParHeureV2.setBounds(523, 92, 38, 29);
		panelDonneScientifique.add(lblKmParHeureV2);

		JLabel lblMCarreV1 = new JLabel("m/s");
		lblMCarreV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMCarreV1.setBounds(244, 132, 56, 29);
		panelDonneScientifique.add(lblMCarreV1);

		JLabel lblNV1 = new JLabel("N");
		lblNV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNV1.setBounds(244, 172, 38, 29);
		panelDonneScientifique.add(lblNV1);

		JLabel lblNV1_1 = new JLabel("N");
		lblNV1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNV1_1.setBounds(523, 172, 38, 29);
		panelDonneScientifique.add(lblNV1_1);

		JLabel lblNGraviteV1 = new JLabel("N");
		lblNGraviteV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNGraviteV1.setBounds(244, 215, 38, 29);
		panelDonneScientifique.add(lblNGraviteV1);

		JLabel lblNGraviteV2 = new JLabel("N");
		lblNGraviteV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNGraviteV2.setBounds(523, 215, 38, 29);
		panelDonneScientifique.add(lblNGraviteV2);

		JLabel lblNFreinageV1 = new JLabel("N");
		lblNFreinageV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNFreinageV1.setBounds(244, 257, 38, 29);
		panelDonneScientifique.add(lblNFreinageV1);

		JLabel lblNAttractionV1 = new JLabel("N");
		lblNAttractionV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNAttractionV1.setBounds(244, 297, 38, 29);
		panelDonneScientifique.add(lblNAttractionV1);

		JLabel lblNFreinageV2 = new JLabel("N");
		lblNFreinageV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNFreinageV2.setBounds(523, 257, 38, 29);
		panelDonneScientifique.add(lblNFreinageV2);

		JLabel lblNAttractionV2 = new JLabel("N");
		lblNAttractionV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNAttractionV2.setBounds(523, 297, 38, 29);
		panelDonneScientifique.add(lblNAttractionV2);

		JLabel lblNum2 = new JLabel("2");
		lblNum2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum2.setBounds(270, 132, 30, 14);
		panelDonneScientifique.add(lblNum2);

		JLabel lblMCarreV1_1 = new JLabel("m/s");
		lblMCarreV1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMCarreV1_1.setBounds(523, 132, 56, 29);
		panelDonneScientifique.add(lblMCarreV1_1);

		JLabel lblNum2_1 = new JLabel("2");
		lblNum2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNum2_1.setBounds(549, 132, 30, 14);
		panelDonneScientifique.add(lblNum2_1);

		JLabel lblTempsEcoule = new JLabel("Temps écoulé :");
		lblTempsEcoule.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTempsEcoule.setBounds(10, 422, 109, 44);
		panelDonneScientifique.add(lblTempsEcoule);

		JLabel lblSeconde = new JLabel("S");
		lblSeconde.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeconde.setBounds(244, 422, 38, 44);
		panelDonneScientifique.add(lblSeconde);

		lblTempsEcouleValeur = new JLabel("0.00");
		lblTempsEcouleValeur.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTempsEcouleValeur.setBounds(137, 422, 109, 44);
		panelDonneScientifique.add(lblTempsEcouleValeur);

		lblAccEnXV1 = new JLabel("0.00");
		lblAccEnXV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccEnXV1.setBounds(119, 132, 65, 29);
		panelDonneScientifique.add(lblAccEnXV1);

		lblAccEnYV1 = new JLabel("0.00");
		lblAccEnYV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccEnYV1.setBounds(194, 132, 65, 29);
		panelDonneScientifique.add(lblAccEnYV1);
		
		lblAccEnXV2 = new JLabel("0.00");
		lblAccEnXV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccEnXV2.setBounds(361, 132, 65, 29);
		panelDonneScientifique.add(lblAccEnXV2);

		lblAccEnYV2 = new JLabel("0.00");
		lblAccEnYV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccEnYV2.setBounds(433, 132, 65, 29);
		panelDonneScientifique.add(lblAccEnYV2);

		JLabel lblAccV1Separator = new JLabel("[               ,               ]");
		lblAccV1Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccV1Separator.setBounds(108, 132, 162, 29);
		panelDonneScientifique.add(lblAccV1Separator);
		
		JLabel lblAccV2Separator = new JLabel("[               ,               ]");
		lblAccV2Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAccV2Separator.setBounds(350, 132, 162, 29);
		panelDonneScientifique.add(lblAccV2Separator);

		lblVitesseEnXV1 = new JLabel("0.00");
		lblVitesseEnXV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseEnXV1.setBounds(119, 92, 65, 29);
		panelDonneScientifique.add(lblVitesseEnXV1);

		lblVitesseEnYV1 = new JLabel("0.00");
		lblVitesseEnYV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseEnYV1.setBounds(194, 92, 65, 29);
		panelDonneScientifique.add(lblVitesseEnYV1);
		
		lblVitesseEnXV2 = new JLabel("0.00");
		lblVitesseEnXV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseEnXV2.setBounds(361, 92, 65, 29);
		panelDonneScientifique.add(lblVitesseEnXV2);

		lblVitesseEnYV2 = new JLabel("0.00");
		lblVitesseEnYV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseEnYV2.setBounds(433, 92, 65, 29);
		panelDonneScientifique.add(lblVitesseEnYV2);

		JLabel lblVitesseV1Separator = new JLabel("[               ,               ]");
		lblVitesseV1Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV1Separator.setBounds(108, 92, 162, 29);
		panelDonneScientifique.add(lblVitesseV1Separator);

		lblPositionEnYV1 = new JLabel("0.00");
		lblPositionEnYV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionEnYV1.setBounds(196, 52, 65, 29);
		panelDonneScientifique.add(lblPositionEnYV1);

		lblPositionEnXV1 = new JLabel("0.00");
		lblPositionEnXV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionEnXV1.setBounds(121, 52, 65, 29);
		panelDonneScientifique.add(lblPositionEnXV1);
		
		JLabel lblVitesseV2Separator = new JLabel("[               ,               ]");
		lblVitesseV2Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesseV2Separator.setBounds(350, 92, 162, 29);
		panelDonneScientifique.add(lblVitesseV2Separator);
		
		lblPositionEnYV2 = new JLabel("0.00");
		lblPositionEnYV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionEnYV2.setBounds(433, 52, 65, 29);
		panelDonneScientifique.add(lblPositionEnYV2);

		lblPositionEnXV2 = new JLabel("0.00");
		lblPositionEnXV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionEnXV2.setBounds(361, 52, 65, 29);
		panelDonneScientifique.add(lblPositionEnXV2);

		JLabel lblPositionV1Separator = new JLabel("[               ,               ]");
		lblPositionV1Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionV1Separator.setBounds(108, 52, 162, 29);
		panelDonneScientifique.add(lblPositionV1Separator);
		
		JLabel lblPositionV2Separator = new JLabel("[               ,               ]");
		lblPositionV2Separator.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPositionV2Separator.setBounds(350, 52, 162, 29);
		panelDonneScientifique.add(lblPositionV2Separator);

		JLabel lblAngleVoiture1 = new JLabel("Angle :");
		lblAngleVoiture1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAngleVoiture1.setBounds(10, 342, 109, 29);
		panelDonneScientifique.add(lblAngleVoiture1);

		lblAngleVoiture1Rad = new JLabel("0.00");
		lblAngleVoiture1Rad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAngleVoiture1Rad.setBounds(135, 342, 65, 29);
		panelDonneScientifique.add(lblAngleVoiture1Rad);

		JLabel lblRadVoiture1 = new JLabel("Rad");
		lblRadVoiture1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRadVoiture1.setBounds(244, 342, 38, 29);
		panelDonneScientifique.add(lblRadVoiture1);

		lblAngleVoiture2Rad = new JLabel("0.00");
		lblAngleVoiture2Rad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAngleVoiture2Rad.setBounds(404, 342, 65, 29);
		panelDonneScientifique.add(lblAngleVoiture2Rad);

		JLabel lblRadVoiture2 = new JLabel("Rad");
		lblRadVoiture2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRadVoiture2.setBounds(523, 337, 38, 29);
		panelDonneScientifique.add(lblRadVoiture2);
		

		JLabel lblNombreToursV1 = new JLabel("Nombre de tours :");
		lblNombreToursV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursV1.setBounds(10, 382, 132, 29);
		panelDonneScientifique.add(lblNombreToursV1);

		lblNombreToursVoiture1 = new JLabel("0");
		lblNombreToursVoiture1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursVoiture1.setBounds(157, 382, 65, 29);
		panelDonneScientifique.add(lblNombreToursVoiture1);

		JLabel lblNombreTourV1 = new JLabel("Tours");
		lblNombreTourV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreTourV1.setBounds(244, 382, 56, 29);
		panelDonneScientifique.add(lblNombreTourV1);

		lblNombreToursVoiture2 = new JLabel("0.00");
		lblNombreToursVoiture2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursVoiture2.setBounds(404, 382, 65, 29);
		panelDonneScientifique.add(lblNombreToursVoiture2);

		JLabel lblNombreTourV2 = new JLabel("Tours");
		lblNombreTourV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreTourV2.setBounds(523, 382, 56, 29);
		panelDonneScientifique.add(lblNombreTourV2);

		JPanel panelObjetEtGraphique = new JPanel();
		panelObjetEtGraphique.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelObjetEtGraphique.setBounds(720, 510, 570, 179);
		add(panelObjetEtGraphique);
		panelObjetEtGraphique.setLayout(null);

		JProgressBar progressBarFroce = new JProgressBar();
		progressBarFroce.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBarFroce.setStringPainted(true);
		progressBarFroce.setOrientation(SwingConstants.VERTICAL);
		progressBarFroce.setBounds(519, 11, 30, 157);
		panelObjetEtGraphique.add(progressBarFroce);
		
		ZoneVitesse zoneVitesse = new ZoneVitesse();
		zoneVitesse.setBounds(-50, -33, 250, 274);
		panelObjetEtGraphique.add(zoneVitesse);
		
		ZoneAcceleration zoneAcceleration = new ZoneAcceleration();
		zoneAcceleration.setBounds(200, -33, 250, 274);
		panelObjetEtGraphique.add(zoneAcceleration);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("RetourDuJeuScience", null, -1);

				pcs.firePropertyChange("Test", null, -1);

			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);
		
		/**
		 * @author Ludovic Julien
		 * 
		 * m�thode qui permet avec un timer de prendre la valeur de la vitesse acutel de la voiture a chaquee 0.1 seconde 
		 * et de l'ajouter dans le tableau "vitesse" pour permettre de faire les graphiques
		 */
		Timer timerVitesse = new Timer(100, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		     //	maVoiture.ajouterVitesseParSeconde();
		    	double vitesseActuelle = zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getVoiture().getVitesse().module();
		    	  if (vitesseActuelle < 0) {
				    	vitesseActuelle = (vitesseActuelle*-1);
				    } 
               zoneVitesse.ajouterVitesse(vitesseActuelle);
                System.out.println("la vitesse est de: "+ vitesseActuelle);
		    }
		});
		
		/**
		 * @author Ludovic Julien
		 * 
		 * m�thode qui permet avec un timer de prendre la valeur de l'acceleration acutel de la voiture a chaquee 0.1 seconde 
		 * et de l'ajouter dans le tableau "acceleration" pour permettre de faire les graphiques
		 */
		Timer timerAcc = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double AccActuelle = zoneAnimPhysique.getRegroupement().getListePisteDeDepart().get(0).getVoiture().getAccel().module();
				if (AccActuelle < 0) {
					AccActuelle = (AccActuelle*-1);
			    } 
				zoneAcceleration.ajouterAcceleration(AccActuelle);
			}
			
		});
		
		/**
		 * @author Ludovic Julien
		 * 
		 * m�thode qui permet avec un timer d'ajouter une seconde supplementaire dans le tableaux temps en appelant la methode "ajouterTemps"
		 * a chaque 0.1 seconde pour permettre de faire les graphiques
		 */
		Timer timerTemps = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 zoneVitesse.ajouterTemps();
				 zoneAcceleration.ajouterTemps();
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
				
				//Ludovic Julien
				//active les timer "timerVitesse","timerAcc" et "timerTemps"
				timerVitesse.start();
				timerAcc.start();
				timerTemps.start();
			}
		});
		btnStart.setBounds(10, 563, 89, 76);
		add(btnStart);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.restartPos();
				btnNextImg.setEnabled(true);
				btnStart.setEnabled(true);
				pcs.firePropertyChange("CHECKBOXACTIVE", null, -1);
				
					//Ludovic Julien
					//appelle la methode renouvler pour clear les tableaux des graphique
					zoneVitesse.renouvlerTemps();
	                zoneVitesse.renouvlerVitesse();
	                zoneAcceleration.renouvlerAcceleration();
	                zoneAcceleration.renouvlerTemps();
			}
		});
		btnReset.setBounds(175, 563, 89, 76);
		add(btnReset);

		btnNextImg = new JButton("Next Img");
		btnNextImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.avancerUnPas();
			}
		});
		btnNextImg.setBounds(355, 563, 89, 76);
		add(btnNextImg);

		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.arreter();
				btnNextImg.setEnabled(true);
				btnStart.setEnabled(true);
				
				//Ludovic Julien
				//arrete les timer "timerVitesse","timerAcc" et "timerTemps"
				timerVitesse.stop();
				timerTemps.stop();
				timerAcc.stop();
			}
		});
		btnStop.setBounds(538, 563, 89, 76);
		add(btnStop);

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
	// Par Tan Tommy Rin

	public ZoneAnimPhysique getZoneAnimPhysique() {
		return zoneAnimPhysique;
	}

	/**
	 * Méthode qui permet de changer la zone d'animation par une nouvelle
	 * 
	 * @param zoneAnimPhysique La nouvelle zone d'animation
	 */
	// Par Tan Tommy Rin

	public void setZoneAnimPhysique(ZoneAnimPhysique zoneAnimPhysique) {
		this.zoneAnimPhysique = zoneAnimPhysique;
	}

	/**
	 * Méthode qui change le texte/l'information durant l'animation
	 * 
	 * @param evt Évènement lorsque l'information change
	 */
	// Par Tan Tommy Rin
	public void changementDeTextePendantLAnimation(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "tempsEcoule":
			lblTempsEcouleValeur.setText(String.format("%.2f", evt.getNewValue()));
		case "accEnXV1":
			lblAccEnXV1.setText(String.format("%.2f", evt.getNewValue()));
		case "accEnYV1":
			lblAccEnYV1.setText(String.format("%.2f", evt.getNewValue()));
		case "vitEnXV1":
			lblVitesseEnXV1.setText(String.format("%.2f", evt.getNewValue()));
		case "vitEnYV1":
			lblVitesseEnYV1.setText(String.format("%.2f", evt.getNewValue()));
		case "posEnXV1":
			lblPositionEnXV1.setText(String.format("%.2f", evt.getNewValue()));
		case "posEnYV1":
			lblPositionEnYV1.setText(String.format("%.2f", evt.getNewValue()));
		case "angleV1":
			lblAngleVoiture1Rad.setText(String.format("%.2f", evt.getNewValue()));
		case "nombreToursV1":
			lblNombreToursVoiture1.setText(String.format("%.0f", evt.getNewValue()));
		case "accEnXV2":
			lblAccEnXV2.setText(String.format("%.2f", evt.getNewValue()));
		case "accEnYV2":
			lblAccEnYV2.setText(String.format("%.2f", evt.getNewValue()));
		case "vitEnXV2":
			lblVitesseEnXV2.setText(String.format("%.2f", evt.getNewValue()));
		case "vitEnYV2":
			lblVitesseEnYV2.setText(String.format("%.2f", evt.getNewValue()));
		case "posEnXV2":
			lblPositionEnXV2.setText(String.format("%.2f", evt.getNewValue()));
		case "posEnYV2":
			lblPositionEnYV2.setText(String.format("%.2f", evt.getNewValue()));
		case "angleV2":
			lblAngleVoiture2Rad.setText(String.format("%.2f", evt.getNewValue()));
		case "nombreToursV2":
			lblNombreToursVoiture2.setText(String.format("%.0f", evt.getNewValue()));
		}
	}
}
