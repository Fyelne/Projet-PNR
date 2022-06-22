package Controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Modele.donnee.ObsChouette;
import Modele.donnee.Observation;
import Modele.donnee.TypeObservation;
import Modele.requete.ChouetteBdd;
import Modele.requete.ObsChouetteBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListenerAfficheAllChouette {
    // @FXML
    // private TableColumn<ObsChouette, Date> date;
    // @FXML
    // private TableColumn<ObsChouette, Time> heure;
    @FXML
    private TableColumn<ObsChouette, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsChouette, TypeObservation> typeObs;
    @FXML
    private TableView<ObsChouette> tab;
    @FXML
    private Button user;

    protected void load(){
        ObsChouetteBdd data = new ObsChouetteBdd();

        //ArrayList<ObsChouette> obs = data.builder(data.getAllChouetteToBuild());
        //ObservableList<ObsChouette> tr = FXCollections.observableArrayList(obs);

        // date.setCellValueFactory(new PropertyValueFactory<>("date"));

        // heure.setCellValueFactory(new PropertyValueFactory<>("heure"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));


        typeObs.setCellValueFactory(new PropertyValueFactory<>("typeObs"));

        //tab.setItems(tr);
    }

}
