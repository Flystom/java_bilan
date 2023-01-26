package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import entite.Chapitre;
import entite.Ligne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ChapitreBDD;
import service.LigneBDD;

public class LigneModifController implements Initializable {
	
	private ChapitreBDD chapitreBDD = new ChapitreBDD();
	private LigneBDD ligneBDD = new LigneBDD();
	
	@FXML
	private RadioButton radioButtonChapitreDepense;	
	@FXML
	private RadioButton radioButtonChapitreRecette;
	
	@FXML
	private TableColumn<Ligne, String> nomLigne;
	@FXML
	private TableColumn<Ligne, Integer> idChapitre;
	@FXML
	private TableColumn<Ligne, String> montant;
	
	@FXML
	private TableColumn<Chapitre, String> ChapNom;

    @FXML
    private TableView<Ligne> tableauLigne;
    
    //TABLEAU CHAPITRE
  	@FXML
  	private TableView<Chapitre> tableau;
  	
  	@FXML
  	private TextField ModifNomLigne;
  	@FXML
  	private TextField ModifIdChap;
  	@FXML
  	private TextField ModifMontantLigne;
  	
  	private ObservableList<Chapitre> chapitres;
    private ObservableList<Ligne> lignes;
    
	private Stage stage;
	private Scene scene;
	private Parent root;

	
	//Changement de page
		public void switchPage1(ActionEvent event) throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("/vues/vueBilan.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		public void switchPage2(ActionEvent event) throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("/vues/vueChapitre.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		public void switchPage3(ActionEvent event) throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("/vues/vueLigne.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		public void afficheDepense() throws SQLException { 
			List<Chapitre> listChapitres = chapitreBDD.resultatDepense();
						
			ChapNom.setCellValueFactory(new PropertyValueFactory<>("titre")); 
	        
	        chapitres = FXCollections.observableArrayList(listChapitres);
	        tableau.setItems(chapitres);
	        
		}
		
		public void afficheRecette() throws SQLException { 
			List<Chapitre> listChapitres = chapitreBDD.resultatRecette();
			
			ChapNom.setCellValueFactory(new PropertyValueFactory<>("titre"));
			 
	        chapitres = FXCollections.observableArrayList(listChapitres);
	        tableau.setItems(chapitres);
		}
		
		public void afficheLignes() throws SQLException { 
			int selectedChap = tableau.getSelectionModel().getSelectedItem().getId();
			List<Ligne> listLigne = ligneBDD.resultatLigne(selectedChap);
			
			nomLigne.setCellValueFactory(new PropertyValueFactory<>("nomLigne"));
			idChapitre.setCellValueFactory(new PropertyValueFactory<>("idChapitre"));
			montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
	        
	        lignes = FXCollections.observableArrayList(listLigne);
	        tableauLigne.setItems(lignes);
		}
		
		public void supprimerLignes() throws SQLException { 
			int idLigne = tableauLigne.getSelectionModel().getSelectedItem().getId();
			int selectedChap = tableauLigne.getSelectionModel().getSelectedItem().getIdChapitre();
			double Montant = tableauLigne.getSelectionModel().getSelectedItem().getMontant();
			List<Ligne> listLigne = ligneBDD.supprimerLigne(idLigne,selectedChap,Montant);
		
			afficheLignes();
			
		}
		
		public void afficheModification() throws SQLException { 
			this.ModifNomLigne.setText(tableauLigne.getSelectionModel().getSelectedItem().getNomLigne());
			this.ModifIdChap.setText(String.valueOf(tableauLigne.getSelectionModel().getSelectedItem().getIdChapitre()));
			this.ModifMontantLigne.setText(String.valueOf(tableauLigne.getSelectionModel().getSelectedItem().getMontant()));
		}
		
		public void Modifier() throws SQLException { 
			int idLigne = tableauLigne.getSelectionModel().getSelectedItem().getId();
			int selectedChap = Integer.parseInt(ModifIdChap.getText());
			double Montant = Double.parseDouble(ModifMontantLigne.getText());
			double montantPrecedent = tableauLigne.getSelectionModel().getSelectedItem().getMontant();
			
			List<Ligne> listLigne = ligneBDD.modifierLigne(idLigne, ModifNomLigne.getText(), Integer.parseInt(ModifIdChap.getText()), Double.parseDouble(ModifMontantLigne.getText()),montantPrecedent);
			
			chapitreBDD.updateMontantChapitre(selectedChap,Montant);
			afficheLignes();
			ModifNomLigne.setText("");
			ModifIdChap.setText("");
			ModifMontantLigne.setText("");
			
			
		}
		
		public void exportCSV() throws SQLException, IOException {
			List<Ligne> listLigne = ligneBDD.touteLigne();
			
            try (FileWriter file = new FileWriter("Ligne.csv")){
            	
            	String myLine = "";
                for (Ligne ligne : listLigne) {
                    myLine += ligne.getNomLigne()+";"+ligne.getIdChapitre()+";"+ligne.getMontant()+"\n";
                    
                   }
                file.write(myLine);
                Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Fichier exporté");

        		// Header Text: null
        		alert.setHeaderText(null);
        		alert.setContentText("Toutes les lignes ont été exportés avec succès.");

        		alert.showAndWait();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InputMismatchException e) {
                e.printStackTrace();
            }  
	    }
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			ligneBDD = new LigneBDD();
		}		
	
}
