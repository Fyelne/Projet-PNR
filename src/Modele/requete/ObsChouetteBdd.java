package Modele.requete;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import Modele.Singleton;
import Modele.donnee.*;

public class ObsChouetteBdd {
    private Connection con;

    public ObsChouetteBdd(){
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

                if(d == null){
                    d = new Date(0);
                }

                if(t == null){
                    t = new Time(0);
                }

                Lieu l = Utilitaire.recupLieu(r);

                ArrayList<Observateur> obs = new ArrayList<Observateur>();
                ResultSet res = Utilitaire.recupObs(r.getInt("numObs"));
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

                int p = r.getInt("protocole");
                boolean protocole = false;
                if(p == 1){
                    protocole = true;
                }

                String numIndividu = r.getString("leNumIndividu");

                ObsChouette oChouette = new ObsChouette(idChouette, d, t, l, obs, typeObserv, protocole, numIndividu);
                ret.add(oChouette);
            }

        } catch (SQLException e) {
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


    public void insertIneIntoBdd(ObsChouette c, String idIndiv){

        Utilitaire.insertBaseObs(c);
        TypeObservation t = c.getTypeObs();
        String type = "";
        switch(t){
            case SONORE:
                type = "SONORE";
                break;
            case VISUELLE:
                type = "VISUEL";
                break;
            case SONORE_VISUELLE:
                type = "SONORE ET VISUEL";
                break;
        }

        boolean p = c.getProtocole();
        int protocole = 0;
        if(p){
            protocole = 1;
        }

        String req = "INSERT INTO obs_chouette (protocole,typeObs,leNumIndividu,numObs) VALUES ("
                        + protocole + ", '" + type + "' , '" + idIndiv + "' , " + c.getId() + ");"; 

         
        try {
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getAllChouetteToBuild(){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(numObs), dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, typeObs, protocole, leNumIndividu "+
        "FROM `obs_Chouette`, `observation` " +
        "WHERE numObs = idObs " +
        "ORDER BY dateObs DESC;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


    public ResultSet getFilteredChouette(String recherche, String typeObs, String protocole){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(numObs), dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, typeObs, protocole, leNumIndividu "+
        "FROM `obs_Chouette`, `observation` " +
        "WHERE numObs = idObs " +
        "AND leNumIndividu LIKE '%" + recherche + "%'";
        if(!typeObs.equals("")){
            req += "AND typeObs = '" + typeObs + "'";
        }

        if(!protocole.equals("")){
            req += "AND protocole = " + protocole + " ";
        }

        req += "ORDER BY dateObs DESC ";

        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public ArrayList<String> recupNumIndiv(){
        ArrayList<String> ret = new ArrayList<String>();

        String req = "SELECT * FROM chouette;";
        System.out.println(req);
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                ret.add(res.getString("numIndividu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return ret;
    }

    public ArrayList<String> getAllBatracienTypeObs(){
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("");

        String req = "SELECT DISTINCT(typeObs) FROM Obs_Chouette;";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                String typeObs = res.getString("typeObs");
                ret.add(typeObs.substring(0, 1).toUpperCase() + typeObs.substring(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public ArrayList<String> getAllBatracienProtocole(){
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("");

        String req = "SELECT DISTINCT(protocole) FROM Obs_Chouette;";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(req);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                ret.add(res.getString("protocole"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
