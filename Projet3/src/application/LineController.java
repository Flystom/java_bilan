package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ConnectBDD;

public class LineController {

	private ConnectBDD connectBDD = new ConnectBDD();
	
	@FXML
	private RadioButton radioButtonChapitreDepense;	
	@FXML
	private RadioButton radioButtonChapitreRecette;

	//DÉPENSES
	@FXML
	private TableColumn<Chapitre, String> Chap_nom;
	@FXML
	private TableColumn<Chapitre, String> Budget;
	
	//TABLEAU CHAPITRE
	@FXML
	private TableView<Chapitre> tableau;
	
	private ObservableList<Chapitre> chapitres;
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
	
		public void afficheDepense() throws SQLException { 
			List<Chapitre> listChapitres = connectBDD.resultatDepense();
			System.out.println(listChapitres);
			
			Chap_nom.setCellValueFactory(new PropertyValueFactory<>("titre"));
			Budget.setCellValueFactory(new PropertyValueFactory<>("budget")); 
	        
	        chapitres = FXCollections.observableArrayList(listChapitres);
	        tableau.setItems(chapitres);
	        
		}
		
		public void afficheRecette() throws SQLException { 
			List<Chapitre> listChapitres = connectBDD.resultatRecette();
			System.out.println(listChapitres);
			
			Chap_nom.setCellValueFactory(new PropertyValueFactory<>("titre"));
			Budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
	        
	        chapitres = FXCollections.observableArrayList(listChapitres);
	        tableau.setItems(chapitres);
		}
		
		public void recupChap() throws SQLException { 
			Chapitre selectedChap = tableau.getSelectionModel().getSelectedItem();
			System.out.println("Selected person: " + selectedChap);
		}
}
