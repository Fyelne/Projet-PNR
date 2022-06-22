package Controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;


public class ListenerVisuObs {
    private Utilitaire util = new Utilitaire();

    @FXML
    private Button retour;

    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("AffichageObservationLoutre", retour);
    }
}
