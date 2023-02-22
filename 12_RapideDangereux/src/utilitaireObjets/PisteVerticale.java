package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

public class PisteVerticale implements Dessinable {
	
	private static final int TAILLE_PISTE = 50;
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
	g2d.fillRect(x, y, x + 2, y + TAILLE_PISTE);
	g2d.fillRect(x + TAILLE_PISTE, y, x - 2, y + TAILLE_PISTE);
	}

}
