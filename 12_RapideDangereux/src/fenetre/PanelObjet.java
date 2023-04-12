package fenetre;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JPanel;

import application.OutilsImage;
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
 * Classe qui permet de crée un panel composé d'objet. Ce panel servira de
 * visuel pour les objets
 * 
 * @author Tan Tommy Rin
 *
 */

public class PanelObjet extends JPanel {

	private final int X_OBJET = 75;
	private final int Y_OBJET = 20;
	private BlocMystere blocMystere;
	private PisteDeDepart pisteDeDepart;
	private PisteHorizontale pisteHorizontale;
	private PisteVerticale pisteVerticale;
	private PisteVirageBas pisteVirageBas;
	private PisteVirageDroit pisteVirageDroit;
	private PisteVirageGauche pisteVirageGauche;
	private PisteVirageHaut pisteVirageHaut;
	private Accelerateur accelerateur;

	/**
	 * Méthode permettant de dessiner sur la fenetre
	 */
//Tan Tommy Rin
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		blocMystere = new BlocMystere(75, new Vecteur2D(X_OBJET, Y_OBJET));
		pisteDeDepart = new PisteDeDepart(X_OBJET * 3, Y_OBJET);
		pisteHorizontale = new PisteHorizontale(X_OBJET, Y_OBJET * 7);
		pisteVerticale = new PisteVerticale(X_OBJET * 3, Y_OBJET * 7);
		pisteVirageBas = new PisteVirageBas(X_OBJET, Y_OBJET * 14);
		pisteVirageDroit = new PisteVirageDroit(X_OBJET * 3, Y_OBJET * 21);
		pisteVirageGauche = new PisteVirageGauche(X_OBJET * 3, Y_OBJET * 14);
		pisteVirageHaut = new PisteVirageHaut(X_OBJET, Y_OBJET * 21);
		accelerateur = new Accelerateur(X_OBJET, Y_OBJET * 28);

		pisteDeDepart.dessiner(g2d);

		pisteHorizontale.dessiner(g2d);
		pisteVerticale.dessiner(g2d);
		pisteVirageBas.dessiner(g2d);
		pisteVirageDroit.dessiner(g2d);
		pisteVirageGauche.dessiner(g2d);
		pisteVirageHaut.dessiner(g2d);
		accelerateur.dessiner(g2d);
		Image boiteMystere = OutilsImage.lireImageEtRedimensionner("LuckyBox.png", 75, 75);
		g2d.drawImage(boiteMystere, (int) this.blocMystere.getPosition().getX(),
				(int) this.blocMystere.getPosition().getY(), null);

		boiteMystere.flush();
	}

}
