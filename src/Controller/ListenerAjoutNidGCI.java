package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Modele.donnee.NidGCI;
import Modele.requete.NidGCIBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class ListenerAjoutNidGCI implements Initializable{

    @FXML
    private TextField bFemelle;
    @FXML
    private TextField bMale;
    @FXML
    private TextField plage;
    @FXML
    private ComboBox<String> protect;
    @FXML
    private Button val;
    private NidGCIBdd nidBdd;

    /**
     * Prend les données des champs de texte et de la boîte de choix et les insère dans la base de
     * données
     * @param event l'événement qui a déclenché la méthode
     */
    @FXML
    void valide(ActionEvent event) {
        int id = nidBdd.getIdNid();
        String pl = plage.getText();
        String bM = bMale.getText();
        String bF = bFemelle.getText();
        String pr = protect.getSelectionModel().getSelectedItem();
        boolean protection = false;
        if(pr.equals("OUI")){
            protection = true;
        }
        NidGCI nid;
        if(bM != null && bF != null){
            nid = new NidGCI(id, pl, protection, bM, bF);
        }else{
            nid = new NidGCI(id, pl, protection);
        }

        nidBdd.insertOneInto(nid);

        ajoutReussi("Le nid a été ajouté avec succès");

        Button bt = (Button) event.getSource();
        Stage st = (Stage) bt.getScene().getWindow();
        st.close();
    }

    
   /**
    * Essaie d'ajouter deux éléments à un ComboBox.
    * @param location l'emplacement du fichier FXML
    * @param resources Faisceau de ressources
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        protect.getItems().add("OUI");
        protect.getItems().add("NON");
        nidBdd = new NidGCIBdd();
    }

    /**
     * Créer un popup qui confirme l'ajout de l'observation
     * @param message Le message à afficher.
     */
    public void ajoutReussi(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(message);
        alert.setContentText(message);
        alert.showAndWait();
    }




}
