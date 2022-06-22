package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.*;

public class ChouetteBdd {
    private Connection con;

    public ChouetteBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<Chouette> builder(ResultSet r) {
        ArrayList<Chouette> ret = new ArrayList<Chouette>();
        //Construction des objets
        try {
            while (r.next()) {
                String idChouette = r.getString("numIndividu");

                Sexe sexeChouette = null ;
                if (r.getString("sexe").equals("male")) {
                    sexeChouette = Sexe.MALE ;
                } else if (r.getString("sexe").equals("femelle")) {
                    sexeChouette = Sexe.FEMELLE ;
                } else {
                    sexeChouette = Sexe.INCONNU ;
                }

                EspeceChouette especeChouette = null ;
                if (r.getString("espece").equals("Hulotte")) {
                    especeChouette = EspeceChouette.HULOTTE ;
                } else if (r.getString("espece").equals("Effraie")) {
                    especeChouette = EspeceChouette.EFFRAIE ;
                } else {
                    especeChouette = EspeceChouette.CHEVECHE ;
                }

                Chouette chouette = new Chouette(idChouette, sexeChouette, especeChouette) ;
                ret.add(chouette);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
        
    }


    public void insertOneIntoBdd(Chouette ch){
        String id = ch.getId();

        EspeceChouette es = ch.getEspece();
        String espece = "";
        switch(es){
            case HULOTTE:
                espece = "Hulotte";
                break;
            
            case EFFRAIE:
                espece = "Effraie";
                break;
            
            case CHEVECHE:
                espece =  "Cheveche";
                break;
        }

        Sexe s = ch.getSexe();
        String sexe ="";
        switch(s){
            case MALE:
                sexe = "male";
                break;
            case FEMELLE:
                sexe = "femelle";
                break;
            default:
                sexe = "inconnu";
                break;
        }

        String req = "INSERT INTO chouette(numIndividu, espece, sexe) VALUES ('" +
                        id +"' , '" + espece + "' , '" +sexe + "');";

        try  {
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObsChouetteBdd cBdd = new ObsChouetteBdd();
        for(ObsChouette o : ch.getObservations()){
            cBdd.insertIneIntoBdd(o, id);
        }
    }

    

    public boolean check(String num){
        boolean ret = false;
        String req = "SELECT * FROM chouette WHERE numIndividu = '" + num + "';";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ResultSet res = stmt.executeQuery();
            if(!res.next()){
                ret = true;
            }
        } catch (SQLException e) {
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
