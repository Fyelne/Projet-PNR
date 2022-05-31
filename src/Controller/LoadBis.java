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
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class LoadBis {

    @FXML
    private Label bonjour;

    private String name;

    @FXML
    void change(MouseEvent event) {
        try {
            for(int i = 0; i < 50 ; i ++){
                Thread.sleep(10);
            }
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BorderPane bt = (BorderPane) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "..//View//frame//Accueil.fxml";
        try {
            // change the scene
            FXMLLoader l = new FXMLLoader(getClass().getResource(url));
            root = l.load();
            ListenerAccueil controle = l.getController();
            controle.setNom(this.name);
            sc.setRoot(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    

    public void myFonction(boolean connecter, String name){
        if(connecter && name != null){
            this.ecranCon(name);
            this.name = name;
        }

    }

    private void ecranCon(String name){
        bonjour.setText("Bonjour Monsieur " + name);
        bonjour.setVisible(true);
    }

    
}
