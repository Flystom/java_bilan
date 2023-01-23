package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

import entite.Chapitre;

public class ConnectBDD {
	Connection cn = null;
	private String URL = "jdbc:derby:java_bilan;create=true";
	private String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private String LOGIN = "";
	private String PWD = "";
	
	public ConnectBDD() {
		try {
			Class.forName(DRIVER);
			cn = DriverManager.getConnection(URL, LOGIN, PWD);
			System.out.println("Connexion à la base de données (insertion)");
			Statement st = cn.createStatement();
//			st.execute("drop table chapitre");
//			st.execute("create table chapitre (id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
//					+ "categorie int, libelle varchar(255), budget int, montant_realise int)");
//			st.executeUpdate("INSERT INTO chapitre (categorie, libelle, budget,montant_realise) VALUES (1, 'Charges à caractère général', 1139950 ,0)");
//			st.executeUpdate("INSERT INTO chapitre (categorie, libelle, budget,montant_realise) VALUES (2, 'Test test', 2222223 ,0)");
				
		}catch (ClassNotFoundException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}

			catch (SQLException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		}
	
		public List<Chapitre> resultatDepense() {
			try {
				System.out.println("Connection à la base de données pour les dépenses (récupération)");
				Statement st = cn.createStatement();
				ResultSet csDepense = st.executeQuery("SELECT * FROM chapitre WHERE categorie = 1");
				
				List<Chapitre> listDep = new ArrayList<>();
				while(csDepense.next()) {
					Chapitre chapitre = new Chapitre(csDepense.getInt("id"), csDepense.getInt("categorie"), csDepense.getString("libelle"), csDepense.getInt("budget"),csDepense.getInt("montant_realise"));
					listDep.add(chapitre);
				}
				return listDep;
					
			}
				catch (SQLException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
			return null;
			
		}
		
		public double totalBudgetDepense(List<Chapitre> listChapitres){
			double totalbudgetdep = 0;
			for (Chapitre dep : listChapitres) {
				totalbudgetdep += dep.getBudget();
				
				System.out.println(dep);
	        }
			return totalbudgetdep;
		}
		
		public double totalMrealDepense(List<Chapitre> listChapitres){
			double totalMrealDep = 0;
			for (Chapitre dep : listChapitres) {
				totalMrealDep += dep.getMontant_realise();
				
				System.out.println(dep);
	        }
			return totalMrealDep;
		}
		
		public List<Chapitre> resultatRecette() {
			try {
				System.out.println("Connection à la base de données pour les recettes (récupération)");
				Statement st = cn.createStatement();
				ResultSet csRecette = st.executeQuery("SELECT * FROM chapitre WHERE categorie = 2");
				
				List<Chapitre> listRec = new ArrayList<>();
				while(csRecette.next()) {
					Chapitre chapitre = new Chapitre(csRecette.getInt("id"), csRecette.getInt("categorie"), csRecette.getString("libelle"), csRecette.getInt("budget"),csRecette.getInt("montant_realise"));
					listRec.add(chapitre);
				}
				return listRec;
					
			}
				catch (SQLException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
			return null;
			
		}
		
		public double totalBudgetRec(List<Chapitre> listChapitres){
			double totalbudgetRec = 0;
			for (Chapitre rec : listChapitres) {
				totalbudgetRec += rec.getBudget();
				
				System.out.println(rec);
	        }
			return totalbudgetRec;
		}
		
		public double totalMrealRec(List<Chapitre> listChapitres){
			double totalMrealRec = 0;
			for (Chapitre rec : listChapitres) {
				totalMrealRec += rec.getMontant_realise();
				
				System.out.println(rec);
	        }
			return totalMrealRec;
		}
		
public void ajoutChapitre(String nomChapitre, int budgetChapitre, int categorie) throws SQLException {
        
            
            Statement st = cn.createStatement();            
            String insertSQL = "INSERT INTO chapitre (categorie, libelle, budget, montant_realise) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStmt = this.cn.prepareStatement(insertSQL);
           
            insertStmt.setInt(1, categorie);
            insertStmt.setString(2, nomChapitre);
            insertStmt.setInt(3, budgetChapitre);
            insertStmt.setInt(4, 0);
            insertStmt.executeUpdate();
            insertStmt.close();
            
        }
	}