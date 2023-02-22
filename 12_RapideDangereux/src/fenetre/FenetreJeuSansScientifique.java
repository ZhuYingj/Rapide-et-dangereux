package fenetre;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreJeuSansScientifique extends JPanel {

	/**
	 * Create the panel.
	 */
	public FenetreJeuSansScientifique() {
		setLayout(null);
		setBounds(100, 100, 1300, 700);
		JLabel lblTitreModeScientifique = new JLabel("Jeu");
		lblTitreModeScientifique.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitreModeScientifique.setBounds(this.getWidth() / 2 - lblTitreModeScientifique.getWidth(), 0, 223, 22);
		add(lblTitreModeScientifique);
	}

}
