package Modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Graphe {
    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;
    
    public Graphe(ArrayList<Sommet> sommets, double dist) {
        if(sommets != null && dist >= 0) {
            this.sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>();
            for(Sommet s : sommets) {
                ArrayList<Sommet> voisins = new ArrayList<Sommet>();
                for(Sommet s2 : sommets) {
                    if(s.calculeDist(s2) <= dist) {
                        voisins.add(s2);
                    }
                }
                this.sommetsVoisins.put(s, voisins);
            }
            
            System.out.println("Graphe construit");
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
        int ret = 0;
        //forearch key 
        for (Sommet s : this.sommetsVoisins.keySet()) {
            ret+= this.sommetsVoisins.get(s).size();
        }
        
        ret = ret / 2;
        return ret;
    }
    
    public boolean estDansGraphe(int idSom) {
        boolean ret = false;
        if(this.searchSommet(idSom) != null) {
            ret = true;
        }
        
        return ret;
    }
    
    public int calculeDegre(int idSom) {
        int ret = 0;
        if(this.estDansGraphe(idSom)) {
            Sommet s = searchSommet(idSom);
            
            ret = this.sommetsVoisins.get(s).size();
        }
        
        return ret;
    }
    
    public HashMap<Sommet, Integer> calculeDegres() {
        HashMap<Sommet, Integer> ret = new HashMap<Sommet, Integer>();
        for (Sommet s : this.sommetsVoisins.keySet()) {
            ret.put(s, this.sommetsVoisins.get(s).size());
        }
        
        return ret;
    }
    
    public Sommet somMaxDegre() {
        int maxDegre = 0;
        Sommet ret = null;
        for(Sommet s : this.sommetsVoisins.keySet()) {
            int degre = this.sommetsVoisins.get(s).size();
            if(degre > maxDegre) {
                maxDegre = degre;
                ret = s;
            }
        }
        
        return ret;
    }
    
    public boolean sontVoisins(int idSom1, int idSom2) {
        boolean ret = false;
        if(this.estDansGraphe(idSom1) && this.estDansGraphe(idSom2)) {
            Sommet s1 = searchSommet(idSom1);
            Sommet s2 = searchSommet(idSom2);
            if(this.sommetsVoisins.get(s1).contains(s2)) {
                ret = true;
            }
        }
        
        return ret;
    }
    
    public boolean existeChemin(int idSom1, int idSom2) {
        return false;
    }
    
    public ArrayList<Sommet> voisins(int idSom) {
        ArrayList<Sommet> ret = new ArrayList<Sommet>();
        Set<Sommet> keys = this.sommetsVoisins.keySet();
        Iterator<Sommet> it = keys.iterator();
        boolean trouve = false;
        
        while(it.hasNext() && !trouve) {
            Sommet s = null;
            s = it.next();
            if(s.getId() == idSom) {
                ret = this.sommetsVoisins.get(s);
                trouve = true;
            }
        }
        
        return ret;
    }
    
    public boolean ajouteArete(int idSom1, int idSom2) {
        boolean ret = false;
        if(this.estDansGraphe(idSom1) && this.estDansGraphe(idSom2)) {
            Sommet s1 = searchSommet(idSom1);
            Sommet s2 = searchSommet(idSom2);
            if(!this.sommetsVoisins.get(s1).contains(s2)) {
                this.sommetsVoisins.get(s1).add(s2);
                this.sommetsVoisins.get(s2).add(s1);
                ret = true;
            }
        }
        
        return ret;
    }
    
    public boolean retireArete(int idSom1, int idSom2) {
        boolean ret = false;
        if(this.estDansGraphe(idSom1) && this.estDansGraphe(idSom2)) {
            Sommet s1 = searchSommet(idSom1);
            Sommet s2 = searchSommet(idSom2);
            if(this.sommetsVoisins.get(s1).contains(s2)) {
                this.sommetsVoisins.get(s1).remove(s2);
                this.sommetsVoisins.get(s2).remove(s1);
                ret = true;
            }
        }
        
        return ret;
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
        double ret = -1;
        if(this.estDansGraphe(idSom1) && this.estDansGraphe(idSom2)) {
            
        }

        return ret;
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
    
    public Sommet searchSommet(int idSom) {
        Sommet s = null;
        Set<Sommet> keys = this.sommetsVoisins.keySet();
        Iterator<Sommet> it = keys.iterator();
        boolean trouve = false;
        
        while(it.hasNext() && !trouve) {
            Sommet sCourant = null;
            sCourant = it.next();
            if(sCourant.getId() == idSom) {
                s = sCourant;
                trouve = true;
            }
        }
        
        return s;
    }
}
