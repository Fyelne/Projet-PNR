package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.Singleton;
import Modele.donnee.ObsBatracien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ListenerVisuZH {

    @FXML
    private Label ouverture;

    @FXML
    private Label pente;

    @FXML
    private Label profondeur;

    @FXML
    private Button retour;

    @FXML
    private Label surface;

    @FXML
    private Label temporaire;

    @FXML
    private Label titre;

    @FXML
    private Label typeMare;

    @FXML
    void goBack(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Stage st = (Stage) bt.getScene().getWindow();
        st.close();
    }


    void getControl(ObsBatracien batra) {
        String zh = "SELECT * FROM Obs_Batracien, ZoneHumide WHERE concerne_ZH = zh_id" ;
        
        Connection con = Singleton.getInstance().getConnection();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(zh);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            int t = rs.getInt("zh_temporaire");
            String tempo = "";
            if (t == 1) {
                tempo = "OUI";
            } else {
                tempo = "NON";
            }

            String typeM = rs.getString("zh_typeMare");
            double prof = rs.getDouble("zh_profondeur");
            double surf = rs.getDouble("zh_surface");
            String pentes = rs.getString("zh_pente");
            String ouv = rs.getString("zh_ouverture");

            temporaire.setText(temporaire.getText() + tempo);
            typeMare.setText(typeMare.getText() + " " + typeM);
            profondeur.setText(profondeur.getText() + " " + prof);
            surface.setText(surface.getText() + " " + surf);
            pente.setText(pente.getText() + " " + pentes);
            ouverture.setText(ouverture.getText() + " " + ouv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
