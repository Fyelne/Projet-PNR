package Modele.donnee;

public class Observateur {
    private int idObs;
    private String nom;
    private String prenom;

    /**
     * Constructeur de la classe Observateur
     * @param id id de l'observateur
     * @param leNom nom de l'observateur
     * @param lePrenom prenom de l'observateur
     */
    public Observateur(int id, String leNom, String lePrenom) {
        if(id >= 0 && lePrenom != null) {
            this.idObs = id;
            this.nom = leNom;
            this.prenom = lePrenom;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Retourne l'id de l'observateur
     * @return id de l'observateur
     */
    public int getId() {
        return this.idObs;
    }

    /**
     * Retourne le nom de l'observateur
     * @return nom de l'observateur
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne le prenom de l'observateur
     * @return le prenom de l'observateur
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Modifie l'id de l'observateur
     * @param id id de l'observateur
     */
    public void setId(int id) {
        if(id >= 0) {
            this.idObs = id;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie le nom de l'observateur
     * @param leNom nom de l'observateur
     */
    public void setNom(String leNom) {
        if(leNom != null) {
            this.nom = leNom;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie le prenom de l'observateur
     * @param lePrenom prenom de l'observateur
     */
    public void setPrenom(String lePrenom) {
        if(lePrenom != null) {
            this.prenom = lePrenom;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }


}
