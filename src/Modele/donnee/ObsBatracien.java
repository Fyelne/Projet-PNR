package Modele.donnee;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsBatracien extends Observation {
    private int nombreAdultes;
    private int nombreAmplexus;
    private int nombreTetard;
    private int nombrePonte;
    private EspeceBatracien espece;
    private int temperature;
    private String[] meteo;


    /**
     * Constructeur de la classe ObsBatracien
     * @param id id de l'observation
     * @param date date de l'observation
     * @param heure heure de l'observation
     * @param lieu lieu de l'observation
     * @param observateurs observateurs de l'observation
     * @param resObs resultats de l'observation
     * @param lEspece espece de l'observation
     */
    public ObsBatracien(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, int[] resObs, EspeceBatracien lEspece, int temp, String[] met){
        super(id, date, heure, lieu, observateurs);
        if(resObs != null && lEspece != null && resObs.length == 4 && met.length == 4) {
            this.nombreAdultes = resObs[0];
            this.nombreAmplexus = resObs[1];
            this.nombreTetard = resObs[2];
            this.nombrePonte = resObs[3];
            this.espece = lEspece;
            this.meteo = met;
            this.temperature = temp;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Retourne l'espece de l'observation
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.BATRACIEN;
    }

    /**
     * Retourne le nombre d'adultes
     * @return nombre d'adultes
     */
    public int getNombreAdultes() {
        return nombreAdultes;
    }

    /**
     * Retourne le nombre d'amplexus
     * @return nombre d'amplexus
     */
    public int getNombreAmplexus() {
        return nombreAmplexus;
    }

    /**
     * Retourne le nombre de tetards
     * @return nombre de tetards
     */
    public int getNombreTetard() {
        return nombreTetard;
    }

    /**
     * Retourne le nombre de ponte
     * @return nombre de ponte
     */
    public int getNombrePonte() {
        return nombrePonte;
    }

    /**
     * Retourne l'espece du batracien
     * @return espece du batracien
     */
    public EspeceBatracien getEspece() {
        return espece;
    }

    /**
     * Modifie le nombre d'adultes
     * @param nombreAdultes nombre d'adultes
     */
    public void setNombreAdultes(int nombreAdultes) {
        if(nombreAdultes >= 0) {
            this.nombreAdultes = nombreAdultes;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie le nombre d'amplexus
     * @param nombreAmplexus nombre d'amplexus
     */
    public void setNombreAmplexus(int nombreAmplexus) {
        if(nombreAmplexus >= 0) {
            this.nombreAmplexus = nombreAmplexus;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie le nombre de tetards
     * @param nombreTetard nombre de tetards
     */
    public void setNombreTetard(int nombreTetard) {
        if(nombreTetard >= 0) {
            this.nombreTetard = nombreTetard;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie le nombre de ponte
     * @param nombrePonte nombre de ponte
     */
    public void setNombrePonte(int nombrePonte) {
        if(nombrePonte >= 0) {
            this.nombrePonte = nombrePonte;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie l'espece du batracien
     * @param espece espece du batracien
     */
    public void setEspece(EspeceBatracien espece) {
        if(espece != null) {
            this.espece = espece;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    public int getTemperature(){
        return this.temperature;
    }

    public void setTemperature(int temp){
        this.temperature = temp;
    }

    public String[] getMeteo(){
        return this.meteo;
    }

    public void setMeteo(String[] met){
        this.meteo = met;
    }
}
