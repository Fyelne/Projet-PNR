package Modele.traitement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
        }
        
    }
    
    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins) {
        if(somVoisins != null) {
            this.sommetsVoisins = somVoisins;
        } else {
            throw new IllegalArgumentException("Graphe null");
        }
    }
    
    public Graphe(Graphe g) {
        if(g != null) {
            this.sommetsVoisins = g.getSommetsVoisins();
        } else {
            throw new IllegalArgumentException("Graphe null");
        }
    }
    
    public HashMap<Sommet, ArrayList<Sommet>> getSommetsVoisins() {
        return this.sommetsVoisins;
    }
    
    public ArrayList<Sommet> getSommets() {
        ArrayList<Sommet> sommets = new ArrayList<Sommet>(this.sommetsVoisins.keySet());

        //On trie les sommets par id
        sommets.sort(new Comparator<Sommet>() {
            @Override
            public int compare(Sommet o1, Sommet o2) {
                return o1.getId() - o2.getId();
            }
        });
        return sommets;
        
    }
    

    public int nbSommets() {
        return this.sommetsVoisins.size();
    }
    
    public int nbAretes() {
        int ret = 0;

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
        boolean ret = false;
        if(this.estDansGraphe(idSom1) && this.estDansGraphe(idSom2)){
            ArrayList<Sommet> DFS = this.LaunchDFS(idSom1);
            if(DFS.contains(searchSommet(idSom1)) && DFS.contains(searchSommet(idSom2))){
                ret = true;
            }
        }
        return ret;
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
        int[][] ret = new int[this.nbSommets()][this.nbSommets() + 1];
        
        int i = 0;
        int j = 0;

        ArrayList<Sommet> keys = this.getSommets();

        Iterator<Sommet> it = keys.iterator();
        while(it.hasNext()) {
            Sommet s = it.next();
            ret[i][j] = s.getId();
            j++;
            ArrayList<Sommet> voisins = this.sommetsVoisins.get(s);
            for(Sommet voisin : voisins) {
                ret[i][voisin.getId()] = 1;
                j++;
            }
            i++;
            j = 0;
        }
        
        return ret;
    }
    
    public boolean estConnexe() {
        boolean ret = true;
        ArrayList<Sommet> res;
        for(Map.Entry<Sommet, ArrayList<Sommet>> som : this.sommetsVoisins.entrySet()){
            Sommet s = som.getKey();
            res = this.LaunchDFS(s.getId());
            for(Map.Entry<Sommet, ArrayList<Sommet>> check : this.sommetsVoisins.entrySet()){
                if(!(res.contains(check.getKey()))){
                    ret = false;
                }
            }
        }
        return ret;
    }
    
    public ArrayList<Graphe> composanteConnexe() {
        ArrayList<Sommet> allSom = new ArrayList<Sommet>();
        ArrayList<Graphe> ret = new ArrayList<Graphe>();
        //Recupère l'ensemble des sommets du graphes 
        for(Map.Entry<Sommet, ArrayList<Sommet>> som : this.sommetsVoisins.entrySet()){
            allSom.add(som.getKey());
        }

        /*
         * Pour chaque sommet du graphe  : 
         * - Vérifie si le sommet fait déjà partie d'une composante connexe
         * - Si ce n'est pas le cas, récupère tout les sommets de sa composante, 
         *      créer un graphe et l'ajoute à la liste 
         */
        for(Sommet s : allSom){
            boolean dejaDansConnexe = false;
            if(ret != null){
                int i = 0;

                while((!dejaDansConnexe) && i < ret.size()){
                    if(ret.get(i).estDansGraphe(s.getId())){
                        dejaDansConnexe = true;
                    }

                    i = i + 1;
                }
            }
            if(!dejaDansConnexe){
                ArrayList<Sommet> composante = this.LaunchDFS(s.getId());
                HashMap<Sommet, ArrayList<Sommet>> compoGraphe = new HashMap<Sommet, ArrayList<Sommet>>();
                for(Sommet som : composante){
                    compoGraphe.putIfAbsent(som, this.voisins(som.getId()));
                }

                ret.add(new Graphe(compoGraphe));
            }

        }
        return ret;
    }


    
    public int distAretes(int idSom1, int idSom2) {
        int ret = 0;
        //prepare data for launch dikstra
        if(estDansGraphe(idSom1) && estDansGraphe(idSom2)){
            if(existeChemin(idSom1, idSom2)){
                HashMap<Sommet,Integer> dist = new HashMap<Sommet,Integer>();
                ArrayList<Graphe> g = this.composanteConnexe();
                int id = 0;
                for(Graphe parcour : g){
                    if(parcour.estDansGraphe(idSom1) && parcour.estDansGraphe(idSom2)){
                        id = g.indexOf(parcour);
                    }
                }
                Graphe cherche = g.get(id);

                HashMap<Sommet, ArrayList<Sommet>> list = cherche.getSommetsVoisins();
                for(Map.Entry<Sommet, ArrayList<Sommet>> l : list.entrySet()){
                    int in = Integer.MAX_VALUE;
                    Integer m = in;
                    dist.put(l.getKey(), m);
                }
                dist.replace(cherche.searchSommet(idSom1), 0);
                //Launch de dextra
                this.Dijkstra(idSom2, idSom1, dist, cherche, 0);
            }else{
                ret = 0;
            }
        }else{
            ret = -1;
        }
        return ret;
    }

    // need to be fix doesnt work
    private int Dijkstra(int Som2, int SomCourant, HashMap<Sommet,Integer> s, Graphe g, int ret){
        
        for(Map.Entry<Sommet, Integer> l : s.entrySet()){
            System.out.println(l.getKey().getId() + " : " + l.getValue());
        }
        if(!s.containsValue((Integer) Integer.MAX_VALUE) ){
            System.out.println(s.get(g.searchSommet(Som2)));
            ret = s.get(g.searchSommet(Som2));
        }else{
            ArrayList<Sommet> vois = g.voisins(SomCourant);
            int min = Integer.MAX_VALUE;
            int SMin = 0;
            for(Sommet v : vois){
                if(s.get(g.searchSommet(SomCourant)) + 1 < s.get(v) ){
                    s.replace(v, (s.get(g.searchSommet(SomCourant)) + 1));
                }

            }
            System.out.println("Sommet min : " + SMin);
            this.Dijkstra(Som2, SMin, s, g, ret);
        }
        System.out.println(ret);
        return ret;
    }
    


    public int excentricite(int idSom) {
        int ret = 0;
        if(estDansGraphe(idSom) && this.estConnexe()){
            
        }else{
            ret = -1;
        }

        return ret;
    }
    
    public int diametre() {
        int ret = 0;
        if(this.estConnexe()){
            for (Sommet s : this.sommetsVoisins.keySet()){
                int excent = this.excentricite(s.getId());
                if(excent > ret){
                    ret = excent;
                }
            }
        } else {
            ret = -1;
        }

        return ret;
    }
    
    public int rayon() {
        int ret = -1;
        if(this.estConnexe()){
            ret = excentricite(this.sommetsVoisins.keySet().iterator().next().getId());
            for (Sommet s : this.sommetsVoisins.keySet()) {
                int ex = excentricite(s.getId());
                if(ret > ex) {
                    ret = ex;
                }
            }
        }
        return ret;
    }
    

    /*
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    Méthodes non obligatoires à prtir d'ici
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    */


    public double calculeDist(int idSom1, int idSom2) {
        double ret = -1;
        int[][] mat = matriceAdjacence();
        int[][] mat2 = new int[mat.length][mat[0].length - 1];
        int x = 0;
        int y = 0;
        for(int i = 0; i < mat.length; i++){
            if(mat[i][0] == idSom1){
                x = i;
            }
            if(mat[i][0] == idSom2){
                y = i;
            }
            for(int j = 1; j < mat[0].length; j++){
                mat2[i][j - 1] = mat[i][j];
            }
        }
        
        boolean trouve = false;
        int[][] mult = mat2.clone();
        while(ret < mat2.length && !trouve){
            ret++;
            if(mat2[x][y] == 1){
                trouve = true;
            }

            mat2 = multiplication_matrix(mat2, mult);
        }
        if(trouve){
            ret++;
        } else {
            ret = -1;
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

    
    public ArrayList<Sommet> LaunchDFS(int idSom){
        ArrayList<Sommet> parcouru = new ArrayList<Sommet>();
        ArrayList<Sommet> stack = new ArrayList<Sommet>();
        stack.add(this.searchSommet(idSom));
        for(Sommet s : this.voisins(idSom)){
            stack.add(s);
        }
        ArrayList<Sommet> ret = this.DFS(parcouru, stack, 0);
        return ret;
    }

    public ArrayList<Sommet> DFS(ArrayList<Sommet> parcouru, ArrayList<Sommet> stack, int index){ 
        if(index < stack.size()){

            // ajout des voisins a la stack
            ArrayList<Sommet> tampon = this.voisins(stack.get(index).getId());
            ArrayList<Sommet> newStack = new ArrayList<Sommet>(stack);
            for(Sommet s : tampon){
                if(!(parcouru.contains(s))){
                    newStack.add(s);
                }
            }
            if(!(parcouru.contains(stack.get(index)))){
                parcouru.add(stack.get(index));
            }

            index = index + 1;
            this.DFS(parcouru,newStack, index);

        }

        return parcouru;
    }


    public int[][] multiplication_matrix(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public int[][] puissance_matrix(int[][] A, int n) {
        if (n == 1) {
            return A;
        }
        int[][] B = puissance_matrix(A, n / 2);
        B = multiplication_matrix(B, B);
        if (n % 2 == 1) {
            B = multiplication_matrix(B, A);
        }
        return B;
    }

}
