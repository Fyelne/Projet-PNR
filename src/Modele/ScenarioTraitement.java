package Modele;

import Modele.traitement.*;
import Modele.donnee.*;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

public class ScenarioTraitement {
    static Graphe g;
    static Graphe g2;

    //test de la classe Graphe et Sommet 
    public static void main(String[] args) {
        creationGraphe();
        //Sommet 
        //Création de 4 sommets
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|\t Test des classes Graphe et Sommet \t\t |");
        System.out.println("|--------------------------------------------------------|");

        System.out.println("\r\n \t - 1 : Sommet ");
        System.out.println("|--------------------------------------------------------|");

        System.out.println("- Création de 4 Sommets : ");

        Lieu lieu1 = new Lieu(1,1);
        Lieu lieu2 = new Lieu(1,2);
        Lieu lieu3 = new Lieu(3,3);
        Lieu lieu4 = new Lieu(4,4);

        Date date1 = new Date(0);
        Date date2 = new Date(86400000);
        Date date3 = new Date(86400000*2);
        Date date4 = new Date(86400000*3);

        ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
        observateurs.add(new Observateur(1, "Noel", "Andy"));
        observateurs.add(new Observateur(2, "Auger", "Mateo"));

        ObsChouette obsChouette = new ObsChouette(1, new Date(1000), new Time(1), lieu3, observateurs, TypeObservation.SONORE_VISUELLE);
        Sommet s1 = new Sommet(1, lieu1, date1, EspeceObservee.BATRACIEN);
        Sommet s2 = new Sommet(2, lieu2, date2, EspeceObservee.GCI);
        
        Sommet s3 = new Sommet(obsChouette);
        Sommet s4 = new Sommet(4, lieu4, date4, EspeceObservee.LOUTRE);

        System.out.println("\t Sommets Crées ");
        System.out.println("|--------------------------------------------------------|");

        System.out.println(" - Test de méthodes de Sommet : ");
        System.out.print("La distance entre le sommet 1 et le sommet 2 est de : " + s1.calculeDist(s2));
        //test
        double ret = Math.sqrt(Math.pow(lieu1.getxCoord() - lieu2.getxCoord(), 2) 
        + Math.pow(lieu1.getyCoord() - lieu2.getyCoord(), 2));
        test(s1.calculeDist(s2), ret);

        System.out.print("ID du Sommet : " + s4.getId());
        test(s4.getId(), 4);

        Lieu recup = s2.getCoordLieu();
        System.out.print("Récupération des coordonnées : L_X : " + recup.getxCoord() + " / " + "L_Y  : " + recup.getyCoord());
        test(recup.getxCoord(), 1.0); test(recup.getyCoord(), 2.0);
        
        System.out.println("\t\t FIN test Sommet");

        System.out.println("|--------------------------------------------------------|");
        System.out.println("|\t\t Test de Graphe \t\t\t |");
        System.out.println("|--------------------------------------------------------|");



        // Test calcul degré
        System.out.println("Voisins du sommet 1 : " + arrayIdtoString(g.voisins(1)));
        System.out.println("Degre du sommet 1 : " + g.calculeDegre(1));
        System.out.println("Excentricité du sommet 1 : " + g.excentricite(1) + "\n");

        //Test Matrice Adjacence
        System.out.println("Matrice d'adjacence : ");
        int[][] adjacence = g.matriceAdjacence();
        for (int[] ligne : adjacence) {
            System.out.println(Arrays.toString(ligne));
        }
        System.out.println();

        //Test DFS
        System.out.println("Voisins de 2 : " + arrayIdtoString(g.voisins(2)) + "\n");
       
        System.out.println("Nouveau Graphe \n");
        ArrayList<Sommet> DFS = g2.LaunchDFS(1);
        System.out.print("DFS du sommet 1 : " + arrayIdtoString(DFS) + "\n");

        System.out.println("Test si g2 est connexe(non) : " + g2.estConnexe() + "\n");

        ArrayList<Graphe> compo = g2.composanteConnexe();
        System.out.println("Composantes connexes : ");

        for(Graphe gra : compo){
            HashMap<Sommet, ArrayList<Sommet>> liste = gra.getSommetsVoisins();

            ArrayList<Sommet> listeKeys = new ArrayList<Sommet>(liste.keySet());
            System.out.println("\tComposante connexe : " + arrayIdtoString(listeKeys));
        }

        System.out.println();
    }

    public static void test(double res, double attendu ){
        if(res == attendu){
            System.out.println(" -> OK");
        }else{
            System.err.println(" -> ERREUR");
        }
    }

    public static void creationGraphe(){
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
        
        g = new Graphe(sommets, 2);

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

        g2 = new Graphe(sommetsVoisins);

    }

    public static String arrayIdtoString(ArrayList<Sommet> liste){
        String s = "[";
        for(Sommet sommet : liste){
            s += sommet.getId() + ", ";
        }
        s = s.substring(0, s.length()-2);
        s += "]";
        
        return s;
    }
}
