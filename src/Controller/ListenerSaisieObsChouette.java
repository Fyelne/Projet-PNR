package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import Modele.donnee.*;
import Modele.requete.ObsChouetteBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class ListenerSaisieObsChouette extends ListenerObs{

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
    private Label laChouette;
    @FXML
    private Spinner<Integer> minute;
    @FXML
    private ComboBox<String> prot;
    @FXML
    private Button retour;
    @FXML
    private ComboBox<TypeObservation> typeObs;

    private ArrayList<Observateur> listDesObs = new ArrayList<Observateur>();

    private ObsChouetteBdd chBdd;

    private Chouette chou;

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
        TypeObservation type = typeObs.getSelectionModel().getSelectedItem();
        boolean protocole = false;
        String pr = prot.getSelectionModel().getSelectedItem();
        if(pr.equals("OUI")){
            protocole = true;
        }

        ObsChouette obsC = new ObsChouette(id, da, t, l, this.listDesObs, type, protocole);

        chBdd.insertIneIntoBdd(obsC, chou.getId());

        

    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void openMenu(ActionEvent event) {

    }

    @FXML
    void openUserMenu(ActionEvent event) {

    }


    public void load(Chouette ch) {
        prot.getItems().add("OUI");
        prot.getItems().add("NON");

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        valueFactory.setValue(00);
        heure.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> val = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        val.setValue(00);
        minute.setValueFactory(val);

        typeObs.getItems().addAll(TypeObservation.values());
        laChouette.setText(laChouette.getText() + ch.getId());
        chBdd = new ObsChouetteBdd();
        chou = ch;
        
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
    public void setListDesObs(ArrayList<Observateur> o){
        this.listDesObs = o;
    }

}
