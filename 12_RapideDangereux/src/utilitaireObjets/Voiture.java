package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import interfaces.Dessinable;

public class Voiture implements Dessinable {
	
	private Color skin;
	private Ellipse2D.Double voiture;
	
	public Voiture (Color skin) {
		
		this.skin = skin;
		voiture = new Ellipse2D.Double(120, 120, 10, 10);
		
	}
	
	public void setCouleur(Color couleur) {
		this.skin = couleur;

	}

	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(skin);
		g2d.fill(voiture);
		
	}
}
