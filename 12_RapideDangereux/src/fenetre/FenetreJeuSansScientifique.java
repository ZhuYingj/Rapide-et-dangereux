package fenetre;

import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JLabel;
import javax.swing.JPanel;
import dessin.ZoneAnimPhysique;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class FenetreJeuSansScientifique extends JPanel {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	/**
	 * Create the panel.
	 */
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public FenetreJeuSansScientifique() {
		setLayout(null);
		setBounds(100, 100, 1300, 700);
		
		ZoneAnimPhysique zoneAnimPhysique = new ZoneAnimPhysique();
		zoneAnimPhysique.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ZONE D'ANIMATION", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		zoneAnimPhysique.setBounds(10, 33, 1280, 656);
		add(zoneAnimPhysique);
		zoneAnimPhysique.setLayout(null);
		
		JPanel panelObjet = new JPanel();
		panelObjet.setBounds(936, 24, 323, 226);
		zoneAnimPhysique.add(panelObjet);
		panelObjet.setLayout(null);
		panelObjet.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JProgressBar progressBarFroce = new JProgressBar();
		progressBarFroce.setOrientation(SwingConstants.VERTICAL);
		progressBarFroce.setBounds(279, 11, 34, 204);
		panelObjet.add(progressBarFroce);
		progressBarFroce.setStringPainted(true);
		progressBarFroce.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lblTitreModeScientifique = new JLabel("Jeu");
		lblTitreModeScientifique.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitreModeScientifique.setBounds(650, 0, 47, 22);
		add(lblTitreModeScientifique);
	}
}
