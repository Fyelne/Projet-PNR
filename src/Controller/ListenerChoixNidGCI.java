package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.text.AbstractDocument.BranchElement;

import Modele.Singleton;
import Modele.donnee.NidGCI;
import Modele.donnee.RaisonArretObs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListenerChoixNidGCI {

    @FXML
    private ComboBox<RaisonArretObs> arr;

    @FXML
    private CheckBox arret;

    @FXML
    private Label enTete;

    @FXML
    private VBox toShow;

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
            e.printStackTrace();
        }
        if(arret.isSelected()){
            RaisonArretObs r = arr.getSelectionModel().getSelectedItem();
            String raison = "";
            switch(r){
                case ENVOL:
                    raison = "Envol";
                    break;
                case INCONNU:
                    raison = "Inconnu";
                    break;
                case MAREE:
                    raison = "Maree";
                    break;
                case PIETINEMENT:
                    raison = "Pietinement";
                    break;
                case PREDATION:
                    raison = "Predation";
                    break;
                default:
                    raison = "Inconnu";
                    break;
            }
            String upda = "UPDATE nid_gci SET raisonArretObservation = '"+ raison 
                         + "' WHERE idNid = " + ret +"; "; 
            System.out.println(upda);
            try {
                PreparedStatement s = con.prepareStatement(upda);
                s.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Utilitaire util = new Utilitaire();
            util.changeScene("ChoixAjouter");
            

        }else{
            this.l.setLeNid(ret);
        }
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
            e.printStackTrace();
        }

        plage.getItems().addAll(lesPlages);
        arr.getItems().addAll(RaisonArretObs.values());

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


    
    @FXML
    void is(ActionEvent event) {
        boolean s = arret.isSelected();
        System.out.println(s);
        if(s){
            toShow.setVisible(true);
        }else{
            toShow.setVisible(false);
        }
    }
}
