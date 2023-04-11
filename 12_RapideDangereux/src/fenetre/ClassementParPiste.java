package fenetre;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.event.ActionEvent;

/**
 * 
 * @author
 *
 */
public class ClassementParPiste extends JPanel {
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 */

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	/**
	 * 
	 */
	public ClassementParPiste() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Tableau du classement par piste");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(222, 76, 428, 83);
		add(lblNewLabel);

		JButton btnReinitialiser = new JButton("R\u00E9initialiser les statistiques");
		btnReinitialiser.setBounds(77, 600, 168, 39);
		add(btnReinitialiser);

		JButton btnFermer = new JButton("QUITTER");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("QUITTER", null, -1);

			}
		});
		btnFermer.setBounds(389, 600, 139, 39);
		add(btnFermer);

		JButton btnColorer = new JButton("Colorer l'arriere plan");
		btnColorer.setBounds(682, 600, 139, 39);
		add(btnColorer);

		JLabel lblPiste = new JLabel("Piste");
		lblPiste.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPiste.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiste.setBounds(97, 243, 61, 33);
		add(lblPiste);

		JLabel lblRecordTemps = new JLabel("Record\r\n\r\n(temps en secondes)");
		lblRecordTemps.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRecordTemps.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecordTemps.setBounds(191, 236, 207, 47);
		add(lblRecordTemps);

		JLabel lblName = new JLabel("Record par");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(408, 227, 96, 64);
		add(lblName);

		JLabel lblNbEssaie = new JLabel("Nd fois ou \r\nla piste a \r\n\u00E9t\u00E9 jou\u00E9e");
		lblNbEssaie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNbEssaie.setHorizontalAlignment(SwingConstants.CENTER);
		lblNbEssaie.setBounds(531, 243, 154, 33);
		add(lblNbEssaie);

		JLabel lblMoyenne = new JLabel("Moyenne (temps en secondes)");
		lblMoyenne.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoyenne.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMoyenne.setBounds(730, 227, 182, 64);
		add(lblMoyenne);
	}

}
