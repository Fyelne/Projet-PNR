package Controller;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.*;

import Modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

//Listener for the start of the program until connection
public class DebutListener {

    //Ecran de chargement 
    private Utilitaire util = new Utilitaire();
    //passer a la page de connexion
    @FXML
    /**
     * To change the scene when the "connexion" button is clicked
     * @param event Event
     */
    void conClicked(ActionEvent event) {
        
        util.changeScene("Connexion", event);
        
    }



    // password

    @FXML
    private PasswordField mdp; // field mdp

    @FXML
    private TextField user; // field for identifiant

    @FXML
    private Label wrong; // label who is show when the password is incorect

    @FXML
    /**
     * To check password and username / if they are good, you can connect
     * Change the scene if it's good
     * @param event Event
     */
    void connect(ActionEvent event) {

        LogBDD l = new LogBDD("jdbc:mysql://localhost/bd_PNR", "PNR", "PNR");
        Connection c = l.connexion();
        try {
            String requete = "SELECT * FROM `utilisateur` WHERE prenomUtilisateur = \'" + user.getText() +"\'";
            System.out.println(requete);
            PreparedStatement  stmt = c.prepareStatement(requete);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                System.out.println(res.getString("mdpUtilisateur"));
                if(res.getString("mdpUtilisateur").equals(mdp.getText())){
                    Stage newStage = new Stage();
                    Parent r;
                    try {
                        r = FXMLLoader.load(getClass().getResource("..\\View\\frame\\Accueil.fxml"));
                        Scene s = new Scene(r);
                        newStage.setTitle("Accueil");
                        newStage.setScene(s);
                        newStage.setMaximized(true);
                        newStage.show();
                        newStage.centerOnScreen();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                    //fermeture de la page de connexion
                    Button bt = (Button) event.getSource();
                    Scene sc = bt.getScene();
                    Stage st = (Stage) sc.getWindow();
                    st.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        /*
        String username = user.getText();
        String password = mdp.getText();
        if(username.equals("hugo") && password.equals("123456")){
            Stage newStage = new Stage();
            Parent r;
            try {
                r = FXMLLoader.load(getClass().getResource("..\\View\\frame\\Accueil.fxml"));
                Scene s = new Scene(r);
                newStage.setTitle("Accueil");
                newStage.setScene(s);
                newStage.setMaximized(true);
                newStage.show();
                newStage.centerOnScreen();
            }catch (IOException e) {
                e.printStackTrace();
            }
            //fermeture de la page de connexion
            Button bt = (Button) event.getSource();
            Scene sc = bt.getScene();
            Stage st = (Stage) sc.getWindow();
            st.close();
        }else{
            wrong.setVisible(true);
        }
        */
    }
   



}
