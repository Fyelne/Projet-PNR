package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.Singleton;
import Modele.donnee.ObsChouette;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ListenerAffichageObsChouette {
    private Utilitaire util = new Utilitaire();

    @FXML
    private HBox HMenu;
    @FXML
    private Button quit;
    @FXML
    private Button user;
    @FXML
    private Button admin;


    private ObsChouette obsChouette ;

    @FXML
    private Label X;

    @FXML
    private Label Y;

    @FXML
    private Label date;

    @FXML
    private Label espece;

    @FXML
    private Label heure;

    @FXML
    private Button menu;

    @FXML
    private Label numIndividu;

    @FXML
    private Label protocole;

    @FXML
    private Button retour;

    @FXML
    private Label sexe;

    @FXML
    private Label titre;

    @FXML
    private Label typeObservation;


    /**
     * Retourne à la page précédente
     * @param event l'événement qui a déclenché la méthode
     */
    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("ListeObsChouette");
    }


    /**
     * Obtient les informations de la base de données liée à l'observation de chouette en cours et les affiche dans l'interface graphique
     * @param chouette ObsChouette
     */
    public void getControl(ObsChouette chouette) {
        this.obsChouette = chouette ;

        String oChouette = "SELECT * FROM Obs_Chouette, Chouette WHERE leNumIndividu = numIndividu" ;
        
        Connection con = Singleton.getInstance().getConnection();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(oChouette);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            titre.setText(titre.getText() + obsChouette.getId());

            date.setText(obsChouette.getDate().toString());
            if(obsChouette.getHeure() == null){
                heure.setText(heure.getText() +  " Heure non renseigné");
            }else{
                heure.setText(heure.getText() + " " + obsChouette.getHeure().toString());
            }

            Y.setText(Y.getText() + " " + Double.toString(obsChouette.getLieu().getyCoord()));
            X.setText(X.getText() + " " + Double.toString(obsChouette.getLieu().getxCoord()));

            String numIndiv = rs.getString("leNumIndividu");
            String esp = rs.getString("espece");
            String sex = rs.getString("sexe");
            int proto = rs.getInt("protocole");
            String typeObs = rs.getString("typeObs");

            numIndividu.setText(numIndividu.getText() + " " + numIndiv);
            espece.setText(espece.getText() + " " + esp);
            sexe.setText(sexe.getText() + " " + sex);
            protocole.setText(protocole.getText() + " " + proto);
            typeObservation.setText(typeObservation.getText() + " " + typeObs);

        } catch (SQLException e) {
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
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsChouette");
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
