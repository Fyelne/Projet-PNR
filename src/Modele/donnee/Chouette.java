package Modele.donnee;

import java.util.ArrayList;

public class Chouette implements IObs<ObsChouette>{
    private String idChouette;
    private ArrayList<ObsChouette> lesObservations;
    private EspeceChouette espece;
    private Sexe sexe;

    /**
     * Constructeur de la classe Chouette
     * @param id id de la chouette
     * @param leSexe sexe de la chouette
     * @param lEspece espece de la chouette
     */
    public Chouette(String id, Sexe leSexe, EspeceChouette lEspece) {
        if(id != null && leSexe != null && lEspece != null) {
            this.idChouette = id;
            this.lesObservations = new ArrayList<ObsChouette>();
            this.espece = lEspece;
            this.sexe = leSexe;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Ajoute une observation à la liste des observations
     * @param obs observation à ajouter
     */
    public void ajouteUneObs(ObsChouette obs) {
        if(obs != null) {
            this.lesObservations.add(obs);
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Ajoute plusieurs observations à la liste des observations
     * @param obs observations à ajouter
     */
    public void ajoutePlsObs(ArrayList<ObsChouette> obs) {
        if(obs != null) {
            this.lesObservations.addAll(obs);
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Vide la liste d'observations
     */
    public void videObs() {
        this.lesObservations.clear();
    }

    /**
     * Retire une observation de la liste des observations
     * @param idObs id de l'observation
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
     * Retourne l'id de la chouette
     * @return l'id de la chouette
     */
    public String getId() {
        return this.idChouette;
    }

    /**
     * Retourne l'espece de la chouette
     * @return l'espece de la chouette
     */
    public EspeceChouette getEspece() {
        return this.espece;
    }

    /**
     * Retourne le sexe de la chouette
     * @return le sexe de la chouette
     */
    public Sexe getSexe() {
        return this.sexe;
    }

    /**
     * Retourne les observations de la chouette
     * @return les observations de la chouette
     */
    public ArrayList<ObsChouette> getObservations() {
        return this.lesObservations;
    }

    /**
     * Modifie l'id de la chouette
     * @param id id de la chouette
     */
    public void setId(String id) {
        if(id != null) {
            this.idChouette = id;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie l'espece de la chouette
     * @param espece espece de la chouette
     */
    public void setEspece(EspeceChouette espece) {
        if(espece != null) {
            this.espece = espece;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie le sexe de la chouette
     * @param sexe le sexe de la chouette
     */
    public void setSexe(Sexe sexe) {
        if(sexe != null) {
            this.sexe = sexe;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }
}
