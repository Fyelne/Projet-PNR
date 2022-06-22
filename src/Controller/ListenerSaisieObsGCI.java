package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.donnee.ContenuNid;
import Modele.donnee.Lieu;
import Modele.donnee.NidGCI;
import Modele.donnee.ObsGCI;
import Modele.donnee.Observateur;
import Modele.requete.ObsGCIBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ListenerSaisieObsGCI extends ListenerObs implements Initializable {

    private Utilitaire util = new Utilitaire();

    @FXML
    private Button ajouter;

    @FXML
    private Button btAddObs;

    @FXML
    private TextField coordX;

    @FXML
    private TextField coordY;

    @FXML
    private DatePicker date;

    @FXML
    private Spinner<Integer> heure;

    @FXML
    private Button menu;

    @FXML
    private Spinner<Integer> minute;

    @FXML
    private Spinner<Integer> nb;

    @FXML
    private ComboBox<String> present;

    @FXML
    private ComboBox<ContenuNid> nature;

    @FXML
    private Button user;

    @FXML
    private Label intitule;

    
    @FXML
    private Button chNid;

    private ArrayList<Observateur> listDesObs = new ArrayList<Observateur>();
    private int leNid;

    @FXML
    public void addObs(ActionEvent event) {
        Lieu l = new Lieu(Double.parseDouble(coordX.getText()), Double.parseDouble(coordY.getText()));

;
        System.out.println(date.getValue().toString());
        // à revoir pour avoir un timePicker
        int h = heure.getValue();
        int min = minute.getValue();
        Time t = new Time((h-1)*60*100*60 + min*60*1000);
        
        int id = Modele.requete.Utilitaire.giveID();
        Date da = Date.valueOf(date.getValue());

        ContenuNid cont = nature.getSelectionModel().getSelectedItem();
        System.out.println(cont.toString());
        String res = present.getSelectionModel().getSelectedItem();
        boolean pres = false;
        if(res.equals("OUI")){
            pres = true;
        }

        ObsGCI g = new ObsGCI(id, da, t, l, listDesObs, cont, nb.getValue(), pres, leNid);

        ObsGCIBdd gBdd = new ObsGCIBdd();
        gBdd.insertOneInto(g, leNid);
    }

    @FXML
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
            
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(NidGCI nid){
       
    }

    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }

    @FXML
    void openMenu(ActionEvent event) {

    }

    @FXML
    void openUserMenu(ActionEvent event) {

    }

    @Override
    public void setListDesObs(ArrayList<Observateur> o) {
        listDesObs = o;
        
    }

    @FXML
    void chooseNid(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("..\\View\\frame\\ChoixNidGCI.fxml"));
            r = loader.load();
            ListenerChoixNidGCI o = loader.getController();
            o.load(this);
            Scene s = new Scene(r);
            newStage.setTitle("Choix du Nid");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();
            
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        present.getItems().add("OUI");
        present.getItems().add("NON");

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        valueFactory.setValue(00);
        heure.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> val = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        val.setValue(00);
        minute.setValueFactory(val);

        SpinnerValueFactory<Integer> n = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        n.setValue(00);
        nb.setValueFactory(n);

        nature.getItems().addAll(ContenuNid.values());
        leNid = 0;
    }

    public void setLeNid(int n){
        this.leNid = n;
    }



}
