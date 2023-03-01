package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import interfaces.Dessinable;

public class PisteHorizontale implements Dessinable {

	private static final int TAILLE_PISTE = 80;
	private int ligneRougeH1X;
	private int ligneRougeH1Y;
	private int ligneRougeH2X;
	private int ligneRougeH2Y;
	private Rectangle2D.Double pisteVerticale;
	private int x;
	private int y;
	private double pixelsParMetre = 1; //Defaut

	public PisteHorizontale(int x, int y) {
		this.x = x;
		this.y = y;
		this.ligneRougeH1X = x + 1;
	    this.ligneRougeH1Y = y;
	    this.ligneRougeH2X = x + 1;
	    this.ligneRougeH2Y = y + TAILLE_PISTE;

	}

	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D g2dCopie = (Graphics2D) g2d.create();
		g2dCopie.scale(pixelsParMetre, pixelsParMetre);
		g2dCopie.setColor(Color.BLACK);
		g2dCopie.fillRect(x, y, TAILLE_PISTE, TAILLE_PISTE);
		g2dCopie.setColor(Color.RED);
		g2dCopie.setColor(Color.RED);
		Stroke stroke = new BasicStroke(3f);
		g2dCopie.setStroke(stroke);
		g2dCopie.drawLine(ligneRougeH1X, ligneRougeH1Y, x + TAILLE_PISTE - 1, y);
		g2dCopie.drawLine(ligneRougeH2X, ligneRougeH2Y, x + TAILLE_PISTE - 1, y + TAILLE_PISTE);

	}

	public double getPixelsParMetre() {
		return pixelsParMetre;
	}

	public void setPixelsParMetre(double pixelsParMetre) {
		this.pixelsParMetre = pixelsParMetre;
		
	}
	
	public int getLigneRougeH1Y() {
        return ligneRougeH1Y;
    }

    public int getLigneRougeH2Y() {
        return ligneRougeH2Y;
    }
    


}
