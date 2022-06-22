package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import Modele.donnee.ContenuNid;
import Modele.donnee.Lieu;
import Modele.donnee.NidGCI;
import Modele.donnee.ObsGCI;
import Modele.donnee.Observateur;
import Modele.requete.ObsGCIBdd;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListenerSaisieObsGCI extends ListenerObs {

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
    private Label intitule;

    private ArrayList<Observateur> listDesObs = new ArrayList<Observateur>();
    private NidGCI lNid;

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
        String res = present.getSelectionModel().getSelectedItem();
        boolean pres = false;
        if(res.equals("OUI")){
            pres = true;
        }

        ObsGCI g = new ObsGCI(id, da, t, l, listDesObs, cont, nb.getValue(), pres);

        ObsGCIBdd gBdd = new ObsGCIBdd();
        gBdd.insertOneInto(g, lNid.getId());
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
        intitule.setText("Observation sur le nid " + nid.getId() + " situé sur la plage " + nid.getNomPlage());
        this.lNid = nid;
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
    }

    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }

    @Override
    public void setListDesObs(ArrayList<Observateur> o) {
        // TODO Auto-generated method stub
        
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
        
        String url = "..//View//frame//InfoUser.fxml";
        try {
            // change the scene
            FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
            root = fx.load();
            ListenerUtilisateur lu = fx.getController();
            lu.load(Utilitaire.getCurrentNameUser(), "SaisieObsGCI");
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
