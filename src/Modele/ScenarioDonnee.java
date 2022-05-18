package Modele;

import Modele.donnee.*;
import java.sql.*;
import java.util.ArrayList;

public class ScenarioDonnee {
    public static void main(String[] args) {
        Chouette chouette = new Chouette("C", Sexe.MALE, EspeceChouette.EFFRAIE);
        System.out.println("Informations de la chouette : \n\tId : " + chouette.getId() + "\n\tSexe : " + chouette.getSexe() + "\n\tEspece : " + chouette.getEspece());
        chouette.setEspece(EspeceChouette.CHEVECHE);
        chouette.setSexe(Sexe.FEMELLE);
        System.out.println("Informations de la chouette : \n\tId : " + chouette.getId() + "\n\tSexe : " + chouette.getSexe() + "\n\tEspece : " + chouette.getEspece());
        
        
        Lieu lieu = new Lieu(1, 2);
        System.out.println("Informations du lieu : \n\txCoord : " + lieu.getxCoord() + "\n\tyCoord : " + lieu.getyCoord());
        lieu.setXCoord(3);
        lieu.setYCoord(4);
        System.out.println("Informations du lieu : \n\txCoord : " + lieu.getxCoord() + "\n\tyCoord : " + lieu.getyCoord());


        NidGCI nidGCI = new NidGCI(1, "laPlage");
        System.out.println("Informations du nidGCI : \n\tId : " + nidGCI.getId() + "\n\tNom de la plage : " + nidGCI.getNomPlage() + "\n\tNombre d'envol : " + nidGCI.getNbEnvol());
        nidGCI.setNomPlage("laNouvellePlage");
        nidGCI.setNbEnvol(1);


        Observateur observateur = new Observateur(1, "leNom", "lePrenom");
        System.out.println("Informations de l'observateur : \n\tId : " + observateur.getId() + "\n\tNom : " + observateur.getNom() + "\n\tPrenom : " + observateur.getPrenom());
        observateur.setNom("NOEL");
        observateur.setPrenom("Andy");
        System.out.println("Informations de l'observateur : \n\tId : " + observateur.getId() + "\n\tNom : " + observateur.getNom() + "\n\tPrenom : " + observateur.getPrenom());
        
        
        ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
        observateurs.add(observateur);
        observateurs.add(new Observateur(2, "leNom", "lePrenom"));


        int[] resObs = {1, 2, 3, 4};
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(1000), new Time(1), lieu, observateurs, resObs, EspeceBatracien.CALAMITE);
        System.out.println("Informations de l'observation du batracien :" 
        + "\n\tId : " + obsBatracien.getId() + "\n\tDate : " + obsBatracien.getDate() + "\n\tHeure : " + obsBatracien.getHeure()
        + "\n\tLieu : " + printLieu(obsBatracien.getLieu()) + "\n\tObservateurs : " + printArray(obsBatracien.getObservateurs())
        + "\n\tNombres d'adultes : " + obsBatracien.getNombreAdultes()
        + "\n\tNombres d'amplexus : " + obsBatracien.getNombreAmplexus()
        + "\n\tNombres de tetard : " + obsBatracien.getNombreTetard()
        + "\n\tNombres de ponte : " + obsBatracien.getNombrePonte()
        + "\n\tEspece : " + obsBatracien.getEspece());

        ObsChouette obsChouette = new ObsChouette(1, new Date(1000), new Time(1), lieu, observateurs, TypeObservation.SONORE_VISUELLE);
        System.out.println("Informations de l'observation de la chouette :"
        + "\n\tId : " + obsChouette.getId() 
        + "\n\tDate : " + obsChouette.getDate() 
        + "\n\tHeure : " + obsChouette.getHeure()
        + "\n\tLieu : " + printLieu(obsChouette.getLieu()) 
        + "\n\tObservateurs : " + printArray(obsChouette.getObservateurs())
        + "\n\tType d'observation : " + obsChouette.getTypeObs());
        
        ObsGCI obsGCI = new ObsGCI(1, new Date(1000), new Time(1), lieu, observateurs, ContenuNid.POUSSIN, 1);
        System.out.println("Informations de l'observation du GCI :"
        + "\n\tId : " + obsGCI.getId() 
        + "\n\tDate : " + obsGCI.getDate() 
        + "\n\tHeure : " + obsGCI.getHeure()
        + "\n\tLieu : " + printLieu(obsGCI.getLieu()) 
        + "\n\tObservateurs : " + printArray(obsGCI.getObservateurs())
        + "\n\tContenu du nid : " + obsGCI.getNatureNid()
        + "\n\tNombre d'oiseaux observés : " + obsGCI.getNombre());

        ObsHippocampe obsHippocampe = new ObsHippocampe(1, new Date(1000), new Time(1), lieu, observateurs, 5.0, false, Peche.PETIT_FILET, EspeceHippocampe.HIPPOCAMPUS_HIPPOCAMPUS, Sexe.FEMELLE);
        System.out.println("Informations de l'observation du hippocampe :"
        + "\n\tId : " + obsHippocampe.getId() 
        + "\n\tDate : " + obsHippocampe.getDate() 
        + "\n\tHeure : " + obsHippocampe.getHeure()
        + "\n\tLieu : " + printLieu(obsHippocampe.getLieu()) 
        + "\n\tObservateurs : " + printArray(obsHippocampe.getObservateurs())
        + "\n\tTaille : " + obsHippocampe.getTaille()
        + "\n\tPeche : " + obsHippocampe.getTypePeche()
        + "\n\tEspece : " + obsHippocampe.getEspece()
        + "\n\tSexe : " + obsHippocampe.getSexe());

        ObsLoutre obsLoutre = new ObsLoutre(1, new Date(1000), new Time(1), lieu, observateurs, IndiceLoutre.POSITIF);
        System.out.println("Informations de l'observation de la loutre :"
        + "\n\tId : " + obsLoutre.getId() 
        + "\n\tDate : " + obsLoutre.getDate() 
        + "\n\tHeure : " + obsLoutre.getHeure()
        + "\n\tLieu : " + printLieu(obsLoutre.getLieu()) 
        + "\n\tObservateurs : " + printArray(obsLoutre.getObservateurs())
        + "\n\tIndice : " + obsLoutre.getIndice());
    }

    public static String printArray(ArrayList<Observateur> observateurs) {
        String ret = "[";
        for (Observateur observateur : observateurs) {
            ret += observateur.getNom() + " " + observateur.getPrenom() + ", ";
        }
        ret = ret.substring(0, ret.length() - 2);
        ret += "]";
        return ret;
    }

    public static String printLieu(Lieu lieu){
        String ret = "";
        ret += "[X : " + lieu.getxCoord() + ", Y : " + lieu.getyCoord() + "]";
        return ret;
    }
}


