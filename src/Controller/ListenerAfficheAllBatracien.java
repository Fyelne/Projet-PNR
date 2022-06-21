package Controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Modele.donnee.EspeceBatracien;
import Modele.donnee.IndiceLoutre;
import Modele.donnee.ObsBatracien;
import Modele.donnee.Observation;
import Modele.requete.Batracien;
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

public class ListenerAfficheAllBatracien {
    @FXML
    private TableColumn<ObsBatracien, Date> date;
    @FXML
    private TableColumn<ObsBatracien, Time> heure;
    @FXML
    private TableColumn<ObsBatracien, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsBatracien, EspeceBatracien> espece;
    @FXML
    private TableView<ObsBatracien> tab;
    @FXML
    private Button user;

    protected void load(){
        Batracien data = new Batracien();

        ArrayList<ObsBatracien> obs = data.builder(data.getAllBatracienToBuild());
        ObservableList<ObsBatracien> tr = FXCollections.observableArrayList(obs);

        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        espece.setCellValueFactory(new PropertyValueFactory<>("espece"));

        tab.setItems(tr);
    }

}
