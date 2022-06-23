package Modele;

import java.sql.*;

public class Singleton {
    private static Singleton instance = null;
    private static Connection con;
    /**
     * Singleton
     */
    private Singleton(){
        //importer l'url, le user et le pass d'une autre classe ici
        String url = "jdbc:mysql://localhost/bd_PNR";
        String user = "PNR";
        String pass = "PNR";

        try{
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println(e.getLocalizedMessage());
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
