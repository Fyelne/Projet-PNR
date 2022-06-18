package Modele.requete;

import java.sql.Connection;

import Modele.Singleton;

public class ZoneHumideBdd {
    
    private Connection con;

    public ZoneHumideBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public void insertOneInto(){

    }

    
}
