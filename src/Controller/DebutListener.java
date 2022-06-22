package Controller;
import java.sql.*;

import Modele.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
        
        util.changeScene("Connexion", (Button) event.getSource());
        
    }



    // password

    @FXML
    private PasswordField mdp; // field mdp

    @FXML
    private TextField user; // field for identifiant

    @FXML
    private Label wrong; // label who is show when the password is incorect

    @FXML
    private Button connectButton;

    
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
            while(res.next()){
                System.out.println(res.getString("mdpUtilisateur"));
                if(res.getString("mdpUtilisateur").equals(mdp.getText())){
                        Utilitaire.setCurrentNameUser(user.getText());
                        util.changeScene("Accueil", connectButton);
                        
                        
                }else{
                    wrong.setVisible(true);
                }
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
