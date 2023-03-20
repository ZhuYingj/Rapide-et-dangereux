package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;

public class Accelerateur implements Dessinable, Selectionnable{

	/** Taille de la piste qui est toujours constante **/
	private static final int TAILLE_PISTE = 87;
	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer  **/
	private int y;
	private double pixelParMetre = 1;

	private Rectangle2D.Double carre;
	private Shape shapeCarre;
	
	
	public Accelerateur(int x, int y) {
		this.x = x;
		this.y = y;
		creerLaGeometrie();
	}
	
	
	public void dessiner(Graphics2D g2d) {
		Graphics2D gCopie = (Graphics2D) g2d.create();
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
		
		AffineTransform mat = new AffineTransform();
		shapeCarre = mat.createTransformedShape(carre);
		mat.scale(pixelParMetre, pixelParMetre);
		gCopie.setColor(Color.cyan);
		gCopie.fill(shapeCarre);
	}

	public double getPixelsParMetre() {
		return pixelParMetre;
	}

	public void setPixelsParMetre(double pixelParMetre) {
		this.pixelParMetre = pixelParMetre;

	}
	
	public void creerLaGeometrie() {
		 carre = new Rectangle2D.Double(x, y, x/2, y+TAILLE_PISTE);
	}
	
	
	
	public void applyeffect(Voiture voiture){
		System.out.println("asldafjajkdfb");
		voiture.setVitesse(Vecteur2D.multiplie(voiture.getVitesse(),2));
		voiture.setAccel(Vecteur2D.multiplie(voiture.getAccel(), 2));
	}
	
	
	public Shape getShapeCarre() {
		return shapeCarre;
	}

	public void setShapeCarre(Shape shapeCarre) {
		this.shapeCarre = shapeCarre;
	}


	@Override
	public boolean contient(double xPix, double yPix) {
		
		return false;
	}
	
	
}
