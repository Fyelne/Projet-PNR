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
    private Button Hippo;

    @FXML
    private Button bat;

    @FXML
    private Button chouette;

    @FXML
    private Button grav;

    @FXML
    void obsBat(ActionEvent event) {

    }

    @FXML
    void obsChouette(ActionEvent event) {

    }

    @FXML
    void obsGrav(ActionEvent event) {

    }

    @FXML
    void obsHippo(ActionEvent event) {
        util.changeScene("ListeObsHippo", Hippo);
    }

    @FXML
    void obsLoutre(ActionEvent event) {
        util.changeScene("ListeObsLoutre", loutre);
    }

    @FXML
    void retouracc(ActionEvent event) {
        util.changeScene("Accueil", retour);
    }

}
