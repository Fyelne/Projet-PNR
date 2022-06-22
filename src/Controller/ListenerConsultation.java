package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ListenerConsultation {

    private Utilitaire util = new Utilitaire();

    @FXML
    private Button loutre;

    @FXML
    private Button retour;


    @FXML
    void obsLoutre(ActionEvent event) {
        util.changeScene("ListeObsLoutre", loutre);
    }

    @FXML
    void retouracc(ActionEvent event) {
        util.changeScene("Accueil", retour);
    }

}
