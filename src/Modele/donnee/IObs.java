package Modele.donnee;

import java.util.ArrayList;

public interface IObs<T>{
    /**
     * Permets d'ajouter une observation à la liste
     * @param obs observation à ajouter
     */
    public void ajouteUneObs(T obs);
    /**
     * Permets d'ajouter plusieurs observations à la liste 
     * @param obs liste des observation à observer
     */
    public void ajoutePlsObs(ArrayList<T> obs);
    /**
     * Vide la liste des observation
     */
    public void videObs();
    /**
     * Retire une obs de la liste
     * @param idObs id de l'observation à supprimer
     * @return true si réussi sinon false
     */
    public boolean retireObs(int idObs);
    /**
     * donne le nombre d'obs dans la liste
     * @return le nombre d'obs dans la liste 
     */
    public int nbObs();


}
