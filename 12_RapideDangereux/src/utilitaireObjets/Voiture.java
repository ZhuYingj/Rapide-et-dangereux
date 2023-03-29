package utilitaireObjets;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;

import dessin.ZoneVitesse;
import geometrie.FlecheVectorielle;
import geometrie.Vecteur2D;
import interfaces.Dessinable;
import physique.MoteurPhysique;

/**
 * Classe qui permet de gérér et de créer la voiture
 * 
 * @author Tan Tommy Rin
 * @author Kevin Nguyen
 */

public class Voiture implements Dessinable, Serializable {

	private static final long serialVersionUID = 1L;
	/** Diametre de la voiture puisque la voiture est un cercle **/
	private double diametre = 1;
	/** Masse de la voiture **/
	private double masseEnKg;
	/** Masse initiale de la voiture **/
	private double masseEnKgInitial;
	/** Nombre de pixel par metre **/
	private double pixelsParMetre;
	/** La couleur de la voiture **/
	private Color skin;
	/** La forme de la voiture **/
	private Ellipse2D cercle;
	/** Vecteur de la position de la voiture **/
	private Vecteur2D position;
	/** Vecteur de la vitesse de la voiture **/
	private Vecteur2D vitesse = new Vecteur2D(0, 0); // par defaut
	/** Vecteur de l'acceleration de la voiture **/
	private Vecteur2D accel = new Vecteur2D(0, 0); // par defaut
	/** Fleche vectorielle visuelle **/
	private FlecheVectorielle flecheVectorielle;
	/** Angle en degré pour la fleche vectorielle **/
	private double angle = 0;
	/** Notre voiture après transformation **/
	private transient Shape voitureTransfo;
	/** Vitesse maximale selon le niveau sélectionné **/
	private double vitesseMaxSelonNiveau;
	/** Le diametre de la voiture initial **/
	private double diametreInitial;
	
	public static ArrayList<Double> vitessesParSeconde;

	/**
	 * Méthode qui permet de construire une voiture avec des paramètres
	 * 
	 * @param position           VecteurPosition voulu
	 * @param skin               La couleur de la voiture
	 * @param masse              La masse de la voiture
	 * @param diametre           Le diametre de la voiture
	 * @param vitesseMaxSelonNiv La vitesse maximale selon le niveau selectionné
	 */
	// Par Tan Tommy Rin
	public Voiture(Vecteur2D position, Color skin, double masse, double diametre, double angle,
			double vitesseMaxSelonNiv) {
		this.position = position;
		this.skin = skin;
		this.diametre = diametre;
		this.angle = angle;
		this.vitesseMaxSelonNiveau = vitesseMaxSelonNiv;
		this.masseEnKg = masse;
		masseEnKgInitial = masseEnKg;
		diametreInitial = diametre;
		creerLaGeometrie();
		vitessesParSeconde = new ArrayList<Double>();
	}

	/**
	 * Constructeur défaut avec un diametre et une vitesse maximale fixé d'avance
	 */
	// Par Tan Tommy Rin
	public Voiture() {
		this.diametre = 16;
		this.vitesseMaxSelonNiveau = 5.0;
		masseEnKgInitial = masseEnKg;
		diametreInitial = diametre;
	}

	/**
	 * Création de la voiture à l'aide d'une ellipse et la flèche vectorielle
	 */
	// Kevin Nguyen
	private void creerLaGeometrie() {

		cercle = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre);
		flecheVectorielle = new FlecheVectorielle(position.getX() + diametre / 2, (position.getY() + diametre / 2),
				diametre, 0);

		flecheVectorielle.setLongueurTraitDeTete(2.5);
		flecheVectorielle.setAngleTete(90);

	}

	/**
	 * Méthode qui permet de dessiner sur la zone d'animation à l'aide du g2d
	 */
	// Kevin Nguyen
	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D gCopie = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
//		gCopie.scale(pixelsParMetre, pixelsParMetre);
		gCopie.rotate(angle, position.getX() + diametre / 2, position.getY() + diametre / 2);

		gCopie.setColor(skin);
		voitureTransfo = mat.createTransformedShape(cercle);

		gCopie.fill(voitureTransfo);
		gCopie.setColor(Color.RED);

		gCopie.setStroke(new BasicStroke((float) 0.5));
		flecheVectorielle.dessiner(gCopie);

	}

	/**
	 * Méthode qui retourne le diametre de la voiture
	 * 
	 * @return le diametre de la voiture
	 */
	// Par Tan Tommy Rin
	public double getDiametre() {
		return diametre;
	}

	/**
	 * Méthode qui change la valeur du diametre par un diametre voulu
	 * 
	 * @param diametre le diametre voulu
	 */
	// Par Tan Tommy Rin
	public void setDiametre(double diametre) {
		this.diametre = diametre;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui permet de retourner la masse de la voiture en kg
	 * 
	 * @return la voiture en kg
	 */
	// Par Tan Tommy Rin
	public double getMasseEnKg() {
		return masseEnKg;
	}

	/**
	 * Méthode qui change la masse de la voiture par une masse voulu
	 * 
	 * @param masseEnKg une masse voulu
	 */
	// Par Tan Tommy Rin
	public void setMasseEnKg(double masseEnKg) {
		this.masseEnKg = masseEnKg;
	}

	/**
	 * Méthode qui retourne la couleur de la voiture
	 * 
	 * @return la couleur de la voiture
	 */
	// Par Tan Tommy Rin
	public Color getSkin() {
		return skin;
	}

	/**
	 * Méthode qui permet de retourner la couleur de la voiture
	 * 
	 * @param skin la couleur voulue
	 */
	// Par Tan Tommy Rin
	public void setSkin(Color skin) {
		this.skin = skin;
	}

	/**
	 * Méthode qui retorune la position de la voiture en vecteur
	 * 
	 * @return la position de la voiture en vecteur
	 */
	// Kevin Nguyen
	public Vecteur2D getPosition() {
		return position;
	}

	/**
	 * Méthode qui permet de changer la position de la voiture par une nouvelle
	 * 
	 * @param position nouvelle position de la voiture
	 */
	// Kevin Nguyen
	public void setPosition(Vecteur2D position) {
		this.position = position;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui retorune le vecteur de vitesse
	 * 
	 * @return vecteur de vitesse
	 */
	// Kevin Nguyen
	public Vecteur2D getVitesse() {
		return vitesse;
	}

	/**
	 * Méthode qui permet de changer le vecteur de vitesse par un nouveau
	 * 
	 * @param vitesse vitesse voulu
	 */
	// Kevin Nguyen
	public void setVitesse(Vecteur2D vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * Méthode qui permet de retourner le vecteur d'acceleration
	 * 
	 * @return le vecteur d'acceleration
	 */
	// Kevin Nguyen
	public Vecteur2D getAccel() {
		return accel;
	}

	/**
	 * Méthode qui permet de changer le vecteur d'accélération
	 * 
	 * @param accel La nouvelle acceleration
	 */
	// Kevin Nguyen
	public void setAccel(Vecteur2D accel) {
		this.accel = accel;
	}

	/**
	 * Retourne l'angle (orientation) de la voiture et de la fleche vectorielle
	 * 
	 * @return Angle de la voiture
	 */
	// Kevin Nguyen
	public double getAngle() {
		return angle;
	}

	/**
	 * Attribuer un angle a la voiture et a la fleche vectorielle
	 * 
	 * @param angle Nouvel angle
	 */
	// Kevin Nguyen
	public void setAngle(double angle) {
		this.angle = angle;
		creerLaGeometrie();
	}

	/**
	 * Recalcule l'acceleration de la voiture a l'aide la nouvelle somme des forces
	 * passee en parametre Ceci aura pour consequence de modifier l'acceleration
	 * 
	 * @param sommeForcesSurLaBalle La somme des forces exercees sur la voiture
	 */
	// Kevin Nguyen
	public void setSommeDesForces(Vecteur2D sommeForcesSurLaVoiture) {
		// ici changer les forces signifie recalculer l'acceleration
		// on relegue cette tache au moteur physique.
		try {
			accel = MoteurPhysique.calculAcceleration(sommeForcesSurLaVoiture, masseEnKg);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Calcule la nouvelle vitesse et la nouvelle position de la balle apres cet
	 * nouvel intervalle de temps.
	 * 
	 * @param deltaT intervalle de temps (pas)
	 */
	// Kevin Nguyen
	public void avancerUnPas(double deltaT) {
		this.vitesse = MoteurPhysique.calculVitesse(deltaT, vitesse, accel);
		this.position = MoteurPhysique.calculPosition(deltaT, position, vitesse);

		creerLaGeometrie(); // la position a changé! on recree notre cercle

	}

	/**
	 * Méthode qui permet de changer le nombre de pixel par mètre par un nombre
	 * voulu
	 * 
	 * @param pixelsParMetreVoulu
	 */
	// Par Tan Tommy Rin
	public void setPixelsParMetre(double pixelsParMetreVoulu) {
		this.pixelsParMetre = pixelsParMetreVoulu;

	}

	/**
	 * Méthode qui retourne le nombre de pixels par metre
	 * 
	 * @return nombre de pixel par metre
	 */
	// Par Tan Tommy Rin
	public double getPixelsParMetre() {
		return this.pixelsParMetre;
	}

	/**
	 * Méthode qui retourne la vitesse maximale selon le niveau selectionné
	 * 
	 * @return la vitesse maximale selon le niveau selectionné
	 */
	// Par Tan Tommy Rin
	public double getVitesseMaxSelonNiveau() {
		return vitesseMaxSelonNiveau;
	}

	/**
	 * Méthode qui permet de changer la vitesse maximale par une autre vitesse voulu
	 * 
	 * @param vitesseMaxSelonNiveau La nouvelle vitesse maximale voulue
	 */
	// Par Tan Tommy Rin
	public void setVitesseMaxSelonNiveau(double vitesseMaxSelonNiveau) {
		this.vitesseMaxSelonNiveau = vitesseMaxSelonNiveau;
	}

	public Shape getVoitureTransfo() {
		return voitureTransfo;
	}

	public void setVoitureTransfo(Shape voitureTransfo) {
		this.voitureTransfo = voitureTransfo;
	}

	/** Méthode qui permet de retourner la masse en kg initiale de la voiture **/
	// Par TanTommyRin
	public double getMasseEnKgInitial() {
		return masseEnKgInitial;
	}

	/**
	 * Méthode qui permet de changer la masse initiale de la voiture par une autre
	 * 
	 * @param masseEnKgInitial masseInitiale voulue
	 */
	// Par Tan Tommy Rin

	public void setMasseEnKgInitial(double masseEnKgInitial) {
		this.masseEnKgInitial = masseEnKgInitial;
	}

	/**
	 * Méthode qui permet de retourner la valeur du diametre initiale de la voiture
	 * 
	 * @return la valeur du diametre initiale
	 */
	// Par Tan Tommy Rin
	public double getDiametreInitial() {
		return diametreInitial;
	}

	/**
	 * Retourne la forme cercle qui est la voiture
	 * 
	 * @return la forme cercle
	 */
	// Kevin Nguyen
	public Ellipse2D getCercle() {
		return cercle;
	}

//	 public void ajouterVitesseParSeconde() {
//		    double vitesseActuelle = (vitesse.getX()+vitesse.getY());
//		    if (vitesseActuelle > 0) {
//		    	vitesseActuelle = (vitesseActuelle*-1);
//		    } 
//	        vitessesParSeconde.add(vitesseActuelle);
//	        System.out.println("Viettes de la voiture � ce moment est: " + vitesseActuelle);
//		}
	 
}
