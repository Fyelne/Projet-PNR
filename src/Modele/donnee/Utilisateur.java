package Modele.donnee;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.SQLException;

import Modele.Singleton;

public class Utilisateur {
    private int idUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String telephone;
    private boolean estAdmin;
    private ComboBox<String> adminCB;
    private Button supprButton;
    Connection con;
    
    public Utilisateur(int id, String nom, String prenom, String telephone, boolean estAdmin) {
        if ((id > 0) && (nom != null)) {
            this.idUtilisateur = id ;
            this.nomUtilisateur = nom ;
            this.prenomUtilisateur = prenom;
            this.telephone = telephone;
            this.estAdmin = estAdmin;

            this.con = Singleton.getInstance().getConnection();
            this.adminCB = new ComboBox<String>(){
                {
                    getItems().addAll("Administrateur", "Utilisateur");
                    setOnAction(e -> {
                        int admin = 0;
                        if(getSelectionModel().getSelectedItem() == "Administrateur"){
                            setEstAdmin(true);
                            admin = 1;
                        } else {
                            setEstAdmin(false);
                        }
                        try {
                            con.createStatement().executeUpdate(
                                "UPDATE Utilisateur SET estAdmin = " + admin +
                                " WHERE nomUtilisateur = '" + getNom() + "' AND prenomUtilisateur = '" + getPrenom() + "'");
                        } catch (SQLException ex) {
                            System.out.println("Erreur lors de l'ajout des droits");
                        }
                    });
                }
            };

            if(this.estAdmin){
                adminCB.getSelectionModel().select("Administrateur");
            } else {
                adminCB.getSelectionModel().select("Utilisateur");
            }

            this.supprButton = new Button("Supprimer"){
                {
                    setOnAction(e -> {
                        System.out.println("Suppression de l'utilisateur " + getNom() + " " + getPrenom());
                        setText("Supprimé");
                        setDisable(true);
                        adminCB.setDisable(true);
                        try {
                            String req= "UPDATE Utilisateur SET nomUtilisateur = 'Utilisateur supprimé', " + 
                            "telephone = NULL, adresse = NULL, estAdmin = 0 " +
                            "WHERE nomUtilisateur = '" + getNom() + "' AND prenomUtilisateur = '" + getPrenom() + "';";
                            System.out.println(req);
                            con.createStatement().executeUpdate(req);
                        } catch (SQLException ex) {
                            System.out.println("Erreur lors de la suppression de l'utilisateur");
                        }
                    });
                }
            };


        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }
    
    public void setEstAdmin(boolean estAdmin){
        this.estAdmin = estAdmin;
    }

    public boolean getEstAdmin(){
        return this.estAdmin;
    }
    
    public int getId(){
        return this.idUtilisateur;
    }
    
    public String getNom(){
        return this.nomUtilisateur;
    }
    
    public String getPrenom(){
        return this.prenomUtilisateur;
    }

    public String getTelephone(){
        return this.telephone;
    }

    public ComboBox<String> getAdminCB(){
        return this.adminCB;
    }

    public Button getSupprButton(){
        return this.supprButton;
    }
}