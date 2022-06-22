package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import Modele.Singleton;
import Modele.donnee.ObsBatracien;
import Modele.donnee.Vegetation;
import Modele.requete.VegetationBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ListenerVisuVege {
    private Utilitaire util = new Utilitaire();

    @FXML
    private Label description;

    @FXML
    private Label natVege;

    @FXML
    private Button retour;

    @FXML
    private Label titre;

    @FXML
    private Label typeVege;

    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("AffichageObservationBatracien");
    }


    public void getControl(ObsBatracien batra) {
        String vegetation = "SELECT * FROM ObsBatracien, Vegetation WHERE concerne_vege = decritLieu" ;
        
        Connection con = Singleton.getInstance().getConnection();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(vegetation);
            ResultSet rs = stmt.executeQuery();
            VegetationBdd vgBdd = new VegetationBdd();
            ArrayList<Vegetation> listVege = vgBdd.builder(rs);
            for (Vegetation ve : listVege) {
                System.out.println(ve.getId());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        /* 
        natVege.setText(natVege.getText() + " " + vege.getNatureVege());
        typeVege.setText(typeVege.getText() + " " + vege.getVege());
        description.setText(description.getText() + " " + vege.getDecritLieu());*/
    }

}
