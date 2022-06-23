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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListenerAffichageObsHippocampe {

    @FXML
    private HBox HMenu;
    @FXML
    private Button quit;
    @FXML
    private Button user;
    @FXML
    private Button admin;

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
    private Label titre;

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

        titre.setText(titre.getText() + h.getId());
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

    //code du Menu
    /**
     * Affiche le menu
     * @param event le bouton cliqué
     */
    @FXML
    void openMenu(ActionEvent event) {
        HMenu.setVisible(true);
    }

    /**
     * Quitte le menu
     * @param event le bouton cliqué
     */
    @FXML
    void quitMenu(ActionEvent event) {
        HMenu.setVisible(false);
    }

    /**
     * Permet d'acceder au menu qui donne des informations sur l'utilisateur 
     * @param event le bouton cliqué
     */
    @FXML
    void openUserMenu(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "/frame/InfoUser.fxml";
        try {
            // change the scene
            FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
            root = fx.load();
            ListenerUtilisateur lu = fx.getController();
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsHippocampe");
            sc.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de changer de scene et d'acceder à la page Admin
     * @param event le bouton cliqué
     */
    @FXML
    void goToAdmin(ActionEvent event) {
        util.changeScene("Admin");
    }

    /**
     * Permet de changer de scene et d'acceder à la page Accueil
     * @param event le bouton cliqué
     */
    @FXML
    void goToAccueil(ActionEvent event) {
        util.changeScene("Accueil");
    }

    /**
     * Permet de changer de scene et d'acceder à la page ChoixAjouter
     * @param event le bouton cliqué
     */
    @FXML
    void goToAddDonnee(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }

    /**
     * Permet de changer de scene et d'acceder à la page Consultation
     * @param event le bouton cliqué
     */
    @FXML
    void goToChoixReleve(ActionEvent event) {
        util.changeScene("Consultation");
    }

}
