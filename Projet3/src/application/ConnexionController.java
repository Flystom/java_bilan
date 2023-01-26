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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import service.ChapitreBDD;
import service.ConnexionService;

public class ConnexionController {

    private ConnexionService connexionService = new ConnexionService();


    @FXML private TextField txtNomUtilisateur;

    @FXML private PasswordField txtMdpUtilisateur;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchPageBilan(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/vues/vueBilan.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void connexion(ActionEvent event) throws SQLException, IOException {
        String nom = txtNomUtilisateur.getText();
        String mdp = txtMdpUtilisateur.getText();
        boolean verif = connexionService.verificationConnexion(nom,mdp);
        if(verif) {
            switchPageBilan(event);
        }
        else {
            Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erreur connexion");

    		// Header Text: null
    		alert.setHeaderText(null);
    		alert.setContentText("Identifiant ou mot de passe incorrect.");

    		alert.showAndWait();
        }
    }
}