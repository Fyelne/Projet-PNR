package Modele.requete;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modele.donnee.Observateur;

public class ObservateurBdd {


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
            e.printStackTrace();
        }
        return ret;
    }
}
