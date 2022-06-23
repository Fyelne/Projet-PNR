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


    /**
     * Construit une arraylist d'objet de type Chouette
     * @param r
     * @return Une ArrayList d'objet de type Chouette
     */
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


    /**
     * Permet d'inserer un objet Chouette dans la base de données 
     * @param ch objet de type Chouette
     */
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

    

    /**
     * Verifie que le num est deja dans la base de données
     * @param num numéro à verifier l'existence
     * @return Un boolean
     */
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



    



}
