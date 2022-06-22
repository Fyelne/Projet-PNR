package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ListenerSuccèsAjout{    

    private Utilitaire util = new Utilitaire();

    @FXML
    void openUserMenu(ActionEvent event) {

    }

    @FXML
    void charge(MouseEvent event) {
        try {
            for(int i = 0; i < 40 ; i ++){
                Thread.sleep(10);
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        util.changeScene("ChoixAjouter");

        
    }



}
