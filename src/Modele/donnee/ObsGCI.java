package Modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsGCI extends Observation {
    private int nombre;
    private ContenuNid natureNid ;
    private boolean presentMaisNonObs;
    private int leNid;

    /**
     * Constructeur de la classe ObsGCI
     * @param id id de l'observation
     * @param date date de l'observation
     * @param heure heure de l'observation
     * @param lieu lieu de l'observation
     * @param observateurs observateurs de l'observation
     * 
     * @param leNombre nombre d'oiseaux observés
     */
    public ObsGCI(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, ContenuNid nature, int leNombre, boolean present, int leNid) {
        super(id, date, heure, lieu, observateurs);
        if(leNombre >= 0 && nature != null) {
            this.natureNid = nature;
            this.nombre = leNombre;
            this.presentMaisNonObs = present;
            this.leNid = leNid;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Retourne l'espece observée
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.GCI;
    }

    /**
     * Retourne le nombre d'oiseaux observés
     * @return nombre d'oiseaux observés
     */
    public int getNombre() {
        return this.nombre;
    }

    /**
     * Retourne le contenu du nid
     * @return contenu du nid
     */
    public ContenuNid getNatureNid() {
        return this.natureNid;
    }

    /**
     * Modifie le contenu du nid
     * @param natureNid contenu du nid
     */
    public void setNatureNid(ContenuNid natureNid) {
        if(natureNid != null) {
            this.natureNid = natureNid;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie le nombre d'oiseaux observés
     * @param nombre nombre d'oiseaux observés
     */
    public void setNombre(int nombre) {
        if(nombre >= 0) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Donne si présent ou pas 
     * @return true si présent sinon, false
     */
    public boolean getPresentMaisNonObs(){
        return this.presentMaisNonObs;
    }

    /**
     * modifie le present mais non observer 
     * @param present valeur à mettre 
     */
    public void setPresentMaisNonObs(boolean present){
        this.presentMaisNonObs = present;
    }

    /**
     * donne le nid auquel appartient l'obs
     * @return l'id du nid 
     */
    public int getLeNid(){
        return this.leNid;
    }

    /**
     * modifie le nid auquel appartient l'obs du nid
     * @param idNid valeur pour le nouveau nid
     */
    public void setLeNid(int idNid){
        this.leNid = idNid;
    }
}
