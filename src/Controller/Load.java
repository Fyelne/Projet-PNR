package Controller;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Load{    

    /**
     * Change la scène après un délai de 5 secondes
     * 
     * @param event L'événement qui a été déclenché.
     */
    @FXML
    void charge(MouseEvent event) {
        try {
            for(int i = 0; i < 50 ; i ++){
                Thread.sleep(10);
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BorderPane bt = (BorderPane) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        try {
            // change the scene
            System.out.println(getClass().getResource("/"));
            root = FXMLLoader.load(getClass().getResource("/frame/Debut.fxml"));
            sc.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
