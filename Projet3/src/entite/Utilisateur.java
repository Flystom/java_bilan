package entite;

public class Utilisateur {

	private int id;
	private String nom;
	private String mdp;

	public Utilisateur(int id, String nom, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.mdp = mdp;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
