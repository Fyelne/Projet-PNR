package Modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogBDD {

    private String url;
    private Connection con;
    private String user;
    private String pass;

    public LogBDD(String url, String user, String pass){
        if((url != null) && (user != null) && (pass != null)){
            this.url = url;
            this.user = user;
            this.pass = pass;
        }
    }

    public Connection connexion(){
        try{
            this.con = DriverManager.getConnection(this.url, this.user, this.pass);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println(e.getLocalizedMessage());
        
        }
        return this.con;
    }
    public static void main(String[] args) {
        LogBDD l = new LogBDD("jdbc:mysql://localhost/bd_PNR", "PNR", "PNR");
        Connection PNR = l.connexion();
        try {
            PreparedStatement  stmt = PNR.prepareStatement("SELECT * `obs_loutre` WHERE commune = 'ELVEN'; ");
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                
                int id = res.getInt("ObsL");
                String com = res.getString("commune");
                String lieu = res.getString("lieuDit");
                String indice = res.getString("indice");
                System.out.println("| " + id + " | " + com + " | " + lieu + " | " + indice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 


    }
}
