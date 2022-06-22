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

public class ListenerConsultation {

    private Utilitaire util = new Utilitaire();

    @FXML
    private Button loutre;

    @FXML
    private Button retour;


    @FXML
    void obsLoutre(ActionEvent event) {
        util.changeScene("ChoixConsultation", loutre);
    }

    @FXML
    void retouracc(ActionEvent event) {
        util.changeScene("Accueil", retour);
    }

}
