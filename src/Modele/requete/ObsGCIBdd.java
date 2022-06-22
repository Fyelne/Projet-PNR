package Modele.requete;

import java.sql.*;
import java.util.ArrayList;

import Modele.Singleton;
import Modele.donnee.Lieu;
import Modele.donnee.Observateur;
import Modele.donnee.ContenuNid;
import Modele.donnee.ObsGCI;

public class ObsGCIBdd {
    
    private Connection con;

    public ObsGCIBdd(){
        this.con = Singleton.getInstance().getConnection();
    }




    public ArrayList<ObsGCI> builder(ResultSet r){
        ArrayList<ObsGCI> ret = new ArrayList<ObsGCI>();
        //Construction des objets
        try {
            while (r.next()) {
                int idGCI = r.getInt("obsG");

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

                ContenuNid natureNid = null ;
                if (r.getString("nature").equals("Oeuf")) {
                    natureNid = ContenuNid.OEUF ;
                }
                else if (r.getString("nature").equals("Poussin")) {
                    natureNid = ContenuNid.POUSSIN ;
                }
                else {
                    natureNid = ContenuNid.NID_SEUL ;
                }

                int nb = r.getInt("nombre");
                //A vérifier (int ou boolean)
                int p = r.getInt("presentMaisNonObs");
                boolean present = false;
                if (p == 1) {
                    present = true ;
                }

                ObsGCI oGCI = new ObsGCI(idGCI, d, t, l, obs, natureNid, nb, present) ;
                ret.add(oGCI);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ret;
    }



    public void insertOneInto(ObsGCI g, int nid){
        Utilitaire.insertBaseObs(g);

        int nb = g.getNombre();
        ContenuNid c = g.getNatureNid();
        String contenuNid = "";
        switch(c){
            case OEUF:
                contenuNid = "oeuf";
                break;
            case POUSSIN:
                contenuNid = "poussin";
            case NID_SEUL:
                contenuNid = "nid";
            default:
                contenuNid = null;
                break;
        }
        boolean pres = g.getPresentMaisNonObs();
        int present = 0;
        if(pres){
            present = 1;
        }

        String req = "INSERT INTO obs_gci VALUES(" + g.getId() + " , '" + contenuNid + "' , " + nb + " , " 
                        + present + " , " + nid +");";  

        
        try {
            PreparedStatement stmt = con.prepareStatement(req);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

    }
}
