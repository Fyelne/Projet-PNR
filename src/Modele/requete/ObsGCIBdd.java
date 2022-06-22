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




    /**
     * Prépare une liste d'observation pour le tableau
     * 
     * @param r Jeu de résultats
     * @return Une ArrayList d'objets ObsGCI.
     */
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
                ResultSet res = Utilitaire.recupObs(r.getInt("obsG"));
                while(res.next()){
                    int id = res.getInt("idObservateur");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    Observateur nObservateur = new Observateur(id, nom, prenom);
                    obs.add(nObservateur);
                }

                ContenuNid natureNid = null;
                String natureString = r.getString("nature").toUpperCase();
                if (natureString.equals("OEUF")) {
                    natureNid = ContenuNid.OEUF ;
                }
                else if (natureString.equals("POUSSIN")) {
                    natureNid = ContenuNid.POUSSIN ;
                }
                else {
                    natureNid = ContenuNid.NID_SEUL ;
                }
                int nb = r.getInt("nombre");

                int leNid = r.getInt("leNid");

                ObsGCI oGCI = new ObsGCI(idGCI, d, t, l, obs, natureNid, nb, false, leNid) ;
                ret.add(oGCI);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ret;
    }



    /**
     * Il insère une nouvelle observation d'un GCI dans la base de données
     * 
     * @param g l'objet ObsGCI
     * @param nid l'identifiant du nid
     */
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


    /**
     * Il renvoie un ResultSet contenant toutes les observations du GCI (Groupe de Chasseurs
     * d'Insectes) dans la base de données
     * 
     * @return Un objet ResultSet.
     */
    public ResultSet getAllGCIToBuild(){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(ObsG), dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, nature, nombre, leNid "+
        "FROM `obs_GCI`, `observation` " +
        "WHERE ObsG = idObs " +
        "ORDER BY dateObs DESC;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Il renvoie une ArrayList de toutes les différentes valeurs de la colonne "nature" dans la table
     * "obs_GCI"
     * 
     * @return Une ArrayList de chaînes
     */
    public ArrayList<String> getAllGCINature(){
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("");
        String req  = "SELECT DISTINCT(nature) "+
        "FROM `obs_GCI` " +
        "ORDER BY nature;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ResultSet query = stmt.executeQuery();
            while(query.next()){
                String nature = query.getString("nature");
                ret.add(nature.substring(0, 1).toUpperCase() + nature.substring(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * Elle renvoie une ArrayList de Strings contenant toutes les valeurs distinctes de la colonne
     * "nombre" dans la table "obs_GCI" classées par "nombre"
     * 
     * @return Une ArrayList de chaînes.
     */
    public ArrayList<String> getAllGCINombre(){
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("");
        String req  = "SELECT DISTINCT(nombre) "+
        "FROM `obs_GCI` " +
        "ORDER BY nombre;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ResultSet query = stmt.executeQuery();
            while(query.next()){
                ret.add(query.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }


    /**
     * Elle retourne un ResultSet contenant les résultats d'une requête qui sélectionne toutes les
     * lignes de la table "obs_GCI" et "observation" où la colonne "leNid" contient la chaîne
     * "recherche", la colonne "nature" contient la chaîne "nature" et la colonne "nombre" est égale à
     * l'entier "nombre"
     * 
     * @param recherche une chaîne qui est le nom du nid
     * @param nature un string
     * @param nombre un nombre entier
     * @return Un objet ResultSet.
     */
    public ResultSet getFilteredGCI(String recherche, String nature, String nombre){
        ResultSet ret = null;
        String req  = "SELECT DISTINCT(ObsG), dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y, nature, nombre, leNid "+
        "FROM `obs_GCI`, `observation` " +
        "WHERE ObsG = idObs " +
        "AND leNid LIKE '%" + recherche + "%' " +
        "AND nature LIKE '%" + nature + "%' " +
        "AND nombre = " + nombre +
        " ORDER BY dateObs DESC;";
        try{
            PreparedStatement  stmt = con.prepareStatement(req);
            ret = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    
}
