package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListenerChoixAjouter {

    @FXML
    private Button addChouette;
    @FXML
    private Button addNid;
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
    /**
     * Affiche le menu
     * @param event le bouton cliqué
     */
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

    /**
     * Permet d'acceder au menu qui donne des informations sur l'utilisateur 
     * @param event le bouton cliqué
     */
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

    /**
     * Permet d'acceder au menu admin
     * @param event le bouton cliqué
     */
    @FXML
    void goToAdmin(ActionEvent event) {
        util.changeScene("Admin");
    }

    /**
     * Permet d'acceder à l'accueil
     * @param event le bouton cliqué
     */
    @FXML
    void goToAccueil(ActionEvent event) {
        util.changeScene("Accueil");
    }

    /**
     * Permet d'acceder au menu d'ajout de données
     * @param event le bouton cliqué
     */
    @FXML
    void goToAddDonnee(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }

    /**
     * Permet d'acceder au menu des choix de relevé
     * @param event le bouton cliqué
     */
    @FXML
    void goToChoixReleve(ActionEvent event) {
        util.changeScene("Consultation");
    }

    /**
     * Permet d'acceder au menu de saisie de loutre
     * @param event le bouton cliqué
     */
    @FXML
    void obsLoutre(ActionEvent event) {
        util.changeScene("SaisieLoutre");
    }

    /**
     * Permet d'acceder au menu de saisie de batracien
     * @param event le bouton cliqué
     */
    @FXML
    void obsBat(ActionEvent event) {
        util.changeScene("SaisieBatracien");
    }

    /**
     * Permet d'acceder au menu de saisie de gravelot
     * @param event le bouton cliqué
     */
    @FXML
    void obsGrav(ActionEvent event) {
        util.changeScene("SaisieObsGCI");
    }

    /**
     * Permet d'acceder au menu de saisie de chouette
     * @param event le bouton cliqué
     */
    @FXML
    void obsChouette(ActionEvent event) {
        util.changeScene("SaisieObsChouette");
    }

    /**
     * Permet d'acceder au menu de saisie de loutre
     * @param event le bouton cliqué
     */
    @FXML
    void obsHippo(ActionEvent event) {
        util.changeScene("SaisieHippocampe");
    }

    /**
    * Retourne à l'accueil
     * @param event le bouton cliqué
     */
    @FXML
    void retouracc(ActionEvent event) {
        util.changeScene("Accueil");
    }

    /**
     * Ouvre une nouvelle fenêtre (AjoutChouette.fxml) lorsque l'utilisateur clique sur le bouton
     * "Ajouter une Chouette"
     * @param event L'événement qui a déclenché l'action.
     */
    @FXML
    void ToAddChouette(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/AjoutChouette.fxml"));
            r = loader.load();
            Scene s = new Scene(r);
            newStage.setTitle("Ajout Chouette");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Ouvre une nouvelle fenêtre (AjoutNidGCI.fxml) lorsque l'utilisateur clique sur le bouton
     * "Ajouter un nid"
     * @param event L'événement qui a déclenché l'action.
     */
    @FXML
    void ToAddNid(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/AjoutNidGCI.fxml"));
            r = loader.load();
            Scene s = new Scene(r);
            newStage.setTitle("Ajout un nid");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen(); 
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
