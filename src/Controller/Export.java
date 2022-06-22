package Controller;


import java.io.*;
import java.sql.*;

/**
 * Constructeur de la classe export, cette classe permet de récuperer la base de données.
 */
public class Export {
    public static void exportTableToCSV(String tablename) {
        try {
            PrintWriter pw= new PrintWriter(new File("./" + tablename + ".csv"));
            StringBuilder sb=new StringBuilder();
            
            
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_pnr", "root", "root");            
            String query="select * from " + tablename;
            Statement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String[] columns = new String[columnsNumber];

            for(int i = 1; i <= columnsNumber; i++){
                if(i > 1){
                    sb.append(",");
                }
                sb.append(rsmd.getColumnName(i));
                columns[i-1]=rsmd.getColumnName(i);
            }
            sb.append("\r\n");

            while(rs.next()){
                for(int i = 1; i <= columnsNumber; i++){
                    if(i > 1){
                        sb.append(",");
                    }
                    sb.append(rs.getString(columns[i-1]));
                }
                sb.append("\r\n");
            }
            
            pw.write(sb.toString());
            pw.close();
            System.out.println("Export fini");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de créer les tables en format CSV.
     * @param tablenames nom des tables
     */
    public static void exportAllTableToCSV(String[] tablenames) {
        for(String tablename : tablenames){
            exportTableToCSV(tablename);
        }
    }
}
