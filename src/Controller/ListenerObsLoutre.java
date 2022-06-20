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

public class ListenerObsLoutre {
    @FXML
    private Label X;

    @FXML
    private Label Y;

    @FXML
    private Label commune;

    @FXML
    private Label date;

    @FXML
    private Label heure;

    @FXML
    private Label indice;

    @FXML
    private Label lieuDit;

    @FXML
    private Button menu;

    @FXML
    private Button user;

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
        System.out.println(l.getLieuDit());
        date.setText(l.getDate().toString());
        if(l.getHeure() == null){
            heure.setText(heure.getText() +  " Heure non renseigné");
        }else{
            heure.setText(heure.getText() + " " + l.getHeure().toString());
        }
        
        Y.setText(Y.getText() + " " + Double.toString(l.getLieu().getyCoord()));
        X.setText(X.getText() + " " + Double.toString(l.getLieu().getxCoord()));
        lieuDit.setText(lieuDit.getText()+ " "+ l.getLieuDit());
        commune.setText(commune.getText() + " " + l.getCommune());
        indice.setText(indice.getText() + " " +  l.getIndice().toString());

    }


}
