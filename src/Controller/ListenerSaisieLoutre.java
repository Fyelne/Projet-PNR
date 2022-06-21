package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import com.mysql.cj.xdevapi.PreparableStatement;

import Modele.donnee.*;
import Modele.requete.Loutre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ListenerSaisieLoutre extends ListenerObs implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private TextField commune;
    @FXML
    private TextField coordX;
    @FXML
    private TextField coordY;
    @FXML
    private DatePicker date;
    @FXML
    private Spinner<Integer> heure;
    @FXML
    private ComboBox<IndiceLoutre> indice;
    @FXML
    private TextField lieuDit;
    @FXML
    private Button menu;
    @FXML
    private Spinner<Integer> minute;
    @FXML
    private Button user;
    @FXML
    private Button btAddObs;

    private ArrayList<Observateur> listDesObs = new ArrayList<Observateur>();

    @FXML
    void connect(ActionEvent event) {

    }

    @FXML
    void openMenu(ActionEvent event) {

    }

    @FXML
    void openUserMenu(ActionEvent event) {

    }
    public void setListDesObs(ArrayList<Observateur> o){
        this.listDesObs = o;
    }

    @Override
    public void addObservateur(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("..\\View\\frame\\GestionObservateur.fxml"));
            r = loader.load();
            ListenerGestionObs o = loader.getController();
            o.getControl(this, listDesObs);
            Scene s = new Scene(r);
            newStage.setTitle("Saisie Observateur");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();
            Utilitaire.setScene(s);
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        indice.getItems().addAll(IndiceLoutre.values());

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        valueFactory.setValue(00);
        heure.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> val =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        val.setValue(00);
        minute.setValueFactory(val);
        


        
    }

    @FXML
    public void addObs(ActionEvent event) {
        Lieu l = new Lieu(Double.parseDouble(coordX.getText()), Double.parseDouble(coordY.getText()));


        System.out.println(date.getValue().toString());
        // à revoir pour avoir un timePicker
        int h = heure.getValue();
        int min = minute.getValue();
        Time t = new Time((h-1)*60*100*60 + min*60*1000);
        
        int id = Modele.requete.Utilitaire.giveID();
        Date da = Date.valueOf(date.getValue());
        

        ObsLoutre obsL = new ObsLoutre(id, da, t, l, this.listDesObs, indice.getValue(), commune.getText(), lieuDit.getText());

        Loutre loutreBDD = new Loutre();
        
        loutreBDD.insertOneIntoBdd(obsL);
        System.out.println("L'observation a été Ajouté");
    }

    
    public void addObservateur() {
        // TODO Auto-generated method stub
        
    }


}
