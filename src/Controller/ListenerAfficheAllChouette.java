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


    /**
     * C'est une fonction qui est appelée lors d'un double clic sur une ligne d'un tableau.
     * Choisi vers quelle observation de chouette il faut aller.
     * @param event l'événement qui a déclenché la méthode - double click sur une observation (ligne d'un tableau)
     */
    @FXML
    void chooseObs(MouseEvent event) {

        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                Scene sc = ((Node) event.getSource()).getScene();
                Parent root;
                
                String url = "/frame/AffichageObservationChouette.fxml";
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


    /**
     * Initialise un ComboBox avec les données d'une base de données.
     * Utilise la classe appelée ObsChouetteBdd pour obtenir les données de la base de données.
     * Utilise la classe appelée ObsChouette pour stocker les données.
     * Utilise la classe appelée ObsChouetteController pour afficher les données.
     * Utilise la classe appelée ObsChouetteMain pour lancer le programme.
     * Utilise la classe appelée ObsChouetteModel pour stocker les données.
     * Utilise la classe appelée ObsChouetteView pour afficher les données.
     * Utilise la classe appelée ObsChouetteViewController pour afficher les données.
     * Utilise la classe appelée ObsChouetteViewModel pour stocker les données.
     * Utilise la classe appelée ObsChouetteViewModelController pour afficher les données.
     * Utilise la classe appelée ObsChouetteViewModelMain pour lancer le programme.
     * @param location l'emplacement du fichier FXML
     * @param resources les ressources utilisées pour localiser l'objet racine, ou null si l'objet
     * racine n'a pas été localisé.
     */
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

    /**
     * Si l'utilisateur appuie sur Entrée ou si le champ de texte est vide, appelez la fonction
     * filtre().
     * @param event L'événement qui a déclenché la méthode.
     */
    @FXML
    void recherche(KeyEvent event){
        TextField source = (TextField)event.getSource();
        if(event.getCode().equals(KeyCode.ENTER) || source.getText().equals("")){
            filtre();
        }
    }

    /**
     * Lorsque l'utilisateur sélectionne une nouvelle valeur dans la combo, appelez la fonction
     * filtre().
     * @param event L'événement qui a déclenché la méthode.
     */
    @FXML
    void comboBoxChange(ActionEvent event){
        filtre();
    }


    /**
     * Prend le texte d'un champ de texte, l'élément sélectionné d'une zone de liste déroulante et
     * l'élément sélectionné d'une autre zone de liste déroulante, puis il utilise ces valeurs pour
     * filtrer les données dans une vue de table
     */
    void filtre(){
        String rechercheString = rechercheTF.getText();
        String typeObsString = typeObsCB.getSelectionModel().getSelectedItem();
        String protocoleString = protocoleCB.getSelectionModel().getSelectedItem();

        ObsChouetteBdd data = new ObsChouetteBdd();

        ArrayList<ObsChouette> obs = data.builder(data.getFilteredChouette(rechercheString, typeObsString, protocoleString));
        initializeData(obs);
    }

    /**
     * "Lorsque le bouton est cliqué, changez la scène en scène de consultation."
     * @param event L'événement qui a déclenché l'action.
     */
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
        
        String url = "/frame/InfoUser.fxml";
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
