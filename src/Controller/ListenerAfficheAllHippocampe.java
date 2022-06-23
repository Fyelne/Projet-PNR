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
import javafx.scene.layout.HBox;

public class ListenerAfficheAllHippocampe implements Initializable{

    @FXML
    private HBox HMenu;
    @FXML
    private Button quit;
    @FXML
    private Button user;
    @FXML
    private Button admin;

    
    @FXML
    private TableColumn<ObsHippocampe, Date> date;
    @FXML
    private TableColumn<ObsHippocampe, IndiceLoutre> indice;
    @FXML
    private TableColumn<ObsHippocampe, Integer> id;
    @FXML
    private TableColumn<ObsHippocampe, EspeceHippocampe> espece;
    @FXML
    private TableColumn<ObsHippocampe, Peche> typePeche;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<ObsHippocampe, String> commune;
    @FXML
    private TableView<ObsHippocampe> tab;
    @FXML
    private TextField rechercheTF;
    @FXML
    private ComboBox<String> especeCB;
    @FXML
    private ComboBox<String> typePecheCB;

    private Utilitaire util = new Utilitaire();

    @FXML
    private Button retour;

    /**
     * C'est une fonction qui est appelée lors d'un double clic sur une ligne d'un tableau.
     * Choisi vers quelle observation d'hippocampe il faut aller.
     * @param event l'événement qui a déclenché la méthode - double click sur une observation (ligne d'un tableau)
     */
    @FXML
    void chooseObs(MouseEvent event) {

        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                ObsHippocampe l = tab.getSelectionModel().getSelectedItem();
                Scene sc = ((Node) event.getSource()).getScene();
                Parent root;
                
                String url = "/frame/AffichageObservationHippocampe.fxml";
                try {
                    // change the scene
                    FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
                    root = fx.load();
                    ListenerAffichageObsHippocampe lu = fx.getController();
                    lu.load(l);
                    sc.setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * Initialise les données dans la tableView
     * @param location l'emplacement du fichier FXML
     * @param resources Faisceau de ressources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObsHippocampeBdd data = new ObsHippocampeBdd();

        ArrayList<ObsHippocampe> obs = data.builder(data.getAllHippocampeToBuild());

        initializeData(obs);

        ObservableList<String> especeList = FXCollections.observableArrayList(data.getAllHippocampeEspece());
        especeCB.setItems(especeList);
        especeCB.getSelectionModel().select("");

        ObservableList<String> typePecheList = FXCollections.observableArrayList(data.getAllHippocampeTypePeche());
        typePecheCB.setItems(typePecheList);
        typePecheCB.getSelectionModel().select("");
    }

    /**
     * Prend une ArrayList d'objets ObsHippocampe et les place dans une TableView
     * @param obs TableauListe des ObsHippocampe
     */
    private void initializeData(ArrayList<ObsHippocampe> obs){
        ObservableList<ObsHippocampe> tr = FXCollections.observableArrayList(obs);

        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        typePeche.setCellValueFactory(new PropertyValueFactory<>("typePeche"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        espece.setCellValueFactory(new PropertyValueFactory<>("espece"));

        tab.setItems(tr);
    }
 
    /**
     * Si l'utilisateur appuie sur la touche entrée ou si le champ de texte est vide, alors appelez la
     * fonction filtre().
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
        ObsHippocampeBdd data = new ObsHippocampeBdd();
        String especeString = especeCB.getSelectionModel().getSelectedItem();
        String typePecheString = typePecheCB.getSelectionModel().getSelectedItem();

        ArrayList<ObsHippocampe> obs = data.builder(data.getFilteredHippocampe(rechercheString, especeString, typePecheString));
        initializeData(obs);
    }

    /**
     * "Lorsque le bouton est cliqué, la scène change en scène de consultation."
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
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsHippocampe");
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
