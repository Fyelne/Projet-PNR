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

    /**
     * Permet d'inserer un objet Chouette dans la base de données 
     * @param ch objet de type Chouette
     */
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

    /**
     * Renvoie les identifiants d'un nid
     * @return
     */
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

    public ResultSet getAllNidGci(){
        ResultSet ret = null;

        String req = "SELECT * FROM nid_gci;";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ret;
    }


    /**
     * Construit une arraylist d'objet de type Chouette
     * @param r
     * @return Une ArrayList d'objet de type Chouette
     */
    public ArrayList<NidGCI> builder(ResultSet r){
        ArrayList<NidGCI> ret = new ArrayList<NidGCI>();

        try {
            while(r.next()){
                int id = r.getInt("idNid");
                String pl = r.getString("nomPlage");
                int pr = r.getInt("protection");
                boolean prot = false;
                if(pr == 1){
                    prot = true;
                }
                NidGCI ad = new NidGCI(id, pl, prot);
                ad.setNbEnvol(r.getInt("nbEnvol"));

                ret.add(ad);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

}
