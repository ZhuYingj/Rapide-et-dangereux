package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;
/**
 * class qui permet de creer un objet colle
 * 
 * @author Ludovic Julien
 *
 */
public class Colle implements Dessinable {

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 87;
	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer  **/
	private int y;
	private TypeObjetSpecial typeObjet = TypeObjetSpecial.COLLE;
	
	/**
	 * Méthode qui permet de créer un objet colle à l'aide de paramètres
	 * 
	 * @param x position en x
	 * @param y position en y
	 
	 */
	public Colle(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Methode qui permet de dessiner l'objet colle sur la zone d'animation a
	 * l'aide de g2d
	 */
	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.ORANGE);
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

	public TypeObjetSpecial getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjetSpecial typeObjet) {
		this.typeObjet = typeObjet;
	}

	}
	

