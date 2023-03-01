package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import interfaces.Dessinable;

/**
 * Class qui permet de creer un objet piste virage droit
 * 
 * @author Ludovic Julien
 *
 */

public class PisteVirageBas implements Dessinable{

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 80;
	/** la position en x de depart que l'objet piste qui vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste qui vas etre creer  **/
	private int y;
	
	public PisteVirageBas(int x, int y) {
		this.x = x;
		this.y = y;
	
	}
	
	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke = new BasicStroke(3f);
		g2d.setStroke(stroke);	
		g2d.drawLine(x + ((TAILLE_PISTE/3)*2), y, x + TAILLE_PISTE-1, y );
		g2d.drawLine(x + ((TAILLE_PISTE/3)), y + ((TAILLE_PISTE/3)), x + ((TAILLE_PISTE/3)*2), y );
		g2d.drawLine(x, y + ((TAILLE_PISTE/3)*2), x + (TAILLE_PISTE/3), y + (TAILLE_PISTE/3) );
		g2d.drawLine(x , y + TAILLE_PISTE-1, x , y + ((TAILLE_PISTE/3)*2)  );
		
	}
	
}
