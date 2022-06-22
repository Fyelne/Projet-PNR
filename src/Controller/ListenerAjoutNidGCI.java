package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Modele.donnee.NidGCI;
import Modele.requete.NidGCIBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        protect.getItems().add("OUI");
        protect.getItems().add("NON");
        nidBdd = new NidGCIBdd();
    }

    public String ajoutReussi(String message) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Ajout d'un nid GCI");
        dialog.setHeaderText(message);
        Optional<String> nomObs = dialog.showAndWait();
        String result = nomObs.get();
        return result;
    }




}
