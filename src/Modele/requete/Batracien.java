package Modele.requete;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import Modele.Singleton;
import Modele.donnee.EspeceBatracien;
import Modele.donnee.Lieu;
import Modele.donnee.ObsBatracien;
import Modele.donnee.Observateur;

public class Batracien{
    private Connection con;

    public Batracien(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ObsBatracien> builder(ResultSet r){
        ArrayList<ObsBatracien> ret = new ArrayList<ObsBatracien>();
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

                int idBatracien = r.getInt("idObs");
                Date d = r.getDate("dateObs");
                Time t = r.getTime("heureObs");
                int[] resObs = new int[] {r.getInt("nombreAdultes"), r.getInt("nombreAmplexus"), r.getInt("nombrePonte"), r.getInt("nombreTetard")};
                EspeceBatracien especeBatracien = EspeceBatracien.valueOf(r.getString("espece").toUpperCase());                
                if(idBatracien >= 0 && d != null && t != null && l != null){
                    ObsBatracien oBatracien = new ObsBatracien(idBatracien, d, t, l, obs, resObs, especeBatracien);
                    ret.add(oBatracien);
                }

            
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return ret;
    }

    public ResultSet getAllBatracienToBuild(){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(idObs), dateObs, heureObs, lieu_Lambert_X,lieu_Lambert_Y, nombreAdultes, nombreAmplexus, nombrePonte, nombreTetard, espece "+
        "FROM `obs_Batracien`, `observation`" +
        "WHERE ObsB = idObs " +
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

    public ResultSet getAllBatracienBDD(){
        ResultSet ret = null;
        try{
            PreparedStatement  stmt = con.prepareStatement(
                "SELECT * FROM `bd_pnr`.`Obs_Batracien`");
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
