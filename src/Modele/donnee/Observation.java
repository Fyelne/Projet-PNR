package Modele.donnee;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public abstract class Observation {
    int idObs;
    Date dateObs;
    Time heureObs;
    Lieu lieuObs;
    ArrayList<Observateur> lesObservateurs;
    EspeceObservee espece;

    /**
     * Constructeur de la classe Observation
     * @param id id de l'observation
     * @param date date de l'observation
     * @param heure heure de l'observation
     * @param lieu lieu de l'observation
     * @param observateurs liste des observateurs
     */
    public Observation(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs) {
        if(id >= 0 && date != null && lieu != null && observateurs != null) {
            this.idObs = id;
            this.dateObs = date;
            this.heureObs = heure;
            this.lieuObs = lieu;
            this.lesObservateurs = observateurs;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Ajoute un observateur à l'observation
     * @param o observateur à ajouter
     */
    public void ajouteObservateur(Observateur o){
        if(o != null) {
            this.lesObservateurs.add(o);
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Retire un observateur de l'observation
     * @param idObservateur id de l'observateur à retirer
     */
    public void retireObservateur(int idObservateur){
        boolean trouve = false;
        int i = 0;
        while(!trouve && i < this.lesObservateurs.size()) {
            if(this.lesObservateurs.get(i).getId() == idObservateur) {
                trouve = true;
            } else {
                i++;
            }
        }
    }

    /**
     * Retourne l'espece observée
     * @return espece observée
     */
    public abstract EspeceObservee especeObs();

    /**
     * Retourne l'id de l'observation
     * @return id de l'observation
     */
    public int getId() {
        return this.idObs;
    }

    /**
     * Retourne la date de l'observation
     * @return date de l'observation
     */
    public Date getDate() {
        return this.dateObs;
    }

    /**
     * Retourne l'heure de l'observation
     * @return heure de l'observation
     */
    public Time getHeure() {
        return this.heureObs;
    }

    /**
     * Retourne le lieu de l'observation
     * @return lieu de l'observation
     */
    public Lieu getLieu() {
        return this.lieuObs;
    }

    /**
     * Retourne la liste des observateurs
     * @return liste des observateurs
     */
    public ArrayList<Observateur> getObservateurs() {
        return this.lesObservateurs;
    }

    /**
     * Modifie l'id de l'observation
     * @param id nouvel id de l'observation
     */
    public void setId(int id) {
        if(id >= 0) {
            this.idObs = id;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie la date de l'observation
     * @param date nouvelle date de l'observation
     */
    public void setDate(Date date) {
        if(date != null) {
            this.dateObs = date;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie l'heure de l'observation
     * @param heure nouvelle heure de l'observation
     */
    public void setHeure(Time heure) {
        if(heure != null) {
            this.heureObs = heure;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }

    /**
     * Modifie le lieu de l'observation
     * @param lieu nouveau lieu de l'observation
     */
    public void setLieu(Lieu lieu) {
        if(lieu != null) {
            this.lieuObs = lieu;
        } else {
            throw new IllegalArgumentException("Parametres invalides");
        }
    }
}
