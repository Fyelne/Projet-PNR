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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;

public class ListenerAfficheAllGCI implements Initializable{

    @FXML
    private HBox HMenu;
    @FXML
    private Button quit;
    @FXML
    private Button user;
    @FXML
    private Button admin;

    @FXML
    private TableColumn<ObsGCI, Date> date;
    @FXML
    private TableColumn<ObsGCI, Time> heure;
    @FXML
    private TableColumn<ObsGCI, Integer> id;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsGCI, ContenuNid> nature;
    @FXML
    private TableColumn<ObsGCI, Integer> nombre;
    @FXML
    private TableColumn<ObsGCI, Integer> idNid;
    @FXML
    private TableView<ObsGCI> tab;
    @FXML
    private TextField rechercheTF;
    @FXML
    private ComboBox<String> natureCB;
    @FXML 
    private ComboBox<String> nombreCB;

    private Utilitaire util = new Utilitaire();

    @FXML
    private Button retour;

    @FXML
    void chooseObs(MouseEvent event) {

        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                ObsGCI l = tab.getSelectionModel().getSelectedItem();
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
        ObsGCIBdd data = new ObsGCIBdd();

        ArrayList<ObsGCI> obs = data.builder(data.getAllGCIToBuild());

        initializeData(obs);

        ObservableList<String> natureList = FXCollections.observableArrayList(data.getAllGCINature());
        natureCB.setItems(natureList);
        natureCB.getSelectionModel().select("");

        ObservableList<String> nombreList = FXCollections.observableArrayList(data.getAllGCINombre());
        nombreCB.setItems(nombreList);
        nombreCB.getSelectionModel().selectFirst();
    }

    private void initializeData(ArrayList<ObsGCI> obs){
        ObservableList<ObsGCI> tr = FXCollections.observableArrayList(obs);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        nature.setCellValueFactory(new PropertyValueFactory<>("natureNid"));

        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        idNid.setCellValueFactory(new PropertyValueFactory<>("leNid"));

        tab.setItems(tr);
    }

    @FXML
    void recherche(KeyEvent event){
        TextField source = (TextField)event.getSource();
        if(event.getCode().equals(KeyCode.ENTER) || source.getText().equals("")){
            filtre();
        }
    }

    @FXML
    void comboBoxChange(ActionEvent event){
        filtre();
    }

    void filtre(){
        String rechercheString = rechercheTF.getText();
        ObsGCIBdd data = new ObsGCIBdd();
        String nature = natureCB.getSelectionModel().getSelectedItem();
        String nombre = nombreCB.getSelectionModel().getSelectedItem();

        ArrayList<ObsGCI> obs = data.builder(data.getFilteredGCI(rechercheString, nature, nombre));
        initializeData(obs);
    }

    @FXML
    void retourcons(ActionEvent event) {
        util.changeScene("Consultation");
    }

    //code du Menu
    /**
     * Affiche le menu
     * @param event le bouton cliqué
     */
    @FXML
    void openMenu(ActionEvent event) {
        HMenu.setVisible(true);
    }

    /**
     * Quitte le menu
     * @param event le bouton cliqué
     */
    @FXML
    void quitMenu(ActionEvent event) {
        HMenu.setVisible(false);
    }

    /**
     * Permet d'acceder au menu qui donne des informations sur l'utilisateur 
     * @param event le bouton cliqué
     */
    @FXML
    void openUserMenu(ActionEvent event) {
        Button bt = (Button) event.getSource();
        Scene sc = bt.getScene();
        Parent root;
        
        String url = "..//View//frame//InfoUser.fxml";
        try {
            // change the scene
            FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
            root = fx.load();
            ListenerUtilisateur lu = fx.getController();
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsGravelot");
            sc.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de changer de scene et d'acceder à la page Admin
     * @param event le bouton cliqué
     */
    @FXML
    void goToAdmin(ActionEvent event) {
        util.changeScene("Admin");
    }

    /**
     * Permet de changer de scene et d'acceder à la page Accueil
     * @param event le bouton cliqué
     */
    @FXML
    void goToAccueil(ActionEvent event) {
        util.changeScene("Accueil");
    }

    /**
     * Permet de changer de scene et d'acceder à la page ChoixAjouter
     * @param event le bouton cliqué
     */
    @FXML
    void goToAddDonnee(ActionEvent event) {
        util.changeScene("ChoixAjouter");
    }

    /**
     * Permet de changer de scene et d'acceder à la page Consultation
     * @param event le bouton cliqué
     */
    @FXML
    void goToChoixReleve(ActionEvent event) {
        util.changeScene("Consultation");
    }

}
