package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Voiture extends JPanel{
	
	private Color skin;
	private Ellipse2D.Double voiture;
	
	public Voiture (Color skin) {
		
		this.skin = skin;
		voiture = new Ellipse2D.Double(80.0, 30.0, 50.2, 35.4);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent( g ); 
		Graphics2D  g2d = (Graphics2D) g;
		g2d.setColor(skin);
		g2d.fill(voiture);

	}
}
