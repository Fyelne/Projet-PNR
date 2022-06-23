package Controller;

import Modele.donnee.Ouverture;
import Modele.donnee.Pente;
import Modele.donnee.TypeMare;
import Modele.donnee.ZoneHumide;
import Modele.requete.ZoneHumideBdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ListenerGestionZh {

    @FXML
    private ComboBox<TypeMare> mare;

    @FXML
    private ComboBox<Ouverture> ouverture;

    @FXML
    private ComboBox<Pente> pente;

    @FXML
    private TextField prof;

    @FXML
    private TextField surface;

    @FXML
    private ComboBox<String> tempo;

    @FXML
    private Button val;

    private ListenerObs listenerO;
    private ZoneHumideBdd zBdd = new ZoneHumideBdd();

    /**
     * Prend les valeurs des champs de texte et des listes déroulantes, crée un nouvel objet
     * ZoneHumide et le transmet à l'objet listenerO
     * 
     * @param event l'événement qui a déclenché la méthode
     */
    @FXML
    void valide(ActionEvent event) {
        int id = zBdd.giveIdZh();
        String t = tempo.getSelectionModel().getSelectedItem();
        boolean te = false;
        if(t.equals("OUI")){
            te = true;
        }
        double p = Double.parseDouble(prof.getText());
        double sur =  Double.parseDouble(surface.getText());
        if(prof.getText().contains(",") && surface.getText().contains(",")){
            String tempProf = prof.getText().replace(",", ".");
            String tempSur = surface.getText().replace(",", ".");
            p = Double.parseDouble(tempProf);
            sur = Double.parseDouble(tempSur);
        }

        ZoneHumide laZh = new ZoneHumide(id, te, p, sur, mare.getSelectionModel().getSelectedItem()
                                            , pente.getSelectionModel().getSelectedItem()
                                            , ouverture.getSelectionModel().getSelectedItem());

        listenerO.setZoneHumide(laZh);
        Button bt = (Button) event.getSource();
        Stage st = (Stage) bt.getScene().getWindow();
        st.close();
    }


    /**
     * La fonction getControl() est appelée par la classe ListenerObs, et elle est utilisée pour
     * initialiser les ComboBoxes de l'IHM
     * 
     * @param o AuditeurObs
     */
    public void getControl(ListenerObs o){
        this.listenerO = o;

        mare.getItems().addAll(TypeMare.values());
        ouverture.getItems().addAll(Ouverture.values());
        pente.getItems().addAll(Pente.values());
        tempo.getItems().add("OUI");
        tempo.getItems().add("NON");
    }

    
}
