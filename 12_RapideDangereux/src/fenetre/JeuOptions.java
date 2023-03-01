package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import utilitaireObjets.Voiture;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class JeuOptions extends JPanel {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final ButtonGroup buttonGroupDiff = new ButtonGroup();
	private JRadioButton rdbtnFacile;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnDifficile;
	private Voiture voiture;
	private JSlider slider;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */

	public JeuOptions() {
		setLayout(null);

		JButton btnMexique = new JButton("Mexique");
		btnMexique.setBounds(130, 77, 126, 78);
		add(btnMexique);

		JButton btnCanada = new JButton("Canada");
		btnCanada.setBounds(307, 77, 126, 78);
		add(btnCanada);

		JButton btnItalie = new JButton("Italie");
		btnItalie.setBounds(474, 77, 126, 78);
		add(btnItalie);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 208, 635, 481);
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(760, 345, 549, 297);
		add(panel_1);
		panel_1.setLayout(null);

		slider = new JSlider();

		slider.setMajorTickSpacing(10);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(10);
		slider.setMinimum(50);
		slider.setBounds(141, 33, 343, 40);
		panel_1.add(slider);

		JLabel lblMasse = new JLabel("Masse de la voiture en kg : ");
		lblMasse.setBounds(10, 39, 132, 20);
		panel_1.add(lblMasse);

		JLabel lblDifficulte = new JLabel("Difficulter du jeu : ");
		lblDifficulte.setBounds(10, 84, 110, 14);
		panel_1.add(lblDifficulte);

		rdbtnFacile = new JRadioButton("Facile");
		rdbtnFacile.setSelected(true);
		rdbtnFacile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pcs.firePropertyChange("VITESSEMAXFACILE", null, 5.0);

			}
		});
		rdbtnFacile.setBounds(141, 80, 109, 23);
		panel_1.add(rdbtnFacile);
		buttonGroupDiff.add(rdbtnFacile);

		rdbtnMedium = new JRadioButton("Intermediaire");
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("VITESSEMAXMOYEN", null, 10.0);
			}
		});
		rdbtnMedium.setBounds(141, 106, 109, 23);
		panel_1.add(rdbtnMedium);
		buttonGroupDiff.add(rdbtnMedium);

		rdbtnDifficile = new JRadioButton("Difficile");
		rdbtnDifficile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("VITESSEMAXDIFFICILE", null, 15.0);
			}
		});
		rdbtnDifficile.setBounds(141, 132, 109, 23);
		panel_1.add(rdbtnDifficile);
		buttonGroupDiff.add(rdbtnDifficile);

		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("COMMENCER!", null, -1);
				pcs.firePropertyChange("MASSE", null, (double) slider.getValue());

			}
		});
		btnCommencer.setBounds(984, 653, 143, 36);
		add(btnCommencer);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(970, 77, 143, 257);
		add(panel_2);

		JButton btnGauche = new JButton("<");
		btnGauche.setBounds(905, 188, 55, 23);
		add(btnGauche);

		JButton btnDroite = new JButton(">");
		btnDroite.setBounds(1123, 188, 55, 23);
		add(btnDroite);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

	}
}
