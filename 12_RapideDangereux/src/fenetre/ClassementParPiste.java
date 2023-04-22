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
public class ClassementParPiste extends JPanel{
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Methode qui permettra de s'ajouter en tant qu'ecouteur
	 */
//Ludovic Julien
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Creation de la fenetre.
	 */
	//Ludovic Julien
	public ClassementParPiste() {
		setBackground(Color.CYAN);
		setOpaque(true);
		
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Tableau du classement par piste");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(188, 99, 428, 83);
		add(lblNewLabel);

		JButton btnReinitialiser = new JButton("R\u00E9initialiser les statistiques");
		btnReinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//MyTableModel.resetTableData();
				try {
					GestionnaireDeFichiersSurLeBureau.viderFichier();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnReinitialiser.setBounds(94, 403, 197, 39);
		add(btnReinitialiser);

		JButton btnFermer = new JButton("QUITTER");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs.firePropertyChange("QUITTER", null, -1);

			}
		});
		btnFermer.setBounds(315, 479, 139, 39);
		add(btnFermer);

		JButton btnColorer = new JButton("Colorer l'arriere plan");
		btnColorer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Color bgColor = JColorChooser.showDialog(ClassementParPiste.this,"Choisir une couleur", Color.WHITE);
				
		        if (bgColor != null) { 
		            setBackground(bgColor); 
		        }
			}
		});
		btnColorer.setBounds(519, 403, 189, 39);
		add(btnColorer);
		
		TableauRecord tableauRecord = new TableauRecord();
		tableauRecord.setBounds(183, 267, 452, 74);
		add(tableauRecord);
	}
	
	
	
}
