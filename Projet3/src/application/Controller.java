package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONException;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ChapitreBDD;

public class Controller implements Initializable{

	private ChapitreBDD chapitreBDD;
	
	//DÉPENSES
	@FXML
    private TableColumn<Chapitre, String> Chap_dep;
    @FXML
    private TableColumn<Chapitre, String> Bud_dep;
    @FXML
    private TableColumn<Chapitre, String> Real_dep;
    
    //RECETTES
    @FXML
    private TableColumn<Chapitre, String> Chap_rec;
    @FXML
    private TableColumn<Chapitre, String> Bud_rec;
    @FXML
    private TableColumn<Chapitre, String> Real_rec;
    
    //TABLEAU CHAPITRE
    @FXML
    private TableView<Chapitre> tb_dep;
    @FXML
    private TableView<Chapitre> tb_rec;
    
    //AFFICHE TOTAL
    @FXML
	private Label TBudget_dep;
    @FXML
	private Label TReal_dep;
    @FXML
	private Label TBudget_rec;
    @FXML
	private Label TReal_rec;
    
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
		List<Chapitre> listChapitres = chapitreBDD.resultatDepense();
		
		Chap_dep.setCellValueFactory(new PropertyValueFactory<>("titre"));
		Bud_dep.setCellValueFactory(new PropertyValueFactory<>("budget"));
        Real_dep.setCellValueFactory(new PropertyValueFactory<>("montant_realise"));     
        
        chapitres = FXCollections.observableArrayList(listChapitres);
        tb_dep.setItems(chapitres);
        
        double totalbudgetdep = chapitreBDD.totalBudgetDepense(listChapitres);
        TBudget_dep.setText(String.valueOf(totalbudgetdep));
        
        double totalMrealDep = chapitreBDD.totalMrealDepense(listChapitres);
        TReal_dep.setText(String.valueOf(totalMrealDep));
	}
	
	public void afficheRecette() throws SQLException { 
		List<Chapitre> listChapitres = chapitreBDD.resultatRecette();
		
		Chap_rec.setCellValueFactory(new PropertyValueFactory<>("titre"));
		Bud_rec.setCellValueFactory(new PropertyValueFactory<>("budget"));
        Real_rec.setCellValueFactory(new PropertyValueFactory<>("montant_realise"));
        
        chapitres = FXCollections.observableArrayList(listChapitres);
        tb_rec.setItems(chapitres);
        
        double totalbudgetRec = chapitreBDD.totalBudgetRec(listChapitres);
        TBudget_rec.setText(String.valueOf(totalbudgetRec));
        
        double totalMrealRec = chapitreBDD.totalMrealRec(listChapitres);
        TReal_rec.setText(String.valueOf(totalMrealRec));
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
