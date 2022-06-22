package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.*;

public class Utilitaire {
    private static Connection con = Singleton.getInstance().getConnection();;


    public static ResultSet recupObs(int id){
        ResultSet ret = null;
        String req = "SELECT * FROM Observateur, aobserve WHERE lObservation = " + id + " AND  lObservateur = idObservateur"; 
        System.out.println(req);
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    protected static Lieu recupLieu(ResultSet r){
        Lieu ret = null;
       
        try {
            double x = r.getDouble("lieu_Lambert_X");
            double y = r.getDouble("lieu_Lambert_Y");
            ret = new Lieu(x, y);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ret;
    }

    public static void insertBaseObs(Observation o){
        insertLieuBdd(o.getLieu());
        for(Observateur obs : o.getObservateurs()){
            insertObservateurBdd(obs);
        }
        //Création de l'observation
        insertObs(o);
        //Création aObserver
        insertAObserver(o);
    }

    private static void insertLieuBdd(Lieu l){
        double x = l.getxCoord();
        double y = l.getyCoord();
        String req = "INSERT INTO Lieu (coord_Lambert_X,coord_Lambert_Y) VALUES (" + x + " , " + y + ");";

        try{    
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.executeUpdate();
            System.out.println(req);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static boolean insertObservateurBdd(Observateur o){
        String nom = o.getNom();
        String prenom = o.getPrenom();
        String req = "SELECT * FROM observateur WHERE nom = \'"  + nom + "\' AND prenom = \'" + prenom + "\';";
        boolean ret = true;
        try{    
            PreparedStatement stmt = con.prepareStatement(req);
            ResultSet r = stmt.executeQuery();
            if(!(r.next())){
                String insert = "INSERT INTO Observateur (idObservateur,nom, prenom) VALUES("+ o.getId() + ", \'" + nom + "\' , \'" + prenom + "\');"; 
                PreparedStatement add = con.prepareStatement(insert);
                add.executeUpdate();
                System.out.println(insert);
            }else{
                ret = false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ret;
    }

    private static void insertAObserver(Observation o){
        String req = "INSERT INTO aobserve (lobservateur, lobservation) VALUES (";
        ArrayList<Observateur> obs = o.getObservateurs();
        for(Observateur observe : obs){
            String reqBis = req + observe.getId() + " , " + o.getId() + ");";
            System.out.println(reqBis);
            try{    
                PreparedStatement stmt = con.prepareStatement(reqBis);
                stmt.executeUpdate();
    
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    private static void insertObs(Observation l){
        String req = "INSERT INTO observation (idObs, dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y) VALUES( " 
                        + l.getId() + " , \'" + l.getDate() + "\' , \'" + l.getHeure() + "\' , " + l.getLieu().getxCoord() + " , " + l.getLieu().getyCoord() + ");";
        System.out.println(req);
        try{    
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.executeUpdate();        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static int giveID(){
        int id = 0;
        String req = "SELECT MAX(idObs) FROM observation";
        try{    
            PreparedStatement stmt = con.prepareStatement(req);
            ResultSet res =  stmt.executeQuery();
            res.next();
            id = res.getInt("MAX(idObs)");
        }catch(SQLException e){
            e.printStackTrace();
        }

        return id+1;
    }


}
