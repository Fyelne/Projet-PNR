package Controller;

import java.util.ArrayList;

import Modele.donnee.Observateur;
import Modele.donnee.Vegetation;
import Modele.donnee.ZoneHumide;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class ListenerObs {
    /**
     * Ajoute un observateur
     * @param event L'événement qui a déclenché l'action.
     */
    @FXML
    public abstract void  addObservateur(ActionEvent event);


    /**
     * Modifie la liste des observateurs
     * @param ArrayList<Observateur> liste des observateurs
     */
    public abstract void setListDesObs(ArrayList<Observateur> o);

    /**
     * Ajoute une observation
     * @param event L'événement qui a déclenché l'action.
     */
    public abstract void addObs(ActionEvent event);

    /**
     * 
     * @param ArrayList<Vegetation> liste des  végétations
     */
    public void setListVege(ArrayList<Vegetation> v){

    }

    /**
     * Modifie la zone humide
     * @param ZoneHumide zone humide en cours
     */
    public void setZoneHumide(ZoneHumide zh){
    }
    
}
