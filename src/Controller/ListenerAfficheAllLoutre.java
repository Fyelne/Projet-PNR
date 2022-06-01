package Controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Modele.donnee.IndiceLoutre;
import Modele.donnee.ObsLoutre;
import Modele.donnee.Observation;
import Modele.requete.Loutre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListenerAfficheAllLoutre {
    @FXML
    private TableColumn<ObsLoutre, Date> date;
    @FXML
    private TableColumn<ObsLoutre, Time> heure;
    @FXML
    private TableColumn<ObsLoutre, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<Observation, Integer> nom;
    @FXML
    private TableColumn<ObsLoutre, IndiceLoutre> statut;
    @FXML
    private TableView<ObsLoutre> tab;
    @FXML
    private Button user;

    protected void load(){
        Loutre data = new Loutre();
        ArrayList<ObsLoutre> obs = data.builder(data.getAllLoutreToBuild());
        ObservableList<ObsLoutre> tr = FXCollections.observableArrayList(obs);

        date.setCellValueFactory(new PropertyValueFactory<>("dateObs"));

        heure.setCellValueFactory(new PropertyValueFactory<>("heureObs"));

        id.setCellValueFactory(new PropertyValueFactory<>("idObs"));

        nom.setCellValueFactory(new PropertyValueFactory<>("idObs"));

        statut.setCellValueFactory(new PropertyValueFactory<>("indice"));

        tab.setItems(tr);
    }

}
