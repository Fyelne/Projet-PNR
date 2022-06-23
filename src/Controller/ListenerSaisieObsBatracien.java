package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.donnee.EspeceBatracien;
import Modele.donnee.Lieu;
import Modele.donnee.MeteoCiel;
import Modele.donnee.MeteoPluie;
import Modele.donnee.MeteoTemp;
import Modele.donnee.MeteoVent;
import Modele.donnee.ObsBatracien;
import Modele.donnee.Observateur;
import Modele.donnee.Vegetation;
import Modele.donnee.ZoneHumide;
import Modele.requete.ObsBatracienBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListenerSaisieObsBatracien extends ListenerObs implements Initializable{

    private Utilitaire util = new Utilitaire();

    @FXML
    private HBox HMenu;
    @FXML
    private Button quit;
    @FXML
    private Button user;
    @FXML
    private Button admin;

    @FXML
    private TextField X;
    @FXML
    private TextField Y;
    @FXML
    private Button add;
    @FXML
    private Spinner<Integer> adulte;
    @FXML
    private Spinner<Integer> ampexus;
    @FXML
    private ComboBox<MeteoCiel> ciel;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<EspeceBatracien> espece;
    @FXML
    private Spinner<Integer> heure;
    @FXML
    private Button menu;
    @FXML
    private Spinner<Integer> minute;
    @FXML
    private Button observateur;
    @FXML
    private ComboBox<MeteoPluie> pluie;
    @FXML
    private Spinner<Integer> ponte;
    @FXML
    private Button ret;
    @FXML
    private ComboBox<MeteoTemp> temp;
    @FXML
    private Spinner<Integer> temperature;
    @FXML
    private Spinner<Integer> tetard;
    @FXML
    private Button veg;
    @FXML
    private ComboBox<MeteoVent> vent;
    @FXML
    private Button zh;
    private ArrayList<Observateur> listDesObs = new ArrayList<Observateur>();
    private ArrayList<Vegetation> laVege = new ArrayList<Vegetation>();
    private ZoneHumide laZoneHumide;
    private ObsBatracienBdd batracienBdd;

    @FXML
    public void addObs(ActionEvent event) {
        Lieu l = new Lieu(Double.parseDouble(X.getText()), Double.parseDouble(Y.getText()));


        System.out.println(date.getValue().toString());
        // à revoir pour avoir un timePicker
        int h = heure.getValue();
        int min = minute.getValue();
        Time t = new Time((h-1)*60*100*60 + min*60*1000);
        
        int id = Modele.requete.Utilitaire.giveID();
        Date da = Date.valueOf(date.getValue());

        int[] resultats = {adulte.getValue(), ampexus.getValue(), tetard.getValue(), ponte.getValue()};
        ObsBatracien obsB = new ObsBatracien(id, da, t, l, listDesObs, resultats, espece.getSelectionModel().getSelectedItem()
                            , temperature.getValue(), ciel.getSelectionModel().getSelectedItem(), temp.getSelectionModel().getSelectedItem()
                            , vent.getSelectionModel().getSelectedItem(), pluie.getSelectionModel().getSelectedItem());
        
        batracienBdd.insertBatracienAndOther(obsB, laVege, laZoneHumide);
        ajoutReussi("Observation ajoutée");
        util.changeScene("ChoixAjouter");
    }

    public void ajoutReussi(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(message);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void addObservateur(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/GestionObservateur.fxml"));
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

    @FXML
    void addVege(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/gestionVege.fxml"));
            r = loader.load();
            ListenerGestionVege o = loader.getController();
            o.getControl(this, laVege);
            Scene s = new Scene(r);
            newStage.setTitle("Saisie de la végétation");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();
            
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addZh(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/gestionZoneHumide.fxml"));
            r = loader.load();
            ListenerGestionZh o = loader.getController();
            o.getControl(this);
            Scene s = new Scene(r);
            newStage.setTitle("Saisie de la zone Humide");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();
            
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        valueFactory.setValue(00);
        heure.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> val = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        val.setValue(00);
        minute.setValueFactory(val);

        
        SpinnerValueFactory<Integer> ad = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25);
        ad.setValue(00);
        adulte.setValueFactory(ad);

        SpinnerValueFactory<Integer> amp = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25);
        amp.setValue(00);
        ampexus.setValueFactory(amp);

        
        SpinnerValueFactory<Integer> p = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25);
        p.setValue(00);
        ponte.setValueFactory(p);

        SpinnerValueFactory<Integer> tet = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25);
        tet.setValue(00);
        tetard.setValueFactory(tet);
        
        SpinnerValueFactory<Integer> tem = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        tem.setValue(00);
        temperature.setValueFactory(tem);

        ciel.getItems().addAll(MeteoCiel.values());
        espece.getItems().addAll(EspeceBatracien.values());
        pluie.getItems().addAll(MeteoPluie.values());
        temp.getItems().addAll(MeteoTemp.values());
        vent.getItems().addAll(MeteoVent.values());
        
        batracienBdd = new ObsBatracienBdd();
    }

    @Override
    public void setListDesObs(ArrayList<Observateur> o) {
        this.listDesObs = o;
    }

    public void setListVege(ArrayList<Vegetation> v){
        this.laVege = v;
    }

    public void setZoneHumide(ZoneHumide z){
        this.laZoneHumide = z;
    }

    //code du Menu
    /**
     * Affiche le menu
     * @param event le bouton cliqué
     */
    @FXML
    void openMenu(ActionEvent event) {
        HMenu.setVisible(true);
    }

    /**
     * Quitte le menu
     * @param event le bouton cliqué
     */
    @FXML
    void quitMenu(ActionEvent event) {
        HMenu.setVisible(false);
    }

    /**
     * Permet d'acceder au menu qui donne des informations sur l'utilisateur 
     * @param event le bouton cliqué
     */
    @FXML
    void openUserMenu(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "/frame/InfoUser.fxml";
        try {
            // change the scene
            FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
            root = fx.load();
            ListenerUtilisateur lu = fx.getController();
            lu.load(Utilitaire.getCurrentNameUser(), "SaisieBatracien");
            sc.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de changer de scene et d'acceder à la page Admin
     * @param event le bouton cliqué
     */
    @FXML
    void goToAdmin(ActionEvent event) {
        util.changeScene("Admin");
    }

    /**
     * Permet de changer de scene et d'acceder à la page Accueil
     * @param event le bouton cliqué
     */
    @FXML
    void goToAccueil(ActionEvent event) {
        util.changeScene("Accueil");
    }

    /**
     * Permet de changer de scene et d'acceder à la page ChoixAjouter
     * @param event le bouton cliqué
     */
    @FXML
    void goToAddDonnee(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }

    /**
     * Permet de changer de scene et d'acceder à la page Consultation
     * @param event le bouton cliqué
     */
    @FXML
    void goToChoixReleve(ActionEvent event) {
        util.changeScene("Consultation");
    }

}
