package Controller;

import java.util.ArrayList;

import Modele.donnee.NatureVege;
import Modele.donnee.Vegetation;
import Modele.requete.VegetationBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListenerGestionVege{

    @FXML
    private Button addnew;

    @FXML
    private TableColumn<Vegetation, NatureVege> nat;

    @FXML
    private ComboBox<NatureVege> nature;
    @FXML
    private TableView<Vegetation> table;

    @FXML
    private Button val;

    @FXML
    private TableColumn<Vegetation, String> veg;

    @FXML
    private TextField vegetation;

    private VegetationBdd ob = new VegetationBdd();

    private ArrayList<Vegetation> listVege;

    private ListenerObs lelistener;


    /**
     * Ajoute une nouvelle ligne au tableau.
     * @param event ActionÉvénement
     */
    @FXML
    void ajouterNew(ActionEvent event) {
        Vegetation v = new Vegetation((ob.getIdVege() + listVege.size()), nature.getSelectionModel().getSelectedItem()
                                        , vegetation.getText(), ob.getIdDecritVege());
        this.updateTable(v);

    }

    /**
     * Lorsque l'utilisateur clique sur le bouton "Valider", la liste des légumes est envoyée à
     * l'auditeur, et la fenêtre se ferme.
     * 
     * @param event l'événement qui a déclenché la méthode
     */
    @FXML
    void valide(ActionEvent event) {
        this.lelistener.setListVege(this.listVege);
        Button bt = (Button) event.getSource();
        Stage st = (Stage) bt.getScene().getWindow();
        st.close();
    }

    /**
     * Prend un ListenerObs et un ArrayList d'objets Vegetation et définit la fabrique de valeurs de
     * cellule pour les propriétés natureVege et vege de la classe Vegetation
     * 
     * @param s ListenerObs est une interface que créée pour écouter les changements dans la
     * table.
     * @param o ArrayList of Vegetation
     */
    void getControl(ListenerObs s, ArrayList<Vegetation> o){
        this.lelistener = s;
        this.listVege = o;
        nat.setCellValueFactory(new PropertyValueFactory<>("natureVege"));
        veg.setCellValueFactory(new PropertyValueFactory<>("vege"));

        nature.getItems().addAll(NatureVege.values());
        
        this.updateTable();
    }

    /**
     * Prend la liste des objets Vegetation et les place dans une ObservableList, qui est ensuite
     * définie comme éléments de la table
     */
    private void updateTable(){
        ObservableList<Vegetation> ne = FXCollections.observableArrayList(listVege);
        table.setItems(ne);
    }


    /**
     * Ajoute un nouvel objet Vegetation à la liste listVege, puis il crée une nouvelle
     * ObservableList à partir de la liste listVege, puis il définit les éléments de la table sur la
     * nouvelle ObservableList
     * 
     * @param ve Végétation
     */
    void updateTable(Vegetation ve){
        listVege.add(ve);
        ObservableList<Vegetation> ne = FXCollections.observableArrayList(listVege);

        table.setItems(ne);
    }

}
