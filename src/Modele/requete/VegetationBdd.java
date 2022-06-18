package Modele.requete;

import java.sql.Connection;

import Modele.Singleton;

public class VegetationBdd {
    private Connection con;

    public VegetationBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public void insertOneInto(){
        
    }

}
