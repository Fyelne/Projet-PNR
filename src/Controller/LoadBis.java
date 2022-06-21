package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class LoadBis implements Initializable{

    @FXML
    private Label bonjour;

    private String name;
    private Utilitaire util = new Utilitaire();

    @FXML
    void change() {
        System.out.print("tru");
        try {
            for(int i = 0; i < 50 ; i ++){
                
                Thread.sleep(10);
                System.out.println("test");
            }
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        String url = "..//View//frame//Accueil.fxml";
        Scene sc = bonjour.getScene();
        Parent root;
        try {
            // change the scene
            root = FXMLLoader.load(getClass().getResource(url));
            sc.setRoot(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name = Utilitaire.getCurrentNameUser();
        bonjour.setText("Bonjour " + name);
        bonjour.setVisible(true);
        change();
        
    }

    
}
