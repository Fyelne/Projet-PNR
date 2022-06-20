package Modele.requete;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.util.Util;

import Modele.Singleton;
import Modele.donnee.ContenuNid;
import Modele.donnee.IndiceLoutre;
import Modele.donnee.Lieu;
import Modele.donnee.NidGCI;
import Modele.donnee.ObsGCI;
import Modele.donnee.ObsLoutre;
import Modele.donnee.Observateur;
import Modele.donnee.RaisonArretObs;

public class ObsGCIBdd {
    
    private Connection con;

    public ObsGCIBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public void insertOneInto(ObsGCI g, int nid){
        Utilitaire.insertBaseObs(g);

        int nb = g.getNombre();
        ContenuNid c = g.getNatureNid();
        String contenuNid = "";
        switch(c){
            case OEUF:
                contenuNid = "oeuf";
                break;
            case POUSSIN:
                contenuNid = "poussin";
            case NID_SEUL:
                contenuNid = "nid";
            default:
                contenuNid = null;
                break;
        }
        boolean pres = g.getPresentMaisNonObs();
        int present = 0;
        if(pres){
            present = 1;
        }

        String req = "INSERT INTO obs_gci VALUES(" + g.getId() + " , '" + contenuNid + "' , " + nb + " , " 
                        + present + " , " + nid +");";  

        
        try {
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

    }
}
