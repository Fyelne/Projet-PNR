package View;

import Controller.*;
import java.io.IOException;


import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class App extends Application{


    public void start(Stage primaryStage) {
        Parent root;
        
        try {
            // Création de la 1ère frame, celle d'accueil
            // System.out.println(getClass().getResource("/frame").toString());
            root = FXMLLoader.load(getClass().getResource("/frame/Loading.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PNR");
            primaryStage.setMaximized(true);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.centerOnScreen();
            Utilitaire.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }  
        
        
        
    }
 
 public static void main(String[] args) {
        launch(args);
    }
}
