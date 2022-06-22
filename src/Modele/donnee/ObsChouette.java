package Modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsChouette extends Observation {
    private TypeObservation typeObs;
    private boolean protocole;
    String numIndividu;
    

    /**
     * Constructeur de la classe ObsChouette
     * @param id id de l'observation
     * @param date date de l'observation
     * @param heure heure de l'observation
     * @param lieu lieu de l'observation
     * @param observateurs observateurs de l'observation
     * @param type type de l'observation
     */
    public ObsChouette(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, TypeObservation type, boolean prot, String num) {
        super(id, date, heure, lieu, observateurs);
        if(type != null) {
            this.typeObs = type;
            this.protocole = prot;
            this.numIndividu = num;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Retourne l'espece observée
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.CHOUETTE;
    }

    /**
     * Retourne le type de l'observation
     * @return type de l'observation
     */
    public TypeObservation getTypeObs() {
        return typeObs;
    }

    /**
     * Modifie le type de l'observation
     * @param typeObs nouveau type de l'observation
     */
    public void setTypeObs(TypeObservation typeObs) {
        if(typeObs != null) {
            this.typeObs = typeObs;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    public boolean getProtocole(){
        return this.protocole;
    }

    public void setProtocole(boolean prot){
        this.protocole = prot;
    } 

    public String getNumIndividu(){
        return this.numIndividu;
    }

    public void setNumIndivdu(String num){
        if(null != null){
            this.numIndividu = num;
        }
    }
}
