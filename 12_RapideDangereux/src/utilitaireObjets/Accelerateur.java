package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import interfaces.Dessinable;

public class Accelerateur implements Dessinable{

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 87;
	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer  **/
	private int y;
	
	
	public Accelerateur(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);
		g2d.drawLine(x, y, x + TAILLE_PISTE , y);
		g2d.drawLine(x, y+ TAILLE_PISTE-1, x + TAILLE_PISTE , y+ TAILLE_PISTE-1);
		g2d.drawLine(x, y, x, y+TAILLE_PISTE);
		g2d.drawLine(x+TAILLE_PISTE, y, x+TAILLE_PISTE, y+TAILLE_PISTE);
		g2d.drawLine(x,y, x+ TAILLE_PISTE, y + TAILLE_PISTE );
		g2d.drawLine(x + (TAILLE_PISTE/2),y, x+ TAILLE_PISTE, y + (TAILLE_PISTE/2) );
		g2d.drawLine(x, y+ (TAILLE_PISTE/2) , x + (TAILLE_PISTE/2), y + TAILLE_PISTE);
	}
	
}
