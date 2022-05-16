import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ListenerAccueil {

    @FXML
    private Pane Menu;

    @FXML
    private Button menu;

    @FXML
    void openMenu(ActionEvent event) {
        Menu.setLayoutX(0);
    }

}
