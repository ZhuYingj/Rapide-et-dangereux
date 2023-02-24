package dessin;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import pisteDeCourse.PisteMexique;

public class ZoneDessinVoirPiste extends JPanel implements Runnable {

	private Image img=null;

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
//		g2d.drawImage(image, o,o,null)
		
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
