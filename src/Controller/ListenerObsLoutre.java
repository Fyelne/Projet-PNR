package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Modele.donnee.ObsLoutre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Button retour;

    @FXML
    private Button visu;

    private Utilitaire util = new Utilitaire();
    private ObsLoutre laLoutre;

    @FXML
    void retourliste(ActionEvent event) {
        util.changeScene("ListeObsLoutre");
    }

    @FXML
    void openMenu(ActionEvent event) {

    }

    @FXML
    void visuobs(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("..\\View\\frame\\VisualisationObservateur.fxml"));
            r = loader.load();
            ListenerVisuObs o = loader.getController();
            o.load(laLoutre);
            Scene s = new Scene(r);
            newStage.setTitle("Affiche des observateur");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();
            
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    void openUserMenu(ActionEvent event) {

    }

    void load(ObsLoutre l){
        laLoutre = l;
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
