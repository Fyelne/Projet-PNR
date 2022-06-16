package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.TypeObservation;
import Modele.donnee.Lieu;
import Modele.donnee.*;
import Modele.donnee.Observateur;

public class Chouette {
    private Connection con;

    public Chouette(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<ObsChouette> builder(ResultSet r){
        ArrayList<ObsChouette> ret = new ArrayList<ObsChouette>();
        //Construction des objets
        try {
            while (r.next()) {
                int idChouette = r.getInt("numObs");

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

                TypeObservation typeObserv = null ;
                if (r.getString("typeObs").equals("Sonore")) {
                    typeObserv = TypeObservation.SONORE ;
                }
                else if (r.getString("typeObs").equals("Visuel")) {
                    typeObserv = TypeObservation.VISUELLE ;
                }
                else {
                    typeObserv = TypeObservation.SONORE_VISUELLE ;
                }

                ObsChouette oChouette = new ObsChouette(idChouette, d, t, l, obs, typeObserv) ;
                ret.add(oChouette);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return ret;

        
    }

    
    public ResultSet getAllChouette(String... sort){
        ResultSet ret = null;
        try{
            PreparedStatement  stmt = con.prepareStatement(
                "SELECT * FROM `bd_pnr`.`Obs_Chouette`");
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public ResultSet getChouetteById(String idChouette){
        ResultSet ret = null;
        try{
            PreparedStatement  stmt = con.prepareStatement(
                "SELECT * FROM Obs_Chouette WHERE leNumIndividu = ?");
            stmt.setString(1, idChouette);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public ResultSet getChouetteByDate(Date date){
        ResultSet ret = null;
        try{
            PreparedStatement  stmt = con.prepareStatement(
                "SELECT Obs_Chouette.* from Obs_Chouette, observation, lieu where idObs = numObs AND dateObs = ?");
            stmt.setString(1, date.toString());
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public ResultSet getChouetteByEspece(EspeceChouette espece){
        ResultSet ret = null;
        try{
            PreparedStatement  stmt = con.prepareStatement(
                "select obs_chouette.* from obs_chouette, chouette where numIndividu=leNumIndividu AND ");
            stmt.setString(1, espece.toString());
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }



}
