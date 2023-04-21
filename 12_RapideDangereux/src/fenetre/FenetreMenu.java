package fenetre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import application.OutilsImage;
import fenetre.FenetreAPropos;

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
	private FenetreInstruction fenInstructions;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 * @param listener L'écouteur
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
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(602, 146, 297, 75);
		add(lblNewLabel);

		btnJouer = new JButton("JOUER");
		btnJouer.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("JOUER", 0, -1);
			}
		});
		btnJouer.setBounds(700, 245, 100, 49);
		add(btnJouer);

		FenetreInstruction fenInstructions = new FenetreInstruction();
		btnAide = new JButton("AIDE");
		btnAide.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnAide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fenInstructions.setVisible(true);
			}
		});
		btnAide.setBounds(700, 321, 100, 49);
		add(btnAide);

		btnQuitter = new JButton("QUITTER");
		btnQuitter.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(700, 402, 100, 49);
		add(btnQuitter);

		FenetreAPropos pnlAPropos = new FenetreAPropos();
		pnlAPropos.setPreferredSize(new Dimension(500, 250));

		JButton btnTesterAPropos = new JButton("À PROPOS");
		btnTesterAPropos.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnQuitter.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnTesterAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// on utilise simplement un JOptionPane ici
				JOptionPane.showMessageDialog(null, pnlAPropos, "� Propos de cette application",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnTesterAPropos.setBounds(700, 483, 100, 49);
		add(btnTesterAPropos);

		JLabel lblPhoto = new JLabel("");
		lblPhoto.setBounds(0, 0, 1600, 800);
		add(lblPhoto);

		Image deuxVoiture = OutilsImage.lireImageEtRedimensionner("DeuxVoiture.jpg", 1600, 800);
		if (deuxVoiture != null) {
			lblPhoto.setIcon(new ImageIcon(deuxVoiture));
			deuxVoiture.flush();
		}

	}
}