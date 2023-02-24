package fenetre;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import application.Identifiants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FenetreMenu extends JPanel {
	private JLabel lblInfoRecue;
	private JButton btnJouer;
	private JButton btnAide;
	private JButton btnQuitter;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private JButton btnTest;
	
	/**
	 * Create the panel.
	 */
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public FenetreMenu() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		setLayout(null);
		setBounds(100,100,1300,700);
		
		JLabel lblNewLabel = new JLabel("Rapide et Dangereux");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(293, 146, 349, 75);
		add(lblNewLabel);
		
		btnJouer = new JButton("JOUER");
		btnJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("JOUER", 0, -1);
			}
		});
		btnJouer.setBounds(411, 245, 100, 49);
		add(btnJouer);
		
		btnAide = new JButton("AIDE");
		btnAide.setBounds(411, 321, 100, 49);
		add(btnAide);
		
		btnQuitter = new JButton("QUITTER");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(411, 402, 100, 49);
		add(btnQuitter);
		
		btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("Test", 0, -1);
			}
		});
		btnTest.setBounds(411, 462, 100, 54);
		add(btnTest);

		
	}
	public void setInfoRecue(Identifiants idRecu) {
		lblInfoRecue.setText(idRecu.toString());;
	}
}