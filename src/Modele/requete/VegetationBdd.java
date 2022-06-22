package Modele.requete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.NatureVege;
import Modele.donnee.Vegetation;

public class VegetationBdd {
    private Connection con;

    public VegetationBdd(){
        this.con = Singleton.getInstance().getConnection();
    }


    public ArrayList<Vegetation> builder(ResultSet r) {
        ArrayList<Vegetation> ret = new ArrayList<Vegetation>();
        //Construction des objets
        try {
            while (r.next()) {
                int idVegetation = r.getInt("idVege");

                NatureVege natVeg = null ;
                if (r.getString("natureVege").equals("environnement")) {
                    natVeg = NatureVege.ENVIRONNEMENT ;
                } else if (r.getString("natureVege").equals("bordure")) {
                    natVeg = NatureVege.BORDURE ;
                } else {
                    natVeg = NatureVege.RISPYLE ;
                }

                String vege = r.getString("vegetation");

                int decritLieuVege = r.getInt("decrit_LieuVege");

                Vegetation vegetation = new Vegetation(idVegetation, natVeg, vege, decritLieuVege) ;
                ret.add(vegetation);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
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
                vege = "ripisyle";
                break;
            default:
                vege = null;
                break;
        }

        String vegetation = v.getVege();
        int decrit = v.getDecritLieu();

        String req = "INSERT INTO vegetation VALUES(" + id + " , '" + vege + "' , '" + vegetation +"' , " + decrit + ");";
        System.out.println(req);
        try {
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.executeUpdate();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
    }
    
    public int getIdDecritVege(){
        int ret = 0;
        String req = "SELECT MAX(idVegeLieu) FROM lieu_vegetation";
        try{    
            PreparedStatement stmt = con.prepareStatement(req);
            ResultSet res =  stmt.executeQuery();
            res.next();
            ret = res.getInt("MAX(idVegeLieu)");
        }catch(SQLException e){
            e.printStackTrace();
        }

        return ret+1;
    }

    public int getIdVege(){
        int ret = 0;
        String req = "SELECT MAX(idVege) FROM vegetation";
        try{    
            PreparedStatement stmt = con.prepareStatement(req);
            ResultSet res =  stmt.executeQuery();
            res.next();
            ret = res.getInt("MAX(idVege)");
        }catch(SQLException e){
            e.printStackTrace();
        }

        return ret+1;
    }

}
