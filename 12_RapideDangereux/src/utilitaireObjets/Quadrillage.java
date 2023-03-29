package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

public class Quadrillage implements Dessinable {
	private int tailleDuCarre = 80;
	private int x, y;
	private int longueurGauche;
	private int longueurDroite;
	private int longueurHaut;
	private int longueurBas;
	private Rectangle2D.Double formeAire;

	public Quadrillage(int x, int y) {
		this.x = x;
		this.y = y;
		this.longueurDroite = x + tailleDuCarre + 1;
		this.longueurGauche = x + tailleDuCarre + 1;
		this.longueurHaut = y + 1;
		this.longueurBas = y + tailleDuCarre;
		formeAire = new Rectangle2D.Double(this.x, this.y, tailleDuCarre, tailleDuCarre);

	}

	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dCopie = (Graphics2D) g2d.create();
		
		for( int i = 1 ; i < tailleDuCarre ; i++) {
			
		g2dCopie.setColor(Color.white);
		g2dCopie.fillRect(x, y, tailleDuCarre, tailleDuCarre);
		g2dCopie.setStroke(new BasicStroke(1));
		g2dCopie.setColor(Color.black);
		g2dCopie.drawLine(x, y, x + tailleDuCarre, y);
		g2dCopie.drawLine(x, y + tailleDuCarre, x + tailleDuCarre, y + tailleDuCarre);
		g2dCopie.drawLine(x, y, x, y + tailleDuCarre);
		g2dCopie.drawLine(x + tailleDuCarre, y, x + tailleDuCarre, y + tailleDuCarre);
			
		}
	}
}

