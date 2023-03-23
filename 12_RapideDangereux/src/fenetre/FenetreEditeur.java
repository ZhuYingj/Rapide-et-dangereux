package fenetre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JPanel;

import geometrie.Vecteur2D;
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
	private JPanel panelPiste;
	private JButton btnRetour;
	private Regroupement regroupement;
	private int XOBJET =105;
	private int YOBJET = 30;
	private int X, Y;
	private BlocMystere blocMystere;
	private PisteDeDepart pisteDeDepart;

	private PisteHorizontale pisteHorizontale;
	private PisteVerticale pisteVerticale;
	private PisteVirageBas pisteVirageBas;
	private PisteVirageDroit pisteVirageDroit;
	private PisteVirageGauche pisteVirageGauche;
	private PisteVirageHaut pisteVirageHaut;
	private Accelerateur accelerateur;
	private boolean selectionObjet = false;
	private int xPrecedent, yPrecedent;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

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
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (selectionObjet) {
					e.getComponent().setLocation((e.getX() + e.getComponent().getX()) - xPrecedent , (e.getY() + e.getComponent().getY()) - yPrecedent);
				}
				repaint();
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (accelerateur.contient(e.getX(), e.getY())) {
					System.out.println("ss");
					selectionObjet = true;
					xPrecedent = e.getX();
					yPrecedent = e.getY();
					repaint();

				}
				System.out.println(e.getX());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				selectionObjet = false;
				repaint();
			}
		});

	}

	/**
	 * Méthode permettant de dessiner sur la fenetre
	 */

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

//		blocMystere = new BlocMystere(75, new Vecteur2D(XOBJET, YOBJET));
//		pisteDeDepart = new PisteDeDepart(XOBJET * 3, YOBJET);
//		pisteDeDepart.getVoiture().getPosition().setX(XOBJET * 2.5);
//
//		pisteHorizontale = new PisteHorizontale(XOBJET, YOBJET * 7);
//		pisteVerticale = new PisteVerticale(XOBJET * 3, YOBJET * 7);
//		pisteVirageBas = new PisteVirageBas(XOBJET, YOBJET * 14);
//		pisteVirageDroit = new PisteVirageDroit(XOBJET * 3, YOBJET * 14);
//		pisteVirageGauche = new PisteVirageGauche(XOBJET, YOBJET * 21);
//		pisteVirageHaut = new PisteVirageHaut(XOBJET * 3, YOBJET * 21);
		accelerateur = new Accelerateur(XOBJET, YOBJET);

//		pisteDeDepart.dessiner(g2d);
//		blocMystere.dessiner(g2d);
//
//		pisteHorizontale.dessiner(g2d);
//		pisteVerticale.dessiner(g2d);
//		pisteVirageBas.dessiner(g2d);
//		pisteVirageDroit.dessiner(g2d);
//		pisteVirageGauche.dessiner(g2d);
//		pisteVirageHaut.dessiner(g2d);
		accelerateur.dessiner(g2d);

	}
}
