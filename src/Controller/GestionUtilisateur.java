/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Controller;

import Modele.donnee.Utilisateur;
import Modele.requete.UtilisateurBdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
*
* @author Cool IT Help
*/
public class GestionUtilisateur implements Initializable {
    @FXML
    private AnchorPane Menu;
    @FXML
    private TableColumn<Utilisateur, String> nom;
    @FXML
    private TableColumn<Utilisateur, String> prenom;
    @FXML
    private TableColumn<Utilisateur, String> telephone;
    @FXML
    private TableColumn<Utilisateur, ComboBox<String>> adminCB;
    @FXML
    private TableColumn<Utilisateur, Button> supprButton;
    @FXML
    private TextField nomTF;
    @FXML
    private TextField prenomTF;
    
    @FXML
    private Label label;
    
    @FXML 
    TableView<Utilisateur> tab;   
    
    ObservableList<Utilisateur> data;

    Connection con;

    Utilitaire util = new Utilitaire();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateurBdd data = new UtilisateurBdd();

        ArrayList<Utilisateur> obs = data.builder(data.getAllUtilisateurToBuild());
        
        initializeData(obs);
        // con = Singleton.getInstance().getConnection();

        // data = FXCollections.observableArrayList();
        // try{
        //     Statement stmt = con.createStatement();
        //     ResultSet rs = stmt.executeQuery("SELECT nom,prenom FROM Observateur WHERE nom IS NOT NULL AND prenom IS NOT NULL");
        //     while(rs.next()){
        //         String nom = rs.getString("nom");
        //         String prenom = rs.getString("prenom");
        //         Utilisateur p = new Utilisateur(nom, prenom, "email", "Utilisateur");
        //         data.add(p);
        //     }
        // } catch (SQLException e) {
        //     System.out.println("Connection Failed! Check output console");
        //     e.printStackTrace();
        // }
        
        
        // nomCol.setCellValueFactory(
        //     new PropertyValueFactory<Utilisateur,String>("Nom")
        // );

        // prenomCol.setCellValueFactory(
        // new PropertyValueFactory<Utilisateur,String>("Prenom")
        // );
        // prenomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // prenomCol.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrenom(e.getNewValue()));         

        // emailCol.setCellValueFactory(     
        // new PropertyValueFactory<Utilisateur,String>("Email")
        // );    
        // emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // emailCol.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue()));         
        
        // droitsCol.setCellValueFactory(     
        // new PropertyValueFactory<Utilisateur,String>("Droits")
        // ); 
        
        // deleteCol.setCellValueFactory(
        // new PropertyValueFactory<Utilisateur,String>("Supprimer")
        // );
        
        // tableview.setEditable(true);

        // tableview.setItems(data);
                       
    } 

    private void initializeData(ArrayList<Utilisateur> obs){
        ObservableList<Utilisateur> tr = FXCollections.observableArrayList(obs);

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        adminCB.setCellValueFactory(new PropertyValueFactory<>("adminCB"));

        supprButton.setCellValueFactory(new PropertyValueFactory<>("supprButton"));

        tab.setItems(tr);
    }
    
    /**
     * Permet d'acceder au menu qui donne des informations sur l'utilisateur 
     * @param event le bouton cliqué
     */
    @FXML
    void openUserMenu(ActionEvent event) {
        Menu.setVisible(true);
    }


    /**
     * Permet de quitter le menu qui donne des informations sur l'utilisateur 
     * @param event le bouton cliqué
     */
    @FXML
    void quitUserMenu(ActionEvent event) {
        Menu.setVisible(false);
    }

    @FXML
    void retourcons(ActionEvent event) {
        util.changeScene("Admin");
    }


    @FXML
    void recherche(KeyEvent event){
        TextField source = (TextField)event.getSource();
        if(event.getCode().equals(KeyCode.ENTER) || source.getText().equals("")){
            filter();
        }
    }

    void filter(){
        UtilisateurBdd data = new UtilisateurBdd();
        String nomString = nomTF.getText();
        String prenomString = prenomTF.getText();

        ArrayList<Utilisateur> obs = data.builder(data.getFilteredUtilisateur(nomString, prenomString));
        initializeData(obs);
    }

    @FXML
    void GoToAddUser(ActionEvent event) {
        util.changeScene("adduser");
    }

}
