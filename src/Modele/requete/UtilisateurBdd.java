/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
package Modele.requete;

import java.sql.*;
import Controller.SQLConnect;
import javafx.beans.property.*;
import javafx.scene.control.*;

public class Utilisateur {
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty email;
    private ComboBox<String> droits;
    private Button supprimer;
    
 
    public Utilisateur(String nom, String prenom, String email, String value) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.droits = new ComboBox<String>(){
            {
                getItems().addAll("Administrateur", "Utilisateur");
                setValue(value);
            }
        };

        this.supprimer = new Button("Supprimer"){
            {
                setOnAction(e -> {
                    System.out.println("Suppression de l'utilisateur " + getNom() + " " + getPrenom());
                    setText("Supprimé");
                    setDisable(true);
                    getDroits().setDisable(true);
                    try {
                        SQLConnect.connect().createStatement().executeUpdate(
                            "UPDATE Observateur SET nom = 'Utilisateur supprimé', prenom = NULL WHERE nom = '" + getNom() + "' AND prenom = '" + getPrenom() + "'");
                    } catch (SQLException ex) {
                        System.out.println("Erreur lors de la suppression de l'utilisateur");
                    }
                });
            }
        };        
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.Utilisateur;

public class UtilisateurBdd {
    private Connection con;

    public UtilisateurBdd() {
        con = Singleton.getInstance().getConnection();
    }

    public ArrayList<Utilisateur> builder (ResultSet r) {
        ArrayList<Utilisateur> ret = new ArrayList<Utilisateur>();
        //Construction des objets
        try {
            while (r.next()) {
                int id = r.getInt("idUtilisateur");

                String nom = r.getString("nomUtilisateur");
                String prenom = r.getString("prenomUtilisateur");

                int e =  r.getInt("estAdmin");
                boolean isAdmin = false;
                if (e == 1) {
                    isAdmin = true;
                }

                Utilisateur utilisateur = new Utilisateur(id, nom, prenom, isAdmin) ;
                ret.add(utilisateur);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }   

    public boolean ajouterUtilisateur(String nom, String prenom, String mdp, String telephone, String adresse, boolean admin) {
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO utilisateur (nomUtilisateur, prenomUtilisateur, mdpUtilisateur, telephone, adresse, estAdmin, dateCreation)"
                + " VALUES ('" + nom + "', '" + prenom + "', '" + mdp + "', '" + telephone + "', '" + adresse + "'," + admin + ", DEFAULT)";
            stmt.executeUpdate(sql);
            sql = "CREATE USER '" + nom + "'@'localhost' IDENTIFIED BY '" + mdp + "'";
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerUtilisateur(String idUtilisateur) {
        try {
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM utilisateur WHERE idUtilisateur = " + idUtilisateur;
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ajouterRole(String idUtilisateur, String idRole) {
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO utilisateur_role (idUtilisateur, idRole)"
                + " VALUES (" + idUtilisateur + ", " + idRole + ")";
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.getErrorCode();
            return false;
        }
    }

    public static void main(String[] args) {

        // Utilisateur u = new Utilisateur();
        // u.ajouterUtilisateur("Dupont", "Jean", "mdp", "0612345678", "1 rue de la paix", false);
        
    }
 
    public String getNom() {
        return this.nom.get();
    }
    public void setNom(String nom) {
        try {
            SQLConnect.connect().createStatement().executeUpdate(
                "UPDATE Observateur SET nom = '" + nom + "' WHERE nom = '" + getNom() + "' AND prenom = '" + getPrenom() + "'");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du nom de l'utilisateur");
        }
        this.nom.set(nom);
    }
        
    public String getPrenom() {
        return this.prenom.get();
    }
    public void setPrenom(String prenom) {
        try {
            SQLConnect.connect().createStatement().executeUpdate(
                "UPDATE Observateur SET prenom = '" + prenom + "' WHERE nom = '" + getNom() + "' AND prenom = '" + getPrenom() + "'");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du prenom de l'utilisateur");
        }
        this.prenom.set(prenom);
    }
    
    public String getEmail() {
        return this.email.get();
    }
    public void setEmail(String nom) {
        this.email.set(nom);
    }
    

 public ComboBox<String> getDroits() {
        return this.droits;
    }
 
    public void setDroits(ComboBox<String> droits) {
        this.droits = droits;
    }
    
    public Button getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }
    
}
*/