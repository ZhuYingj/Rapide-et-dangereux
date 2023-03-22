package fenetre;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JPanel;

import utilitaireObjets.BlocMystere;
import utilitaireObjets.Regroupement;
import javax.swing.border.LineBorder;

/**
 * Classe qui permet de créer la fenetre éditeur
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class FenetreEditeur extends JPanel {

	private PanelObjet panelObjet;
	private JPanel panelPiste;
	private JButton btnRetour;
	private Regroupement regroupement;
	private BlocMystere blocMystere;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */

	public FenetreEditeur() {
		setLayout(null);

		panelPiste = new JPanel();
		panelPiste.setBackground(new Color(255, 255, 255));
		panelPiste.setBounds(10, 60, 750, 600);
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

		panelObjet = new PanelObjet();
		panelObjet.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelObjet.setBounds(800, 20, 373, 700);
		panelObjet.setBackground(Color.gray);
		add(panelObjet);

	}
}
