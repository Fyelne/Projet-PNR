package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.util.Util;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ListenerAccueil implements Initializable{
    @FXML
    private HBox HMenu;
    @FXML
    private Button menu;
    @FXML
    private Button quit;
    @FXML
    private Button user;
    @FXML
    private Button admin;
    


    @FXML
    private Label bv;
    @FXML
    private Button test;
    @FXML
    private Button add;


    private Utilitaire util = new Utilitaire();
    @FXML
    void changeTest(ActionEvent event) {
            // change the scene
            util.changeScene("Consultation");

    }

    @FXML
    void tr(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "..//View//frame//SaisieBatracien.fxml";
        try {
            // change the scene
            FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
            root = fx.load();
            sc.setRoot(root); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        bv.setText("Bienvenue " + Utilitaire.getCurrentNameUser());
        if(Utilitaire.getEstAdmin()){
            admin.setVisible(true);
        }
    }



    //code du Menu
    @FXML
    void openMenu(ActionEvent event) {
        HMenu.setVisible(true);
    }
    @FXML
    void quitMenu(ActionEvent event) {
        HMenu.setVisible(false);
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
            lu.load(Utilitaire.getCurrentNameUser(), "Accueil");
            sc.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToAdmin(ActionEvent event) {
        util.changeScene("Admin");
    }
    @FXML
    void goToAccueil(ActionEvent event) {
        util.changeScene("Accueil");
    }

    @FXML
    void goToAddDonnee(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }

    @FXML
    void goToChoixReleve(ActionEvent event) {
        util.changeScene("Consultation");
    }



}
