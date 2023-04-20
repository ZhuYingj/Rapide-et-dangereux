package application;

public class InfoLigne {

	private String nom;
    private double temps;
    private String piste;

    public InfoLigne(String nom, double temps, String piste) {
        this.nom = nom;
        this.temps = temps;
        this.piste = piste;
    }

    public String getNom() {
        return nom;
    }

    public double getTemps() {
        return temps;
    }

    public String getPiste() {
        return piste;
    }

    public String toString() {
        return nom + ";" + temps + ";" + piste;
    }
	
}
