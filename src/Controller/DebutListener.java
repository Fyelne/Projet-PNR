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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private PasswordField mdp;
    //Add listener to mdp when enter is pressed



    @FXML
    private TextField user; // field for identifiant

    @FXML
    private Label wrong; // label who is show when the password is incorect

    @FXML
    private Button connectButton; // button to connect

    
    private LogBDD l = new LogBDD("jdbc:mysql://localhost/bd_PNR", "PNR", "PNR");
    private Connection c = l.connexion();
    



    @FXML
    /**
     * To check password and username / if they are good, you can connect
     * Change the scene if it's good
     * @param event Event
     */
    void connect(ActionEvent event) {
        try {
            String requete = "SELECT * FROM `utilisateur` WHERE prenomUtilisateur = \'" + user.getText() +"\'";
            System.out.println(requete);
            PreparedStatement  stmt = c.prepareStatement(requete);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                System.out.println(res.getString("mdpUtilisateur"));
                if(res.getString("mdpUtilisateur").equals(mdp.getText())){
                    Stage newStage = new Stage();
                    Parent r;
                    try {
                        FXMLLoader loader  = new FXMLLoader(getClass().getResource("..\\View\\frame\\LoadBis.fxml"));
                        r = loader.load();
                        LoadBis controle = loader.getController();
                        controle.myFonction(true, user.getText());
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
                    Scene sc = connectButton.getScene();
                    Stage st = (Stage) sc.getWindow();
                    st.close();
                }else{
                    wrong.setVisible(true);
                }
            } else {
                wrong.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    @FXML
    /**
     * To check password and username / if they are good, you can connect
     * Change the scene if it's good
     * @param event Event
     */
    void enterPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            connect(null);
        }
    }


}
