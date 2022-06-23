package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.donnee.IndiceLoutre;
import Modele.donnee.ObsLoutre;
import Modele.requete.ObsLoutreBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;

public class ListenerAfficheAllLoutre implements Initializable{

    @FXML
    private HBox HMenu;
    @FXML
    private Button quit;
    @FXML
    private Button user;
    @FXML
    private Button admin;


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

    /**
     * C'est une fonction qui est appelée lors d'un double clic sur une ligne d'un tableau.
     * Choisi vers quelle observation de loutre il faut aller.
     * @param event l'événement qui a déclenché la méthode - double click sur une observation (ligne d'un tableau)
     */
    @FXML
    void chooseObs(MouseEvent event) {

        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                ObsLoutre l = tab.getSelectionModel().getSelectedItem();
                Scene sc = ((Node) event.getSource()).getScene();
                Parent root;
                
                String url = "/frame/AffichageObservationLoutre.fxml";
                try {
                    // change the scene
                    FXMLLoader fx = new FXMLLoader(getClass().getResource(url));
                    root = fx.load();
                    ListenerObsLoutre lu = fx.getController();
                    lu.load(l);
                    sc.setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Double clicked");
            }
        }
    }


    /**
     * J'essaie d'obtenir les données de la base de données et de les mettre dans la table.
     * @param location l'emplacement du fichier FXML
     * @param resources les ressources utilisées pour localiser l'objet racine, ou null si l'objet
     * racine n'a pas été localisé.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObsLoutreBdd data = new ObsLoutreBdd();

        ArrayList<ObsLoutre> obs = data.builder(data.getAllLoutreToBuild());
        
        initializeData(obs);
    }

    /**
     * Il prend une ArrayList d'objets ObsLoutre et les place dans une TableView
     * @param obs ArrayListe des ObsLoutre
     */
    private void initializeData(ArrayList<ObsLoutre> obs){
        ObservableList<ObsLoutre> tr = FXCollections.observableArrayList(obs);

        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        indice.setCellValueFactory(new PropertyValueFactory<>("indice"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        commune.setCellValueFactory(new PropertyValueFactory<>("commune"));

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
     * Prend le texte d'un champ de texte, l'utilise pour filtrer une base de données, puis met à
     * jour la vue de table avec les données filtrées
     */
    void filtre(){
        String rechercheString = rechercheTF.getText();
        ObsLoutreBdd data = new ObsLoutreBdd();

        ArrayList<ObsLoutre> obs = data.builder(data.getFilteredLoutre(rechercheString));
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
            lu.load(Utilitaire.getCurrentNameUser(), "ListeObsLoutre");
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
