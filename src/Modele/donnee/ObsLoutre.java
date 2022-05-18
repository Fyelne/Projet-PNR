package Modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsLoutre extends Observation {
    private IndiceLoutre indice;

    /**
     * Constructeur de la classe ObsLoutre
     * @param id id de l'observation
     * @param date date de l'observation
     * @param heure heure de l'observation
     * @param lieu lieu de l'observation
     * @param observateurs observateurs de l'observation
     * @param lIndice indice de prospection de l'observation de la loutre
     */
    public ObsLoutre(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, IndiceLoutre lIndice) {
        super(id, date, heure, lieu, observateurs);
        if(lIndice != null) {
            this.indice = lIndice;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Retourne l'espece observée
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.LOUTRE;
    }

    /**
     * Retourne l'indice de prospection de l'observation de la loutre
     * @return indice de prospection de l'observation de la loutre
     */
    public IndiceLoutre getIndice(){
        return this.indice;
    }

    /**
     * Modifie l'indice de prospection de l'observation de la loutre
     * @param indice nouvel indice de prospection de l'observation de la loutre
     */
    public void setIndice(IndiceLoutre indice){
        if(indice != null) {
            this.indice = indice;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }
}
