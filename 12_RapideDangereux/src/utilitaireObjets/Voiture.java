package utilitaireObjets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import javax.swing.JPanel;

import geometrie.FlecheVectorielle;
import geometrie.Vecteur2D;
import interfaces.Dessinable;
import interfaces.Selectionnable;
import physique.MoteurPhysique;

/**
 * Classe qui permet de gérér et de créer la voiture
 * 
 * @author Tan Tommy Rin
 *@author Kevin Nguyen
 */

public class Voiture implements Dessinable, Selectionnable {
	/** Diametre de la voiture puisque la voiture est un cercle **/
	private double diametre = 1;
	/** Masse de la voiture **/
	private double masseEnKg = 50;
	/** Nombre de pixel par metre **/
	private double pixelsParMetre;
	/** La couleur de la voiture **/
	private Color skin;
	/** La forme de la voiture **/
	private Ellipse2D cercle;
	/** Vecteur de la position de la voiture **/
	private Vecteur2D position ;
	/** Vecteur de la vitesse de la voiture **/
	private Vecteur2D vitesse = new Vecteur2D(0, 0); // par defaut
	/** Vecteur de l'acceleration de la voiture **/
	private Vecteur2D accel = new Vecteur2D(0, 0); // par defaut
	private FlecheVectorielle flecheVectorielle;
	private double angle = 0;
	private Shape voitureTransfo;

	/**
	 * Méthode qui permet de construire une voiture avec des paramètres
	 * 
	 * @param position VecteurPosition voulu
	 * @param skin     La couleur de la voiture
	 * @param masse    La masse de la voiture
	 * @param diametre Le diametre de la voiture
	 */
	public Voiture(Vecteur2D position, Color skin, double masse, double diametre, double angle) {
		this.position = position;
		this.skin = skin;
		this.masseEnKg = masse;
		this.diametre = diametre;
		this.angle = angle;
		creerLaGeometrie();

	}

	/**
	 * Constructeur défaut avec un diametre fixé d'avance
	 */
	public Voiture() {
		this.diametre = 25;
	}

	/**
	 * Création de la voiture à l'aide d'une ellipse et la flèche vectorielle
	 */
	//Kevin Nguyen
	private void creerLaGeometrie() {

		cercle = new Ellipse2D.Double(position.getX(), position.getY(), diametre, diametre);
		flecheVectorielle = new FlecheVectorielle(position.getX() + diametre / 2, (position.getY() + diametre / 2),
				diametre, 0);

		flecheVectorielle.setLongueurTraitDeTete(5);
		flecheVectorielle.setAngleTete(90);

	}

	/**
	 * Méthode qui permet de dessiner sur la zone d'animation à l'aide du g2d
	 */
	//Kevin Nguyen
	@Override
	public void dessiner(Graphics2D g2d) {
		Graphics2D gCopie = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();

		mat.rotate(angle, position.getX() + diametre / 2, position.getY() + diametre / 2);
		gCopie.setColor(skin);
		voitureTransfo = mat.createTransformedShape(cercle);

		gCopie.fill(voitureTransfo);
		gCopie.setColor(Color.RED);

		gCopie.rotate(angle, position.getX() + diametre / 2, (position.getY() + diametre / 2));

		flecheVectorielle.dessiner(gCopie);

	}

	//	public void gererCollisionSol() {
	//	
	//	}
	/**
	 * Méthode qui retourne le diametre de la voiture
	 * 
	 * @return le diametre de la voiture
	 */

	public double getDiametre() {
		return diametre;
	}

	/**
	 * Méthode qui change la valeur du diametre par un diametre voulu
	 * 
	 * @param diametre le diametre voulu
	 */

	public void setDiametre(double diametre) {
		this.diametre = diametre;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui permet de retourner la masse de la voiture en kg
	 * 
	 * @return la voiture en kg
	 */

	public double getMasseEnKg() {
		return masseEnKg;
	}

	/**
	 * Méthode qui change la masse de la voiture par une masse voulu
	 * 
	 * @param masseEnKg une masse voulu
	 */

	public void setMasseEnKg(double masseEnKg) {
		this.masseEnKg = masseEnKg;
	}

	/**
	 * Méthode qui retourne la couleur de la voiture
	 * 
	 * @return la couleur de la voiture
	 */

	public Color getSkin() {
		return skin;
	}

	/**
	 * Méthode qui permet de retourner la couleur de la voiture
	 * 
	 * @param skin la couleur voulue
	 */
	public void setSkin(Color skin) {
		this.skin = skin;
	}

	/**
	 * Méthode qui retorune la position de la voiture en vecteur
	 * 
	 * @return la position de la voiture en vecteur
	 */
	//Kevin Nguyen
	public Vecteur2D getPosition() {
		return position;
	}

	/**
	 * Méthode qui permet de changer la position de la voiture par une nouvelle
	 * 
	 * @param position nouvelle position de la voiture
	 */
	//Kevin Nguyen
	public void setPosition(Vecteur2D position) {
		this.position = position;
		creerLaGeometrie();
	}

	/**
	 * Méthode qui retorune le vecteur de vitesse
	 * 
	 * @return vecteur de vitesse
	 */
	//Kevin Nguyen
	public Vecteur2D getVitesse() {
		return vitesse;
	}

	/**
	 * Méthode qui permet de changer le vecteur de vitesse par un nouveau
	 * 
	 * @param vitesse vitesse voulu
	 */
	//Kevin Nguyen
	public void setVitesse(Vecteur2D vitesse) {
		this.vitesse = vitesse;
	}

	/**
	 * Méthode qui permet de retourner le vecteur d'acceleration
	 * 
	 * @return le vecteur d'acceleration
	 */
	//Kevin Nguyen
	public Vecteur2D getAccel() {
		return accel;
	}

	/**
	 * Méthode qui permet de changer le vecteur d'accélération
	 * 
	 * @param accel La nouvelle acceleration
	 */
	//Kevin Nguyen
	public void setAccel(Vecteur2D accel) {
		this.accel = accel;
	}

	/**
	 * Retourne l'angle (orientation) de la voiture et de la fleche vectorielle
	 * @return Angle de la voiture
	 */
	//Kevin Nguyen
	public double getAngle() {
		return angle;
	}

	/**
	 * Attribuer un angle a la voiture et a la fleche vectorielle
	 * @param angle Nouvel angle
	 */
	//Kevin Nguyen
	public void setAngle(double angle) {
		this.angle = angle;
		creerLaGeometrie();
	}

	/**
	 * Recalcule l'acceleration de la balle a l'aide la nouvelle somme des forces
	 * passee en parametre Ceci aura pour consequence de modifier l'acceleration
	 * 
	 * @param sommeForcesSurLaBalle La somme des forces exercees sur la balle
	 */
	//Kevin Nguyen
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
	//Kevin Nguyen
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
	public void setPixelsParMetre(double pixelsParMetreVoulu) {
		this.pixelsParMetre = pixelsParMetreVoulu;

	}

	/**
	 * Méthode qui retourne le nombre de pixels par metre
	 * 
	 * @return nombre de pixel par metre
	 */

	public double getPixelsParMetre() {
		return this.pixelsParMetre;
	}

	// A completer plus tard
	@Override
	public boolean contient(double xPix, double yPix) {

		return false;
	}

	/**
	 * Cette methode permet de determiner si la voiture depasse le composant dessin et changer sa vitesse selon la collision
	 * @param positionXDroite Position droite en x du composant
	 * @param positionXGauche Position gauche en x du composant
	 * @param positionYBas Position bas en y du composant
	 * @param positionYHaut Position haut en y du composant
	 */
	//Kevin Nguyen
	public void gererCollision(double positionXDroite,double positionXGauche, double positionYBas,  double positionYHaut) {

		//pour le bas
		if(position.getY() > positionYBas-this.diametre) {
			this.vitesse = getVitesse();
			setVitesse(new Vecteur2D(vitesse.getX(), -vitesse.getY()));
			position.setY(positionYBas-this.diametre);
		}
		//pour le haut
		else if (position.getY() < positionYHaut) {
			this.vitesse = getVitesse();
			setVitesse(new Vecteur2D(vitesse.getX(), -vitesse.getY()));
			position.setY(positionYHaut);
		}
		//pour la droite
		else if (position.getX() > positionXDroite-this.diametre) {
			this.vitesse = getVitesse();
			setVitesse(new Vecteur2D(-vitesse.getX(), vitesse.getY()));
			position.setX(positionXDroite-this.diametre);
		}
		//pour   la gauche
		else if (position.getX() < positionXGauche) {
			this.vitesse = getVitesse();
			setVitesse(new Vecteur2D(-vitesse.getX(), vitesse.getY()));
			position.setX(positionXGauche);
		}
	}
}
