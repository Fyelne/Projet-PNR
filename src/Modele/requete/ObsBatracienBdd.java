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

                Lieu l = Utilitaire.recupLieu(r);

                ArrayList<Observateur> obs = new ArrayList<Observateur>();
                ResultSet res = Utilitaire.recupObs(r.getInt("idObs"));
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

                EspeceBatracien especeBatra = null ;
                if (r.getString("nature").equals("Oeuf")) {
                    especeBatra = EspeceBatracien.CALAMITE ;
                }
                else {
                    especeBatra = EspeceBatracien.PELODYTE ;
                }

                int temp = r.getInt("temperature");
  
                MeteoCiel meteoCiel = null ;
                if (r.getString("meteo_ciel").equals("dégagé")) {
                    meteoCiel = MeteoCiel.DEGAGE ;
                } else if (r.getString("meteo_ciel").equals("semi-dégagé")) {
                    meteoCiel = MeteoCiel.SEMI_DEGAGE ;
                }else {
                    meteoCiel = MeteoCiel.NUAGEUX ;
                }
                
                MeteoTemp meteoTemp = null ;
                if (r.getString("meteo_temp").equals("froid")) {
                    meteoTemp = MeteoTemp.FROID ;
                } else if (r.getString("meteo_temp").equals("moyen")) {
                    meteoTemp = MeteoTemp.MOYEN ;
                }else {
                    meteoTemp = MeteoTemp.CHAUD ;
                }

                MeteoVent meteoVent = null ;
                if (r.getString("meteo_vent").equals("non")) {
                    meteoVent = MeteoVent.NON ;
                } else if (r.getString("meteo_vent").equals("léger")) {
                    meteoVent = MeteoVent.LEGER ;
                } else if (r.getString("meteo_vent").equals("moyen")) {
                    meteoVent = MeteoVent.MOYEN ;
                } else {
                    meteoVent = MeteoVent.FORT ;
                }

                MeteoPluie meteoPluie = null ;
                if (r.getString("meteo_pluie").equals("non")) {
                    meteoPluie = MeteoPluie.NON ;
                } else if (r.getString("meteo_pluie").equals("légère")) {
                    meteoPluie = MeteoPluie.LEGERE ;
                } else if (r.getString("meteo_pluie").equals("moyenne")) {
                    meteoPluie = MeteoPluie.MOYENNE ;
                }else {
                    meteoPluie = MeteoPluie.FORTE ;
                }

                ObsBatracien oBatracien = new ObsBatracien(idBatracien, d, t, l, obs, tabObs, especeBatra, temp, meteoCiel, meteoTemp, meteoVent, meteoPluie) ;
                ret.add(oBatracien);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
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
