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
     * Il fixe la liste des observateurs.
     * 
     * @param o la liste des observateurs
     */
    public abstract void setListDesObs(ArrayList<Observateur> o);

    /**
     * Ajoute une observation
     * @param event L'événement qui a déclenché l'action.
     */
    public abstract void addObs(ActionEvent event);

    /**
     * 
     * @param v liste des  végétations
     */
    public void setListVege(ArrayList<Vegetation> v){

    }

    /**
     * Modifie la zone humide
     * @param zh zone humide en cours
     */
    public void setZoneHumide(ZoneHumide zh){
    }
    
}
