package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import com.mysql.cj.xdevapi.Result;

import Modele.Singleton;
import Modele.donnee.NatureVege;
import Modele.donnee.ObsBatracien;
import Modele.donnee.Vegetation;
import Modele.requete.VegetationBdd;
import javafx.stage.Stage;




public class ListenerVisuVege {


    @FXML
    private TableColumn<Vegetation, Integer> id;

    @FXML
    private TableColumn<Vegetation, NatureVege> natuVege;

    @FXML
    private Button retour;

    @FXML
    private TableView<Vegetation> table;

    @FXML
    private Label titre;

    @FXML
    private TableColumn<Vegetation, String> typeVegeta;


    @FXML
    void goBack(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Stage st = (Stage) bt.getScene().getWindow();
        st.close();
    }


    public void getControl(ObsBatracien batra) {
        String vegetation = "SELECT * FROM Obs_Batracien, Vegetation WHERE concernes_vege = idVege" ;
        
        Connection con = Singleton.getInstance().getConnection();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(vegetation);
            ResultSet rs = stmt.executeQuery();
            VegetationBdd vgBdd = new VegetationBdd();
            ArrayList<Vegetation> listVege = vgBdd.builder(rs);
            ObservableList<Vegetation> tr = FXCollections.observableArrayList(listVege);

 
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            natuVege.setCellValueFactory(new PropertyValueFactory<>("NatureVege"));
            typeVegeta.setCellValueFactory(new PropertyValueFactory<>("Vege"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

