package Modele.requete;

import java.sql.*;

import Modele.Singleton;

public class Utilisateur {
    private Connection con;
    public Utilisateur() {
        con = Singleton.getInstance().getConnection();
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
}
