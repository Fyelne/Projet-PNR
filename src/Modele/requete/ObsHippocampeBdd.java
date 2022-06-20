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
            while (r.next()) {
                int idHippocampe = r.getInt("obsH");

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

                double taille = res.getDouble("taille");

                int g = res.getInt("gestant");
                boolean gestant = false ;
                if (g == 1) {
                    gestant = true ;
                }

                Peche unTypePeche = null ;
                if (r.getString("typePeche").equals("casierMorgates")) {
                    unTypePeche = Peche.CASIER_MORGATES ;
                } else if (r.getString("typePeche").equals("casierCrevettes")) {
                    unTypePeche = Peche.CASIER_CREVETTES ;
                } else if (r.getString("typePeche").equals("petitFilet")) {
                    unTypePeche = Peche.PETIT_FILET ;
                } else if (r.getString("typePeche").equals("VerveuxAnguilles")) {
                    unTypePeche = Peche.VERVEUX_ANGUILLES ;
                } else {
                    unTypePeche = Peche.NON_RENSEIGNE ;
                }

                EspeceHippocampe especeHip = null ;
                if (r.getString("espece").equals("Syngnathus acus")) {
                    especeHip = EspeceHippocampe.SYNGNATHUS_ACUS ;
                } else if (r.getString("espece").equals("Hippocampus guttulatus")) {
                    especeHip = EspeceHippocampe.HIPPOCAMPUS_GUTTULATUS ;
                } else if (r.getString("espece").equals("Hippocampus hippocampus")) {
                    especeHip = EspeceHippocampe.HIPPOCAMPUS_HIPPOCAMPUS ;
                } else {
                    especeHip = EspeceHippocampe.ENTERURUS_AEQUOREUS ;
                }

                Sexe sexeHip = null ;
                if (r.getString("sexe").equals("male")) {
                    sexeHip = Sexe.MALE ;
                } else if (r.getString("sexe").equals("femelle")) {
                    sexeHip = Sexe.FEMELLE ;
                } else {
                    sexeHip = Sexe.INCONNU ;
                }
                
                double tempEau = (double) r.getInt("temperatureEau") ;

                ObsHippocampe oHippocampe = new ObsHippocampe(idHippocampe, d, t, l, obs, taille, gestant, unTypePeche, especeHip, sexeHip, tempEau) ;
                ret.add(oHippocampe);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
                espece = "Hippocampus Hippocampus";
                break;

            case ENTERURUS_AEQUOREUS:
                espece = "Enterurus aequoreus";
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
        System.out.println(addHippo);

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
}
