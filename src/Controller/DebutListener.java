package Controller;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.*;

import com.mysql.cj.util.Util;

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
        
        util.changeScene("Connexion", (Button)event.getSource());
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
            PreparedStatement  stmt = c.prepareStatement(requete);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                if(res.getString("mdpUtilisateur").equals(mdp.getText())){
                    if(res.getString("estAdmin").equals("1")){
                        util.changeScene("Admin", connectButton);
                    }
                    else{
                        util.changeScene("Accueil", connectButton);
                    }
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
