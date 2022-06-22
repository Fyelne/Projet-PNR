package Controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.Singleton;
import Modele.donnee.NatureVege;
import Modele.donnee.Observateur;
import Modele.donnee.Vegetation;
import Modele.requete.VegetationBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Connection con;

    private VegetationBdd ob = new VegetationBdd();

    private ArrayList<Vegetation> listVege;

    private ListenerObs lelistener;


    @FXML
    void ajouterNew(ActionEvent event) {
        Vegetation v = new Vegetation((ob.getIdVege() + listVege.size()), nature.getSelectionModel().getSelectedItem()
                                        , vegetation.getText(), ob.getIdDecritVege());
        this.updateTable(v);

    }

    @FXML
    void valide(ActionEvent event) {
        this.lelistener.setListVege(this.listVege);
        Button bt = (Button) event.getSource();
        Stage st = (Stage) bt.getScene().getWindow();
        st.close();
    }

    void getControl(ListenerObs s, ArrayList<Vegetation> o){
        this.lelistener = s;
        this.listVege = o;
        nat.setCellValueFactory(new PropertyValueFactory<>("natureVege"));
        veg.setCellValueFactory(new PropertyValueFactory<>("vege"));

        nature.getItems().addAll(NatureVege.values());

        this.con = Singleton.getInstance().getConnection();
        
        this.updateTable();

    }

    private void updateTable(){
        ObservableList<Vegetation> ne = FXCollections.observableArrayList(listVege);
        table.setItems(ne);
    }



    void updateTable(Vegetation ve){
        listVege.add(ve);
        ObservableList<Vegetation> ne = FXCollections.observableArrayList(listVege);

        table.setItems(ne);

    }

}
