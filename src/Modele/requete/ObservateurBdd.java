package Modele.requete;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.Observateur;

public class ObservateurBdd {
    private Connection con;

    public ObservateurBdd(){
        this.con = Singleton.getInstance().getConnection();
    }

    public ArrayList<Observateur>  builder(ResultSet res){
        ArrayList<Observateur> ret = new ArrayList<Observateur>();

        try {
            while(res.next()){  
                int id = res.getInt("idObservateur");
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                
                Observateur o = new Observateur(id, nom, prenom);
                ret.add(o);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }
}
