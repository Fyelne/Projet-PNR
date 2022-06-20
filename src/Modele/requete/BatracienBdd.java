package Modele.requete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.EspeceBatracien;
import Modele.donnee.MeteoCiel;
import Modele.donnee.MeteoPluie;
import Modele.donnee.MeteoTemp;
import Modele.donnee.MeteoVent;
import Modele.donnee.ObsBatracien;
import Modele.donnee.ZoneHumide;

public class BatracienBdd{

    private Connection con;

    public BatracienBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public void insertBatracienAndOther(ObsBatracien b, ArrayList<VegetationBdd> allVege, ZoneHumide zh){
        int idVege = this.idLieuVege();
        String reqLieuVege = "INSERT INTO lieu_vegetation VALUES(" + idVege + ");";
        
        try {
            PreparedStatement s = con.prepareStatement(reqLieuVege);
            s.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        int idZh = this.idZH();
        ZoneHumideBdd zBdd = new ZoneHumideBdd();
        zBdd.insertOneInto(zh);
        this.insertOneInto(b, idZh, idVege);
    }

    // à revoir pour la liaison avec zonehumide et lieuVege
    public void insertOneInto(ObsBatracien b, int idZh, int idVege){


        Utilitaire.insertBaseObs(b);

        EspeceBatracien esp = b.getEspece();
        String espece = "";
        switch(esp){
            case CALAMITE:
                espece = "calamite";
                break;
            case PELODYTE:
                espece = "pelodyte";
                break;
        }

        int nbAdulte = b.getNombreAdultes();
        int nbAmpl = b.getNombreAmplexus();
        int nbPonte = b.getNombrePonte();
        int nbTet = b.getNombreTetard();
        int temp = b.getTemperature();
        MeteoCiel metCiel = b.getMeteoCiel();
        MeteoTemp metTemp = b.getMeteoTemp();
        MeteoVent metVent = b.getMeteoVent();
        MeteoPluie metPluie = b.getMeteoPluie();

        // à revoir 
        
        

        String req = "INSERT INTO obs_batracien VALUES ( " + b.getId() + " , '" + espece + "' , " + nbAdulte + " , " + nbAmpl +
                        " , " + nbPonte + " , " + nbTet + " , " + temp + " , '" + metCiel + "','" + metTemp + "','" + 
                        metVent + "', '" + metPluie + "' , " + idZh + " , " + idVege + ");";

        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int idZH(){
        int id = 0;
        String req = "SELECT MAX(zh_id) FROM zonehumide";
        try{    
            PreparedStatement stmt = con.prepareStatement(req);
            ResultSet res =  stmt.executeQuery();
            res.next();
            id = res.getInt("MAX(zh_id)");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return id+1;
    }

    public int idLieuVege(){
        int id = 0;
        String req = "SELECT MAX(idVegeLieu) FROM lieu_vegetation";
        try{    
            PreparedStatement stmt = con.prepareStatement(req);
            ResultSet res =  stmt.executeQuery();
            res.next();
            id = res.getInt("MAX(idVegeLieu)");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return id+1;
    }

    
    
}
