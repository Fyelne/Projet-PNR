/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import Modele.Singleton;
import javafx.beans.property.*;
import javafx.scene.control.*;
import Modele.donnee.Utilisateur;

public class UtilisateurBdd {
    private String nom;
    private String prenom;
    private String telephone;
    private ComboBox<String> droitsCB;
    private Button supprimer;
    private Connection con;
    
 
    public UtilisateurBdd() {
        con = Singleton.getInstance().getConnection(); 
    }
 
    public ArrayList<Utilisateur> builder(ResultSet r){
        ArrayList<Utilisateur> ret = new ArrayList<Utilisateur>();
        //Construction des objets
        try {
            while(r.next()){
                // Creation lieu 
                int id = r.getInt("idUtilisateur");
                String nom = r.getString("nomUtilisateur");
                String prenom = r.getString("prenomUtilisateur");
                String telephone = r.getString("telephone");
                boolean estAdmin = false;
                if(r.getInt("estAdmin") == 1){
                    estAdmin = true;
                }


                if(!nom.equals("Utilisateur supprimé")){
                    Utilisateur user = new Utilisateur(id, nom, prenom, telephone, estAdmin);
                    ret.add(user);
                }
            
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ret;
    }


    public ResultSet getAllUtilisateurToBuild(){
        ResultSet ret = null;
        String req  = "SELECT Utilisateur.* "+
        "FROM `Utilisateur`" +
        "ORDER BY nomUtilisateur DESC ";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ResultSet getFilteredUtilisateur(String nom, String prenom){
        ResultSet ret = null;
        String req  = "SELECT Utilisateur.* "+
        "FROM `Utilisateur` " +
        "WHERE nomUtilisateur LIKE '%" + nom + "%' " +
        "AND prenomUtilisateur LIKE '%" + prenom + "%' "+
        "ORDER BY nomUtilisateur DESC ";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
