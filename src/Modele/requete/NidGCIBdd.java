package Modele.requete;

import java.sql.*;
import java.util.ArrayList;


import Modele.Singleton;
import Modele.donnee.NidGCI;
import Modele.donnee.ObsGCI;
import Modele.donnee.RaisonArretObs;

public class NidGCIBdd {
    private Connection con;

    public NidGCIBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public void insertOneInto(NidGCI n){
        int idNid = n.getId();
        int nbEnvol = n.getNbEnvol();
        String nomPlage = n.getNomPlage().toUpperCase();
        
        
        boolean p = n.getProtection();
        int protection = 0;
        if(p){
            protection = 1;
        }

        String bagueMale = n.getBagueMale();
        String bagueFemelle = n.getBagueFemelle();

        String req1 = "INSERT INTO nid_gci VALUES(" + idNid + " , '" + nomPlage + "' , " + null + " , " 
                        + nbEnvol + " , " + protection + " , '" + bagueMale + "' , '" + bagueFemelle + "');";

        try {
            PreparedStatement stmt = con.prepareStatement(req1);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<ObsGCI> lesObs = n.getObservations();
        ObsGCIBdd obsBdd = new ObsGCIBdd();
        for(ObsGCI obs : lesObs){
            obsBdd.insertOneInto(obs, idNid);
        }
    }




    public int getIdNid(){
        int ret = 0;
        String req = "SELECT MAX(idNid) FROM nid_gci;";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ResultSet res = stmt.executeQuery();
            res.next();
            ret = res.getInt("MAX(idNid)") + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
