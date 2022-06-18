package Modele.requete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modele.Singleton;
import Modele.donnee.EspeceBatracien;
import Modele.donnee.ObsBatracien;

public class BatracienBdd{

    private Connection con;

    public BatracienBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public void insertOneInto(ObsBatracien b){
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
        String[] met = b.getMeteo();
        int idVege = this.idLieuVege();
        int idZh = this.idZH();

        String req = "INSERT INTO obs_batracien VALUES ( " + b.getId() + " , '" + espece + "' , " + nbAdulte + " , " + nbAmpl +
                        " , " + nbPonte + " , " + nbTet + " , " + temp + " , '" + met[0] + "','" + met[1] + "','" + 
                        met[2] + "', '" + met[3] + "' , " + idZh + " , " + idVege + ");";

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
            id = res.getInt("MAX(idObs)");
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
            id = res.getInt("MAX(idObs)");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return id+1;
    }

    
    
}
