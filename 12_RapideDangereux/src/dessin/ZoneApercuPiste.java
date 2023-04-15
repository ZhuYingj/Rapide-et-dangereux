package dessin;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import application.OutilsImage;

/**
 * Zone de dessin permettant d'afficher les apercu des piste
 * 
 * @author Ludovic Julien
 *
 */
public class ZoneApercuPiste extends JPanel {
	private Image img = null;

	/**
	 * Constructeur qui permet de créer une zone d'apercu de piste.
	 */
	// Ludovic Julien
	public ZoneApercuPiste() {
		img = OutilsImage.lireImage("PisteMexique.png");
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

	/**
	 * permet de dessiner les apercu des piste
	 * 
	 */
	// Ludovic Julien
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, null);
	}
}
