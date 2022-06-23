package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Modele.donnee.Chouette;
import Modele.donnee.EspeceChouette;
import Modele.donnee.Sexe;
import Modele.requete.ChouetteBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class ListenerAjoutChouette implements Initializable{
    @FXML
    private ComboBox<EspeceChouette> espece;
    @FXML
    private TextField numIndiv;
    @FXML
    private ComboBox<Sexe> sexe;
    @FXML
    private Button val;
    @FXML
    private Label mess;

    private ChouetteBdd cBdd;

    @FXML
    void valide(ActionEvent event) {
        String num = numIndiv.getText();
        if(cBdd.check(num)){
            EspeceChouette esp = espece.getSelectionModel().getSelectedItem();
            Sexe se = sexe.getSelectionModel().getSelectedItem();
            Chouette ch = new Chouette(num, se, esp);
            cBdd.insertOneIntoBdd(ch);
            ajoutReussi("Ajout de la chouette Réussi");

            Button bt = (Button) event.getSource();
            Stage st = (Stage) bt.getScene().getWindow();
            st.close();

        }else{  
            mess.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexe.getItems().addAll(Sexe.values());
        espece.getItems().addAll(EspeceChouette.values());
        cBdd = new ChouetteBdd();
    }

    public void ajoutReussi(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(message);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
