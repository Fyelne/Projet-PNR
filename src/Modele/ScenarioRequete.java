package Modele;

import java.util.ArrayList;
import java.sql.Time;

import Modele.donnee.*;

public class ScenarioRequete {
    public static void main(String[] args) {



        Observateur observateur = new Observateur(81, "leNom", "lePrenom");
        observateur.setNom("NOEL");
        observateur.setPrenom("Andy");
        
        ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
        observateurs.add(observateur);
        observateurs.add(new Observateur(82, "leNom", "lePrenom"));

        Time t = new Time(17*60*60*1000 );
        System.out.println(t.toString());
        

        


    }
}
