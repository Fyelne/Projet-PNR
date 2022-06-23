package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.PreparableStatement;

import Modele.donnee.NidGCI;
import Modele.requete.NidGCIBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ListenerAfficheAllNid implements Initializable{

    @FXML
    private HBox HMenu;

    @FXML
    private Button admin;

    @FXML
    private TableColumn<NidGCI, Integer> id;

    @FXML
    private Button menu;

    @FXML
    private TableColumn<NidGCI, Integer> nombre;

    @FXML
    private TableColumn<NidGCI, String> plage;

    @FXML
    private TableColumn<NidGCI, Boolean> prot;

    @FXML
    private Button quit;

    @FXML
    private TableView<NidGCI> tab;

    @FXML
    private Button user;

    private NidGCIBdd nBdd;

    private Utilitaire util;

    /**
     * Permet d'acceder à l'accueil
     * @param event le bouton cliqué
     */
    @FXML
    void goToAccueil(ActionEvent event) {
        util.changeScene("Accueil");
    }

    /**
     * Permet d'acceder au menu d'ajout de données
     * @param event le bouton cliqué
     */
    @FXML
    void goToAddDonnee(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }
    
    /**
     * Permet d'acceder au menu admin
     * @param event le bouton cliqué
     */
    @FXML
    void goToAdmin(ActionEvent event) {
        util.changeScene("Admin");
    }

    /**
     * Permet d'acceder au menu des choix de relevé
     * @param event le bouton cliqué
     */
    @FXML
    void goToChoixReleve(ActionEvent event) {
        util.changeScene("Consultation");
    }


    /**
     * Affiche le menu
     * @param event le bouton cliqué
     */
    @FXML
    void openMenu(ActionEvent event) {

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
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsLoutre");
            sc.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Quitte le menu
     * @param event le bouton cliqué
     */
    @FXML
    void quitMenu(ActionEvent event) {

    }

    /**
     * Lorsque le bouton est cliqué, change la scène en scène Consultation
     * @param event l'événement qui a déclenché l'action
     */
    @FXML
    void retourcons(ActionEvent event) {
        util.changeScene("Consultation");
    }

    /**
     * Essaie d'afficher les données de ma base de données dans un tableau
     * @param location l'emplacement du fichier FXML
     * @param resources Faisceau de ressources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nBdd = new NidGCIBdd();
        util = new Utilitaire();
        ArrayList<NidGCI> lesNids = nBdd.builder(nBdd.getAllNidGci());
        ObservableList<NidGCI> tr = FXCollections.observableArrayList(lesNids);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        plage.setCellValueFactory(new PropertyValueFactory<>("nomPlage"));

        prot.setCellValueFactory(new PropertyValueFactory<>("protection"));

        nombre.setCellValueFactory(new PropertyValueFactory<>("nbEnvol"));

        tab.setItems(tr);
    }

}
