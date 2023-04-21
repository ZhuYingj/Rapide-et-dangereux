package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import utilitaireObjets.Voiture;

/**
 * Zone de dessin permettant d'afficher le graphique de la vitesse en fonction
 * du temps
 * 
 * @author Ludovic Julien
 *
 */
public class ZoneVitesse extends JPanel {
	/** Tableau du temps **/
	private List<Integer> temps;
	/** Tableau de la vitesse **/
	private List<Integer> vitesses;

	/**
	 * Methode qui permet de construire les deux tableaux
	 */
	// Ludovic Julien
	public ZoneVitesse() {
		temps = new ArrayList<>(Arrays.asList(0));
		vitesses = new ArrayList<>(Arrays.asList(0));
	}

	/**
	 * methode qui clear le tableau vitesse
	 */
	// Ludovic Julien
	public void renouvlerVitesse() {
		vitesses.clear();
		repaint();
	}

	/**
	 * methode qui clear le tableau temps
	 */
	// Ludovic Julien
	public void renouvlerTemps() {
		temps.clear();
		repaint();
	}

	/**
	 * methode qui ajoute la nouvelle valeur de la vitesse dans le tableaux vitesse
	 * 
	 * @param vitesse la vitesse actuel de la voiture
	 */
	// Ludovic Julien
	public void ajouterVitesse(double vitesse) {
		vitesses.add((int) vitesse);
		repaint();
	}

	/**
	 * Methode qui permet de dessiner le graphiquet sur la zone d'animation a l'aide
	 * de g2d
	 * 
	 * @param g Composant graphique
	 */
	// Ludovic Julien
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		int x = 50;
		int y = 10;
		int z = 3;
		int largeur = 215;
		int hauteur = 200;

		g2d.setColor(Color.BLACK);
		g2d.drawLine(x, y + hauteur, x + largeur, y + hauteur); // Axe horizontal
		g2d.drawLine(x + largeur, y + hauteur, x + largeur - z, y + hauteur - z);
		g2d.drawLine(x + largeur, y + hauteur, x + largeur - z, y + hauteur + z);

		g2d.drawLine(x, y + (largeur / 2), x - z, y + (largeur / 2));

		g2d.drawLine(x, y, x, y + hauteur); // Axe vertical

		g2d.drawLine(x, y, x - z, y + z);
		g2d.drawLine(x, y, x + z, y + z);

		g.setColor(Color.BLUE);

		for (int i = 0; i < vitesses.size(); i++) {
			double tempsEnS = temps.get(i);
			double vitesseEnMps = vitesses.get(i);
			int posX = x + (int) (tempsEnS * largeur / temps.get(temps.size() - 1));
			int posY = y + (int) (hauteur - vitesseEnMps * hauteur / 120.0);
			g.fillOval(posX - 3, posY - 3, 4, 4);

		}
	}

	/**
	 * methode qui ajoute une seconde au tableau temps
	 */
	// Ludovic Julien
	public void ajouterTemps() {
		int k;
		int n = temps.size();
		temps.add(n);
		repaint();
	}

}
