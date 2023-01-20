package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ConnectBDD;

public class ChapterController {

	private ConnectBDD connectBDD;
	
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
}


