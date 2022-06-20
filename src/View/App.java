package View;

import Controller.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.stage.Stage;

public class App extends Application{

    private Stage primaryStage;

    public void start(Stage primaryStage) {
        Parent root;
        
        try {
            // Création de la 1ère frame, celle d'accueil
            root = FXMLLoader.load(getClass().getResource("frame\\Loading.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Connexion");
            primaryStage.setMaximized(true);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.centerOnScreen();
            this.primaryStage = primaryStage;
            Utilitaire.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }  
        
        
        
    }
 
 public static void main(String[] args) {
        launch(args);
    }
}
