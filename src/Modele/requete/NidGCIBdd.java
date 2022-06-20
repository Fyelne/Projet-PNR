package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.util.Util;

import Modele.Singleton;
import Modele.donnee.IndiceLoutre;
import Modele.donnee.Lieu;
import Modele.donnee.NidGCI;
import Modele.donnee.ObsGCI;
import Modele.donnee.ObsLoutre;
import Modele.donnee.Observateur;
import Modele.donnee.RaisonArretObs;

public class NidGCIBdd {
    private Connection con;

    public NidGCIBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public void insertOneInto(NidGCI n){
        int idNid = n.getId();
        int nbEnvol = n.getNbEnvol();
        String nomPlage = n.getNomPlage();
        RaisonArretObs r = n.getRaisonArretObs();
        String raison = "";
        switch(r){
            case ENVOL:
                raison = "envol";
                break;
            case INCONNU:
                raison = "inconnu";
                break;
            case MAREE:
                raison = "maree";
                break;
            case PIETINEMENT:
                raison = "pietinement";
                break;
            case PREDATION:
                raison = "predation";
                break;
            default:
                raison = null;
                break;
        }
        boolean p = n.getProtection();
        int protection = 0;
        if(p){
            protection = 1;
        }

        String bagueMale = n.getBagueMale();
        String bagueFemelle = n.getBagueFemelle();

        String req1 = "INSERT INTO nid_gci VALUES(" + idNid + " , '" + nomPlage + "' , '" + raison + " , " 
                        + nbEnvol + " , " + protection + " , '" + bagueMale + "' , '" + bagueFemelle + "');";

        try {
            PreparedStatement stmt = con.prepareStatement(req1);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<ObsGCI> lesObs = n.getObservations();
        ObsGCIBdd obsBdd = new ObsGCIBdd();
        for(ObsGCI obs : lesObs){
            obsBdd.insertOneInto(obs, idNid);
        }
        
    }

}
