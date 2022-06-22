package Modele.requete;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;

import Modele.Singleton;
import Modele.donnee.Lieu;
import Modele.donnee.ObsChouette;
import Modele.donnee.Observateur;
import Modele.donnee.TypeObservation;

public class Chouette{
    private Connection con;

    public Chouette(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ObsChouette> builder(ResultSet r){
        ArrayList<ObsChouette> ret = new ArrayList<ObsChouette>();
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

                int idChouette = r.getInt("idObs");
                Date d = r.getDate("dateObs");
                Time t = r.getTime("heureObs");

                if(d == null){
                    d = new Date(0);
                }
                if(t == null){
                    t = new Time(0);
                }

                String typeObString = r.getString("typeObs").toUpperCase();
                if(typeObString.equals("SONORE ET VISUEL")){
                    typeObString = "SONORE_VISUEL";
                }

                TypeObservation typeObs = TypeObservation.valueOf(typeObString);
                ObsChouette oChouette = new ObsChouette(idChouette, d, t, l, obs, typeObs);
                ret.add(oChouette);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return ret;
    }

    public ResultSet getAllChouetteToBuild(){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(idObs), dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, typeObs "+
        "FROM `obs_Chouette`, `observation` " +
        "WHERE numObs = idObs " +
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

    public ResultSet getAllChouetteBDD(){
        ResultSet ret = null;
        try{
            PreparedStatement  stmt = con.prepareStatement(
                "SELECT * FROM `bd_pnr`.`Obs_Chouette`");
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
