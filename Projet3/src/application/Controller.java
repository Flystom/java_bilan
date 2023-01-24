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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ChapitreBDD;

public class Controller implements Initializable{

	private ChapitreBDD chapitreBDD;
	
	//DÉPENSES
	@FXML
    private TableColumn<Chapitre, String> nomChapitreDepense;
    @FXML
    private TableColumn<Chapitre, String> budgetChapitreDepense;
    @FXML
    private TableColumn<Chapitre, String> montantRealiseChapitreDepense;
    
    //RECETTES
    @FXML
    private TableColumn<Chapitre, String> nomChapitreRecette;
    @FXML
    private TableColumn<Chapitre, String> budgetChapitreRecette;
    @FXML
    private TableColumn<Chapitre, String> montantRealiseChapitreRecette;
    
    //TABLEAU CHAPITRE
    @FXML
    private TableView<Chapitre> tableViewDepense;
    @FXML
    private TableView<Chapitre> tableViewRecette;
    
    //AFFICHE TOTAL
    @FXML
	private Label totalBudgetDepense;
    @FXML
	private Label totalMontantRealiseDepense;
    @FXML
	private Label totalBudgetRecette;
    @FXML
	private Label totalMontantRealiseRecette;
    
    private ObservableList<Chapitre> chapitres;
		private Stage stage;
		private Scene scene;
		//Changement de page
	public void switchPage1(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("vueBilan.fxml"));
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
		List<Chapitre> listChapitres = chapitreBDD.resultatDepense();
		
		nomChapitreDepense.setCellValueFactory(new PropertyValueFactory<>("titre"));
		budgetChapitreDepense.setCellValueFactory(new PropertyValueFactory<>("budget"));
		montantRealiseChapitreDepense.setCellValueFactory(new PropertyValueFactory<>("montantRealise"));     
        
        chapitres = FXCollections.observableArrayList(listChapitres);
        tableViewDepense.setItems(chapitres);
        
        double totalbudgetdep = chapitreBDD.totalBudgetDepense(listChapitres);
        totalBudgetDepense.setText(String.valueOf(totalbudgetdep));
        
        double totalMrealDep = chapitreBDD.totalMontantRealiseDepense(listChapitres);
        totalMontantRealiseDepense.setText(String.valueOf(totalMrealDep));
	}
	
	public void afficheRecette() throws SQLException { 
		List<Chapitre> listChapitres = chapitreBDD.resultatRecette();
		
		nomChapitreRecette.setCellValueFactory(new PropertyValueFactory<>("titre"));
		budgetChapitreRecette.setCellValueFactory(new PropertyValueFactory<>("budget"));
		montantRealiseChapitreRecette.setCellValueFactory(new PropertyValueFactory<>("montantRealise"));
        
        chapitres = FXCollections.observableArrayList(listChapitres);
        tableViewRecette.setItems(chapitres);
        
        double totalbudgetRec = chapitreBDD.totalBudgetRec(listChapitres);
        totalBudgetRecette.setText(String.valueOf(totalbudgetRec));
        
        double totalMrealRec = chapitreBDD.totalMrealRec(listChapitres);
        totalMontantRealiseRecette.setText(String.valueOf(totalMrealRec));
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chapitreBDD = new ChapitreBDD();
		
		try {
			this.afficheDepense();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.afficheRecette();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
