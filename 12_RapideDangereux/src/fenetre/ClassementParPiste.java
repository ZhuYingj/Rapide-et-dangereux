package fenetre;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import application.GestionnaireDeFichiersSurLeBureau;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import dessin.TableauRecord;
import dessin.TableauRecord.MyTableModel;

import java.awt.Color;

/**
 * Class qui permet de creer et gerer le classement par piste
 * 
 * @author Ludovic Julien
 *
 */
public class ClassementParPiste extends JPanel {
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 * 
	 * @param listener L'écouteur
	 */
//Ludovic Julien
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.PCS.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */
	// Ludovic Julien
	public ClassementParPiste() {
		setBackground(Color.CYAN);
		setOpaque(true);

		setLayout(null);

		JLabel lblNewLabel = new JLabel("Tableau du classement par piste");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(395, 87, 428, 83);
		add(lblNewLabel);

		JButton btnReinitialiser = new JButton("R\u00E9initialiser les statistiques");
		btnReinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyTableModel.actionReinitialiser();		
				System.out.println("Reinitialiser");

			}
		});
		btnReinitialiser.setBounds(218, 361, 197, 39);
		add(btnReinitialiser);

		JButton btnFermer = new JButton("Retour");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PCS.firePropertyChange("QUITTER", null, -1);

			}
		});
		btnFermer.setBounds(10, 11, 107, 23);
		add(btnFermer);

		JButton btnColorer = new JButton("Colorer l'arriere plan");
		btnColorer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Color bgColor = JColorChooser.showDialog(ClassementParPiste.this, "Choisir une couleur", Color.WHITE);

				if (bgColor != null) { // vérifier si l'utilisateur a choisi une couleur
					setBackground(bgColor); // changer la couleur de l'arrière-plan
				}

			}
		});
		btnColorer.setBounds(786, 361, 189, 39);
		add(btnColorer);

		TableauRecord tableauRecord = new TableauRecord();
		tableauRecord.setBounds(146, 169, 902, 158);
		add(tableauRecord);
	}
	
	
	
}
