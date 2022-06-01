package Modele.requete;

import java.sql.*;


import Modele.Singleton;
import Modele.donnee.EspeceChouette;

public class Chouette {
    private Connection con;

    public Chouette(){
        this.con = Singleton.getInstance().getConnection();
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
