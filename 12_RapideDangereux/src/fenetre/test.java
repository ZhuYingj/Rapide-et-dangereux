package fenetre;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class test extends JPanel {

	/**
	 * Create the panel.
	 */
	public test() {
		setLayout(null);
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "test");
			}
		});
		btnTest.setBounds(435, 217, 325, 100);
		add(btnTest);

	}
}
