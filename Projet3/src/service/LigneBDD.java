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
import entite.Ligne;

public class LigneBDD {
	Connection cn = null;
	private String URL = "jdbc:derby:java_bilan;create=true";
	private String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private String LOGIN = "";
	private String PWD = "";
	
	public LigneBDD() {
		try {
			Class.forName(DRIVER);
			cn = DriverManager.getConnection(URL, LOGIN, PWD);
			System.out.println("Connexion à la base de données (insertion)");
			Statement st = cn.createStatement();
//			st.execute("drop table ligne");
//			st.execute("create table ligne (id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
//					+ "nom_ligne varchar(255), id_chapitre int, montant DOUBLE PRECISION)");
//			

				
		}catch (ClassNotFoundException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}

			catch (SQLException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		}
	
		//ListRec
		public List<Ligne> resultatLigne(int selectedChap) {
			try {
				System.out.println("Connection à la base de données pour les lignes (récupération)");
				Statement st = cn.createStatement();
				ResultSet csLigne = st.executeQuery("SELECT * FROM ligne WHERE idChapitre = " + selectedChap);
				
				List<Ligne> listLigne = new ArrayList<>();
				while(csLigne.next()) {
					Ligne ligne = new Ligne(csLigne.getInt("id"), csLigne.getString("nomLigne"), csLigne.getInt("idChapitre"), csLigne.getDouble("montant"));
					listLigne.add(ligne);
				}
				return listLigne;
					
			}
				catch (SQLException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
			return null;
			
		}
		
		public List<Ligne> touteLigne() {
			try {
				Statement st = cn.createStatement();
				ResultSet csLigne = st.executeQuery("SELECT * FROM ligne");
				
				List<Ligne> listLigne = new ArrayList<>();
				while(csLigne.next()) {
					Ligne ligne = new Ligne(csLigne.getInt("id"), csLigne.getString("nomLigne"), csLigne.getInt("idChapitre"), csLigne.getDouble("montant"));
					listLigne.add(ligne);
				}
				return listLigne;
					
			}
				catch (SQLException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
			return null;
			
		}
		
	public void ajoutLigne(String nomL, int idChapitre, Double montant) throws SQLException {
			
		    Statement st = cn.createStatement();   
		    String insertSQL = "INSERT INTO ligne (nomLigne, idChapitre, montant) VALUES (?, ?, ?)";
		    PreparedStatement insertStmt = this.cn.prepareStatement(insertSQL);
		    
		    insertStmt.setString(1, nomL);
            insertStmt.setInt(2, idChapitre);
            insertStmt.setDouble(3, montant);

            insertStmt.executeUpdate();
		    insertStmt.close();
		}
		
		
		public List<Ligne> modifierLigne(int id, String nomLigne, int idChapitre, Double montant, Double montantPrecedent) {
			try {
				String operation = "";
				System.out.println(montantPrecedent);
				if(montant > montantPrecedent) {
					operation = "+";
				} else {
					operation = "-";
				}
				String requete = "UPDATE ligne SET nomLigne = '" + nomLigne + "', idChapitre = " + idChapitre + ", montant = " + montant + " WHERE id = " + id;
				String updateMontantRealiser = "UPDATE chapitre SET montantRealise = montantRealise " + operation + " " + montant + " WHERE id = " + idChapitre;
				PreparedStatement updateTableStat = this.cn.prepareStatement(updateMontantRealiser);
				PreparedStatement createTableStat = this.cn.prepareStatement(requete);
				updateTableStat.executeUpdate();
				createTableStat.executeUpdate();	
			}
				catch (SQLException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
			return null;
			
		}

		public List<Ligne> supprimerLigne(int idLigne, int idChapitre, double montant) {
			try {
				System.out.println("supression");
				String requete = "DELETE FROM ligne WHERE id = " + idLigne;
				String updateSQL = "UPDATE chapitre SET montantRealise = montantRealise - " + montant + " WHERE id = " + idChapitre;
				PreparedStatement createTableStat = this.cn.prepareStatement(requete);
				PreparedStatement modifyTableStat = this.cn.prepareStatement(updateSQL);
				createTableStat.executeUpdate();	
				modifyTableStat.executeUpdate();	
			}
				catch (SQLException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
			return null;
			
		}
	

			
		
	}