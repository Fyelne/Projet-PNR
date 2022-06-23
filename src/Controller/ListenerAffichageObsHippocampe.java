package Controller;

import java.io.IOException;

import Modele.donnee.ObsHippocampe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ListenerAffichageObsHippocampe {

    @FXML
    private Label X;
    @FXML
    private Label Y;
    @FXML
    private Label date;
    @FXML
    private Label esp;
    @FXML
    private Label gestant;
    @FXML
    private Label heure;
    @FXML
    private Button menu;
    @FXML
    private Label peche;
    @FXML
    private Label sexe;
    @FXML
    private Label taille;
    @FXML
    private Button user;
    @FXML
    private Button visu;
    private ObsHippocampe obsH;
    private Utilitaire util = new Utilitaire();

    /**
     * Retourne à la page précédente
     * @param event l'événement qui a déclenché la méthode
     */
    @FXML
    void goback(ActionEvent event) {
        util.changeScene("ListeObsHippocampe");
    }

    /**
     * Affiche le menu
     * @param event le bouton cliqué
     */
    @FXML
    void openMenu(ActionEvent event) {

    }

    /**
     * Permet d'acceder au menu qui donne des informations sur l'utilisateur 
     * @param event le bouton cliqué
     */
    @FXML
    void openUserMenu(ActionEvent event) {

    }

    /**
     * Ouvre la fenêtre des observateur(s) lié(s) à l'observation de batracien en cours.
     * Créé une nouvelle étape, charge un nouveau fichier FXML, obtient le contrôleur de ce fichier
     * FXML, appelle une méthode sur ce contrôleur, puis affiche l'étape
     * @param event L'événement qui a déclenché l'action.
     */
    @FXML
    void visuobs(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/VisualisationObservateur.fxml"));
            r = loader.load();
            ListenerVisuObs o = loader.getController();
            o.load(obsH);
            Scene s = new Scene(r);
            newStage.setTitle("Affiche des observateur");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();
                        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prend un objet ObsHippocampe et affiche ses attributs dans les TextViews correspondants
     * @param h ObsHippocampe
     */
    public void load(ObsHippocampe h){
        X.setText(X.getText() + " " + h.getLieu().getxCoord());
        Y.setText(Y.getText() + " " + h.getLieu().getyCoord());

        esp.setText(esp.getText() + " " + h.getEspece());
        date.setText(date.getText() + " " + h.getDate());
        heure.setText(heure.getText() + " " + h.getHeure());
        taille.setText(taille.getText() + " " + h.getTaille());
        sexe.setText(sexe.getText() + " " + h.getSexe());
        String estGestant = "Non";
        if(h.getEstGestant()){
            estGestant = "Oui";
        }
        gestant.setText(gestant.getText() + " " + estGestant);
        peche.setText(peche.getText() + " " + h.getTypePeche());
        this.obsH = h;
    }

}
