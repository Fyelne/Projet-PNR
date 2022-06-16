package Modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsHippocampe extends Observation {
    private double taille;
    private boolean estGestant = false;
    private Peche typePeche;
    private EspeceHippocampe espece;
    private Sexe sexe;
    private double temperature;

    /**
     * Constructeur de la classe ObsHippocampe
     * @param id id de l'observation
     * @param date date de l'observation
     * @param heure heure de l'observation
     * @param lieu lieu de l'observation
     * @param observateurs observateurs de l'observation
     * @param laTaille taille de l'hippocampe
     * @param estGestant booléen gestant
     * @param leTypePeche type de peche
     * @param lEspece espece de l'hippocampe
     * @param leSexe sexe de l'hippocampe
     */
    public ObsHippocampe(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, double laTaille, boolean estGestant, Peche leTypePeche, EspeceHippocampe lEspece, Sexe leSexe, double temperatureEau) {
        super(id, date, heure, lieu, observateurs);
        if(laTaille >= 0 && leTypePeche != null && lEspece != null && leSexe != null) {
            this.taille = laTaille;
            this.estGestant = estGestant;
            this.typePeche = leTypePeche;
            this.espece = lEspece;
            this.sexe = leSexe;
            this.temperature = temperatureEau;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Retourne l'espece observée
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.HIPPOCAMPE;
    }

    /**
     * Retourne la taille de l'hippocampe
     * @return taille de l'hippocampe
     */
    public double getTaille() {
        return this.taille;
    }

    /**
     * Retourne si l'hippocampe est gestant
     * @return booléen gestant
     */
    public boolean getEstGestant() {
        return this.estGestant;
    }

    /**
     * Retourne le type de peche
     * @return type de peche
     */
    public Peche getTypePeche() {
        return this.typePeche;
    }

    /**
     * Retourne l'espece de l'hippocampe
     * @return espece de l'hippocampe
     */
    public EspeceHippocampe getEspece(){
        return this.espece;
    }

    /**
     * Retourne le sexe de l'hippocampe
     * @return sexe de l'hippocampe
     */
    public Sexe getSexe() {
        return this.sexe;
    }

    /**
     * Modifie la taille de l'hippocampe
     * @param taille nouvelle taille de l'hippocampe
     */
    public void setTaille(double taille) {
        if(taille >= 0) {
            this.taille = taille;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie si l'hippocampe est gestant
     * @param estGestant nouveau booléen gestant
     */
    public void setEstGestant(boolean estGestant) {
        if(sexe.equals(Sexe.MALE) || sexe.equals(Sexe.INCONNU)){
            this.estGestant = estGestant;
            if(sexe.equals(Sexe.INCONNU)){
                this.sexe = Sexe.MALE;
            }
        }
    }

    /**
     * Modifie le type de peche
     * @param typePeche nouveau type de peche
     */
    public void setTypePeche(Peche typePeche) {
        if(typePeche != null) {
            this.typePeche = typePeche;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie l'espece de l'hippocampe
     * @param espece nouvelle espece de l'hippocampe
     */
    public void setEspece(EspeceHippocampe espece) {
        if(espece != null) {
            this.espece = espece;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie le sexe de l'hippocampe
     * @param sexe nouveau sexe de l'hippocampe
     */
    public void setSexe(Sexe sexe) {
        if(sexe != null) {
            this.sexe = sexe;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    public double getTemperature(){
        return this.temperature;
    }

    public void setTemperature(double temp){
        this.temperature = temp;
    }
}
