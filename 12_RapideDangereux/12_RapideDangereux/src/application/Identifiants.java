package application;

public class Identifiants {
	private String nomUsager;
	private String motPasse;
	
	public Identifiants(String nomUsager, String motPasse) {
		this.nomUsager = nomUsager;
		this.motPasse = motPasse;
	}
	
	public String toString() {
		return ("Identifiants= [nom usager: " +  nomUsager + " Mot de passe:" + motPasse + "]");
	}

}
