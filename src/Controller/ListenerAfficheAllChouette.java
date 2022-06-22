package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.donnee.IndiceLoutre;
import Modele.donnee.*;
import Modele.requete.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;

public class ListenerAfficheAllChouette implements Initializable{
    @FXML
    private TableColumn<ObsChouette, Date> date;
    @FXML
    private TableColumn<ObsChouette, IndiceLoutre> indice;
    @FXML
    private TableColumn<ObsChouette, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsChouette, String> commune;
    @FXML
    private TableView<ObsChouette> tab;
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
                ObsChouette l = tab.getSelectionModel().getSelectedItem();
                Scene sc = ((Node) event.getSource()).getScene();
                Parent root;
                
                String url = "..//View//frame//AffichageObservationHippocampe.fxml";
                try {
                    // change the scene
                    FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
                    root = fx.load();
                    //ListenerObs lu = fx.getController();
                    //lu.load(l);
                    sc.setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Double clicked");
            }
        }
        


        

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObsHippocampeBdd data = new ObsHippocampeBdd();

       // ArrayList<ObsHippocampe> obs = data.builder(data.getAllHippocampeToBuild());
        
        //initializeData(obs);
    }

    private void initializeData(ArrayList<ObsLoutre> obs){
        ObservableList<ObsLoutre> tr = FXCollections.observableArrayList(obs);

        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        indice.setCellValueFactory(new PropertyValueFactory<>("indice"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        commune.setCellValueFactory(new PropertyValueFactory<>("commune"));

       // tab.setItems(tr);
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
        util.changeScene("Consultation");
    }

}
