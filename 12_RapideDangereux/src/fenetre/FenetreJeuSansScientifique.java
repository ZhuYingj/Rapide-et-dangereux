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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

/**
 * Classe qui permet de créer une fenetre de jeu sans le mode scientifique
 * activé
 * 
 * @author Tan Tommy Rin
 *
 */

public class FenetreJeuSansScientifique extends JPanel {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private ZoneAnimPhysique zoneAnimPhysique;
	private JButton btnNextImg;
	private JButton btnStart;

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 */
	// Tan Tommy Rin
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Méthode qui permet de crée la fenetre
	 */
	// Tan Tommy Rin
	public FenetreJeuSansScientifique() {
		setLayout(null);
		setBounds(100, 100, 1600, 800);

		zoneAnimPhysique = new ZoneAnimPhysique();
		zoneAnimPhysique.setBorder(null);
		zoneAnimPhysique.setBounds(165, 27, 1048, 673);
		add(zoneAnimPhysique);
		zoneAnimPhysique.setLayout(null);
		JLabel lblTitreModeScientifique = new JLabel("Jeu");
		lblTitreModeScientifique.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitreModeScientifique.setBounds(650, 0, 47, 22);
		add(lblTitreModeScientifique);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.restartPos();
				btnNextImg.setEnabled(true);
				btnStart.setEnabled(true);
				pcs.firePropertyChange("CHECKBOXACTIVE", null, -1);
			}
		});
		btnReset.setBounds(30, 345, 97, 58);
		add(btnReset);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.arreter();
				btnNextImg.setEnabled(true);
				btnStart.setEnabled(true);
			}
		});
		btnStop.setBounds(30, 441, 97, 58);
		add(btnStop);

		btnNextImg = new JButton("Next Img");
		btnNextImg.setBounds(30, 260, 97, 58);
		add(btnNextImg);

		btnStart = new JButton("Start");
		btnStart.setBounds(30, 177, 97, 58);
		add(btnStart);
		
		JLabel lblNewLabel = new JLabel("80 M");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(205, 724, 89, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<------------>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(175, 703, 269, 14);
		add(lblNewLabel_1);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.setEnCoursDAnimation(false);
				zoneAnimPhysique.demarrer();
				btnNextImg.setEnabled(false);
				btnStart.setEnabled(false);
				pcs.firePropertyChange("STARTBUTTONACTIVE", null, -1);
			}
		});
		btnNextImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.avancerUnPas();
			}
		});
	}

	public ZoneAnimPhysique getZoneAnimPhysique() {
		return zoneAnimPhysique;
	}

	public void setZoneAnimPhysique(ZoneAnimPhysique zoneAnimPhysique) {
		this.zoneAnimPhysique = zoneAnimPhysique;
	}
}
