package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private TableColumn<ObsChouette, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsChouette, TypeObservation> typeObs;
    @FXML
    private TableColumn<ObsChouette, Boolean> protocole;
    @FXML
    private TableColumn<ObsChouette, String> numIndividu;
    @FXML
    private TableView<ObsChouette> tab;
    @FXML
    private Button user;
    @FXML
    private TextField rechercheTF;
    private Utilitaire util = new Utilitaire();

    @FXML
    private Button retour;


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
            }
        }
        


        

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObsChouetteBdd data = new ObsChouetteBdd();

        ArrayList<ObsChouette> obs = data.builder(data.getAllChouetteToBuild());
        
        initializeData(obs);
    }

    private void initializeData(ArrayList<ObsChouette> obs){
        ObservableList<ObsChouette> tr = FXCollections.observableArrayList(obs);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        typeObs.setCellValueFactory(new PropertyValueFactory<>("typeObs"));

        protocole.setCellValueFactory(new PropertyValueFactory<>("protocole"));

        numIndividu.setCellValueFactory(new PropertyValueFactory<>("numIndividu"));

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
        ObsChouetteBdd data = new ObsChouetteBdd();

        ArrayList<ObsChouette> obs = data.builder(data.getFilteredChouette(rechercheString));
        initializeData(obs);
    }

    @FXML
    void retourcons(ActionEvent event) {
        util.changeScene("Consultation");
    }

}
