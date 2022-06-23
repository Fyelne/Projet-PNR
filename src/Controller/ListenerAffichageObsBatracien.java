package Controller;

import java.io.IOException;

import Modele.donnee.ObsBatracien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListenerAffichageObsBatracien {
    private Utilitaire util = new Utilitaire();

    @FXML
    private HBox HMenu;
    @FXML
    private Button quit;
    @FXML
    private Button user;
    @FXML
    private Button admin;


    private ObsBatracien batra ;

    @FXML
    private Label X;

    @FXML
    private Label Y;

    @FXML
    private Label date;

    @FXML
    private Label esp;

    @FXML
    private Label heure;

    @FXML
    private Button menu;

    @FXML
    private Label nbAdulte;

    @FXML
    private Label nbAmplexus;

    @FXML
    private Label nbPonte;

    @FXML
    private Label nbTetard;

    @FXML
    private Label numObservation;

    @FXML
    private Button retour;

    @FXML
    private Label temp;

    @FXML
    private Button visuMeteo;

    @FXML
    private Button visuObservateur;

    @FXML
    private Button visuVege;

    @FXML
    private Button visuZH;

    /**
     * Change la scène à celle spécifiée dans le paramètre
     * @param event l'événement qui a déclenché la méthode
     */
    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("ListeObsBatracien");
    }



    /**
     * Il définit le texte des TextViews sur les valeurs de l'objet ObsBatracien
     * @param batra l'objet ObsBatracien que l'on veut afficher
     */
    public void getControl(ObsBatracien batra) {
        this.batra = batra ;
        date.setText(batra.getDate().toString());
        if(batra.getHeure() == null){
            heure.setText(heure.getText() +  " Heure non renseigné");
        }else{
            heure.setText(heure.getText() + " " + batra.getHeure().toString());
        }

        Y.setText(Y.getText() + " " + Double.toString(batra.getLieu().getyCoord()));
        X.setText(X.getText() + " " + Double.toString(batra.getLieu().getxCoord()));
        esp.setText(esp.getText() + " " + batra.getEspece());
        nbAdulte.setText(nbAdulte.getText() + " " + Integer.toString(batra.getNombreAdultes()));
        nbAmplexus.setText(nbAmplexus.getText() + " " + Integer.toString(batra.getNombreAmplexus()));
        nbPonte.setText(nbPonte.getText() + " " + Integer.toString(batra.getNombrePonte()));
        nbTetard.setText(nbTetard.getText() + " " + Integer.toString(batra.getNombreTetard()));
        numObservation.setText(numObservation.getText() + " " + Integer.toString(batra.getId()));
        temp.setText(temp.getText() + " " + Integer.toString(batra.getTemperature()));
    }


    /**
     * Ouvre une nouvelle fenêtre et passe une référence au contrôleur principal au contrôleur de la
     * nouvelle fenêtre.
     * @param event L'événement qui a déclenché l'action.
     */
    @FXML
    public void openVisuMeteo(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/VisualisationMeteo.fxml"));
            r = loader.load();
            ListenerVisuMeteo o = loader.getController();
            o.getControl(batra);
            Scene s = new Scene(r);
            newStage.setTitle("Visualisation de la météo ");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Ouvre la fenêtre des observateur(s) lié(s) à l'observation de batracien en cours.
     * @param event l'événement qui a déclenché l'action (un bouton en l'occurrence)
     */
    @FXML
    void openVisuObs(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/VisualisationObservateur.fxml"));
            r = loader.load();
            ListenerVisuObs o = loader.getController();
            o.load(batra);
            Scene s = new Scene(r);
            newStage.setTitle("Visualisation des différents observateurs ");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Ouvre la fenêtre des végétations liée l'observation de batracien en cours.
     * @param event L'événement qui a déclenché l'action.
     */
    @FXML
    void openVisuVege(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/VisualisationVegetation.fxml"));
            r = loader.load();
            ListenerVisuVege o = loader.getController();
            o.getControl(batra);
            Scene s = new Scene(r);
            newStage.setTitle("Visualisation des la végétation ");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Ouvre la fenêtre de la zone humide liée à l'observation de batracien en cours.
     * @param event l'événement qui a déclenché l'action (un bouton en l'occurrence)
     */
    @FXML
    void openVisuZH(ActionEvent event) {
        Stage newStage = new Stage();
        Parent r;
        try {
            FXMLLoader loader  = new FXMLLoader(getClass().getResource("/frame/VisualisationZoneHumide.fxml"));
            r = loader.load();
            ListenerVisuZH o = loader.getController();
            o.getControl(batra);
            Scene s = new Scene(r);
            newStage.setTitle("Visualisation de la zone humide ");
            newStage.setScene(s);
            newStage.show();
            newStage.centerOnScreen();

        }catch (IOException e) {
            e.printStackTrace();
        }
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
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsBatracien");
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
