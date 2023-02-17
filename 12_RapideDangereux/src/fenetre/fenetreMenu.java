package fenetre;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class fenetreMenu extends JPanel {

	
	/**
	 * Create the panel.
	 */
	public fenetreMenu() {
		setLayout(null);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(369, 409, 101, 34);
		add(btnQuitter);
		
		JButton btnAide = new JButton("Aide");
		btnAide.setBounds(369, 352, 101, 34);
		add(btnAide);
		
		JButton btnJouer = new JButton("Jouer");
		btnJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnJouer.setBounds(369, 299, 101, 34);
		add(btnJouer);
		
		JLabel lblNewLabel = new JLabel("Rapide et Dangereux");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(230, 203, 349, 75);
		add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 85, 22);
		add(menuBar);
		
		JMenu mnBarreOutils = new JMenu("New menu");
		mnBarreOutils.setForeground(Color.WHITE);
		mnBarreOutils.setBackground(Color.BLUE);
		menuBar.add(mnBarreOutils);
		
		JMenuItem mntmCentreAide = new JMenuItem("Centre D'aide");
		mnBarreOutils.add(mntmCentreAide);
		
		JMenuItem mntmModeScientifique = new JMenuItem("Mode Scientifique");
		mnBarreOutils.add(mntmModeScientifique);
		
		JMenuItem mntmEffetsSonores = new JMenuItem("Effets Sonores");
		mnBarreOutils.add(mntmEffetsSonores);

		
	}
}
