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
    
    int minDistance(int dist[], Boolean visite[]) {
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v < visite.length; v++)
			if (visite[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}
		return min_index;
	}
    
    public int[] dijkstra(int adjacence[][], int source) {
		int dist[] = new int[adjacence.length]; 

		Boolean visite[] = new Boolean[adjacence.length];
		
		for (int i = 0; i < adjacence.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			visite[i] = false;
		}
		dist[source] = 0;

		
		for (int count = 0; count < adjacence.length - 1; count++) {
			int u = minDistance(dist, visite);
			System.out.println(u+""+dist[u]);
			visite[u] = true;
			for (int v = 0; v < adjacence.length; v++){
				//Si le sommet v n'est pas visité et que le chemin u -> v existe
				if (!visite[v] && adjacence[u][v] != 0 && dist[u] != Integer.MAX_VALUE){
					// Si le chemin u -> v est plus court que le chemin actuel
					if(dist[u] + adjacence[u][v] < dist[v]){
						dist[v] = dist[u] + 1;
					}
				}
			}
		}

		return dist;
	}
    
    
    public int excentricite(int idSom) {
        int ret = 0;
        if(estDansGraphe(idSom) && this.estConnexe()){
            int[][] adj = this.matriceAdjacence();
            int[][] matrice = new int[adj.length][adj[0].length - 1];
            int x = 0;
            for(int i = 0; i < adj.length; i++){
                if(adj[i][0] == idSom){
                    x = i;
                }
                for(int j = 1; j < adj[0].length; j++){
                    matrice[i][j - 1] = adj[i][j];
                }
            }
            for(int i = 0; i < matrice[x].length; i++){
                if(matrice[x][i] > ret){
                    ret = matrice[x][i];
                }
            }
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
                int excent = excentricite(s.getId());
                if(ret < excent) {
                    ret = excent;
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
    
    
    public int calculeDist(int idSom1, int idSom2) {
        int ret = -1;
        if(estDansGraphe(idSom1) && estDansGraphe(idSom2)){
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
        // System.out.println(Arrays.deepToString(B));
        if (n % 2 == 1) {
            B = multiplication_matrix(B, A);
        }
        return B;
    }
    
}
