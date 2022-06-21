package Controller;

import java.sql.*;

public class SQLConnect {
    public static Connection connect() throws SQLException {
        try {
            Connection res = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_pnr", "root", "root");
            if (res != null) {
                System.out.println("Connection working");
            } else {
                System.out.println("Failed to make connection!");
            }
            return res;
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            throw e;
        }
    }
}
