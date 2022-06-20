package Modele.requete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modele.Singleton;
import Modele.donnee.NatureVege;
import Modele.donnee.Vegetation;

public class VegetationBdd {
    private Connection con;

    public VegetationBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public void insertOneInto(Vegetation v){
        int id = v.getId();
        NatureVege natVeg = v.getNatureVege();
        String vege = "";
        switch(natVeg){
            case ENVIRONNEMENT:
                vege = "environnement";
                break;
            case BORDURE:
                vege = "bordure";
                break;
            case RISPYLE:
                vege = "rispyle";
                break;
            default:
                vege = null;
                break;
        }

        String vegetation = v.getVege();
        int decrit = v.getDecritLieu();

        String req = "INSERT INTO vegetation VALUES(" + id + " , '" + vege + "' , '" + vegetation +"' , " + decrit + ");";

        try {
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.executeUpdate();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
    }   

}
