package Controller;

import java.io.IOException;
import Modele.donnee.ObsLoutre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListenerObsLoutre {

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
    private Label titre;

    @FXML
    private Label Y;

    @FXML
    private Label commune;

    @FXML
    private Label date;

    @FXML
    private Label heure;

    @FXML
    private Label indice;

    @FXML
    private Label lieuDit;

    @FXML
    private Button menu;


    @FXML
    private Button retour;

    @FXML
    private Button visu;

    private Utilitaire util = new Utilitaire();
    private ObsLoutre laLoutre;

    /**
     * Lorsque le bouton est cliqué, change la scène en scène ListeObsLoutre.
     * @param event l'événement qui a déclenché l'action
     */
    @FXML
    void retourliste(ActionEvent event) {
        util.changeScene("ListeObsLoutre");
    }


    /**
     * Ouvre une nouvelle fenêtre et charge un nouveau fichier FXML
     * 
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
            o.load(laLoutre);
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
     * Prend un objet ObsLoutre comme paramètre, puis définit le texte d'un groupe de TextViews sur
     * les valeurs de l'objet ObsLoutre
     * 
     * @param l ObsLoutre est un objet qui contient toutes les informations sur l'observation.
     */
    void load(ObsLoutre l){
        titre.setText(titre.getText() + l.getId());
        laLoutre = l;
        date.setText(l.getDate().toString());
        if(l.getHeure() == null){
            heure.setText(heure.getText() +  " Heure non renseigné");
        }else{
            heure.setText(heure.getText() + " " + l.getHeure().toString());
        }
        
        Y.setText(Y.getText() + " " + Double.toString(l.getLieu().getyCoord()));
        X.setText(X.getText() + " " + Double.toString(l.getLieu().getxCoord()));
        lieuDit.setText(lieuDit.getText()+ " "+ l.getLieuDit());
        commune.setText(commune.getText() + " " + l.getCommune());
        indice.setText(indice.getText() + " " +  l.getIndice().toString());

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
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsLoutre");
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
