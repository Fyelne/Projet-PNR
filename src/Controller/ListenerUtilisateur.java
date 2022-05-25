package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ListenerUtilisateur {

    private String PagePrec;

    public ListenerUtilisateur(String file){
        if(file != null){
            this.PagePrec = file;
        }
    }

    @FXML
    private Button quit;
    private Utilitaire util = new Utilitaire();
    @FXML
    void quitMenu(ActionEvent event) {
        util.changeScene("Accueil", event);
    }

    

}
