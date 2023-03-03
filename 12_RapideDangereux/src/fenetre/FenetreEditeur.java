package fenetre;

import javax.swing.JPanel;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class FenetreEditeur extends JPanel {

	private JPanel panelObjets;
	private JPanel panelPiste;
	private JButton btnRetour;
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	/**
	 * Create the panel.
	 */
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
		}
		
	public FenetreEditeur() {
		setLayout(null);
		
		panelObjets = new JPanel();
		panelObjets.setBackground(new Color(255, 255, 255));
		panelObjets.setBounds(718, 11, 257, 607);
		add(panelObjets);
		panelObjets.setLayout(null);
		
		panelPiste = new JPanel();
		panelPiste.setBackground(new Color(255, 255, 255));
		panelPiste.setBounds(10, 118, 648, 500);
		add(panelPiste);
		panelPiste.setLayout(null);
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", null, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

	}
}
