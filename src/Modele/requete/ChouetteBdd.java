package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.TypeObservation;
import Modele.donnee.Lieu;
import Modele.donnee.*;
import Modele.donnee.Observateur;

public class ChouetteBdd {
    private Connection con;

    public ChouetteBdd(){
        this.con = Singleton.getInstance().getConnection();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ObsChouetteBdd cBdd = new ObsChouetteBdd();
        for(ObsChouette o : ch.getObservations()){
            cBdd.insertIneIntoBdd(o, id);
        }
    }

    



}
