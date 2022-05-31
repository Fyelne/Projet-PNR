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
        Sommet s1 = new Sommet(1, lieu1, date1, EspeceObservee.BATRACIEN);
        Sommet s2 = new Sommet(2, lieu2, date2, EspeceObservee.GCI);
        Sommet s3 = new Sommet(3, lieu3, date3, EspeceObservee.HIPPOCAMPE);
        Sommet s4 = new Sommet(4, lieu4, date4, EspeceObservee.LOUTRE);

        sommets.add(s1);
        sommets.add(s2);
        sommets.add(s3);
        sommets.add(s4);
        /*
        Graphe g = new Graphe(sommets, 1);
        System.out.println(g.calculeDegre(1));
        int[][] adjacence = g.matriceAdjacence();
        for (int[] ligne : adjacence) {
            System.out.println(Arrays.toString(ligne));
        }*/


        //test du DFS 
        ArrayList<Sommet> sVoisin1 = new ArrayList<Sommet>();
        ArrayList<Sommet> sVoisin2 = new ArrayList<Sommet>();
        ArrayList<Sommet> sVoisin3 = new ArrayList<Sommet>();
        ArrayList<Sommet> sVoisin4 = new ArrayList<Sommet>();
        
        sVoisin1.add(s2);
        
        sVoisin2.add(s1);
        sVoisin2.add(s3);

        sVoisin3.add(s2);
        sVoisin3.add(s4);

        sVoisin4.add(s3);

        sommetsVoisins.put(s1, sVoisin1);
        sommetsVoisins.put(s2, sVoisin2);
        sommetsVoisins.put(s3, sVoisin3);
        sommetsVoisins.put(s4, sVoisin4);

        

        Graphe g2 = new Graphe(sommetsVoisins);

        for(Sommet s : g2.voisins(2)){
            System.out.println(s.getId());
        }
       
        
        ArrayList<Sommet> DFS = g2.LaunchDFS(1);
        
        for(Sommet s : DFS){
            System.out.print(s.getId() + "/");
        }
        System.out.println();

        System.out.println(g2.estConnexe());

        ArrayList<Graphe> compo = g2.composanteConnexe();

        for(Graphe gra : compo){
            HashMap<Sommet, ArrayList<Sommet>> liste = gra.getSommetsVoisins();

            for(Map.Entry<Sommet, ArrayList<Sommet>> l : liste.entrySet()){
                Sommet somme = l.getKey();
                System.out.print(somme.getId() + "/");
            }
            System.out.println(" \t compo");
        }

        System.out.println(g2.distArete(1, 4));
    }
}
