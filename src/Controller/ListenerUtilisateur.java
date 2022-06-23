package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.Singleton;
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

    private Connection log = Singleton.getInstance().getConnection();

    private Utilitaire util = new Utilitaire();
    @FXML
    void quitMenu(ActionEvent event) {
        
        util.changeScene(this.pagePrec);
    }

    @FXML
    void deconnexion(ActionEvent event) {
        util.changeScene("Connexion");
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
                        oldPass.setText("");
                        newPass.setText("");
                        checkPass.setText("");
                       //ajouter un pop up de mot de passe changer avec succès
                        
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
            String nomString = "";
            String prenomString = "";
            while(res.next()){
                nomString = res.getString("nomUtilisateur");
                prenomString = res.getString("prenomUtilisateur");
                prenom.setText(prenom.getText() + " " + prenomString);
                nom.setText(nom.getText() + " " + nomString);
                tel.setText(tel.getText() + " " + res.getString("telephone"));
                adresse.setText(adresse.getText() + " " + res.getString("adresse"));
                if(res.getString("estAdmin").equals("1")){
                    role.setText(role.getText() + " " + "Administrateur");
                } else{
                    role.setText(role.getText() + " " + "Utilisateur");
                }
                String date = res.getString("dateCreation");
                String[] dateSplit = date.split("-");
                since.setText(since.getText() + " " + dateSplit[2].split(" ")[0] + "/" + dateSplit[1] + "/" + dateSplit[0]);
                enTete.setText(name);
            }

            requete = "SELECT COUNT(*) nbObs " +
            "FROM Observateur, AObserve, Observation WHERE lObservateur = idObservateur " +
            "AND lObservation = idObs " +
            "AND nom = '" + nomString + "' " +
            "AND PRENOM = '" + prenomString + "' " +
            "GROUP BY idObservateur;";
            System.out.println(requete);

            stmt = log.prepareStatement(requete);

            res = stmt.executeQuery();
            if(res.next()){
                nb_Obs.setText(nb_Obs.getText() + " " + res.getString("nbObs"));
            } else{
                nb_Obs.setText(nb_Obs.getText() + " " + "0");
            }

            
        }catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }

    

}
