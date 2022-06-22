/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Controller;

import Modele.requete.Utilisateur;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

/**
*
* @author Cool IT Help
*/
public class GestionUtilisateur implements Initializable {
    @FXML
    private AnchorPane Menu;
    
    @FXML
    private Label label;
    
    @FXML 
    TableView<Utilisateur> tableview;   
    
    ObservableList<Utilisateur> data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Editable cell in tableview   
        TableColumn<Utilisateur,String> nomCol = new TableColumn<Utilisateur,String>("Nom");
        nomCol.setEditable(true);
        TableColumn<Utilisateur,String> prenomCol = new TableColumn<Utilisateur,String>("Prenom");
        TableColumn<Utilisateur,String> emailCol = new TableColumn<Utilisateur,String>("Email"); 
        TableColumn<Utilisateur,String> droitsCol = new TableColumn<Utilisateur,String>("Droits");
        TableColumn<Utilisateur,String> deleteCol = new TableColumn<Utilisateur,String>("Supprimer");
        
        tableview.getColumns().addAll(nomCol,prenomCol,emailCol,droitsCol,deleteCol);
        
        // data = FXCollections.observableArrayList(
        // new Person("Jacob", "Smith", "jacob.smith@example.com", "Good"),
        // new Person("Isabella", "Johnson", "isabella.johnson@example.com", "Good"),
        // new Person("Ethan", "Williams", "ethan.williams@example.com", "Good"),
        // new Person("Emma", "Jones", "emma.jones@example.com", "Good"),
        // new Person("Michael", "Brown", "michael.brown@example.com", "Good"),
        // new Person("Michael", "Brown", "michael.brown@example.com", "Good"),
        // new Person("Michael", "Brown", "michael.brown@example.com", "Good"),
        // new Person("Michael", "Brown", "michael.brown@example.com", "Good")
        // );
        data = FXCollections.observableArrayList();
        try{
            Connection conn = SQLConnect.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nom,prenom FROM Observateur WHERE nom IS NOT NULL AND prenom IS NOT NULL");
            while(rs.next()){
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                Utilisateur p = new Utilisateur(nom, prenom, "email", "Utilisateur");
                data.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
        
        
        nomCol.setCellValueFactory(
            new PropertyValueFactory<Utilisateur,String>("Nom")
        );

        prenomCol.setCellValueFactory(
        new PropertyValueFactory<Utilisateur,String>("Prenom")
        );
        prenomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomCol.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setPrenom(e.getNewValue()));         

        emailCol.setCellValueFactory(     
        new PropertyValueFactory<Utilisateur,String>("Email")
        );    
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue()));         
        
        droitsCol.setCellValueFactory(     
        new PropertyValueFactory<Utilisateur,String>("Droits")
        ); 
        
        deleteCol.setCellValueFactory(
        new PropertyValueFactory<Utilisateur,String>("Supprimer")
        );
        
        tableview.setEditable(true);

        tableview.setItems(data);
                       
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

}
