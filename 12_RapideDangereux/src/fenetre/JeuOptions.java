package fenetre;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.event.ActionEvent;

public class JeuOptions extends JPanel {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	/**
	 * Create the panel.
	 */
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
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
		panel_1.setBounds(741, 345, 549, 297);
		add(panel_1);
		
		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("COMMENCER!", null, -1);
			}
		});
		btnCommencer.setBounds(984, 653, 143, 36);
		add(btnCommencer);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(970, 77, 143, 257);
		add(panel_2);
		
		JButton btnGauche = new JButton("<");
		btnGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
