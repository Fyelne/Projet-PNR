package Modele.requete;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;

import Modele.Singleton;
import Modele.donnee.EspeceHippocampe;
import Modele.donnee.Lieu;
import Modele.donnee.ObsHippocampe;
import Modele.donnee.Observateur;
import Modele.donnee.Peche;
import Modele.donnee.Sexe;

public class Hippocampe{
    private Connection con;

    public Hippocampe(){
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
                    } else if(especeHippocampeString.equals("Enterurus aequoreus")){
                        especeHippocampe = EspeceHippocampe.valueOf("ENTERURUS_AEQUOREUS");
                    } else {
                        especeHippocampe = EspeceHippocampe.valueOf("HIPPOCAMPUS_HIPPOCAMPUS");
                    }
                } else {
                    especeHippocampe = EspeceHippocampe.valueOf("HIPPOCAMPUS_HIPPOCAMPUS");
                }

                Sexe leSexe = Sexe.valueOf(r.getString("sexe").toUpperCase());

                ObsHippocampe oHippocampe = new ObsHippocampe(idHippocampe, d, t, l, obs, laTaille, estGestant, leTypePeche, especeHippocampe, leSexe);
                ret.add(oHippocampe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ret;
    }

    public ResultSet getAllHippocampeToBuild(){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(idObs), dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, espece, sexe, taille, gestant, typePeche "+
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
}
