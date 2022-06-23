package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Modele.Singleton;
import Modele.donnee.*;
import Modele.requete.ObservateurBdd;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ListenerGestionObs implements Initializable{

    @FXML
    private ComboBox<String> nom;
    @FXML
    private ComboBox<String> prenom;
    private Connection con;
    private TextField txt;
    @FXML
    private Button addnew;
    @FXML
    private TableColumn<Observateur, String> pre;

    @FXML
    private TableColumn<Observateur, String> no;
    @FXML
    private TableView<Observateur> table;

    private ObservateurBdd ob = new ObservateurBdd();

    private ArrayList<Observateur> listObserve;

    @FXML
    private Button val;
    private ListenerObs lelistener;


    /**
     * Essaie d'obtenir la valeur de l'élément sélectionné dans la liste déroulante, puis de
     * l'utiliser pour obtenir la valeur correspondante dans l'autre liste déroulante.
     * @param location l'emplacement du fichier FXML
     * @param resources Faisceau de ressources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        no.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.con = Singleton.getInstance().getConnection();
        this.listObserve = new ArrayList<Observateur>();
       
        String req = "SELECT * FROM observateur";
        PreparedStatement stmt;
        ResultSet rep = null;

        try {
            stmt = con.prepareStatement(req);
            rep = stmt.executeQuery();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Observateur> observe = ob.builder(rep);
        ArrayList<String> lesNom = new ArrayList<String>();
        ArrayList<String> lesPrenoms = new ArrayList<String>();
        for(Observateur o : observe){
            lesNom.add(o.getNom());
            if(o.getNom() == null){
                lesPrenoms.add(o.getPrenom());
            }
        }

        ObservableList<String> n = FXCollections.observableArrayList(lesNom);
        ObservableList<String> p = FXCollections.observableArrayList(lesPrenoms);

        prenom.setItems(p);
        nom.setItems(n);
        txt = nom.getEditor();
    }

    /**
     * Cette fonction est appelée par la classe principale pour obtenir le listener et la liste des
     * observateurs.
     * @param s l'auditeur
     * @param o TableauListe d'Observateur
     */
    void getControl(ListenerObs s, ArrayList<Observateur> o){
        this.lelistener = s;
        this.listObserve = o;
        this.updateTable();

    }

    /**
     * Prend l'élément sélectionné dans une liste déroulante et l'utilise pour interroger une base
     * de données, puis met à jour une autre liste déroulante avec les résultats de la requête
     * @param event ActionÉvénement
     */
    @FXML
    void updateNom(ActionEvent event) {
        ArrayList<String>  newPrenom = new ArrayList<String>();
        String name = nom.getSelectionModel().getSelectedItem();
        String req = "SELECT * FROM observateur WHERE nom = '" + name + "';";
        if(name == null){
            System.out.println("est Nulle");
            req = "SELECT * FROM observateur WHERE nom IS NULL;";
        }
        System.out.println(req);
        ObservateurBdd ob = new ObservateurBdd();
        ResultSet res = null;
        try {
            PreparedStatement stmt = con.prepareStatement(req);
            res = stmt.executeQuery();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        ArrayList<Observateur> lesNouvObs = ob.builder(res);
        for(Observateur o : lesNouvObs){
            newPrenom.add(o.getPrenom());
        }
        ObservableList<String> ne = FXCollections.observableArrayList(newPrenom);
        prenom.setItems(ne);
        prenom.getSelectionModel().select(0);
    }


    /**
     * Prend le texte d'un champ de texte, le recherche dans une base de données et affiche les
     * résultats dans une liste
     * @param event javafx.scene.input.KeyEvent
     */
    @FXML
    void proposeNom(KeyEvent event) {
        ArrayList<String> newNom = new ArrayList<String>();

        nom.setVisibleRowCount(10);
        String search = txt.getText().toUpperCase();
        System.out.println(search);

        String req = "SELECT * FROM observateur WHERE UPPER(nom) LIKE '"+search+"%';";
        System.out.println(req);
        ObservateurBdd ob = new ObservateurBdd();
        ResultSet res = null;

        try {
            PreparedStatement stmt = con.prepareStatement(req);
            res = stmt.executeQuery();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        ArrayList<Observateur> lesNouvObs = ob.builder(res);
        for(Observateur o : lesNouvObs){
            newNom.add(o.getNom());
        }
        ObservableList<String> ne = FXCollections.observableArrayList(newNom);
        nom.setItems(ne);
    }

    /**
     * Essaie d'ajouter un élément à un tableau, mais je ne peux pas car l'élément est déjà dans le
     * tableau.
     * @param event ActionÉvénement
     */
    @FXML
    void ajouterNew(ActionEvent event) {
        String name = nom.getSelectionModel().getSelectedItem();
        String pr = prenom.getSelectionModel().getSelectedItem();
        String req = "";
        if(name == null){
            req = "SELECT * FROM observateur WHERE nom IS NULL AND prenom = '" + pr +"';";
        }else{
            name = name.toUpperCase();
            req = "SELECT * FROM observateur WHERE UPPER(nom) = '" + name 
                    + "' AND prenom = '" + pr +"';";
        }

        PreparedStatement stmt;
        ResultSet res = null;
        try {
            stmt = con.prepareStatement(req);
            res = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            res.next();
            Observateur n = new Observateur(res.getInt("idObservateur"), res.getString("Nom"), res.getString("Prenom"));
            if(!(listObserve.contains(n))){
                this.updateTable(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un observateur à la liste des observateurs, puis il met à jour le tableau avec la
     * nouvelle liste des observateurs
     * @param obs l'objet qui est ajouté à la table
     */
    void updateTable(Observateur obs){
        listObserve.add(obs);
        ObservableList<Observateur> ne = FXCollections.observableArrayList(listObserve);

        table.setItems(ne);
    }

    /**
     * Prend la liste des observateurs et la met dans le tableau
     */
    void updateTable(){
        ObservableList<Observateur> ne = FXCollections.observableArrayList(listObserve);
        table.setItems(ne);
    }

    
    /**
     * Lorsque l'utilisateur clique sur le bouton "Valider", la liste des observateurs sélectionnés est
     * envoyée à la fenêtre principale, et la fenêtre est fermée.
     * 
     * @param event l'événement qui a déclenché la méthode
     */
    @FXML
    void valide(ActionEvent event) {
        this.lelistener.setListDesObs(this.listObserve);
        Button bt = (Button) event.getSource();
        Stage st = (Stage) bt.getScene().getWindow();
        st.close();
    }

}
