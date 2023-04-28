package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;
import dessin.ZoneAnimPhysique;

import application.AppPrincipale12;
import application.OutilsImage;

import javax.swing.JProgressBar;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Classe qui permet de créer une fenetre de jeu sans le mode scientifique
 * activé
 * 
 * @author Tan Tommy Rin
 * @author Ludovic Julien
 * @author Alexis Pineda-Alvarado
 */

public class FenetreJeuSansScientifique extends JPanel {

	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	private ZoneAnimPhysique zoneAnimPhysique;
	private JButton btnNextImg;
	private JButton btnStart;
	private JLabel lblTempsEcouleValeur;
	private static Clip clip;
	private static Clip clip2;
	private static Clip clip3;
	private static Clip clip4;
	private static String pistemusic;
	private JProgressBar progressBarFroce2;
	private JProgressBar progressBarFroce;
	private JLabel lblImageObjet1;
	private JLabel lblImageObjet2;
	private JLabel lblBackgroundBleu;
	private Image champignon;
	private Image bouleNeige1;
	private Image champignon2;
	private Image bouleNeige2;
	private Image trouNoir1;
	private Image trouNoir2;
	private Image colle1;
	private Image colle2;
	private JLabel lblNombreToursVoiture2;
	private JLabel lblNombreToursVoiture1;
	private JLabel lblNbToursAFaire2;
	private JTextArea txtArea;
	private JButton btnReset;
	private JButton btnStop;
	public static Boolean annim;

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 * 
	 * @param listener L'écouteur
	 */
	// Tan Tommy Rin
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	/**
	 * Méthode qui permet de crée la fenetre
	 */
	// Tan Tommy Rin
	public FenetreJeuSansScientifique() {
		setLayout(null);
		setBounds(100, 100, 1600, 800);

		lireMusic();
		lireMusic2();
		lireMusic3();
		lireMusic4();

		JPanel panel = new JPanel();
		panel.setBounds(10, 373, 145, 304);
		add(panel);
		panel.setLayout(null);

		JScrollPane spPourMessage = new JScrollPane();
		spPourMessage.setBounds(0, 0, 145, 304);
		panel.add(spPourMessage);

		txtArea = new JTextArea();
		spPourMessage.setViewportView(txtArea);
		txtArea.setEditable(false);
		txtArea.setForeground(Color.RED);
		txtArea.setFont(new Font("Dubai", Font.PLAIN, 12));
		txtArea.setText("Pessez sur le bouton Start pour démarrer le jeu!");
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);

		JLabel lblpixelsm = new JLabel("1.4640625pixels/m                                                   ");
		lblpixelsm.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblpixelsm.setBounds(165, 705, 1026, 14);
		add(lblpixelsm);

		JLabel lblNewLabel12 = new JLabel("80m/Morceau de piste");
		lblNewLabel12.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblNewLabel12.setBounds(1033, 705, 158, 14);
		add(lblNewLabel12);

		JLabel lblNombreToursV2 = new JLabel("Nombre de tours :");
		lblNombreToursV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursV2.setBounds(1282, 449, 132, 29);
		add(lblNombreToursV2);

		JLabel lblNombreTourV2 = new JLabel("Tours");
		lblNombreTourV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreTourV2.setBounds(1528, 449, 59, 29);
		add(lblNombreTourV2);

		lblNombreToursVoiture2 = new JLabel("0");
		lblNombreToursVoiture2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursVoiture2.setBounds(1424, 449, 30, 29);
		add(lblNombreToursVoiture2);

		JLabel lblSurNbTours2 = new JLabel("/");
		lblSurNbTours2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSurNbTours2.setBounds(1453, 449, 17, 29);
		add(lblSurNbTours2);

		lblNbToursAFaire2 = new JLabel("3");
		lblNbToursAFaire2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNbToursAFaire2.setBounds(1475, 449, 30, 29);
		add(lblNbToursAFaire2);
		zoneAnimPhysique = new ZoneAnimPhysique();
		zoneAnimPhysique.setBorder(null);
		zoneAnimPhysique.setBounds(165, 27, 1026, 650);
		add(zoneAnimPhysique);
		zoneAnimPhysique.setLayout(null);
		zoneAnimPhysique.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				changementDeTextePendantLAnimation(evt);
			}
		});
		JLabel lblTitreModeScientifique = new JLabel("Jeu");
		lblTitreModeScientifique.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitreModeScientifique.setBounds(650, 0, 47, 22);
		add(lblTitreModeScientifique);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBtnRetour();
				resetMusic();
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		btnReset = new JButton("");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBtnReset();
				resetMusic();
			}
		});
		btnReset.setBounds(30, 219, 97, 58);
		add(btnReset);

		Image reset = OutilsImage.lireImageEtRedimensionner("reset.png", 97, 58);
		if (reset != null) {
			btnReset.setIcon(new ImageIcon(reset));
			reset.flush();
		}

		btnStop = new JButton("");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				actionBtnStop();

				arretMusic();
			}
		});
		btnStop.setBounds(30, 288, 97, 58);
		add(btnStop);

		Image stop = OutilsImage.lireImageEtRedimensionner("stop.png", 97, 58);
		if (stop != null) {
			btnStop.setIcon(new ImageIcon(stop));
			stop.flush();
		}
		
		btnNextImg = new JButton("");
		btnNextImg.setBounds(30, 150, 97, 58);
		add(btnNextImg);
		
		Image nextImage = OutilsImage.lireImageEtRedimensionner("prochimage.png", 97, 58);
		if (nextImage != null) {
			btnNextImg.setIcon(new ImageIcon(nextImage));
			nextImage.flush();
		}

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musicStart();
			}
		});
		btnStart.setBounds(30, 81, 97, 58);
		add(btnStart);
		
		Image start = OutilsImage.lireImageEtRedimensionner("demarrer.png", 97, 58);
		if (start != null) {
			btnStart.setIcon(new ImageIcon(start));
			start.flush();
		}

		JLabel lblTemps = new JLabel("Temps écoulé : ");
		lblTemps.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTemps.setBounds(1282, 27, 153, 22);
		add(lblTemps);

		lblTempsEcouleValeur = new JLabel("0.00");
		lblTempsEcouleValeur.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTempsEcouleValeur.setBounds(1437, 27, 93, 22);
		add(lblTempsEcouleValeur);

		JLabel lblS = new JLabel("s");
		lblS.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblS.setBounds(1500, 27, 36, 22);
		add(lblS);
		JLabel lblForce = new JLabel("Force lancement voiture 1 :");
		lblForce.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblForce.setBounds(1282, 70, 271, 22);
		add(lblForce);

		progressBarFroce = new JProgressBar();
		progressBarFroce.setBounds(1282, 103, 248, 45);
		add(progressBarFroce);
		progressBarFroce.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBarFroce.setStringPainted(true);

		JLabel lblForce12 = new JLabel("Force lancement voiture 1 :");
		lblForce12.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblForce12.setBounds(1282, 373, 271, 22);
		add(lblForce12);

		progressBarFroce2 = new JProgressBar();
		progressBarFroce2.setStringPainted(true);
		progressBarFroce2.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBarFroce2.setBounds(1282, 406, 248, 45);
		add(progressBarFroce2);

		lblImageObjet1 = new JLabel("");
		lblImageObjet1.setBounds(1282, 206, 248, 169);
		add(lblImageObjet1);

		lblImageObjet2 = new JLabel("");
		lblImageObjet2.setBounds(1282, 508, 248, 169);
		add(lblImageObjet2);

		JLabel lblObjetVoiture = new JLabel("Objet special voiture 1 :");
		lblObjetVoiture.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblObjetVoiture.setBounds(1282, 173, 271, 22);
		add(lblObjetVoiture);

		JLabel lblObjetSpecialVoiture = new JLabel("Objet special voiture 2 :");
		lblObjetSpecialVoiture.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblObjetSpecialVoiture.setBounds(1282, 477, 271, 22);
		add(lblObjetSpecialVoiture);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBtnStart();
				musicStart();

			}
		});
		btnNextImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionBtnProchImage();
			}
		});

		JLabel lblNombreToursV1 = new JLabel("Nombre de tours :");
		lblNombreToursV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursV1.setBounds(1282, 148, 132, 29);
		add(lblNombreToursV1);

		JLabel lblNbToursAFaire = new JLabel("3");
		lblNbToursAFaire.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNbToursAFaire.setBounds(1475, 148, 30, 29);
		add(lblNbToursAFaire);

		lblNombreToursVoiture1 = new JLabel("0");
		lblNombreToursVoiture1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreToursVoiture1.setBounds(1424, 148, 30, 29);
		add(lblNombreToursVoiture1);

		JLabel lblNombreTourV1 = new JLabel("Tours");
		lblNombreTourV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreTourV1.setBounds(1528, 148, 59, 29);
		add(lblNombreTourV1);

		JLabel lblSurNbTours = new JLabel("/");
		lblSurNbTours.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSurNbTours.setBounds(1453, 148, 17, 29);
		add(lblSurNbTours);

		lblBackgroundBleu = new JLabel("");
		lblBackgroundBleu.setBounds(0, 0, 1600, 800);
		add(lblBackgroundBleu);

		Image imgBleu = OutilsImage.lireImageEtRedimensionner("backgroundJeu.jpg", 1600, 800);
		if (imgBleu != null) {
			lblBackgroundBleu.setIcon(new ImageIcon(imgBleu));
			imgBleu.flush();
		}
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
		case "nombreToursV1":
			lblNombreToursVoiture1.setText(String.format("%.0f", evt.getNewValue()));
			break;
		case "nombreToursV2":
			lblNombreToursVoiture2.setText(String.format("%.0f", evt.getNewValue()));
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
		case "lblNombreToursVoiture2":
			Double newData3 = new Double((double) evt.getNewValue());
			int valeur3 = newData3.intValue();
			lblNombreToursVoiture2.setText(valeur3 + "");
			break;
		case "champignon1":
			champignon = OutilsImage.lireImageEtRedimensionner("champignon.png", 248, 169);
			if (champignon != null) {
				lblImageObjet1.setIcon(new ImageIcon(champignon));

			}
			break;
		case "bouleNeige1":
			bouleNeige1 = OutilsImage.lireImageEtRedimensionner("snowball.png", 248, 169);
			if (bouleNeige1 != null) {
				lblImageObjet1.setIcon(new ImageIcon(bouleNeige1));

			}
			break;
		case "trouNoir1":
			trouNoir1 = OutilsImage.lireImageEtRedimensionner("blackhole.png", 248, 169);
			if (trouNoir1 != null) {
				lblImageObjet1.setIcon(new ImageIcon(trouNoir1));

			}
			break;
		case "colle1":
			colle1 = OutilsImage.lireImageEtRedimensionner("Colle.png", 248, 169);
			if (colle1 != null) {
				lblImageObjet1.setIcon(new ImageIcon(colle1));

			}
			break;
		case "champignon2":
			champignon2 = OutilsImage.lireImageEtRedimensionner("champignon.png", 248, 169);
			if (champignon2 != null) {
				lblImageObjet2.setIcon(new ImageIcon(champignon2));

			}
			break;

		case "bouleNeige2":
			bouleNeige2 = OutilsImage.lireImageEtRedimensionner("snowball.png", 248, 169);
			if (bouleNeige2 != null) {
				lblImageObjet2.setIcon(new ImageIcon(bouleNeige2));

			}
			break;
		case "trouNoir2":
			trouNoir2 = OutilsImage.lireImageEtRedimensionner("blackhole.png", 248, 169);
			if (trouNoir2 != null) {
				lblImageObjet2.setIcon(new ImageIcon(trouNoir2));

			}
			break;
		case "colle2":
			colle2 = OutilsImage.lireImageEtRedimensionner("Colle.png", 248, 169);
			if (colle2 != null) {
				lblImageObjet2.setIcon(new ImageIcon(colle2));

			}
			break;
		case "reset1":
			progressBarFroce.setValue(0);
			lblImageObjet1.setIcon(null);

			break;
		case "reset2":
			progressBarFroce2.setValue(0);
			lblImageObjet2.setIcon(null);

			break;
		}
	}

	/**
	 * méthode qui permet de démarrer l'animation du jeu
	 */
	// Alexis Pineda-Alvarado
	private void actionBtnStart() {
		zoneAnimPhysique.requestFocusInWindow();
		zoneAnimPhysique.setEnCoursDAnimation(false);
		zoneAnimPhysique.demarrer();
		btnNextImg.setEnabled(false);
		btnStart.setEnabled(false);
		PCS.firePropertyChange("STARTBUTTONACTIVE", null, -1);
		txtArea.append("\nVous avez démarrer le jeu");
	}

	/**
	 * méthode qui permet d'arrêter l'animation du jeu
	 */
	// Alexis Pineda-Alvarado
	private void actionBtnStop() {
		zoneAnimPhysique.requestFocusInWindow();
		zoneAnimPhysique.arreter();
		btnNextImg.setEnabled(true);
		btnStart.setEnabled(true);
		txtArea.append("\nVous avez arrêter le jeu");
	}

	/**
	 * méthode qui permet reinitialiser le jeu
	 */
	// Alexis Pineda-Alvarado
	private void actionBtnReset() {
		zoneAnimPhysique.requestFocusInWindow();
		zoneAnimPhysique.restartPosPisteDepart();
		btnNextImg.setEnabled(false);
		btnStart.setEnabled(true);
		PCS.firePropertyChange("CHECKBOXACTIVE", null, -1);
		txtArea.append("\nVous avez reinitialiser le jeu");
	}

	/**
	 * méthode qui appui l'événement pour retourner au panel précédent
	 */
	// Alexis Pineda-Alvarado
	private void actionBtnRetour() {
		PCS.firePropertyChange("Retour", 0, -1);
	}

	/**
	 * méthode qui permet de simuler la prochaine image du jeu
	 */
	// Alexis Pineda-Alvarado
	private void actionBtnProchImage() {
		zoneAnimPhysique.requestFocusInWindow();
		zoneAnimPhysique.avancerUnPas();
		txtArea.append("\nVoici la prochaine image du jeu");
	}

	public ZoneAnimPhysique getZoneAnimPhysique() {
		return zoneAnimPhysique;
	}

	public void setZoneAnimPhysique(ZoneAnimPhysique zoneAnimPhysique) {
		this.zoneAnimPhysique = zoneAnimPhysique;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(JButton btnStart) {
		this.btnStart = btnStart;
	}

	public JButton getBtnNextImg() {
		return btnNextImg;
	}

	public void setBtnNextImg(JButton btnNextImg) {
		this.btnNextImg = btnNextImg;
	}

	/**
	 * méthode qui permet de lancer la piste audio
	 */
	// Ludovic Julien
	public void musicStart() {
		annim = true;
		if (AppPrincipale12.getCheckAudio() == false) {
			if (pistemusic == "Mexique") {
			clip.start();
			}else {
				if (pistemusic == "Italie") {
					clip2.start();
					}else {
						if (pistemusic == "Canada") {
							clip3.start();
						}else {
							if (pistemusic == "Autre") {
								clip4.start();
						}
					}
					}
			}
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
	
	public static Clip getClip2() {
		return clip2;
	}
	
	public static Clip getClip3() {
		return clip3;
	}
	
	public static Clip getClip4() {
		return clip4;
	}
	
	public static void setmusicPiste(String piste) {
		pistemusic = piste;
	}
	
	public static String getMusicPiste() {
		return pistemusic;
	}
	/**
	 * méthode qui permet d'arreter et de recommencer la music au debut
	 * 
	 */
	// Ludovic Julien
	public void resetMusic() {
		annim = false;
		if (clip != null) {
			clip.stop();
			clip.setMicrosecondPosition(0);
		}
		if (clip2 != null) {
			clip2.stop();
			clip2.setMicrosecondPosition(0);
		}
		if (clip3 != null) {
			clip3.stop();
			clip3.setMicrosecondPosition(0);
		}
		if (clip4 != null) {
			clip4.stop();
			clip4.setMicrosecondPosition(0);
		}
	}

	public static boolean getAnnim() {
		return annim;
	}
	
	/**
	 * méthode qui permet de lire un fichier audio
	 */
	// Ludovic Julien
	public void lireMusic() {
		try {
			
			clip = AudioSystem.getClip();
			URL resource = getClass().getClassLoader().getResource("Mexico.wav");
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(resource);
			clip.open(inputStream);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * méthode qui permet de lire un fichier audio
	 */
	// Ludovic Julien
	public void lireMusic2() {
		try {
			
			clip2 = AudioSystem.getClip();
			URL resource2 = getClass().getClassLoader().getResource("Italie.wav");
			AudioInputStream inputStream2 = AudioSystem.getAudioInputStream(resource2);
			clip2.open(inputStream2);
					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * méthode qui permet de lire un fichier audio
	 */
	// Ludovic Julien
	public void lireMusic3() {
		try {
			
			clip3 = AudioSystem.getClip();
			URL resource3 = getClass().getClassLoader().getResource("Canada.wav");
			AudioInputStream inputStream3 = AudioSystem.getAudioInputStream(resource3);
			clip3.open(inputStream3);
					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * méthode qui permet de lire un fichier audio
	 */
	// Ludovic Julien
	public void lireMusic4() {
		try {
			
			clip4 = AudioSystem.getClip();
			URL resource4 = getClass().getClassLoader().getResource("Kosmorider-Night.wav");
			AudioInputStream inputStream4 = AudioSystem.getAudioInputStream(resource4);
			clip4.open(inputStream4);
					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * méthode qui permet d'arreter une music
	 */
	// Ludovic Julien
	public void arretMusic() {
		annim = false;
			if (clip != null) {
				clip.stop();
			}
			if (clip2 != null) {
				clip2.stop();
			}
			if (clip3 != null) {
				clip3.stop();
			}
			if (clip4 != null) {
				clip4.stop();
			}
		}
}
