package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.donnee.ObsLoutre;
import Modele.donnee.Observateur;
import Modele.donnee.Observation;
import Modele.requete.ObservateurBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ListenerVisuObs {
    private Utilitaire util = new Utilitaire();

    @FXML
    private TableColumn<Observateur, String> no;

    @FXML
    private TableColumn<Observateur, String> pre;

    @FXML
    private Button retour;

    @FXML
    private TableView<Observateur> table;
    private ObservateurBdd oBdd;

    @FXML
    void goBack(ActionEvent event) {
        
    }

    public void load(Observation obsL){
        int id = obsL.getId();
        oBdd = new ObservateurBdd();
        ResultSet res = Modele.requete.Utilitaire.recupObs(id);

        ArrayList<Observateur> lesObs = oBdd.builder(res);

        ObservableList<Observateur> ne = FXCollections.observableArrayList(lesObs);
        pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        no.setCellValueFactory(new PropertyValueFactory<>("nom"));
        table.setItems(ne);
    }
}
