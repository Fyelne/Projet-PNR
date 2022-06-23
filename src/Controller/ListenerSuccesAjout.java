package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class ListenerSuccesAjout implements Initializable{    

    private Utilitaire util = new Utilitaire();

    /**
     * Permet d'acceder au menu qui donne des informations sur l'utilisateur 
     * @param event le bouton cliqué
     */
    @FXML
    void openUserMenu(ActionEvent event) {

    }

    /**
     * Initialise le succes d'un ajout
     * 
     * @param location l'emplacement du fichier FXML
     * @param resources Faisceau de ressources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("TestLoc");
        util.changeScene("ChoixAjouter");
        
    }



}
