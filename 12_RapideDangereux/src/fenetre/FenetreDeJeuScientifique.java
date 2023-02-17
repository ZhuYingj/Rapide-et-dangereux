package fenetre;


import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dessin.ZoneAnimPhysique;
import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.Color;


public class FenetreDeJeuScientifique extends JFrame {


	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreDeJeuScientifique frame = new FenetreDeJeuScientifique();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public FenetreDeJeuScientifique() {

		setTitle("Mode scientifique");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ZoneAnimPhysique zoneAnimPhysique = new ZoneAnimPhysique();
		zoneAnimPhysique.setForeground(Color.BLACK);
		zoneAnimPhysique.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		zoneAnimPhysique.setBounds(10, 11, 813, 439);
		contentPane.add(zoneAnimPhysique);
		zoneAnimPhysique.setLayout(null);

		JSeparator separatorHaut = new JSeparator();
		separatorHaut.setForeground(Color.BLACK);
		separatorHaut.setBounds(860, 11, 414, 19);
		contentPane.add(separatorHaut);

		JSeparator separatorBas = new JSeparator();
		separatorBas.setForeground(Color.BLACK);
		separatorBas.setBounds(860, 448, 414, 19);
		contentPane.add(separatorBas);

		JSeparator separatorGauche = new JSeparator();
		separatorGauche.setForeground(Color.BLACK);
		separatorGauche.setOrientation(SwingConstants.VERTICAL);
		separatorGauche.setBounds(860, 11, 13, 439);
		contentPane.add(separatorGauche);

		JSeparator separatorDroite = new JSeparator();
		separatorDroite.setForeground(Color.BLACK);
		separatorDroite.setOrientation(SwingConstants.VERTICAL);
		separatorDroite.setBounds(1271, 11, 13, 439);
		contentPane.add(separatorDroite);

		JLabel lblVoiture1 = new JLabel("Voiture 1");
		lblVoiture1.setFont(new Font("Arial", Font.BOLD, 22));
		lblVoiture1.setBounds(975, 41, 105, 37);
		contentPane.add(lblVoiture1);

		JLabel lblVoiture2 = new JLabel("Voiture 2");
		lblVoiture2.setFont(new Font("Arial", Font.BOLD, 22));
		lblVoiture2.setBounds(1131, 41, 105, 37);
		contentPane.add(lblVoiture2);

		JLabel lblPosition = new JLabel("Position :");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPosition.setBounds(883, 101, 79, 14);
		contentPane.add(lblPosition);

		JLabel lblEnXPos = new JLabel("En x :");
		lblEnXPos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnXPos.setBounds(883, 135, 79, 14);
		contentPane.add(lblEnXPos);

		JLabel lblEnYPos = new JLabel("En y :");
		lblEnYPos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnYPos.setBounds(883, 174, 79, 14);
		contentPane.add(lblEnYPos);

		JLabel lblVitesse = new JLabel("Vitesse :");
		lblVitesse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesse.setBounds(883, 215, 79, 14);
		contentPane.add(lblVitesse);

		JLabel lblAcceleration = new JLabel("Accélération :");
		lblAcceleration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAcceleration.setBounds(883, 256, 125, 14);
		contentPane.add(lblAcceleration);

		JLabel lblFrottement = new JLabel("Frottement :");
		lblFrottement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFrottement.setBounds(883, 297, 125, 14);
		contentPane.add(lblFrottement);

		JLabel lblGravite = new JLabel("Gravité :");
		lblGravite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGravite.setBounds(883, 333, 125, 14);
		contentPane.add(lblGravite);

		JLabel lblFreinage = new JLabel("Freinage :");
		lblFreinage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFreinage.setBounds(883, 376, 125, 19);
		contentPane.add(lblFreinage);

		JSeparator separatorObjetHaut = new JSeparator();
		separatorObjetHaut.setForeground(Color.BLACK);
		separatorObjetHaut.setBounds(860, 461, 414, 19);
		contentPane.add(separatorObjetHaut);

		JSeparator separatorObjetBas = new JSeparator();
		separatorObjetBas.setForeground(Color.BLACK);
		separatorObjetBas.setBounds(860, 642, 414, 19);
		contentPane.add(separatorObjetBas);

		JSeparator separatorObjetGauche = new JSeparator();
		separatorObjetGauche.setForeground(Color.BLACK);
		separatorObjetGauche.setOrientation(SwingConstants.VERTICAL);
		separatorObjetGauche.setBounds(860, 461, 21, 180);
		contentPane.add(separatorObjetGauche);

		JSeparator separatorObjetDroite = new JSeparator();
		separatorObjetDroite.setForeground(Color.BLACK);
		separatorObjetDroite.setOrientation(SwingConstants.VERTICAL);
		separatorObjetDroite.setBounds(1271, 461, 21, 180);
		contentPane.add(separatorObjetDroite);

		JLabel lblObjet = new JLabel("OBJET");
		lblObjet.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblObjet.setBounds(989, 461, 57, 26);
		contentPane.add(lblObjet);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 11));
		progressBar.setStringPainted(true);
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setBounds(1240, 478, 21, 153);
		contentPane.add(progressBar);

		JSeparator separatorHautGraphique = new JSeparator();
		separatorHautGraphique.setForeground(Color.BLACK);
		separatorHautGraphique.setBounds(10, 461, 813, 19);
		contentPane.add(separatorHautGraphique);

		JSeparator separatorBasGraphique = new JSeparator();
		separatorBasGraphique.setForeground(Color.BLACK);
		separatorBasGraphique.setBounds(10, 642, 813, 19);
		contentPane.add(separatorBasGraphique);

		JSeparator separatorGraphiqueDroit = new JSeparator();
		separatorGraphiqueDroit.setForeground(Color.BLACK);
		separatorGraphiqueDroit.setOrientation(SwingConstants.VERTICAL);
		separatorGraphiqueDroit.setBounds(822, 461, 21, 180);
		contentPane.add(separatorGraphiqueDroit);

		JSeparator separatorGraphiqueGauche = new JSeparator();
		separatorGraphiqueGauche.setForeground(Color.BLACK);
		separatorGraphiqueGauche.setOrientation(SwingConstants.VERTICAL);
		separatorGraphiqueGauche.setBounds(10, 461, 21, 180);
		contentPane.add(separatorGraphiqueGauche);

		JLabel lblAttraction = new JLabel("Attraction :");
		lblAttraction.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttraction.setBounds(883, 418, 125, 19);
		contentPane.add(lblAttraction);

	}
}