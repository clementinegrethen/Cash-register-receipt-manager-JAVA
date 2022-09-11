public class Utilisateur {
	enum Sexe {M,F};
	public static final int nombres_tel = 5;
	
	private String nom;
	private String prenom;
	
	private String pseudo; /*Unique pour chaque utilisateur*/
	private String mdp;
	private Sexe sexe;
	private String ville;
	private String magasin_favori;
	private String mail;
	private String[] numero;
	
	public Utilisateur(String Nom, String Prenom, String Pseudo, String Mdp, Sexe sexe, String Ville, String Magasin_favori, String Mail, String[] Numero) {
		this.nom = Nom;
		
		this.prenom = Prenom;
		this.pseudo = Pseudo;
		this.mdp = Mdp;
		this.sexe = sexe;
		this.ville = Ville;
		this.magasin_favori = Magasin_favori;
		this.mail = Mail;
		this.numero = Numero;
	}
	
	public String getNom() {
		return (this.nom);
	}
	
	public String getPrenom() {
		return (this.prenom);
	}
	

	
	public String getPseudo() {
		return (this.pseudo);
	}
	
	public String getMdp() {
		return (this.mdp);
	}
		
	public Sexe getSexe() {
		return (this.sexe);
	}
	
	public String getVille() {
		return (this.ville);
	}
	
	public String getMagasin() {
		return (this.magasin_favori);
	}
	
	public String getMail() {
		return (this.mail);
	}
	
	public String getNumero() {
		String chaine = "";
		int nombre = this.numero.length;
		for (int i = 0; i < nombre; i++) {
			chaine += this.numero[i] + ".";
		}
		chaine = chaine.replaceFirst(".$", "");
		System.out.print(chaine);
		return(chaine);
	}
	
	
	public static void main (String[] args) {
		String[] num1 = new String[nombres_tel];
		num1[0]= "07";
		num1[1]= "83";
		num1[2]= "58";
		num1[3]= "49";
		num1[4]= "40";
		Utilisateur user = new Utilisateur("Nial", "Jerome", "JleSang", "ok31", Sexe.M, "Toulouse", "Carrefour", "jerome@gmail.com", num1);
		System.out.println(user.getNom());
		System.out.println(user.getPrenom());
		
		System.out.println(user.getPseudo());
		System.out.println(user.getMdp());
		System.out.println(user.getSexe());
		System.out.println(user.getVille());
		System.out.println(user.getMagasin());
		System.out.println(user.getMail());
		user.getNumero();
	}
}
