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
        //Sommet 
        //Création de 4 sommets
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|\t Test des classes Graphe et Sommet \t\t |");
        System.out.println("|--------------------------------------------------------|");

        System.out.println("\r\n \t - 1 : Sommet ");
        System.out.println("|--------------------------------------------------------|");

        System.out.println("| - Création de 4 Sommets : ");

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

        ObsChouette obsChouette = new ObsChouette(3, new Date(1000), new Time(1), lieu3, observateurs, TypeObservation.SONORE_VISUELLE);
        Sommet s1 = new Sommet(1, lieu1, date1, EspeceObservee.BATRACIEN);
        Sommet s2 = new Sommet(2, lieu2, date2, EspeceObservee.GCI);
        
        Sommet s3 = new Sommet(obsChouette);
        Sommet s4 = new Sommet(4, lieu4, date4, EspeceObservee.LOUTRE);

        System.out.println("\t Sommets Crées ");
        System.out.println("|--------------------------------------------------------|");

        System.out.println("| - Test de méthodes de Sommet : ");
        System.out.print("| - La distance entre le sommet 1 et le sommet 2 est de : " + s1.calculeDist(s2));
        //test
        double ret = Math.sqrt(Math.pow(lieu1.getxCoord() - lieu2.getxCoord(), 2) 
        + Math.pow(lieu1.getyCoord() - lieu2.getyCoord(), 2));
        test(s1.calculeDist(s2), ret);

        System.out.print("| - ID du Sommet : " + s4.getId());
        test(s4.getId(), 4);

        Lieu recup = s2.getCoordLieu();
        System.out.print("| - Récupération des coordonnées : L_X : " + recup.getxCoord() + " / " + "L_Y  : " + recup.getyCoord());
        test(recup.getxCoord(), 1.0); test(recup.getyCoord(), 2.0);
        
        System.out.println("\t\t FIN test Sommet");

        System.out.println("|--------------------------------------------------------|");
        System.out.println("|\t\t 2- Test de Graphe \t\t\t |");
        System.out.println("|--------------------------------------------------------|");

        System.out.println("| - Création de Graphe : ");
        System.out.println("|--------------------------------------------------------|");

        // AJout des sommmets
        ArrayList<Sommet> sommets = new ArrayList<Sommet>();
        sommets.add(s1);
        sommets.add(s2);
        sommets.add(s3);
        sommets.add(s4);
        System.out.println("\t - Ajout des Sommet ");

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
        System.out.println("\t - Ajout des voisins de chaque sommet");
        HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>();
        sommetsVoisins.put(s1, sVoisin1);
        sommetsVoisins.put(s2, sVoisin2);
        sommetsVoisins.put(s3, sVoisin3);
        sommetsVoisins.put(s4, sVoisin4);
        System.out.println("\t - Ajout des Sommet et de leur voisin dans la Hashmap");
        
        g = new Graphe(sommetsVoisins);
        System.out.println("|--------------------------------------------------------|");
        System.out.println("| - Création du graphe g réussite");
        System.out.println("|--------------------------------------------------------|");

        System.out.println("| - Duplication du graphe ");
        Graphe g2 = new Graphe(g);
        System.out.println("| - Nouveau graphe avec les même caractéristique que g");

        System.out.println("|--------------------------------------------------------|");
        System.out.println("| - Création d'un graphe par rapport à la distance");
        Graphe g3 = new Graphe(sommets, 1.5);
        System.out.println("| - Graphe g3 crée");
        System.out.println("|--------------------------------------------------------|");

        System.out.print("| - Le graphe contient " + g.nbSommets() + " sommets");
        test(g.nbSommets(), 4);

        System.out.print("| - Le graphe contient " + g.nbAretes() + " arêtes");
        test(g.nbAretes(), 3);

        System.out.print("| - Le sommet avec l'id 2 est dans le graphe : " + g.estDansGraphe(2));
        System.out.println(" -> " + (g.estDansGraphe(2) == true ));

        System.out.print("| - Le sommet avec l'id 12 est dans le graphe : " + g.estDansGraphe(12));
        System.out.println(" -> " + (g.estDansGraphe(12) == false ));


        System.out.print("| - Le sommet 2 a un degré de " + g.calculeDegre(2));
        test(g.calculeDegre(2), 2);

        System.out.println("Le sommet avec le degré maximum est le sommet " + g.somMaxDegre().getId());
        System.out.println("|--------------------------------------------------------|");   

        
        System.out.println("|--------------------------------------------------------|");
        HashMap<Sommet, Integer> deg = g.calculeDegres();
        System.out.println("| - Liste des sommets et de leur degré : ");
        for(Map.Entry<Sommet, Integer> m : deg.entrySet()){
            System.out.println(" \t - Sommet : " + m.getKey().getId() + " \t Degre : " + m.getValue());
        }
        System.out.println("|--------------------------------------------------------|");

        System.out.println("| - Les sommets 1 et 2 sont voisins -> " + g.sontVoisins(1,2)  + " \t OK ?-> " + (g.sontVoisins(1, 2)== true));
        System.out.println("| - Les sommets 1 et 4 sont voisins -> " + g.sontVoisins(1,4)  + " \t OK ?-> " + (g.sontVoisins(1, 4)== false));


        System.out.println("|--------------------------------------------------------|");  

        

        System.out.println("| - Voisins du sommet 3 : ");
        ArrayList<Sommet> v = g.voisins(3);
        int[] res = {2,4};
        int j = 0;
        for(Sommet s : v){
            System.out.print("\t - Sommet " + s.getId());
            test(s.getId(), res[j]);
            j = j + 1;
        }
        
        System.out.println("|--------------------------------------------------------|");

        System.out.println("| - Suppression d'une arête entre 2 et 3");
        System.out.print("| - Suppression réussite ? -> ");
        System.out.println(g.retireArete(2, 3));

        System.out.println("| - Test de la suppression");
        System.out.println("| - test 2 voisins de 3 -> " + g.sontVoisins(2, 3));

        System.out.println("|--------------------------------------------------------|");

        System.out.print("| - Il existe un chemin entre le sommet 1 et le 2 : " + g.existeChemin(1, 2));
        System.out.println(" -> " + (g.existeChemin(1, 2) == true));
        System.out.print("| - Il existe un chemin entre le sommet 1 et le 4 : " + g.existeChemin(1, 4));
        System.out.println(" -> " + (g.existeChemin(1, 4) == false));

        System.out.println("|--------------------------------------------------------|");

        System.out.println("| - matrice d'adjacence du graphe : ");
        int[][] mat = g.matriceAdjacence();
        for(int a = 0; a < mat.length; a ++){
            System.out.print(" | " );
            for(int b = 0; b < mat[a].length; b++){
                System.out.print(mat[a][b] + " | ");
            }
            System.out.println();
        }

        System.out.println("|--------------------------------------------------------|");


        System.out.print("| - Le graphe est connexe : " + g.estConnexe());
        System.out.println(" -> " + (g.estConnexe() == false));
        System.out.println("| - Affichage des composantes connexes : ");
        ArrayList<Graphe> compoCo = g.composanteConnexe();
        int i = 1;
        for(Graphe compo : compoCo){
            System.out.println("| - Composante connexe " + i);
            System.out.println(compo);
            i = i + 1;
        }
        
        System.out.println("|--------------------------------------------------------|");

        System.out.println("| - Rajout de l'arete entre 2 et 3 pour refaire un graphe connexe ");
        g.ajouteArete(2, 3);
        System.out.print("| - Vérification de la création : -> " + g.sontVoisins(2, 3));
        System.out.println(" -> " + (g.sontVoisins(2, 3) == true));

        System.out.println("|--------------------------------------------------------|");
        System.out.print("| - Le graphe est maintenant connexe : " +  g.estConnexe());
        System.out.println(" -> " + (g.estConnexe() == true));

        System.out.println("|--------------------------------------------------------|");

        //System.out.print("| - Distance entre le sommet 1 et le sommet 4 : " + g.distAretes(1, 4));
        //test(g.distAretes(1, 4), 3);
        System.out.println("|--------------------------------------------------------|");

        System.out.print("| -  Excentricité du sommet 2 : " + g.excentricite(2));
        test(g.excentricite(2), 2);;

        System.out.print("| - Le diametre du graphe est de : " + g.diametre());
        test(g.diametre(), 3);

        System.out.println("| - Le rayon du graphe est de : " + g.rayon());
        test(g.rayon(), 2);

        System.out.println("|--------------------------------------------------------|");
        System.out.println("|\t\t Fin du Scenario Traitement \t\t |");
        System.out.println("|--------------------------------------------------------|");
        
        /* 
        int[] dij = g.dijkstra(g.matriceAdjacence(), 4);
        for(int c = 0; c < dij.length; c++){
            System.out.print(dij[c] + " ; ");
        }
        */
    }
   

    



    public static void test(double res, double attendu ){
        if(res == attendu){
            System.out.println(" -> OK");
        }else{
            System.err.println(" -> ERREUR");
        }
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
