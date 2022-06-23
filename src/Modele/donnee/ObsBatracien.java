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
    private MeteoCiel meteoCiel;
    private MeteoTemp meteoTemp;
    private MeteoVent meteoVent;
    private MeteoPluie meteoPluie;


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
    public ObsBatracien(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, int[] resObs, EspeceBatracien lEspece, int temp, MeteoCiel metCiel, MeteoTemp metTemp, MeteoVent metVent, MeteoPluie metPluie) {
        super(id, date, heure, lieu, observateurs);
        if ( (resObs != null) && (lEspece != null) && (resObs.length == 4) && (metCiel != null) && (metTemp != null) && (metVent != null) && (metPluie != null) ){
            this.nombreAdultes = resObs[0];
            this.nombreAmplexus = resObs[1];
            this.nombreTetard = resObs[2];
            this.nombrePonte = resObs[3];
            this.espece = lEspece;
            this.temperature = temp;
            this.meteoCiel = metCiel;
            this.meteoTemp = metTemp;
            this.meteoVent = metVent;
            this.meteoPluie = metPluie;

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

    /**
     * donne la température
     * @return la température
     */
    public int getTemperature(){
        return this.temperature;
    }

    /**
     * modifie la valeur de la température
     * @param temp valeur de la température
     */
    public void setTemperature(int temp){
        this.temperature = temp;
    }

    /**
     * donne la météo
     * @return la météo
     */
    public MeteoCiel getMeteoCiel(){
        return this.meteoCiel;
    }

    /**
     * Modifie l'indice de météo du ciel
     * @param metCiel nouvel indice de météo du ciel
     */
    public void setMeteoCiel(MeteoCiel metCiel) {
        if (metCiel != null) {
            this.meteoCiel = metCiel;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * donne la météo
     * @return la meteo
     */
    public MeteoTemp getMeteoTemp(){
        return this.meteoTemp;
    }
    
    /**
     * Modifie l'indice de météo de temperature
     * @param metTemp nouvel indice de météo de temperature
     */
    public void setMeteoTemp(MeteoTemp metTemp) {
        if (metTemp != null) {
            this.meteoTemp = metTemp;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Donne la météo du vent 
     * @return meteoVent
     */
    public MeteoVent getMeteoVent(){
        return this.meteoVent;
    }

    /**
     * Modifie l'indice de météo du vent
     * @param metVent nouvel indice de météo du vent
     */
    public void setMeteoVent(MeteoVent metVent) {
        if (metVent != null) {
            this.meteoVent = metVent;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * donne la météo pluie 
     * @return la météo plue
     */
    public MeteoPluie getMeteoPluie(){
        return this.meteoPluie;
    }

    /**
     * Modifie l'indice de météo de la pluie
     * @param metPluie nouvel indice de météo de la pluie
     */
    public void setMeteoPluie(MeteoPluie metPluie) {
        if (metPluie != null) {
            this.meteoPluie = metPluie;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }
}
