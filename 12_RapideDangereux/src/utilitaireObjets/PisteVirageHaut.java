package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics2D;

import interfaces.Dessinable;

public class PisteVirageHaut implements Dessinable{

private static final int TAILLE_PISTE = 50;
	
	private int x;
	private int y;
	
	public PisteVirageHaut(int x, int y) {
		this.x = x;
		this.y = y;
	
	}
	
	@Override
	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, TAILLE_PISTE,TAILLE_PISTE);
		g2d.setColor(Color.RED);
		g2d.fillRect(x, y, x+2, y + (TAILLE_PISTE/3));
		g2d.fillRect(x, y + (TAILLE_PISTE/3), x + (TAILLE_PISTE/3), y + ((TAILLE_PISTE/3)*2));
		g2d.fillRect(x + (TAILLE_PISTE/3),  y + ((TAILLE_PISTE/3)*2), x + ((TAILLE_PISTE/3)*2), y + TAILLE_PISTE );
		g2d.fillRect(x + ((TAILLE_PISTE/3)*2), y + TAILLE_PISTE, x + TAILLE_PISTE, y + TAILLE_PISTE  );
		
	}
	
}
