package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
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

public class ListenerAfficheAllBatracien implements Initializable{
    @FXML
    private TableColumn<ObsBatracien, Date> date;
    @FXML
    private TableColumn<ObsBatracien, Time> heure;
    @FXML
    private TableColumn<ObsBatracien, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsBatracien, EspeceBatracien> espece;
    @FXML
    private TableView<ObsBatracien> tab;
    @FXML
    private TextField rechercheTF;
    private Utilitaire util = new Utilitaire();

    @FXML
    private Button retour;



    @FXML
    void chooseObs(MouseEvent event) {

        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                ObsBatracien l = tab.getSelectionModel().getSelectedItem();
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
        ObsBatracienBdd data = new ObsBatracienBdd();

       ArrayList<ObsBatracien> obs = data.builder(data.getAllBatracienToBuild());
        
        initializeData(obs);
    }

    private void initializeData(ArrayList<ObsBatracien> obs){
        ObservableList<ObsBatracien> tr = FXCollections.observableArrayList(obs);

        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        espece.setCellValueFactory(new PropertyValueFactory<>("espece"));

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
        ObsBatracienBdd data = new ObsBatracienBdd();

        // ArrayList<ObsBatracien> obs = data.builder(data.getFilteredBatracien(rechercheString));
        // initializeData(obs);
    }

    @FXML
    void retourcons(ActionEvent event) {
        util.changeScene("Consultation");
    }

}
