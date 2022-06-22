package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.NidGCI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ListenerChoixNidGCI {

    @FXML
    private ComboBox<String> plage;

    @FXML
    private ComboBox<Double> x;

    @FXML
    private ComboBox<Double> y;

    @FXML
    private Button val;
    private Connection con;

    private ListenerSaisieObsGCI l;

    @FXML
    void valide(ActionEvent event) {

        String req = "SELECT idNid FROM nid_gci, obs_gci, observation  WHERE idObs = ObsG AND leNid = idNid " +
                        " AND lieu_Lambert_X = " + x.getSelectionModel().getSelectedItem() 
                        + " AND lieu_Lambert_Y = " + y.getSelectionModel().getSelectedItem() 
                        + " AND nomPlage = '" + plage.getSelectionModel().getSelectedItem() + "' ;";
        System.out.println(req);
        PreparedStatement stmt;
        int ret = 0;
        try {
            stmt = con.prepareStatement(req);
            ResultSet r = stmt.executeQuery();
            r.next();
            ret = r.getInt("idNid");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.l.setLeNid(ret);
        Button bt = (Button) event.getSource();
        Stage st = (Stage) bt.getScene().getWindow();
        st.close();
        
    }

    public void load(ListenerSaisieObsGCI obs){
        this.con = Singleton.getInstance().getConnection();
        this.l = obs;
        ArrayList<String> lesPlages = new ArrayList<String>();
        String req = "SELECT DISTINCT(nomPlage) FROM nid_gci;";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                lesPlages.add(res.getString("nomPlage"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        plage.getItems().addAll(lesPlages);

    }

    
    @FXML
    void setCoord(ActionEvent event) {
        String pl = plage.getSelectionModel().getSelectedItem();
        ArrayList<Integer> lesNid = new ArrayList<Integer>();
        String req = "SELECT idNid FROM nid_GCI WHERE nomplage = '" + pl + "';";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ResultSet r = stmt.executeQuery();
            while(r.next()){
                lesNid.add(r.getInt("idNid"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<Double> coordX = new ArrayList<Double>();
        ArrayList<Double> coordY = new ArrayList<Double>();

        for(Integer i : lesNid){
            String requete = "SELECT DISTINCT(idNid), lieu_Lambert_X, lieu_Lambert_Y, MIN(dateObs) FROM observation, nid_gci, obs_gci WHERE idObs = ObsG " 
            + "AND leNid = idNid AND leNid = " + i +"; ";
            PreparedStatement s;
            try {
                s = con.prepareStatement(requete);
                ResultSet ret = s.executeQuery();
                ret.next();
                coordX.add(ret.getDouble("lieu_Lambert_X"));
                coordY.add(ret.getDouble("lieu_Lambert_Y"));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }            
        }
        ObservableList<Double> tx = FXCollections.observableArrayList(coordX);
        ObservableList<Double> ty = FXCollections.observableArrayList(coordY);

        x.getItems().addAll(tx);
        y.getItems().addAll(ty);
    }

    @FXML
    void choixX(ActionEvent event) {
        int ind = x.getSelectionModel().getSelectedIndex();
        y.getSelectionModel().select(ind);
    }

    @FXML
    void choixY(ActionEvent event) {
        int ind = y.getSelectionModel().getSelectedIndex();
        x.getSelectionModel().select(ind);
    }
}
