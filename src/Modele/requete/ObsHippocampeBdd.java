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
}
