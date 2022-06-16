package Modele;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;

import Modele.donnee.*;
import Modele.requete.*;

public class ScenarioRequete {
    public static void main(String[] args) {


        Lieu lieu = new Lieu(7, 78);

        Observateur observateur = new Observateur(81, "leNom", "lePrenom");
        observateur.setNom("NOEL");
        observateur.setPrenom("Andy");
        
        ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
        observateurs.add(observateur);
        observateurs.add(new Observateur(82, "leNom", "lePrenom"));

        ObsLoutre obsLoutre = new ObsLoutre(2130, new Date(1000), new Time(1), lieu, observateurs, IndiceLoutre.POSITIF, "Vannes", "Bar");
        
        Loutre bddLoutre = new Loutre();
        bddLoutre.insertOneIntoBdd(obsLoutre);
    }
}
