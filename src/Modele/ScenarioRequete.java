package Modele;

import java.util.ArrayList;

import Modele.donnee.ObsLoutre;
import Modele.requete.*;

public class ScenarioRequete {
    public static void main(String[] args) {
        Loutre test = new Loutre();
        ArrayList<ObsLoutre> a = test.builder(test.getAllLoutreToBuild());

        for(ObsLoutre l : a){
            System.out.println(l.getId());
        }
    }
}
