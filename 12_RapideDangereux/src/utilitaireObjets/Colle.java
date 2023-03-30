package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.TypeObjetSpecial;

/**
 * class qui permet de creer un objet colle
 * 
 * @author Alexis Pineda-Alvarado
 *
 */
public class Colle implements Dessinable {

	/** Taille de la piste qui est toujours constante **/
	private int taillePiste = 80;
	/** la position en x de depart que l'objet piste vas etre creer **/
	private int x;
	/** la position en y de depart que l'objet piste vas etre creer **/
	private int y;
	private TypeObjetSpecial typeObjet = TypeObjetSpecial.COLLE;
	private transient Shape shapeColle;
	private transient Area bouleDeNeigeAire;
	private transient Area bouleDeNeigeAireCopie;
	private transient Area aireVoiture;

	private double pixelsParMetre;;

	private Ellipse2D.Double colle;

	private double diametre;
	private Vecteur2D position;

	/**
	 * Constructeur permettant de créer une colle
	 * 
	 * @param pos      La position en vecteur2D
	 * @param diametre Le diametre de la colle
	 */

	public Colle(Vecteur2D pos, double diametre) {
		this.diametre = diametre;
		this.position = pos;

		creerLaGeometrie();

	}

	/**
	 * Méthode qui permet de dessiner sur le g2d
	 * 
	 * @param g2d Le composant graphique
	 */

	@Override

	public void dessiner(Graphics2D g2d) {
		g2d.setColor(Color.ORANGE);

		g2d.setStroke(new BasicStroke(3f));
		g2d.drawLine((int) this.position.getX(), (int) this.position.getY(), (int) this.position.getX() + taillePiste,
				(int) this.position.getY());
		g2d.drawLine((int) this.position.getX(), (int) this.position.getY() + taillePiste - 1,
				(int) this.position.getX() + taillePiste, (int) this.position.getY() + taillePiste - 1);
		g2d.drawLine((int) this.position.getX(), (int) this.position.getY(), (int) this.position.getX(),
				(int) this.position.getY() + taillePiste);
		g2d.drawLine((int) this.position.getX() + taillePiste, (int) this.position.getY(),
				(int) this.position.getX() + taillePiste, (int) this.position.getY() + taillePiste);
		g2d.drawLine((int) this.position.getX(), (int) this.position.getY(), (int) this.position.getX() + taillePiste,
				(int) this.position.getY() + taillePiste);
		g2d.drawLine((int) this.position.getX() + (taillePiste / 2), (int) this.position.getY(),
				(int) this.position.getX() + taillePiste, (int) this.position.getY() + (taillePiste / 2));
		g2d.drawLine((int) this.position.getX(), (int) this.position.getY() + (taillePiste / 2),
				(int) this.position.getX() + (taillePiste / 2), (int) this.position.getY() + taillePiste);
		System.out.println(x);

	}

	public TypeObjetSpecial getTypeObjet() {
		return typeObjet;
	}

	public void setTypeObjet(TypeObjetSpecial typeObjet) {
		this.typeObjet = typeObjet;
	}

	/**
	 * Méthode permetant de créer la géométrie de cet objet
	 */

	private void creerLaGeometrie() {

		colle = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre);
	}

	/**
	 * Méthode qui permet de changer le nombre de pixel par mètre par un nombre
	 * voulu
	 * 
	 * @param pixelsParMetreVoulu
	 */
	// Par Alexis Pineda-Alvarado
	public void setPixelsParMetre(double pixelsParMetreVoulu) {
		this.pixelsParMetre = pixelsParMetreVoulu;

	}

	/**
	 * Méthode qui retourne le nombre de pixels par metre
	 * 
	 * @return nombre de pixel par metre
	 */
	// Par Alexis Pineda-Alvarado
	public double getPixelsParMetre() {
		return this.pixelsParMetre;
	}

}
