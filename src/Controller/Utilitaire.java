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

    //To change easily the frame
    public  void changeScene(String file, Button bt) {
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "..//View//frame//" + file + ".fxml";
        try {
            // change the scene
            root = FXMLLoader.load(getClass().getResource(url));
            sc.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setScene(Scene st){
        actualScene = st;
    }

    public static void setCurrentUser(int id){
        idUtilisateurCourant = id; 
        Connection con = Singleton.getInstance().getConnection();
        String req = "SELECT prenomUtilisateur FROM utilisateur WHERE idUtilisateur = " + idUtilisateurCourant + ";";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ResultSet res = stmt.executeQuery();
            res.next();
            currentUsername = res.getString("prenomUtilisateur");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }


    public static int getCurrentUser(){
        return idUtilisateurCourant;
    }

    public static String getCurrentNameUser(){
        System.out.println(currentUsername);
        return currentUsername;
    }

    public static void setCurrentNameUser(String name){
        if(name != null){
            System.out.println(name + "/");
            currentUsername = name;
        }
        
    }

}
