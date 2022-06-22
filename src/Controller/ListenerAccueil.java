package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ListenerAccueil implements Initializable{
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

    

    //@FXML
    //private Label username;

    @FXML
    private Button test;

    private Utilitaire util = new Utilitaire();
    @FXML
    void changeTest(ActionEvent event) {
            // change the scene
            util.changeScene("ChoixConsultation", user);

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    void openMenu(ActionEvent event) {
        
        Menu.setVisible(true);

    }

    @FXML
    private void goToGestionUtilisateur(ActionEvent event) {
        Utilitaire util = new Utilitaire();
        util.changeScene("GestionUtilisateur", user);
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
            lu.load(Utilitaire.getCurrentNameUser(), "Accueil");
            sc.setRoot(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        bv.setText("Bienvenue " + Utilitaire.getCurrentNameUser());
        
    }

}
