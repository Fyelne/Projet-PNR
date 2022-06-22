package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Admin {
    @FXML
    private Button champ;

    @FXML
    private Button gestionUtilisateur;

    @FXML
    private Button user;

    /**
     * Permet d'acceder au menu qui sauvegarde
     * @param event le bouton cliqué
     */
    @FXML
    void goToSave(ActionEvent event) {

    }

    /**
     * Permet d'acceder au menu qui donne des informations sur l'utilisateur 
     * @param event le bouton cliqué
     */
    @FXML
    void openUserMenu(ActionEvent event) {

    }

    /**
     * Permet d'acceder tableau listant les utilisateurs
     * @param event le bouton cliqué
     */
    @FXML
    private void goToGestionUtilisateur(ActionEvent event) {
        Utilitaire util = new Utilitaire();
        util.changeScene("GestionUtilisateur");
    }
}
