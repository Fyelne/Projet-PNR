package Modele.requete;

import java.sql.Connection;

import Modele.Singleton;

public class BatracienBdd{

    private Connection con;

    public BatracienBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    
    
}
