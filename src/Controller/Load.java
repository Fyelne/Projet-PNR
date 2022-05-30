package Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public class Load {

    @FXML
    void charge(MouseEvent event) {
        try {
            for(int i = 0; i < 100 ; i ++){
                Thread.sleep(10);
            }
            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BorderPane bt = (BorderPane) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "..//View//frame//Debut.fxml";
        try {
            // change the scene
            root = FXMLLoader.load(getClass().getResource(url));
            sc.setRoot(root);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
