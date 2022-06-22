package Modele.requete;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;

import Modele.Singleton;
import Modele.donnee.ContenuNid;
import Modele.donnee.Lieu;
import Modele.donnee.ObsGCI;
import Modele.donnee.Observateur;
import Modele.donnee.TypeObservation;

public class GCI{
    private Connection con;

    public GCI(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ObsGCI> builder(ResultSet r){
        ArrayList<ObsGCI> ret = new ArrayList<ObsGCI>();
        //Construction des objets
        try {
            while(r.next()){
                // Creation lieu 
                double x = r.getDouble("lieu_Lambert_X");
                double y = r.getDouble("lieu_Lambert_Y");
                Lieu l = new Lieu(x, y);
                
                ArrayList<Observateur> obs = new ArrayList<Observateur>();
                ResultSet res = this.recupObs(r.getInt("idObs"));
                while(res.next()){
                    int id = res.getInt("idObservateur");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    Observateur nObservateur = new Observateur(id, nom, prenom);
                    obs.add(nObservateur);
                }

                int idGCI = r.getInt("idObs");
                Date d = r.getDate("dateObs");
                Time t = r.getTime("heureObs");

                if(d == null){
                    d = new Date(0);
                }
                if(t == null){
                    t = new Time(0);
                }
                String natureString = r.getString("nature").toUpperCase();
                if(natureString.equals("NID")){
                    natureString = "NID_SEUL";
                }
                ContenuNid nature = ContenuNid.valueOf(natureString);
                int leNombre = r.getInt("nombre");
                
                ObsGCI oGCI = new ObsGCI(idGCI, d, t, l, obs, nature, leNombre);
                ret.add(oGCI);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return ret;
    }

    public ResultSet getAllGCIToBuild(){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(idObs), dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, nature, nombre "+
        "FROM `obs_GCI`, `observation` " +
        "WHERE ObsG = idObs " +
        "ORDER BY dateObs DESC;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private ResultSet recupObs(int id){
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

    public ResultSet getAllGCIBDD(){
        ResultSet ret = null;
        try{
            PreparedStatement  stmt = con.prepareStatement(
                "SELECT * FROM `bd_pnr`.`Obs_GCI`");
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
