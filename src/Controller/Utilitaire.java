package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.Singleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;


public class Utilitaire {
    private static int idUtilisateurCourant;
    private static String currentUsername;
    private static Scene actualScene;
    private static boolean estAdmin = false; 

    //To change easily the frame
    /**
     * Change la scène à celle spécifiée dans le paramètre
     * 
     * @param file le nom du fichier que vous souhaitez modifier.
     */
    public void changeScene(String file) {
        
        Parent root;
        try {
            // change the scene
            root = FXMLLoader.load(getClass().getResource("/frame/" + file + ".fxml"));
            actualScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goUserMenu(Button bt){
        
    }

    public static void setScene(Scene st){
        actualScene = st;
    }

    /**
     * Définit l'identifiant, le nom d'utilisateur et le statut d'administrateur de l'utilisateur
     * actuel
     * 
     * @param id l'identifiant de l'utilisateur
     */
    public static void setCurrentUser(int id){
        idUtilisateurCourant = id; 
        Connection con = Singleton.getInstance().getConnection();
        String req = "SELECT * FROM utilisateur WHERE idUtilisateur = " + idUtilisateurCourant + ";";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ResultSet res = stmt.executeQuery();
            res.next();
            currentUsername = res.getString("prenomUtilisateur");
            int admin = res.getInt("estAdmin");
            
            if(admin == 1){
                estAdmin = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Cette fonction retourne la valeur de la variable estAdmin
     * @return la variable estAdmin
     */
    public static boolean getEstAdmin(){
        return estAdmin;
    }

    /**
     * Cette fonction renvoie l'identifiant de l'utilisateur courant
     * 
     * @return L'identifiant de l'utilisateur actuel.
     */
    public static int getCurrentUser(){
        return idUtilisateurCourant;
    }

    /**
     * Renvoie le nom d'utilisateur actuel.
     * @return Le nom d'utilisateur actuel.
     */
    public static String getCurrentNameUser(){
        return currentUsername;
    }

    /**
     * Si le nom n'est pas nul, cette méthode imprime le nom et définit le nom d'utilisateur actuel sur le nom.
     * @param name Le nom d'utilisateur
     */
    public static void setCurrentNameUser(String name){
        if(name != null){
            System.out.println(name + "/");
            currentUsername = name;
        }
    }

}
