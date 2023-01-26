package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import entite.Chapitre;
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

public class LigneController {

	private ChapitreBDD chapitreBDD = new ChapitreBDD();
	private LigneBDD ligneBDD = new LigneBDD();
	
	@FXML
	private RadioButton radioButtonChapitreDepense;	
	@FXML
	private RadioButton radioButtonChapitreRecette;
	@FXML
	private TextField txtNomLigne;

	@FXML
	private TableColumn<Chapitre, String> ChapNom;
	@FXML
	private TableColumn<Chapitre, String> Budget;
	
	//TABLEAU CHAPITRE
	@FXML
	private TableView<Chapitre> tableau;
	
	@FXML
	private TextField montantEcriture;
	
	@FXML
	private Button valider;
	
	@FXML
	private Button CSV;
	
	private ObservableList<Chapitre> chapitres;
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
		public void switchPage4(ActionEvent event) throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("/vues/vueLigneModif.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	
		public void afficheDepense() throws SQLException { 
			List<Chapitre> listChapitres = chapitreBDD.resultatDepense();
						
			ChapNom.setCellValueFactory(new PropertyValueFactory<>("titre"));
			Budget.setCellValueFactory(new PropertyValueFactory<>("budget")); 
	        
	        chapitres = FXCollections.observableArrayList(listChapitres);
	        tableau.setItems(chapitres);
	        
		}
		
		public void afficheRecette() throws SQLException { 
			List<Chapitre> listChapitres = chapitreBDD.resultatRecette();
			
			ChapNom.setCellValueFactory(new PropertyValueFactory<>("titre"));
			Budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
	        
	        chapitres = FXCollections.observableArrayList(listChapitres);
	        tableau.setItems(chapitres);
		}
		
		public void BouttonValiderLigne() throws SQLException {

	        try {
	            String nomLigne = txtNomLigne.getText();
	            double Montant = Float.parseFloat(montantEcriture.getText());
	            int selectedChap = tableau.getSelectionModel().getSelectedItem().getId();
	            
	            if(nomLigne != "") {
	                        
	            Alert alert = new Alert(AlertType.INFORMATION);
	     		alert.setTitle("Validé");
	
	     		// Header Text: null
	     		alert.setHeaderText(null);
	     		alert.setContentText("Ligne ajouté.");
	
	     		alert.showAndWait();
	     		ligneBDD.ajoutLigne(nomLigne, selectedChap, Montant);
	            chapitreBDD.updateMontantChapitre(selectedChap,Montant);
	            }else {
             		Alert alert = new Alert(AlertType.INFORMATION);             		
    	     		alert.setTitle("Erreur sur le nommage");
    	
    	     		// Header Text: null
    	     		alert.setHeaderText(null);
    	     		alert.setContentText("Veuillez nommer la ligne.");
    	
    	     		alert.showAndWait();	
             	}
	        }
	        catch (NullPointerException  e){
	            System.out.println(e);
	            Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Erreur sur le chapitre");

	    		// Header Text: null
	    		alert.setHeaderText(null);
	    		alert.setContentText("Veuillez sélectionnez un chapitre.");

	    		alert.showAndWait();
	        }catch (NumberFormatException  e){
	            System.out.println(e);
	            Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Erreur sur le montant");

	    		// Header Text: null
	    		alert.setHeaderText(null);
	    		alert.setContentText("Le montant est incorrect.");

	    		alert.showAndWait();
	    		
	        }
	    }
		
		public void importCSV() throws SQLException {
	        // Check if Desktop is supported by Platform or not
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + filePath);
           
            int chapitre = 0;
            String nomLigne = "";
            double montant = 0.0;

            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNextLine()) {
                	String line = scanner.nextLine();
                	String[] value = line.split(";");
                    chapitre = Integer.parseInt(value[0]);
                    nomLigne = value[1];
                    montant = Double.parseDouble(value[2]);
                    
                    System.out.println(chapitre + " / " + nomLigne + " / " + montant);
                    chapitreBDD.updateMontantChapitre(chapitre,montant);
                    ligneBDD.ajoutLigne(nomLigne, chapitre, montant);
                    
                    Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Fichier importé");

            		// Header Text: null
            		alert.setHeaderText(null);
            		alert.setContentText("Ligne importé avec succès.");

            		alert.showAndWait();
                }
            } catch (FileNotFoundException e) {
            	System.out.println(e);
                Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Erreur fichier");

        		// Header Text: null
        		alert.setHeaderText(null);
        		alert.setContentText("Fichier introuvable.");

        		alert.showAndWait();
            } catch (InputMismatchException e) {
            	System.out.println(e);
                Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Erreur fichier");

        		// Header Text: null
        		alert.setHeaderText(null);
        		alert.setContentText("Fichier incompatible veuillez vérifier le contenue.");

        		alert.showAndWait();
            } 
            catch (NumberFormatException e) {
            	System.out.println(e);
                Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Erreur fichier");

        		// Header Text: null
        		alert.setHeaderText(null);
        		alert.setContentText("Fichier incompatible veuillez vérifier le contenue.");

        		alert.showAndWait();
            } 
	    }
}
