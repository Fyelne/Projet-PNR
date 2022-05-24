package Modele;

import Modele.traitement.*;
import Modele.donnee.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;

public class ScenarioTraitement {
    public static void main(String[] args) {
        HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>();
        Lieu lieu1 = new Lieu(1,1);
        Lieu lieu2 = new Lieu(2,2);
        Lieu lieu3 = new Lieu(3,3);
        Lieu lieu4 = new Lieu(4,4);

        Date date1 = new Date(0);
        Date date2 = new Date(86400000);
        Date date3 = new Date(86400000*2);
        Date date4 = new Date(86400000*3);


        ArrayList<Sommet> sommets = new ArrayList<Sommet>();
        sommets.add(new Sommet(1, lieu1, date1, EspeceObservee.BATRACIEN));
        sommets.add(new Sommet(2, lieu2, date2, EspeceObservee.GCI));
        sommets.add(new Sommet(3, lieu3, date3, EspeceObservee.HIPPOCAMPE));
        sommets.add(new Sommet(4, lieu4, date4, EspeceObservee.LOUTRE));

        Graphe g = new Graphe(sommets, 100);
        System.out.println(g.calculeDegre(1));

        
    }
}
