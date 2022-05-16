package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ListenerAccueil {
    @FXML
    private Pane Menu;

    @FXML
    private Button closeUser;

    @FXML
    private Button menu;

    @FXML
    private AnchorPane menuUser;

    @FXML
    private Button quit;

    @FXML
    private Button user;

    //@FXML
    //private Label username;

    @FXML
    void openMenu(ActionEvent event) {
        Menu.setLayoutX(0);
        Menu.setVisible(true);
    }

    @FXML
    void quitMenu(ActionEvent event) {
        Menu.setVisible(false);
    }

    @FXML
    void openUserMenu(ActionEvent event) {
        menuUser.setLayoutX(0);
        menuUser.setVisible(true);
        user.setVisible(false);
    }

    @FXML
    void closeUserMenu(ActionEvent event) {
        menuUser.setLayoutX(1200);
        menuUser.setVisible(false);
        user.setVisible(true);
    }

}
