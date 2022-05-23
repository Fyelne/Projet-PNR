package Modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Graphe {
    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;
    
    public Graphe(ArrayList<Sommet> sommets, double dist) {
        if(sommets != null && dist >= 0) {
            this.sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>();
            for(Sommet s : sommets) {
                ArrayList<Sommet> voisins = new ArrayList<Sommet>();
                for(Sommet s2 : sommets) {
                    if(s.calculDist(s2) <= dist) {
                        voisins.add(s2);
                    }
                }
                this.sommetsVoisins.put(s, voisins);
            }
        }
        
    }
    
    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins) {
        if(sommetsVoisins != null) {
            this.sommetsVoisins = somVoisins;
        } else {
            throw new IllegalArgumentException("Graphe null");
        }
    }
    
    public Graphe(Graphe g) {
        if(g != null) {
            this.sommetsVoisins = g.getSommetsVoisins();
        }
    }
    
    public HashMap<Sommet, ArrayList<Sommet>> getSommetsVoisins() {
        return this.sommetsVoisins;
    }
    
    public int nbSommets() {
        return this.sommetsVoisins.size();
    }
    
    public int nbAretes() {
        return 0;
    }
    
    public boolean estDansGraphe(int idSom) {
        boolean ret = false;
        int i = 0;
        
        
        return ret;
    }
    
    public int calculeDegre(int idSom) {
        return 0;
    }
    
    public HashMap<Sommet, Integer> calculeDegres() {
        return null;
    }
    
    public Sommet somMaxDegre() {
        int maxDegre = 0;
        Sommet ret = null;
        for(Sommet s : this.sommetsVoisins.keySet()) {
            int degre = this.calculeDegre(s.getId());
            if(degre > maxDegre) {
                maxDegre = degre;
                ret = s;
            }
        }

        return ret;
    }
    
    public boolean sontVoisins(int idSom1, int idSom2) {
        boolean ret = false;
        // if(this.sommetsVoisins.containsKey(idSom1) && this.sommetsVoisins.containsKey(idSom2)) {
        //     ret = this.sommetsVoisins.get(idSom1).contains(idSom2);
        // }

        return ret;
    }
    
    public boolean existeChemin(int idSom1, int idSom2) {
        return false;
    }
    
    public ArrayList<Sommet> voisins(int idSom) {
        return null;
    }
    
    public boolean ajouteArete(int idSom1, int idSom2) {
        return false;
    }
    
    public boolean retireArete(int idSom1, int idSom2) {
        return false;
    }
    
    public int[][] matriceAdjacence() {
        return null;
    }
    
    public boolean estConnexe() {
        return false;
    }
    
    public ArrayList<Graphe> composanteConnexe() {
        return null;
    }
    
    public int distArete(int idSom1, int idSom2) {
        return 0;
    }
    
    public int excentricite(int idSom) {
        return 0;
    }
    
    public int diametre() {
        return 0;
    }
    
    public int rayon() {
        return 0;
    }
    
    public double calculeDist(int idSom1, int idSom2) {
        return 0;
    }
    
    public double excentriciteDist(int idSom) {
        return 0;
    }
    
    public double diametreDist() {
        return 0;
    }
    
    public double rayonDist() {
        return 0;
    }
    
    public double[][] matricePonderation(){
        return null;
    }
    
    public Graphe clotureTransitive() {
        return null;
    }
}
