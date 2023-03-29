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

public class ZoneVitesse extends JPanel{
	private List<Integer> temps;
	private List<Integer> vitesses;
	
	public ZoneVitesse() {
        temps = new ArrayList<>(Arrays.asList(0));
       vitesses = new ArrayList<>(Arrays.asList(0));
    }
	
	public void renouvlerVitesse() {
		vitesses.clear();
		repaint();
	}
	
	public void renouvlerTemps() {
		temps.clear();
		repaint();
	}
	
	public void ajouterVitesse(double vitesse) {
        vitesses.add((int) vitesse);
        repaint(); 
    }
	
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
            System.out.println("la valeur de la vitesse est: "+ vitesseEnMps + " et la valeur du temps est: " + tempsEnS);
        }
}

	public void ajouterTemps() {
		int n = temps.size();
		temps.add(n);
		repaint();
	}
}
