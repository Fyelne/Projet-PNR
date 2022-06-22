package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.Singleton;
import Modele.donnee.*;
import Modele.requete.ObsChouetteBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ListenerSaisieObsChouette extends ListenerObs implements Initializable{

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
    private Label laChouette;
    @FXML
    private Spinner<Integer> minute;
    @FXML
    private ComboBox<String> prot;
    @FXML
    private Button retour;
    @FXML
    private ComboBox<TypeObservation> typeObs;
    @FXML
    private ComboBox<String> numC;

    private ArrayList<Observateur> listDesObs = new ArrayList<Observateur>();

    private ObsChouetteBdd chBdd;

    private TextField txt;


    @FXML
	public void addObs(ActionEvent event) {
        Lieu l = new Lieu(Double.parseDouble(coordX.getText()), Double.parseDouble(coordY.getText()));

;
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

        ObsChouette obsC = new ObsChouette(id, da, t, l, this.listDesObs, type, protocole, numC.getSelectionModel().getSelectedItem());

        chBdd.insertIneIntoBdd(obsC, numC.getSelectionModel().getSelectedItem());

        

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


    public void load(Chouette ch) {
        
        

        
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
            
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void setListDesObs(ArrayList<Observateur> o){
        this.listDesObs = o;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prot.getItems().add("OUI");
        prot.getItems().add("NON");
        chBdd = new ObsChouetteBdd();
        ArrayList<String> t = chBdd.recupNumIndiv();
        for(String g : t){
            System.out.println(g);
        }
        numC.getItems().addAll(t);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        valueFactory.setValue(00);
        heure.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> val = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        val.setValue(00);
        minute.setValueFactory(val);

        typeObs.getItems().addAll(TypeObservation.values());
        txt = numC.getEditor();
    }

    @FXML
    void search(KeyEvent event) {
        ArrayList<String> newId = new ArrayList<String>();
        Connection con = Singleton.getInstance().getConnection();
        
        numC.setVisibleRowCount(10);
        String search = txt.getText().toUpperCase();
        System.out.println(search);

        String req = "SELECT * FROM chouette WHERE numIndividu LIKE '%"+search+"%';";
        System.out.println(req);
        ResultSet res = null;
        try {
            PreparedStatement stmt = con.prepareStatement(req);
            res = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
            try {
                while(res.next()){
                    newId.add(res.getString("numIndividu"));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
        ObservableList<String> ne = FXCollections.observableArrayList(newId);
        numC.setItems(ne);
    }

}
