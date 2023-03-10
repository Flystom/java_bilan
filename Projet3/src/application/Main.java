package application;
    
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent rootFXML = FXMLLoader.load(getClass().getResource("/vues/vueConnexion.fxml"));
            Scene sceneFXML = new Scene(rootFXML);
            primaryStage.setScene(sceneFXML);
            primaryStage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    

} 