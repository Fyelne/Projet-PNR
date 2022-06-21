package Controller;

import java.util.ArrayList;

import Modele.donnee.Observateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class ListenerObs {
    @FXML
    public abstract void  addObservateur(ActionEvent event);

    public abstract void setListDesObs(ArrayList<Observateur> o);

    public abstract void addObs(ActionEvent event);
    
}
