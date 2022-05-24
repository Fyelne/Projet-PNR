package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ListenerAccueil {
    @FXML
    private AnchorPane Menu;

    @FXML
    private Button menu;

    

    @FXML
    private Button quit;

    @FXML
    private Button user;

    private Utilitaire util = new Utilitaire();

    //@FXML
    //private Label username;

    @FXML
    void openMenu(ActionEvent event) {
        
        Menu.setVisible(true);

    }

    @FXML
    void quitMenu(ActionEvent event) {
        Menu.setVisible(false);
    }

    @FXML
    void openUserMenu(ActionEvent event) {
        util.changeScene("InfoUser", event);
    }

}
