package pisteDeCourse;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import interfaces.Dessinable;
import utilitaireObjets.PisteDeDepart;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVirageBas;

public class PisteMexique implements Dessinable {

	private static final int TAILLE_PISTE = 80;
	
	private int x;
	private int y;
	
	private PisteDeDepart depart;

	private PisteHorizontale horizontale;

	private PisteVirageBas bas;
	
	public PisteMexique(int x, int y) {
		this.x = x;
		this.y = y;
	
	}
	
	
	
	@Override
	public void dessiner(Graphics2D g2d) {
		
		//piste virgae bas :
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		Stroke stroke = new BasicStroke(3f);
//		g2d.setStroke(stroke);
//		g2d.drawLine(x + ((TAILLE_PISTE/3)*2), y, x + TAILLE_PISTE-1, y );
//		g2d.drawLine(x + ((TAILLE_PISTE/3)), y + ((TAILLE_PISTE/3)), x + ((TAILLE_PISTE/3)*2), y );
//		g2d.drawLine(x, y + ((TAILLE_PISTE/3)*2), x + (TAILLE_PISTE/3), y + (TAILLE_PISTE/3) );
//		g2d.drawLine(x , y + TAILLE_PISTE-1, x , y + ((TAILLE_PISTE/3)*2)  );
		bas = new PisteVirageBas(x,y);
		bas.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale depart :
		
		
		
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke1 = new BasicStroke(3f);
//		g2d.setStroke(stroke1);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
//		
//		g2d.setColor(Color.WHITE);
//		Stroke stroke0= new BasicStroke(5f);
//		g2d.setStroke(stroke0);
//		g2d.drawLine(x+(TAILLE_PISTE/2),  y+(TAILLE_PISTE/7), x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*2));
//		g2d.drawLine(x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*3), x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*4));
//		g2d.drawLine(x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*5), x+(TAILLE_PISTE/2), y+((TAILLE_PISTE/7)*6) );
		depart = new PisteDeDepart(x,y);
		depart.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		
		
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke2 = new BasicStroke(3f);
//		g2d.setStroke(stroke2);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke3 = new BasicStroke(3f);
//		g2d.setStroke(stroke3);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke4 = new BasicStroke(3f);
//		g2d.setStroke(stroke4);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke5 = new BasicStroke(3f);
//		g2d.setStroke(stroke5);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke6 = new BasicStroke(3f);
//		g2d.setStroke(stroke6);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//Piste virage vers la gauche:
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke7 = new BasicStroke(3f);
		g2d.setStroke(stroke7);
		g2d.drawLine(x +1, y, x + (TAILLE_PISTE/3), y );
		g2d.drawLine(x + (TAILLE_PISTE/3), y, x + ((TAILLE_PISTE/3)*2), y + (TAILLE_PISTE/3));
		g2d.drawLine(x + ((TAILLE_PISTE/3)*2), y + (TAILLE_PISTE/3), x + TAILLE_PISTE, y + ((TAILLE_PISTE/3)*2) );
		g2d.drawLine(x + TAILLE_PISTE, y + ((TAILLE_PISTE/3)*2), x + TAILLE_PISTE, y + TAILLE_PISTE -1);

		this.y = y + TAILLE_PISTE;
		
		//piste vertical:
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke8 = new BasicStroke(3f);
		g2d.setStroke(stroke8);
		g2d.drawLine(x, y +1, x, y + TAILLE_PISTE -1);
		g2d.drawLine(x + TAILLE_PISTE, y+1, x + TAILLE_PISTE, y + TAILLE_PISTE-1);
	
		this.y = y + TAILLE_PISTE;
	
		//piste vertical:
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke9 = new BasicStroke(3f);
		g2d.setStroke(stroke9);
		g2d.drawLine(x, y +1, x, y + TAILLE_PISTE -1);
		g2d.drawLine(x + TAILLE_PISTE, y+1, x + TAILLE_PISTE, y + TAILLE_PISTE-1);
	
		this.y = y + TAILLE_PISTE;
	
		//piste vertical:
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke10 = new BasicStroke(3f);
		g2d.setStroke(stroke10);
		g2d.drawLine(x, y +1, x, y + TAILLE_PISTE -1);
		g2d.drawLine(x + TAILLE_PISTE, y+1, x + TAILLE_PISTE, y + TAILLE_PISTE-1);
	
		this.y = y + TAILLE_PISTE;
		
		//piste vers le bas:
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke11 = new BasicStroke(3f);
		g2d.setStroke(stroke11);	
		g2d.drawLine(x, y + TAILLE_PISTE,x + ((TAILLE_PISTE/3)),  y + TAILLE_PISTE);
		g2d.drawLine(x + ((TAILLE_PISTE/3)), y + TAILLE_PISTE, x + ((TAILLE_PISTE/3)*2),y + ((TAILLE_PISTE/3)*2));
		g2d.drawLine(x + ((TAILLE_PISTE/3)*2),y + ((TAILLE_PISTE/3)*2), x + TAILLE_PISTE, y + ((TAILLE_PISTE/3)) );
		g2d.drawLine(x + TAILLE_PISTE, y + ((TAILLE_PISTE/3)), x + TAILLE_PISTE, y  );
		
		
		//piste horizontale :
		this.x = x - TAILLE_PISTE;
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke12 = new BasicStroke(3f);
//		g2d.setStroke(stroke12);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		
		this.x = x - TAILLE_PISTE;
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke13 = new BasicStroke(3f);
//		g2d.setStroke(stroke13);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		
		this.x = x - TAILLE_PISTE;
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke14 = new BasicStroke(3f);
//		g2d.setStroke(stroke14);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		
		this.x = x - TAILLE_PISTE;
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke15 = new BasicStroke(3f);
//		g2d.setStroke(stroke15);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		
		this.x = x - TAILLE_PISTE;
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke16 = new BasicStroke(3f);
//		g2d.setStroke(stroke16);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		
		this.x = x - TAILLE_PISTE;
//		g2d.setColor(Color.BLACK);
//		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
//		g2d.setColor(Color.RED);
//		g2d.setColor(Color.RED);
//		Stroke stroke17 = new BasicStroke(3f);
//		g2d.setStroke(stroke17);
//		g2d.drawLine(x+1, y, x + TAILLE_PISTE-1, y);
//		g2d.drawLine(x+1, y + TAILLE_PISTE, x + TAILLE_PISTE -1, y + TAILLE_PISTE);
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		
		this.x = x - TAILLE_PISTE;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke18 = new BasicStroke(3f);
		g2d.setStroke(stroke18);
		g2d.drawLine(x, y, x, y + (TAILLE_PISTE/3));
		g2d.drawLine(x, y + (TAILLE_PISTE/3), x + (TAILLE_PISTE/3), y + ((TAILLE_PISTE/3)*2));
		g2d.drawLine(x + (TAILLE_PISTE/3),  y + ((TAILLE_PISTE/3)*2), x + ((TAILLE_PISTE/3)*2), y + TAILLE_PISTE );
		g2d.drawLine(x + ((TAILLE_PISTE/3)*2), y + TAILLE_PISTE, x + TAILLE_PISTE, y + TAILLE_PISTE  );
		
		this.y = y - TAILLE_PISTE;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke19 = new BasicStroke(3f);
		g2d.setStroke(stroke19);
		g2d.drawLine(x, y +1, x, y + TAILLE_PISTE -1);
		g2d.drawLine(x + TAILLE_PISTE, y+1, x + TAILLE_PISTE, y + TAILLE_PISTE-1);
		
		this.y = y - TAILLE_PISTE;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke20 = new BasicStroke(3f);
		g2d.setStroke(stroke20);
		g2d.drawLine(x, y +1, x, y + TAILLE_PISTE -1);
		g2d.drawLine(x + TAILLE_PISTE, y+1, x + TAILLE_PISTE, y + TAILLE_PISTE-1);
		
		this.y = y - TAILLE_PISTE;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2d.setColor(Color.RED);
		Stroke stroke21 = new BasicStroke(3f);
		g2d.setStroke(stroke21);
		g2d.drawLine(x, y +1, x, y + TAILLE_PISTE -1);
		g2d.drawLine(x + TAILLE_PISTE, y+1, x + TAILLE_PISTE, y + TAILLE_PISTE-1);
		
	}

}
