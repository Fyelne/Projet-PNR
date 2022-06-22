package Controller;


import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ResourceBundle;

import Modele.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Constructeur de la classe export, cette classe permet de récuperer la base de données.
 */
public class Export implements Initializable {
    Utilitaire util = new Utilitaire();
    Connection con;

    @FXML
    Label exportLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        con = Singleton.getInstance().getConnection();
    }

    public void exportTableToCSV(String tablename, String path) {
        try {
            Files.createDirectories(Paths.get(path+"/Observations/"));
            PrintWriter pw= new PrintWriter(new File(path + "/observations/" + tablename + ".csv"));
            StringBuilder sb=new StringBuilder();
            
            String query = "select * from " + tablename;
            Statement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String[] columns = new String[columnsNumber];

            for(int i = 1; i <= columnsNumber; i++){
                if(i > 1){
                    sb.append(";");
                }
                sb.append(rsmd.getColumnName(i));
                columns[i-1]=rsmd.getColumnName(i);
            }
            sb.append("\r\n");

            while(rs.next()){
                for(int i = 1; i <= columnsNumber; i++){
                    if(i > 1){
                        sb.append(";");
                    }
                    sb.append(rs.getString(columns[i-1]));
                }
                sb.append("\r\n");
            }
            
            pw.write(sb.toString());
            pw.close();
            exportLabel.setText("Exportation réussie");            
        } catch (Exception e) {
            exportLabel.setText("Une erreur est survenue");
            e.printStackTrace();
        }
    }

    /**
     * Permet de créer les tables en format CSV.
     * @param tablenames nom des tables
     */
    public void exportAllTableToCSV(String[] tablenames, String path) {
        for(String tablename : tablenames){
            exportTableToCSV(tablename, path);
        }
    }

    @FXML
    void retourcons(ActionEvent event) {
        util.changeScene("Admin");
    }

    @FXML
    void exportAll(ActionEvent event){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Exporter...");

        File defaultDirectory = new File("C:/");
        chooser.setInitialDirectory(defaultDirectory);

        File selectedDirectory = chooser.showDialog(new Stage());
        String path = selectedDirectory.getAbsolutePath();

        String[] tables = new String[]{"Obs_Chouette", "Obs_GCI", "Obs_Hippocampe", "Obs_Batracien", "Obs_Loutre", "Nid_GCI", "Chouette", "Observateur", "Observation", "Lieu", "ZoneHumide", "Vegetation"};
        exportAllTableToCSV(tables, path);
    }
}
