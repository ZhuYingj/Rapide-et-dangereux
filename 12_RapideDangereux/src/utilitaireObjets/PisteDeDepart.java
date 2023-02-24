package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

public class PisteDeDepart implements Dessinable{

	private static final int TAILLE_PISTE = 80;
	private int x;
	private int y;
	
		public PisteDeDepart(int x, int y) {
			this.x = x;
			this.y = y;
		
		}
		

		@Override
		public void dessiner(Graphics2D g2d) {
			g2d.setColor(Color.BLACK);
			g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
			g2d.setColor(Color.RED);
			g2d.setColor(Color.RED);
			Stroke stroke1 = new BasicStroke(3f);
			g2d.setStroke(stroke1);
			g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
			g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
			
			g2d.setColor(Color.WHITE);
			Stroke stroke0= new BasicStroke(5f);
			g2d.setStroke(stroke0);
			g2d.drawLine(x+(TAILLE_PISTE/2),  y+(TAILLE_PISTE/7), x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*2));
			g2d.drawLine(x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*3), x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*4));
			g2d.drawLine(x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*5), x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*6) );
		
		
	
		}
	
}
