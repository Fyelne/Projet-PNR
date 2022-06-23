package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Modele.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ListenerAddUser implements Initializable{

    @FXML
    private TextField adresse;
    @FXML
    private PasswordField conf;
    @FXML
    private Label error;
    @FXML
    private PasswordField mdp;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private ChoiceBox<String> role;
    @FXML
    private TextField telephone;
    @FXML
    private Button user;
    private Utilitaire util; 
    private Connection con = Singleton.getInstance().getConnection();
    

    @FXML
    void NewUser(ActionEvent event) {   
        String pre = prenom.getText();
        String no = nom.getText();
        String m = mdp.getText();
        String tel = telephone.getText();
        String a = adresse.getText();
        String d = role.getValue();
        int admin = 0;
        if(d.equals("Administrateur")){
            admin = 1;
        }
        String req = "INSERT INTO utilisateur(nomUtilisateur,prenomUtilisateur,mdpUtilisateur,telephone,adresse,estAdmin) VALUES ('" + 
                        no + "' , '" + pre + "' , '" + m + "' , '" + tel + "' , '" + a + "' , " + admin +  ");";

        if(m.equals(conf.getText())){
            PreparedStatement stmt;
            try {
                stmt = con.prepareStatement(req);
                stmt.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ajoutReussi("Ajout d'utilisateur réussi");
            util.changeScene("GestionUtilisateur");
        }else{
            error.setVisible(true);
        }
    }   

    
    public void ajoutReussi(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(message);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void goBack(ActionEvent event) {
        util.changeScene("GestionUtilisateur");
    }

    @FXML
    void openUserMenu(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().add("Administrateur");
        role.getItems().add("Utilisateur");
        
    }

}
