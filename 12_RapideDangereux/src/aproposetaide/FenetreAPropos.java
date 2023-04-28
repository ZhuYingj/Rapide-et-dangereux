package aproposetaide;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;

/**
 * Panel qui affiche dans des onglets de l'information sur les auteurs et sur
 * les sources // Caroline Houle Inspiration de cette classe
 * 
 * @author Ludovic Julien
 *
 */

public class FenetreAPropos extends JPanel {
	private static final long serialVersionUID = -3110011146750233775L;

	/**
	 * Cr�ation du panel
	 */
	// Ludovic Julien
	public FenetreAPropos() {
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		// noter: aucun layout précisé: le conteneur à onglets prendra la largeur de la
		// plus longue ligne de texte
		JTabbedPane tabOnglets = new JTabbedPane(JTabbedPane.TOP);
		add(tabOnglets);

		JPanel pnlAuteurs = new JPanel();
		tabOnglets.addTab("Auteurs", null, pnlAuteurs, null);
		pnlAuteurs.setLayout(null);

		JLabel lblAuteurs = new JLabel("<html>Équipe 12 <br><br>Kevin Nguyen<br>Ludovic Julien<br>Alexis Pineda<br>Tan Tommy Rin<br><br>Cours 420-SCD<br>Intégration des apprentissages en SIM<br>Hiver 2023</html>");
		lblAuteurs.setBounds(10, 11, 298, 189);
		lblAuteurs.setVerticalAlignment(SwingConstants.TOP);
		pnlAuteurs.add(lblAuteurs);

		JPanel pnlSources = new JPanel();
		tabOnglets.addTab("Sources", null, pnlSources, null);
		pnlSources.setLayout(new BorderLayout(0, 0));

		JLabel lblSources = new JLabel("<html>Musique du jeu : https://www.auboutdufil.com/index.php?id=647<br>Boite mystere : https://unturned.fandom.com/wiki/Chrome_Plated_Mystery_Box<br>fenetre Editeur : https://www.wallpaperflare.com/blue-dazzling-lines<br>-dark-background-4k-hd-technology-data-wireless-technology-wallpaper-mxjce<br>champignon : https://icones8.fr/icon/6OUwhiZ6iy_O/champignon<br>Boule de neige : http://clipart-library.com/clipart/ziXGaj4iB.htm<br>Colle : https://www.seekpng.com/ima/u2q8r5i1i1r5e6i1/<br>Trou noir : https://icons8.com/icons/set/black-hole<br>Image Jeu: https://stock.adobe.com/ca/search?k=faded+navy+blue+background<br>Image Mexique : https://www.dreamstime.com/mexico-cactus-desert-landscape-background-<br>ai-generative-image273476176<br>Image Canada : https://www.dreamstime.com/photos-images/winter-scenery.html<br>Image Italie : https://www.dreamstime.com/photos-images/florence-italy-dusk.html<br>Image start : https://www.onlinewebfonts.com/icon/396080<br>Image stop :  https://icons-for-free.com/music+pause+play+stop+icon-1320185671881968262/<br>Image reset :https://www.flaticon.com/free-icon/reset_2618245<br>Image prochain image : https://www.iconarchive.com/show/windows-8-icons-by-icons8/Media-Controls-Fast-Forward-icon.html");
						
					//	+ "<br>Module de code pour telle chose: aaa.bbb.org" + "<br>(etc etc) </html>");
		lblSources.setVerticalAlignment(SwingConstants.TOP);
		pnlSources.add(lblSources);
	}
}
