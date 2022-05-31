package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.LogBDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ListenerUtilisateur {
    @FXML
    private Label adresse;
    @FXML
    private Button btPass;
    @FXML
    private PasswordField checkPass;
    @FXML
    private Button deco;
    @FXML
    private Label enTete;
    @FXML
    private Label nb_Obs;
    @FXML
    private PasswordField newPass;
    @FXML
    private Label nom;
    @FXML
    private PasswordField oldPass;
    @FXML
    private Label prenom;
    @FXML
    private Button quit;
    @FXML
    private Label role;
    @FXML
    private Label since;
    @FXML
    private Label tel;
    @FXML
    private Label notif;

    private String pagePrec;
    private String name;

    private LogBDD bd = new LogBDD("jdbc:mysql://localhost/bd_PNR", "PNR", "PNR");
    private Connection log = bd.connexion();

    private Utilitaire util = new Utilitaire();
    @FXML
    void quitMenu(ActionEvent event) {
        util.changeScene(this.pagePrec, event);
    }

    @FXML
    void deconnexion(ActionEvent event) {
        util.changeScene("Connexion", event);
    }

    @FXML
    void changePass(ActionEvent event) {
        String old = this.oldPass.getText();
        String n = this.newPass.getText();
        String check = this.checkPass.getText();
        
        if(old != null && n != null && check != null){
            String pass = "";
            System.out.println("test1");
            try{
                String requete = "SELECT * FROM `utilisateur` WHERE prenomUtilisateur = \'" + name +"\'";
                PreparedStatement  stmt = log.prepareStatement(requete);
                ResultSet res = stmt.executeQuery();
                while(res.next()){
                    pass = res.getString("mdpUtilisateur");
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

            if(old.equals(pass)){
                System.out.println("test2");
                if(n.equals(check)){
                    System.out.println("OK");
                    String r = "UPDATE utilisateur SET mdpUtilisateur = \'" + n + "\' WHERE prenomUtilisateur = \'" + name +"\'";
                    System.out.println(r);
                    try{
                        PreparedStatement  st = log.prepareStatement(r);
                        st.executeUpdate();
                        String save = notif.getText();
                        notif.setText("Mot de passe modifier avec succès");
                        oldPass.setText("");
                        newPass.setText("");
                        checkPass.setText("");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        notif.setText(save);
                        
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else{
                System.out.println("Erreur du mot de passe");
            }

        }
    }

    void load(String name, String pagePrec){
        if(pagePrec != null && name != null){
            this.pagePrec = pagePrec;
            this.name = name;
        }
        

        try{
            String requete = "SELECT * FROM `utilisateur` WHERE prenomUtilisateur = \'" + name +"\'";
            System.out.println(requete);
            PreparedStatement  stmt = log.prepareStatement(requete);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                prenom.setText(prenom.getText() + " " + res.getString("prenomUtilisateur"));
                nom.setText(nom.getText() + " " + res.getString("nomUtilisateur"));
                tel.setText(tel.getText() + " " + res.getString("telephone"));
                adresse.setText(adresse.getText() + " " + res.getString("adresse"));
                enTete.setText(name);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }

    

}
