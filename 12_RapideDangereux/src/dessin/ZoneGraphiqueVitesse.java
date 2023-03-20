package dessin;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;
import java.awt.Color;

public class ZoneGraphiqueVitesse extends JPanel{
	    
	    private static final int WIDTH = 800;
	    private static final int HEIGHT = 600;
	    private static final int MARGIN = 50;
	    private static final int DOT_SIZE = 6;
	    
	    private List<Double> temps;
	    private List<Double> vitesses;

	    public ZoneGraphiqueVitesse(List<Double> temps, List<Double> vitesses) {
	        this.temps = temps;
	        this.vitesses = vitesses;
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        // dessiner les axes
	        g.setColor(Color.BLACK);
	        g.drawLine(MARGIN, HEIGHT - MARGIN, WIDTH - MARGIN, HEIGHT - MARGIN);
	        g.drawLine(MARGIN, HEIGHT - MARGIN, MARGIN, MARGIN);
	        
	        // dessiner les étiquettes des axes
	        g.drawString("Temps", WIDTH/2, HEIGHT - 10);
	        g.drawString("Vitesse", 0, HEIGHT/2);
	        
	        // tracer les points de données
	        g.setColor(Color.RED);
	        for (int i = 0; i < temps.size(); i++) {
	            int x = (int)(temps.get(i) * (WIDTH - 2 * MARGIN));
	            int y = HEIGHT - MARGIN - (int)(vitesses.get(i) * (HEIGHT - 2 * MARGIN));
	            g.fillOval(MARGIN + x - DOT_SIZE/2, y - DOT_SIZE/2, DOT_SIZE, DOT_SIZE);
	        }
	        
	        // tracer les lignes de connexion entre les points de données
	        g.setColor(Color.BLUE);
	        for (int i = 0; i < temps.size() - 1; i++) {
	            int x1 = (int)(temps.get(i) * (WIDTH - 2 * MARGIN));
	            int y1 = HEIGHT - MARGIN - (int)(vitesses.get(i) * (HEIGHT - 2 * MARGIN));
	            int x2 = (int)(temps.get(i+1) * (WIDTH - 2 * MARGIN));
	       //    int y2 = HEIGHT - MARGIN - (int)(vitesses.get(i+1) *
	        }


}
	    
}
