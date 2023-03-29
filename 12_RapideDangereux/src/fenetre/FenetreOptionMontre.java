package fenetre;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JPanel;

import dessin.OutilsImage;
import dessin.ZoneApercupiste;
import interfaces.TypePiste;
import utilitaireObjets.Regroupement;

/**
 * 
 * Classe qui crée la fenetre pour les paramètres a choisir pour le mode de jeu
 * course contre la montre
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class FenetreOptionMontre extends JPanel {

	private TypePiste type = TypePiste.MEXIQUE;
	private Image imageActuelle;
	private Regroupement regroupement;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Create the panel.
	 */
	public FenetreOptionMontre() {
		setLayout(null);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", 0, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		JPanel PanelApercu = new JPanel();
		PanelApercu.setBackground(Color.WHITE);
		PanelApercu.setBounds(10, 203, 700, 439);
		add(PanelApercu);
		PanelApercu.setLayout(null);

		ZoneApercupiste zoneApercupiste = new ZoneApercupiste();
		zoneApercupiste.setBounds(345, 5, 10, 10);
		PanelApercu.add(zoneApercupiste);

		JButton btnCanada = new JButton("Canada");
		btnCanada.setBounds(307, 77, 126, 78);
		add(btnCanada);
		btnCanada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = TypePiste.CANADA;
				imageActuelle = OutilsImage.lireImage("Construction.gif");
				zoneApercupiste.setImg(imageActuelle);
				zoneApercupiste.repaint();
			}
		});

		JButton btnItalie = new JButton("Italie");
		btnItalie.setBounds(474, 77, 126, 78);
		add(btnItalie);
		btnItalie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = TypePiste.ITALIE;
				imageActuelle = OutilsImage.lireImage("pisteItalie.PNG");
				zoneApercupiste.setImg(imageActuelle);
				zoneApercupiste.repaint();

			}
		});
		
		JButton btnMexique = new JButton("Mexique");
		// btnMexique.setIcon(icone);
		btnMexique.setBounds(130, 77, 126, 78);
		add(btnMexique);
		btnMexique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = TypePiste.MEXIQUE;
				imageActuelle = OutilsImage.lireImage("PisteMexique.png");
				zoneApercupiste.setImg(imageActuelle);
				zoneApercupiste.repaint();
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(760, 345, 549, 297);
		add(panel_1);
		panel_1.setLayout(null);

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

		JButton btnCommencer = new JButton("COMMENCER!");
		btnCommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("COMMENCER!", null, -1);
				//pcs.firePropertyChange("MASSE", null, (double) slider.getValue());
				pcs.firePropertyChange("TYPEPISTE", null, type);
			}
		});
		btnCommencer.setBounds(984, 653, 143, 36);
		add(btnCommencer);

	}
}
