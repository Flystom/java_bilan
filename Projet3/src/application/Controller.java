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
import service.ConnectBDD;

public class Controller implements Initializable{

	private ConnectBDD connectBDD;
	
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
    
    @FXML
    private TableView<Chapitre> tb_dep;
    @FXML
    private TableView<Chapitre> tb_rec;
    
    @FXML
	private Label TBudget_dep;
    @FXML
	private Label TReal_dep;
    @FXML
	private Label TBudget_rec;
    @FXML
	private Label TReal_rec;
	@FXML
	private TextField txtNomChapitre;
	@FXML
	private TextField txtBudgetChapitre;
	@FXML
	private Label labAffichage;
	@FXML
	private RadioButton radioButtonChapitreDepense;	
	@FXML
	private RadioButton radioButtonChapitreRecette;
    
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
		
		Chap_dep.setCellValueFactory(new PropertyValueFactory<>("titre"));
		Bud_dep.setCellValueFactory(new PropertyValueFactory<>("budget"));
        Real_dep.setCellValueFactory(new PropertyValueFactory<>("montant_realise"));     
        
        chapitres = FXCollections.observableArrayList(listChapitres);
        tb_dep.setItems(chapitres);
        
        double totalbudgetdep = connectBDD.totalBudgetDepense(listChapitres);
        TBudget_dep.setText(String.valueOf(totalbudgetdep));
        
        double totalMrealDep = connectBDD.totalBudgetDepense(listChapitres);
        TReal_dep.setText(String.valueOf(totalMrealDep));
	}
	
	public void afficheRecette() throws SQLException { 
		List<Chapitre> listChapitres = connectBDD.resultatRecette();
		System.out.println(listChapitres);
		
		Chap_rec.setCellValueFactory(new PropertyValueFactory<>("titre"));
		Bud_rec.setCellValueFactory(new PropertyValueFactory<>("budget"));
        Real_rec.setCellValueFactory(new PropertyValueFactory<>("montant_realise"));
        
        chapitres = FXCollections.observableArrayList(listChapitres);
        tb_rec.setItems(chapitres);
        
        double totalbudgetRec = connectBDD.totalBudgetDepense(listChapitres);
        TBudget_rec.setText(String.valueOf(totalbudgetRec));
        
        double totalMrealRec = connectBDD.totalBudgetDepense(listChapitres);
        TReal_rec.setText(String.valueOf(totalMrealRec));
	}
	
	public void creationChapitre() {
		
		 if(radioButtonChapitreDepense.isSelected()){
				System.out.println("depense");

	        }
	        else if(radioButtonChapitreRecette.isSelected()){
				System.out.println("recette");

	        }

		String nomChapitre = txtNomChapitre.getText();
		String budgetChapitre = txtBudgetChapitre.getText();
		System.out.println("nom : "+ nomChapitre +" montant : " + budgetChapitre);
		
		
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		connectBDD = new ConnectBDD();
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
