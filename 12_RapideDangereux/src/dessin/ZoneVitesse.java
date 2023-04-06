package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

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
	public ZoneVitesse() {
		temps = new ArrayList<>(Arrays.asList(0));
		vitesses = new ArrayList<>(Arrays.asList(0));
	}

	/**
	 * methode qui clear le tableau vitesse
	 */
	public void renouvlerVitesse() {
		vitesses.clear();
		repaint();
	}

	/**
	 * methode qui clear le tableau temps
	 */
	public void renouvlerTemps() {
		temps.clear();
		repaint();
	}

	/**
	 * methode qui ajoute la nouvelle valeur de la vitesse dans le tableaux vitesse
	 * 
	 * @param acc la vitesse actuel de la voiture
	 */
	public void ajouterVitesse(double vitesse) {
		vitesses.add((int) vitesse);
		repaint();
	}

	/**
	 * Methode qui permet de dessiner le graphiquet sur la zone d'animation a l'aide
	 * de g2d
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		int x = 50;
		int y = 10;
		int largeur = 200;
		int hauteur = 200;

		g2d.setColor(Color.BLACK);
		g2d.drawLine(x, y + hauteur, x + largeur, y + hauteur); // Axe horizontal
		g2d.drawLine(x, y, x, y + hauteur); // Axe vertical

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
	public void ajouterTemps() {
		int k;
		int n = temps.size();
		temps.add(n);
		repaint();
	}
}
