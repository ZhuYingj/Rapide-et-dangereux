package fenetre;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ModeDeJeu extends JPanel {
	private JButton btnMonde;
	private JButton btnCourseMontre;
	private JButton btnEditeur;
	private JLabel lblTitre;
	/**
	 * Create the panel.
	 */
	public ModeDeJeu() {
		setLayout(null);
		btnMonde = new JButton("MONDE");
		btnMonde.setForeground(Color.BLUE);
		btnMonde.setBounds(182, 127, 89, 23);
		add(btnMonde);

		btnCourseMontre = new JButton("COURSE CONTRE LA MONTRE");
		btnCourseMontre.setForeground(Color.BLUE);
		btnCourseMontre.setBounds(163, 161, 123, 23);
		add(btnCourseMontre);

		btnEditeur = new JButton("EDITEUR");
		btnEditeur.setForeground(Color.BLUE);
		btnEditeur.setBounds(182, 195, 89, 23);
		add(btnEditeur);

		lblTitre = new JLabel("Mode de jeu");
		lblTitre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 30));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(81, 33, 288, 41);
		add(lblTitre);
	}

}
