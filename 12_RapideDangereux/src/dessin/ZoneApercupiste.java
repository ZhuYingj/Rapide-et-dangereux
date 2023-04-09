package dessin;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Zone de dessin permettant d'afficher les apercu des piste
 * 
 * @author Ludovic Julien
 *
 */
public class ZoneApercupiste extends JPanel {
	private Image img = null;

	public ZoneApercupiste() {
		img = OutilsImage.lireImage("PisteMexique.png");
	}

	/**
	 * permet de modifier la variable img
	 * 
	 * @param img
	 */
	//Ludovic Julien
	public void setImg(Image img) {
		this.img = img;
	}

	/**
	 * permet de dessiner les apercu des piste
	 * 
	 */
	//Ludovic Julien
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, null);
	}
}
