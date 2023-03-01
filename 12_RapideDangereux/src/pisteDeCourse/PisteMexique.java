package pisteDeCourse;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import interfaces.Dessinable;
import utilitaireObjets.PisteDeDepart;
import utilitaireObjets.PisteHorizontale;
import utilitaireObjets.PisteVerticale;
import utilitaireObjets.PisteVirageBas;
import utilitaireObjets.PisteVirageGauche;

/**
 * Class qui permet de creer une piste deja faite (PisteMexique)
 * 
 * @author Ludovic Julien
 *
 */

public class PisteMexique implements Dessinable {
	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 80;
	/** Position en x de la piste au complet **/
	private int x;
	/** Position en y de la piste au complet  **/
	private int y;
	
	private PisteDeDepart depart;
	private PisteHorizontale horizontale;
	private PisteVerticale verticale;
	private PisteVirageBas bas;
	private PisteVirageGauche gauche;
	
	
	/**
	 * Methode qui permet de construire la piste Mexique a l'aide de parametres
	 * 
	 * @param x		position en x de la piste
	 * @param y		position en y de la piste
	 */
	public PisteMexique(int x, int y) {
		this.x = x;
		this.y = y;
	
	}
	
	
	/**
	 * Methode qui permet de dessiner la piste Mexique sur la zone d'animation a l'aide de g2d
	 */
	@Override
	public void dessiner(Graphics2D g2d) {
		
		//piste virgae bas :
		bas.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale depart :
		depart = new PisteDeDepart(x,y);
		depart.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x + TAILLE_PISTE;
		
		//Piste virage vers la gauche:
		gauche = new PisteVirageGauche(x,y);
		gauche.dessiner(g2d);
		this.y = y + TAILLE_PISTE;
		
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);
		this.y = y + TAILLE_PISTE;
	
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);
		this.y = y + TAILLE_PISTE;
	
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);
		this.y = y + TAILLE_PISTE;
		
		//piste vers le bas:
		gauche = new PisteVirageGauche(x,y);
		gauche.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
		horizontale = new PisteHorizontale(x,y);
		horizontale.dessiner(g2d);
		this.x = x - TAILLE_PISTE;
		
		//piste horizontale :
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
		
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);	
		this.y = y - TAILLE_PISTE;
		
		//piste vertical:
		verticale = new PisteVerticale(x,y);
		verticale.dessiner(g2d);
		
	}

}
