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

public class LoadBis {

    @FXML
    private Label bonjour;

    private String name;
    private Utilitaire util = new Utilitaire();

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
        
        String url = "..//View//frame//Accueil.fxml";
        util.changeScene("Accueil");

        
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
