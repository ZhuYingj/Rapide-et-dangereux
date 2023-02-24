package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

public class PisteVerticale implements Dessinable {
	
	private static final int TAILLE_PISTE = 80;
	private int x;
	private int y;

	public PisteVerticale(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void dessiner(Graphics2D g2d) {
	g2d.setColor(Color.BLACK);
	g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
	g2d.setColor(Color.RED);
	Stroke stroke = new BasicStroke(3f);
	g2d.setStroke(stroke);
	g2d.drawLine(x, y +1, x, y + TAILLE_PISTE -1);
	g2d.drawLine(x + TAILLE_PISTE, y+1, x + TAILLE_PISTE, y + TAILLE_PISTE-1);
	

	}

}
