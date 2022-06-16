package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import javax.lang.model.element.TypeElement;

import Modele.Singleton;
import Modele.donnee.*;

public class Hyppocampe {

    private Connection con;

    public Hyppocampe(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ObsHippocampe> builder(ResultSet r){
        ArrayList<ObsHippocampe> ret = new ArrayList<ObsHippocampe>();

        try {
            while(r.next()){
                int i = r.getInt("obsH");
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

                double taille = r.getDouble("taille");

                int g = r.getInt("gestant");
                boolean gestant = false;
                if(g == 1){
                    gestant = true;
                }

                Peche typePeche = null; 
                String sPeche = r.getString("typePeche");
                
                switch(sPeche){
                    case "verveuxAnguilles":
                        typePeche = Peche.VERVEUX_ANGUILLES;
                        break;
                    case "casierCrevettes":
                        typePeche = Peche.CASIER_CREVETTES;
                        break;
                    case "casierMorgates" : 
                        typePeche = Peche.CASIER_MORGATES;
                        break;
                    case "petitFilet" :
                        typePeche = Peche.PETIT_FILET;
                        break;
                    default:
                        typePeche = Peche.NON_RENSEIGNE;
                        break;
                }

                EspeceHippocampe espH = null;
                String esp = r.getString("espece");

                switch(esp){
                    case "Syngnathus acus": 
                        espH = EspeceHippocampe.SYNGNATHUS_ACUS;
                        break;
                    case "Hippocampus guttulatus":
                        espH = EspeceHippocampe.HIPPOCAMPUS_GUTTULATUS;
                        break;

                    case "Hippocampus Hippocampus":
                        espH = EspeceHippocampe.HIPPOCAMPUS_HIPPOCAMPUS;
                        break;

                    case "Enterurus aequoreus":
                        espH = EspeceHippocampe.ENTERURUS_AEQUOREUS;
                        break;
                }

                Sexe sex = Sexe.INCONNU;

                String s = r.getString("sexe");
                
                switch(s){
                    case "male":
                        sex = Sexe.MALE;
                    case "femelle":
                        sex = Sexe.FEMELLE;
                }

                double temp = r.getDouble("temperatureEau");

                ObsHippocampe n = new ObsHippocampe(i, d, t, l , obs, taille, gestant, typePeche, espH, sex, temp);
                ret.add(n);
            }
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
