package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.donnee.IndiceLoutre;
import Modele.donnee.ObsLoutre;
import Modele.donnee.Observation;
import Modele.requete.ObsLoutreBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;

public class ListenerAfficheAllLoutre implements Initializable{
    @FXML
    private TableColumn<ObsLoutre, Date> date;
    @FXML
    private TableColumn<ObsLoutre, IndiceLoutre> indice;
    @FXML
    private TableColumn<ObsLoutre, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsLoutre, String> commune;
    @FXML
    private TableView<ObsLoutre> tab;
    @FXML
    private TextField rechercheTF;
    private Utilitaire util = new Utilitaire();

    @FXML
    private Button retour;


    protected void load(){

    }


    @FXML
    void chooseObs(MouseEvent event) {

        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                ObsLoutre l = tab.getSelectionModel().getSelectedItem();
                Scene sc = ((Node) event.getSource()).getScene();
                Parent root;
                
                String url = "..//View//frame//AffichageObservationLoutre.fxml";
                try {
                    // change the scene
                    FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
                    root = fx.load();
                    ListenerObsLoutre lu = fx.getController();
                    lu.load(l);
                    sc.setRoot(root);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Double clicked");
            }
        }
        


        

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObsLoutreBdd data = new ObsLoutreBdd();

        ArrayList<ObsLoutre> obs = data.builder(data.getAllLoutreToBuild());
        
        initializeData(obs);
    }

    private void initializeData(ArrayList<ObsLoutre> obs){
        ObservableList<ObsLoutre> tr = FXCollections.observableArrayList(obs);

        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        indice.setCellValueFactory(new PropertyValueFactory<>("indice"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        commune.setCellValueFactory(new PropertyValueFactory<>("commune"));

        tab.setItems(tr);
    }

    @FXML
    void recherche(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            filtre();
        }
    }




    void filtre(){
        String rechercheString = rechercheTF.getText();
        ObsLoutreBdd data = new ObsLoutreBdd();

        ArrayList<ObsLoutre> obs = data.builder(data.getFilteredLoutre(rechercheString));
        initializeData(obs);
    }

    @FXML
    void retourcons(ActionEvent event) {
        util.changeScene("Consultation", retour);
    }

}
