package fenetre;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import application.OutilsImage;

/**
 * Classe qui crée la premiere fenêtre "Menu"
 * 
 * @author Ludovic Julien
 *
 */
public class FenetreMenu extends JPanel {
	private JLabel lblInfoRecue;
	private JButton btnJouer;
	private JButton btnAide;
	private JButton btnQuitter;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Create the panel.
	 */
//Ludovic Julien
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Méthode qui permet la création du panel
	 */
	// Ludovic Julien
	public FenetreMenu() {

		setLayout(null);
		setBounds(100, 100, 1300, 700);

		JLabel lblNewLabel = new JLabel("Rapide et Dangereux");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(471, 145, 255, 75);
		add(lblNewLabel);

		btnJouer = new JButton("JOUER");
		btnJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("JOUER", 0, -1);
			}
		});
		btnJouer.setBounds(this.getWidth() / 2 - 100, 245, 100, 49);
		add(btnJouer);

		btnAide = new JButton("AIDE");
		btnAide.setBounds(this.getWidth() / 2 - 100, 321, 100, 49);
		add(btnAide);

		btnQuitter = new JButton("QUITTER");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(this.getWidth() / 2 - 100, 402, 100, 49);
		add(btnQuitter);

		JLabel lblPhoto = new JLabel("");
		lblPhoto.setBounds(0, 0, 1350, 800);
		add(lblPhoto);
		
		Image deuxVoiture = OutilsImage.lireImageEtRedimensionner("DeuxVoiture.jpg", 1350, 800);
		if (deuxVoiture != null) {
			lblPhoto.setIcon(new ImageIcon(deuxVoiture));
			deuxVoiture.flush();
		}

	}
}