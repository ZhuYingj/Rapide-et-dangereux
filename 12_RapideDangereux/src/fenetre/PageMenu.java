package fenetre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PageMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageMenu frame = new PageMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PageMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnJouer = new JButton("JOUER");
		btnJouer.setForeground(Color.BLUE);
		btnJouer.setBounds(424, 289, 114, 32);
		contentPane.add(btnJouer);
		
		JButton btnAide = new JButton("AIDE");
		btnAide.setForeground(Color.BLUE);
		btnAide.setBounds(424, 349, 114, 32);
		contentPane.add(btnAide);
		
		JButton btnQuitter = new JButton("QUITTER");
		btnQuitter.setForeground(Color.BLUE);
		btnQuitter.setBounds(424, 405, 114, 32);
		contentPane.add(btnQuitter);
		
		JLabel lblTitre = new JLabel("Rapide et Dangereux");
		lblTitre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 30));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(279, 126, 436, 59);
		contentPane.add(lblTitre);
	}
}
