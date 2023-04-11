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
		btnReinitialiser.setBounds(77, 600, 197, 39);
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
		btnColorer.setBounds(682, 600, 189, 39);
		add(btnColorer);

		JLabel lblPiste = new JLabel("Piste");
		lblPiste.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPiste.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiste.setBounds(109, 235, 61, 33);
		add(lblPiste);

		JLabel lblRecordTemps = new JLabel("Record");
		lblRecordTemps.setToolTipText("");
		lblRecordTemps.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRecordTemps.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecordTemps.setBounds(186, 227, 207, 49);
		add(lblRecordTemps);

		JLabel lblName = new JLabel("Record par");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(407, 219, 96, 64);
		add(lblName);

		JLabel lblNbEssaie = new JLabel("Nd fois ou \r\nla ");
		lblNbEssaie.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNbEssaie.setHorizontalAlignment(SwingConstants.CENTER);
		lblNbEssaie.setBounds(544, 235, 154, 33);
		add(lblNbEssaie);

		JLabel lblMoyenne = new JLabel("Moyenne ");
		lblMoyenne.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoyenne.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMoyenne.setBounds(730, 219, 182, 64);
		add(lblMoyenne);
		
		JLabel lblNewLabel_1 = new JLabel("(Temps en seconde)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(222, 276, 139, 33);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("piste a \r\n\u00E9t\u00E9 jou\u00E9e");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(541, 275, 144, 33);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("(Temps en seconde)");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(756, 276, 139, 33);
		add(lblNewLabel_1_1);
	}
}
