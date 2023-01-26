package entite;

public class Ligne {
	
	private int id;
	private String nomLigne;
	private int idChapitre;
	private double montant;

	public Ligne(int id, String nomLigne, int idChapitre, double montant) {
		super();
		this.id = id;
		this.nomLigne = nomLigne;
		this.idChapitre = idChapitre;
		this.montant = montant;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomLigne() {
		return nomLigne;
	}
	public void setNomLigne(String nomLigne) {
		this.nomLigne = nomLigne;
	}
	public int getIdChapitre() {
		return idChapitre;
	}
	public void setIdChapitre(int idChapitre) {
		this.idChapitre = idChapitre;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

	
	
}
