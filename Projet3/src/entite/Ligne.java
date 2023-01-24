package entite;

public class Ligne {
	private int id;
	private String nom_ligne;
	private int id_chapitre;
	private double montant;

	public Ligne(int id, String nom_ligne, int id_chapitre, double montant) {
		super();
		this.id = id;
		this.nom_ligne = nom_ligne;
		this.id_chapitre = id_chapitre;
		this.montant = montant;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom_ligne() {
		return nom_ligne;
	}
	public void setNom_ligne(String nom_ligne) {
		this.nom_ligne = nom_ligne;
	}
	public int getId_chapitre() {
		return id_chapitre;
	}
	public void setId_chapitre(int id_chapitre) {
		this.id_chapitre = id_chapitre;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

	
	
}
