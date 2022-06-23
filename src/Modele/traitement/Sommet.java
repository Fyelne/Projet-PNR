package Modele.traitement;

import java.sql.*;

import Modele.donnee.*;

public class Sommet {
    private int id;
    private Lieu coordLieu;
    private Date date;
    private EspeceObservee espece;
    
    public Sommet(int id, Lieu coord, Date date, EspeceObservee espece) {
        this.id = id;
        this.coordLieu = coord;
        this.date = date;
        this.espece = espece;
    }

    public Sommet(Observation obs) {
        this.id = obs.getId();
        this.coordLieu = obs.getLieu();
        this.date = obs.getDate();
        this.espece = obs.especeObs();
    }

    public double calculeDist(Sommet som) {
        double ret = -1;
        if(som != null) {
            ret = Math.sqrt(Math.pow(this.coordLieu.getxCoord() - som.getCoordLieu().getxCoord(), 2) 
            + Math.pow(this.coordLieu.getyCoord() - som.getCoordLieu().getyCoord(), 2));
        } else {
            throw new IllegalArgumentException("Sommet null");
        }

        return ret;
    }

    public int getId() {
        return id;
    }

    public Lieu getCoordLieu() {
        return this.coordLieu;
    }

    public Date getDate(){
        return this.date;
    }

    public EspeceObservee getEspece(){
        return this.espece;
    }
}
