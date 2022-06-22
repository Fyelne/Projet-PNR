package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.donnee.EspeceHippocampe;
import Modele.donnee.Observateur;
import Modele.donnee.Peche;
import Modele.donnee.Sexe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ListenerSaisieObsHippocampe extends ListenerObs implements Initializable{

    @FXML
    private TextField X;
    @FXML
    private TextField Y;
    @FXML
    private Button add;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<EspeceHippocampe> espece;
    @FXML
    private ComboBox<String> gestant;
    @FXML
    private Spinner<Integer> heure;
    @FXML
    private Button menu;
    @FXML
    private Spinner<Integer> minute;
    @FXML
    private Button observateur;
    @FXML
    private ComboBox<Peche> peche;
    @FXML
    private Button ret;
    @FXML
    private ComboBox<Sexe> sexe;
    @FXML
    private Spinner<Integer> taille;
    @FXML
    private Spinner<Integer> temperature;
    @FXML
    private Button user;
    private ArrayList<Observateur> listDesObs;

    @FXML
    public void addObs(ActionEvent event) {
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

    @FXML
    public void addObservateur(ActionEvent event) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listDesObs = new ArrayList<Observateur>();
        espece.getItems().addAll(EspeceHippocampe.values());

        peche.getItems().addAll(Peche.values());

        sexe.getItems().addAll(Sexe.values());

        gestant.getItems().add("OUI");
        gestant.getItems().add("NON");

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        valueFactory.setValue(00);
        heure.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> val = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        val.setValue(00);
        minute.setValueFactory(val);

        SpinnerValueFactory<Integer> tail = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        tail.setValue(00);
        taille.setValueFactory(tail);

        SpinnerValueFactory<Integer> temp = new SpinnerValueFactory.IntegerSpinnerValueFactory(-50, 60);
        temp.setValue(00);
        temperature.setValueFactory(temp);
        
    }

    @Override
    public void setListDesObs(ArrayList<Observateur> o) {
        
        
    }

}
