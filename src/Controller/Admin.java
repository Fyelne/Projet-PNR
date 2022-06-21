package Controller;
import Controller.Utilitaire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Admin {
    @FXML
    private void goToGestionUtilisateur(ActionEvent event) {
        Utilitaire util = new Utilitaire();
        util.changeScene("GestionUtilisateur", (Button)event.getSource());
    }
}
