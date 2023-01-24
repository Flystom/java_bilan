package entite;

public class Chapitre {

	private int id;
	//categorie : 
	//Dépenses = 1 
	//Recettes = 2
	private int categorie;
	private String libelle;
	private String titre;
	private int budget;
	private int montantRealise;
	

	
	public Chapitre(int id, int categorie, String libelle, int budget, int montantRealise) {
	    this.id = id;
	    this.titre = id + " " + libelle;
	    this.categorie = categorie;
		this.libelle = libelle;
		this.budget = budget;
		this.montantRealise = montantRealise;
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


	public int getMontantRealise() {
		return montantRealise;
	}


	public void setMontantRealise(int montantRealise) {
		this.montantRealise = montantRealise;
	}
	
	
}
