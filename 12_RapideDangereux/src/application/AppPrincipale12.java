package application;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import fenetre.ClassementParPiste;
import fenetre.FenetreEditeur;
import fenetre.FenetreJeuSansScientifique;
import fenetre.FenetreJeuScientifique;
import fenetre.FenetreMenu;
import fenetre.FenetreOptionMontre;
import fenetre.JeuOptions;
import fenetre.ModeDeJeu;
import fenetre.PanelRegroupement;
import interfaces.TypePiste;

/**
 * Application permettant d'illustrer une simulation physique
 * 
 * @author Alexis Pineda-Alvarado
 * @author Tan Tommy Rin
 * @author Ludovic Julien
 * @author Kevin Nguyen
 */

public class AppPrincipale12 extends JFrame {

	private JCheckBoxMenuItem checkBoxModeNonScientifique;

	private JCheckBoxMenuItem checkBoxAudio;

	private int nombrePiste = 1;

	private String nomFichBinRegroupement = "Piste" + nombrePiste + ".dat";

	private String sousDossierSurBureau = "SauvegardePiste";

	private static int audio = 1;

	private static boolean pisteAudio;

	private File fichierDeTravail = new File(System.getProperty("user.home"),
			"Desktop" + "\\" + sousDossierSurBureau + "\\" + nomFichBinRegroupement);

	/**
	 * Lancement de l'application.
	 * 
	 * @param args Liste de String
	 */
	// Alexis Pineda-Alvarado

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppPrincipale12 frame = new AppPrincipale12();
					frame.setVisible(true);
					frame.requestFocus();
					frame.checkBoxModeNonScientifique.setEnabled(false);
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							try {
								UIManager.setLookAndFeel(info.getClassName());
							} catch (Exception e) {
								e.printStackTrace();
							}
							break;
						}
					} // fin for

					frame.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if (e.getKeyCode() == KeyEvent.VK_P) {

							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Méthode qui permet de mettre les pistes déja enregistré sur le comboBox s'il
	 * existe des fichiers
	 * 
	 * @param fenEditeur la fenetre d'édition
	 */
	// Tan Tommy Rin
	public void ajouterModeEditeurComboBox(FenetreEditeur fenEditeur) {
		while (fichierDeTravail.exists()) {

			fenEditeur.getComboBoxPiste().addItem((String) nomFichBinRegroupement);
			nombrePiste++;
			nomFichBinRegroupement = ("Piste" + nombrePiste + ".dat");

			fichierDeTravail = new File(System.getProperty("user.home"),
					"Desktop" + "\\" + sousDossierSurBureau + "\\" + nomFichBinRegroupement);
			fenEditeur.getGestionFich().setNombrePiste(nombrePiste);

		}
	}

	/**
	 * Creation de la fenetre.
	 */
	// Alexis Pineda-Alvarado
	public AppPrincipale12() {

		Image img = OutilsImage.lireImage("icon.png");
		setIconImage(img);

		FenetreMenu fenMenu = new FenetreMenu();
		ModeDeJeu fenModeJeu = new ModeDeJeu();
		FenetreEditeur fenEditeur = new FenetreEditeur();
		FenetreJeuSansScientifique fenSansScience = new FenetreJeuSansScientifique();
		FenetreJeuScientifique fenJeuScience = new FenetreJeuScientifique();
		JeuOptions fenOptions = new JeuOptions();
		FenetreOptionMontre fenOptionMontre = new FenetreOptionMontre();
		ClassementParPiste fenRecord = new ClassementParPiste();

		ajouterModeEditeurComboBox(fenEditeur);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 800);
		setTitle("Rapide et Dangereux");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Options");
		menuBar.add(mnMenu);

		setContentPane(fenMenu);

		fenMenu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionChangeModeJeu(evt, fenMenu, fenModeJeu);
			}
		});

		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourMenu(evt, fenMenu, fenModeJeu);
			}
		});
		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionChangeEditeur(evt, fenModeJeu, fenEditeur);
			}
		});

		fenEditeur.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourModeJeu1(evt, fenModeJeu, fenEditeur, fenJeuScience);

			}
		});

		fenEditeur.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionChangeJeuCourse2(evt, fenOptionMontre, fenJeuScience, fenSansScience, fenEditeur);
				actionJouerDeEditeur(evt, fenEditeur, fenJeuScience);

				actionPisteCouleur(evt, fenOptions, fenJeuScience, fenSansScience, fenOptionMontre, fenEditeur);

			}
		});

		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionChangeOption(evt, fenModeJeu, fenOptionMontre, fenOptions);
			}
		});

		fenOptions.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourModeJeu2(evt, fenModeJeu, fenOptions);
			}
		});

		fenOptions.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionFenOptions(evt, fenJeuScience, fenOptions, fenSansScience);
			}
		});

		fenOptions.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionFenOptions2(evt, fenJeuScience, fenOptions, fenSansScience, fenRecord);
				actionPisteCouleur(evt, fenOptions, fenJeuScience, fenSansScience, fenOptionMontre, fenEditeur);
				actionChangeCouleurMatPiste(evt, fenOptions, fenJeuScience, fenSansScience, fenOptionMontre,
						fenEditeur);
			}
		});

		fenRecord.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionfenRecord(evt, fenOptions, fenRecord);
			}
		});

		fenJeuScience.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourOptions(evt, fenJeuScience, fenModeJeu);
			}
		});

		fenSansScience.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionRetourOptions2(evt, fenSansScience, fenModeJeu);
			}
		});

		fenModeJeu.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionChangeJeuOptionCourse(evt, fenModeJeu, fenOptionMontre);
			}
		});

		fenOptionMontre.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {

				actionChangeJeuCourse2(evt, fenOptionMontre, fenJeuScience, fenSansScience, fenEditeur);
				actionChangeCouleurMatPiste(evt, fenOptions, fenJeuScience, fenSansScience, fenOptionMontre,
						fenEditeur);
				actionPisteCouleur(evt, fenOptions, fenJeuScience, fenSansScience, fenOptionMontre, fenEditeur);

			}
		});

		fenOptionMontre.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionChangeJeuCourse(evt, fenOptionMontre, fenJeuScience, fenSansScience);
				actionRetourOptionCCM(evt, fenOptionMontre, fenModeJeu);
			}
		});

		fenRecord.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				actionfenRecord(evt, fenOptions, fenRecord);
			}
		});

		checkBoxModeNonScientifique = new JCheckBoxMenuItem("Mode Non-Scientifique");
		checkBoxModeNonScientifique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCheckBox(fenSansScience, fenJeuScience);
			}
		});
		mnMenu.add(checkBoxModeNonScientifique);

		checkBoxAudio = new JCheckBoxMenuItem("Effet sonore");
		checkBoxAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCheckBox2();
			}
		});
		mnMenu.add(checkBoxAudio);

	}

	/**
	 * méthode qui permet de supprimer les effet audio
	 * 
	 */
	// Ludovic Julien
	public void actionCheckBox2() {
		if (checkBoxAudio.isSelected()) {
			FenetreJeuScientifique.getClip().stop();
			FenetreJeuSansScientifique.getClip().stop();
			pisteAudio = true;
		} else {
			FenetreJeuScientifique.getClip().start();
			FenetreJeuSansScientifique.getClip().start();
			pisteAudio = false;
		}
	}

	/**
	 * méthode qui retourne true si l'option checkbox pour supprimer les effets
	 * sonor est cocher
	 * 
	 * @return pisteAudio
	 */
	// Ludovic Julien
	public static boolean getCheckAudio() {
		return pisteAudio;

	}

	/**
	 * Méthode qui permet de changer de fenetre selon le check box
	 * 
	 * @param jeuSansScience La fenetre de jeu non scientifique
	 * @param fenJeuScience  La fenetre de jeu scientifique
	 */
	// Alexis Pineda-Alvarado
	public void actionCheckBox(FenetreJeuSansScientifique jeuSansScience, FenetreJeuScientifique fenJeuScience) {
		if (checkBoxModeNonScientifique.isSelected()) {
			actionChangeDesTypeJeu(jeuSansScience, fenJeuScience);

		} else {
			actionRetourDesTypeJeu(jeuSansScience, fenJeuScience);

		}
	}

	/**
	 * Méthode permettant d'accomplir des actions selon des levés d'évènements liés
	 * à la fenetre de jeu d'options
	 * 
	 * @param evt            evenement
	 * @param fenJeuScience  la fenetre de jeu avec mode science activé
	 * @param fenOptions     la fenetre de jeu d'options
	 * @param fenSansScience la fenetre non scientifique
	 */

	// Tan Tommy Rin
	public void actionFenOptions(PropertyChangeEvent evt, FenetreJeuScientifique fenJeuScience, JeuOptions fenOptions,
			FenetreJeuSansScientifique fenSansScience) {
		switch (evt.getPropertyName()) {

		case "COMMENCER!":
			fenJeuScience.setVisible(true);
			fenOptions.setVisible(false);
			setContentPane(fenJeuScience);
			checkBoxModeNonScientifique.setEnabled(true);
			pushingP(fenJeuScience);

			break;
		case "MASSE1":
			fenJeuScience.getZoneAnimPhysique().setVoitureMasse((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureMasse((double) evt.getNewValue());
			break;
		case "MASSE2":
			fenJeuScience.getZoneAnimPhysique().setVoitureMasse2((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureMasse2((double) evt.getNewValue());
			break;
		case "VITESSEMAXFACILE":
			fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenJeuScience.getZoneAnimPhysique().setTempsMontreFacile(100000.0);
			break;
		case "VITESSEMAXINTERMEDIAIRE":
			fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenJeuScience.getZoneAnimPhysique().setTempsMontreFacile(100000.0);
			break;
		case "VITESSEMAXAVANCE":
			fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenJeuScience.getZoneAnimPhysique().setTempsMontreFacile(100000.0);
			break;
		case "TYPEPISTE":
			fenJeuScience.getZoneAnimPhysique().setTypePiste((TypePiste) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setTypePiste((TypePiste) evt.getNewValue());
			fenJeuScience.getZoneAnimPhysique().restartPosPisteDepart();
			fenSansScience.getZoneAnimPhysique().restartPosPisteDepart();
			fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture()
					.setNombreToursFaits(0);
			fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture2()
					.setNombreToursFaits(0);
			fenOptions.getZoneAnimPhysique().setTypePiste((TypePiste) evt.getNewValue());

			break;
		case "NBBOITE":
			Double newData = new Double((double) evt.getNewValue());
			int valeur = newData.intValue();

			fenJeuScience.getZoneAnimPhysique().setNombreBlocMystere(valeur);
			fenSansScience.getZoneAnimPhysique().setNombreBlocMystere(valeur);
			break;

		case "MATPISTEASPHALT":
			fenJeuScience.getZoneAnimPhysique().setTestFrottement(0.25);
			fenSansScience.getZoneAnimPhysique().setTestFrottement(0.25);
			break;

		case "MATPISTESABLE":
			fenJeuScience.getZoneAnimPhysique().setTestFrottement(0.70);
			fenSansScience.getZoneAnimPhysique().setTestFrottement(0.70);
			break;

		case "MATPISTEGLACE":
			fenJeuScience.getZoneAnimPhysique().setTestFrottement(0.02);
			fenSansScience.getZoneAnimPhysique().setTestFrottement(0.02);
			break;

		case "COULEURMATPISTE":
			fenJeuScience.getZoneAnimPhysique().changerTexture((Color) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().changerTexture((Color) evt.getNewValue());
			break;
		}
	}

	/**
	 * Méthode permettant d'accomplir des actions selon des levés d'évènements liés
	 * à la fenetre de jeu d'options
	 * 
	 * @param evt            evenement
	 * @param fenJeuScience  la fenetre de jeu avec mode science activé
	 * @param fenOptions     la fenetre de jeu d'options
	 * @param fenSansScience la fenetre non scientifique
	 * @param fenRecord      la fenetre avec le classement par piste
	 */
	// Ludovic Julien
	public void actionFenOptions2(PropertyChangeEvent evt, FenetreJeuScientifique fenJeuScience, JeuOptions fenOptions,
			FenetreJeuSansScientifique fenSansScience, ClassementParPiste fenRecord) {
		switch (evt.getPropertyName()) {
		case "SKIN":
			fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture()
					.setSkin((Color) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture()
					.setSkin((Color) evt.getNewValue());
			break;
		case "SKIN2":
			fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture2()
					.setSkin((Color) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture2()
					.setSkin((Color) evt.getNewValue());
			break;
		case "RECORD":
			fenRecord.setVisible(true);
			fenOptions.setVisible(false);
			setContentPane(fenRecord);
			break;
		case "SKINOPTIONS1":
			fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture()
					.setSkin((Color) evt.getNewValue());
			break;
		case "SKINOPTIONS2":
			fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture2()
					.setSkin((Color) evt.getNewValue());
		}
	}


	/**
	 * Méthode permettant d'accomplir des actions selon des levés d'évènements liés
	 * à la fenetre d'édition
	 * 
	 * @param evt             evenement
	 * @param fenOptionMontre fenetre du mode course contre la montre
	 * @param fenJeuScience   la fenetre de jeu avec mode science activé
	 * @param fenSansScience  la fenetre non scientifique
	 * @param fenEditeur      la fenetre d'édition
	 */
	// Ludovic Julien
	public void actionChangeJeuCourse2(PropertyChangeEvent evt, FenetreOptionMontre fenOptionMontre,
			FenetreJeuScientifique fenJeuScience, FenetreJeuSansScientifique fenSansScience,
			FenetreEditeur fenEditeur) {
		switch (evt.getPropertyName()) {
		case "SKIN":
			fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture()
					.setSkin((Color) evt.getNewValue());
//			fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture()
//					.setSkin((Color) evt.getNewValue());
			break;
		case "SKIN2":
			fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture2()
					.setSkin((Color) evt.getNewValue());
//			fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture2()
//					.setSkin((Color) evt.getNewValue());
			break;
		case "SKINOPTIONS1":
			fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture()
					.setSkin((Color) evt.getNewValue());
			break;
		case "SKINOPTIONS2":
			fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0).getVoiture2()
					.setSkin((Color) evt.getNewValue());

			break;
		case "SKINOPTIONS3":
			fenEditeur.getPanelObjet().getPisteDeDepart().getVoiture().setSkin((Color) evt.getNewValue());
			fenEditeur.getPanelRegroupement().getListePisteDeDepart().get(0).getVoiture()
					.setSkin((Color) evt.getNewValue());
			break;
		case "SKINOPTIONS4":
			fenEditeur.getPanelObjet().getPisteDeDepart().getVoiture2().setSkin((Color) evt.getNewValue());
			fenEditeur.getPanelRegroupement().getListePisteDeDepart().get(0).getVoiture2()
					.setSkin((Color) evt.getNewValue());
			break;

		}

	}

	/**
	 * 
	 * Méthode qui permet de montrer la couleur de la piste dans les fenêtres
	 * d'options dependant de le matériel de la piste choisi
	 * 
	 * @param evt             evenement
	 * @param fenOptions      fenetre des paramètres du mode monde
	 * @param fenOptionMontre fenetre du mode course contre la montre
	 * @param fenJeuScience   la fenetre de jeu avec mode science activé
	 * @param fenSansScience  la fenetre non scientifique
	 * @param fenEditeur      la fenetre d'édition
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeCouleurMatPiste(PropertyChangeEvent evt, JeuOptions fenOptions,
			FenetreJeuScientifique fenJeuScience, FenetreJeuSansScientifique fenSansScience,
			FenetreOptionMontre fenOptionMontre, FenetreEditeur fenEditeur) {
		switch (evt.getPropertyName()) {

		case "COULEURPISTEASPHALT":

			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().size() != 0) {
				fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
						.setColor((Color) evt.getNewValue());
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			break;

		case "COULEURPISTESABLE":
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().size() != 0) {
				fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
						.setColor((Color) evt.getNewValue());
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			break;

		case "COULEURPISTEGLACE":
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().size() != 0) {
				fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
						.setColor((Color) evt.getNewValue());
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().size() != 0) {
				for (int a = 0; a < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
						.size(); a++) {
					fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			break;
		case "COULEURPISTEASPHALT2":
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().size() != 0) {
				fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
						.setColor((Color) evt.getNewValue());
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			break;
		case "COULEURPISTESABLE2":
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().size() != 0) {
				fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
						.setColor((Color) evt.getNewValue());
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			break;

		case "COULEURPISTEGLACE2":
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().size() != 0) {
				fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
						.setColor((Color) evt.getNewValue());
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			if (fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().size() != 0) {
				for (int a = 0; a < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
						.size(); a++) {
					fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(a)
							.setColor((Color) evt.getNewValue());
				}
			}
			break;
		}

	}

	/**
	 * Méthode permettant d'accomplir des actions selon des levés d'évènements liés
	 * à la fenetre de classement par piste
	 * 
	 * @param evt        evenement
	 * @param fenOptions la fenetre de jeu d'options
	 * @param fenRecord  la fenetre avec le classement par piste
	 */
	// Ludovic Julien
	public void actionfenRecord(PropertyChangeEvent evt, JeuOptions fenOptions, ClassementParPiste fenRecord) {
		switch (evt.getPropertyName()) {
		case "QUITTER":

			fenOptions.setVisible(true);
			fenRecord.setVisible(false);
			setContentPane(fenOptions);
			break;
		}

	}

	/**
	 * Méthode permettant d'accomplir des actions selon des levés d'évènements liés
	 * à la fenetre du menu et la fenetre mde de jeu
	 * 
	 * @param evt        evenement
	 * @param fenMenu    la fenêtre du menu
	 * @param fenModeJeu la fenêtre du mode de jeu a choisir qui va être activé
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeModeJeu(PropertyChangeEvent evt, FenetreMenu fenMenu, ModeDeJeu fenModeJeu) {
		if (evt.getPropertyName().equals("JOUER")) {

			fenMenu.setVisible(false);
			fenModeJeu.setVisible(true);
			setContentPane(fenModeJeu);
		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre menu et celui du mode de jeu
	 * 
	 * @param evt        evenement
	 * @param fenMenu    la fenêtre du menu qui va être activé
	 * @param fenModeJeu la fenêtre du mode de jeu a choisir
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourMenu(PropertyChangeEvent evt, FenetreMenu fenMenu, ModeDeJeu fenModeJeu) {
		switch (evt.getPropertyName()) {

		case "Retour":
			fenModeJeu.setVisible(false);
			fenMenu.setVisible(true);
			setContentPane(fenMenu);
			checkBoxModeNonScientifique.setEnabled(false);
			break;

		}
	}

	/**
	 * Méthode qui change de la fenetre mode de jeu à la fenetre du mode éditeur
	 * avec les levés d'évenements
	 * 
	 * @param evt        evenement
	 * @param fenModeJeu la fenêtre du mode de jeu a choisir
	 * @param fenEditeur fenêtre du mode editeur qui est activé
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeEditeur(PropertyChangeEvent evt, ModeDeJeu fenModeJeu, FenetreEditeur fenEditeur) {
		switch (evt.getPropertyName()) {

		case "EDITEUR":
			fenModeJeu.setVisible(false);
			fenEditeur.setVisible(true);

			setContentPane(fenEditeur);
			break;

		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre du mod de jeu et celui du mode éditeur
	 * 
	 * @param evt           evenement
	 * @param fenModeJeu    la fenêtre du mode de jeu a choisir qui est activé
	 * @param fenEditeur    fenêtre du mode editeur
	 * @param fenJeuScience la fenêtre du jeu scientifique
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourModeJeu1(PropertyChangeEvent evt, ModeDeJeu fenModeJeu, FenetreEditeur fenEditeur,
			FenetreJeuScientifique fenJeuScience) {
		switch (evt.getPropertyName()) {

		case "Retour":

			fenModeJeu.setVisible(true);
			fenEditeur.setVisible(false);
			setContentPane(fenModeJeu);
			fenJeuScience.getZoneAnimPhysique().restartPosPisteDepart();
			fenJeuScience.getBtnStart().setEnabled(true);
			break;

		}
	}

	/**
	 * Méthode qui permet de passer au panel du jeu sans le mode scientifique activé
	 * selon des levés d'évènements liés entre la fenetre du jeu sans le mode
	 * scientifique et celui du mode éditeur
	 * 
	 * @param evt        evenement
	 * @param fenEditeur fenêtre du mode editeur
	 * @param fenScience la fenetre de jeu avec le mode scientifique
	 */
	// Tan Tommy Rin

	public void actionJouerDeEditeur(PropertyChangeEvent evt, FenetreEditeur fenEditeur,
			FenetreJeuScientifique fenScience) {
		switch (evt.getPropertyName()) {
		case "JOUEREDITEUR":
			fenScience.setVisible(true);
			fenEditeur.setVisible(false);
			setContentPane(fenScience);
			fenScience.getZoneAnimPhysique().setTempsMontreFacile(100000.0);
			break;
		case "REGROUPEMENT":
			fenScience.getZoneAnimPhysique().setNomFichierRegroupement((String) evt.getNewValue());
			fenScience.getZoneAnimPhysique().setTypePiste(TypePiste.AUTRE);

		}
	}

	/**
	 * Méthode qui change de la fenetre mode de jeu à la fenetre options avec les
	 * levés d'évenements
	 * 
	 * @param evt             evenement
	 * @param fenModeJeu      la fenêtre du mode de jeu a choisir
	 * @param fenOptionMontre la fenetre option du mode de jeu "Course contre la
	 *                        montre"
	 * @param fenOptions      fenêtre des options du jeu qui va être activé
	 * 
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeOption(PropertyChangeEvent evt, ModeDeJeu fenModeJeu, FenetreOptionMontre fenOptionMontre,
			JeuOptions fenOptions) {
		switch (evt.getPropertyName()) {

		case "MONDE":
			fenModeJeu.setVisible(false);
			fenOptions.setVisible(true);
			setContentPane(fenOptions);
			break;
		case "PISTECREATION":
			fenOptions.getZoneAnimPhysique().setTypePiste((TypePiste) evt.getNewValue());
			fenOptionMontre.getZoneAnimPhysique().setTypePiste((TypePiste) evt.getNewValue());
		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre du mode de jeu et celui de la fenetre des
	 * options
	 * 
	 * @param evt        evenement
	 * @param fenModeJeu la fenêtre du mode de jeu a choisir qui va être acrivé
	 * @param fenOptions fenêtre des options du jeu
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourModeJeu2(PropertyChangeEvent evt, ModeDeJeu fenModeJeu, JeuOptions fenOptions) {
		switch (evt.getPropertyName()) {

		case "Retour":
			fenModeJeu.setVisible(true);
			fenOptions.setVisible(false);
			setContentPane(fenModeJeu);
			break;
		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre du jeu et celui de la fenetre des options
	 * 
	 * @param evt           evenement
	 * @param fenJeuScience fenêtre du jeu avec les paramètres scientifiques
	 * @param fenModeJeu    la fenêtre du mode de jeu a choisir qui va être acrivé
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourOptions(PropertyChangeEvent evt, FenetreJeuScientifique fenJeuScience,
			ModeDeJeu fenModeJeu) {

		switch (evt.getPropertyName()) {
		case "RetourDuJeuScience":
			fenJeuScience.setVisible(false);
			fenModeJeu.setVisible(true);
			setContentPane(fenModeJeu);
			fenJeuScience.getZoneAnimPhysique().restartPosPisteDepart();
			fenJeuScience.getBtnStart().setEnabled(true);

			break;

		case "STARTBUTTONACTIVE":
			checkBoxModeNonScientifique.setEnabled(false);
			break;
		case "CHECKBOXACTIVE":
			checkBoxModeNonScientifique.setEnabled(true);

		}
	}

	/**
	 * Méthode qui permet de retourner au panel précédent selon des levés
	 * d'évènements liés entre la fenetre du jeu sans les parametres scientifique et
	 * celui de la fenetre des options
	 * 
	 * @param evt            evenement
	 * @param fenSansScience fenêtre du jeu sans les paramètres scientifiques
	 * @param fenOptions     la fenetre des options du jeu qui va être activé
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourOptions2(PropertyChangeEvent evt, FenetreJeuSansScientifique fenSansScience,
			ModeDeJeu fenModeJeu) {

		switch (evt.getPropertyName()) {
		case "Retour":
			fenSansScience.setVisible(false);
			fenModeJeu.setVisible(true);
			setContentPane(fenModeJeu);
			fenSansScience.getZoneAnimPhysique().restartPosPisteDepart();
			fenSansScience.getBtnStart().setEnabled(true);
			break;

		}
	}

	/**
	 * Méthode qui permet de changer de la fenetre du jeu avec les paremetres
	 * scientifiques avec la fenetre du jeu sans les paramatres scientifique
	 * 
	 * @param fenSansScience fenêtre du jeu sans les paramètres scientifiques
	 * @param fenJeuScience  fenêtre du jeu avec les paramètres scientifiques
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeDesTypeJeu(FenetreJeuSansScientifique fenSansScience,
			FenetreJeuScientifique fenJeuScience) {

		fenSansScience.setVisible(true);
		fenJeuScience.setVisible(false);
		setContentPane(fenSansScience);
		fenJeuScience.getZoneAnimPhysique().requestFocusInWindow();

		checkBoxModeNonScientifique.setEnabled(true);
		pushingP(fenSansScience);

	}

	/**
	 * Méthode qui permet de changer de la fenetre du jeu avec sans les paremetres
	 * scientifiques avec la fenetre du jeu avec les paramatres scientifique
	 * 
	 * @param fenSansScience fenêtre du jeu sans les paramètres scientifiques
	 * @param fenJeuScience  fenêtre du jeu avec les paramètres scientifiques
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourDesTypeJeu(FenetreJeuSansScientifique fenSansScience,
			FenetreJeuScientifique fenJeuScience) {

		fenSansScience.setVisible(false);
		fenJeuScience.setVisible(true);
		setContentPane(fenJeuScience);

	}

	/**
	 * Méthode qui change la fenêtre du mode de jeu avec la fenêtre des options pour
	 * le mode Course contre la montre
	 * 
	 * @param evt             evenement
	 * @param fenModeJeu      fenêtre de la selection de mode de jeu
	 * @param fenOptionMontre fenêtre des paramètres a choisir dans le mode course
	 *                        contre la montre
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeJeuOptionCourse(PropertyChangeEvent evt, ModeDeJeu fenModeJeu,
			FenetreOptionMontre fenOptionMontre) {
		switch (evt.getPropertyName()) {
		case "COURSE CONTRE LA MONTRE":
			fenOptionMontre.setVisible(true);

			fenModeJeu.setVisible(false);
			setContentPane(fenOptionMontre);
			break;
		}
	}

	/**
	 * Méthode qui change la fenêtre des options pour le mode de jeu course contre
	 * la montre avec la fenêtre du jeu
	 * 
	 * @param evt             evenement
	 * @param fenOptionMontre fenêtre des paramètres a choisir dans le mode course
	 *                        contre la montre
	 * @param fenJeuScience   fenetre avec le mode scientifique
	 * @param fenSansScience  fenetre avec le mode sans les paramètres non
	 *                        scientifique
	 */
	// Alexis Pineda-Alvarado
	public void actionChangeJeuCourse(PropertyChangeEvent evt, FenetreOptionMontre fenOptionMontre,
			FenetreJeuScientifique fenJeuScience, FenetreJeuSansScientifique fenSansScience) {
		switch (evt.getPropertyName()) {
		case "COMMENCER COURSE MONTRE":
			fenOptionMontre.setVisible(false);
			fenJeuScience.setVisible(true);
			setContentPane(fenJeuScience);
			fenJeuScience.getZoneAnimPhysique().getRegroupement().setNombreBoiteMystere(0);
			fenSansScience.getZoneAnimPhysique().getRegroupement().setNombreBoiteMystere(0);
			checkBoxModeNonScientifique.setEnabled(true);
			break;
		case "TYPEPISTE":
			fenJeuScience.getZoneAnimPhysique().setTypePiste((TypePiste) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setTypePiste((TypePiste) evt.getNewValue());
			fenJeuScience.getZoneAnimPhysique().restartPosPisteDepart();
			fenSansScience.getZoneAnimPhysique().restartPosPisteDepart();
			break;
		case "MASSEMONTRE1":
			fenJeuScience.getZoneAnimPhysique().setVoitureMasse((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureMasse((double) evt.getNewValue());
			break;
		case "MASSEMONTRE2":
			fenJeuScience.getZoneAnimPhysique().setVoitureMasse2((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureMasse2((double) evt.getNewValue());
			break;
		case "VITESSEMAXFACILE2":
			fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenJeuScience.getZoneAnimPhysique().arretTempFacile();
			fenJeuScience.getZoneAnimPhysique().setTempsMontreFacile(130.0);
			break;
		case "VITESSEMAXINTERMEDIAIRE2":
			fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenJeuScience.getZoneAnimPhysique().setTempsMontreFacile(110.0);
			break;
		case "VITESSEMAXAVANCE2":
			fenJeuScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().setVoitureVitesseMax((double) evt.getNewValue());
			fenJeuScience.getZoneAnimPhysique().setTempsMontreFacile(80.0);
			break;
		case "NBRDETOUR":
			fenJeuScience.getZoneAnimPhysique().getRegroupement().setNombreToursAFaire((double) evt.getNewValue());
			fenSansScience.getZoneAnimPhysique().getRegroupement().setNombreToursAFaire((double) evt.getNewValue());
			break;
		case "MATPISTEASPHALT":
			fenJeuScience.getZoneAnimPhysique().setTestFrottement(0.25);
			break;

		case "MATPISTESABLE":
			fenJeuScience.getZoneAnimPhysique().setTestFrottement(0.70);
			break;

		case "MATPISTEGLACE":
			fenJeuScience.getZoneAnimPhysique().setTestFrottement(0.02);
			break;

		case "COULEURMATPISTE":
			fenJeuScience.getZoneAnimPhysique().changerTexture((Color) evt.getNewValue());
			break;
		}
	}

	/**
	 * Méthode qui change le panel des options pour le mode course contre la montre
	 * avec le panel de mode de jeu
	 * 
	 * @param evt             evenement
	 * @param fenOptionMontre fenêtre des paramètres a choisir dans le mode course
	 *                        contre la montre
	 * @param fenModeJeu      fenêtre de la selection de mode de jeu
	 */
	// Alexis Pineda-Alvarado
	public void actionRetourOptionCCM(PropertyChangeEvent evt, FenetreOptionMontre fenOptionMontre,
			ModeDeJeu fenModeJeu) {
		switch (evt.getPropertyName()) {
		case "Retour":
			fenOptionMontre.setVisible(false);
			fenModeJeu.setVisible(true);
			setContentPane(fenModeJeu);
			break;
		}
	}

	/**
	 * Méthode qui change la couleur des bordures de pistes selon l'option choisi
	 * dans le panel option
	 * 
	 * @param evt             evenement
	 * @param fenJeuScience   fenètre jeu scientifique
	 * @param fenOptions      fenètre choix des options du jeu monde
	 * @param fenSansScience  fenètre jeu non-scientifique
	 * @param fenOptionMontre fenètre choix des options du jeu course contre la
	 *                        montre
	 * @param fenEditeur      fenètre éditeur
	 * 
	 */
	// Kevin Nguyen
	public void actionPisteCouleur(PropertyChangeEvent evt, JeuOptions fenOptions, FenetreJeuScientifique fenJeuScience,
			FenetreJeuSansScientifique fenSansScience, FenetreOptionMontre fenOptionMontre, FenetreEditeur fenEditeur) {
		switch (evt.getPropertyName()) {
		case "COULEURPISTE":

			fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
					.setBordure((Color) evt.getNewValue());
			for (int i = 0; i < fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
					.size(); i++) {
				fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
					.size(); i++) {
				fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
					.size(); i++) {
				fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
					.size(); i++) {
				fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
					.size(); i++) {
				fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
					.size(); i++) {
				fenJeuScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(i)
						.setBordure((Color) evt.getNewValue());
			}

			fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
					.setBordure((Color) evt.getNewValue());
			for (int i = 0; i < fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
					.size(); i++) {
				fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
					.size(); i++) {
				fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
					.size(); i++) {
				fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
					.size(); i++) {
				fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
					.size(); i++) {
				fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
					.size(); i++) {
				fenSansScience.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(i)
						.setBordure((Color) evt.getNewValue());
			}

			break;
		case "COULEURPISTE2":
			fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
					.setBordure((Color) evt.getNewValue());
			for (int i = 0; i < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
					.size(); i++) {
				fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
					.size(); i++) {
				fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
					.size(); i++) {
				fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
					.size(); i++) {
				fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
					.size(); i++) {
				fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
					.size(); i++) {
				fenOptions.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			break;
		case "COULEURPISTE3":
			fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteDeDepart().get(0)
					.setBordure((Color) evt.getNewValue());
			for (int i = 0; i < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale()
					.size(); i++) {
				fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteHorizontale().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale()
					.size(); i++) {
				fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVerticale().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche()
					.size(); i++) {
				fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageGauche().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit()
					.size(); i++) {
				fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageDroit().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut()
					.size(); i++) {
				fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageHaut().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas()
					.size(); i++) {
				fenOptionMontre.getZoneAnimPhysique().getRegroupement().getListePisteVirageBas().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			break;
		case "COULEURPISTE4":
			fenEditeur.getPanelRegroupement().getListePisteDeDepart().get(0).setBordure((Color) evt.getNewValue());
			for (int i = 0; i < fenEditeur.getPanelRegroupement().getListePisteHorizontale().size(); i++) {
				fenEditeur.getPanelRegroupement().getListePisteHorizontale().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenEditeur.getPanelRegroupement().getListePisteVerticale().size(); i++) {
				fenEditeur.getPanelRegroupement().getListePisteVerticale().get(i).setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenEditeur.getPanelRegroupement().getListePisteVirageGauche().size(); i++) {
				fenEditeur.getPanelRegroupement().getListePisteVirageGauche().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenEditeur.getPanelRegroupement().getListePisteVirageDroit().size(); i++) {
				fenEditeur.getPanelRegroupement().getListePisteVirageDroit().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenEditeur.getPanelRegroupement().getListePisteVirageHaut().size(); i++) {
				fenEditeur.getPanelRegroupement().getListePisteVirageHaut().get(i)
						.setBordure((Color) evt.getNewValue());
			}
			for (int i = 0; i < fenEditeur.getPanelRegroupement().getListePisteVirageBas().size(); i++) {
				fenEditeur.getPanelRegroupement().getListePisteVirageBas().get(i).setBordure((Color) evt.getNewValue());
			}

			fenEditeur.getPanelObjet().getPisteDeDepart().setBordure((Color) evt.getNewValue());

			fenEditeur.getPanelObjet().getPisteHorizontale().setBordure((Color) evt.getNewValue());

			fenEditeur.getPanelObjet().getPisteVerticale().setBordure((Color) evt.getNewValue());

			fenEditeur.getPanelObjet().getPisteVirageGauche().setBordure((Color) evt.getNewValue());

			fenEditeur.getPanelObjet().getPisteVirageDroit().setBordure((Color) evt.getNewValue());

			fenEditeur.getPanelObjet().getPisteVirageHaut().setBordure((Color) evt.getNewValue());

			fenEditeur.getPanelObjet().getPisteVirageBas().setBordure((Color) evt.getNewValue());
			break;
		}
	}

	/**
	 * Fait en sorte que lorsqu'on appui sur la touche P un panel JOptionPane va
	 * apparaitre
	 * 
	 * @param fenetreVoulu permet de prendre le JPanel specifique
	 */
	// Alexis Pineda-Alvarado
	public void pushingP(JPanel fenetreVoulu) {
		fenetreVoulu.requestFocus();
		fenetreVoulu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_P) {
					JOptionPane.showMessageDialog(null,
							"Bonjour dans RAPIDE ET DANGEREUX! \nle but de ce jeux et de battre votre combattant"
									+ " \nles contrôles du jeu sont :  \n↑ : pour avancer la voiture"
									+ " \n� et → : pour tourner a gauche et a droite \n↓ : pour ralentir la voiture"
									+ "\nLes boîtes jaunes choisisent un effet mis sur la voiture au hasard lorsque vous la toucher");
				}
			}
		});
	}

}
