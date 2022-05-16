package View;
import Controller.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Maquette extends Application{

    private Stage primaryStage;

    public void start(Stage primaryStage) {
        Parent root;
        
        try {
            // Création de la 1ère frame, celle d'accueil
            root = FXMLLoader.load(getClass().getResource("frame\\Debut.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Connexion");
            primaryStage.setWidth(600);
            primaryStage.setHeight(435);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.centerOnScreen();
            this.primaryStage = primaryStage;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }


 
 public static void main(String[] args) {
        launch(args);
    }
}
