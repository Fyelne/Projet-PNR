package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.util.Util;

import Modele.Singleton;
import Modele.donnee.IndiceLoutre;
import Modele.donnee.Lieu;
import Modele.donnee.ObsLoutre;
import Modele.donnee.Observateur;

public class ObsLoutreBdd {
    private Connection con;

    public ObsLoutreBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ObsLoutre> builder(ResultSet r){
        ArrayList<ObsLoutre> ret = new ArrayList<ObsLoutre>();
        //Construction des objets
        try {
            while(r.next()){
                int idLoutre = r.getInt("idObs");

                Date d = r.getDate("dateObs");
                Time t = r.getTime("heureObs");

                // Creation lieu 
                Lieu l = Utilitaire.recupLieu(r);
                
                ArrayList<Observateur> obs = new ArrayList<Observateur>();
                ResultSet res = Utilitaire.recupObs(r.getInt("idObs"));
                while(res.next()){
                    int id = res.getInt("idObservateur");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    Observateur nObservateur = new Observateur(id, nom, prenom);
                    obs.add(nObservateur);
                }

                IndiceLoutre indice = null;
                if(r.getString("indice").equals("positif")){
                    indice = IndiceLoutre.POSITIF;
                }
                if(r.getString("indice").equals("negatif")){
                    indice = IndiceLoutre.NEGATIF;
                } else{
                    indice = IndiceLoutre.NON_PROSPECTION;
                }

                String lieuD = r.getString("lieuDit");
                String commune = r.getString("commune");

                ObsLoutre oLoutre = new ObsLoutre(idLoutre, d, t, l, obs, indice, lieuD, commune);

                ret.add(oLoutre);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return ret;
    }

    public ResultSet getAllLoutreToBuild(){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(idObs), dateObs, heureObs, lieu_Lambert_X,lieu_Lambert_Y,indice "+
        "FROM `obs_loutre`, `observation`" +
        "WHERE ObsL = idObs " +
        "ORDER BY dateObs DESC ";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    

    public ResultSet getAllLoutreBDD(){
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
    

    public void insertOneIntoBdd(ObsLoutre l){        
        Utilitaire.insertBaseObs(l);
        
        String indice = "";
        IndiceLoutre ind = l.getIndice();
        switch(ind){
            case POSITIF:
                indice = "positif";
                break;
            
            case NEGATIF:
                indice = "negatif";
                break;

            case NON_PROSPECTION:
                indice = "non prospection";
                break;
        } 

        String addLoutre = "INSERT INTO Obs_Loutre (ObsL,commune,lieuDit,indice) VALUES ("
                            + l.getId()  + " , \'" + l.getCommune() + "\' , \'" + l.getLieuDit() + "\' , \'" + indice + "\');";
        System.out.println(addLoutre);
        
        try{
            PreparedStatement  stmt = con.prepareStatement(addLoutre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAllIntoBdd(ArrayList<ObsLoutre> oL){
        for(ObsLoutre o : oL){
            insertOneIntoBdd(o);
        }
    }
}