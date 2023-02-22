package fenetre;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import dessin.ZoneAnimPhysique;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;

public class FenetreJeuScientifique extends JPanel {

	/**
	 * Create the panel.
	 */
	public FenetreJeuScientifique() {
		setLayout(null);
		setBounds(100, 100, 1300, 700);

		JLabel lblTitreModeScientifique = new JLabel("Mode scientifique activé");
		lblTitreModeScientifique.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitreModeScientifique.setBounds(this.getWidth() / 2 - lblTitreModeScientifique.getWidth(), 0, 223, 22);
		add(lblTitreModeScientifique);
		
		ZoneAnimPhysique zoneAnimPhysique = new ZoneAnimPhysique();
		zoneAnimPhysique.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ZONE D'ANIMATION", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		zoneAnimPhysique.setBounds(10, 33, 700, 466);
		add(zoneAnimPhysique);
		
		JPanel panelDonneScientifique = new JPanel();
		panelDonneScientifique.setBorder(new TitledBorder(null, "DONN\u00C9S SCIENTIFIQUES", TitledBorder.CENTER, TitledBorder.TOP, null, null));
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
		lblPosition.setBounds(10, 71, 109, 29);
		panelDonneScientifique.add(lblPosition);
		
		JLabel lblVitesse = new JLabel("Vitesse :");
		lblVitesse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitesse.setBounds(10, 121, 109, 29);
		panelDonneScientifique.add(lblVitesse);
		
		JLabel lblAcceleration = new JLabel("Accélération :");
		lblAcceleration.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAcceleration.setBounds(10, 173, 109, 29);
		panelDonneScientifique.add(lblAcceleration);
		
		JLabel lblFrottement = new JLabel("Frottement :");
		lblFrottement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFrottement.setBounds(10, 222, 109, 29);
		panelDonneScientifique.add(lblFrottement);
		
		JLabel lblGravite = new JLabel("Gravité :");
		lblGravite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGravite.setBounds(10, 270, 109, 29);
		panelDonneScientifique.add(lblGravite);
		
		JLabel lblFreinage = new JLabel("Freinage :");
		lblFreinage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFreinage.setBounds(10, 322, 109, 29);
		panelDonneScientifique.add(lblFreinage);
		
		JLabel lblAttraction = new JLabel("Attraction :");
		lblAttraction.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttraction.setBounds(10, 371, 109, 29);
		panelDonneScientifique.add(lblAttraction);
		
		JLabel lblMetreV1 = new JLabel("m");
		lblMetreV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMetreV1.setBounds(246, 71, 24, 29);
		panelDonneScientifique.add(lblMetreV1);
		
		JLabel lblMetreV2 = new JLabel("m");
		lblMetreV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMetreV2.setBounds(477, 71, 24, 29);
		panelDonneScientifique.add(lblMetreV2);
		
		JLabel lblKmParHeureV1 = new JLabel("km/h");
		lblKmParHeureV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKmParHeureV1.setBounds(246, 121, 38, 29);
		panelDonneScientifique.add(lblKmParHeureV1);
		
		JLabel lblKmParHeureV2 = new JLabel("km/h");
		lblKmParHeureV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblKmParHeureV2.setBounds(477, 121, 38, 29);
		panelDonneScientifique.add(lblKmParHeureV2);
		
		JLabel lblMCarreV1 = new JLabel("m^2");
		lblMCarreV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMCarreV1.setBounds(246, 173, 38, 29);
		panelDonneScientifique.add(lblMCarreV1);
		
		JLabel lblMCarreV2 = new JLabel("m^2");
		lblMCarreV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMCarreV2.setBounds(477, 173, 38, 29);
		panelDonneScientifique.add(lblMCarreV2);
		
		JLabel lblNV1 = new JLabel("N");
		lblNV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNV1.setBounds(246, 222, 38, 29);
		panelDonneScientifique.add(lblNV1);
		
		JLabel lblNV1_1 = new JLabel("N");
		lblNV1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNV1_1.setBounds(477, 222, 38, 29);
		panelDonneScientifique.add(lblNV1_1);
		
		JLabel lblNGraviteV1 = new JLabel("N");
		lblNGraviteV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNGraviteV1.setBounds(246, 270, 38, 29);
		panelDonneScientifique.add(lblNGraviteV1);
		
		JLabel lblNGraviteV2 = new JLabel("N");
		lblNGraviteV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNGraviteV2.setBounds(477, 270, 38, 29);
		panelDonneScientifique.add(lblNGraviteV2);
		
		JLabel lblNFreinageV1 = new JLabel("N");
		lblNFreinageV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNFreinageV1.setBounds(246, 322, 38, 29);
		panelDonneScientifique.add(lblNFreinageV1);
		
		JLabel lblNAttractionV1 = new JLabel("N");
		lblNAttractionV1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNAttractionV1.setBounds(246, 371, 38, 29);
		panelDonneScientifique.add(lblNAttractionV1);
		
		JLabel lblNFreinageV2 = new JLabel("N");
		lblNFreinageV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNFreinageV2.setBounds(477, 322, 38, 29);
		panelDonneScientifique.add(lblNFreinageV2);
		
		JLabel lblNAttractionV2 = new JLabel("N");
		lblNAttractionV2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNAttractionV2.setBounds(477, 371, 38, 29);
		panelDonneScientifique.add(lblNAttractionV2);
		
		JPanel panelGraphique = new JPanel();
		panelGraphique.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelGraphique.setBounds(10, 510, 700, 179);
		add(panelGraphique);
		
		JPanel panelObjet = new JPanel();
		panelObjet.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelObjet.setBounds(720, 510, 570, 179);
		add(panelObjet);
		panelObjet.setLayout(null);
		
		JProgressBar progressBarFroce = new JProgressBar();
		progressBarFroce.setFont(new Font("Tahoma", Font.BOLD, 12));
		progressBarFroce.setStringPainted(true);
		progressBarFroce.setOrientation(SwingConstants.VERTICAL);
		progressBarFroce.setBounds(519, 11, 30, 157);
		panelObjet.add(progressBarFroce);
	}
}
