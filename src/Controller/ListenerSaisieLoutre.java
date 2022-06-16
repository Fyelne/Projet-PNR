package Controller;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

import com.mysql.cj.xdevapi.PreparableStatement;

import Modele.donnee.*;
import Modele.requete.Loutre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ListenerSaisieLoutre implements Initializable {
    @FXML
    private TextField commune;
    @FXML
    private ComboBox<IndiceLoutre> indice;
    @FXML
    private TextField lieuDit;
    @FXML
    private TextField coordX;
    @FXML
    private TextField coordY;
    @FXML
    private DatePicker date;
    @FXML
    private TextField heure;
    @FXML
    private Button menu;
    @FXML
    private Button user;

    @FXML
    void connect(ActionEvent event) {

    }

    @FXML
    void openMenu(ActionEvent event) {

    }

    @FXML
    void openUserMenu(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        indice.getItems().addAll(IndiceLoutre.values());
        
    }

    @FXML
    void addObs(ActionEvent event) {
        Lieu l = new Lieu(Double.parseDouble(coordX.getText()), Double.parseDouble(coordY.getText()));

        Observateur observateur = new Observateur(81, "leNom", "lePrenom");
        observateur.setNom("NOEL");
        observateur.setPrenom("Andy");
        
        ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
        observateurs.add(observateur);
        observateurs.add(new Observateur(82, "leNom", "lePrenom"));

        Date d = Date.valueOf(date.getValue());
        // à revoir pour avoir un timePicker
        LocalTime t = LocalTime.parse(heure.getText());
        Time ti = Time.valueOf(t);

        int id = Modele.requete.Utilitaire.giveID();
        
        

        ObsLoutre obsL = new ObsLoutre(id, d, ti, l, observateurs, indice.getValue(), commune.getText(), lieuDit.getText());

        Loutre loutreBDD = new Loutre();
        
        loutreBDD.insertOneIntoBdd(obsL);
        System.out.println("L'observation a été Ajouté");
    }


}
