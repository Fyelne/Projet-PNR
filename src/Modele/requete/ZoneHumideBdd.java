package Modele.requete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.Ouverture;
import Modele.donnee.Pente;
import Modele.donnee.TypeMare;
import Modele.donnee.ZoneHumide;

public class ZoneHumideBdd {
    
    private Connection con;

    public ZoneHumideBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ZoneHumide> builder(ResultSet r) {
        ArrayList<ZoneHumide> ret = new ArrayList<ZoneHumide>();
        //Construction des objets
        try {
            while (r.next()) {
                int idZoneHumide = r.getInt("zh_id");

                int t = r.getInt("zh_temporaire");
                boolean tempoZH = false;
                if (t == 1) {
                    tempoZH = true;
                }

                double profondeurZH = r.getDouble("zh_profondeur");
                double surfaceZH = r.getDouble("zh_surface");

                TypeMare mareType = null ;
                if (r.getString("zh_typeMare").equals("Prairie")) {
                    mareType = TypeMare.PRAIRIE ;
                } else if (r.getString("zh_typeMare").equals("Etang")) {
                    mareType = TypeMare.ETANG ;
                } else if (r.getString("zh_typeMare").equals("Marais")) {
                    mareType = TypeMare.MARAIS ;
                }else if (r.getString("zh_typeMare").equals("Mare")) {
                    mareType = TypeMare.MARE ;
                }else {
                    mareType = null ;
                }

                Pente pente = null ;
                if (r.getString("zh_pente").equals("Raide")) {
                    pente = Pente.RAIDE ;
                } else if (r.getString("zh_pente").equals("Abrupte")) {
                    pente = Pente.ABRUPTE ;
                } else if (r.getString("zh_pente").equals("Douce")) {
                    pente = Pente.DOUCE ;
                }else {
                    pente = null ;
                }

                Ouverture ouverture = null ;
                if (r.getString("zh_ouverture").equals("Abritee")) {
                    ouverture = Ouverture.ABRITEE ;
                } else if (r.getString("zh_ouverture").equals("Ouverte")) {
                    ouverture = Ouverture.OUVERTE ;
                } else if (r.getString("zh_ouverture").equals("Semi-abritee")) {
                    ouverture = Ouverture.SEMI_ABRITEE ;
                }else {
                    ouverture = null ;
                }

                ZoneHumide zoneHumide = new ZoneHumide(idZoneHumide, tempoZH, profondeurZH, surfaceZH, mareType, pente, ouverture) ;
                ret.add(zoneHumide);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }



    public void insertOneInto(ZoneHumide z){
        int id = z.getId();
        boolean t = z.getTempo();
        int tempo = 0;
        if(t){
            tempo = 1;
        }

        double profondeur = z.getProdondeur();
        double surface = z.getSurface();

        TypeMare ty = z.getTypeMaree();
        String typeMare = "";
        switch(ty){
            case PRAIRIE:
                typeMare = "Prairie";
            break;
            case ETANG:
                typeMare = "Etang";
            break;
            case MARAIS:
                typeMare = "Marais";
                break;
            case MARE:
                typeMare = "Mare";
                break;
            case FOSSE:
                typeMare = "Fosse";   
                break;
            default:
                typeMare = null;

                break;
        }

        Pente p = z.getPente();
        String pente = "";
        switch(p){
            case RAIDE:
                pente = "Raide";
                break;
            case ABRUPTE:
                pente = "Abrupte";       
                break;
            case DOUCE:
                pente = "Douce";
                break;
            default:
                pente = null;
                break;
        }

        Ouverture o = z.getOuverture();
        String ouverture = "";
        switch(o){
            case ABRITEE:
                ouverture = "Abritee";
                break;
            case SEMI_ABRITEE:
                ouverture = "Semi-abritee";
                break;
            case OUVERTE:
                ouverture = "Ouverte";
                break;
            default:
                ouverture = null;
                break;
        }

        String req = "INSERT INTO zonehumide VALUES(" + id + " , " + tempo + " , " + profondeur + " , " + surface 
                        + " , '" + typeMare + "' , '" + pente + "' , '" + ouverture +"');";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public  int giveIdZh(){
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
    
}
