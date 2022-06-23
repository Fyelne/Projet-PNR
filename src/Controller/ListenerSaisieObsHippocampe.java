package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.donnee.EspeceHippocampe;
import Modele.donnee.Lieu;
import Modele.donnee.ObsHippocampe;
import Modele.donnee.Observateur;
import Modele.donnee.Peche;
import Modele.donnee.Sexe;
import Modele.requete.ObsHippocampeBdd;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListenerSaisieObsHippocampe extends ListenerObs implements Initializable{

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
    private DatePicker date;
    @FXML
    private ComboBox<EspeceHippocampe> espece;
    @FXML
    private Label erreurValue;
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
    private TextField taille;
    @FXML
    private TextField temperature;

    private ArrayList<Observateur> listDesObs;
    private ObsHippocampeBdd hippoBdd;

    /**
     * Ajoute une observation d'hippocampe à la base de données
     * @param event ActionÉvénement
     */
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

        String g = gestant.getSelectionModel().getSelectedItem();
        boolean estGestant = false;
        if(g.equals("OUI")){
            estGestant = true;
        }

        if(taille.getText().contains(".") && temperature.getText().contains(".")){
            double ta = Double.parseDouble(taille.getText());
            double te = Double.parseDouble(taille.getText());
            ObsHippocampe obsH = new ObsHippocampe(id, da, t, l, listDesObs, ta, estGestant, peche.getSelectionModel().getSelectedItem()
                                                    , espece.getSelectionModel().getSelectedItem(), sexe.getSelectionModel().getSelectedItem(),
                                                     te);

            hippoBdd.insertOneHippocampe(obsH);
        }else if(taille.getText().contains(",") && temperature.getText().contains(",")){
            String tempTaille = taille.getText().replace(",", ".");
            String tempTemperature = temperature.getText().replace(",", ".");
            double ta = Double.parseDouble(tempTaille);
            double te = Double.parseDouble(tempTemperature);
            ObsHippocampe obsH = new ObsHippocampe(id, da, t, l, listDesObs, ta, estGestant, peche.getSelectionModel().getSelectedItem()
                                                    , espece.getSelectionModel().getSelectedItem(), sexe.getSelectionModel().getSelectedItem(),
                                                     te);

            hippoBdd.insertOneHippocampe(obsH);
        }else{
            erreurValue.setVisible(true);
        }
        
    }

    /**
     * Ajoute un observateur pour une observation d'hippocampe
     * 
     * @param event l'ActionEvent qui a déclenché le gestionnaire
     */
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

    /**
     * Change la scène à celle spécifiée dans le paramètre
     * Retourne à la page précédente
     * @param event l'événement qui a déclenché la méthode
     */
    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }


    /**
     * Obtiens les valeurs des spinners et de les mets dans un nouvel objet de la classe
     * ObsHippocampe
     * 
     * @param location l'emplacement du fichier FXML
     * @param resources Faisceau de ressources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listDesObs = new ArrayList<Observateur>();
        espece.getItems().addAll(EspeceHippocampe.values());
        espece.getSelectionModel().select(0);

        peche.getItems().addAll(Peche.values());
        peche.getSelectionModel().select(0);

        sexe.getItems().addAll(Sexe.values());
        sexe.getSelectionModel().select(0);

        gestant.getItems().add("OUI");
        gestant.getItems().add("NON");
        gestant.getSelectionModel().select(0);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        valueFactory.setValue(00);
        heure.setValueFactory(valueFactory);

        SpinnerValueFactory<Integer> val = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        val.setValue(00);
        minute.setValueFactory(val);

        hippoBdd = new ObsHippocampeBdd();
        
    }

    /**
     * Cette fonction est utilisée pour définir la liste des observateurs.
     * 
     * @param o la liste des observateurs
     */
    @Override
    public void setListDesObs(ArrayList<Observateur> o) {
        this.listDesObs = o; 
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
            lu.load(Utilitaire.getCurrentNameUser(), "SaisieHippocampe");
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
