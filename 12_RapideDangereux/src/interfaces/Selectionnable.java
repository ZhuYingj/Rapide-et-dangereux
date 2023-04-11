package interfaces;

/**
 * Interface qui d�finit la m�thode qu'un objet doit impl�menter pour pouvoir
 * �tre s�lectionn�
 * 
 * @author Caroline Houle
 *
 */

public interface Selectionnable {

	/**
	 * Retourne vrai si le point pass� en param�tre fait partie de l'objet
	 * dessinable sur lequel cette methode sera appel�e
	 * 
	 * 
	 * @param xPix Coordonn�e en x du point (exprim� en pixels)
	 * @param yPix Coordonn�e en y du point (exprim� en pixels)
	 * @return si le clic contient la forme
	 */

	public boolean contient(double xPix, double yPix);

}
