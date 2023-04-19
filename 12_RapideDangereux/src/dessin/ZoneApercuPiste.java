package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import application.OutilsImage;
import geometrie.Vecteur2D;

/**
 * Zone de dessin permettant d'afficher les apercu des piste
 * 
 * @author Ludovic Julien
 *
 */
public class ZoneApercuPiste extends JPanel {
	private Image img = null;
	private Ellipse2D.Double v1;
	private Ellipse2D.Double v2;
	private Color color1 = Color.yellow;
	private Color color2 = Color.white;
	/**Position défaut pour piste mexique**/
	private Vecteur2D pos1 = new Vecteur2D(86, 9);
	/**Position défaut pour piste mexique**/
	private Vecteur2D pos2 = new Vecteur2D(86, 53);

	/**
	 * Constructeur qui permet de créer une zone d'apercu de piste.
	 */
	// Ludovic Julien
	public ZoneApercuPiste() {
		img = OutilsImage.lireImage("PisteMexique.png");
	}

	/**
	 * permet de dessiner les apercu des piste
	 * 
	 */
	// Ludovic Julien

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(img, 0, 0, null);
		v1 = new Ellipse2D.Double(pos1.getX(), pos1.getY(), 20, 20);
		g2d.setColor(color1);
		g2d.fill(v1);
		v2 = new Ellipse2D.Double(pos2.getX(), pos2.getY(), 20, 20);
		g2d.setColor(color2);
		g2d.fill(v2);
	}

	/**
	 * permet de modifier la variable img
	 * 
	 * @param img
	 */
	// Ludovic Julien
	public void setImg(Image img) {
		this.img = img;
	}

	public Vecteur2D getPos1() {
		return pos1;
	}

	public void setPos1(Vecteur2D pos1) {
		this.pos1 = pos1;
	}

	public Vecteur2D getPos2() {
		return pos2;
	}

	public void setPos2(Vecteur2D pos2) {
		this.pos2 = pos2;
	}

	public Color getColor1() {
		return color1;
	}

	public void setColor1(Color color1) {
		this.color1 = color1;
	}

	public Color getColor2() {
		return color2;
	}

	public void setColor2(Color color2) {
		this.color2 = color2;
	}
}
