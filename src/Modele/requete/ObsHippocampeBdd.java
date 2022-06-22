package Modele.requete;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.Lieu;
import Modele.donnee.ObsHippocampe;
import Modele.donnee.Observateur;
import Modele.donnee.*;

public class ObsHippocampeBdd {

    private Connection con;

    public ObsHippocampeBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ObsHippocampe> builder(ResultSet r){
        ArrayList<ObsHippocampe> ret = new ArrayList<ObsHippocampe>();
        //Construction des objets
        try {
            while(r.next()){
                // Creation lieu 
                double x = r.getDouble("lieu_Lambert_X");
                double y = r.getDouble("lieu_Lambert_Y");
                Lieu l = new Lieu(x, y);
                
                ArrayList<Observateur> obs = new ArrayList<Observateur>();
                ResultSet res = this.recupObs(r.getInt("idObs"));
                while(res.next()){
                    int id = res.getInt("idObservateur");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    Observateur nObservateur = new Observateur(id, nom, prenom);
                    obs.add(nObservateur);
                }

                int idHippocampe = r.getInt("idObs");
                Date d = r.getDate("dateObs");
                Time t = r.getTime("heureObs");

                if(d == null){
                    d = new Date(0);
                }
                if(t == null){
                    t = new Time(0);
                }
                
                double laTaille = r.getDouble("taille");
                boolean estGestant = false;
                if(r.getInt("gestant") == 1){
                    estGestant = true;
                }

                String leTypePecheString = r.getString("typePeche");
                Peche leTypePeche;

                if(leTypePecheString != null) {
                    if(leTypePecheString.equals("casierCrevettes")){
                        leTypePeche = Peche.valueOf("CASIER_CREVETTES");
                    } else if(leTypePecheString.equals("verveuxAnguilles")){
                        leTypePeche = Peche.valueOf("VERVEUX_ANGUILLES");
                    } else if(leTypePecheString.equals("casierMorgates")){
                        leTypePeche = Peche.valueOf("CASIER_MORGATES");
                    } else if(leTypePecheString.equals("petitFilet")){
                        leTypePeche = Peche.valueOf("PETIT_FILET");
                    } else {
                        leTypePeche = Peche.valueOf("NON_RENSEIGNE");
                    }
                } else {
                    leTypePeche = Peche.valueOf("NON_RENSEIGNE");
                }

                String especeHippocampeString = r.getString("espece");
                EspeceHippocampe especeHippocampe;
                if(especeHippocampeString != null){
                    if(especeHippocampeString.equals("Syngnathus acus")){
                        especeHippocampe = EspeceHippocampe.valueOf("SYNGNATHUS_ACUS");
                    } else if(especeHippocampeString.equals("Hippocampus guttulatus")){
                        especeHippocampe = EspeceHippocampe.valueOf("HIPPOCAMPUS_GUTTULATUS");
                    } else if(especeHippocampeString.equals("Entelurus aequoreus")){
                        especeHippocampe = EspeceHippocampe.valueOf("ENTELURUS_AEQUOREUS");
                    } else {
                        especeHippocampe = EspeceHippocampe.valueOf("HIPPOCAMPUS_HIPPOCAMPUS");
                    }
                } else {
                    especeHippocampe = EspeceHippocampe.valueOf("HIPPOCAMPUS_HIPPOCAMPUS");
                }

                Sexe leSexe = Sexe.valueOf(r.getString("sexe").toUpperCase());
                double tempe = r.getDouble("temperatureEau");
                ObsHippocampe oHippocampe = new ObsHippocampe(idHippocampe, d, t, l, obs, laTaille, estGestant, leTypePeche, especeHippocampe, leSexe, tempe);
                ret.add(oHippocampe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ret;
    }

    private ResultSet recupObs(int id){
        ResultSet ret = null;
        String req = "SELECT idObservateur, nom, prenom FROM Observateur, aobserve WHERE lObservation = " + id + " AND  lObservateur = idObservateur"; 
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
        
    }

    public void insertOneHippocampe(ObsHippocampe h){
        Utilitaire.insertBaseObs(h);
        double taille = h.getTaille();
        int gestant = 0;
        if(h.getEstGestant()){
            gestant = 1;
        }

        Peche p = h.getTypePeche();
        String typePeche = "";
        switch(p){
            case CASIER_CREVETTES:
                typePeche = "CasierCrevettes";
                break;
            case CASIER_MORGATES:
                typePeche = "casierMorgates";
                break;
            case PETIT_FILET:
                typePeche = "petitFilet";
                break;
            case VERVEUX_ANGUILLES:
                typePeche = "verveuxAnguilles";
                break;
            default:
                typePeche = "nonRenseigner";
                break;
        }

        EspeceHippocampe e =  h.getEspece();
        String espece = "";
        switch(e){
            case SYNGNATHUS_ACUS:
                espece = "Syngnathus acus";
                break;

            case HIPPOCAMPUS_GUTTULATUS:
                espece = "Hippocampus guttulatus";
                break;

            case HIPPOCAMPUS_HIPPOCAMPUS :
                espece = "Hippocampus hippocampus";
                break;

            case ENTELURUS_AEQUOREUS:
                espece = "Entelurus aequoreus";
                break;
        }

        Sexe s = h.getSexe();
        String sexe = "";
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

        String addHippo = "INSERT INTO obs_hippocampe (obsH, espece, sexe, temperatureEau, typePeche, taille, gestant) VALUES ("
                            + h.getId() + " , \'" + espece + "\' , \'" + sexe + "\' , " +  + h.getTemperature() +  " , \'" + typePeche + "\' , " + taille + " , " + gestant + ");" ;

        try{
            PreparedStatement  stmt = con.prepareStatement(addHippo);
            stmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public void insertAllIntoBdd(ArrayList<ObsHippocampe> oH){
        for(ObsHippocampe obsH : oH){
            insertOneHippocampe(obsH);
        }
    }

    public ResultSet getAllHippocampeToBuild(){
        ResultSet ret = null;
        String req  = "SELECT ObsH, idObs, dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, espece, sexe, taille, gestant, typePeche, temperatureEau "+
        "FROM `obs_Hippocampe`, `observation` " +
        "WHERE ObsH = idObs " +
        "ORDER BY dateObs DESC;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ResultSet getAllHippocampeBDD(){
        ResultSet ret = null;
        try{
            PreparedStatement  stmt = con.prepareStatement(
                "SELECT * FROM `bd_pnr`.`Obs_Hippocampe`");
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public ResultSet getFilteredHippocampe(String recherche, String espece, String typePeche){
        ResultSet ret = null;
        String req  = "SELECT ObsH, idObs, dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, espece, sexe, taille, gestant, typePeche, temperatureEau "+
        "FROM `obs_Hippocampe`, `observation` " +
        "WHERE ObsH = idObs " +
        "AND espece LIKE '%" + espece + "%' " +
        "AND typePeche LIKE '%" + typePeche + "%' " +
        "ORDER BY dateObs DESC;";
        System.out.println(req);
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ArrayList<String> getAllHippocampeEspece(){
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("");
        String req  = "SELECT DISTINCT(espece) "+
        "FROM `obs_Hippocampe` " +
        "WHERE espece IS NOT NULL " +
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

    public ArrayList<String> getAllHippocampeTypePeche(){
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("");
        String req  = "SELECT DISTINCT(typePeche) "+
        "FROM `obs_Hippocampe` " +
        "WHERE typePeche IS NOT NULL " +
        "ORDER BY espece;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ResultSet query = stmt.executeQuery();
            while(query.next()){
                String espece = query.getString("typePeche");
                ret.add(espece.substring(0, 1).toUpperCase() + espece.substring(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
