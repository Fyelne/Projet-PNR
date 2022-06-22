package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ListenerConsultation {

    private Utilitaire util = new Utilitaire();

    @FXML
    void obsBat(ActionEvent event) {
        util.changeScene("ListeObsBatracien");
    }

    /**
     * Lorsque le bouton est cliqué, la scène est changée en scène ListeObsChouette
     * @param event L'événement qui a déclenché la méthode.
     */
    @FXML
    void obsChouette(ActionEvent event) {
        util.changeScene("ListeObsChouette");
    }

    @FXML
    void obsGrav(ActionEvent event) {
        util.changeScene("ListeObsGravelot");
    }

    @FXML
    void obsHippo(ActionEvent event) {
        util.changeScene("ListeObsHippocampe");
    }

    @FXML
    void obsLoutre(ActionEvent event) {
        util.changeScene("ListeObsLoutre");
    }

    @FXML
    void retouracc(ActionEvent event) {
        util.changeScene("Accueil");
    }

}
