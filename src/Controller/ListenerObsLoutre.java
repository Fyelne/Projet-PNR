package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;

import Modele.donnee.ObsLoutre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ListenerObsLoutre{

    @FXML
    private Label Commune;

    @FXML
    private Label Indice;

    @FXML
    private Label LieuDit;

    @FXML
    private Label X;

    @FXML
    private Label Y;

    @FXML
    private Label date;

    @FXML
    private Label heure;

    @FXML
    private Button menu;

    @FXML
    private Button user;

    @FXML
    private MapView map;
    
    @FXML
    void connect(ActionEvent event) {

    }

    @FXML
    void openMenu(ActionEvent event) {


    }

    @FXML
    void openUserMenu(ActionEvent event) {

    }

    void load(ObsLoutre l){
        date.setText(l.getDate().toString());
        if(l.getHeure() == null){
            heure.setText("Heure non renseigné");
        }else{
            heure.setText(l.getHeure().toString());
        }
        
        Y.setText(Double.toString(l.getLieu().getyCoord()));
        X.setText(Double.toString(l.getLieu().getxCoord()));
        LieuDit.setText(l.getLieuDit());
        Commune.setText(l.getCommune());
        Indice.setText(l.getIndice().toString());

        /* 
        map.initialize();
        Coordinate c1 = new Coordinate(l.getLieu().getyCoord(), l.getLieu().getxCoord());
        map.setCenter(c1);
        */
        
    }


}
