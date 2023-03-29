package fenetre;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import utilitaireObjets.Quadrillage;

public class PanelQuadrillage extends JPanel {

	private Quadrillage quad;

	/**
	 * Create the panel.
	 */
	public PanelQuadrillage() {

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		quad = new Quadrillage(0,0);
		
		quad.dessiner(g2d);
	}

}
