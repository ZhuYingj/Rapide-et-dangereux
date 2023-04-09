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
		setBounds(100, 100, 1300, 700);

		zoneAnimPhysique = new ZoneAnimPhysique();
		zoneAnimPhysique.setBorder(null);
		zoneAnimPhysique.setBounds(10, 40, 910, 571);
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

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		JButton btnStart = new JButton("Start");
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
		btnStart.setBounds(134, 604, 97, 58);
		add(btnStart);

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
		btnReset.setBounds(376, 604, 97, 58);
		add(btnReset);

		btnNextImg = new JButton("Next Img");
		btnNextImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.avancerUnPas();
			}
		});
		btnNextImg.setBounds(632, 604, 103, 58);
		add(btnNextImg);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneAnimPhysique.requestFocusInWindow();
				zoneAnimPhysique.arreter();
				btnNextImg.setEnabled(true);
				btnStart.setEnabled(true);
			}
		});
		btnStop.setBounds(953, 604, 103, 58);
		add(btnStop);
	}

	public ZoneAnimPhysique getZoneAnimPhysique() {
		return zoneAnimPhysique;
	}

	public void setZoneAnimPhysique(ZoneAnimPhysique zoneAnimPhysique) {
		this.zoneAnimPhysique = zoneAnimPhysique;
	}
}
