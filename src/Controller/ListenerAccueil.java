package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ListenerAccueil {
    @FXML
    private AnchorPane Menu;

    @FXML
    private Button menu;

    @FXML
    private Button quit;

    @FXML
    private Button user;
    
    @FXML
    private Label bv;
    private String name;

    private Utilitaire util = new Utilitaire();

    //@FXML
    //private Label username;

    @FXML
    private Button test;

    @FXML
    void changeTest(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "..//View//frame//ChoixConsultation.fxml";
        try {
            // change the scene
            FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
            root = fx.load();
            ListenerAfficheAllLoutre lu = fx.getController();
            lu.load();
            sc.setRoot(root); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void tr(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "..//View//frame//SaisieLoutre.fxml";
        try {
            // change the scene
            FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
            root = fx.load();
            sc.setRoot(root); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    void openMenu(ActionEvent event) {
        
        Menu.setVisible(true);

    }

    @FXML
    void quitMenu(ActionEvent event) {
        Menu.setVisible(false);
    }

    @FXML
    void openUserMenu(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "..//View//frame//InfoUser.fxml";
        try {
            // change the scene
            FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
            root = fx.load();
            ListenerUtilisateur lu = fx.getController();
            lu.load(this.name, "Accueil");
            sc.setRoot(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void setNom(String nom){
        bv.setText("Bienvenue " + nom);
        this.name = nom;
    }

}
