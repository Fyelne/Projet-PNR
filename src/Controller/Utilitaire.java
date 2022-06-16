package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;

public class Utilitaire {

    //To change easily the frame
    public  void changeScene(String file, ActionEvent event){
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "..//View//frame//" + file + ".fxml";
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
