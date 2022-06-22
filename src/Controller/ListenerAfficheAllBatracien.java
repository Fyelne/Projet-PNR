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
import javafx.scene.layout.HBox;

public class ListenerAfficheAllBatracien implements Initializable{

    @FXML
    private HBox HMenu;
    @FXML
    private Button menu;
    @FXML
    private Button quit;
    @FXML
    private Button user;
    @FXML
    private Button admin;
    @FXML
    private TableColumn<ObsBatracien, Date> date;
    @FXML
    private TableColumn<ObsBatracien, Time> heure;
    @FXML
    private TableColumn<ObsBatracien, Integer> id;
    @FXML
    private TableColumn<ObsBatracien, EspeceBatracien> espece;
    @FXML
    private TableView<ObsBatracien> tab;
    @FXML
    private TextField rechercheTF;
    @FXML
    private ComboBox<String> especeCB;
    private Utilitaire util = new Utilitaire();

    @FXML
    private Button retour;


    /**
     * permet de savoir 
     * @param event
     */
    @FXML
    void chooseObs(MouseEvent event) {

        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                ObsBatracien l = tab.getSelectionModel().getSelectedItem();
                Scene sc = ((Node) event.getSource()).getScene();
                Parent root;
                
                String url = "..//View//frame//AffichageObservationBatracien.fxml";
                try {
                    // change the scene
                    FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
                    root = fx.load();
                    ListenerAffichageObsBatracien lu = fx.getController();                    
                    lu.getControl(tab.getSelectionModel().getSelectedItem());
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

        ObservableList<String> especeList = FXCollections.observableArrayList(data.getAllBatracienEspece());
        especeCB.setItems(especeList);
        especeCB.getSelectionModel().select("");
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
        String especeString = especeCB.getSelectionModel().getSelectedItem();
        ObsBatracienBdd data = new ObsBatracienBdd();

        ArrayList<ObsBatracien> obs = data.builder(data.getFilteredBatracien(rechercheString, especeString));
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
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsBatracien");
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
