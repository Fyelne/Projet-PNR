package Controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Modele.donnee.ContenuNid;
import Modele.donnee.EspeceHippocampe;
import Modele.donnee.ObsHippocampe;
import Modele.donnee.Observation;
import Modele.donnee.Peche;
import Modele.donnee.Sexe;
import Modele.requete.ObsHippocampeBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListenerAfficheAllHippocampe {
    @FXML
    private TableColumn<ObsHippocampe, Date> date;
    @FXML
    private TableColumn<ObsHippocampe, Time> heure;
    @FXML
    private TableColumn<ObsHippocampe, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsHippocampe, Double> taille;
    @FXML
    private TableColumn<ObsHippocampe, Boolean> estGestant;
    @FXML
    private TableColumn<ObsHippocampe, Peche> typePeche;
    @FXML
    private TableColumn<ObsHippocampe, EspeceHippocampe> espece;
    @FXML
    private TableColumn<ObsHippocampe, Sexe> sexe;
    @FXML
    private TableView<ObsHippocampe> tab;
    @FXML
    private Button user;

    protected void load(){
        ObsHippocampeBdd data = new ObsHippocampeBdd();

        ArrayList<ObsHippocampe> obs = data.builder(data.getAllHippocampeToBuild());
        ObservableList<ObsHippocampe> tr = FXCollections.observableArrayList(obs);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));

        taille.setCellValueFactory(new PropertyValueFactory<>("taille"));

        estGestant.setCellValueFactory(new PropertyValueFactory<>("estGestant"));
        
        typePeche.setCellValueFactory(new PropertyValueFactory<>("typePeche"));
        
        espece.setCellValueFactory(new PropertyValueFactory<>("espece"));

        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));


        tab.setItems(tr);
    }

    /* 
    @FXML
    void recherche(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            filtre();
        }
    }



    
    void filtre(){
        String rechercheString = rechercheTF.getText();
        ObsLoutreBdd data = new ObsLoutreBdd();

        ArrayList<ObsHippocampe> obs = data.builder(data.getFilteredLoutre(rechercheString));
        initializeData(obs);
    }

    @FXML
    void retourcons(ActionEvent event) {
        util.changeScene("Consultation");
    }*/

}
