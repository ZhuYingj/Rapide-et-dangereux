package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;
/**
 * Zone de dessin permettant d'afficher le graphique de l'acceleration en fonction du temps
 * 
 * @author Ludovic Julien
 *
 */
public class ZoneAcceleration extends JPanel {
	/** Tableau du temps **/
	private List<Integer> temps;
	/** Tableau de l'acceleration **/
	private List<Integer> acceleration;
	
	/**
	 * Methode qui permet de construire les deux tableaux
	 */
	public ZoneAcceleration() {
        temps = new ArrayList<>(Arrays.asList(0));
       acceleration = new ArrayList<>(Arrays.asList(0));
    }
	
	/**
	 * Methode qui permet de dessiner le graphiquet sur la zone d'animation a
	 * l'aide de g2d
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
        for (int i = 0; i < acceleration.size(); i++) {
            double tempsEnS = temps.get(i);
            double accelerationEnMps = acceleration.get(i);
            int posX = x + (int) (tempsEnS * largeur / temps.get(temps.size() - 1));
            int posY = y + (int) (hauteur - accelerationEnMps * hauteur / 30.0);
            g.fillOval(posX - 3, posY - 3, 4, 4);
            System.out.println("la valeur de l'acceleration est: "+ accelerationEnMps + " et la valeur du temps est: " + tempsEnS);
        }
}
	
	/**
	 * methode qui clear le tableau acceleration
	 */
	public void renouvlerAcceleration() {
		acceleration.clear();
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
	 * methode qui ajoute la nouvelle valeur de l'acceleration dans le tableaux vitesse
	 * 
	 * @param acc 	la vitesse actuel de la voiture
	 */
	public void ajouterAcceleration(double acc) {
        acceleration.add((int) acc);
        repaint(); 
    }
	
	/**
	 * methode qui ajoute une seconde au tableau temps
	 */
	public void ajouterTemps() {
		int n = temps.size();
		temps.add(n);
		repaint();
	}
	
}
