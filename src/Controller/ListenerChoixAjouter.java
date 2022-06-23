package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ListenerChoixAjouter {


    @FXML
    private Button menu;

    @FXML
    private Button user;
    

    @FXML
    private HBox HMenu;

    @FXML
    private Button admin;

    @FXML
    private Button loutre;

    @FXML
    private Button quit;

    @FXML
    private Button retour;
    private Utilitaire util = new Utilitaire();
//code du Menu
    @FXML
    void openMenu(ActionEvent event) {
        HMenu.setVisible(true);
    }
    
    /**
     * When the quit button is clicked, the menu is set to invisible.
     * @param event The event that triggered the action.
     */
    @FXML
    void quitMenu(ActionEvent event) {
        HMenu.setVisible(false);
    }
    @FXML
    void openUserMenu(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "/frame/InfoUser.fxml";
        try {
            // change the scene
            FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
            root = fx.load();
            ListenerUtilisateur lu = fx.getController();
            lu.load(Utilitaire.getCurrentNameUser(), "ChoixAjouter");
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


    @FXML
    void obsLoutre(ActionEvent event) {
        util.changeScene("SaisieLoutre");
    }

    @FXML
    void obsBat(ActionEvent event) {
        util.changeScene("SaisieBatracien");
    }

    @FXML
    void obsGrav(ActionEvent event) {
        util.changeScene("SaisieObsGCI");
    }

    @FXML
    void obsChouette(ActionEvent event) {
        util.changeScene("SaisieObsChouette");
    }

    @FXML
    void obsHippo(ActionEvent event) {
        util.changeScene("SaisieHippocampe");
    }

    @FXML
    void retouracc(ActionEvent event) {
        util.changeScene("Accueil");
    }

}
