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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ChapitreBDD;
import service.LigneBDD;

public class LineModifController implements Initializable {
	
	private LigneBDD ligneBDD;
	
	//DÉPENSES
	@FXML
	private TableColumn<Ligne, String> nom_ligne;
	@FXML
	private TableColumn<Ligne, Integer> idchap;
	@FXML
	private TableColumn<Ligne, String> montant;
	
    @FXML
    private TableView<Ligne> tableau_ligne;
    
	
    private ObservableList<Ligne> lignes;
    
	private Stage stage;
	private Scene scene;
	private Parent root;

	
	//Changement de page
		public void switchPage1(ActionEvent event) throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("vueProjet3.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		public void switchPage2(ActionEvent event) throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("vueChapitre.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		public void switchPage3(ActionEvent event) throws IOException{
			Parent root = FXMLLoader.load(getClass().getResource("vueLigne.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		public void afficheLignes() throws SQLException { 
			List<Ligne> listLigne = ligneBDD.resultatLigne();
			System.out.println(listLigne);
			this.nom_ligne.setCellValueFactory(new PropertyValueFactory<>("nom_ligne"));
			this.idchap.setCellValueFactory(new PropertyValueFactory<>("id_chapitre"));
			this.montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
	        
	        lignes = FXCollections.observableArrayList(listLigne);
	        tableau_ligne.setItems(lignes);
		}
		
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			ligneBDD = new LigneBDD();
			
			try {
				afficheLignes();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}		
	
}
