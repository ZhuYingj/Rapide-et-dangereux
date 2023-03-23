package fenetre;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;

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
import utilitaireObjets.Voiture;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * Classe qui permet de crée un panel composé d'objet
 * 
 * @author Tan Tommy Rin
 *
 */

public class PanelObjet extends JPanel {
	private int XOBJET = 75;
	private int YOBJET = 20;
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
	private double xPrecedent, yPrecedent;

	/**
	 * Creation de la fenetre.
	 */
	public PanelObjet() {
//		addMouseMotionListener(new MouseMotionAdapter() {
//			@Override
//			public void mouseDragged(MouseEvent e) {
//				if (selectionObjet) {
//					XOBJET += e.getX() - xPrecedent;
//					YOBJET += e.getY() - yPrecedent;
//					xPrecedent = e.getX();
//					yPrecedent = e.getY();
//					repaint();
//				}
//			}
//		});
//		addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				if (pisteDeDepart.contient(e.getX(), e.getY())) {
//					System.out.println("ss");
//					selectionObjet = true;
//					xPrecedent = e.getX();
//					yPrecedent = e.getY();
//				}
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				selectionObjet = false;
//				repaint();
//			}
//		});
	}

	/**
	 * Méthode permettant de dessiner sur la fenetre
	 */

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		blocMystere = new BlocMystere(75, new Vecteur2D(XOBJET, YOBJET));
		pisteDeDepart = new PisteDeDepart(XOBJET * 3, YOBJET);
		pisteDeDepart.getVoiture().getPosition().setX(XOBJET * 2.5);
//		voiture = new Voiture(new Vecteur2D(0,0), Color.yellow, 50, 16, 0, 60);
		pisteHorizontale = new PisteHorizontale(XOBJET, YOBJET * 7);
		pisteVerticale = new PisteVerticale(XOBJET * 3, YOBJET * 7);
		pisteVirageBas = new PisteVirageBas(XOBJET, YOBJET * 14);
		pisteVirageDroit = new PisteVirageDroit(XOBJET * 3, YOBJET * 14);
		pisteVirageGauche = new PisteVirageGauche(XOBJET, YOBJET * 21);
		pisteVirageHaut = new PisteVirageHaut(XOBJET * 3, YOBJET * 21);
		accelerateur = new Accelerateur(XOBJET, YOBJET * 28);

		pisteDeDepart.dessiner(g2d);
		blocMystere.dessiner(g2d);
//		voiture.dessiner(g2d);
		pisteHorizontale.dessiner(g2d);
		pisteVerticale.dessiner(g2d);
		pisteVirageBas.dessiner(g2d);
		pisteVirageDroit.dessiner(g2d);
		pisteVirageGauche.dessiner(g2d);
		pisteVirageHaut.dessiner(g2d);
		accelerateur.dessiner(g2d);

	}

}
