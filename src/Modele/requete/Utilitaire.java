package Modele.requete;

import java.sql.*;

import Modele.Singleton;
import Modele.donnee.*;

public class Utilitaire {
    private static Connection con = Singleton.getInstance().getConnection();;


    protected static ResultSet recupObs(int id){
        ResultSet ret = null;
        String req = "SELECT idObservateur, nom, prenom FROM Observateur, aobserve WHERE lObservation = " + id + " AND  lObservateur = idObservateur"; 
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    protected static Lieu recupLieu(ResultSet r){
        Lieu ret = null;
        
       
        try {
            double x = r.getDouble("lieu_Lambert_X");
            double y = r.getDouble("lieu_Lambert_Y");
            ret = new Lieu(x, y);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        return ret;
    }

}
