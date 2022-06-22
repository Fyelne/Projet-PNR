package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.IndiceLoutre;
import Modele.donnee.Lieu;
import Modele.donnee.ObsLoutre;
import Modele.donnee.Observateur;

public class ObsLoutreBdd{
    private Connection con;

    public ObsLoutreBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ObsLoutre> builder(ResultSet r){
        ArrayList<ObsLoutre> ret = new ArrayList<ObsLoutre>();
        //Construction des objets
        try {
            while(r.next()){
                // Creation lieu 
                double x = r.getDouble("lieu_Lambert_X");
                double y = r.getDouble("lieu_Lambert_Y");
                Lieu l = new Lieu(x, y);

                IndiceLoutre indice = null;
                if(r.getString("indice").equals("positif")){
                    indice = IndiceLoutre.POSITIF;
                }else{
                    indice = IndiceLoutre.NEGATIF;
                    
                }
                
                ArrayList<Observateur> obs = new ArrayList<Observateur>();
                ResultSet res = Utilitaire.recupObs(r.getInt("idObs"));
                while(res.next()){
                    int id = res.getInt("idObservateur");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    Observateur nObservateur = new Observateur(id, nom, prenom);
                    obs.add(nObservateur);
                }

                int idLoutre = r.getInt("idObs");
                Date d = r.getDate("dateObs");
                Time t = r.getTime("heureObs");
                String lieuD = r.getString("lieuDit");
                String commune = r.getString("commune");
                ObsLoutre oLoutre = new ObsLoutre(idLoutre, d, t, l, obs, indice, lieuD, commune);

                ret.add(oLoutre);
            
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ret;
    }

    public ResultSet getAllLoutreToBuild(){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(idObs), dateObs, heureObs, lieu_Lambert_X,lieu_Lambert_Y,indice, commune, lieuDit "+
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
                "SELECT * FROM `bd_pnr`.`Obs_Loutre`");
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
                            + l.getId()  + " , \'" +  l.getLieuDit() + "\' , \'" + l.getCommune() + "\' , \'" + indice + "\');";
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

    public ResultSet getFilteredLoutre(String recherche){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(idObs), dateObs, heureObs, lieu_Lambert_X,lieu_Lambert_Y,indice, commune, lieuDit "+
        "FROM `obs_loutre`, `observation` " +
        "WHERE ObsL = idObs " +
        "AND commune LIKE '%" + recherche + "%'" +
        "ORDER BY dateObs DESC ";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
