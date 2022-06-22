package Modele.requete;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.*;


public class ObsBatracienBdd {
    
    private Connection con;

    public ObsBatracienBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ObsBatracien> builder(ResultSet r){
        ArrayList<ObsBatracien> ret = new ArrayList<ObsBatracien>();
        //Construction des objets
        try {
            while (r.next()) {
                int idBatracien = r.getInt("obsB");

                Date d = r.getDate("dateObs");
                Time t = r.getTime("heureObs");

                if(d == null){
                    d = new Date(0);
                }

                if(t == null){
                    t = new Time(0);
                }

                Lieu l = Utilitaire.recupLieu(r);

                ArrayList<Observateur> obs = new ArrayList<Observateur>();
                ResultSet res = Utilitaire.recupObs(r.getInt("ObsB"));
                while(res.next()){
                    int id = res.getInt("idObservateur");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    Observateur nObservateur = new Observateur(id, nom, prenom);
                    obs.add(nObservateur);
                }

                int[] tabObs = new int[4] ;
                tabObs[0] = r.getInt("nombreAdultes");
                tabObs[1] = r.getInt("nombreAmplexus");
                tabObs[2] = r.getInt("nombrePonte");
                tabObs[3] = r.getInt("nombreTetard");

                EspeceBatracien espece = null;
                String especeString = r.getString("espece").toUpperCase();
                if (especeString.equals("CALAMITE")) {
                    espece = EspeceBatracien.CALAMITE ;
                }
                else {
                    espece = EspeceBatracien.PELODYTE ;
                }

                int temp = r.getInt("temperature");
  
                MeteoCiel meteoCiel = null ;
                String meteoCielString = r.getString("meteo_ciel");
                if(meteoCielString != null){
                    if (meteoCielString.equals("dégagé")) {
                        meteoCiel = MeteoCiel.DEGAGE ;
                    } else if (meteoCielString.equals("semi-dégagé")) {
                        meteoCiel = MeteoCiel.SEMI_DEGAGE ;
                    }else {
                        meteoCiel = MeteoCiel.NUAGEUX ;
                    }
                } else {
                    meteoCiel = MeteoCiel.SEMI_DEGAGE ;
                }
                
                MeteoTemp meteoTemp = null ;
                String meteoTempString = r.getString("meteo_temp");
                if(meteoTempString != null){
                    if (meteoTempString.equals("froid")) {
                        meteoTemp = MeteoTemp.FROID ;
                    } else if (meteoTempString.equals("moyen")) {
                        meteoTemp = MeteoTemp.MOYEN ;
                    }else {
                        meteoTemp = MeteoTemp.CHAUD ;
                    }
                } else {
                    meteoTemp = MeteoTemp.MOYEN ;
                }

                MeteoVent meteoVent = null ;
                String meteoVentString = r.getString("meteo_vent");
                if(meteoVentString != null){
                    if (meteoVentString.equals("non")) {
                        meteoVent = MeteoVent.NON ;
                    } else if (meteoVentString.equals("léger")) {
                        meteoVent = MeteoVent.LEGER ;
                    } else if (meteoVentString.equals("moyen")) {
                        meteoVent = MeteoVent.MOYEN ;
                    } else {
                        meteoVent = MeteoVent.FORT ;
                    }
                } else {
                    meteoVent = MeteoVent.NON ;
                }

                MeteoPluie meteoPluie = null ;
                String meteoPluieString = r.getString("meteo_pluie");
                if(meteoPluieString != null){
                    if (meteoPluieString.equals("non")) {
                        meteoPluie = MeteoPluie.NON ;
                    } else if (meteoPluieString.equals("légère")) {
                        meteoPluie = MeteoPluie.LEGERE ;
                    } else if (meteoPluieString.equals("moyenne")) {
                        meteoPluie = MeteoPluie.MOYENNE ;
                    }else {
                        meteoPluie = MeteoPluie.FORTE ;
                    }
                } else {
                    meteoPluie = MeteoPluie.NON ;
                }

                ObsBatracien oBatracien = new ObsBatracien(idBatracien, d, t, l, obs, tabObs, espece, temp, meteoCiel, meteoTemp, meteoVent, meteoPluie) ;
                ret.add(oBatracien);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void insertBatracienAndOther(ObsBatracien b, ArrayList<Vegetation> allVege, ZoneHumide zh){
        int idVege = allVege.get(0).getDecritLieu();
        String reqLieuVege = "INSERT INTO lieu_vegetation VALUES(" + idVege + ");";
        
        try {
            PreparedStatement s = con.prepareStatement(reqLieuVege);
            s.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        int idZh = this.idZH();
        ZoneHumideBdd zBdd = new ZoneHumideBdd();
        zBdd.insertOneInto(zh);
        VegetationBdd vBdd = new VegetationBdd();
        for(Vegetation v : allVege){
            vBdd.insertOneInto(v);
        }
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
        int tem = b.getTemperature();
        MeteoCiel metCiel = b.getMeteoCiel();
        String ciel = "";
        switch(metCiel){
            case DEGAGE:
                ciel = "dégagé";
                break;
            case SEMI_DEGAGE:
                ciel = "semi-dégagé";
                break;
            case NUAGEUX:
                ciel = "nuageux";
                break;
            default:
                ciel = null;
                break;
        }
        MeteoTemp metTemp = b.getMeteoTemp();
        String temp = "";
        switch(metTemp){
            case FROID:
                temp = "froid";
                break;
            case MOYEN:
                temp = "moyen";
                break;
            case CHAUD:
                temp = "chaud";
                break;
            default: 
                temp = null;
                break;
        }

        MeteoVent metVent = b.getMeteoVent();
        String vent = "";
        switch(metVent){
            case NON:
                vent = "non";
                break;
            case LEGER:
                vent = "léger";
                break;
            case MOYEN:
                vent = "moyen";
                break;
            case FORT:
                vent = "fort";
                break;
            default:
                vent = null;
                break;
        }
        MeteoPluie metPluie = b.getMeteoPluie();
        String pluie = "";
        switch(metPluie){
            case NON:
                pluie = "non";
                break;
            case LEGERE:
                pluie = "légère";
                break;
            case MOYENNE:
                pluie = "moyenne";
                break;
            case FORTE:
                pluie = "forte";
                break;
            default:
                pluie = null;
                break;
        }

        String req = "INSERT INTO obs_batracien VALUES ( " + b.getId() + " , '" + espece + "' , " + nbAdulte + " , " + nbAmpl +
                        " , " + nbPonte + " , " + nbTet + " , " + tem + " , '" + ciel + "','" + temp + "','" + 
                        vent + "', '" + pluie + "' , " + idZh + " , " + idVege + ");";

        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            stmt.executeUpdate();
        } catch (SQLException e) {
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

    public ResultSet getAllBatracienToBuild(){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(ObsB), dateObs, heureObs, lieu_Lambert_X,lieu_Lambert_Y, " +
        "nombreAdultes, nombreAmplexus, nombrePonte, nombreTetard, espece, temperature, meteo_ciel, meteo_temp, meteo_vent, meteo_pluie "+
        "FROM `obs_Batracien`, `observation`" +
        "WHERE ObsB = idObs " +
        "ORDER BY dateObs DESC;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    public ResultSet getFilteredBatracien(String recherche, String espece){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(ObsB), dateObs, heureObs, lieu_Lambert_X,lieu_Lambert_Y, " +
        "nombreAdultes, nombreAmplexus, nombrePonte, nombreTetard, espece, temperature, meteo_ciel, meteo_temp, meteo_vent, meteo_pluie "+
        "FROM `obs_Batracien`, `observation`" +
        "WHERE ObsB = idObs " +
        "AND espece LIKE '%" + espece + "%' " +
        "ORDER BY dateObs DESC;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ArrayList<String> getAllBatracienEspece(){
        ArrayList<String> ret = new ArrayList<String> ();
        ret.add("");
        String req  = "SELECT DISTINCT(espece) " +
        "FROM `obs_Batracien` " +
        "ORDER BY espece;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ResultSet query = stmt.executeQuery();
            while(query.next()){
                String espece = query.getString("espece");
                ret.add(espece.substring(0, 1).toUpperCase() + espece.substring(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
