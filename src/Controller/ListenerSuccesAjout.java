package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class ListenerSuccesAjout implements Initializable{    

    private Utilitaire util = new Utilitaire();

    @FXML
    void openUserMenu(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("TestLoc");
        util.changeScene("ChoixAjouter");


        
        
    }



}
