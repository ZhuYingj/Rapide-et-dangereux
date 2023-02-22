package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Voiture extends JPanel{
	
	private double masse = 50;

	private Color skin;
	private Ellipse2D.Double voiture;
	
	public Voiture (Color skin) {
		
		this.skin = skin;
		voiture = new Ellipse2D.Double(80.0, 30.0, 50.2, 35.4);
		
	}
	
	
	
	public void dessiner(Graphics g2d) {
		Graphics2D gCopie =  (Graphics2D) g2d.create();
		
		gCopie.setColor(skin);
		gCopie.fill(voiture);

	}
}
