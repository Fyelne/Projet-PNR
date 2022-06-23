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

public class ListenerAffichageObsChouette {
    private Utilitaire util = new Utilitaire();

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

    @FXML
    private Button user;


    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("ListeObsChouette");
    }

    @FXML
    void openMenu(ActionEvent event) {
    }


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
            lu.load(Utilitaire.getCurrentNameUser(), "Accueil");
            sc.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
