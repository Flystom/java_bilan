package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ChapitreBDD;

public class ChapitreController {

	private ChapitreBDD chapitreBDD = new ChapitreBDD();
	
	@FXML
	private TextField txtNomChapitre;
	@FXML
	private TextField txtBudgetChapitre;
	@FXML
	private RadioButton radioButtonChapitreDepense;	
	@FXML
	private RadioButton radioButtonChapitreRecette;
	
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
		public void switchPageChapitre(ActionEvent event) throws IOException{
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
	

	public void BouttonValiderChapitre() throws SQLException {

        try {
            int categorie=0;
            String nomChapitre = txtNomChapitre.getText();
            int budgetChapitre = 0;
            budgetChapitre = Integer.parseInt(txtBudgetChapitre.getText());
            
            if(radioButtonChapitreDepense.isSelected() || radioButtonChapitreRecette.isSelected()) {
             if(radioButtonChapitreDepense.isSelected()){
                    categorie=1;

                }
                else if(radioButtonChapitreRecette.isSelected()){
                    categorie=2;
                }
            }else {
            	Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Erreur sur la catégorie");

        		// Header Text: null
        		alert.setHeaderText(null);
        		alert.setContentText("Veuillez sélectionnez une catégorie.");

        		alert.showAndWait();
            }
             
             chapitreBDD.ajoutChapitre(nomChapitre,budgetChapitre,categorie);
        }
        catch (NumberFormatException  e){
            System.out.println(e);
            Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erreur sur le montant");

    		// Header Text: null
    		alert.setHeaderText(null);
    		alert.setContentText("Le montant est incorrect.");

    		alert.showAndWait();
        }
    }
}


