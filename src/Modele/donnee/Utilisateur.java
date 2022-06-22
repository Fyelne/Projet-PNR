package Modele.donnee;

import java.sql.Date;

public class Utilisateur {
    private int idUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private boolean estAdmin;

    public Utilisateur(int id, String nom, String prenom, boolean isAdmin) {
        if ((id > 0) && (nom != null) && (isAdmin == true || isAdmin == false)) {
            this.idUtilisateur = id ;
            this.nomUtilisateur = nom ;
            this.prenomUtilisateur = prenom ;
            this.estAdmin = isAdmin ;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }
}
