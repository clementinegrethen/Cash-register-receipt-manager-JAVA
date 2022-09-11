
public class Magasin {
	
	private String adresse;
	
	private String nom;
	
	public Magasin (String nom, String adresse) {
		this.adresse=adresse;
		this.nom=nom;
		
	}
	
	public String getNom() {
		return this.nom;
		
	}
	
	public String getAdresse() {
		return this.adresse;
	}
	
	public String toString() {
		return (this.nom+":  "+this.adresse);
	}
}
