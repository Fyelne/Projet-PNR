package Controller;

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
import javafx.fxml.Initializable;
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

    @FXML
    void goToAccueil(ActionEvent event) {

    }

    @FXML
    void goToAddDonnee(ActionEvent event) {

    }

    @FXML
    void goToAdmin(ActionEvent event) {

    }

    @FXML
    void goToChoixReleve(ActionEvent event) {

    }

    @FXML
    void openMenu(ActionEvent event) {

    }

    @FXML
    void openUserMenu(ActionEvent event) {

    }

    @FXML
    void quitMenu(ActionEvent event) {

    }

    @FXML
    void retourcons(ActionEvent event) {
        util.changeScene("ListeObsGravelot");
    }

    @FXML
    void showNid(MouseEvent event) {

    }

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
