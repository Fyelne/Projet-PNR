package Modele.requete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                        + " , '" + typeMare + "' , '" + pente + "' , '" + ouverture +");";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    
}
