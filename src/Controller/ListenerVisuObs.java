package Controller;


import java.sql.ResultSet;
import java.util.ArrayList;

import Modele.donnee.Observateur;
import Modele.donnee.Observation;
import Modele.requete.ObservateurBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListenerVisuObs {

    @FXML
    private TableColumn<Observateur, String> no;

    @FXML
    private TableColumn<Observateur, String> pre;

    @FXML
    private Button retour;

    @FXML
    private TableView<Observateur> table;
    private ObservateurBdd oBdd;

    /**
     * Ferme la fenêtre popup en cours
     * @param event l'événement qui a déclenché la méthode
     */
    @FXML
    void goBack(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Stage st = (Stage) bt.getScene().getWindow();
        st.close();
    }

    /**
     * Prend un objet de type Observation, obtient l'identifiant de l'observation, créé un nouvel
     * objet ObservateurBdd, obtient le résultat d'une requête, crée une ArrayList d'objets
     * Observateur, crée une ObservableList d'objets Observateur, définit la fabrique de valeurs de
     * cellule de deux colonnes , et définit les éléments d'une table sur ObservableList
     * 
     * @param obs l'objet qui est sélectionné dans la tableView
     */
    public void load(Observation obs){
        int id = obs.getId();
        oBdd = new ObservateurBdd();
        ResultSet res = Modele.requete.Utilitaire.recupObs(id);

        ArrayList<Observateur> lesObs = oBdd.builder(res);

        ObservableList<Observateur> ne = FXCollections.observableArrayList(lesObs);
        pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        no.setCellValueFactory(new PropertyValueFactory<>("nom"));
        table.setItems(ne);
    }
}
