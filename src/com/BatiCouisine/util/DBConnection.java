package com.BatiCouisine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection connectionInstance = null;
    private static Connection DBconnection;


    private DBConnection() {
         final String postgreURL = "jdbc:postgresql://localhost:5433/batiCuisine";
         final String DBUsername = "admin";
         final String DBPassword = "admin";

        try {
            this.DBconnection = DriverManager.getConnection(postgreURL, DBUsername, DBPassword);
            System.out.println("Connexion établie avec succès à PostgreSQL !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à PostgreSQL : " + e.getMessage());
        }
    }

    public static DBConnection getConnectionInstance() {
        if (connectionInstance == null) {
            connectionInstance = new DBConnection();
        }
        return connectionInstance;
    }

    public Connection getConnection() {
        return DBconnection;
    }
}
