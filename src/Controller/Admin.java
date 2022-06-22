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

    @FXML
    void goToSave(ActionEvent event) {

    }

    @FXML
    void openUserMenu(ActionEvent event) {

    }
    @FXML
    private void goToGestionUtilisateur(ActionEvent event) {
        Utilitaire util = new Utilitaire();
        util.changeScene("GestionUtilisateur");
    }
}
