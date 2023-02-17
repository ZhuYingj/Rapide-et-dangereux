package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class piste0 extends JPanel {

	private Rectangle2D.Double piste0;

	public piste0() {
		
		piste0  = new Rectangle2D.Double(30.5, 32, 40, 60); 
		
	}
	
	public void paintComponent(Graphics g) {	
			super.paintComponent(g);
			Graphics2D  g2d = (Graphics2D) g;
			g2d.setColor(Color.black);
			g2d.fill(piste0);
	}
	
}
