package fenetre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import utilitaireObjets.Accelerateur;
import utilitaireObjets.BlocMystere;
import utilitaireObjets.PisteDeDepart;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.PisteVirageDroit;
import utilitaireObjets.PisteVirageGauche;
import utilitaireObjets.PisteVirageHaut;
import utilitaireObjets.Regroupement;

/**
 * Classe qui permet de créer la fenetre éditeur
 * 
 * @author Alexis Pineda-Alvarado
 *
 */

public class FenetreEditeur extends JPanel {

	private PanelObjet panelObjet;
	private JButton btnRetour;
	private Regroupement regroupement;
	private double xAccelerateur = 105;
	private double yAccelerateur = 30;
	private BlocMystere blocMystere;
	private PisteDeDepart pisteDeDepart;
	private PisteHorizontale pisteHorizontale;
	private PisteVerticale pisteVerticale;
	private PisteVirageBas pisteVirageBas;
	private PisteVirageDroit pisteVirageDroit;
	private PisteVirageGauche pisteVirageGauche;
	private PisteVirageHaut pisteVirageHaut;
	private int xPrecedent, yPrecedent;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private ArrayList<Accelerateur> listeAcc = new ArrayList<Accelerateur>();
	private Accelerateur acc;
	private boolean objetSelectionne = false;

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */

	public FenetreEditeur() {

		setLayout(null);

		setBackground(Color.LIGHT_GRAY);

		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Retour", null, -1);
			}
		});
		btnRetour.setBounds(10, 11, 89, 23);
		add(btnRetour);

		PanelObjet panelObjet = new PanelObjet();
		panelObjet.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelObjet.setBounds(920, 11, 386, 720);
		add(panelObjet);
		panelObjet.setLayout(null);
		panelObjet.setEnabled(false);

		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Accelerateur accelerateur = new Accelerateur(100, 50);
				listeAcc.add(accelerateur);
				repaint();
			}
		});
		btnNewButton.setBounds(72, 102, 41, 23);
		panelObjet.add(btnNewButton);

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				if (listeAcc.size() != 0 && objetSelectionne == true) {

					xAccelerateur += e.getX() - xPrecedent;
					yAccelerateur += e.getY() - yPrecedent;

					xPrecedent = e.getX();
					yPrecedent = e.getY();
					acc.setX((int) xAccelerateur);
					acc.setY((int) yAccelerateur);
					acc.getFormeAire().setRect(xAccelerateur, yAccelerateur, acc.getTaillePiste(),

					acc.getTaillePiste());
//					System.out.println("X " + acc.getFormeAire().getX());

					acc.getTaillePiste();


				}
				if (objetSelectionne == false) {

				}

				repaint();

			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (int a = 0; a < listeAcc.size(); a++) {
					if (listeAcc.get(a).contient(e.getX(), e.getY())) {

						xPrecedent = e.getX();
						yPrecedent = e.getY();
						xAccelerateur = listeAcc.get(a).getFormeAire().getX();
						yAccelerateur = listeAcc.get(a).getFormeAire().getY();
						acc = listeAcc.get(a);

						objetSelectionne = true;

						break;

					} else {
						objetSelectionne = false;

					}

				} // Fin loop

			}

		});

	}

	/**
	 * Méthode permettant de dessiner sur la fenetre
	 */

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (listeAcc.size() != 0) {

			for (int a = 0; a < listeAcc.size(); a++) {

				listeAcc.get(a).dessiner(g2d);

			}

		}
//		blocMystere = new BlocMystere(75, new Vecteur2D(XOBJET, YOBJET));
//		pisteDeDepart = new PisteDeDepart(xAccelera * 3, YOBJET);
//		pisteDeDepart.getVoiture().getPosition().setX(XOBJET * 2.5);
//
//		pisteHorizontale = new PisteHorizontale(XOBJET, YOBJET * 7);
//		pisteVerticale = new PisteVerticale(XOBJET * 3, YOBJET * 7);
//		pisteVirageBas = new PisteVirageBas(XOBJET, YOBJET * 14);
//		pisteVirageDroit = new PisteVirageDroit(XOBJET * 3, YOBJET * 14);
//		pisteVirageGauche = new PisteVirageGauche(XOBJET, YOBJET * 21);
//		pisteVirageHaut = new PisteVirageHaut(XOBJET * 3, YOBJET * 21);

//		pisteDeDepart.dessiner(g2d);
//		blocMystere.dessiner(g2d);
//
//		pisteHorizontale.dessiner(g2d);
//		pisteVerticale.dessiner(g2d);
//		pisteVirageBas.dessiner(g2d);
//		pisteVirageDroit.dessiner(g2d);
//		pisteVirageGauche.dessiner(g2d);
//		pisteVirageHaut.dessiner(g2d);
//		accelerateur.dessiner(g2d);

	}
}
