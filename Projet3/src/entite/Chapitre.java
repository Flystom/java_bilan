package entite;

public class Chapitre {

	private int id;
	//categorie: 1 = Dépenses / 2 = Recettes
	private int categorie;
	private String libelle;
	private String titre;
	private int budget;
	private int montant_realise;
	

	
	public Chapitre(int id, int categorie, String libelle, int budget, int montant_realise) {
	    this.id = id;
	    this.titre = id + " " + libelle;
	    this.categorie = categorie;
		this.libelle = libelle;
		this.budget = budget;
		this.montant_realise = montant_realise;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public int getCategorie() {
		return categorie;
	}


	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public int getBudget() {
		return budget;
	}


	public void setBudget(int budget) {
		this.budget = budget;
	}


	public int getMontant_realise() {
		return montant_realise;
	}


	public void setMontant_realise(int montant_realise) {
		this.montant_realise = montant_realise;
	}
	
	
}
