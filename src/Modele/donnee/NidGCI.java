package Modele.donnee;

import java.util.ArrayList;
import java.sql.Date;

public class NidGCI implements IObs<ObsGCI>{
    private int idNid;
    private int nbEnvol = 0;
    private String nomPlage;
    private ArrayList<ObsGCI> lesObservations;
    private RaisonArretObs raisonArretObs;
    private boolean protection;
    private String bagueMale;
    private String bagueFemelle;

    /**
     * Constructeur de la classe NidGCI
     * @param id id du nid
     * @param plage nom de la plage
     */
    public NidGCI(int id, String plage, boolean protect, String bagueM, String bagueF) {
        if(id >= 0 && plage != null) {
            this.idNid = id;
            this.nomPlage = plage;
            this.lesObservations = new ArrayList<ObsGCI>();
            this.raisonArretObs = null;
            this.protection = protect;
            this.bagueMale = bagueM;
            this.bagueFemelle = bagueF;

        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Constructeur de la classe NidGCI
     * @param id id du nid
     * @param plage nom de la plage
     */
    public NidGCI(int id, String plage, boolean protect) {
        if(id >= 0 && plage != null) {
            this.idNid = id;
            this.nomPlage = plage;
            this.lesObservations = new ArrayList<ObsGCI>();
            this.raisonArretObs = null;
            this.protection = protect;
            this.bagueMale = null;
            this.bagueFemelle = null;

        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Retourne la date de la première observation
     * @return Date
     */
    public Date dateDebutObs(){
        Date date = null;
        if(this.lesObservations.size() > 0) {
            date = this.lesObservations.get(0).getDate();
        }

        return date;
    }

    /**
     * Retourne la date de la dernière observation
     * @return Date
     */
    public Date dateFinObs(){
        Date date = null;
        if(this.lesObservations.size() > 0) {
            date = this.lesObservations.get(this.lesObservations.size() - 1).getDate();
        }

        return date;
    }

    /**
     * Ajoute une observation à la liste des observations
     * @param obs observation à ajouter
     */
    public void ajouteUneObs(ObsGCI obs) {
        if(obs != null) {
            this.lesObservations.add(obs);
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Ajoute plusieurs observations à la liste des observations
     * @param obs liste d'observations à ajouter
     */
    public void ajoutePlsObs(ArrayList<ObsGCI> obs) {
        if(obs != null) {
            this.lesObservations.addAll(obs);
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Vide la liste des observations
     */
    public void videObs() {
        this.lesObservations.clear();
    }

    /**
     * Retire une observation de la liste des observations
     * @param idObs id de l'observation à retirer
     */
    public boolean retireObs(int idObs){
        boolean trouve = false;
        int i = 0;
        while(!trouve && i < this.lesObservations.size()) {
            if(this.lesObservations.get(i).getId() == idObs) {
                this.lesObservations.remove(i);
                trouve = true;
            } else {
                i++;
            }
        }

        return trouve;
    }

    /**
     * Retourne le nombre d'observations
     * @return le nombre d'observations
     */
    public int nbObs() {
        return this.lesObservations.size();
    }

    /**
     * Retourne l'id du nid
     * @return l'id du nid
     */
    public int getId() {
        return this.idNid;
    }

    /**
     * Retourne le nom de la plage
     * @return le nom de la plage
     */
    public String getNomPlage() {
        return this.nomPlage;
    }

    /**
     * Retourne le nombre d'envol
     * @return le nombre d'envol
     */
    public int getNbEnvol() {
        return this.nbEnvol;
    }

    /**
     * Retourne la liste des observations
     * @return la liste des observations
     */
    public ArrayList<ObsGCI> getObservations() {
        return this.lesObservations;
    }

    /**
     * Modifie le nombre d'envol
     * @param nb nombre d'envol
     */
    public void setNbEnvol(int nb) {
        if(nb >= 0) {
            this.nbEnvol = nb;
        } else {
            throw new IllegalArgumentException("Parametre invalide");
        }
    }

    /**
     * Modifie le nom de la plage
     * @param nom nom de la plage
     */
    public void setNomPlage(String nom) {
        if(nom != null) {
            this.nomPlage = nom;
        } else {
            throw new IllegalArgumentException("Parametre invalide");
        }
    }

    /**
     * Modifie l'id du nid
     * @param id id du nid
     */
    public void setIdNid(int id) {
        if(id >= 0) {
            this.idNid = id;
        } else {
            throw new IllegalArgumentException("Parametre invalide");
        }
    }

    public RaisonArretObs getRaisonArretObs(){
        return this.raisonArretObs;
    }

    public void setRaisonArretObs(RaisonArretObs r){
        this.raisonArretObs = r;
    }

    public boolean getProtection(){
        return this.protection;
    }

    public void setProtection(boolean p){
        this.protection = p;
    }

    public String getBagueMale(){
        return this.bagueMale;
    }

    public void setBagueMale(String bM){
        this.bagueMale = bM;
    }

    public String getBagueFemelle(){
        return this.bagueFemelle;
    }

    public void setBagueFemelle(String bF){
        this.bagueFemelle = bF;
    }
}
