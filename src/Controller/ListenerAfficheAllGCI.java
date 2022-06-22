package Controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Modele.donnee.ContenuNid;
import Modele.donnee.ObsGCI;
import Modele.donnee.Observation;
import Modele.requete.ObsGCIBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListenerAfficheAllGCI {
    @FXML
    private TableColumn<ObsGCI, Date> date;
    @FXML
    private TableColumn<ObsGCI, Time> heure;
    @FXML
    private TableColumn<ObsGCI, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsGCI, ContenuNid> nature;
    @FXML
    private TableColumn<ObsGCI, Integer> nombre;
    @FXML
    private TableView<ObsGCI> tab;
    @FXML
    private Button user;

    protected void load(){
        ObsGCIBdd data = new ObsGCIBdd();

        ArrayList<ObsGCI> obs = data.builder(data.getAllGCIToBuild());
        ObservableList<ObsGCI> tr = FXCollections.observableArrayList(obs);

        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        nature.setCellValueFactory(new PropertyValueFactory<>("natureNid"));

        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));


        tab.setItems(tr);
    }

}
