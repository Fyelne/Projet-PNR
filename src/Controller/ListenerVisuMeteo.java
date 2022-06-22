package Controller;

import Modele.donnee.ObsBatracien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ListenerVisuMeteo {

    @FXML
    private Label labelCiel;

    @FXML
    private Label labelPluie;

    @FXML
    private Label labelTemp;

    @FXML
    private Label labelVent;

    @FXML
    private Button retour;

    @FXML
    private Label titre;

    @FXML
    void goBack(ActionEvent event) {

    }


    void getControl(ObsBatracien batra) {
        labelCiel.setText(labelCiel.getText() + " " + batra.getMeteoCiel());
        labelTemp.setText(labelTemp.getText() + " " + batra.getMeteoTemp());
        labelVent.setText(labelTemp.getText() + " " + batra.getMeteoVent());
        labelPluie.setText(labelPluie.getText() + " " +  batra.getMeteoPluie());
    }

}
