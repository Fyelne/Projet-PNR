package Controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.HBox;

public class ListenerAfficheAllChouette implements Initializable{
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
    private TableColumn<ObsChouette, Integer> id;
    @FXML
    private TableColumn<ObsChouette, TypeObservation> typeObs;
    @FXML
    private TableColumn<ObsChouette, Boolean> protocole;
    @FXML
    private TableColumn<ObsChouette, String> numIndividu;
    @FXML
    private TableView<ObsChouette> tab;

    @FXML
    private TextField rechercheTF;
    @FXML
    private ComboBox<String> typeObsCB;
    @FXML
    private ComboBox<String> protocoleCB;

    private Utilitaire util = new Utilitaire();

    @FXML
    private Button retour;


    @FXML
    void chooseObs(MouseEvent event) {

        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                Scene sc = ((Node) event.getSource()).getScene();
                Parent root;
                
                String url = "..//View//frame//AffichageObservationChouette.fxml";
                try {
                    // change the scene
                    FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
                    root = fx.load();
                    ListenerAffichageObsChouette lu = fx.getController();                    
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
        ObsChouetteBdd data = new ObsChouetteBdd();

        ArrayList<ObsChouette> obs = data.builder(data.getAllChouetteToBuild());

        ObservableList<String> typeObsList = FXCollections.observableArrayList(data.getAllBatracienTypeObs());
        typeObsCB.setItems(typeObsList);
        typeObsCB.getSelectionModel().select("");

        ObservableList<String> protocoleList = FXCollections.observableArrayList(data.getAllBatracienProtocole());
        protocoleCB.setItems(protocoleList);
        protocoleCB.getSelectionModel().select("");
        
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
        String typeObsString = typeObsCB.getSelectionModel().getSelectedItem();
        String protocoleString = protocoleCB.getSelectionModel().getSelectedItem();

        ObsChouetteBdd data = new ObsChouetteBdd();

        ArrayList<ObsChouette> obs = data.builder(data.getFilteredChouette(rechercheString, typeObsString, protocoleString));
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
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsChouette");
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
